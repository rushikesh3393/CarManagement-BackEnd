package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.AppointmentModel;
import com.example.demo.Service.Appointmentservice;


@RestController
@CrossOrigin("http://localhost:5173/")
public class AppointmentController 
{
	@Autowired
	Appointmentservice appservice;

	@Autowired
    JavaMailSender javaMailSender;

	@PostMapping("/getAppointment")
	public ResponseEntity<AppointmentModel> bookAppointment(@RequestBody AppointmentModel app) {
	    boolean isSaved = appservice.newAppointment(app);
	    
	    if (!isSaved) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    AppointmentModel saved = appservice.searchAppointmentByFields(app.getCname(), app.getAdate(), app.getAtime());

	    String email = appservice.getUserEmail(app.getCname());
	    if (email != null && !email.isEmpty())
	    {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject("Booking Confirmation");
	        message.setText("Dear " + app.getCname() +
	            ",\n\nYour appointment is confirmed. Please visit our garage at your scheduled date and time for the service." +
	            "\n\nDate: " + app.getAdate() +
	            "\nTime: " + app.getAtime() +
	            "\nBooking ID: " + saved.getApid() +
	            "\n\nThank you!"+
	            "\n\n\n\n Regards," +
	            "\n RMOTORS Garage Services "+
	            "\n Contact:8055967453"+
	            "\n Email:rmotorsgarageservices2406@gmail.com");

	        javaMailSender.send(message);
	    }

	    return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}


	
	
	@GetMapping("/getAppointmentList")
	public List<AppointmentModel> getAllAppointment()
	{
		return appservice.getAppointmentList();
	}
	
	
	@GetMapping("/searchAppointment/{apid}")
	public AppointmentModel searchAppointmentByApid(@PathVariable("apid") Integer apid)
	{
		AppointmentModel ap=appservice.searchAppointment(apid);
		if(ap!=null)
		{
			return ap;
		}
		else
		{
			return null;
		}
	}
	
	
	@DeleteMapping("/cancelAppointment/{apid}")
	public String deleteAppointmentById(@PathVariable("apid") Integer apid)
	{
		boolean b=appservice.deleteAppointmentById(apid);
		if(b)
		{
			return "Appointment Cancel Successfully....!";
		}
		else
		{
			return "Appointment Cancel Successfully....!";
		}
		
	}
	
	
	@PutMapping("/updateAppointment")
	public String UpdateAppointmentById(@RequestBody AppointmentModel app)
	{
		boolean b=appservice.updateAppointmentById(app);
		if(b)
		{
			return "Your Appointment Updated Successfully...!";
		}
		else
		{
			return "Your Appointment NOT Updated Successfully...!";	
		}	
	}
	
	@GetMapping("/searchPattern/{pattern}")
	public List<AppointmentModel> getpattern(@PathVariable("pattern")  String pattern)
	{
		List<AppointmentModel> plist=appservice.getpattern(pattern);
		
		if(plist.size()>0)
		{
			return plist;
		}
		else
		{
			return null;
		}
	}
	
	@GetMapping("/getCustByApid/{apid}")
	public String getCustNameByApid(@PathVariable("apid") Integer apid)
	{
		String custName=appservice.getCustNameByApid(apid);
		return custName;
	}
	
	@GetMapping("/getVehicleByApid/{apid}")
	public String getVehicleByApid(@PathVariable("apid") Integer apid)
	{
		String vnumber=appservice.getVehicleByApid(apid);
		return vnumber;
	}
	
	
	

}
