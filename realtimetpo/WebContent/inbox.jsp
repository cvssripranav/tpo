<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>  
var request;  
function sendInfo()  
{  
var v=document.inbox.link.value;  
var url="index.jsp?val="+v;  
  
if(window.XMLHttpRequest){  
request=new XMLHttpRequest();  
}  
else if(window.ActiveXObject){  
request=new ActiveXObject("Microsoft.XMLHTTP");  
}  
  
try{  
request.onreadystatechange=getInfo;  
request.open("GET",url,true);  
request.send(url);  
}catch(e){alert("Unable to connect to server");}  
}  
  
function getInfo(){  
if(request.readyState==4){  
var val=request.responseText;  
document.getElementById('op').innerHTML=val;  

}  
}  

$(document).ready(function() {
    $('#no').click(function() {
    	$("#link").prop("disabled","disabled");
    	$("#addlink").prop("disabled","disabled");

       // alert($('#columnData').html());
                   
    });
    $('#yes').click(function() {
    	$("#link").removeProp("disabled");
    	$("#addlink").removeProp("disabled");
       // alert($('#columnData').html());
                   
    });
   
});

  
</script>  
</head>
<body>
<% System.out.println("inbox"+request.getParameter("percent")); %>
<center>
<form name="inbox" action="MailingController" >
<input type="hidden" name="hidden" value="<%=request.getParameter("percent") %>">
Enter username:<input type="email" name="uid" required><br><br>
Enter password:<input type="password" name="pwd" required><br><br>

Do you have attachments:<input type="radio" name="attachments" id="yes" value="yes" required>Yes
                        <input type="radio" name="attachments" id="no" value="no">No<br><br>
Enter mail subject:<input type="text" name="subject" required><br><br>
Enter mail body:<textarea name="body"  cols="50" required></textarea><br><br>
Paste the link of attachments:<input type="text" name="link" id="link"required>
 <input type="button" name="addlink" value="attach" id="addlink" onClick="sendInfo()"> <br>
 
<center><input type="submit" name="submit" value="send"></center>

</form>
</center>
<span id="op"></span>
</body>
</html>