package com.realtimetpo.daos;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 import org.apache.poi.xssf.usermodel.XSSFCell;
 import org.apache.poi.xssf.usermodel.XSSFRow;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
  import java.io.File;
  import java.io.FileOutputStream;


import com.realtimetpo.factories.*;
import com.realtimetpo.entities.Eligibility;
public class EligibilityDao {
	 Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    //List<Eligibility> userList = new ArrayList<Eligibility>();
	    public EligibilityDao(){
	    	try {
	             con = ConnectionFactory.getConnection();
	             stmt = con.createStatement();
	         }catch(Exception e){System.out.println(e);}
	    }
	    public List<String> getEligibleColumns(String percent)
	    {
	    	List<String> columnList = new ArrayList<String>();
	    	 try
		        {
		        	System.out.println(percent);
		            rs = stmt.executeQuery("select  * from mails where percent>='"+ percent +"'");
	 
		           // while(rs.next())
		          //  {
		                Eligibility eligibles = EntityFactory.getEligible();
		                
		                //ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM TABLE2");
		                ResultSetMetaData rsmd = rs.getMetaData();
		                int columnCount = rsmd.getColumnCount();
System.out.println(columnCount);
		                // The column count starts from 1
		                for (int i = 1; i <= columnCount; i++ ) {
		                  String name = rsmd.getColumnName(i);
		                  System.out.println(name);
		                  // Do stuff with name
		                 // eligibles.setColumn(name);
		                  columnList.add(name);
		                 // System.out.println(columnList.get(i));
		                } 
		                
		           // }
		            System.out.println("outs  "+columnList.size());
		            
		        }catch(Exception e){System.out.println(e);}
		        
		        return columnList;

	    }
	    
	    public List<Eligibility> getEligibleList(String percent)
	    {
	    	
	    	List<Eligibility> userList = new ArrayList<Eligibility>();
	    	userList.clear();
	    System.out.println("in  "+userList.size());
	        try
	        {
	        	System.out.println(percent);
	            rs = stmt.executeQuery("select  * from mails where percent>='"+ percent +"'");
 
	            while(rs.next())
	            {
	                Eligibility eligible = EntityFactory.getEligible();
	                
	                //eligible.setId(rs.getInt(1));
	                eligible.setPercent(rs.getFloat(1));
	                eligible.setMailid(rs.getString(2));
	                //user.setName(rs.getString(4));
	                //user.setMobile(rs.getString(5));
	                
	                userList.add(eligible);
	            }
	            System.out.println("out  "+userList.size());
	            
	        }catch(Exception e){System.out.println(e);}
	        
	        return userList;
	    }
	  /*  public void emptys()
	    {
	    	System.out.println("hii");
	    	  userList.clear();
	    }*/

	    public void generateEligibleList(String percent,String filename)
	    {
	    	  List<Eligibility> eligibleList = new ArrayList<Eligibility>();
	    	  List<String> columnList = new ArrayList<String>();
	    	try{
	    		rs=stmt.executeQuery("select * from mails where percent>='"+ percent +"' ");
	    		 while(rs.next())
		            {
		                Eligibility eligible = EntityFactory.getEligible();
		                
		                //eligible.setId(rs.getInt(1));
		                eligible.setPercent(rs.getFloat(1));
		                eligible.setMailid(rs.getString(2));
		                //user.setName(rs.getString(4));
		                //user.setMobile(rs.getString(5));
		                
		                eligibleList.add(eligible);
		            }
		            
		        		        
		        
		    
	    	
           	 XSSFWorkbook workbook = new XSSFWorkbook(); 
             XSSFSheet spreadsheet = workbook
             .createSheet("employe db");
             XSSFRow row=spreadsheet.createRow(1);
             XSSFCell cell;
             ResultSetMetaData rsmd = rs.getMetaData();
             int columnCount = rsmd.getColumnCount();
System.out.println(columnCount);
             // The column count starts from 1
             for (int i = 1; i <= columnCount; i++ ) {
               String name = rsmd.getColumnName(i);
               System.out.println(name);
               // Do stuff with name
              // eligibles.setColumn(name);
            //   columnList.add(name);
              // System.out.println(columnList.get(i));
               cell=row.createCell(i);
               cell.setCellValue(name);
             
             } 
         
            // cell=row.createCell(2);
            // cell.setCellValue(" Mailid");
          /*   cell=row.createCell(3);
             cell.setCellValue("brnch");
             cell=row.createCell(4);
             cell.setCellValue("percent");
             cell=row.createCell(5);
             cell.setCellValue("bklogs");*/
             int i=2;
             
             for(Eligibility str: eligibleList){
             
             row=spreadsheet.createRow(i);
             cell=row.createCell(1);
             cell.setCellValue(str.getPercent());
             cell=row.createCell(2);
             cell.setCellValue(str.getMailid());
             /*cell=row.createCell(3);
             cell.setCellValue(resultSet.getString("sbranch"));
             cell=row.createCell(4);
             cell.setCellValue(resultSet.getFloat("percent"));
             cell=row.createCell(5);
             cell.setCellValue(resultSet.getInt("backlogs"));*/
             i++;
          }
          FileOutputStream os = new FileOutputStream(
          new File("d:\\"+ filename +".xls"));
          workbook.write(os);
         	                 

         // out.close();
        System.  out.println(
          "exceldatabase.xlsx written successfully"+ filename);
       
	    	}catch(Exception e){System.out.println(e);}


		  


	    }
	    public List<String> getInchargeList(String department)
	    {
	    	
	    	List<String> inchargeList = new ArrayList<String>();
	    	inchargeList.clear();
	    System.out.println("in  "+inchargeList.size());
	        try
	        {
	        	System.out.println(department);
	            rs = stmt.executeQuery("select  * from incharge where department='"+ department +"'");
 
	            while(rs.next())
	            {
	                //Eligibility eligible = EntityFactory.getEligible();
	                
	                //eligible.setId(rs.getInt(1));
	              /*  eligible.setPercent(rs.getFloat(1));
	                eligible.setMailid(rs.getString(2));
	              */  //user.setName(rs.getString(4));
	                //user.setMobile(rs.getString(5));
	                
	                inchargeList.add(rs.getString("email"));
	            }
	            System.out.println("out  "+inchargeList.size());
	            
	        }catch(Exception e){System.out.println(e);}
	        
	        return inchargeList;
	    }
	  /*  public void emptys()
	    {
	    	System.out.println("hii");
	    	  userList.clear();
	    }*/


	 }
