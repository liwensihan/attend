package com.attend.attendance.service;

import com.attend.attendance.entity.Attend;
import com.attend.attendance.vo.QueryCondition;
import com.attend.common.page.PageQueryBean;

/**
 * Created by
 * 白夜行 on 2018/4/27.
 */
public interface AttendService {

    public void signAttend(Attend attend) throws Exception;

    PageQueryBean listAttend(QueryCondition queryCondition);
}
