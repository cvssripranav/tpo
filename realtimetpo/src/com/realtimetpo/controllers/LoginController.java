package com.realtimetpo.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.realtimetpo.daos.*;
import com.realtimetpo.entities.*;
import com.realtimetpo.factories.*;


public class LoginController extends HttpServlet {

    public void init(ServletConfig cnf)throws ServletException
    {
        super.init(cnf);
    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        //read uname/pwd from index.html
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        //supply this uname and pwd to Users
        Users user = EntityFactory.getUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setName("na");
        user.setDepartment("na");
        user.setUserid(0);
        //pass users obj to DAO ask him to check credentials
        UsersDAO udao = DAOFactory.getUsersDAO();
        user = udao.checkUserLogin(user);
                
        //read response from dao and take decision
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        RequestDispatcher rd = null;
        
        if(user.getName().equals("na"))
        {   rd = request.getRequestDispatcher("login.jsp");
            rd.include(request, response);
            out.println("<center><font color=red>wrong username / password</font></center>");
        }
        else if(!user.getName().equals("na")&& !user.getDepartment().equalsIgnoreCase("admin"))
        {
            HttpSession usession = request.getSession(true);
            usession.setAttribute("username", username);
            usession.setAttribute("name", user.getName());
            usession.setAttribute("department", user.getDepartment());
            usession.setAttribute("rno", user.getUserid());
            System.out.println(usession.getAttribute("rno"));
            response.sendRedirect("StdentDetails.jsp?rno="+usession.getAttribute("username")+"&department="+usession.getAttribute("department"));
        }   
        else{
        	
        	 HttpSession usession = request.getSession(true);
             usession.setAttribute("username", username);
             usession.setAttribute("name", user.getName());
             usession.setAttribute("department", user.getDepartment());
             
             response.sendRedirect("trytpo.jsp");
        	
        }
    }
    public void destroy()
    {
        System.out.println("Destroying Login Controller");
    }
}