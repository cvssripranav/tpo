<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import ="java.util.Properties"%>
<%@ page import ="java.sql.*"%>

<%@ page import ="java.util.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page import ="javax.activation.DataHandler"%>
<%@ page import ="javax.activation.DataSource" %>
<%@ page import ="javax.activation.FileDataSource"%>
<%@ page import ="javax.mail.BodyPart"%>
<%@ page import ="javax.mail.Message"%>
<%@ page import ="javax.mail.MessagingException"%>
<%@ page import ="javax.mail.Multipart"%>
<%@ page import ="javax.mail.PasswordAuthentication"%>
<%@ page import ="javax.mail.Session"%>
<%@ page import ="javax.mail.Transport"%>
<%@ page import ="javax.mail.internet.InternetAddress"%>
<%@ page import=" javax.mail.internet.MimeBodyPart" %>
<%@ page import=" javax.mail.internet.MimeMessage" %>
<%@ page import=" javax.mail.internet.MimeMultipart"%>
<%@ page import=" com.realtimetpo.daos.*" %>
<%@page import="java.util.*,java.io.*,javax.servlet.*,javax.servlet.http.*"%>
<%@ page import=" com.realtimetpo.entities.*" %>

<%@ page import ="com.realtimetpo.factories.*" %>

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
 
 if(null==session.getAttribute("username"))
{
	 response.setContentType("text/html"); 
	PrintWriter outs=response.getWriter();
	 request.getRequestDispatcher("login.jsp").include(request, response);  
		//rd = request.getRequestDispatcher("registration.html");
	    //rd.include(request, response);
	 // response.sendRedirect(request.getContextPath() + "/index.jsp");
		outs.println("<center><font color=red>Please login to continue</font></center>");
		outs.close();
	}
 else{


String percent = request.getParameter("percent");
String password = request.getParameter("pwd");
String username=request.getParameter("uid");
System.out.println(username);
String subject=request.getParameter("subject");
String body=request.getParameter("body");
//supply this uname and pwd to Users
Mailing mailss = EntityFactory.getMails();
mailss.setUsername(username);
mailss.setPassword(password);
mailss.setSubject(subject);
mailss.setText(body);

//pass users obj to DAO ask him to check credentials
MailingDao mdao = DAOFactory.getMailingDao();
mdao.sendMail(mailss,percent);

 }

%>
</body>
</html>