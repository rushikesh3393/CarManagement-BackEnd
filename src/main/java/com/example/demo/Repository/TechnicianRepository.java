package com.example.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TechnicianModel;

@Repository("techRepo")
public class TechnicianRepository 
{

	List<TechnicianModel> techList;
	@Autowired
	JdbcTemplate template;
	
	public boolean isAddNewTechnician(TechnicianModel tmodel)
	{
		int value=template.update("insert into technicianmodel values('0',?,?,?,?)",new PreparedStatementSetter () 
		{
            public void setValues(PreparedStatement ps) throws SQLException 
            {
            	ps.setString(1, tmodel.getTname());
            	ps.setString(2, tmodel.getTemail());
            	ps.setString(3, tmodel.getTcontact());
            	ps.setInt(4,tmodel.getSalary());
			}	                                                                        
		});
		return value>0?true:false;
	}
	
	
	public List<TechnicianModel> getAllTechnicians()
	{
		List<TechnicianModel> techList=template.query("select *from technicianmodel order by Tname asc",new RowMapper<TechnicianModel> () {

			public TechnicianModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				TechnicianModel tmodel=new TechnicianModel();
				tmodel.setTid(rs.getInt(1));
				tmodel.setTname(rs.getString(2));
				tmodel.setTemail(rs.getString(3));
				tmodel.setTcontact(rs.getString(4));
				tmodel.setSalary(rs.getInt(5));
				return tmodel;
			}	
		});
		return techList;	
	}
	
	
	public TechnicianModel getTechnicianById(int tid)
	{
		List<TechnicianModel> techList=template.query("select *from TechnicianModel where tid=?",new Object[] {tid},new RowMapper() 
		{
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				TechnicianModel tmodel=new TechnicianModel();
				tmodel.setTid(rs.getInt(1));
				tmodel.setTname(rs.getString(2));
				tmodel.setTemail(rs.getString(3));
				tmodel.setTcontact(rs.getString(4));
				tmodel.setSalary(rs.getInt(5));
				return tmodel;
			}
		});
		return techList.get(0);
	}
	
	
	public boolean isDeleteTechnician(int tid)
	{
		int value=template.update("delete from technicianmodel where tid="+tid);
		
		if(value>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean isUpdateTechnician(TechnicianModel tmodel)
	{
		int value=template.update("update technicianmodel set tname=? ,temail=?,tcontact=?,salary=? where tid=?",new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, tmodel.getTname());
				ps.setString(2, tmodel.getTemail());
				ps.setString(3, tmodel.getTcontact());
				ps.setInt(4, tmodel.getSalary());
				ps.setInt(5, tmodel.getTid());
			}
			
		});
		return value>0?true:false;
	}
	
	
	public List<TechnicianModel> getAllCustomPattern(String pattern)
	{
		List<TechnicianModel> techList=template.query("select *from technicianmodel where tname like '%"+pattern+"%' ", new RowMapper <TechnicianModel> () {

			public TechnicianModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				TechnicianModel tmodel=new TechnicianModel();
				tmodel.setTid(rs.getInt(1));
				tmodel.setTname(rs.getString(2));
				tmodel.setTemail(rs.getString(3));
				tmodel.setTcontact(rs.getString(4));
				tmodel.setSalary(rs.getInt(5));
				return tmodel;
			}
		});
		return techList;
	}
}
