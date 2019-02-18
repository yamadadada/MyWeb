package com.example.getrealpopular.dao;

import com.example.getrealpopular.pojo.Visit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VisitDao {

    @Insert("insert into `visit`(`remote_address`, `remote_host`, `remote_port`, `user-agent`) values(#{remoteAddress}, #{remoteHost}, #{remotePort}, #{userAgent})")
    void add(Visit visit);

    @Select("SELECT count(*) FROM `visit` where TO_DAYS(time) = TO_DAYS(NOW())")
    Integer getTodayCount();

    @Select("select count(*) from visit")
    Integer getTotalCount();
}
