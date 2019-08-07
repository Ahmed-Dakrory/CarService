package main.com.carService.tools;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import main.com.carService.car.car;
import main.com.carService.car.carRepository;
import main.com.carService.invoice.invoiceAppServiceImpl;
import main.com.carService.invoiceCars.invoiceCarAppServiceImpl;
import main.com.carService.shipper.shipperAppServiceImpl;
import main.com.carService.vendor.vendorAppServiceImpl;

@Component
public class SampleJob
{
	
	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	 


	@ManagedProperty(value = "#{vendorFacadeImpl}")
	private vendorAppServiceImpl vendorFacade;
	
	@ManagedProperty(value = "#{shipperFacadeImpl}")
	private shipperAppServiceImpl shipperFacade;
	

	
	@ManagedProperty(value = "#{invoiceFacadeImpl}")
	private invoiceAppServiceImpl invoiceFacade;
	
	@ManagedProperty(value = "#{invoiceCarFacadeImpl}")
	private invoiceCarAppServiceImpl invoiceCarFacade;

	@Autowired
	carRepository carDataRepository;
	
	public void run() {
	      List<car> allCars=carDataRepository.getAllWithAllowSendState(true);
	      for(int i=0;i<allCars.size();i++) {
	    	  car selectedCar = allCars.get(i);
	    	  if(selectedCar.getEmailToSendComment()!=null) {
	    		  
	    		  if(!selectedCar.getEmailToSendComment().equalsIgnoreCase("")) {
	    	  String[] AllEmails = selectedCar.getEmailToSendComment().split(";");
	    	  for(int j=0;j<AllEmails.length;j++) {
	    		  String sentEmail= AllEmails[j];
	    		  //System.out.println("Ahmed Dakrory: "+sentEmail);
	    		  Constants.sendEmailNotificationForThisEmailWithMessage(selectedCar, selectedCar.getCommentToSend(), sentEmail);
	    	  }
	    		  }
	    	  }
	      }
			//System.out.println("Hello Quartz!: "+allCars.size());	
	   }
	



	public main.com.carService.loginNeeds.loginBean getLoginBean() {
		return loginBean;
	}



	public void setLoginBean(main.com.carService.loginNeeds.loginBean loginBean) {
		this.loginBean = loginBean;
	}



	public vendorAppServiceImpl getVendorFacade() {
		return vendorFacade;
	}



	public void setVendorFacade(vendorAppServiceImpl vendorFacade) {
		this.vendorFacade = vendorFacade;
	}



	public shipperAppServiceImpl getShipperFacade() {
		return shipperFacade;
	}



	public void setShipperFacade(shipperAppServiceImpl shipperFacade) {
		this.shipperFacade = shipperFacade;
	}



	public invoiceAppServiceImpl getInvoiceFacade() {
		return invoiceFacade;
	}



	public void setInvoiceFacade(invoiceAppServiceImpl invoiceFacade) {
		this.invoiceFacade = invoiceFacade;
	}



	public invoiceCarAppServiceImpl getInvoiceCarFacade() {
		return invoiceCarFacade;
	}



	public void setInvoiceCarFacade(invoiceCarAppServiceImpl invoiceCarFacade) {
		this.invoiceCarFacade = invoiceCarFacade;
	}



	public carRepository getCarDataRepository() {
		return carDataRepository;
	}



	public void setCarDataRepository(carRepository carDataRepository) {
		this.carDataRepository = carDataRepository;
	}




	
	
	
}