package com.example.getrealpopular.service;

import com.example.getrealpopular.dao.VisitDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VisitService {

    @Resource
    private VisitDao visitDao;

    public Integer getTodayCount() {
        return visitDao.getTodayCount();
    }

    public Integer getTotalCount() {
        return visitDao.getTotalCount();
    }
}
