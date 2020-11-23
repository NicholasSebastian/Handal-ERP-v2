package com.handalcargo.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.handalcargo.core.Database;

public class Session {
	
	private static Session instance = null;
	
	public String username;
	public String name;
	
	public int group;
	
	public String address;
	public String kelurahan;
	public String city;
	
	public String phone;
	
	public boolean sex;		// false = male, true = female.
	public String birthplace;
	public Date birthday;
	
	public double lembur;
	public double salary;
	public double allowance;
	public double thr;
	public double bonus;
	
	public boolean active;
	public Date employmentDate;
	
	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}
		return instance;
	}
	
	public static void initialize(String staffId) {
		String query = String.format("SELECT * FROM staff WHERE staffid='%s'", staffId);
		ResultSet info = Database.query(query);
		
		instance.username = staffId;
		instance.initialize(info);
	}

	private Session() {}
	
	private void initialize(ResultSet info) {
		try {
			info.next();
			this.name = info.getString("staffname");
			
			this.group = info.getInt("level");
			
			this.address = info.getString("address1");
			this.kelurahan = info.getString("district");
			this.city = info.getString("city");
			
			this.phone = info.getString("phonenum");
			
			this.sex = info.getBoolean("gender");
			this.birthplace = info.getString("placeofbirth");
			this.birthday = info.getDate("dateofbirth");
			
			this.lembur = info.getDouble("ot/hr");
			this.salary = info.getDouble("salary");
			this.allowance = info.getDouble("foodallowance");
			this.bonus = info.getDouble("bonus");
			
			this.active = info.getBoolean("status");
			this.employmentDate = info.getDate("dateofemployment");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
