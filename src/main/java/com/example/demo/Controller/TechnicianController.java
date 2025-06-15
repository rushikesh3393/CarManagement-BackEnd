package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.TechnicianModel;
import com.example.demo.Service.TechnicianService;

@RestController
@CrossOrigin("http://localhost:5173/")
public class TechnicianController 
{
	List<TechnicianModel> techList;
	@Autowired
	TechnicianService techService;
	
	@PostMapping("/addTechnician")
	public String createTechnician(@RequestBody TechnicianModel tmodel)
	{
		boolean b=techService.isAddNewTechnician(tmodel);
		if(b)
		{
			return "Technician Added Successfully...!";
		}
		else
		{
			return "Technician NOT Added Successfully..!";	
		}
	}
	
	
	@GetMapping("/techicianList")
	public List<TechnicianModel> getAllTechnicians()
	{
		return techService.getAllTechnicians();
	}
	
	
	@GetMapping("/getTechnician/{tid}")
	public TechnicianModel getTechnician(@PathVariable("tid")  Integer tid)
	{
		return  techService.getTechnicianById(tid);	
	}
	
	
	@DeleteMapping("deleteTechnician/{tid}")
	public String DeleteTechnician(@PathVariable("tid") Integer tid)
	{
		boolean b=techService.isDeleteTechnician(tid);
		if(b)
		{
			return "Technician Deleted Successfully...!";
		}
		else
		{
			return "Technician  NOT Deleted Successfully...!";
		}
	}
	
	
	@PutMapping("/updateTechnician")
	public String UpdateTechnician(@RequestBody TechnicianModel tmodel)
	{
		boolean b=techService.isUpdateTechnician(tmodel);
		if(b)
		{
			return "Technician Updated Successfully...!";
		}
		else
		{
			return "Technician NOT Updated Successfully...!";
		}
		
	}
	
	
	@GetMapping("/getTechSearchpattern/{pattern}")
	public List<TechnicianModel> getAllCustomPattern(@PathVariable("pattern") String pattern)
	{
		List<TechnicianModel> pList=techService.getAllCustomPattern(pattern);
		
		if(pList.size()>0)
		{
			return pList;
		}
		else
		{
			return null;
		}
	}

}
