package com.realtimetpo.controllers;

import com.realtimetpo.daos.*;
import com.realtimetpo.entities.*;
import com.realtimetpo.factories.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegController extends HttpServlet {

    public void init(ServletConfig cnf)throws ServletException
    {
        super.init(cnf);
    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
    //read all incoming req params from registration.html
    	String userid= request.getParameter("userid");
     String username = request.getParameter("username");
     String password = request.getParameter("password");
     String department=request.getParameter("dept");
     String name = request.getParameter("name");
     String mobile = request.getParameter("mobile");
     
    //give these values to Users Entity
        Users user = EntityFactory.getUser();
        user.setUserid(Integer.valueOf(userid));
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setMobile(mobile);
        user.setDepartment(department);
    //pass Users entity to DAO, tell him to insertUser
      UsersDAO udao = DAOFactory.getUsersDAO();
      boolean success = udao.insertUser(user);
      
    //read the response from DAO, show output to client 
    PrintWriter out = response.getWriter();
    RequestDispatcher rd = null;
    response.setContentType("text/html");
    
    if(success)
    {
        out.println("<font color=blue>Registration Successful. Please Login</font><br/>");
        rd = request.getRequestDispatcher("login.jsp");
        rd.include(request,response);
    }
    else
    {
        out.println("<font color=red>Unable to Register. Please Try again</font><br/>");
        rd = request.getRequestDispatcher("registration.html");
        rd.include(request,response);
    }
    }
    public void destroy()
    {
        System.out.println("Destroying Reg Controller!!");
    }
}
