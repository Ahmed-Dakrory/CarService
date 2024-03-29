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
import org.primefaces.event.RowEditEvent;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import main.com.carService.car.car;
import main.com.carService.car.carAppServiceImpl;
import main.com.carService.form_settings.form_settings;
import main.com.carService.form_settings.form_settingsAppServiceImpl;
import main.com.carService.invoice.invoice;
import main.com.carService.invoice.invoiceAppServiceImpl;
import main.com.carService.invoice.invoiceDTO;
import main.com.carService.invoiceCars.invoiceCar;
import main.com.carService.invoiceCars.invoiceCarAppServiceImpl;
import main.com.carService.log_info.log_info;
import main.com.carService.log_info.log_infoAppServiceImpl;
import main.com.carService.loginNeeds.user;
import main.com.carService.moneyBox.moneybox;
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
	
	
 

	@ManagedProperty(value = "#{log_infoFacadeImpl}")
	private log_infoAppServiceImpl log_infoFacade;

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
	
	
	


	@ManagedProperty(value = "#{form_settingsFacadeImpl}")
	private form_settingsAppServiceImpl form_settingsFacade;
	

	private List<form_settings> allform_settings;
	private form_settings selectedformSetting;

	private List<shipper> allshippers;
	
	private shipper selectedshipper;
	
	private shipper addNewshipper;
	private user userForInvoice;
	private shipper shipperForInvoice;
	private invoice invoiceData;
	private List<car> carsForthisAccount;
	private List<car> carsForInvoice;
	private Integer selectedCarIdToBeAddedInInvoice;
	
	private List<car> carsForContainer;
	private List<car> containers_for_user;
	private String selectedContainerToBeAddedInInvoice;
	
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
	
	
	private void addCarActionForLog(car car,String sttt) {
	// TODO Auto-generated method stub

		log_info data_info =new log_info();
//		System.out.println(new Date());
//		System.out.println(car.getId());
		
		
//		System.out.println(loginBean.getTheUserOfThisAccount().getId());
//		System.out.println(loginBean.getTheUserOfThisAccount().getEmail());
		data_info.setCarId(car);

//		System.out.println("--------------------Ahmed 1------------");
		data_info.setData(sttt);
//		System.out.println("--------------------Ahmed 2------------");
		data_info.setUserId(loginBean.getTheUserOfThisAccount());
//		System.out.println("--------------------Ahmed 3------------");
		data_info.setDate(new Date());
//		System.out.println("--------------------Ahmed 4------------");
		data_info.setObject("car");
//		System.out.println("--------------------Ahmed 5------------");
		data_info.setObject_id(String.valueOf(car.getId()));

//		System.out.println(car.getId());
//		System.out.println("--------------------Ahmed 6------------");
		log_infoFacade.addlog_info(data_info);
//		System.out.println("--------------------Ahmed 7------------");
}

	
	public void refreshAllSettings() {
		allform_settings = form_settingsFacade.getAll();
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
			try {
			for(int j=0;j<allCarsForThisInvoice.size();j++) {
				car selectedCar = allCarsForThisInvoice.get(j).getCarId();
				
				float totalForCar= selectedCar.getTotal_amount_for_this_car();
				
				
				//This for total Fees Without Transfer
				totalFeesForInvoice+=totalForCar;
				
				
				//This for the total Fees With Transfer

				float trFees = 0;
				if(allInvoicesForThisMainAccount.get(i).getTransferFees()!=null) {
					trFees=allInvoicesForThisMainAccount.get(i).getTransferFees();
				}
				totalFees = totalFees + totalForCar + (totalForCar/100*trFees);
			}
			}catch(Error er) {
				
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
	
	
	
	public void getAllInvoicesBetweenDates_user() {
		totalFees = 0;
		Calendar lowDate = setCalendarFromString(dateLower);
		Calendar highDate = setCalendarFromString(dateHigh);
		
		allInvoice =new ArrayList<invoiceDTO>();
		List<invoice> allInvoicesForThisMainAccount =new ArrayList<invoice>();
		if(lowDate == null || highDate ==null) {
			allInvoicesForThisMainAccount = invoiceFacade.getAllByCustomerId(loginBean.getTheUserOfThisAccount().getId());
			
		}else {
		allInvoicesForThisMainAccount = invoiceFacade.getAllByCustomerIdBetweenDates(loginBean.getTheUserOfThisAccount().getId(),lowDate,highDate);
		}
		if(allInvoicesForThisMainAccount!=null) {
		if(allInvoicesForThisMainAccount.size()>0) {
		for(int i=0;i<allInvoicesForThisMainAccount.size();i++) {
			int totalFeesForInvoice = 0;
			List<invoiceCar> allCarsForThisInvoice = invoiceCarFacade.getAllByinvoiceId(allInvoicesForThisMainAccount.get(i).getId());
			try {
			for(int j=0;j<allCarsForThisInvoice.size();j++) {
				car selectedCar = allCarsForThisInvoice.get(j).getCarId();
				float totalForCar= selectedCar.getTotal_amount_for_this_car();
				
				
				//This for total Fees Without Transfer
				totalFeesForInvoice+=totalForCar;
				
				
				//This for the total Fees With Transfer

				float trFees = 0;
				if(allInvoicesForThisMainAccount.get(i).getTransferFees()!=null) {
					trFees=allInvoicesForThisMainAccount.get(i).getTransferFees();
				}
				totalFees = totalFees + totalForCar + (totalForCar/100*trFees);
			}
			}catch(Error er) {
				
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
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice_main/invoiceList_normal.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	 public void onRowEdit(RowEditEvent event) {
		 

		 form_settings mNewWithDetails= ((form_settings) event.getObject());
		 form_settings mNew=mNewWithDetails;
	        form_settingsFacade.addform_settings(mNew);
	        PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Success',\r\n" + 
					"			text: 'Your data has been saved.',\r\n" + 
					"			type: 'success'\r\n" + 
					"		});");
	       
      
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
	
	
	
	
	public void addContainerToInvoice_requester() {
		List<car> selectedCarsToBeAddedInInvoice= carFacade.getAllForNormalUserAndContainer(userForInvoice.getId(),selectedContainerToBeAddedInInvoice);
		for(int i=0;i<selectedCarsToBeAddedInInvoice.size();i++) {
			car carObject = selectedCarsToBeAddedInInvoice.get(i);
			if(!carsForInvoice.contains(carObject)) {
				if(carObject.isPayed_done() & !carObject.isInvoice_for_normalUser()) {

				carsForInvoice.add(carObject);
				}
			}
		}

		try {

			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice_main/invoiceAdd.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addCarToInvoice_requester() {
		car selectedCarToBeAddedInInvoice= carFacade.getById(selectedCarIdToBeAddedInInvoice);
		System.out.println("CarNew");
		if(selectedCarToBeAddedInInvoice.isPayed_done() & !selectedCarToBeAddedInInvoice.isInvoice_for_normalUser()) {

			System.out.println("Not");
		carsForInvoice.add(selectedCarToBeAddedInInvoice);
		
		

		try {

			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice_main/invoiceAdd.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {

			System.out.println("Not all Dakrory");
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Check this ',\r\n" + 
					"			text: 'Car Cannot Be added because it is not be payed or is already has invoice',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
		}
	}
		

	
	public void addCarToInvoice() {
		car selectedCarToBeAddedInInvoice= carFacade.getById(selectedCarIdToBeAddedInInvoice);
		if(selectedCarToBeAddedInInvoice.isPayed_done() & !selectedCarToBeAddedInInvoice.isInvoice_for_normalUser()) {

		carsForInvoice.add(selectedCarToBeAddedInInvoice);

		try {

			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/invoice/invoiceAdd.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Check this ',\r\n" + 
					"			text: 'Car Cannot Be added because it is not be payed or is already has invoice',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
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
	
	public void deleteCarInInvoice_requester(int indexInList) {
		carsForInvoice.remove(indexInList);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice_main/invoiceAdd.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void saveInvoiceData_requester() {

		invoiceData.setUserIdCustomer(userForInvoice);
		invoiceData.setUserIdIssuer(loginBean.getTheUserOfThisAccount());
		invoiceData.setDate(Calendar.getInstance());
		
		invoiceFacade.addinvoice(invoiceData);
		carFeesInvoice=(float) 0;
		for(int i=0;i<carsForInvoice.size();i++) {
			invoiceCar carinvoice=new invoiceCar();
			car carObject = carsForInvoice.get(i);
			carObject.setInvoice_for_normalUser(true);
			try {
				carFacade.addcar(carObject);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Error',\r\n" + 
						"			text: '"+e.getMessage()+".',\r\n" + 
						"			type: 'error'\r\n" + 
						"		});");
			}
			carinvoice.setCarId(carsForInvoice.get(i));

			addCarActionForLog(carsForInvoice.get(i),"Invoice Generated to this car");
			carinvoice.setInvoiceId(invoiceData);
			
			invoiceCarFacade.addinvoiceCar(carinvoice);
			car selectedCar = carsForInvoice.get(i);
			float totalForCar= selectedCar.getTotal_amount_for_this_car();
			
			carFeesInvoice+=totalForCar;
		}
		
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice_main/invoice.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void showTheInvoice_dashboard(int id) {
//		System.out.println("Ahmed: Data");
		invoiceData = invoiceFacade.getById(id);
		List<invoiceCar> cars_for_invoice = invoiceCarFacade.getAllByinvoiceId(invoiceData.getId());

		carsForInvoice =new ArrayList<car>();
		for(int i=0;i<cars_for_invoice.size();i++) {
			
				carsForInvoice.add(cars_for_invoice.get(i).getCarId());
			
		}
		 
//		System.out.println("Ahmed: Data2");

		
		
		carFeesInvoice=(float) 0;
		for(int i=0;i<carsForInvoice.size();i++) {
			invoiceCar carinvoice=new invoiceCar();
			carinvoice.setCarId(carsForInvoice.get(i));
			carinvoice.setInvoiceId(invoiceData);
			
			car selectedCar = carsForInvoice.get(i);
			float totalForCar= selectedCar.getTotal_amount_for_this_car();
			
			carFeesInvoice+=totalForCar;
		}
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice_main/invoice.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void showTheInvoice_user(int id) {
//		System.out.println("Ahmed: Data");
		invoiceData = invoiceFacade.getById(id);
		List<invoiceCar> cars_for_invoice = invoiceCarFacade.getAllByinvoiceId(invoiceData.getId());

		carsForInvoice =new ArrayList<car>();
		for(int i=0;i<cars_for_invoice.size();i++) {
			
				carsForInvoice.add(cars_for_invoice.get(i).getCarId());
			
		}
		 
//		System.out.println("Ahmed: Data2");

		
		
		carFeesInvoice=(float) 0;
		for(int i=0;i<carsForInvoice.size();i++) {
			invoiceCar carinvoice=new invoiceCar();
			carinvoice.setCarId(carsForInvoice.get(i));
			carinvoice.setInvoiceId(invoiceData);
			
			car selectedCar = carsForInvoice.get(i);
			float totalForCar= selectedCar.getTotal_amount_for_this_car();

			carFeesInvoice+=totalForCar;
		}
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice_main/invoice_user.jsf?faces-redirect=true");
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
			addCarActionForLog(carsForInvoice.get(i),"Invoice Generated to this car");
			carinvoice.setInvoiceId(invoiceData);
			
			invoiceCarFacade.addinvoiceCar(carinvoice);
			car selectedCar = carsForInvoice.get(i);
			float totalForCar= selectedCar.getTotal_amount_for_this_car();
			
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
	
	
	
	public void invoiceDetailsFor_requester(int idBuyer) {
		invoiceData=new invoice();
		userForInvoice = loginBean.getUserDataFacede().getById(idBuyer);

		containers_for_user = carFacade.getAllForNormalUserGroupBy(idBuyer);

		System.out.println("AhmedDAkrory____________");
		System.out.println(String.valueOf(containers_for_user.size()));
		
		carsForthisAccount = new ArrayList<car>();
		
		carsForthisAccount = carFacade.getAllForNormalUser(idBuyer);		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice_main/invoiceAdd.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public void invoiceDetails(int idShipper) {

		invoiceData=new invoice();
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
		


		loginBean.thisAccountMoneyBox = new moneybox();
		loginBean.thisAccountMoneyBox.setActive(true);
		loginBean.thisAccountMoneyBox.setDepositedMoney((float) 0.0);
		loginBean.thisAccountMoneyBox.setUserId(userNew);
		loginBean.thisAccountMoneyBox.setTotalUsed((float) 0.0);
		loginBean.moneyboxDataFacede.addmoneybox(loginBean.thisAccountMoneyBox);
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

	
	
	
	public log_infoAppServiceImpl getLog_infoFacade() {
		return log_infoFacade;
	}


	public void setLog_infoFacade(log_infoAppServiceImpl log_infoFacade) {
		this.log_infoFacade = log_infoFacade;
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


	public form_settingsAppServiceImpl getForm_settingsFacade() {
		return form_settingsFacade;
	}


	public void setForm_settingsFacade(form_settingsAppServiceImpl form_settingsFacade) {
		this.form_settingsFacade = form_settingsFacade;
	}


	public List<form_settings> getAllform_settings() {
		return allform_settings;
	}


	public void setAllform_settings(List<form_settings> allform_settings) {
		this.allform_settings = allform_settings;
	}


	public form_settings getSelectedformSetting() {
		return selectedformSetting;
	}


	public void setSelectedformSetting(form_settings selectedformSetting) {
		this.selectedformSetting = selectedformSetting;
	}


	public user getUserForInvoice() {
		return userForInvoice;
	}


	public void setUserForInvoice(user userForInvoice) {
		this.userForInvoice = userForInvoice;
	}


	public List<car> getCarsForContainer() {
		return carsForContainer;
	}


	public void setCarsForContainer(List<car> carsForContainer) {
		this.carsForContainer = carsForContainer;
	}


	public List<car> getContainers_for_user() {
		return containers_for_user;
	}


	public void setContainers_for_user(List<car> containers_for_user) {
		this.containers_for_user = containers_for_user;
	}


	public String getSelectedContainerToBeAddedInInvoice() {
		return selectedContainerToBeAddedInInvoice;
	}


	public void setSelectedContainerToBeAddedInInvoice(String selectedContainerToBeAddedInInvoice) {
		this.selectedContainerToBeAddedInInvoice = selectedContainerToBeAddedInInvoice;
	}

	

	
	
}
