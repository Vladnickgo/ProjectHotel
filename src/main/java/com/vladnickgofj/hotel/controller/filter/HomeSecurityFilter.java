package com.vladnickgofj.hotel.controller.filter;


import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.service.exception.AuthorisationFailException;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.NOT_AVAILABLE_PAGE;

@WebFilter(urlPatterns = "/home/*")
public class HomeSecurityFilter implements Filter {
    public static final Logger LOGGER = Logger.getLogger(HomeSecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("Init HomeSecurityFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, AuthorisationFailException {
        HttpServletRequest req = (HttpServletRequest) request;
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        LOGGER.info("user: " + user);
        String command = req.getParameter("command");
        if ((user == null ) && ("showUserProfile".equals(command)||"showAdminProfile".equals(command)||"usersOrderPage".equals(command)||"orderHandlerPage".equals(command))) {
            LOGGER.info("AuthorisationFailException("+NOT_AVAILABLE_PAGE+")");
            throw new AuthorisationFailException(NOT_AVAILABLE_PAGE);
        }
        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {
    }
}
