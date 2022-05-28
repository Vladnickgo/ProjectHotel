package com.vladnickgofj.hotel.controller.filter;


import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.entity.Role;
import com.vladnickgofj.hotel.service.exception.AuthorisationFailException;
import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.NOT_AVAILABLE_PAGE;

@WebFilter(urlPatterns = "/home/*")
public class HomeSecurityFilter implements Filter {
    public static final Logger logger = Logger.getLogger(HomeSecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig){
        logger.info("Init HomeSecurityFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, AuthorisationFailException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        logger.info("doFiler");
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        String command = req.getParameter("command");
        if ((user == null || !Role.USER.equals(user.getRole())) && "show-profile".equals(command)) {
            throw new AuthorisationFailException(NOT_AVAILABLE_PAGE);
        }
        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {
    }
}
