package com.realtimetpo.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.realtimetpo.daos.EligibilityDao;
import com.realtimetpo.entities.Eligibility;
import com.realtimetpo.factories.DAOFactory;
import com.realtimetpo.factories.EntityFactory;

public class StudentPercentController extends HttpServlet {

    public void init(ServletConfig cnf)throws ServletException
    {
        super.init(cnf);
    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        //read uname/pwd from index.html
        String percent = request.getParameter("percent");
       // String password = request.getParameter("password");
        
        //supply this uname and pwd to Users
       Eligibility eligible = EntityFactory.getEligible();
    //   eligible.setPercent(percent);
        //user.setPassword(password); 
        //user.setName("na");
       List<Eligibility> userList = new ArrayList<Eligibility>();
        //pass users obj to DAO ask him to check credentials
        EligibilityDao edao = DAOFactory.getEligibleDao();
        userList = edao.getEligibleList(percent);
        
        for(Eligibility str: userList){
			System.out.println(str);
	   }

                
        //read response from dao and take decision
       /* PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        RequestDispatcher rd = null;
        
        if(user.getName().equals("na"))
        {   rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
            out.println("<center><font color=red>wront username / password</font></center>");
        }
        else
        {
            HttpSession usession = request.getSession(true);
            usession.setAttribute("username", username);
            usession.setAttribute("name", user.getName());
            
            response.sendRedirect("mailbox.jsp?mailtype=inbox");
        }   */     
    }
    public void destroy()
    {
        System.out.println("Destroying Login Controller");
    }
}