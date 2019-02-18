package com.example.getrealpopular.filter;

import com.example.getrealpopular.dao.VisitDao;
import com.example.getrealpopular.pojo.Visit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CommonFilter", urlPatterns = "/*")
@Component
public class CommonFilter implements Filter {

    @Resource
    private VisitDao visitDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        if (session.getAttribute("first") == null) {
            session.setAttribute("first", false);
            Visit visit = new Visit();
            visit.setRemoteAddress(servletRequest.getRemoteAddr());
            visit.setRemoteHost(servletRequest.getRemoteHost());
            visit.setRemotePort(servletRequest.getRemotePort());
            visit.setUserAgent(((HttpServletRequest) servletRequest).getHeader("user-agent"));
            visitDao.add(visit);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
