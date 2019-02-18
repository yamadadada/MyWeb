package com.example.getrealpopular.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Visit {

    private String remoteAddress;

    private String remoteHost;

    private Integer remotePort;

    private String userAgent;

    private Date time;
}
