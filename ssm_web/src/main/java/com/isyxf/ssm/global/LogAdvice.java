package com.isyxf.ssm.global;

import com.isyxf.ssm.entity.Log;
import com.isyxf.ssm.entity.Staff;
import com.isyxf.ssm.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class LogAdvice {
    @Autowired
    private LogService logService;

    /**
     * 操作日志 方法执行后都进行一次记录操作
     * @param joinPoint
     */
    @After("execution(* com.isyxf.ssm.controller.*.*(..)) && !execution(* com.isyxf.ssm.controller.SelfController.*(..)) && !execution(* com.isyxf.ssm.controller.SelfController.*.to*(..))")
    private void operationLog(JoinPoint joinPoint) {
         Log log = new Log();
         log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
         log.setOperation(joinPoint.getSignature().getName());

        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();

        Object obj = session.getAttribute("USER");
        Staff staff = (Staff)obj;

        log.setOperator(staff.getAccount());
        log.setResult("成功");

        logService.addOperationLog(log);
    }

    /**
     * 系统日志 方法错误后都进行一次记录操作
     * @param joinPoint
     */
    @AfterThrowing(throwing = "e", pointcut = "execution(* com.isyxf.ssm.controller.*.*(..)) && !execution(* com.isyxf.ssm.controller.SelfController.*(..))")
    private void systemLog(JoinPoint joinPoint, Throwable e) {
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());

        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();

        Object obj = session.getAttribute("USER");
        Staff staff = (Staff)obj;

        log.setOperator(staff.getAccount());
        log.setResult(e.getClass().getSimpleName());

        logService.addSystemLog(log);
    }

    /**
     * 登录日志 方法执行后都进行一次记录操作
     * @param joinPoint
     */
    @After("execution(* com.isyxf.ssm.controller.SelfController.login(..))")
    private void loginLog(JoinPoint joinPoint) {
        this.log(joinPoint);
    }

    /**
     * 退出日志 方法执行后都进行一次记录操作
     * @param joinPoint
     */
    @Before("execution(* com.isyxf.ssm.controller.SelfController.logout(..))")
    private void logoutLog(JoinPoint joinPoint) {
        this.log(joinPoint);
    }

    private void log(JoinPoint joinPoint) {
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());

        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();

        Object obj = session.getAttribute("USER");

        if (obj == null) {
            log.setOperator(request.getParameter("account"));
            log.setResult("失败");
        }else {
            Staff staff = (Staff)obj;
            log.setOperator(staff.getAccount());
            log.setResult("成功");
        }

        logService.addLoginLog(log);
    }
}
