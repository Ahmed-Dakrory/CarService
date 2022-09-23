package main.com.carService.Beans;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

import helpers.retrofit.mainFiles.APIClient;
import helpers.retrofit.mainFiles.APIInterface;
import helpers.retrofit.mainFiles.OrderOutDetails;
import main.com.carService.car.car;
import main.com.carService.car.carAppServiceImpl;
import main.com.carService.carImage.carimage;
import main.com.carService.carImage.carimageAppServiceImpl;
import main.com.carService.consignee.consignee;
import main.com.carService.consignee.consigneeAppServiceImpl;
import main.com.carService.costCalc.transportfee;
import main.com.carService.costCalc.transportfeeAppServiceImpl;
import main.com.carService.customer.customer;
import main.com.carService.customer.customerAppServiceImpl;
import main.com.carService.form_settings.form_settingsAppServiceImpl;
import main.com.carService.invoiceCars.invoiceCar;
import main.com.carService.invoiceCars.invoiceCarAppServiceImpl;
import main.com.carService.loginNeeds.user;
import main.com.carService.mainTwo.mainTwo;
import main.com.carService.mainTwo.mainTwoAppServiceImpl;
import main.com.carService.moneyBox.moneybox;
import main.com.carService.moneyBox.moneyboxAppServiceImpl;
import main.com.carService.moneyTransactionDetails.moneybox_transaction_details;
import main.com.carService.moneyTransactionDetails.moneybox_transaction_detailsAppServiceImpl;
import main.com.carService.shipper.shipper;
import main.com.carService.shipper.shipperAppServiceImpl;
import main.com.carService.vendor.vendor;
import main.com.carService.vendor.vendorAppServiceImpl;
import retrofit2.Call;

@ManagedBean(name = "normalUserBean")
@SessionScoped
public class normalUserBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440236767354796L;


	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	 


	@ManagedProperty(value = "#{consigneeFacadeImpl}")
	private consigneeAppServiceImpl consigneeFacade;

	@ManagedProperty(value = "#{shipperFacadeImpl}")
	private shipperAppServiceImpl shipperFacade;
	

	@ManagedProperty(value = "#{mainTwoFacadeImpl}")
	private mainTwoAppServiceImpl mainTwoFacade;
	

	@ManagedProperty(value = "#{invoiceCarFacadeImpl}")
	private invoiceCarAppServiceImpl invoiceCarFacade;
	
	

	@ManagedProperty(value = "#{carFacadeImpl}")
	private carAppServiceImpl carFacade;
	

	@ManagedProperty(value = "#{vendorFacadeImpl}")
	private vendorAppServiceImpl vendorFacade;

	@ManagedProperty(value = "#{transportfeeFacadeImpl}")
	private transportfeeAppServiceImpl transportfeeFacade;

	@ManagedProperty(value = "#{customerFacadeImpl}")
	private customerAppServiceImpl customerFacade;
	
	@ManagedProperty(value = "#{carimageFacadeImpl}")
	private carimageAppServiceImpl carimageFacade;
	

	@ManagedProperty(value = "#{form_settingsFacadeImpl}")
	private form_settingsAppServiceImpl form_settingsFacade;

	@ManagedProperty(value = "#{moneybox_transaction_detailsFacadeImpl}")
	private moneybox_transaction_detailsAppServiceImpl moneybox_transaction_detailsFacade;
	

	@ManagedProperty(value = "#{moneyboxFacadeImpl}")
	private moneyboxAppServiceImpl moneyboxFacade;
	
	
	private moneybox car_user_normalmoneybox;
	private List<moneybox_transaction_details> allmoneybox_transaction_details;
	private List<moneybox_transaction_details> summery_allmoneybox_transaction_details;
	

	private List<transportfee> allLocation;
	private List<transportfee> allCity;
	private List<transportfee> allState;
	private Map<Integer,String> allLanding;
	
	

	private float amountRemainToPayForAllCars=0;
	private transportfee selectedTansportFees;
	
	
	private int selectedCarState=0; 
	private List<car> allCars;
	private List<consignee> allconsignees;
	private List<mainTwo> allMainTwo;
	private List<shipper> allshipper;
	private List<vendor> allvendor;
	private List<customer> allcustomer;
	
	private car selectedCar;
	private car addNewCar;
	
////////////////////////////////////////////////////////
	private int typeOfTransaction=-1;
	private int carIdOfTransaction=-1;
	private String wireTransferOfTransaction="";
	private String containerOfTransaction="";
	private int userOfTransaction = -1;

	private List<car> allCarsAsUser;
	private List<car> allCarsAsUserContainer;
	///////////////////////////////////////////////////////////////////
	private List<String> carStates;
	

	private  String cargoRecievedDate;
	private  String dvlDate;
	private  String stRecievedDate;
	private  String etdDate;
	private  String etaDate;
	private  String storageStartDate;
	private  String storageEndDate;

	private int mainTwoSelectedId;
	private int shipperSelectedId;
	private int vendorSelectedId;
	private int customerSelectedId;

	private List<String> images;
	private List<String> docs;

	private Map<Integer, String> distinationMap;
	private Map<Integer, String> origineMap;
	
	private int consigneeId;
	private consignee selectedConsignee;

	private  Integer titleRecievedSelected;
	private boolean progress=false;
	
	private List<String> images_deleted;
	private List<String> docs_deleted;

	private int cars_Ship_UnderReview = 0;
	private int cars_PayAndShip_UnderReview=0;
	

	private int cars_Ship_Rejected = 0;
	private int cars_PayAndShip_Rejected=0;
	

	private int cars_Ship_DeliveredByUs = 0;
	private int cars_PayAndShip_PayedByCustomer=0;
	

	private int cars_Ship_InShipping = 0;
	private int cars_PayAndShip_InShipping=0;
	

	private int cars_Ship_DeliveredByCustomer = 0;
	private int cars_PayAndShip_DeliveredByCustomer=0;
	

	private int totalNumberOfShippingOnly = 0;
	private int totalNumberOfShippingAndBuy=0;
	
	
	private float totalPrice=0;
	
	private List<user> allUsers;
	private user selectedUser;
	
	

	private double onlineFees;
	private double GateFees=0;
	private double seaFees;
	private double landFees;
	private double totalFees;
	

	private double copartFees;
	private double ourFees;
	
	

	private float dollarToDinar = 0;
	
	

	public static double onlineBid[]= {	39,  39,  39,  39,  49,  49,  49,  49,  49,  49,
			69,  69,  69,  69,  79,  79,  79,  79,  89,  89,
			89,  89,  99,  99,  99,  99, 119, 129, 129,   0,
			 0,   0,   0,   0,	 0};



	public static int getLevel(double price) {
	
	int level = 0;
	if(price >= 1.00 && price <= 349.99) {
	level = 0;
	}else if(price >= 350.00 && price <= 399.99) {
	level = 1;
	}else if(price >= 400.00 && price <= 449.99) {
	level = 2;
	}else if(price >= 450.00 && price <= 499.99) {
	level = 3;
	}else if(price >= 500.00 && price <= 549.99) {
	level = 4;
	}else if(price >= 550.00 && price <= 599.99) {
	level = 5;
	}else if(price >= 600.00 && price <= 699.99) {
	level = 6;
	}else if(price >= 700.00 && price <= 799.99) {
	level = 7;
	}else if(price >= 800.00 && price <= 899.99) {
	level = 8;
	}else if(price >= 900.00 && price <= 999.99) {
	level = 9;
	}else if(price >= 1000.00 && price <= 1199.99) {
	level = 10;
	}else if(price >= 1200.00 && price <= 1299.99) {
	level = 11;
	}else if(price >= 1300.00 && price <= 1399.99) {
	level = 12;
	}else if(price >= 1400.00 && price <= 1499.99) {
	level = 13;
	}else if(price >= 1500.00 && price <= 1599.99) {
	level = 14;
	}else if(price >= 1600.00 && price <= 1699.99) {
	level = 15;
	}else if(price >= 1700.00 && price <= 1799.99) {
	level = 16;
	}else if(price >= 1800.00 && price <= 1999.99) {
	level = 17;
	}else if(price >= 2000.00 && price <= 2399.99) {
	level = 18;
	}else if(price >= 2400.00 && price <= 2499.99) {
	level = 19;
	}else if(price >= 2500.00 && price <= 2999.99) {
	level = 20;
	}else if(price >= 3000.00 && price <= 3499.99) {
	level = 21;
	}else if(price >= 3500.00 && price <= 3999.99) {
	level = 22;
	}else if(price >= 4000.00 && price <= 4499.99) {
	level = 23;
	}else if(price >= 4500.00 && price <= 4999.99) {
	level = 24;
	}else if(price >= 5000.00 && price <= 5999.99) {
	level = 25;
	}else if(price >= 6000.00 && price <= 7499.99) {
	level = 26;
	}else if(price >= 7500.00 && price <= 9999.99) {
	level = 27;
	}else if(price >= 10000.00 && price <= 14999.99) {
	level = 28;
	}else if(price >= 15000.00 && price <= 19999.99) {
	level = 29;
	}else if(price >= 20000.00 && price <= 24999.99) {
	level = 30;
	}else if(price >= 25000.00 && price <= 29999.99) {
	level = 31;
	}else if(price >= 30000.00 && price <= 34999.99) {
	level = 32;
	}else if(price >= 35000.00 && price <= 10000000.00) {
	level = 33;
	}else if(price == -1) {
	level = 34;
	}

	return level;
	}


	
	@PostConstruct
	public void init() {
		distinationMap=new LinkedHashMap<Integer,String>();
		origineMap=new LinkedHashMap<Integer,String>();
		
		addNewCar = new car();
		carStates = new ArrayList<String>();
		for(int i=0;i<car.stateString.length;i++) {
			carStates.add(car.stateString[i]);
		}
		
		
		
		refresh();
		
		
		fillDashboard();
		

		selectedCarState=0;
	}
	
	
	public void fillDashboard() {
		// TODO Auto-generated method stub
		
		
		summery_allmoneybox_transaction_details = new ArrayList<moneybox_transaction_details>();
		List<moneybox_transaction_details> allDetails = moneybox_transaction_detailsFacade.getAllByUserMoneyBoxId(loginBean.thisAccountMoneyBox.getId());
		int len=0;
		if(allDetails.size()>=5) {
			len=5;
		}else {
			len = allDetails.size();
		}
		for(int i=0;i<len;i++) {
			
				
			summery_allmoneybox_transaction_details.add(allDetails.get(i));
				
		
			
		}
		
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
		
	}


	public void reload_howManyCarAmount_remain() {
			if(loginBean.isLoggedIn()) {
				
				
				amountRemainToPayForAllCars=0;
				List<car> allCars = carFacade.getAllForNormalUser(loginBean.getTheUserOfThisAccount().getId());
				if(allCars!=null) {
				for(int i=0;i<allCars.size();i++) {
					if(!allCars.get(i).isPayed_done()) {
						amountRemainToPayForAllCars = amountRemainToPayForAllCars + (allCars.get(i).getTotal_amount_for_this_car()-allCars.get(i).getAmount_of_payment());
					}
				}
				}
						
			}
		

	}

	public void handleAllUsersForAdmin() {
		
		allUsers = loginBean.getUserDataFacede().getAll();
	}
	
	
	
	public void selectUser(int idUser) {
		selectedUser=loginBean.getUserDataFacede().getById(idUser);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/userEdit.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	

	private boolean checkValidForUser(user addNewshipper2) {
		// TODO Auto-generated method stub
		if(addNewshipper2.getCompany().equals("")||addNewshipper2.getCompany()==null) {
			return false;
		}
		
		if(addNewshipper2.getEmail().equals("")||addNewshipper2.getEmail()==null) {
			return false;
		}
		
		if(addNewshipper2.getFirstName().equals("")||addNewshipper2.getFirstName()==null) {
			return false;
		}
		
		if(addNewshipper2.getLastName().equals("")||addNewshipper2.getLastName()==null) {
			return false;
		}
		
		return true;
	}

	public void cancelEditUser() {
		System.out.println("Cancel");
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/userList.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateData() {

		boolean isValid=checkValidForUser(selectedUser);
		if(isValid) {
		loginBean.getUserDataFacede().adduser(selectedUser);
		

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
	
	public void refreshProfileData() {

		user userNewId=loginBean.getTheUserOfThisAccount();
		if(carFacade.getAllBytypeOfOrderForNormalUser(userNewId.getId(), car.TYPE_SHIPPING)!=null) {
		totalNumberOfShippingOnly = carFacade.getAllBytypeOfOrderForNormalUser(userNewId.getId(), car.TYPE_SHIPPING).size();
		}else {
			totalNumberOfShippingOnly =0;
		}
		
		if(carFacade.getAllBytypeOfOrderForNormalUser(userNewId.getId(), car.TYPE_BUY_SHIPPING)!=null) {
		totalNumberOfShippingAndBuy = carFacade.getAllBytypeOfOrderForNormalUser(userNewId.getId(), car.TYPE_BUY_SHIPPING).size();
		}else {
			totalNumberOfShippingAndBuy=0;
		}
		
		if(carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_AddedByCustomer_REVISE, car.TYPE_SHIPPING)!=null) {
			cars_Ship_UnderReview =  carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_AddedByCustomer_REVISE, car.TYPE_SHIPPING).size();
		}else {
			cars_Ship_UnderReview=0;
		}
		
		if(carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_AddedByCustomer_REVISE, car.TYPE_BUY_SHIPPING)!=null) {
		cars_PayAndShip_UnderReview=carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_AddedByCustomer_REVISE, car.TYPE_BUY_SHIPPING).size();
		}else {
			cars_PayAndShip_UnderReview=0;
		}
		
		
		if(carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_Rejected, car.TYPE_SHIPPING)!=null) {
		cars_Ship_Rejected =  carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_Rejected, car.TYPE_SHIPPING).size();
		}else{
			cars_Ship_Rejected=0;
		}
		
		
		if(carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_Rejected, car.TYPE_BUY_SHIPPING)!=null) {
		cars_PayAndShip_Rejected=carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_Rejected, car.TYPE_BUY_SHIPPING).size();
		}else {
			cars_PayAndShip_Rejected=0;
		}

		if(carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_DeliveredByUs, car.TYPE_SHIPPING)!=null) {
		cars_Ship_DeliveredByUs =  carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_DeliveredByUs, car.TYPE_SHIPPING).size();
		}else {
			cars_Ship_DeliveredByUs=0;
		}
		
		if(carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_PayedByCustomer, car.TYPE_BUY_SHIPPING)!=null) {
		cars_PayAndShip_PayedByCustomer=carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_PayedByCustomer, car.TYPE_BUY_SHIPPING).size();
		}else {
			cars_PayAndShip_PayedByCustomer=0;
		}
		
		
		
		
		if(carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_DeliveredByCustomer, car.TYPE_SHIPPING)!=null) {
		cars_Ship_DeliveredByCustomer =  carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_DeliveredByCustomer, car.TYPE_SHIPPING).size();
		}else {
			cars_Ship_DeliveredByCustomer=0;
		}
		
		
		if(carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_DeliveredByCustomer, car.TYPE_BUY_SHIPPING)!=null) {
		cars_PayAndShip_DeliveredByCustomer=carFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  car.STATE_DeliveredByCustomer, car.TYPE_BUY_SHIPPING).size();
		}else {
			cars_PayAndShip_DeliveredByCustomer=0;
		}
		
		
		
		if(carFacade.getAllBytypeOfOrderAndShippingStateForNormalUser(userNewId.getId(), car.TYPE_SHIPPING)!=null) {
			cars_Ship_InShipping =  carFacade.getAllBytypeOfOrderAndShippingStateForNormalUser(userNewId.getId(), car.TYPE_SHIPPING).size();
		}else {
			cars_Ship_InShipping=0;
		}
		
		
		if(carFacade.getAllBytypeOfOrderAndShippingStateForNormalUser(userNewId.getId(), car.TYPE_BUY_SHIPPING)!=null) {
			cars_PayAndShip_InShipping=carFacade.getAllBytypeOfOrderAndShippingStateForNormalUser(userNewId.getId(), car.TYPE_BUY_SHIPPING).size();
		}else {
			cars_PayAndShip_InShipping=0;
		}

	
	}
	

public void calcValueOfTotalFeesCarSelected() {
		

		updateAllFees();
		selectedTansportFees=transportfeeFacade.getWithSpecs(addNewCar.getSelectedLocation(), addNewCar.getSelectedCity(), addNewCar.getSelectedState());

		if(selectedTansportFees!=null) {
			double price=addNewCar.getOrderPrice();
			int level=getLevel(price);
			onlineFees=onlineBid[level];
			seaFees=0;
			if(addNewCar.getCarSize().equalsIgnoreCase("0")) {
				seaFees=selectedTansportFees.getLowCost();
			}else {
				seaFees=selectedTansportFees.getHighCost();
			}
			
			landFees=0;
			
			
			
			
			if(addNewCar.getOrigin()==1) {
				landFees=selectedTansportFees.getNjPortCost();
			}else if(addNewCar.getOrigin()==39) {
				landFees=selectedTansportFees.getGaPortCost();
			}else if(addNewCar.getOrigin()==381) {
				landFees=selectedTansportFees.getTxPortCost();
			}else if(addNewCar.getOrigin()==117) {
				landFees=selectedTansportFees.getCaPortCost();
			}else if(addNewCar.getOrigin()==391) {
				landFees=selectedTansportFees.getIndianaPortCost();
			}else if(addNewCar.getOrigin()==371) {
				landFees=selectedTansportFees.getFloridaPortCost();
			}else if(addNewCar.getOrigin()==410) {
				landFees=selectedTansportFees.getPaltimorPortCost();
			}

			
			totalFees=copartFees+GateFees+seaFees+landFees+ourFees;
			

			
		}
		
		
		
	}





public void calcValueOfTotalFeesCarSelected_after() {
		

		updateAllFees();
		selectedTansportFees=transportfeeFacade.getWithSpecs(addNewCar.getSelectedLocation(), addNewCar.getSelectedCity(), addNewCar.getSelectedState());

		if(selectedTansportFees!=null) {
			double price=addNewCar.getOrderPrice();
			int level=getLevel(price);
			onlineFees=onlineBid[level];
			seaFees=0;
			if(addNewCar.getCarSize().equalsIgnoreCase("0")) {
				seaFees=selectedTansportFees.getLowCost();
			}else {
				seaFees=selectedTansportFees.getHighCost();
			}
			
			landFees=0;
			
			
			
			
			if(addNewCar.getOrigin()==1) {
				landFees=selectedTansportFees.getNjPortCost();
			}else if(addNewCar.getOrigin()==39) {
				landFees=selectedTansportFees.getGaPortCost();
			}else if(addNewCar.getOrigin()==381) {
				landFees=selectedTansportFees.getTxPortCost();
			}else if(addNewCar.getOrigin()==117) {
				landFees=selectedTansportFees.getCaPortCost();
			}else if(addNewCar.getOrigin()==391) {
				landFees=selectedTansportFees.getIndianaPortCost();
			}else if(addNewCar.getOrigin()==371) {
				landFees=selectedTansportFees.getFloridaPortCost();
			}else if(addNewCar.getOrigin()==410) {
				landFees=selectedTansportFees.getPaltimorPortCost();
			}

			totalFees=copartFees+GateFees+seaFees+landFees+addNewCar.getFees();
			

			
		}
		
		
		
	}



	public String getStringFromCalendar(Calendar calendar) {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-dd-MM HH:mm:ss"); 
		String returnedCalendarString="";
		
			if(calendar!=null) {
				returnedCalendarString=formatter.format(calendar.getTime());
			}
		return returnedCalendarString;
	}
	
	public void getCarWithId() {

		

		
		
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRequest();
		Integer id = null;
		try{
			id=Integer.parseInt(origRequest.getParameterValues("id")[0]);
			
		}catch (Exception e) {
			// TODO: handle exception
			
			System.out.println("Ahmed old: "+String.valueOf(e));
		}
				if(id!=null){
					
					images=new ArrayList<String>();
					docs=new ArrayList<String>();
					
					images_deleted=new ArrayList<String>();
					docs_deleted=new ArrayList<String>();
					
					cargoRecievedDate="";
					titleRecievedSelected=0;
					dvlDate="";
					stRecievedDate="";
					etdDate="";
					etaDate="";
					storageStartDate="";
					storageEndDate="";
					
					addNewCar=new car();
					
					addNewCar=carFacade.getById(id);
					
					calcValueOfTotalFeesCarSelected_after();
					List<carimage> imagesPics = carimageFacade.getAllByCarIdAndType(id, carimage.TYPE_PIC);
					if(imagesPics!=null) {
					for(int i=0;i<imagesPics.size();i++) {
						images.add(imagesPics.get(i).getUrl());

					}
					}
					
					List<carimage> docssPics = carimageFacade.getAllByCarIdAndType(id, carimage.TYPE_DOC);
					if(docssPics!=null) {
					for(int i=0;i<docssPics.size();i++) {
						docs.add(docssPics.get(i).getUrl());

					}
					}

					
					cargoRecievedDate=getStringFromCalendar(addNewCar.getCargoRecieved());
					dvlDate=getStringFromCalendar(addNewCar.getDvl());
					stRecievedDate=getStringFromCalendar(addNewCar.getStRecieved());
					etdDate=getStringFromCalendar(addNewCar.getEtd());

					etaDate=getStringFromCalendar(addNewCar.getEta());
					storageStartDate=getStringFromCalendar(addNewCar.getStorageStartDate());
					storageEndDate=getStringFromCalendar(addNewCar.getStorageEndDate());

					titleRecievedSelected=addNewCar.getTitleRecieved();


					FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
					
				}
		
	}
	
	
	
public String floatOfNumberWithPercent(float number) {
		
		
		return String.format("%.2f", number);
	}


public void refresh(){
		
	
try {
		
		dollarToDinar = Float.valueOf(form_settingsFacade.getById(1).getValue());

		}catch(NullPointerException exp) {
			
		}

		mainTwoSelectedId=-1;
		shipperSelectedId=-1;
		vendorSelectedId=-1;
		customerSelectedId=-1;
		
		progress=false;
		releaseVariablesForMain();
			
		
		
		allLocation = transportfeeFacade.getAllGroupsOfLocation();
		allCity=new ArrayList<transportfee>();
		allState=new ArrayList<transportfee>();
		
		addNewCar.setSelectedLocation(allLocation.get(0).getLocation());
		
		refreshCityList();
		addNewCar.setSelectedCity(allCity.get(0).getCity());		
		refreshStateList();
		
	}
	

public void updateAllFees() {
	int level = calcBean.getLevel(addNewCar.getOrderPrice());
	 if(addNewCar.getTypeOfOrder().equals(0)) {
		 copartFees=0; 
	 }else {
		 copartFees = calcBean.CalculateCopart(level, addNewCar.getOrderPrice());
		 
	 }
	 ourFees= 100;
	 System.out.println("Ahmed Dakrory Done");
	 
	 
}
public void theloaderFirst() {
	
	progress=true;
	refreshUpdatecCarData();

}


public void refreshUpdatecCarData() {

	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:ctl00_BodyHolder_txtModel");
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:ctl00_BodyHolder_txtMake");
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:ctl00_BodyHolder_txtYear");
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:ctl00_BodyHolder_txtAssemblyCountry");
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:ctl00_BodyHolder_txtBodyStyle");
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:ctl00_BodyHolder_txtEngineType");
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:ctl00_BodyHolder_txtEngineLiters");
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:description");
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:searchButton");
}

public void getCarWithVinNew() {
	if(!addNewCar.getUuid().equals("")) {
		
	APIInterface apiInterface = APIClient.getClient(addNewCar.getUuid()+"/").create(APIInterface.class);
	  Call<OrderOutDetails> call = apiInterface.performOrder();
        try {
        	OrderOutDetails car= call.execute().body();

        	addNewCar.setMake(car.Results.get(0).Make);
        	addNewCar.setModel(car.Results.get(0).Model);
        	addNewCar.setYear(car.Results.get(0).ModelYear);
        	addNewCar.setDescription(car.Results.get(0).Make+" "+car.Results.get(0).Model+" "+car.Results.get(0).ModelYear);
        	addNewCar.setAssemlyCountry(car.Results.get(0).PlantCountry);
        	addNewCar.setBodyStyle(car.Results.get(0).DriveType);
        	addNewCar.setEngineLiters(car.Results.get(0).DisplacementL);
        	addNewCar.setEngineType(car.Results.get(0).EngineConfiguration+"- "+car.Results.get(0).EngineCylinders+" Cylinders");


			progress=false;
			refreshUpdatecCarData();
          	  
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			progress=false;
			refreshUpdatecCarData();
		}
	}else {
		progress=false;
		refreshUpdatecCarData();
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Check this ',\r\n" + 
				"			text: 'Please enter the Vin number',\r\n" + 
				"			left:\"2%\"\r\n" + 
				"		});");
	}
}



public void filterCarBySelect() {
	filterCarBySelectFirstTime();
	
	try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/secured/normalUsers/car/vehicleList.jsf?faces-redirect=true");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void filterCarBySelect2() {
	filterCarBySelectFirstTime();
	
	try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/secured/normalUsers/car/vehicleList2.jsf?faces-redirect=true");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private boolean checkCarIsExist(String vinId) {
	// TODO Auto-generated method stub
	
	car theCar=carFacade.getByVin(vinId);
	if(theCar!=null) {
		return false;
	}
	return true;
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

private boolean checkValidForCar(car addNewCar) {
	// TODO Auto-generated method stub
	
	if(addNewCar.getUuid().equals("")||addNewCar.getUuid()==null) {
		return false;
	}

	
	
	return true;
}



public void addImageMain(FileUploadEvent event) {
	byte[] image =event.getFile().getContents();
	String fileName =saveImageToDirectory(image, System.getProperty("catalina.base")+"/images/");
	addNewCar.setMainUrl(fileName);
	addNewCar.setPhotoExist(true);
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:imagesPanelMain");
}




public void refreshCityList() {

	allCity = transportfeeFacade.getAllGroupsOfCityWithLocation(addNewCar.getSelectedLocation());
	if(allCity!=null) {
		addNewCar.setSelectedCity(allCity.get(0).getCity());
		refreshStateList();
	}
}

public void refreshStateList() {

	allState = transportfeeFacade.getAllGroupsOfstateWithCity(addNewCar.getSelectedCity());
	if(allState!=null) {
		if(allState.size()>0) {
			allLanding =new HashedMap<>();
			
			if(allState.get(0).getNjPortCost()!=0) {
				allLanding.put(1, "NEW YORK, NY (1001)");					
			}

			if(allState.get(0).getGaPortCost()!=0) {
				allLanding.put(39, "SAVANNAH, GA (1703)");					
			}

			if(allState.get(0).getTxPortCost()!=0) {
				allLanding.put(381, "HOUSTON, TX (5301)");					
			}

			if(allState.get(0).getCaPortCost()!=0) {
				allLanding.put(117, "LOS ANGELES, CA (2704)");					
			}

			if(allState.get(0).getIndianaPortCost()!=0) {
				allLanding.put(391, "Indianapolis IN (4110)");					
			}

			if(allState.get(0).getFloridaPortCost()!=0) {
				allLanding.put(371, "MIAMI, FL (5201)");					
			}

			if(allState.get(0).getPaltimorPortCost()!=0) {
				allLanding.put(410, "Baltimore, BL (1303)");					
			}
			
		}
	}
}


public void getCarSummeryWithId() {

	

	dollarToDinar = Float.valueOf(form_settingsFacade.getById(1).getValue());
	
	
	HttpServletRequest origRequest = (HttpServletRequest)FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequest();
	Integer id = null;
	try{
		id=Integer.parseInt(origRequest.getParameterValues("id")[0]);
		
	}catch (Exception e) {
		// TODO: handle exception
		
		System.out.println("Ahmed old: "+String.valueOf(e));
	}
	
	System.out.println("Ahmed Load");

	System.out.println(String.valueOf(id));
			if(id!=null){
				
				
				selectedCar=carFacade.getById(id);
				selectedCar.getNormalUserId().getId();
				car_user_normalmoneybox = moneyboxFacade.getByUserId(selectedCar.getNormalUserId().getId());
				
				allmoneybox_transaction_details = moneybox_transaction_detailsFacade.getAllByCarIdAndMoneyBoxId(car_user_normalmoneybox.getId(), selectedCar.getId());

								System.out.println("Ahmed Load3");
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
				
			}
	
}

public void get_all_moneybox_transactions() {
	allCarsAsUserContainer = carFacade.getAllForNormalUserGroupBy(loginBean.getTheUserOfThisAccount().getId());
	allCarsAsUser = carFacade.getAllForNormalUser(loginBean.getTheUserOfThisAccount().getId());
	
	
	allmoneybox_transaction_details = new ArrayList<moneybox_transaction_details>();
	List<moneybox_transaction_details> allDetails = moneybox_transaction_detailsFacade.getAllByUserMoneyBoxId(loginBean.thisAccountMoneyBox.getId());
	for(int i=0;i<allDetails.size();i++) {
		if(allDetails.get(i).getCarId()!=null) {
			if(carIdOfTransaction==(allDetails.get(i).getCarId().getId()) || carIdOfTransaction==-1) {
				if(userOfTransaction==(allDetails.get(i).getCarId().getNormalUserId().getId()) || userOfTransaction==-1) {
				
				
					if(allDetails.get(i).getCarId().getContainer().contains(containerOfTransaction) || containerOfTransaction=="") {
						if(allDetails.get(i).getWire_transfer_number().contains(wireTransferOfTransaction) || wireTransferOfTransaction.equals("")) {
							if(typeOfTransaction==(allDetails.get(i).getTypeOfTransaction()) || typeOfTransaction==-1) {
								
								allmoneybox_transaction_details.add(allDetails.get(i));
							}
						}
					}
				}
			}
		}else if(allDetails.get(i).getWire_transfer_number()!=null) {
				
				if(allDetails.get(i).getWire_transfer_number().contains(wireTransferOfTransaction) || wireTransferOfTransaction.equals("")) {
					if(typeOfTransaction==(allDetails.get(i).getTypeOfTransaction()) || typeOfTransaction==-1) {

						allmoneybox_transaction_details.add(allDetails.get(i));
					}
				}
		}else  {
			if(typeOfTransaction==(allDetails.get(i).getTypeOfTransaction()) || typeOfTransaction==-1) {

				allmoneybox_transaction_details.add(allDetails.get(i));
			}
		}
		
	}
	
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
	
}


public invoiceCar getInvoiceForThisCar(int id) {
	
	invoiceCar inObj = invoiceCarFacade.getByCarId(id);
	return inObj;
}

public void saveNewCarDataMain() {
	loginBean.getTheUserOfThisAccount();
	
	addNewCar.setTitleRecieved(titleRecievedSelected);
	
	addNewCar.setCargoRecieved(setCalendarFromString(cargoRecievedDate));
	addNewCar.setDvl(setCalendarFromString(dvlDate));
	addNewCar.setStRecieved(setCalendarFromString(stRecievedDate));
	addNewCar.setEtd(setCalendarFromString(etdDate));
	addNewCar.setEta(setCalendarFromString(etaDate));
	addNewCar.setStorageStartDate(setCalendarFromString(storageStartDate));
	addNewCar.setStorageEndDate(setCalendarFromString(storageEndDate));
	
	
	
	boolean isValid=checkValidForCar(addNewCar);
	if(isValid) {
		boolean checkCar = checkCarIsExist(addNewCar.getUuid());
		if(checkCar) {
	
	try {
		addNewCar.setState(car.STATE_AddedByCustomer_REVISE);
		updateAllFees();

		addNewCar.setLandcost((float) landFees);
		addNewCar.setSeacost((float) seaFees);
		addNewCar.setFees((float) ( ourFees));
		addNewCar.setCommision((float) (copartFees+GateFees));
		
		
		carFacade.addcar(addNewCar);
		
		for(int i=0;i<images_deleted.size();i++) {
			carimage cImage=new carimage();
			cImage.setCarId(addNewCar);
			cImage.setUrl(images_deleted.get(i));
			cImage.setType(carimage.TYPE_PIC);
			cImage.setDeleted(true);
			carimageFacade.addcarimage(cImage);
			addNewCar.setPhotoExist(false);

		}
		
		for(int i=0;i<docs_deleted.size();i++) {
			carimage cDocs=new carimage();
			cDocs.setCarId(addNewCar);
			cDocs.setUrl(docs_deleted.get(i));
			cDocs.setType(carimage.TYPE_DOC);
			cDocs.setDeleted(true);
			carimageFacade.addcarimage(cDocs);
			addNewCar.setDocExist(false);
		}
		
		
		
		
		for(int i=0;i<images.size();i++) {
			carimage cImage=new carimage();
			cImage.setCarId(addNewCar);
			cImage.setUrl(images.get(i));
			cImage.setType(carimage.TYPE_PIC);
			carimageFacade.addcarimage(cImage);
			addNewCar.setPhotoExist(true);

		}
		
		for(int i=0;i<docs.size();i++) {
			carimage cDocs=new carimage();
			cDocs.setCarId(addNewCar);
			cDocs.setUrl(docs.get(i));
			cDocs.setType(carimage.TYPE_DOC);
			carimageFacade.addcarimage(cDocs);
			addNewCar.setDocExist(true);
		}
		

		
		
		selectedCarState = car.STATE_AddedByCustomer_REVISE;
		carFacade.addcar(addNewCar);
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your car has been added.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/car/vehicleList.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} catch (Exception e1) {
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Error',\r\n" + 
				"			text: '"+e1.getMessage()+".',\r\n" + 
				"			type: 'error'\r\n" + 
				"		});");
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


private BufferedImage scaleImage(BufferedImage bufferedImage, int size) {
    double boundSize = size;
       int origWidth = bufferedImage.getWidth();
       int origHeight = bufferedImage.getHeight();
       double scale;
       if (origHeight > origWidth)
           scale = boundSize / origHeight;
       else
           scale = boundSize / origWidth;
        //* Don't scale up small images.
       if (scale > 1.0)
           return (bufferedImage);
       int scaledWidth = (int) (scale * origWidth);
       int scaledHeight = (int) (scale * origHeight);
       
       BufferedImage after = new BufferedImage(origWidth, origHeight, BufferedImage.TYPE_INT_ARGB);
       AffineTransform at = new AffineTransform();
       at.scale(scale, scale);
       AffineTransformOp scaleOp = 
          new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
       after = scaleOp.filter(bufferedImage, after);
       
       BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
       Graphics2D g = scaledBI.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
       g.drawImage(after, 0, 0, null);
       g.dispose();
       return (scaledBI);
}

public String saveImageToDirectory(byte[] image,String directory) {
	String fileName="";
	try {
		File file=File.createTempFile("img", ".jpg", new File(directory));
	      byte [] data = image;
	      ByteArrayInputStream bis = new ByteArrayInputStream(data);
	      BufferedImage bImage2;
		bImage2 = ImageIO.read(bis);
		
		
		 
	        OutputStream os = new FileOutputStream(file);
		
		// create a BufferedImage as the result of decoding the supplied InputStream
        BufferedImage image2=scaleImage(bImage2, 800);
		// get all image writers for JPG format
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
 
        float quality = 0.5f;
        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);
 
        ImageWriteParam param = writer.getDefaultWriteParam();
 
        // compress to a given quality
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);
 
        // appends a complete image stream containing a single image and
        //associated stream and image metadata and thumbnails to the output
        writer.write(null, new IIOImage(image2, null, null), param);
 
     // close all streams
        os.close();
        ios.close();
        writer.dispose();
		
		
		fileName=file.getName();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return fileName;
      
}


public void previewImage(FileUploadEvent event) {
	byte[] image =event.getFile().getContents();
	String fileName =saveImageToDirectory(image, System.getProperty("catalina.base")+"/images/");
	images.add(fileName);
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:imagesPanel");
}

public void previewFile(FileUploadEvent event) {
	byte[] fileDoc =event.getFile().getContents();
	String fileName =saveImageToDirectory(fileDoc, System.getProperty("catalina.base")+"/images/");
	docs.add(fileName);
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:docsPanel");
}

public void cancel() {
	System.out.println("Cancel");
	try {
		FacesContext.getCurrentInstance()
		   .getExternalContext().redirect("/pages/secured/userData/vehicleList.jsf?faces-redirect=true");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public void deleteFile() {
	 FacesContext context = FacesContext.getCurrentInstance();
	 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	 Integer typeOfFile = Integer.valueOf((String) map.get("typeOfFile"));
	 String fileURL = (String) map.get("fileURL");

		System.out.println(fileURL);
		System.out.println(typeOfFile);
	if(typeOfFile==carimage.TYPE_DOC) {
			removeFileFromDoc(fileURL);
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:docsPanel");
		PrimeFaces.current().executeScript("swal(\"Action Done\", \"The Document Has Been Deleted\", \"success\");");
	}else if(typeOfFile==carimage.TYPE_PIC) {
		
			removeFileFromImages(fileURL);
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:imagesPanel");
		PrimeFaces.current().executeScript("swal(\"Action Done\", \"The Image Has Been Deleted\", \"success\");");
	}
	
}

private void removeFileFromImages(String fileURL) {
	// TODO Auto-generated method stub
	for(int i=0;i<images.size();i++) {
		if(images.get(i).equalsIgnoreCase(fileURL)) {
			images.remove(i);
			images_deleted.add(fileURL);
			return;
		}
	}
}



private void removeFileFromDoc(String fileURL) {
	// TODO Auto-generated method stub
	for(int i=0;i<docs.size();i++) {
		if(docs.get(i).equalsIgnoreCase(fileURL)) {
			docs.remove(i);
			docs_deleted.add(fileURL);
			return;
		}
	}
}


public void releaseVariablesForMain() {
	images=new ArrayList<String>();
	docs=new ArrayList<String>();
	
	images_deleted=new ArrayList<String>();
	docs_deleted=new ArrayList<String>();
	
	cargoRecievedDate="";
	titleRecievedSelected=0;
	dvlDate="";
	stRecievedDate="";
	etdDate="";
	etaDate="";
	storageStartDate="";
	storageEndDate="";
	
	addNewCar=new car();

	addNewCar.setMainId(loginBean.getUserDataFacede().getById(1));
	addNewCar.setNormalUserId(loginBean.getTheUserOfThisAccount());

	allCars=new ArrayList<car>();
	
	filterCarBySelectFirstTime();
}
	

public void refresh_list() {
	filterCarBySelectFirstTime();

	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formOfSelectionCarState");
}

public void filterCarBySelectFirstTime() {

	allCars=new ArrayList<car>();

		user userNewId=loginBean.getTheUserOfThisAccount();
		
		
		if(loginBean.getTheUserOfThisAccount().getRole()==user.ROLE_MAIN) {
			if(selectedCarState==0) {
				//This for warehouse

				List<car> wareHouseMain = carFacade.getAllByStateForMainUser(loginBean.getTheUserOfThisAccount().getId(),selectedCarState);
//				carFacade.getAllWareHouseForMainUser(loginBean.getTheUserOfThisAccount().getId());

				if(wareHouseMain!=null)
					allCars.addAll(wareHouseMain);
				
				

			}else if(selectedCarState==1) {
				// this for dry cargo

				List<car> dryCargoMain = carFacade.getAllByStateForMainUser(loginBean.getTheUserOfThisAccount().getId(),selectedCarState);
//				carFacade.getAllDryCargoForMainUser(loginBean.getTheUserOfThisAccount().getId());

				
				if(dryCargoMain!=null)
					allCars.addAll(dryCargoMain);
				
			

			}else if(selectedCarState==2) {
				
				// this for freight in transit

				List<car> transitMain = carFacade.getAllByStateForMainUser(loginBean.getTheUserOfThisAccount().getId(),selectedCarState);
//				carFacade.getAllFrightInTransitForMainUser(loginBean.getTheUserOfThisAccount().getId());

				
				if(transitMain!=null)
					allCars.addAll(transitMain);
				
				

			}else if(selectedCarState==8) {
				
				// this for SendForPayment

				List<car> SentMain = carFacade.getAllByStateForMainUser(loginBean.getTheUserOfThisAccount().getId(),selectedCarState);
//				getAllFrightSentForPaymentForMainUser(loginBean.getTheUserOfThisAccount().getId());

				
				if(SentMain!=null)
					allCars.addAll(SentMain);
				
				

			}else if(selectedCarState==-1) {
				//this for all

				List<car> wareHouseMain = carFacade.getAllForMainUser(loginBean.getTheUserOfThisAccount().getId());
				
				if(wareHouseMain!=null)
					allCars.addAll(wareHouseMain);
				
								

			}else {
				List<car> wareHouseMain = carFacade.getAllByStateForMainUser(loginBean.getTheUserOfThisAccount().getId(),selectedCarState);
				
				if(wareHouseMain!=null)
					allCars.addAll(wareHouseMain);
			}
		}else {
		if(selectedCarState==0) {
			//This for warehouse
			List<car> wareHouseMain = carFacade.getAllByStateForNormalUser(userNewId.getId(),selectedCarState);
//carFacade.getAllWareHouseFornormalUserId(userNewId.getId());

			if(wareHouseMain!=null)
				allCars.addAll(wareHouseMain);
			
			

		}else if(selectedCarState==1) {
			// this for dry cargo

			List<car> dryCargoMain = carFacade.getAllByStateForNormalUser(userNewId.getId(),selectedCarState);
//carFacade.getAllDryCargoFornormalUserId(userNewId.getId());

			
			if(dryCargoMain!=null)
				allCars.addAll(dryCargoMain);
			
			
			
		}else if(selectedCarState==2) {
			// this for freight in transit

			List<car> transitMain = carFacade.getAllByStateForNormalUser(userNewId.getId(),selectedCarState);
//carFacade.getAllFrightInTransitFornormalUserId(userNewId.getId());

			
			if(transitMain!=null)
				allCars.addAll(transitMain);
			


		}else if(selectedCarState==8) {
			// this for freight Sent

			List<car> SentMain = carFacade.getAllByStateForNormalUser(userNewId.getId(),selectedCarState);

//					carFacade.getAllFrightSentForPaymentFornormalUserId(userNewId.getId());

			
			if(SentMain!=null)
				allCars.addAll(SentMain);
			


		}else if(selectedCarState==-1) {
			//this for all

			allCars = carFacade.getAllForNormalUser(userNewId.getId());


		

		}else {
			List<car> SentMain = carFacade.getAllByStateForNormalUser(userNewId.getId(),selectedCarState);

			
			if(SentMain!=null)
				allCars.addAll(SentMain);
			

		}
		
		}
	}
	
	
	


public main.com.carService.loginNeeds.loginBean getLoginBean() {
	return loginBean;
}


public void setLoginBean(main.com.carService.loginNeeds.loginBean loginBean) {
	this.loginBean = loginBean;
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


public mainTwoAppServiceImpl getMainTwoFacade() {
	return mainTwoFacade;
}


public void setMainTwoFacade(mainTwoAppServiceImpl mainTwoFacade) {
	this.mainTwoFacade = mainTwoFacade;
}


public carAppServiceImpl getCarFacade() {
	return carFacade;
}


public void setCarFacade(carAppServiceImpl carFacade) {
	this.carFacade = carFacade;
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


public carimageAppServiceImpl getCarimageFacade() {
	return carimageFacade;
}


public void setCarimageFacade(carimageAppServiceImpl carimageFacade) {
	this.carimageFacade = carimageFacade;
}


public int getSelectedCarState() {
	return selectedCarState;
}


public void setSelectedCarState(int selectedCarState) {
	this.selectedCarState = selectedCarState;
}


public List<car> getAllCars() {
	return allCars;
}


public void setAllCars(List<car> allCars) {
	this.allCars = allCars;
}


public List<consignee> getAllconsignees() {
	return allconsignees;
}


public void setAllconsignees(List<consignee> allconsignees) {
	this.allconsignees = allconsignees;
}


public List<mainTwo> getAllMainTwo() {
	return allMainTwo;
}


public void setAllMainTwo(List<mainTwo> allMainTwo) {
	this.allMainTwo = allMainTwo;
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


public List<String> getCarStates() {
	return carStates;
}


public void setCarStates(List<String> carStates) {
	this.carStates = carStates;
}


public String getCargoRecievedDate() {
	return cargoRecievedDate;
}


public void setCargoRecievedDate(String cargoRecievedDate) {
	this.cargoRecievedDate = cargoRecievedDate;
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


public int getMainTwoSelectedId() {
	return mainTwoSelectedId;
}


public void setMainTwoSelectedId(int mainTwoSelectedId) {
	this.mainTwoSelectedId = mainTwoSelectedId;
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


public List<String> getImages() {
	return images;
}


public void setImages(List<String> images) {
	this.images = images;
}


public List<String> getDocs() {
	return docs;
}


public void setDocs(List<String> docs) {
	this.docs = docs;
}


public Map<Integer, String> getDistinationMap() {
	return distinationMap;
}


public void setDistinationMap(Map<Integer, String> distinationMap) {
	this.distinationMap = distinationMap;
}


public Map<Integer, String> getOrigineMap() {
	return origineMap;
}


public void setOrigineMap(Map<Integer, String> origineMap) {
	this.origineMap = origineMap;
}


public int getConsigneeId() {
	return consigneeId;
}


public void setConsigneeId(int consigneeId) {
	this.consigneeId = consigneeId;
}


public consignee getSelectedConsignee() {
	return selectedConsignee;
}


public void setSelectedConsignee(consignee selectedConsignee) {
	this.selectedConsignee = selectedConsignee;
}


public Integer getTitleRecievedSelected() {
	return titleRecievedSelected;
}


public void setTitleRecievedSelected(Integer titleRecievedSelected) {
	this.titleRecievedSelected = titleRecievedSelected;
}


public boolean isProgress() {
	return progress;
}


public void setProgress(boolean progress) {
	this.progress = progress;
}




public transportfeeAppServiceImpl getTransportfeeFacade() {
	return transportfeeFacade;
}


public void setTransportfeeFacade(transportfeeAppServiceImpl transportfeeFacade) {
	this.transportfeeFacade = transportfeeFacade;
}


public List<transportfee> getAllLocation() {
	return allLocation;
}


public void setAllLocation(List<transportfee> allLocation) {
	this.allLocation = allLocation;
}


public List<transportfee> getAllCity() {
	return allCity;
}


public void setAllCity(List<transportfee> allCity) {
	this.allCity = allCity;
}


public List<transportfee> getAllState() {
	return allState;
}


public void setAllState(List<transportfee> allState) {
	this.allState = allState;
}


public Map<Integer, String> getAllLanding() {
	return allLanding;
}


public void setAllLanding(Map<Integer, String> allLanding) {
	this.allLanding = allLanding;
}


public transportfee getSelectedTansportFees() {
	return selectedTansportFees;
}


public void setSelectedTansportFees(transportfee selectedTansportFees) {
	this.selectedTansportFees = selectedTansportFees;
}


public List<String> getImages_deleted() {
	return images_deleted;
}


public void setImages_deleted(List<String> images_deleted) {
	this.images_deleted = images_deleted;
}


public List<String> getDocs_deleted() {
	return docs_deleted;
}


public void setDocs_deleted(List<String> docs_deleted) {
	this.docs_deleted = docs_deleted;
}


public float getTotalPrice() {
	return totalPrice;
}


public void setTotalPrice(float totalPrice) {
	this.totalPrice = totalPrice;
}




public int getCars_Ship_UnderReview() {
	return cars_Ship_UnderReview;
}


public void setCars_Ship_UnderReview(int cars_Ship_UnderReview) {
	this.cars_Ship_UnderReview = cars_Ship_UnderReview;
}


public int getCars_PayAndShip_UnderReview() {
	return cars_PayAndShip_UnderReview;
}


public void setCars_PayAndShip_UnderReview(int cars_PayAndShip_UnderReview) {
	this.cars_PayAndShip_UnderReview = cars_PayAndShip_UnderReview;
}


public int getCars_Ship_Rejected() {
	return cars_Ship_Rejected;
}


public void setCars_Ship_Rejected(int cars_Ship_Rejected) {
	this.cars_Ship_Rejected = cars_Ship_Rejected;
}


public int getCars_PayAndShip_Rejected() {
	return cars_PayAndShip_Rejected;
}


public void setCars_PayAndShip_Rejected(int cars_PayAndShip_Rejected) {
	this.cars_PayAndShip_Rejected = cars_PayAndShip_Rejected;
}


public int getCars_Ship_DeliveredByUs() {
	return cars_Ship_DeliveredByUs;
}


public void setCars_Ship_DeliveredByUs(int cars_Ship_DeliveredByUs) {
	this.cars_Ship_DeliveredByUs = cars_Ship_DeliveredByUs;
}


public int getCars_PayAndShip_PayedByCustomer() {
	return cars_PayAndShip_PayedByCustomer;
}


public void setCars_PayAndShip_PayedByCustomer(int cars_PayAndShip_PayedByCustomer) {
	this.cars_PayAndShip_PayedByCustomer = cars_PayAndShip_PayedByCustomer;
}


public int getCars_Ship_InShipping() {
	return cars_Ship_InShipping;
}


public void setCars_Ship_InShipping(int cars_Ship_InShipping) {
	this.cars_Ship_InShipping = cars_Ship_InShipping;
}


public int getCars_PayAndShip_InShipping() {
	return cars_PayAndShip_InShipping;
}


public void setCars_PayAndShip_InShipping(int cars_PayAndShip_InShipping) {
	this.cars_PayAndShip_InShipping = cars_PayAndShip_InShipping;
}


public int getCars_Ship_DeliveredByCustomer() {
	return cars_Ship_DeliveredByCustomer;
}


public void setCars_Ship_DeliveredByCustomer(int cars_Ship_DeliveredByCustomer) {
	this.cars_Ship_DeliveredByCustomer = cars_Ship_DeliveredByCustomer;
}


public int getCars_PayAndShip_DeliveredByCustomer() {
	return cars_PayAndShip_DeliveredByCustomer;
}


public void setCars_PayAndShip_DeliveredByCustomer(int cars_PayAndShip_DeliveredByCustomer) {
	this.cars_PayAndShip_DeliveredByCustomer = cars_PayAndShip_DeliveredByCustomer;
}


public int getTotalNumberOfShippingOnly() {
	return totalNumberOfShippingOnly;
}


public void setTotalNumberOfShippingOnly(int totalNumberOfShippingOnly) {
	this.totalNumberOfShippingOnly = totalNumberOfShippingOnly;
}


public int getTotalNumberOfShippingAndBuy() {
	return totalNumberOfShippingAndBuy;
}


public void setTotalNumberOfShippingAndBuy(int totalNumberOfShippingAndBuy) {
	this.totalNumberOfShippingAndBuy = totalNumberOfShippingAndBuy;
}


public List<user> getAllUsers() {
	return allUsers;
}


public void setAllUsers(List<user> allUsers) {
	this.allUsers = allUsers;
}


public user getSelectedUser() {
	return selectedUser;
}


public void setSelectedUser(user selectedUser) {
	this.selectedUser = selectedUser;
}


public static long getSerialversionuid() {
	return serialVersionUID;
}


public double getOnlineFees() {
	return onlineFees;
}


public void setOnlineFees(double onlineFees) {
	this.onlineFees = onlineFees;
}


public double getGateFees() {
	return GateFees;
}


public void setGateFees(double gateFees) {
	GateFees = gateFees;
}


public double getSeaFees() {
	return seaFees;
}


public void setSeaFees(double seaFees) {
	this.seaFees = seaFees;
}


public double getLandFees() {
	return landFees;
}


public void setLandFees(double landFees) {
	this.landFees = landFees;
}


public double getTotalFees() {
	return totalFees;
}


public void setTotalFees(double totalFees) {
	this.totalFees = totalFees;
}


public double getCopartFees() {
	return copartFees;
}


public void setCopartFees(double copartFees) {
	this.copartFees = copartFees;
}


public double getOurFees() {
	return ourFees;
}


public void setOurFees(double ourFees) {
	this.ourFees = ourFees;
}


public form_settingsAppServiceImpl getForm_settingsFacade() {
	return form_settingsFacade;
}


public void setForm_settingsFacade(form_settingsAppServiceImpl form_settingsFacade) {
	this.form_settingsFacade = form_settingsFacade;
}


public float getDollarToDinar() {
	return dollarToDinar;
}


public void setDollarToDinar(float dollarToDinar) {
	this.dollarToDinar = dollarToDinar;
}





public static double[] getOnlineBid() {
	return onlineBid;
}



public static void setOnlineBid(double[] onlineBid) {
	normalUserBean.onlineBid = onlineBid;
}



public moneybox_transaction_detailsAppServiceImpl getMoneybox_transaction_detailsFacade() {
	return moneybox_transaction_detailsFacade;
}



public void setMoneybox_transaction_detailsFacade(
		moneybox_transaction_detailsAppServiceImpl moneybox_transaction_detailsFacade) {
	this.moneybox_transaction_detailsFacade = moneybox_transaction_detailsFacade;
}



public moneyboxAppServiceImpl getMoneyboxFacade() {
	return moneyboxFacade;
}



public void setMoneyboxFacade(moneyboxAppServiceImpl moneyboxFacade) {
	this.moneyboxFacade = moneyboxFacade;
}



public moneybox getCar_user_normalmoneybox() {
	return car_user_normalmoneybox;
}



public void setCar_user_normalmoneybox(moneybox car_user_normalmoneybox) {
	this.car_user_normalmoneybox = car_user_normalmoneybox;
}



public List<moneybox_transaction_details> getAllmoneybox_transaction_details() {
	return allmoneybox_transaction_details;
}



public void setAllmoneybox_transaction_details(List<moneybox_transaction_details> allmoneybox_transaction_details) {
	this.allmoneybox_transaction_details = allmoneybox_transaction_details;
}



public invoiceCarAppServiceImpl getInvoiceCarFacade() {
	return invoiceCarFacade;
}



public void setInvoiceCarFacade(invoiceCarAppServiceImpl invoiceCarFacade) {
	this.invoiceCarFacade = invoiceCarFacade;
}



public int getTypeOfTransaction() {
	return typeOfTransaction;
}



public void setTypeOfTransaction(int typeOfTransaction) {
	this.typeOfTransaction = typeOfTransaction;
}



public int getCarIdOfTransaction() {
	return carIdOfTransaction;
}



public void setCarIdOfTransaction(int carIdOfTransaction) {
	this.carIdOfTransaction = carIdOfTransaction;
}



public String getWireTransferOfTransaction() {
	return wireTransferOfTransaction;
}



public void setWireTransferOfTransaction(String wireTransferOfTransaction) {
	this.wireTransferOfTransaction = wireTransferOfTransaction;
}



public List<car> getAllCarsAsUser() {
	return allCarsAsUser;
}



public void setAllCarsAsUser(List<car> allCarsAsUser) {
	this.allCarsAsUser = allCarsAsUser;
}



public List<car> getAllCarsAsUserContainer() {
	return allCarsAsUserContainer;
}



public void setAllCarsAsUserContainer(List<car> allCarsAsUserContainer) {
	this.allCarsAsUserContainer = allCarsAsUserContainer;
}



public String getContainerOfTransaction() {
	return containerOfTransaction;
}



public void setContainerOfTransaction(String containerOfTransaction) {
	this.containerOfTransaction = containerOfTransaction;
}



public List<moneybox_transaction_details> getSummery_allmoneybox_transaction_details() {
	return summery_allmoneybox_transaction_details;
}



public void setSummery_allmoneybox_transaction_details(
		List<moneybox_transaction_details> summery_allmoneybox_transaction_details) {
	this.summery_allmoneybox_transaction_details = summery_allmoneybox_transaction_details;
}



public int getUserOfTransaction() {
	return userOfTransaction;
}



public void setUserOfTransaction(int userOfTransaction) {
	this.userOfTransaction = userOfTransaction;
}



public float getAmountRemainToPayForAllCars() {
	return amountRemainToPayForAllCars;
}



public void setAmountRemainToPayForAllCars(float amountRemainToPayForAllCars) {
	this.amountRemainToPayForAllCars = amountRemainToPayForAllCars;
}






}
