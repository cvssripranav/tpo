package com.realtimetpo.daos;
 import java.util.Properties;
 import java.sql.*;

 import java.util.*;
 import javax.activation.DataHandler;
 import javax.activation.DataSource;
 import javax.activation.FileDataSource;
 import javax.mail.BodyPart;
 import javax.mail.Message;
import javax.mail.MessagingException;
 import javax.mail.Multipart;
 import javax.mail.PasswordAuthentication;
 import javax.mail.Session;
 import javax.mail.Transport;
 import  javax.mail.internet.InternetAddress;
 import  javax.mail.internet.MimeBodyPart;
 import  javax.mail.internet.MimeMessage;
 import javax.mail.internet.MimeMultipart;

import com.realtimetpo.entities.Eligibility;
import com.realtimetpo.entities.Mailing;
import com.realtimetpo.factories.ConnectionFactory;
import com.realtimetpo.factories.DAOFactory;
import com.realtimetpo.factories.EntityFactory;


public class MailingDao {
	
	Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
 
	
	
	public MailingDao(){
    	try {
             con = ConnectionFactory.getConnection();
             stmt = con.createStatement();
         }catch(Exception e){System.out.println(e);}
    }
    
	public boolean sendMail(Mailing m,String percent)
	{
		
	
	String host="smtp.gmail.com";  
	//Mailing m = EntityFactory.getMails();
	//final String username=mails.getUsername();//change accordingly  
	// final String pwd=mails.getPassword();//change accordingly  
	  System.out.println(m.getUsername());
//	String to=request.getParameter("rid");//change accordingly  

	 //Get the session object  
	 Properties props = new Properties();  
	 props.put("mail.smtp.host",host);  
	 props.put("mail.smtp.auth", "true");
	 //props.put("mail.smtp.port", "25"); 
	 //props.put("mail.debug", "true"); 
	 //props.put("mail.smtp.auth", "true"); 
	 props.put("mail.smtp.starttls.enable","true"); 
	 //props.put("mail.smtp.EnableSSL.enable","true");
	   
	 Session ss = Session.getDefaultInstance(props,  
	  new javax.mail.Authenticator() {  
	    protected PasswordAuthentication getPasswordAuthentication() {  
	  return new PasswordAuthentication(m.getUsername(),m.getPassword());  
	    }  
	  });  

	 //Compose the message  
	  try {  
	   MimeMessage message = new MimeMessage(ss);  
	   message.setFrom(new InternetAddress(m.getUsername()));  
	//   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));   
	   BodyPart messageBodyPart = new MimeBodyPart();

       // Now set the actual message
      

        
           try{
        	   List<Eligibility> userLists = new ArrayList<Eligibility>();
        	   //pass users obj to DAO ask him to check credentials
        	   EligibilityDao edaos = DAOFactory.getEligibleDao();
        	   userLists = edaos.getEligibleList(percent);
        	   
        	   for(Eligibility strs: userLists){
          
        	   
        	   System.out.println("hii "+strs.getMailid());
        	   
        	                 ////
         	               //  if(generate.equalsIgnoreCase("no")){
         	                 
         	                 int count=0;
         	                 
         	                	 count++;
         	                	 //System.out.println(resultSet.getString("mailid"));
         	                	 message.addRecipient(Message.RecipientType.TO,new InternetAddress(strs.getMailid()));  
         	                  message.setSubject(m.getSubject());  
         	                  message.setText(m.getText());  
         	                 messageBodyPart.setText(m.getText());
         	                 
         	                 }
        	   
           }catch (Exception e) {
         	            	  e.printStackTrace();
         	         	  System.out.println("<h2>Please try again.....</h2>");
         	           }


	     if(m.getAttachment().equalsIgnoreCase("yes")){
	    	
	    	 
	    	 Multipart multipart = new MimeMultipart("mixed");

	         // Set text message part
	  
	    	 multipart.addBodyPart(messageBodyPart);


	         // Part two is attachment
	        
	       
	       
	        Map<Object, MimeBodyPart> map = new HashMap<>();
	   	 Map<Object,DataSource>  ds=new HashMap<>(); 
	       //  String filename = request.getParameter("link");
	       // System.out.println(filename);
	        try{  
	        	 rs=stmt.executeQuery("select * from attachment");  
	        	int count=0;
	        	while(rs.next()){  
	        	//out.print(rs.getClob(1)+"\n "); 
	        	count++;
	        	String att=rs.getString(2);
	        	System.out.println(att + "files");
	        /*	DataSource source = new FileDataSource(att);
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName(att);
	           multipart.addBodyPart(messageBodyPart);*/
	           map.put("v" + count, new MimeBodyPart());
	           ds.put("d"+count,new FileDataSource(att));
	           map.get("v"+count).setDataHandler(new DataHandler(ds.get("d"+count)));
	           map.get("v"+count).setFileName(att);
	           multipart.addBodyPart(map.get("v"+count));
	           message.setContent(multipart);
	            

	        	//count++;
	        	//out.print( '\n');
	        	//System.out.print(count +" files attached '\n'");
	        	} }catch (Exception e) {e.printStackTrace();}

	     }
	  //send the message  
	   Transport.send(message);  

	   System.out.println("message sent successfully...");  
	 
	   } catch (MessagingException e) {e.printStackTrace();} 
	  try{  
     	int i=stmt.executeUpdate("delete from attachment");
	  }catch (Exception e) {e.printStackTrace();}
	  return true;
	}
	public boolean sendAlert(String rno,String department,String subject,String marks)
	{
		String host="smtp.gmail.com";  
		  boolean success=false;
		//Mailing m = EntityFactory.getMails();
		//final String username=mails.getUsername();//change accordingly  
		// final String pwd=mails.getPassword();//change accordingly  
		  //System.out.println(m.getUsername());
//		String to=request.getParameter("rid");//change accordingly  

		 //Get the session object  
		 Properties props = new Properties();  
		 props.put("mail.smtp.host",host);  
		 props.put("mail.smtp.auth", "true");
		 //props.put("mail.smtp.port", "25"); 
		 //props.put("mail.debug", "true"); 
		 //props.put("mail.smtp.auth", "true"); 
		 props.put("mail.smtp.starttls.enable","true"); 
		 //props.put("mail.smtp.EnableSSL.enable","true");
		   
		 Session ss = Session.getDefaultInstance(props,  
		  new javax.mail.Authenticator() {  
		    protected PasswordAuthentication getPasswordAuthentication() {  
		  return new PasswordAuthentication("akil.c4@gmail.com","Sairam5108121995");  
		    }  
		  });  

		 //Compose the message  
		  try {  
		   MimeMessage message = new MimeMessage(ss);  
		   message.setFrom(new InternetAddress("akil.c4@gmail.com"));  
		//   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));   
		 //  BodyPart messageBodyPart = new MimeBodyPart();

	       // Now set the actual message
	      

	        
	           try{
	        	   List<String> inchargeLists = new ArrayList<String>();
	        	   //pass users obj to DAO ask him to check credentials
	        	   EligibilityDao edaos = DAOFactory.getEligibleDao();
	        	   inchargeLists = edaos.getInchargeList(department);
	        	 
	        	   for(String strs: inchargeLists){
	          
	        		   
   	                 
	                		        	   System.out.println("hii "+strs);
	        	   
	        	                 ////
	         	               //  if(generate.equalsIgnoreCase("no")){
	         	                 
	         	                 
	         	                	 //System.out.println(resultSet.getString("mailid"));
	         	                	 message.addRecipient(Message.RecipientType.TO,new InternetAddress(strs));  
	         	                  message.setSubject("Request for updation of marks");  
	         	                  message.setText("Dear Sir/Mam");  
	         	               //  messageBodyPart.setText("hii");
	         	                 Transport.send(message);
	         	                 success=true;
	         	                 }
	        	   
	           }catch (Exception e) {
	         	            	  e.printStackTrace();
	         	         	  System.out.println("<h2>Please try again.....</h2>");
	         	           }


		   
		        	 }catch (Exception e) {e.printStackTrace();}

		     
		  //send the message  
		     
if(success==true)
		  return true;
		 
else
	return false;

	}
}
