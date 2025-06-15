package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Model.AppointmentModel;
import com.example.demo.Repository.AppointmentRepository;

@Service("appservice")
public class Appointmentservice 
{
	@Autowired
	AppointmentRepository appRepo;
	
	public String getUserEmail(String cname)
	{
		return appRepo.getUserEmail(cname);
	}
	
	public boolean newAppointment(AppointmentModel app)
	{
		return appRepo.newAppointment(app);	
	}
	
	public AppointmentModel searchAppointmentByFields(String cname, java.sql.Date adate, String atime)
	{
		return appRepo.searchAppointmentByFields(cname, adate, atime);
	}

	
	public List<AppointmentModel> getAppointmentList()
	{
		return appRepo.getAppointmentList();
	}
	
	public AppointmentModel searchAppointment(int apid)
	{
		return appRepo.getAppointmentByApid(apid);
	}
	
	public boolean deleteAppointmentById(int apid)
	{
		return appRepo.deleteAppointmentById(apid);
	}
	
	public boolean updateAppointmentById(AppointmentModel app)
	{
		return appRepo.updateAppointmentById(app);
	}
	
	public List<AppointmentModel> getpattern(String pattern)
	{
		return appRepo.getpattern(pattern);  
	}
	
	public String getCustNameByApid(int apid) 
	{
		return appRepo.getCustNameByApid(apid);
	}
	
	public String getVehicleByApid(int apid)
	{
		return appRepo.getVehicleByApid(apid);
	}

}
