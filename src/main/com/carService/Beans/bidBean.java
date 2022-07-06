package main.com.carService.Beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import helpers.retrofit.mainFiles.APIClient;
import helpers.retrofit.mainFiles.APIInterface;
import helpers.retrofit.mainFiles.copartReturnImages;
import main.com.carService.car.car;
import main.com.carService.car.carAppServiceImpl;
import main.com.carService.carLanding.carLanding;
import main.com.carService.carLanding.carLanding.stateOfCar;
import main.com.carService.consignee.consignee;
import main.com.carService.consignee.consigneeAppServiceImpl;
import main.com.carService.customer.customer;
import main.com.carService.customer.customerAppServiceImpl;
import main.com.carService.loginNeeds.user;
import main.com.carService.mainTwo.mainTwo;
import main.com.carService.mainTwo.mainTwoAppServiceImpl;
import main.com.carService.carLanding.carLandingAppServiceImpl;
import main.com.carService.moneyBox.moneyBoxWithDetails;
import main.com.carService.moneyBox.moneybox;
import main.com.carService.moneyBox.moneyboxConfig;
import main.com.carService.notification.notification;
import main.com.carService.notification.notificationAppServiceImpl;
import main.com.carService.shipper.shipper;
import main.com.carService.shipper.shipperAppServiceImpl;
import main.com.carService.vendor.vendor;
import main.com.carService.vendor.vendorAppServiceImpl;
import retrofit2.Call;


@ManagedBean(name = "bidBean")
@SessionScoped
public class bidBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440236767354416L;


	/**
	 * 
	 */
	
	
 
	

	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	 

	private List<moneyBoxWithDetails> listOfAllUsersMoneyBox;
	private List<moneybox> selectedlistOfAllUsersMoneyBox;
	
	
	@ManagedProperty(value = "#{notificationFacadeImpl}")
	private notificationAppServiceImpl notificationFacade;

	List<notification> allNotifcationForThisUser;
	List<notification> allNotifcationForThisUserLimited;
	List<notification> allUnReadNotifcationForThisUser;
	

	private List<carLanding> allCarForMyBid;
	private List<carLanding> listOfFilteredCars;
	private carLanding selectedFreight;
	
	@ManagedProperty(value = "#{carLandingFacadeImpl}")
	private carLandingAppServiceImpl carLandingFacade;
	

	private List<String> images;
	

	private double copartFees;
	private double ourFees=100;
	
	

	@ManagedProperty(value = "#{carFacadeImpl}")
	private carAppServiceImpl carFacade;
	
	@ManagedProperty(value = "#{consigneeFacadeImpl}")
	private consigneeAppServiceImpl consigneeFacade;

	@ManagedProperty(value = "#{shipperFacadeImpl}")
	private shipperAppServiceImpl shipperFacade;
	

	@ManagedProperty(value = "#{mainTwoFacadeImpl}")
	private mainTwoAppServiceImpl mainTwoFacade;
	

	@ManagedProperty(value = "#{vendorFacadeImpl}")
	private vendorAppServiceImpl vendorFacade;
	

	@ManagedProperty(value = "#{customerFacadeImpl}")
	private customerAppServiceImpl customerFacade;
	
	@PostConstruct
	public void init() {
		images=new ArrayList<String>();
		refresh();
		
	}
	
	public void refresh(){
		
		listOfAllUsersMoneyBox = new ArrayList<moneyBoxWithDetails>();
		List<moneybox> AllMoneyBoxs = loginBean.getMoneyboxDataFacede().getAll();
		if (AllMoneyBoxs!=null) {
			for(int i=0;i<AllMoneyBoxs.size();i++) {
				
				moneyBoxWithDetails mO=new moneyBoxWithDetails(filterCarToGetTotalMoneyBox(AllMoneyBoxs.get(i).getUserId()), AllMoneyBoxs.get(i));
				listOfAllUsersMoneyBox.add(mO);
			}
		}
		if(loginBean.isLoggedIn()) {
		allNotifcationForThisUser=notificationFacade.getAllByuserId(loginBean.getTheUserOfThisAccount().getId());
		
		allCarForMyBid=carLandingFacade.getAllForUserBiding(loginBean.getTheUserOfThisAccount().getId());
	
		}
		
	}

	public void refreshNotification() {
		if(loginBean.isLoggedIn()) {
		allNotifcationForThisUserLimited=notificationFacade.getAllByuserIdAndLimit(loginBean.getTheUserOfThisAccount().getId(),0,30);
		
		allUnReadNotifcationForThisUser=notificationFacade.getAllByuserIdAndState(loginBean.getTheUserOfThisAccount().getId(),false,0,999);
		

		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("notifiactionPanel1");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("notifiactionPanel2");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("notifiactionPanel3");
		}
		}
	
	
	
	public float filterCarToGetTotalMoneyBox(user userAccount) {
		
		List<car> allCars = new ArrayList<car>();
		if(userAccount.getRole()==user.ROLE_MAIN) {
			
				//This for warehouse

				List<car> wareHouseMain = carFacade.getAllWareHouseForMainUser(userAccount.getId());

				if(wareHouseMain!=null)
					allCars.addAll(wareHouseMain);
				
				

		}else if(userAccount.getRole()==user.ROLE_SHIPPER) {

			shipper shipperNewId=shipperFacade.getByUserId(userAccount.getId());
		
				//This for warehouse
				List<car> wareHouseMain = carFacade.getAllWareHouseForShipper(shipperNewId.getId());

				if(wareHouseMain!=null)
					allCars.addAll(wareHouseMain);
				

		}else if(userAccount.getRole()==user.ROLE_MAIN2) {

			mainTwo mainTwoId=mainTwoFacade.getByUserId(userAccount.getId());
			
				//This for warehouse
				List<car> wareHouseMain = carFacade.getAllWareHouseForMainUserTwo(mainTwoId.getId());

				if(wareHouseMain!=null)
					allCars.addAll(wareHouseMain);
				

		}else if(userAccount.getRole()==user.ROLE_VENDOR) {

			vendor vendorNewId=vendorFacade.getByUserId(userAccount.getId());
			
				//This for warehouse
				List<car> wareHouseMain = carFacade.getAllWareHouseForVendor(vendorNewId.getId());

				if(wareHouseMain!=null)
					allCars.addAll(wareHouseMain);
				
				

		}else if(userAccount.getRole()==user.ROLE_CUSTOMER) {

			customer customerNewId=customerFacade.getByUserId(userAccount.getId());
			
				//This for warehouse
				List<car> wareHouseMain = carFacade.getAllWareHouseForCustomer(customerNewId.getId());

				if(wareHouseMain!=null)
					allCars.addAll(wareHouseMain);
				
				

			
		}else if(userAccount.getRole()==user.ROLE_CONGSIGNEE) {

			List<consignee> consigneeNewId=consigneeFacade.getAllByUserId(userAccount.getId());
			for(int i=0;i<consigneeNewId.size();i++ ) {
			
				//This for warehouse
				List<car> wareHouseMain = carFacade.getAllWareHouseForConsignee(consigneeNewId.get(i).getId());

				if(wareHouseMain!=null)
					allCars.addAll(wareHouseMain);
				
				

			
		}
		}
		
		
		
		float totalPrice = 0;
		for(int i=0;i<allCars.size();i++) {
//			data.commision+data.fees+data.seacost+data.landcost
			float commission = 0;
			float fees = 0;
			float seaCost = 0;
			float landcost = 0;
			if(allCars.get(i).getCommision()!=0) commission =  allCars.get(i).getCommision();
			if(allCars.get(i).getFees()!=0) fees =  allCars.get(i).getFees();
			if(allCars.get(i).getSeacost()!=0) seaCost =  allCars.get(i).getSeacost();
			if(allCars.get(i).getLandcost()!=0) landcost =  allCars.get(i).getLandcost();
			totalPrice +=commission+
					fees+
					seaCost+
					landcost;
		}
		
		return totalPrice;
		
	}
	
	
	public stateOfCar getStateOfCar(int type) {
        return stateOfCar.values()[type];
    }
	
	public stateOfCar[] getStateOfCar() {
        return stateOfCar.values();
    }
	
	
	public void selectCarRowForMain(SelectEvent event) {
		selectedFreight = ((carLanding) event.getObject());
		System.out.println("Selected Id: "+selectedFreight.getLot());
		updateImagesWithLink(selectedFreight.getAllImagesLink());
		int level=calcBean.getLevel(Float.valueOf(selectedFreight.getCurrentBid()));
		copartFees = calcBean.CalculateCopart(level, Float.valueOf(selectedFreight.getCurrentBid()));
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/public/carsForDetails.jsf?id="+selectedFreight.getId()+"&faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void makeThePaymentTransaction() {
		float totalAmount = Float.valueOf(selectedFreight.getCurrentBid())+Float.valueOf(selectedFreight.getCopartFees())+Float.valueOf(selectedFreight.getOurFees());
		if(loginBean.getThisAccountMoneyBox()!=null) {
			if(loginBean.getThisAccountMoneyBox().getDepositedMoney()>=totalAmount) {
				moneyboxConfig.makeaPayment(totalAmount, selectedFreight.getUserMaxBidId(), loginBean.getUserDataFacede(), loginBean.getMoneyboxDataFacede(), loginBean.getMoneybox_transaction_detailsDataFacede());
				selectedFreight.setPaymentDone(true);
				
				carLandingFacade.addcarLanding(selectedFreight);
	    		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Success',\r\n" + 
						"			text: 'Your Payment has been done.',\r\n" + 
						"			type: 'success'\r\n" + 
						"		});");
			}else {
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Your Account ',\r\n" + 
						"			text: 'Please Make a Deposite to be able to make this payment with the freight Total Price',\r\n" + 
						"			left:\"2%\"\r\n" + 
						"		});");
			}
		}
	}
	public void updateImagesWithLink(String lotImagesLink) {
		
        try {
        	images = new ArrayList<String>();
        	String newLinkUrlWithSlach = lotImagesLink.substring(0, lotImagesLink.indexOf('?'))+"/"+lotImagesLink.substring( lotImagesLink.indexOf('?'),lotImagesLink.length());
    		APIInterface apiInterface = APIClient.getClientForCopartImages(newLinkUrlWithSlach).create(APIInterface.class);
    		  Call<copartReturnImages> call = apiInterface.getAllImagesFromCopart();
    		  
        	copartReturnImages carImages= call.execute().body();

        	if(carImages != null) {
    			
    			selectedFreight.setMainImage(carImages.lotImages.get(0).link.get(0).url);
    			for(int i=1;i<carImages.lotImages.size();i++) {
    				images.add(carImages.lotImages.get(i).link.get(0).url);
    			}
    		}
    	

			PrimeFaces.current().executeScript("hideDialog()");
          	  
		} catch (IOException e1) {
			
			PrimeFaces.current().executeScript("hideDialog()");
		} catch (Exception e1) {
			
			PrimeFaces.current().executeScript("hideDialog()");
		}catch (Error e1) {
			PrimeFaces.current().executeScript("hideDialog()");
		}
}
	
	 public void onRowEdit(RowEditEvent event) {
		 

	        moneyBoxWithDetails mNewWithDetails= ((moneyBoxWithDetails) event.getObject());
	        moneybox mNew=mNewWithDetails.moneyBoxItem;
	        loginBean.getMoneyboxDataFacede().addmoneybox(mNew);
	        PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Success',\r\n" + 
					"			text: 'Your data has been saved.',\r\n" + 
					"			type: 'success'\r\n" + 
					"		});");
	       
         
    }
	
	
	
	
	
	public void readNotification() {
		 FacesContext context = FacesContext.getCurrentInstance();
		 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		 Integer notificationId = Integer.valueOf((String) map.get("notificationId"));
		 
		notification notificationNew=notificationFacade.getById(notificationId);
		notificationNew.setReaded(true);
		notificationFacade.addnotification(notificationNew);
	}
	
	
	
	
	public main.com.carService.loginNeeds.loginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(main.com.carService.loginNeeds.loginBean loginBean) {
		this.loginBean = loginBean;
	}

	public List<moneyBoxWithDetails> getListOfAllUsersMoneyBox() {
		return listOfAllUsersMoneyBox;
	}

	public void setListOfAllUsersMoneyBox(List<moneyBoxWithDetails> listOfAllUsersMoneyBox) {
		this.listOfAllUsersMoneyBox = listOfAllUsersMoneyBox;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<moneybox> getSelectedlistOfAllUsersMoneyBox() {
		return selectedlistOfAllUsersMoneyBox;
	}

	public void setSelectedlistOfAllUsersMoneyBox(List<moneybox> selectedlistOfAllUsersMoneyBox) {
		this.selectedlistOfAllUsersMoneyBox = selectedlistOfAllUsersMoneyBox;
	}

	public notificationAppServiceImpl getNotificationFacade() {
		return notificationFacade;
	}

	public void setNotificationFacade(notificationAppServiceImpl notificationFacade) {
		this.notificationFacade = notificationFacade;
	}

	public List<notification> getAllNotifcationForThisUser() {
		return allNotifcationForThisUser;
	}

	public void setAllNotifcationForThisUser(List<notification> allNotifcationForThisUser) {
		this.allNotifcationForThisUser = allNotifcationForThisUser;
	}

	public List<notification> getAllNotifcationForThisUserLimited() {
		return allNotifcationForThisUserLimited;
	}

	public void setAllNotifcationForThisUserLimited(List<notification> allNotifcationForThisUserLimited) {
		this.allNotifcationForThisUserLimited = allNotifcationForThisUserLimited;
	}

	public List<notification> getAllUnReadNotifcationForThisUser() {
		return allUnReadNotifcationForThisUser;
	}

	public void setAllUnReadNotifcationForThisUser(List<notification> allUnReadNotifcationForThisUser) {
		this.allUnReadNotifcationForThisUser = allUnReadNotifcationForThisUser;
	}

	public List<carLanding> getAllCarForMyBid() {
		return allCarForMyBid;
	}

	public void setAllCarForMyBid(List<carLanding> allCarForMyBid) {
		this.allCarForMyBid = allCarForMyBid;
	}

	public carLandingAppServiceImpl getCarLandingFacade() {
		return carLandingFacade;
	}

	public void setCarLandingFacade(carLandingAppServiceImpl carLandingFacade) {
		this.carLandingFacade = carLandingFacade;
	}

	public List<carLanding> getListOfFilteredCars() {
		return listOfFilteredCars;
	}

	public void setListOfFilteredCars(List<carLanding> listOfFilteredCars) {
		this.listOfFilteredCars = listOfFilteredCars;
	}

	public carLanding getSelectedFreight() {
		return selectedFreight;
	}

	public void setSelectedFreight(carLanding selectedFreight) {
		this.selectedFreight = selectedFreight;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
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

	public carAppServiceImpl getCarFacade() {
		return carFacade;
	}

	public void setCarFacade(carAppServiceImpl carFacade) {
		this.carFacade = carFacade;
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
	
	
	
	
	
}
