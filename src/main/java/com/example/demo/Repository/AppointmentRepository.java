package com.example.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.AppointmentModel;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper; 



@Repository("appRepo")
public class AppointmentRepository 
{
	List<AppointmentModel> appList;
	
	@Autowired
	JdbcTemplate template;
	
	public boolean newAppointment(AppointmentModel app)
	{
		int value=template.update("insert into appointmentmodel values('0',?,?,?,?,?)",new PreparedStatementSetter () 
		{
			public void setValues(PreparedStatement ps) throws SQLException 
			{
				ps.setString(1,app.getCname() );
				ps.setDate(2, app.getAdate());
				ps.setString(3, app.getAtime());
				ps.setString(4, app.getVnumber());
				ps.setInt(5,app.getUid());
			}
		});
		return value>0?true:false;
	}
	
	public List<AppointmentModel> getAppointmentList()
	{
		appList=template.query("select *from appointmentmodel order by adate asc", new RowMapper<AppointmentModel>() 
		{
           public AppointmentModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
        	   AppointmentModel apmodel=new AppointmentModel();
        	   apmodel.setApid(rs.getInt(1));
        	   apmodel.setCname(rs.getString(2));
        	   apmodel.setAdate(rs.getDate(3));
        	   apmodel.setAtime(rs.getString(4));
        	   apmodel.setVnumber(rs.getString(5));
			   return apmodel;
			}
		});
		return appList;
	}
	
	public AppointmentModel getAppointmentByApid(int apid)
	{
		appList=template.query("select *from appointmentmodel where apid=?",new Object[] {apid}, new RowMapper<AppointmentModel>() 
		{
           public AppointmentModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
        	   AppointmentModel apmodel=new AppointmentModel();
        	   apmodel.setApid(rs.getInt(1));
        	   apmodel.setCname(rs.getString(2));
        	   apmodel.setAdate(rs.getDate(3));
        	   apmodel.setAtime(rs.getString(4));
        	   apmodel.setVnumber(rs.getString(5));
			   return apmodel;
			}
		});
		return appList.size()>0?appList.get(0):null;
		
	}
	
	
	public boolean deleteAppointmentById(int apid)
	{
		int value=template.update("delete from appointmentmodel where apid="+apid);
		
		if(value>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean updateAppointmentById(AppointmentModel app)
	{
		int value=template.update("update appointmentmodel set cname=?,adate=?,atime=?,vnumber=? where apid=?",new PreparedStatementSetter () {

			public void setValues(PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, app.getCname());
				ps.setDate(2, app.getAdate());
				ps.setString(3, app.getAtime());
				ps.setString(4, app.getVnumber());
				ps.setInt(5, app.getApid());	
			}
			
		});
		
		return value>0?true:false;
	}
	
	public List<AppointmentModel> getpattern(String pattern)
	{
		List<AppointmentModel> plist=template.query("select *from appointmentmodel where cname like '%"+pattern+"%' ", new RowMapper<AppointmentModel>() {

			public AppointmentModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				AppointmentModel ap=new AppointmentModel();
				ap.setApid(rs.getInt(1));
				ap.setCname(rs.getString(2));
				ap.setAdate(rs.getDate(3));
				ap.setAtime(rs.getString(4));
				ap.setVnumber(rs.getString(5));
				
				return ap;
			}			
		});
		
		return plist.size()>0?plist:null;
	}
	
	
	public String getCustNameByApid(int apid) 
	{
	    String cname = template.queryForObject("select cname from appointmentmodel where apid = ?", new Object[]{apid}, new RowMapper<String>() 
	    {
	            public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	            {
	                return rs.getString("cname");
	            }
	        }
	    );
	    return cname;
	}

	
	public String getVehicleByApid(int apid) 
	{
	    String vnumber = template.queryForObject("select vnumber from appointmentmodel where apid = ?", new Object[]{apid}, new RowMapper<String>() 
	    {
	            public String mapRow(ResultSet rs, int rowNum) throws SQLException 
	            {
	                return rs.getString("vnumber");
	            }
	        }
	    );
	    return vnumber;
	}
	
	public AppointmentModel searchAppointmentByFields(String cname, java.sql.Date adate, String atime)
	{
		List<AppointmentModel> list = template.query(
			"SELECT * FROM appointmentmodel WHERE cname = ? AND adate = ? AND atime = ? ORDER BY apid DESC LIMIT 1",
			new Object[]{cname, adate, atime},
			new RowMapper<AppointmentModel>() {
				public AppointmentModel mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					AppointmentModel ap = new AppointmentModel();
					ap.setApid(rs.getInt(1));
					ap.setCname(rs.getString(2));
					ap.setAdate(rs.getDate(3));
					ap.setAtime(rs.getString(4));
					ap.setVnumber(rs.getString(5));
					return ap;
				}
			}
		);
		return list.size() > 0 ? list.get(0) : null;
	}

	public String getUserEmail(String cname) {
	    List<String> emails = template.query(
	        "SELECT DISTINCT u.email FROM usermodel u INNER JOIN appointmentmodel a ON u.uid = a.uid WHERE u.uname = ?",
	        new Object[]{cname},
	        (rs, rowNum) -> rs.getString("email")
	    );

	    return emails.isEmpty() ? null : emails.get(0); 
	}



}
// this 