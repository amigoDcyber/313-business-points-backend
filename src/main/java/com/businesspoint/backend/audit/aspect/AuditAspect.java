package com.businesspoint.backend.audit.aspect;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.audit.entity.AuditLog;
import com.businesspoint.backend.audit.repository.AuditLogRepository;
import com.businesspoint.backend.auth.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;

    @Around("@annotation(auditAction)")
    public Object logAuditAction(ProceedingJoinPoint joinPoint, AuditAction auditAction) throws Throwable {
        Object result = joinPoint.proceed();
        saveAuditLog(auditAction);
        return result;
    }

    private void saveAuditLog(AuditAction auditAction) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(auditAction.action());
        auditLog.setEntityType(auditAction.entityType());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            auditLog.setUserId(userDetails.getUser().getId());
        }

        HttpServletRequest request = getRequest();
        if (request != null) {
            auditLog.setIpAddress(request.getRemoteAddr());
            auditLog.setDeviceInfo(request.getHeader("User-Agent"));
        }

        auditLogRepository.save(auditLog);
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (attr != null) ? attr.getRequest() : null;
    }
}
