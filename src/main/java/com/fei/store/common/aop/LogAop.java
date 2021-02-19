package com.fei.store.common.aop;

import com.fei.store.security.JwtTokenUtil;
import com.fei.store.service.LogService;
import com.fei.store.vo.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private LogService logService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private Date visitTime;
    private Class clazz;
    private Method method;

    //前置通知 获取开始时间 执行的类 执行的方法
    @Before("execution(* com.fei.store.controller.*.*(..)) ")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        method = methodSignature.getMethod();
    }

    //后置通知
    @After("execution(* com.fei.store.controller.*.*(..)))")
    public void doAfter(JoinPoint jp) {
        int time = (int) (new Date().getTime() - visitTime.getTime());
        String url = "";
        if (StringUtils.isEmpty(RequestContextHolder.getRequestAttributes())){
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取url
        if (clazz != null && method != null) {
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] value = classAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] value1 = methodAnnotation.value();
                    url = value[0] + "/" + value1[0];

                    //获取ip
                    String ip = request.getRemoteAddr();
                    //获取用户
                    String username="";
                    String token = "";

                    if (StringUtils.isEmpty(request.getHeader(tokenHeader))) username="用户名后密码在修改";
                    else {
                        token= request.getHeader(tokenHeader).substring(7);
                        username = jwtTokenUtil.getUserNameFromJwtToken(token);
                    }
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("类名" + clazz.getName() + "方法名" + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    logService.save(sysLog);
                }
            }
        }
    }
}
