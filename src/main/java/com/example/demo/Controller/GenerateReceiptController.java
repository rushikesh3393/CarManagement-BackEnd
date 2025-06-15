package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Model.GenerateReceiptModel;
import com.example.demo.Service.GenerateReceiptService;

@RestController
@CrossOrigin("http://localhost:5173/")
public class GenerateReceiptController {

    @Autowired
    GenerateReceiptService rservice;

    @PostMapping("/generateReceipt")
    public String generateReceipt(@RequestBody GenerateReceiptModel rmodel) 
    {
        String cname = rservice.getCustNameByApid(rmodel.getApid());
       
        String vnumber = rservice.getVehicleByApid(rmodel.getApid());
      
        int servicePrice = rservice.getServiceCostBySvname(rmodel.getServicetype());
       
        int sparePrice = rservice.getSparePartPriceByName(rmodel.getSparename());
     
        int spareAmount = sparePrice * rmodel.getSpareqty();
   
        int totalAmount = servicePrice + spareAmount;
        float amount=(float)(totalAmount*0.18f);

        rmodel.setCname(cname);
        rmodel.setVnumber(vnumber);
        rmodel.setServicePrice(servicePrice);
        rmodel.setSpareamt(spareAmount);
        rmodel.setTotalAmount(totalAmount);

        
        boolean b = rservice.generateNewReceipt(rmodel);
        if(b)
        {
        	rservice.isupdateStock(rmodel.getSparename(),rmodel.getSpareqty());
        	return "Invoice Generated Successfully...!";
        }
        else
        {
        	return "Invoice NOT Generated Successfully...!";
        }
        
    }
    
    
    @GetMapping("/getAllReceipt")
    public List<GenerateReceiptModel> getAllReceipt()
    {
    	List<GenerateReceiptModel> receiptList=rservice.getAllReceipt();
    	return receiptList;
    }
    
    
    @GetMapping("/searchReceipt/{apid}")
    public GenerateReceiptModel getReceiptByApid(@PathVariable("apid") Integer apid)
    {
    	return rservice.searchReceiptById(apid);
    }
    
    
    @DeleteMapping("/deleteReceipt/{apid}")
    public String isDeleteReceipt(@PathVariable("apid") Integer apid)
    {
    	boolean b=rservice.isDeleteReceipt(apid);
    	if(b)
    	{
    		return "Receipt deleted SuccessFully...!";
    	}
    	else
    	{
    		return "Receipt NOT deleted SuccessFully...!";
    	}
    	
    }
    
    
    @PutMapping("/updateReceipt")
    public String isUpdateReceipt(@RequestBody GenerateReceiptModel rmodel)
    {
    	boolean b=rservice.isUpdateReceipt(rmodel);
    	if(b)
    	{
    		return "Receipt Updated Successfully...!";
    	}
    	else
    	{
    		return "Receipt NOT Updated Successfully...!";
    	}
    }
    
    
    @GetMapping("/receiptPttern/{recPattern}")
    public List<GenerateReceiptModel> getReceiptPattern(@PathVariable("recPattern") String recPattern)
    {
    	List<GenerateReceiptModel> rpattern=rservice.getReceiptPattern(recPattern);
		return rpattern;
    	
    }
}