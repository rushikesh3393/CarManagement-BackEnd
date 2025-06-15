package com.example.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Model.GenerateReceiptModel;

@Repository
public class GenerateReceiptRepository {

    @Autowired
    JdbcTemplate template;

    public String getCustNameByApid(int apid) 
    {
        String ccname= template.queryForObject("select cname from appointmentmodel where apid = ?",new Object[]{apid},new RowMapper<String>() 
        {
            public String mapRow(ResultSet rs, int rowNum) throws SQLException 
               {
                  return rs.getString("cname");
               }
            }
        );
        return ccname;
    }

    
    public String getVehicleByApid(int apid) 
    {
        String vvname= template.queryForObject("select vnumber from appointmentmodel where apid = ?", new Object[]{apid},new RowMapper<String>() 
        {
            public String mapRow(ResultSet rs, int rowNum) throws SQLException 
               {
                    return rs.getString("vnumber");
               }
            }
        );
        
        return vvname;
    }

    public int getServiceCostBySvname(String svname) 
    {
        int svscost= template.queryForObject("select svcost from servicesmodel where servicetype = ?",new Object[]{svname},new RowMapper<Integer>() 
        {
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException 
                {
                    return rs.getInt("svcost");
                }
            }
        );
        
        return svscost;
    }

    public int getSparePartPriceByName(String spname) 
    {
        int spprice= template.queryForObject( "select spprice from sparepartmodel where spname = ?",new Object[]{spname},new RowMapper<Integer>() 
        {
             public Integer mapRow(ResultSet rs, int rowNum) throws SQLException 
                {
                    return rs.getInt("spprice");
                }
            }
        );
        return spprice;
    }

    public boolean generateNewReceipt(GenerateReceiptModel rmodel) 
    {
        int value = template.update("insert into receipt values (?, ?, ?, ?, ?, ?, ?, ?, ?)", new PreparedStatementSetter() 
        {
            public void setValues(PreparedStatement ps) throws SQLException 
            {
                ps.setInt(1, rmodel.getApid());
                ps.setString(2, rmodel.getCname());
                ps.setString(3, rmodel.getVnumber());
                ps.setString(4, rmodel.getServicetype());
                ps.setInt(5, rmodel.getServicePrice());
                ps.setString(6, rmodel.getSparename());
                ps.setInt(7, rmodel.getSpareqty());
                ps.setInt(8, rmodel.getSpareamt());
                ps.setInt(9, rmodel.getTotalAmount());
            }
        });
        return value > 0 ? true : false;
    }
    
    public List<GenerateReceiptModel> getAllReceipt()
    {
        List<GenerateReceiptModel> rList=template.query("select *from receipt",new RowMapper<GenerateReceiptModel> () 
        {
			public GenerateReceiptModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				GenerateReceiptModel gmodel=new GenerateReceiptModel();
				gmodel.setApid(rs.getInt(1));
				gmodel.setCname(rs.getString(2));
				gmodel.setVnumber(rs.getString(3));
				gmodel.setServicetype(rs.getString(4));
				gmodel.setServicePrice(rs.getInt(5));
				gmodel.setSparename(rs.getString(6));
				gmodel.setSpareqty(rs.getInt(7));
				gmodel.setSpareamt(rs.getInt(8));
				gmodel.setTotalAmount(rs.getInt(9));
				
				return gmodel;
			}
        } );
        return rList;
    }
    
    
    public GenerateReceiptModel searchReceiptById(int apid)
    {
    	List<GenerateReceiptModel> rList=template.query("select *from receipt where apid=?",new Object[] {apid},new RowMapper<GenerateReceiptModel> () 
        {
			public GenerateReceiptModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				GenerateReceiptModel gmodel=new GenerateReceiptModel();
				gmodel.setApid(rs.getInt(1));
				gmodel.setCname(rs.getString(2));
				gmodel.setVnumber(rs.getString(3));
				gmodel.setServicetype(rs.getString(4));
				gmodel.setServicePrice(rs.getInt(5));
				gmodel.setSparename(rs.getString(6));
				gmodel.setSpareqty(rs.getInt(7));
				gmodel.setSpareamt(rs.getInt(8));
				gmodel.setTotalAmount(rs.getInt(9));
				
				return gmodel;
			}
        } );
        return rList.get(0);
    }
    
    
    public boolean isDeleteReceipt(int apid)
    {
    	int value=template.update("delete from receipt where apid="+apid);
    	if(value>0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    
    public boolean isUpdateReceipt(GenerateReceiptModel rmodel)
    {
    	int value=template.update("update receipt set servicetype=?,sparename=?,spareqty=? where apid=?",new PreparedStatementSetter () 
    	{
    		public void setValues(PreparedStatement ps) throws SQLException 
			{
					ps.setString(1, rmodel.getServicetype());
					ps.setString(2, rmodel.getSparename());
					ps.setInt(3, rmodel.getSpareqty());
					ps.setInt(4,rmodel.getApid());
			}
    	});
    	
    	if(value>0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	
    }
    
    
    public List<GenerateReceiptModel> getReceiptPattern(String recPattern)
    {
    	List<GenerateReceiptModel> recList=template.query("select *from receipt where cname like '%"+recPattern+"%' ", new RowMapper<GenerateReceiptModel> () {

    		public GenerateReceiptModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				GenerateReceiptModel gmodel=new GenerateReceiptModel();
				gmodel.setApid(rs.getInt(1));
				gmodel.setCname(rs.getString(2));
				gmodel.setVnumber(rs.getString(3));
				gmodel.setServicetype(rs.getString(4));
				gmodel.setServicePrice(rs.getInt(5));
				gmodel.setSparename(rs.getString(6));
				gmodel.setSpareqty(rs.getInt(7));
				gmodel.setSpareamt(rs.getInt(8));
				gmodel.setTotalAmount(rs.getInt(9));
				
				return gmodel;
			}
        } );
        return recList;
    }
    
    public boolean isupdateStock(String Sparename,int spareqty)
    {
    	int value=template.update("update sparepartmodel set spqty=spqty-? where spname=?", new PreparedStatementSetter () {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, spareqty);
				ps.setString(2, Sparename);
				
			}
    		
    	});
    	if(value>0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

}
