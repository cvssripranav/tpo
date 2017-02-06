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
import com.realtimetpo.daos.MailingDao;
import com.realtimetpo.entities.Eligibility;
import com.realtimetpo.entities.Mailing;
import com.realtimetpo.factories.DAOFactory;
import com.realtimetpo.factories.EntityFactory;

public class NotifyController extends HttpServlet {

    public void init(ServletConfig cnf)throws ServletException
    {
        super.init(cnf);
    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        //read uname/pwd from index.html
        String rno = request.getParameter("rno");
      //  System.out.println("percent="+percent);
        String department = request.getParameter("department");
        String subjects=request.getParameter("subjects");
       // System.out.println(username);
        String marks=request.getParameter("marks");
      //  String body=request.getParameter("body");
     //   String attachment=request.getParameter("attachments");
        //supply this uname and pwd to Users
     /*  Mailing mailss = EntityFactory.getMails();
    mailss.setUsername(username);
       mailss.setPassword(password);
        mailss.setSubject(subject);
        mailss.setText(body);
       mailss.setAttachment(attachment);*/
        //pass users obj to DAO ask him to check credentials
       MailingDao mdao = DAOFactory.getMailingDao();
    boolean success= mdao.sendAlert(rno,department,subjects,marks);
    PrintWriter out = response.getWriter();
    RequestDispatcher rd = null;
    response.setContentType("text/html");
    
    if(success)
    {
        out.println("<font color=blue>mail sent Successful. </font><br/>");
        rd = request.getRequestDispatcher("Notify.jsp");
        rd.include(request,response);
    }
    else
    {
        out.println("<font color=red>Unable to notify. Please Try again</font><br/>");
        rd = request.getRequestDispatcher("Notify.jsp");
        rd.include(request,response);
    }
    }
    public void destroy()
    {
        System.out.println("Destroying Login Controller");
    }
}