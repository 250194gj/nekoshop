package com.fashionstore.interceptor;

import com.fashionstore.model.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle( HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler)throws Exception {
        String uri = request.getRequestURI();
        String path = request.getContextPath();
        if (!path.isEmpty()) {
            uri = uri.substring(path.length());
        }
        HttpSession session = request.getSession(false);
        Role role = null;
        if (session != null) {
            role = (Role) session.getAttribute("role");
        }
        // =========================
        // ADMIN
        // =========================
        if (uri.startsWith("/admin")){
        if (role == Role.ADMIN) {
            return true;
            }
        response.sendRedirect(request.getContextPath()+"/login");
        return false;
        }
        // =========================
        // ADMIN & STAFF
        // =========================
        if (uri.startsWith("/Staff")){
            if (role == Role.ADMIN || role == Role.STAFF) {
                return true;
            }
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        // =========================
        // 誰でも
        // =========================
        if (uri.startsWith("/cart")||
                uri.startsWith("/account")||
                uri.startsWith("/order")){
            if (role == Role.ADMIN || role == Role.STAFF || role == Role.USER) {
                return true;
            }
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return true;
    }

}
