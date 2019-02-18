package com.example.getrealpopular.dao;

import com.example.getrealpopular.pojo.Visit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class VisitDaoTest {

    @Resource
    private VisitDao visitDao;

    @Test
    public void add() {
        Visit visit = new Visit();
        visit.setRemoteAddress("111.111.112.113");
        visit.setRemoteHost("www.qq.com");
        visit.setRemotePort(1234);
//        visitDao.add(visit);
    }

    @Test
    public void getTodayCount() {
        Integer count = visitDao.getTodayCount();
        log.info("【今日访客】" + count);
    }

    @Test
    public void getTotalCount() {
        Integer count = visitDao.getTotalCount();
        log.info("【总访客】" + count);
    }
}