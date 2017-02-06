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


public class Logout extends HttpServlet {

    public void init(ServletConfig cnf)throws ServletException
    {
        super.init(cnf);
    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
    	 response.setContentType("text/html");  
    	 response.setHeader("Cache-Control","no-cache");
    	 response.setHeader("Cache-Control","no-store");
    	 response.setHeader("Pragma","no-cache");
    	 response.setDateHeader ("Expires", 0);

         PrintWriter out=response.getWriter();  
         out.print("<br><center><h3 style='color:red;' >You are successfully logged out!Please login to continue</h3></center>");  
         request.getRequestDispatcher("login.jsp").include(request, response);  
           
         HttpSession session=request.getSession();  
         session.invalidate();  

/*response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires", 0);*/
        // out.println("You are successfully logged out!"); 
         
     		/*out.print("<br><center><font color=red>You are successfully logged out!Please login to continue</font></center>");*/
         
           
         out.close();     
    }
    public void destroy()
    {
        System.out.println("Destroying Login Controller");
    }
}