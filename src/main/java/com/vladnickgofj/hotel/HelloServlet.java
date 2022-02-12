package com.vladnickgofj.hotel;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static final String index = "/WEB-INF/index.jsp";
//    private String message;
//    private String message2;
//
//    public void init() {
//        message = "Hello World!";
//        message2="Best regards from Servlet";
//}

    @Override
    public void init() throws ServletException {
        System.out.println("===================SERVLET IS INIT======================");
        System.out.println("====for path:" + index + "====");
    }

    @Override
    public void destroy() {
        System.out.println("===================SERVLET IS DESTROY======================");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html");
        System.out.println("=======================DO GET===========================");
        System.out.println("===" + request);
//
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("<h2>" + message2 + "</h2>");
//        out.println("<a href='/Hotel_war_exploded/'>Back</h2>");
//        out.println("</body></html>");

        request.getRequestDispatcher(index).forward(request, response);
    }

    //public void doPost(HttpServletRequest request,HttpServletResponse response)
//        throws IOException, ServletException{
//        processRequest(request,response);
//}
//
//    private void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException,IOException{
//    }
//


}