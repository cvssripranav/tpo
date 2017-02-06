
package com.realtimetpo.daos;

import com.realtimetpo.factories.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.realtimetpo.entities.*;;
public class UsersDAO {
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    public UsersDAO()
    {
        try
        {
            con = ConnectionFactory.getConnection();
            stmt = con.createStatement();
        }catch(Exception e){System.out.println(e);}
    }
    public boolean insertUser(Users user)
    {
        String qry = "insert into users "
                + "(userid,username,password,name,mobile,dept) values "
                + "('"+user.getUserid()+"','"+user.getUsername()+"','"+user.getPassword()+"',"
                + "'"+user.getName()+"','"+user.getMobile()+"','"+user.getDepartment()+"')";
        System.out.println(qry);
        try
        {
            stmt.executeUpdate(qry);
            
        }catch(Exception e){ System.out.println(e); return false;}
        return true;
    }
    public List<Users> getUsersList()
    {
        List<Users> userList = new ArrayList<Users>();
        try
        {
            rs = stmt.executeQuery("select id,username,password,name,mobile from users");
            
            while(rs.next())
            {
                Users user = EntityFactory.getUser();
                
                user.setUserid(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setName(rs.getString(4));
                user.setMobile(rs.getString(5));
                
                userList.add(user);
            }
            
        }catch(Exception e){System.out.println(e);}
        
        return userList;
    }
    public Users checkUserLogin(Users user)
    {
        String sql = "select name,dept,userid from users where "
                + "username='"+user.getUsername()+"' "
                + "and password='"+user.getPassword()+"'";
        System.out.println(sql);
        try
        {
            rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                user.setName(rs.getString(1));
                user.setDepartment(rs.getString(2));
                user.setUserid(rs.getInt(3));
            }
            
        }catch(Exception e){System.out.println(e.toString()); }
        return user;
    }
    
    
    
    public boolean insertUserExpenses(Users user)
    {
        String qry = "insert into taxcalculator "
                + "(id_taxcalculator,name,dept,pan,hra,lic,ppf,gpf,medical,donations) values "
                + "('"+user.getUserid()+"','"+user.getUsername()+"',"
                + "'"+user.getDepartment()+"','"+user.getPan()+"','"+user.getHra()+"','"+user.getLic()+"'"
                		+ ",'"+user.getPpf()+"','"+user.getGpf()+"','"+user.getMedical()+"','"+user.getCharity()+"')";
        System.out.println(qry);
        try
        {
            stmt.executeUpdate(qry);
            
        }catch(Exception e){ System.out.println(e); return false;}
        return true;
    }
   
    
}
