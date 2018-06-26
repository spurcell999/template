package com.project.template.api.rest.controllers;

import com.project.template.api.rest.BaseRest;
import com.project.template.domain.HttpTraceInfo;
import com.project.template.repositories.HttpTraceInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Slf4j
public class RestControllerInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    HttpTraceInfoRepository repository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Rest Interceptor preHandle. request payload {}"+ request.getReader());
        HttpTraceInfo traceInfo=new HttpTraceInfo();
        traceInfo.setSession(request.getSession().getId());
        traceInfo.setPartnerId(request.getHeader(BaseRest.X_OPENAPI_CLIENTID));
        traceInfo.setOpenApiRequestId(request.getHeader(BaseRest.PARTNER_ID));
        traceInfo.setHttpRequest(request.getReader().toString());
        log.debug("Request Trace Info {}", traceInfo.toString());
        repository.save(traceInfo);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("Rest Interceptor postHandle. response payload {}"+ response.getWriter());
        HttpTraceInfo traceInfo=repository.findBySession(request.getSession().getId());
        traceInfo.setHttpRequest(response.getWriter().toString());
        traceInfo.setStatus(response.getStatus());
        log.debug("Response Trace Info {}", traceInfo.toString());
        repository.save(traceInfo);
        super.postHandle(request, response, handler, modelAndView);
    }
}
