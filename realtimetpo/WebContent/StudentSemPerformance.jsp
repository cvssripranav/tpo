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
<a href="Logout"  id="logout" style="float:right">Logout</a>
<%-- <a href=<%= "\"StdentDetails.jsp?rno=" + "WEEKS" + "\"" %> id="logout">CompleteDetails</a> --%>
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
<%@ page import ="com.realtimetpo.daos.*"%>
<%@ page import ="com.realtimetpo.entities.*"%>
<%@ page import=" com.realtimetpo.factories.*"%>


<%@ page import=" java.sql.* " %>
<%
response.setContentType("text/html");  
//PrintWriter outs=response.getWriter(); 
//RequestDispatcher rd = null;
System.out.println(session.getId()); 
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(null==session.getAttribute("username"))
{
	PrintWriter outs=response.getWriter();
	 request.getRequestDispatcher("login.jsp").include(request, response);  
		//rd = request.getRequestDispatcher("registration.html");
	    //rd.include(request, response);
	 // response.sendRedirect(request.getContextPath() + "/index.jsp");
		outs.println("<center><font color=red>Please login to continue</font></center>");
		outs.close();
	}
else{
String rno = request.getParameter("rno");
String department=request.getParameter("department");%>
<a href=<%= "\"StdentDetails.jsp?rno=" + rno + "&department="+department+"\"" %> id="logout">CompleteDetails</a>
<a href=<%= "\"Notify.jsp?rno=" + rno + "&department="+department+"\"" %>  id="semMarks">Notify</a>
<!-- //String filename = request.getParameter("filename");
//String generate=request.getParameter("generate"); 

 //supply this uname and pwd to Users
 --> <%
StudentPercent student = EntityFactory.getStudent();
//   eligible.setPercent(percent);
 //user.setPassword(password);
 //user.setName("na");
List<StudentPercent> semMarksList = new ArrayList<StudentPercent>();
List<String> semColumnList = new ArrayList<String>();
 //pass users obj to DAO ask him to check credentials
 StudentInfo sdao = DAOFactory.getStudentInfo();
 semMarksList = sdao.getSemMarksList(rno);
 semColumnList=sdao.getSemMarksColumns(rno);
 int count=0;
 %>
 <tr>
 <%
 for(String str1: semColumnList){
	 count++;
	 %>
	<th><%= str1 %>
<%
}
 System.out.println("hELLO");
 %>
 </th> 
 </tr><%
 Iterator it = semMarksList.iterator();
 int sem=1;
 int total=0;
 int maxtotal=0;
 int i=0;
 for(StudentPercent strs: semMarksList){
	 ++i;
	
	 if(strs.getSemester()==sem+1  ){
		 //total=total-strs.getMarksObtained();
		 %>
		 <tr><td>&nbsp;</td><td>&nbsp;</td><td>Total</td><td>&nbsp;</td><td>&nbsp;</td><td><%=total %></td><td><%=maxtotal %></td></tr>
		 <%
		 sem=sem+1;
		 total=0;
		 maxtotal=0;
	 }
	 total=total+strs.getMarksObtained();
	 maxtotal=maxtotal+strs.getMaxMarks();
	%>
		<tr><td><%= strs.getRno() %></td><td><%= strs.getName()%> 
		</td><td><%= strs.getSubjectCode()%></td><td><%= strs.getSubjectName() %></td><td><%= strs.getSemester()%></td>
		<td><%= strs.getMarksObtained() %></td><td><%= strs.getMaxMarks()%></td>
		</tr>

<%
if(i==semMarksList.size()){
	%>
	<tr><td>&nbsp;</td><td>&nbsp;</td><td>Total</td><td>&nbsp;</td><td>&nbsp;</td><td><%=total %></td><td><%=maxtotal %></td></tr>
<%	
}

}
} 
%>  
 </table>
</body>
</html>

