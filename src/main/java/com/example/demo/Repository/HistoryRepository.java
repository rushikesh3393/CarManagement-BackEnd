package com.example.demo.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.HistoryModel;
import com.example.demo.Model.UserModel;

@Repository("hrepo")
public class HistoryRepository 
{
	@Autowired
	JdbcTemplate template;
	
	List<HistoryModel> ulist;
	
	public List<HistoryModel> getAllHistory() {
	    String sql = "select *from History";

	    return template.query(sql, (rs, rowNum) -> {
	        HistoryModel hmodel = new HistoryModel();

	        hmodel.setCname(rs.getString("cname"));
	        hmodel.setVnumber(rs.getString("vnumber"));
	        hmodel.setServicetype(rs.getString("servicetype"));
	        hmodel.setServicecost(rs.getInt("servicecost"));
	        hmodel.setSparename(rs.getString("sparename"));
	        hmodel.setSpareqty(rs.getInt("spareqty"));
	        hmodel.setSpareamt(rs.getInt("spareamt"));
	        hmodel.setTotalAmount(rs.getInt("totalamount"));

	        return hmodel;
	    });
	}

}

