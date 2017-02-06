package com.realtimetpo.daos;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.realtimetpo.factories.*;
import com.realtimetpo.entities.Eligibility;
import com.realtimetpo.entities.StudentPercent;


public class StudentInfo {
	 Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    //List<Eligibility> userList = new ArrayList<Eligibility>();
	    public StudentInfo(){
	    	try {
	             con = ConnectionFactory.getConnection();
	             stmt = con.createStatement();
	         }catch(Exception e){System.out.println(e);}
	    }
	
	    public List<String> getEligibleColumns(String rno)
	    {
	    	List<String> columnList = new ArrayList<String>();
	    	 try
		        {
		        	//System.out.println(percent);
		            rs = stmt.executeQuery("select  sroll,sname,sbranch ,sem1percent,sem2percent,sem3percent,percent from semmrks where sname='"+ rno +"'");
	 
		           // while(rs.next())
		          //  {
		                //Eligibility eligibles = EntityFactory.getEligible();
		                
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
	    
	    
	    public List<StudentPercent> getMarksList(String rno)
	    {
	    	
	    	List<StudentPercent> marksList = new ArrayList<StudentPercent>();
	    	marksList.clear();
	    System.out.println("in  "+marksList.size());
	        try
	        {
	        	//System.out.println(percent);
	            rs = stmt.executeQuery("select  * from semmrks1 where sname='"+ rno +"'");
 
	            while(rs.next())
	            {
	                StudentPercent student = EntityFactory.getStudent();
	                
	                //eligible.setId(rs.getInt(1));
	                student.setRno(rs.getString("sroll"));
	                student.setName(rs.getString("sname"));
	                student.setBranch(rs.getString("sbranch"));
	                student.setSem1percent(rs.getFloat("sem1percent"));
	                student.setSem2percent(rs.getFloat("sem2percent"));
	                student.setFinalpercent(rs.getFloat("percent"));
	                student.setSem3percent(rs.getFloat("sem3percent"));
	              /*  student.setSem5percent(sem5percent);
	                student.setSem6percent(sem6percent);
	                student.setSem7percent(sem7percent);
	                student.setSem8percent(sem8percent);*///user.setName(rs.getString(4));
	                //user.setMobile(rs.getString(5));
	                
	                marksList.add(student);
	            }
	            System.out.println("out  "+marksList.size());
	            
	        }catch(Exception e){System.out.println(e);}
	        
	        return marksList;
	    }

	    public List<StudentPercent> getSemMarksList(String rno)
	    {
	    	
	    	List<StudentPercent> semMarksList = new ArrayList<StudentPercent>();
	    	semMarksList.clear();
	    System.out.println("in  "+semMarksList.size());
	        try
	        {
	        	//System.out.println(percent);
	            rs = stmt.executeQuery("SELECT distinct m.mroll,st.sname,m.mscode,s.suname,s.susem,m.mmarks,s.sumarks as maxmarks FROM marks m,subjects s,student st where m.mscode=s.sucode  and m.mroll=st.sroll and sname='"+ rno +"'");
 
	            while(rs.next())
	            {
	                StudentPercent students = EntityFactory.getStudent();
	                
	                //eligible.setId(rs.getInt(1));
	                students.setRno(rs.getString("mroll"));
	                students.setName(rs.getString("sname"));
	              /*  student.setBranch(rs.getString("sbranch"));
	                student.setSem1percent(rs.getFloat("sem1percent"));
	                student.setSem2percent(rs.getFloat("sem2percent"));
	                student.setFinalpercent(rs.getFloat("percent"));
	                student.setSem3percent(rs.getFloat("sem3percent"));*/
	              /*  student.setSem5percent(sem5percent);
	                student.setSem6percent(sem6percent);
	                student.setSem7percent(sem7percent);
	                student.setSem8percent(sem8percent);*///user.setName(rs.getString(4));
	                //user.setMobile(rs.getString(5));
	                students.setSubjectCode(rs.getString("mscode"));
	                students.setSubjectName(rs.getString("suname"));
	                students.setSemester(rs.getInt("susem"));
	                students.setMarksObtained(rs.getInt("mmarks"));
	                students.setMaxMarks(rs.getInt("maxmarks"));
	                semMarksList.add(students);
	            }
	            System.out.println("out  "+semMarksList.size());
	            
	        }catch(Exception e){System.out.println(e);}
	        
	        return semMarksList;
	    }
	    public List<String> getSemMarksColumns(String rno)
	    {
	    	List<String> columnList = new ArrayList<String>();
	    	 try
		        {
		        	//System.out.println(percent);
		            rs = stmt.executeQuery("SELECT distinct m.mroll,st.sname,m.mscode,s.suname,s.susem,m.mmarks,s.sumarks as maxmarks FROM marks m,subjects s,student st where m.mscode=s.sucode  and m.mroll=st.sroll and sname='"+ rno +"'");
	 
		           // while(rs.next())
		          //  {
		                //Eligibility eligibles = EntityFactory.getEligible();
		                
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


}
