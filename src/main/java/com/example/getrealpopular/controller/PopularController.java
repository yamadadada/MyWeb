package com.example.getrealpopular.controller;

import com.example.getrealpopular.pojo.Popular;
import com.example.getrealpopular.service.PopularService;
import com.example.getrealpopular.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class PopularController {

    private final PopularService popularService;

    private final VisitService visitService;

    @Autowired
    public PopularController(PopularService popularService, VisitService visitService) {
        this.popularService = popularService;
        this.visitService = visitService;
    }

    @GetMapping("/")
    public String getNewPopular(Map<String, Object> map) {
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
        // 获得访问量
        map.put("todayVisitCount", visitService.getTodayCount());
        map.put("totalVisitCount", visitService.getTotalCount());
        return "index";
    }
}
