package main.com.carService.Beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import main.com.carService.invoice.invoiceDTO;
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
	
	private List<invoiceDTO> allInvoice;
	private String dateLower;
	private String dateHigh;
	private float totalFees;
	
	
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
	
	public void getAllInvoicesBetweenDates() {
		totalFees = 0;
		Calendar lowDate = setCalendarFromString(dateLower);
		Calendar highDate = setCalendarFromString(dateHigh);
		
		allInvoice =new ArrayList<invoiceDTO>();
		List<invoice> allInvoicesForThisMainAccount =new ArrayList<invoice>();
		if(lowDate == null || highDate ==null) {
			allInvoicesForThisMainAccount = invoiceFacade.getAllByUserId(loginBean.getTheUserOfThisAccount().getId());
			
		}else {
		allInvoicesForThisMainAccount = invoiceFacade.getAllByUserIdBetweenDates(loginBean.getTheUserOfThisAccount().getId(),lowDate,highDate);
		}
		if(allInvoicesForThisMainAccount!=null) {
		if(allInvoicesForThisMainAccount.size()>0) {
		for(int i=0;i<allInvoicesForThisMainAccount.size();i++) {
			int totalFeesForInvoice = 0;
			List<invoiceCar> allCarsForThisInvoice = invoiceCarFacade.getAllByinvoiceId(allInvoicesForThisMainAccount.get(i).getId());
			for(int j=0;j<allCarsForThisInvoice.size();j++) {
				car selectedCar = allCarsForThisInvoice.get(j).getCarId();
				float landCost=0;
				float Seacost=0;
				float Commision=0;
				float Fees=0;
				
				
				if(selectedCar.getLandcost()!=null) landCost=selectedCar.getLandcost();
				if(selectedCar.getSeacost()!=null) Seacost=selectedCar.getSeacost();
				if(selectedCar.getCommision()!=null) Commision=selectedCar.getCommision();
				if(selectedCar.getFees()!=null) Fees=selectedCar.getFees();
						
				float totalForCar=(float) (landCost+Seacost
						+Commision+Fees);
				
				
				//This for total Fees Without Transfer
				totalFeesForInvoice+=totalForCar;
				
				
				//This for the total Fees With Transfer

				float trFees = 0;
				if(allInvoicesForThisMainAccount.get(i).getTransferFees()!=null) {
					trFees=allInvoicesForThisMainAccount.get(i).getTransferFees();
				}
				totalFees = totalFees + totalForCar + (totalForCar/100*trFees);
			}
			
			invoiceDTO invoicedto =new invoiceDTO();
			invoicedto.setCarsForInvoice(allCarsForThisInvoice);
			invoicedto.setInvoice(allInvoicesForThisMainAccount.get(i));
			invoicedto.setTotalPrice(totalFeesForInvoice);
			invoicedto.setNumberOfCars(allCarsForThisInvoice.size());
			
			allInvoice.add(invoicedto);
		}
		}
		}
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoiceList.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	private Calendar setCalendarFromString(String cargoRecievedDate2) {

		Calendar cal = null;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-dd-MM HH:mm:ss"); 
		try {
			if(cargoRecievedDate2!=null) {
			if(!cargoRecievedDate2.equals("")) {
				cal=Calendar.getInstance();
				Date date=formatter.parse(cargoRecievedDate2);
				cal.setTime(date);
			}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return cal;
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
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoiceAdd.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void deleteCarInInvoice(int indexInList) {
		carsForInvoice.remove(indexInList);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoiceAdd.jsf?faces-redirect=true");
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
		}
		
		
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoice.jsf?faces-redirect=true");
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
			   .getExternalContext().redirect("/pages/secured/shipper/shipperAddNew.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void selectshipper(int idshipper) {
		selectedshipper=shipperFacade.getById(idshipper);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/shipperEdit.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void invoiceDetails(int idShipper) {
		shipperForInvoice=shipperFacade.getById(idShipper);
		
		
		carsForthisAccount=new ArrayList<car>();
		
		List<car> wareHouseMain = carFacade.getAllWareHouseForShipper(idShipper);
		List<car> dryCargoMain = carFacade.getAllDryCargoForShipper(idShipper);
		List<car> transitMain = carFacade.getAllFrightInTransitForShipper(idShipper);

		if(wareHouseMain!=null)
			carsForthisAccount.addAll(wareHouseMain);
			
		if(dryCargoMain!=null)
			carsForthisAccount.addAll(dryCargoMain);
			
		if(transitMain!=null)
			carsForthisAccount.addAll(transitMain);
			
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoiceAdd.jsf?faces-redirect=true");
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
		userNew.setActive(true);
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
			   .getExternalContext().redirect("/pages/secured/shipper/shipperList.jsf?faces-redirect=true");
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
			   .getExternalContext().redirect("/pages/secured/shipper/shipperList.jsf?faces-redirect=true");
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

	public List<invoiceDTO> getAllInvoice() {
		return allInvoice;
	}

	public void setAllInvoice(List<invoiceDTO> allInvoice) {
		this.allInvoice = allInvoice;
	}

	public String getDateLower() {
		return dateLower;
	}

	public void setDateLower(String dateLower) {
		this.dateLower = dateLower;
	}

	public String getDateHigh() {
		return dateHigh;
	}

	public void setDateHigh(String dateHigh) {
		this.dateHigh = dateHigh;
	}

	public float getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(float totalFees) {
		this.totalFees = totalFees;
	}

	

	
	
}
