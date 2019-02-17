package com.example.getrealpopular.controller;

import com.example.getrealpopular.pojo.Popular;
import com.example.getrealpopular.service.PopularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class PopularController {

    private final PopularService popularService;

    private static LocalDate localDate;

    private static Integer todayVisitCount;

    private static Integer totalVisitCount;

    @Autowired
    public PopularController(PopularService popularService) {
        this.popularService = popularService;
        localDate = ZonedDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalDate();
        todayVisitCount = 0;
        totalVisitCount = 0;
    }

    @GetMapping("/")
    public String getNewPopular(Map<String, Object> map) {
        // 统计访问量
        LocalDate today = ZonedDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalDate();
        if (!localDate.isEqual(today)) {
            todayVisitCount = 0;
        }
        todayVisitCount++;
        totalVisitCount++;

        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.MINUTE) < 10) {
            calendar.add(Calendar.MINUTE, -10);
        }
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = hour - hour % 2;
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
        Date refreshDate = calendar.getTime();
        String refreshTime = sdf.format(refreshDate);
        List<Popular> popularList = popularService.getAvgByTime(refreshTime);
        map.put("popularList", popularList);
        sdf = new SimpleDateFormat("MM-dd HH:mm");
        String showTime = sdf.format(refreshDate);
        map.put("refreshTime", showTime);
        map.put("todayVisitCount", todayVisitCount);
        map.put("totalVisitCount", totalVisitCount);
        return "index";
    }
}
