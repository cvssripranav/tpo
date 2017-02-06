
<%@ page import="java.io.*"%>
<%@page import="java.util.*,java.io.*,javax.servlet.*,javax.servlet.http.*"%>
<%@ page import=" java.io.File" %>
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

  String filename = request.getParameter("fname"); 
System.out.println("hii"+ filename);
  String filepath = "d:\\"; 
  response.setContentType("application/vnd.ms-excel"); 
  response.setHeader("Content-Disposition","attachment; filename=\"" + filename + ".xls\""); 

  java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath + filename+".xls");
		  
  int i; 
  
  while ((i=fileInputStream.read()) != -1) {
    out.write(i); 
  } 
  //responseOutputStream.flush();
  fileInputStream.close(); 
 }
%> 