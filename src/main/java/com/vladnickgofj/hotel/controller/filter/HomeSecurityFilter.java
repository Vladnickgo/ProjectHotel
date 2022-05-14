package com.vladnickgofj.hotel.controller.filter;


import com.vladnickgofj.hotel.PagesConstant;
import com.vladnickgofj.hotel.dao.entity.Role;
import com.vladnickgofj.hotel.dao.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/home/*")
public class HomeSecurityFilter implements Filter {
    Logger logger = Logger.getLogger(HomeSecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Init HomeSecurityFilter>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        logger.info("doFiler HomeSecurityFilter*******************");
        User user = (User) req.getSession().getAttribute("user");
        String command = req.getParameter("command");
        if ((user == null || !Role.USER.equals(user.getRole())) && command.equals("show-profile")) {
            resp.sendRedirect(PagesConstant.NOT_AUTHORIZED_USER_PAGE);
        } else {
            chain.doFilter(req, response);
        }

    }

    @Override
    public void destroy() {

    }
}
