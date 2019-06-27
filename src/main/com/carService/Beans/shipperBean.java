package main.com.carService.Beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import main.com.carService.car.car;
import main.com.carService.car.carAppServiceImpl;
import main.com.carService.invoice.invoice;
import main.com.carService.invoice.invoiceAppServiceImpl;
import main.com.carService.invoiceCars.invoiceCar;
import main.com.carService.invoiceCars.invoiceCarAppServiceImpl;
import main.com.carService.loginNeeds.user;
import main.com.carService.shipper.shipper;
import main.com.carService.shipper.shipperAppServiceImpl;
import main.com.carService.tools.Constants;


@ManagedBean(name = "shipperBean")
@SessionScoped
public class shipperBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440236767354416L;


	/**
	 * 
	 */
	
	
 
	

	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	 

	@ManagedProperty(value = "#{carFacadeImpl}")
	private carAppServiceImpl carFacade;
	

	@ManagedProperty(value = "#{shipperFacadeImpl}")
	private shipperAppServiceImpl shipperFacade;
	
	@ManagedProperty(value = "#{invoiceFacadeImpl}")
	private invoiceAppServiceImpl invoiceFacade;
	
	@ManagedProperty(value = "#{invoiceCarFacadeImpl}")
	private invoiceCarAppServiceImpl invoiceCarFacade;
	
	private List<shipper> allshippers;
	
	private shipper selectedshipper;
	
	private shipper addNewshipper;
	
	private shipper shipperForInvoice;
	private invoice invoiceData;
	private List<car> carsForthisAccount;
	private List<car> carsForInvoice;
	private Integer selectedCarIdToBeAddedInInvoice;
	private Float carFeesInvoice;
	private Float invoiceFeesTax;
	
	public static int TaxPercentage=14;
	@PostConstruct
	public void init() {
		
		refresh();
		
		selectedshipper=new shipper();
		addNewshipper=new shipper();
		user userId=new user();
		addNewshipper.setUserId(userId);
		addNewshipper.setParentId(loginBean.getTheUserOfThisAccount());
		
		invoiceData=new invoice();
	}
	
	public void refresh(){
		allshippers=shipperFacade.getAllByParentId(loginBean.getTheUserOfThisAccount().getId());
		
		
		carsForthisAccount=new ArrayList<car>();
		carsForInvoice=new ArrayList<car>();
		
		//Get all cars for the main account to be used in the invoice
		List<car> wareHouseMain = carFacade.getAllWareHouseForMainUser(loginBean.getTheUserOfThisAccount().getId());
		List<car> dryCargoMain = carFacade.getAllDryCargoForMainUser(loginBean.getTheUserOfThisAccount().getId());
		List<car> transitMain = carFacade.getAllFrightInTransitForMainUser(loginBean.getTheUserOfThisAccount().getId());

		if(wareHouseMain!=null)
			carsForthisAccount.addAll(wareHouseMain);
		
		if(dryCargoMain!=null)
			carsForthisAccount.addAll(dryCargoMain);
		
		if(transitMain!=null)
			carsForthisAccount.addAll(transitMain);
		
	}
	
	public String getFormatedDate(Calendar c) {
		String dateTime="";
		if(c!=null) {
		dateTime = String.valueOf(c.get(Calendar.DAY_OF_MONTH)) +"/"+
				   String.valueOf(c.get(Calendar.MONTH)+1) +"/"+
				   String.valueOf(c.get(Calendar.YEAR));
		}
		return dateTime;
	}
	
	public void addCarToInvoice() {
		car selectedCarToBeAddedInInvoice= carFacade.getById(selectedCarIdToBeAddedInInvoice);
		carsForInvoice.add(selectedCarToBeAddedInInvoice);

		try {

			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoiceAdd.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void deleteCarInInvoice(int indexInList) {
		carsForInvoice.remove(indexInList);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoiceAdd.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void saveInvoiceData() {
		
		invoiceData.setUserIdCustomer(shipperForInvoice.getUserId());
		invoiceData.setUserIdIssuer(loginBean.getTheUserOfThisAccount());
		invoiceData.setDate(Calendar.getInstance());
		
		invoiceFacade.addinvoice(invoiceData);
		carFeesInvoice=(float) 0;
		invoiceFeesTax=(float) 0;
		for(int i=0;i<carsForInvoice.size();i++) {
			invoiceCar carinvoice=new invoiceCar();
			carinvoice.setCarId(carsForInvoice.get(i));
			carinvoice.setInvoiceId(invoiceData);
			
			invoiceCarFacade.addinvoiceCar(carinvoice);
			float landCost=carsForInvoice.get(i).getLandcost();
			float Seacost=carsForInvoice.get(i).getSeacost();
			float Commision=carsForInvoice.get(i).getCommision();
			float Fees=carsForInvoice.get(i).getFees();
					
			float totalForCar=(float) (landCost+Seacost
					+Commision+Fees);
			
			carFeesInvoice+=totalForCar;
			invoiceFeesTax=totalForCar*TaxPercentage/100;
		}
		
		
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoice.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void goToAddNewshipper() {
		addNewshipper=new shipper();
		user userId=new user();
		addNewshipper.setUserId(userId);
		addNewshipper.setParentId(loginBean.getTheUserOfThisAccount());
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/shipperAddNew.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void selectshipper(int idshipper) {
		selectedshipper=shipperFacade.getById(idshipper);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/shipperEdit.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void invoiceDetails(int idShipper) {
		shipperForInvoice=shipperFacade.getById(idShipper);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoiceAdd.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void saveNewshipperData() {
		user userNew= addNewshipper.getUserId();
		
		boolean isValid=checkValidForUser(addNewshipper);
		if(isValid) {
			boolean checkEmail = checkEmailIsExist(addNewshipper.getUserId().getEmail());
			if(checkEmail) {
		userNew.setActive(1);
		userNew.setDate(Calendar.getInstance());
		userNew.setRole(user.ROLE_SHIPPER);
		userNew.setPassword(new  Md5PasswordEncoder().encodePassword(userNew.getEmail(),userNew.getEmail()));
		
		loginBean.getUserDataFacede().adduser(userNew);
		
		addNewshipper.setParentId(loginBean.getTheUserOfThisAccount());
		shipperFacade.addshipper(addNewshipper);
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your shipper has been added.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
		Constants.sendEmailNewAccount(addNewshipper.getUserId().getFirstName(),addNewshipper.getUserId().getEmail(),addNewshipper.getUserId().getEmail());
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/shipperList.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}else {
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Check this ',\r\n" + 
						"			text: 'This email is already Registered',\r\n" + 
						"			left:\"2%\"\r\n" + 
						"		});");
			}
		}else {
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Check this ',\r\n" + 
					"			text: 'Check the Madatory fields',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
		}
	}

	private boolean checkEmailIsExist(String email) {
		// TODO Auto-generated method stub
		
		user the_user=loginBean.getUserDataFacede().getByEmail(email);
		if(the_user!=null) {
			return false;
		}
		return true;
	}

	private boolean checkValidForUser(shipper addNewshipper2) {
		// TODO Auto-generated method stub
		if(addNewshipper2.getUserId().getCompany().equals("")||addNewshipper2.getUserId().getCompany()==null) {
			return false;
		}
		
		if(addNewshipper2.getUserId().getEmail().equals("")||addNewshipper2.getUserId().getEmail()==null) {
			return false;
		}
		
		if(addNewshipper2.getUserId().getFirstName().equals("")||addNewshipper2.getUserId().getFirstName()==null) {
			return false;
		}
		
		if(addNewshipper2.getUserId().getLastName().equals("")||addNewshipper2.getUserId().getLastName()==null) {
			return false;
		}
		
		return true;
	}

	public void cancel() {
		System.out.println("Cancel");
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/shipperList.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateData() {

		boolean isValid=checkValidForUser(selectedshipper);
		if(isValid) {
		loginBean.getUserDataFacede().adduser(selectedshipper.getUserId());
		
		shipperFacade.addshipper(selectedshipper);

		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your data has been updated.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
		
		}else {
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Check this ',\r\n" + 
					"			text: 'Check the Madatory fields',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
			
		}
	}

	public main.com.carService.loginNeeds.loginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(main.com.carService.loginNeeds.loginBean loginBean) {
		this.loginBean = loginBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public shipperAppServiceImpl getshipperFacade() {
		return shipperFacade;
	}

	public void setshipperFacade(shipperAppServiceImpl shipperFacade) {
		this.shipperFacade = shipperFacade;
	}

	public List<shipper> getAllshippers() {
		return allshippers;
	}

	public void setAllshippers(List<shipper> allshippers) {
		this.allshippers = allshippers;
	}

	public shipper getSelectedshipper() {
		return selectedshipper;
	}

	public void setSelectedshipper(shipper selectedshipper) {
		this.selectedshipper = selectedshipper;
	}

	public shipper getAddNewshipper() {
		return addNewshipper;
	}

	public void setAddNewshipper(shipper addNewshipper) {
		this.addNewshipper = addNewshipper;
	}

	public shipperAppServiceImpl getShipperFacade() {
		return shipperFacade;
	}

	public void setShipperFacade(shipperAppServiceImpl shipperFacade) {
		this.shipperFacade = shipperFacade;
	}

	public carAppServiceImpl getCarFacade() {
		return carFacade;
	}

	public void setCarFacade(carAppServiceImpl carFacade) {
		this.carFacade = carFacade;
	}

	public shipper getShipperForInvoice() {
		return shipperForInvoice;
	}

	public void setShipperForInvoice(shipper shipperForInvoice) {
		this.shipperForInvoice = shipperForInvoice;
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

	public invoice getInvoiceData() {
		return invoiceData;
	}

	public void setInvoiceData(invoice invoiceData) {
		this.invoiceData = invoiceData;
	}

	public List<car> getCarsForthisAccount() {
		return carsForthisAccount;
	}

	public void setCarsForthisAccount(List<car> carsForthisAccount) {
		this.carsForthisAccount = carsForthisAccount;
	}

	public List<car> getCarsForInvoice() {
		return carsForInvoice;
	}

	public void setCarsForInvoice(List<car> carsForInvoice) {
		this.carsForInvoice = carsForInvoice;
	}

	public Integer getSelectedCarIdToBeAddedInInvoice() {
		return selectedCarIdToBeAddedInInvoice;
	}

	public void setSelectedCarIdToBeAddedInInvoice(Integer selectedCarIdToBeAddedInInvoice) {
		this.selectedCarIdToBeAddedInInvoice = selectedCarIdToBeAddedInInvoice;
	}

	public Float getCarFeesInvoice() {
		return carFeesInvoice;
	}

	public void setCarFeesInvoice(Float carFeesInvoice) {
		this.carFeesInvoice = carFeesInvoice;
	}

	public Float getInvoiceFeesTax() {
		return invoiceFeesTax;
	}

	public void setInvoiceFeesTax(Float invoiceFeesTax) {
		this.invoiceFeesTax = invoiceFeesTax;
	}

	public static int getTaxPercentage() {
		return TaxPercentage;
	}

	public static void setTaxPercentage(int taxPercentage) {
		TaxPercentage = taxPercentage;
	}
	

	
	
}
