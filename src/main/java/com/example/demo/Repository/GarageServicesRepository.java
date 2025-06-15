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

import com.example.demo.Model.GarageServicesModel;

@Repository("gsRepo")
public class GarageServicesRepository 
{
	 @Autowired
     JdbcTemplate template;
	 
	 List<GarageServicesModel> gsList;
	 
	 public boolean isAddNewService(GarageServicesModel gsmodel)
	 {
		 int value=template.update("insert into servicesmodel values('0',?,?)",new PreparedStatementSetter () 
		 {
			public void setValues(PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, gsmodel.getServicetype());
				ps.setInt(2, gsmodel.getSvcost());	
			} 
		 });
		 return value>0?true:false; 
	 }
	 
	 
	 public List<GarageServicesModel> getAllServices()
	 {
		 List<GarageServicesModel> ServiceList=template.query("select *from servicesmodel",new RowMapper<GarageServicesModel>() {

			public GarageServicesModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				GarageServicesModel gsmodel=new GarageServicesModel();
				gsmodel.setSvid(rs.getInt(1));
				gsmodel.setServicetype(rs.getString(2));
				gsmodel.setSvcost(rs.getInt(3));
				return gsmodel;
			}	 
		 });
		return ServiceList;
	 }
	 
	 
	 public GarageServicesModel getServiceById(int svid)
	 { 
		 List<GarageServicesModel> ServiceList=template.query("select *from servicesmodel where svid=?",new Object[] {svid},new RowMapper<GarageServicesModel>() {

			public GarageServicesModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				GarageServicesModel gsmodel=new GarageServicesModel();
				gsmodel.setSvid(rs.getInt(1));
				gsmodel.setServicetype(rs.getString(2));
				gsmodel.setSvcost(rs.getInt(3));
				return gsmodel;
			}	 
		 });
		return ServiceList.get(0);
	 }
	 
	 
	 public boolean isDeleteServiceById(int svid)
	 {
		 int value=template.update("delete from servicesmodel where svid="+svid);
		 if(value>0)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
				 
	 }
	 
	 public boolean isUpdateService(GarageServicesModel gmodel)
	 {
		 int value=template.update("update servicesmodel set servicetype=?,svcost=? where svid=?",new PreparedStatementSetter () 
		 {
			public void setValues(PreparedStatement ps) throws SQLException 
			{
				ps.setString(1, gmodel.getServicetype());
				ps.setInt(2, gmodel.getSvcost());
				ps.setInt(3, gmodel.getSvid());
			} 
		 });
		return value>0?true:false;
		 
	 }
	 
	 
	 public List<GarageServicesModel> getServiceByPattern(String patterns)
	 {
		 List<GarageServicesModel> gsList=template.query("select * from servicesmodel where servicetype like '%"+patterns+"%'", new RowMapper<GarageServicesModel> () {

			public GarageServicesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				GarageServicesModel gmodel=new GarageServicesModel();
				gmodel.setSvid(rs.getInt(1));
				gmodel.setServicetype(rs.getString(2));
				gmodel.setSvcost(rs.getInt(3));
				
				return gmodel;
			}
		 });
		 return gsList;
	 }
	 
	 public int getServiceCostBySvname(String svname)
	 {
		 int scost=template.queryForObject("select svcost from servicesmodel where servicetype=?",new Object[] {svname},new RowMapper<Integer> () {

			public Integer  mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				return rs.getInt("svcost");
			}
			 
		 } );
		 return scost;
	 }
}
