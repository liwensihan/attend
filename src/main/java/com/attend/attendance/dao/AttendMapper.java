package com.attend.attendance.dao;


import com.attend.attendance.entity.Attend;
import com.attend.attendance.vo.QueryCondition;

import java.util.List;

public interface AttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectTodaySignRecord(Long userId);

    int countByCondition(QueryCondition condition);

    List<Attend> selectAttendPage(QueryCondition condition);

    List<Long> selectTodayAbsence();

    void batchInsert(List<Attend> attendList);

    List<Attend> selectTodayEveningAbsence();
}