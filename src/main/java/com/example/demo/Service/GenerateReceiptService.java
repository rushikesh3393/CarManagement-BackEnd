
package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Model.GenerateReceiptModel;
import com.example.demo.Repository.GenerateReceiptRepository;

@Service
public class GenerateReceiptService 
{

    @Autowired
    GenerateReceiptRepository grRepo;

    public boolean generateNewReceipt(GenerateReceiptModel rmodel) 
    {
        return grRepo.generateNewReceipt(rmodel);
    }

    public String getCustNameByApid(int apid) 
    {
        return grRepo.getCustNameByApid(apid);
    }

    public String getVehicleByApid(int apid) 
    {
        return grRepo.getVehicleByApid(apid);
    }

    public int getServiceCostBySvname(String svname) 
    {
        return grRepo.getServiceCostBySvname(svname);
    }

    public int getSparePartPriceByName(String spname) 
    {
        return grRepo.getSparePartPriceByName(spname);
    }
    
    public List<GenerateReceiptModel> getAllReceipt()
    {
    	return grRepo.getAllReceipt();
    }
    
    public GenerateReceiptModel searchReceiptById(int apid)
    {
    	return grRepo.searchReceiptById(apid);
    }
    
    public boolean isDeleteReceipt(int apid)
    {
    	return grRepo.isDeleteReceipt(apid);
    }
    
    public boolean isUpdateReceipt(GenerateReceiptModel rmodel)
    {
    	return grRepo.isUpdateReceipt(rmodel);
    }
    
    public List<GenerateReceiptModel> getReceiptPattern(String recPattern)
    {
    	return grRepo.getReceiptPattern(recPattern);
    }
    
    public boolean isupdateStock(String Sparename,int spareqty)
    {
    	return grRepo.isupdateStock(Sparename, spareqty);
    }
}
