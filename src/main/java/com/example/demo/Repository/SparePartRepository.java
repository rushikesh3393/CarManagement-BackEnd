package com.example.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.example.demo.Model.SparePartModel;

@Repository("spRepo")
public class SparePartRepository 
{
	@Autowired
	JdbcTemplate template;
	
	public boolean isAddNewSparePart(SparePartModel spmodel)
	{
		int value=template.update("insert into sparepartmodel values('0',?,?,?)",new PreparedStatementSetter () 
		{

			public void setValues(PreparedStatement ps) throws SQLException 
			{
			    ps.setString(1, spmodel.getSpname());
			    ps.setInt(2,spmodel.getSpprice());
			    ps.setInt(3, spmodel.getSpqty());
			}
		});
		 
		return value>0?true:false;
	}
	
	
	public List<SparePartModel> getAllSparePart()
	{
		List<SparePartModel> spList=template.query("select *from sparepartmodel order by spname asc", new RowMapper<SparePartModel> () {

			public SparePartModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				SparePartModel spmodel=new SparePartModel();
				{
				  spmodel.setSpid(rs.getInt(1));
				  spmodel.setSpname(rs.getString(2));
				  spmodel.setSpprice(rs.getInt(3));
				  spmodel.setSpqty(rs.getInt(4));
				  return spmodel;
					
				}
			}
		});
		return spList;
	}
	
	
	public SparePartModel getSparePartById(int spid)
	{
		List<SparePartModel> spList=template.query("select *from sparepartmodel where spid=?",new Object[] {spid} ,new RowMapper<SparePartModel> () {

			public SparePartModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				SparePartModel spmodel=new SparePartModel();
				{
				  spmodel.setSpid(rs.getInt(1));
				  spmodel.setSpname(rs.getString(2));
				  spmodel.setSpprice(rs.getInt(3));
				  spmodel.setSpqty(rs.getInt(4));
				  return spmodel;
					
				}
			}
		});
		return spList.get(0);
	}
	
	
	public boolean isDeleteSparePartById(int spid)
	{
		int value=template.update("delete from sparepartmodel where spid="+spid);
		
		if(value>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean isUpdateSparePart(SparePartModel spmodel)
	{
		int value=template.update("update sparepartmodel set spname=? , spprice=? ,spqty=? where spid=?",new PreparedStatementSetter () {

			public void setValues(PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, spmodel.getSpname());
				ps.setInt(2, spmodel.getSpprice());
				ps.setInt(3, spmodel.getSpqty());
				ps.setInt(4, spmodel.getSpid());
			}
		});
		
		 return value>0?true:false;
	}
	
	
	public List<SparePartModel> getSparePartPattern(String spattern)
	{
		List<SparePartModel> sList=template.query("select *from sparepartmodel where spname like '%"+spattern+"%' ", new RowMapper<SparePartModel> () {
			                                       
			public SparePartModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				SparePartModel spmodel=new SparePartModel();
				{
				  spmodel.setSpid(rs.getInt(1));
				  spmodel.setSpname(rs.getString(2));
				  spmodel.setSpprice(rs.getInt(3));
				  spmodel.setSpqty(rs.getInt(4));
				  return spmodel;
				}
			}
		});
		return sList;
	}
	
	
	public int getSparePartPriceByName(String spname)
	{
		int spprice=template.queryForObject("select spprice from sparepartmodel where spname=?",new Object[] {spname}, new RowMapper<Integer> () {

			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getInt("spprice");
			}
			
		});
		
		return spprice;
	}

}
