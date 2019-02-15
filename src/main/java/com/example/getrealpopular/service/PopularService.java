package com.example.getrealpopular.service;

import com.example.getrealpopular.dao.PopularDao;
import com.example.getrealpopular.pojo.Popular;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PopularService {

    @Resource
    private PopularDao popularDao;

    public List<Popular> getAvgByTime(String time) {
        return popularDao.getAvgByTime(time);
    }
}
