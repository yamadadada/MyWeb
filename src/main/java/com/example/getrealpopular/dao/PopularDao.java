package com.example.getrealpopular.dao;

import com.example.getrealpopular.pojo.Popular;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PopularDao {

    @Select("select id, result.gid, result.game, avg(`value`) `avg_value`, huya `hy_id` from result, init where " +
            "init.gid=result.gid and time=#{time} group by id, gid, game order by value desc")
    List<Popular> getAvgByTime(String time);
}
