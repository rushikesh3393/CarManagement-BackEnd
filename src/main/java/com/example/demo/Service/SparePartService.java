package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Model.SparePartModel;
import com.example.demo.Repository.SparePartRepository;

@Service("spService")
public class SparePartService 
{
	@Autowired
	SparePartRepository spRepo;
	
	public boolean isAddNewSparePart(SparePartModel spmodel)
	{
		return spRepo.isAddNewSparePart(spmodel);
	}
	
	public List<SparePartModel> getAllSparePart()
	{
		return spRepo.getAllSparePart();
	}
	
	public SparePartModel getSparePartById(int spid)
	{
		return spRepo.getSparePartById(spid);
	}
	
	public boolean isDeleteSparePartById(int spid)
	{
		return spRepo.isDeleteSparePartById(spid);
	}
	
	public boolean isUpdateSparePart(SparePartModel spmodel)
	{
		return spRepo.isUpdateSparePart(spmodel);
	}
	
	public List<SparePartModel> getSparePartPattern(String spattern)
	{
		return spRepo.getSparePartPattern(spattern);
	}
	
	public int getSparePartPriceByName(String spname)
	{
		return spRepo.getSparePartPriceByName(spname);
	}

}
