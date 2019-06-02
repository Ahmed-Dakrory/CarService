package main.com.carService.Beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import main.com.carService.loginNeeds.user;
import main.com.carService.shipper.shipper;
import main.com.carService.shipper.shipperAppServiceImpl;
import main.com.carService.vendor.vendor;
import main.com.carService.vendor.vendorAppServiceImpl;
import main.com.carService.car.car;
import main.com.carService.car.carAppServiceImpl;
import main.com.carService.consignee.consignee;
import main.com.carService.consignee.consigneeAppServiceImpl;
import main.com.carService.customer.customer;
import main.com.carService.customer.customerAppServiceImpl;


@ManagedBean(name = "carBean")
@SessionScoped
public class carBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440236767354416L;


	/**
	 * 
	 */
	
	
 
	

	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	 


	@ManagedProperty(value = "#{consigneeFacadeImpl}")
	private consigneeAppServiceImpl consigneeFacade;

	@ManagedProperty(value = "#{shipperFacadeImpl}")
	private shipperAppServiceImpl shipperFacade;
	

	@ManagedProperty(value = "#{carFacadeImpl}")
	private carAppServiceImpl carFacade;
	

	@ManagedProperty(value = "#{vendorFacadeImpl}")
	private vendorAppServiceImpl vendorFacade;
	

	@ManagedProperty(value = "#{customerFacadeImpl}")
	private customerAppServiceImpl customerFacade;
	
	private List<car> allCars;
	private List<consignee> allconsignees;
	private List<shipper> allshipper;
	private List<vendor> allvendor;
	private List<customer> allcustomer;
	
	private car selectedCar;
	
	private car addNewCar;
	
	
	

	private  String cargoRecievedDate;
	private  String titleRecievedDate;
	private  String dvlDate;
	private  String stRecievedDate;
	private  String etdDate;
	private  String etaDate;
	private  String storageStartDate;
	private  String storageEndDate;

	private int shipperSelectedId;
	private int vendorSelectedId;
	private int customerSelectedId;
	
	
	@PostConstruct
	public void init() {
		
		refresh();
		
		
		
	}
	
	public void releaseVariablesForMain() {
		cargoRecievedDate="";
		titleRecievedDate="";
		dvlDate="";
		stRecievedDate="";
		etdDate="";
		etaDate="";
		storageStartDate="";
		storageEndDate="";
		
		addNewCar=new car();
		user userId=new user();
		shipper shipperID=new shipper();

		addNewCar.setMainId(userId);
		addNewCar.setShipperId(shipperID);
	}
	public void releaseVariablesForShipper() {
		cargoRecievedDate="";
		titleRecievedDate="";
		dvlDate="";
		stRecievedDate="";
		etdDate="";
		etaDate="";
		storageStartDate="";
		storageEndDate="";
		
		addNewCar=new car();
		consignee consigneeId=new consignee();
		user userId=new user();
		shipper shipperID=new shipper();
		vendor vendorId=new vendor();
		customer customerId=new customer();

		addNewCar.setMainId(userId);
		addNewCar.setShipperId(shipperID);
		addNewCar.setVendorId(vendorId);
		addNewCar.setCustomerId(customerId);
		addNewCar.setConsigneeId(consigneeId);
	}
	public void refresh(){
		
		
		
		int role=loginBean.getTheUserOfThisAccount().getRole();
		if(role==user.ROLE_MAIN) {
			releaseVariablesForMain();
			addNewCar.setMainId(loginBean.getTheUserOfThisAccount());
			allshipper = shipperFacade.getAllByParentId(loginBean.getTheUserOfThisAccount().getId());
			allconsignees=consigneeFacade.getAllByParentOfParentId(loginBean.getTheUserOfThisAccount().getId());
			
		}else if(role==user.ROLE_SHIPPER) {
			releaseVariablesForShipper();
			shipper shipperOfThisAccount=shipperFacade.getByUserId(loginBean.getTheUserOfThisAccount().getId());
			addNewCar.setShipperId(shipperOfThisAccount);
			addNewCar.setMainId(shipperOfThisAccount.getParentId());
			allvendor=vendorFacade.getAllByParentId(shipperOfThisAccount.getId());
			allconsignees=consigneeFacade.getAllByParentId(shipperOfThisAccount.getId());
		}
		
	}
	
	public void goToAddNewcarShipper() {
		refresh();
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/car/vitView.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void selectCar(int idcar) {
		selectedCar=carFacade.getById(idcar);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/car/vitViewEdit.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void saveNewCarDataMain() {
		addNewCar.setCargoRecieved(setCalendarFromString(cargoRecievedDate));
		addNewCar.setTitleRecieved(setCalendarFromString(titleRecievedDate));
		addNewCar.setDvl(setCalendarFromString(dvlDate));
		addNewCar.setStRecieved(setCalendarFromString(stRecievedDate));
		addNewCar.setEtd(setCalendarFromString(etdDate));
		addNewCar.setEta(setCalendarFromString(etaDate));
		addNewCar.setStorageStartDate(setCalendarFromString(storageStartDate));
		addNewCar.setStorageEndDate(setCalendarFromString(storageEndDate));
		
		//Set the selected shipper
		addNewCar.setShipperId(shipperFacade.getById(shipperSelectedId));
		
		boolean isValid=checkValidForCar(addNewCar);
		if(isValid) {
			boolean checkCar = checkCarIsExist(addNewCar.getUuid());
			if(checkCar) {
		
		carFacade.addcar(addNewCar);
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your car has been added.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/admin/vehicleList.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}else {
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Check this ',\r\n" + 
						"			text: 'This car is already Registered',\r\n" + 
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

	private Calendar setCalendarFromString(String cargoRecievedDate2) {

		Calendar cal = null;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-dd-MM HH:mm:ss"); 
		try {
			if(!cargoRecievedDate2.equals("")) {
				cal=Calendar.getInstance();
				Date date=formatter.parse(cargoRecievedDate2);
				cal.setTime(date);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return cal;
	}

	private boolean checkCarIsExist(String vinId) {
		// TODO Auto-generated method stub
		
		car theCar=carFacade.getByVin(vinId);
		if(theCar!=null) {
			return false;
		}
		return true;
	}

	private boolean checkValidForCar(car addNewCar) {
		// TODO Auto-generated method stub
		
		if(addNewCar.getUuid().equals("")||addNewCar.getUuid()==null) {
			return false;
		}
	
		
		
		return true;
	}

	public void cancel() {
		System.out.println("Cancel");
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/admin/vehicleList.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateDataMain() {

		addNewCar.setCargoRecieved(setCalendarFromString(cargoRecievedDate));
		addNewCar.setTitleRecieved(setCalendarFromString(titleRecievedDate));
		addNewCar.setDvl(setCalendarFromString(dvlDate));
		addNewCar.setStRecieved(setCalendarFromString(stRecievedDate));
		addNewCar.setEtd(setCalendarFromString(etdDate));
		addNewCar.setEta(setCalendarFromString(etaDate));
		addNewCar.setStorageStartDate(setCalendarFromString(storageStartDate));
		addNewCar.setStorageEndDate(setCalendarFromString(storageEndDate));
		
		//Set the selected shipper
		addNewCar.setShipperId(shipperFacade.getById(shipperSelectedId));
		
		boolean isValid=checkValidForCar(selectedCar);
		if(isValid) {
		carFacade.addcar(selectedCar);
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

	
	public List<consignee> getAllconsignees() {
		return allconsignees;
	}

	public void setAllconsignees(List<consignee> allconsignees) {
		this.allconsignees = allconsignees;
	}

	
	public consigneeAppServiceImpl getconsigneeFacade() {
		return consigneeFacade;
	}

	public void setconsigneeFacade(consigneeAppServiceImpl consigneeFacade) {
		this.consigneeFacade = consigneeFacade;
	}

	public consigneeAppServiceImpl getConsigneeFacade() {
		return consigneeFacade;
	}

	public void setConsigneeFacade(consigneeAppServiceImpl consigneeFacade) {
		this.consigneeFacade = consigneeFacade;
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

	public List<car> getAllCars() {
		return allCars;
	}

	public void setAllCars(List<car> allCars) {
		this.allCars = allCars;
	}

	public car getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(car selectedCar) {
		this.selectedCar = selectedCar;
	}

	public car getAddNewCar() {
		return addNewCar;
	}

	public void setAddNewCar(car addNewCar) {
		this.addNewCar = addNewCar;
	}

	public vendorAppServiceImpl getVendorFacade() {
		return vendorFacade;
	}

	public void setVendorFacade(vendorAppServiceImpl vendorFacade) {
		this.vendorFacade = vendorFacade;
	}

	public customerAppServiceImpl getCustomerFacade() {
		return customerFacade;
	}

	public void setCustomerFacade(customerAppServiceImpl customerFacade) {
		this.customerFacade = customerFacade;
	}

	public List<shipper> getAllshipper() {
		return allshipper;
	}

	public void setAllshipper(List<shipper> allshipper) {
		this.allshipper = allshipper;
	}

	public List<vendor> getAllvendor() {
		return allvendor;
	}

	public void setAllvendor(List<vendor> allvendor) {
		this.allvendor = allvendor;
	}

	public List<customer> getAllcustomer() {
		return allcustomer;
	}

	public void setAllcustomer(List<customer> allcustomer) {
		this.allcustomer = allcustomer;
	}

	public String getCargoRecievedDate() {
		return cargoRecievedDate;
	}

	public void setCargoRecievedDate(String cargoRecievedDate) {
		this.cargoRecievedDate = cargoRecievedDate;
	}

	public String getTitleRecievedDate() {
		return titleRecievedDate;
	}

	public void setTitleRecievedDate(String titleRecievedDate) {
		this.titleRecievedDate = titleRecievedDate;
	}

	public String getDvlDate() {
		return dvlDate;
	}

	public void setDvlDate(String dvlDate) {
		this.dvlDate = dvlDate;
	}

	public String getStRecievedDate() {
		return stRecievedDate;
	}

	public void setStRecievedDate(String stRecievedDate) {
		this.stRecievedDate = stRecievedDate;
	}

	public String getEtdDate() {
		return etdDate;
	}

	public void setEtdDate(String etdDate) {
		this.etdDate = etdDate;
	}

	public String getEtaDate() {
		return etaDate;
	}

	public void setEtaDate(String etaDate) {
		this.etaDate = etaDate;
	}

	public String getStorageStartDate() {
		return storageStartDate;
	}

	public void setStorageStartDate(String storageStartDate) {
		this.storageStartDate = storageStartDate;
	}

	public String getStorageEndDate() {
		return storageEndDate;
	}

	public void setStorageEndDate(String storageEndDate) {
		this.storageEndDate = storageEndDate;
	}

	public int getShipperSelectedId() {
		return shipperSelectedId;
	}

	public void setShipperSelectedId(int shipperSelectedId) {
		this.shipperSelectedId = shipperSelectedId;
	}

	public int getVendorSelectedId() {
		return vendorSelectedId;
	}

	public void setVendorSelectedId(int vendorSelectedId) {
		this.vendorSelectedId = vendorSelectedId;
	}

	public int getCustomerSelectedId() {
		return customerSelectedId;
	}

	public void setCustomerSelectedId(int customerSelectedId) {
		this.customerSelectedId = customerSelectedId;
	}
	

	
	
	
	
}
