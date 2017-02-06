<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <script>
 
 function addInput()
 {
	 <% String percents=request.getParameter("percent");%>
var percent="<%=percents%>";
	 
		    $('#mail').load('inbox.jsp?percent='+percent);
		
        
}
 </script> 
<style>
html,body{height:100%; margin:0px;}	
	
	 body {
    background-image: url('imges/websiteBackground.jpg');
 background-position:right bottom , left top;
  background-repeat: no-repeat;
   background-size:cover;
   background-attachment:fixed;
    
 

} 
form.inset {border-style: inset;}

#div1 {
    border-radius: 15px;
        border: 2px solid grey;
    padding: 50px;
    margin:30px;
}#serch,a:link, a:visited {
   background-image: url('imges/websiteBackground.jpg');
    color: grey;
    border: 2px solid skyblue;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    
}

#serch:hover,#serch:active,a:hover, a:active {
    background-color: white;
    color: brown;
    font-size:105%;
}table {
    border-collapse: collapse;
    width: 100%;
    
}

table, td, th {
    border: 1px solid grey;
    text-align:center;
    height:20px;
}
tr,td{
background-color:skyblue;}
th{
background-color:#5bc0de;}

  </style>
   <link 
href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" 
rel="stylesheet">
       <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
       <script 
src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  
  
  

</head>
<body>
<a href="Logout"  id="logout">Logout</a>
<table align="center" cellpadding="4" cellspacing="4">
<tr>

</tr>
<tr bgcolor="skyblue">
<!--  <th><b>Name</b></th>
<th><b>Role</b></th>
<th><b>Branch</b></th>
<th><b>Percentage</b></th>
<th><b>Backlogs</b></th>-->


</tr>
<%@page import="java.util.*,java.io.*,javax.servlet.*,javax.servlet.http.*"%>
<%@ page import=" java.io.File" %>
<%@ page import ="java.io.FileOutputStream"%>
<%@ page import ="java.sql.Connection"%>
<%@ page import ="java.sql.DriverManager"%>
<%@ page import ="java.sql.ResultSet"%>
<%@ page import=" java.sql.Statement"%>
<%@ page import ="com.realtimetpo.daos.EligibilityDao"%>
<%@ page import ="com.realtimetpo.entities.Eligibility"%>
<%@ page import=" com.realtimetpo.factories.DAOFactory"%>
<%@ page import=" com.realtimetpo.factories.EntityFactory"%>

<%@ page import=" java.sql.* " %>
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
String filename = request.getParameter("filename");
String generate=request.getParameter("generate"); 
 //supply this uname and pwd to Users
 
Eligibility eligible = EntityFactory.getEligible();
//   eligible.setPercent(percent);
 //user.setPassword(password);
 //user.setName("na");
List<Eligibility> userList = new ArrayList<Eligibility>();
List<String> columnList = new ArrayList<String>();
 //pass users obj to DAO ask him to check credentials
 EligibilityDao edao = DAOFactory.getEligibleDao();
 userList = edao.getEligibleList(percent);
 columnList=edao.getEligibleColumns(percent);
 int count=0;
 %>
 <tr>
 <%
 for(String str1: columnList){
	 count++;
	 %>
	<th><%= str1 %>
<%
}
 %>
 </th> 
 </tr><%
 for(Eligibility str: userList){
	%>
		<tr><td><%= str.getPercent() %></td><td><%= str.getMailid()%> 
		</td>
		</tr>

<%		
}
 if(generate.equalsIgnoreCase("yes"))
 {
	 edao.generateEligibleList(percent, filename);
 }
%>  
</table>
 <%
// edao.emptys();
if(request.getParameter("mail").equalsIgnoreCase("yes"))
{
	  out.println("hii");
%>
 <form>
	  <input type="button" name="send" value="send" onClick="addInput()"><br><br><br>  
</form> 
<!--<a href="inbox.jsp" onClick="addInput()">send</a>-->
<div id="mail"></div>
<%
}
%>
<a href=<%= "\"download.jsp?fname=" + filename + "\"" %>   target="_blank">download</a>
 
<%} %>  
</body>
</html>

</body>
</html>