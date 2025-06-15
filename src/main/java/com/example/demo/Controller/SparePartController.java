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

import com.example.demo.Model.SparePartModel;
import com.example.demo.Service.SparePartService;

@RestController
@CrossOrigin("http://localhost:5173/")
public class SparePartController 
{
	@Autowired
	SparePartService spService;
	
	
	@PostMapping("/addSparePart")
	public String addSparePart(@RequestBody SparePartModel spmodel)
	{
		boolean b=spService.isAddNewSparePart(spmodel);
		if(b)
		{
			return "SparePart Added Successfully....!";
		}
		else
		{
			return "SparePart NOT  Added Successfully....!";
		}
		
	}
	
	@GetMapping("/getAllSparePart")
	public List<SparePartModel> getAllSparePart()
	{
	    return spService.getAllSparePart();
	}
	
	
	@GetMapping("/getSparePart/{spid}")
	public SparePartModel getSparePartById(@PathVariable("spid") Integer spid)
	{
		return spService.getSparePartById(spid);
	}
	
	
	@DeleteMapping("/deleteSparePart/{spid}")
	public String deleteSparePart(@PathVariable("spid") Integer spid)
	{
		boolean b=spService.isDeleteSparePartById(spid);
		if(b)
		{
			return "Spare part Delete Successfully...!";
		}
		else
		{
			return "Spare part NOT Delete Successfully...!";
		}
	}
	
	
	@PutMapping("/updateSparePart")
	public String updateSparePart(@RequestBody SparePartModel spmodel)
	{
	    boolean b=spService.isUpdateSparePart(spmodel);
	    if(b)
	    {
	    	return "SparePart Updated Successfully...!";
	    }
	    else
	    {
	    	return "SparePart NOT Updated Successfully...!";
	    }
		
	}
	
	
	@GetMapping("/sparePartPattern/{spattern}")
	public List<SparePartModel> getSparePartPattern(@PathVariable("spattern") String spattern)
	{
		List<SparePartModel> pList=spService.getSparePartPattern(spattern);
		
		if(pList.size()>0)
		{
			return pList;
		}
		else
		{
			return null;
		}
		
	}
	
	
	@GetMapping("/sparePriceByName/{spname}")
	public int getSparePartPriceByName(@PathVariable("spname") String spname)
	{
		int spprice=spService.getSparePartPriceByName(spname);
		return spprice;
	}

}
