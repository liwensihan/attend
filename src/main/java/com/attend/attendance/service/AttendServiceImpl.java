package com.attend.attendance.service;
import com.alibaba.druid.sql.visitor.functions.If;
import com.attend.attendance.dao.AttendMapper;
import com.attend.attendance.entity.Attend;
import com.attend.attendance.vo.QueryCondition;
import com.attend.common.page.PageQueryBean;
import com.attend.common.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by
 * 白夜行 on 2018/4/27.
 */
@Service
public class AttendServiceImpl implements AttendService {

    /**
     * 中午十二点判定上下午
     */
    private static final int NOON_HOUR =12 ;
    /**
     * 分钟为整数
     */
    private static final int NOON_MUNITE =00;
    Log log = LogFactory.getLog(AttendServiceImpl.class);
 private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private AttendMapper attendMapper;
    @Override
    public void signAttend(Attend attend) throws Exception {
        try {
            Date today = new Date();
            attend.setAttendDate(today);
            attend.setAttendWeek((byte)DateUtils.getTodayWeek());
           /* Attend todayRecord=attendMapper.selectTodaySignRecord(attend.getUserId());*/
            Attend todayRecord=attendMapper.selectTodaySignRecord(attend.getUserId());
            //设置中午时间
            Date noon = DateUtils.getDate(NOON_HOUR,NOON_MUNITE);
            if (todayRecord == null) {
                if (today.compareTo(noon)<=0){
                    //打卡时间 早于12点
                    attend.setAttendMorning(today);
                }else{
                    //下午打卡
                    attend.setAttendEvening(today);
                }
                attendMapper.insertSelective(attend);
            }else{
                if (today.compareTo(noon)<=0){
                    //打卡时间 早于12点
                    return;
                }else{
                    //下午打卡
                    todayRecord.setAttendEvening(today);
                    attendMapper.updateByPrimaryKeySelective(todayRecord);
                }
            }
            //中午十二点之前打开 都算早晨打卡 9.30以后算迟到
            // 中午十二点以后都算下午打卡
            //下午打卡 检查与上午打卡的时间差 18点之前 算异常 不足八小时都算异常 并且 缺席多少时间 都要存进去
            attendMapper.insertSelective(attend);
        } catch (Exception e) {
            log.error("用户签到异常",e);
            throw e;
        }
    }

    @Override
    public PageQueryBean listAttend(QueryCondition queryCondition) {
        //根据条件查询得到count记录数目
       int count =  attendMapper.countByCondition(queryCondition);
        PageQueryBean pageQueryBean = new QueryCondition();
        //有记录才分页查询
        if (count>0 ) {
            pageQueryBean.setTotalRows(count);
            pageQueryBean.setCurrentPage(queryCondition.getCurrentPage());
            pageQueryBean.setPageSize(queryCondition.getPageSize());
            List<Attend> attendList =  attendMapper.selectAttendPage(queryCondition);
            pageQueryBean.setItems(attendList);
        }
        return pageQueryBean;
    }
}
