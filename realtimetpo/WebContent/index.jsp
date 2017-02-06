<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.sql.*"%>  
<%@ page import="java.io.*"%>   
<%  
String s=request.getParameter("val");  
if(s==null || s.trim().equals("")){  
out.print("Please enter link of atachment");  
}else{  
//int id=Integer.parseInt(s);  
out.print(s);  
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","admin");  
PreparedStatement ps=con.prepareStatement(  
"insert into attachment(link,attach) values(?,?)");  
              
File f=new File(s);  
FileReader fr=new FileReader(f);  
 int count=0;             
ps.setString(1, s);  
ps.setCharacterStream(2,fr,(int)f.length());  
int i=ps.executeUpdate();  
System.out.println(i+" records affected"); 
PreparedStatement ps1=con.prepareStatement("select * from attachment");  
//ps.setInt(1,id);  
ResultSet rs=ps1.executeQuery();  
while(rs.next()){  
//out.print(rs.getClob(1)+"\n "); 

count++;
out.print( '\n');
System.out.print(count +" files attached '\n'");
} 
out.print(System.lineSeparator()+ count +" files attached in total");
con.close();  
              
}catch (Exception e) {e.printStackTrace();}  
}  %>  


</body>
</html>