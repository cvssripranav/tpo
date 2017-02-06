<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Notify</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
  
 <script>
 
 function addInput()
 {

	 
		    $('#mail').load('inbox.jsp');
		
        
}
 </script> 
  <script>
            $(document).ready(function() {
                $('#radioButton').click(function() {
                   // alert($('#columnData').html());
                    var name = $('#percent').html();             
                    $.post("MailingController", {'name': name}, 
                    	       function(data) 
                    	       { 
                    	          alert("Result from second.jsp: " + data.name + " " + data.type); 
                    	       }
                    	);              
                });
               
            });
          /*  function inactive()
{
            	var generate=$("#generate").val();
            	alert(generate)
            	if(generate.match("no"))
            		{
            	$("#filename").prop("disabled","disabled");
            		}
            	           	
            	}          
            function active()
            {
                        	var generate=$("#generates").val();
                        	alert(generate)
                        	if(generate.match("yes"))
                        		{
                        	           		$("#filename").removeProp("disabled");	
                        	}    }      
            
            */
            
            $(document).ready(function() {
                $('#generate').click(function() {
                	$("#filename").prop("disabled","disabled");
                   // alert($('#columnData').html());
                               
                });
                $('#generates').click(function() {
                	$("#filename").removeProp("disabled");
                   // alert($('#columnData').html());
                               
                });
               
            });
           
             
        </script>
        <script>$(document).ready(function() {
       	 $('#generate').click(function() {
             // alert($('#columnData').html());
              var filename = $('#filename').html();             
              $.post("download.jsp", {'filename': filename}, 
              	       function(data) 
              	       { 
              	          alert("Result from second.jsp: " + data.filename + " " + data.type); 
              	       }
              	);              
          });
    });</script>
  
</head>
<body>
<%@page import="java.util.*,java.io.*,javax.servlet.*,javax.servlet.http.*"%>
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


 else
 {


%>
<!-- <a href="attendbydate.jsp" target="f2">REFRESH</a>
<a href="result.jsp" target="f2" style="float: right;">HOME</a><br><br> -->

<a href="Logout" style="float:right">Logout</a>
<a href=<%= "\"StudentSemPerformance.jsp?rno=" + request.getParameter("rno") + "&department="+request.getParameter("department")+"\"" %>  id="semMarks">SemMarks</a>
<a href=<%= "\"StdentDetails.jsp?rno=" + request.getParameter("rno")+"&department="+request.getParameter("department")+ "\"" %>  id="semMarks">CompleteDetails</a>

<center><div id="div1">


<form name="notify" action="NotifyController"> 
Roll number:<input type="text" name="rno" id="rno" value=<%=request.getParameter("rno")%> required> 
<br>
Department:<input type="text" name="department" id="department" value=<%=request.getParameter("department")%> required ><br>
Subjects:<textarea rows="4" cols="10" placeholder="Enter subject names for which marks are wrong" name="subjects"></textarea><br>
Marks:<textarea rows="4" cols="10" placeholder="Enter subject names along with correct marks" name="marks"></textarea><br>

<!-- <input type="button" name="send" value="send" onClick="addInput()"><br><br><br> -->
<div id="mail"> </div>
<input type="submit" name="notify" value="Notify" id="notify"> 
<input type="reset" name="reset"	value="Reset" id="reset">

</form>
</div>

</center>
<%
}
%>
</body>
</html>
