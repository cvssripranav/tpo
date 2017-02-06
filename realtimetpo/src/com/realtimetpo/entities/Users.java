package com.realtimetpo.entities;
public class Users {    
    private int userid;
    private String username;
    private String password;
    private String name;
    private String mobile;
    private String pan;
    private String department;
    private float hra;
    private float lic;
    private float medical;
    private float charity;
    private float ppf;
    private float gpf;
    public float getPpf() {
		return ppf;
	}

	public void setPpf(float ppf) {
		this.ppf = ppf;
	}

	public float getGpf() {
		return gpf;
	}

	public void setGpf(float gpf) {
		this.gpf = gpf;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public float getHra() {
		return hra;
	}

	public void setHra(float hra) {
		this.hra = hra;
	}

	public float getLic() {
		return lic;
	}

	public void setLic(float lic) {
		this.lic = lic;
	}

	public float getMedical() {
		return medical;
	}

	public void setMedical(float medical) {
		this.medical = medical;
	}

	public float getCharity() {
		return charity;
	}

	public void setCharity(float charity) {
		this.charity = charity;
	}

	public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
        
}
