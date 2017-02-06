package com.realtimetpo.factories;

import com.realtimetpo.daos.*;

public class DAOFactory 
{
	public static EligibilityDao getEligibleDao()
	{
	    return new EligibilityDao();
	}
	public static MailingDao getMailingDao()
	{
	    return new MailingDao();
	}
	public static UsersDAO getUsersDAO()
	{
	    return new UsersDAO();
	}
	public static StudentInfo getStudentInfo()
	{
	    return new StudentInfo();
	}
}
