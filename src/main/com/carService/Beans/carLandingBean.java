package main.com.carService.Beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import helpers.retrofit.mainFiles.APIClient;
import helpers.retrofit.mainFiles.APIInterface;
import helpers.retrofit.mainFiles.OrderOutDetails;
import helpers.retrofit.mainFiles.copartReturnImages;
import main.com.carService.biding.biding;
import main.com.carService.biding.bidingAppServiceImpl;
import main.com.carService.carLanding.carLanding;
import main.com.carService.carLanding.carLanding.stateOfCar;
import main.com.carService.costCalc.transportfee;
import main.com.carService.costCalc.transportfeeAppServiceImpl;
import main.com.carService.form_settings.form_settingsAppServiceImpl;
import main.com.carService.carLanding.carLandingAppServiceImpl;
import main.com.carService.carLandingImage.carlandingimage;
import main.com.carService.carLandingImage.carlandingimageAppServiceImpl;
import main.com.carService.myCars.mycars;
import main.com.carService.myCars.mycarsAppServiceImpl;
import main.com.carService.notification.notification;
import main.com.carService.notification.notificationAppServiceImpl;
import retrofit2.Call;


@ManagedBean(name = "carLandingBean")
@ViewScoped
public class carLandingBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440236769354416L;


	/**
	 * 
	 */
	
	
 
	

	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	

	@ManagedProperty(value = "#{bidBean}")
	private bidBean bidBean; 
	 


	@ManagedProperty(value = "#{carLandingFacadeImpl}")
	private carLandingAppServiceImpl carLandingFacade;
	
	

	@ManagedProperty(value = "#{carlandingimageFacadeImpl}")
	private carlandingimageAppServiceImpl carlandingimageFacade;
	

	@ManagedProperty(value = "#{form_settingsFacadeImpl}")
	private form_settingsAppServiceImpl form_settingsFacade;
	

	public HashMap<String, String>allPapers;
	public HashMap<String, Integer>allPapersCodes;
	List<carLanding> listOfAddedCars;

	List<carLanding> listOfCarsLandingScroller;
	
	List<carLanding> listOfCarsGroupByMake;
	
	carLanding selectedFreight;
	private boolean progress=false;
	private boolean showNotificationOfBidWinning=false;
	private boolean showNotificationOfBidOutBid=false;
	

	private Date startDate;
	private Date bidingDate;
	private  Date endDate;
	

	
	private float dollarToDinar = 0;
	
	private List<String> images;
	
	private carLanding selectedCarPage;

	@ManagedProperty(value = "#{notificationFacadeImpl}")
	private notificationAppServiceImpl notificationFacade;
	


	@ManagedProperty(value = "#{bidingFacadeImpl}")
	private bidingAppServiceImpl bidingFacade;
	

	@ManagedProperty(value = "#{mycarsFacadeImpl}")
	private mycarsAppServiceImpl mycarsFacade;
	

	private String searchType;
	private String searchMake;
	private String searchModel;
	private String searchStartYear;
	private String searchEndYear;

	private List<carLanding> listOfUploadedDataCars;
	private List<carLanding> listOfFilteredCars;

	private UploadedFile fileExcel;
	
	private Integer progressLoading;
	private String differenceTimeDateFromBidToEnd;
	private String differenceTimeDateFromStartToBid;
	private float incrementBid;
	private float totalBid;
	

	

	private List<carLanding> allCarsString;
	private String selectedCarSearch;
	private biding currentBiding;
	private biding currentBidingMaximum ;
	private boolean showCurrentStatues;
	
	private Long diffFromBidToEnd;
	private Long diffFromStartToBid;
	private Long diffFromMaxToNow;
	

	private List<biding> allCurrentBidCars;
	private List<mycars> allCarsWatchList;
	private List<mycars> allCarsFavorits;
	
	

	private List<mycars> listOfFilteredCars2;
	private mycars selectedFreight2;
	private biding selectedFreight3;
	private List<biding> listOfFilteredCars3;
	
	private int carViewId=0;
	private boolean carInWatchList=false;
	private String currentBidStateDetails="You haven't Bid";
	private float valueOfCarBid=0;

	private double copartFees;
	private double ourFees;
	private String timeInSecondsForLive;
	private double timeLiveEnd=1;
	private boolean isfileUploaded=false;
	
	

	@ManagedProperty(value = "#{transportfeeFacadeImpl}")
	private transportfeeAppServiceImpl transportfeeFacade;
	
	

	private List<transportfee> allLocation;
	private List<transportfee> allCity;
	private List<transportfee> allState;
	private Map<Integer,String> allLanding;
	
	private transportfee selectedTansportFees;

	private String selectedLocation;
	private String selectedCity;
	private String selectedState;
	
	private int carSize;
	private String carPrice;
	
	private int portType;
	
	private double onlineFees;
	private double GateFees=59;
	private double seaFees;
	private double landFees;
	private double totalFees;
	
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


	public static double CalculateCopart(int level,double price) {

double copFees=0;

if(level == 0) {
copFees=75.00;
}else if(level == 1) {
copFees=75.00;
}else if(level == 2) {
copFees=110.00;
}else if(level == 3) {
copFees=110.00;
}else if(level == 4) {
copFees=125.00;
}else if(level == 5) {
copFees=130.00;
}else if(level == 6) {
copFees=140.00;
}else if(level == 7) {
copFees=155.00;
}else if(level == 8) {
copFees=170.00;
}else if(level == 9) {
copFees=185.00;
}else if(level == 10) {
copFees=200.00;
}else if(level == 11) {
copFees=225.00;
}else if(level == 12) {
copFees=240.00;
}else if(level == 13) {
copFees=275.00;
}else if(level == 14) {
copFees=300.00;
}else if(level == 15) {
copFees=300.00;
}else if(level == 16) {
copFees=300.00;
}else if(level == 17) {
copFees=300.00;
}else if(level == 18) {
copFees=350.00;
}else if(level == 19) {
copFees=375.00;
}else if(level == 20) {
copFees=400.00;
}else if(level == 21) {
copFees=450.00;
}else if(level == 22) {
copFees=475.00;
}else if(level == 23) {
copFees=500.00;
}else if(level == 24) {
copFees=565.00;
}else if(level == 25) {
copFees=600.00;
}else if(level == 26) {
copFees=625.00;
}else if(level == 27) {
copFees=650.00;
}else if(level == 28) {
copFees=675.00;
}else if(level == 29) {
copFees = 0.04 * price;
}else if(level == 30) {
copFees = 0.04 * price;
}else if(level == 31) {
copFees = 0.04 * price;
}else if(level == 32) {
copFees = 0.04 * price;
}else if(level == 33) {
copFees = 0.04 * price;
}else if(level == 34) {
copFees = 0.20 * price;
}

return copFees;
}
    public Integer getProgressLoading() {
        if(progressLoading == null) {
        	progressLoading = 0;
        }
        else {
        	if(progressLoading > 100)
            	progressLoading = 100;
        }
         
        return progressLoading;
    }
 
    public void setProgressLoading(Integer progressLoading) {
        this.progressLoading = progressLoading;
    }
    
    
	@PostConstruct
	public void init() {

		listOfAddedCars=new ArrayList<carLanding>();
		currentBidingMaximum=new biding();
		
		allPapers = new HashMap<>();
		allPapersCodes = new HashMap();
		fillAllPapers();
		refresh();
		
		
		
	}
	
	
	public Date getDateTimeNow(){
		return new Date();
	}
	public void refreshTheCurrentBidStatue() {
		currentBidingMaximum = bidingFacade.getByCarIdandMaxAmountAndType(selectedCarPage.getId(),biding.TYPE_BIDING);
		showCurrentStatues=true;
		if(loginBean.isLoggedIn()) {
			if(selectedCarPage.isActive()) {
				currentBidStateDetails=stateOfCar.values()[selectedCarPage.getState()].getName();
				
			}else {
				if(currentBiding!=null) {
					if(currentBiding.getUserId().getId().equals(selectedCarPage.getUserMaxBidId().getId())) {
						currentBidStateDetails = "YOU WIN THE BIDING";
					}else {
						currentBidStateDetails = "YOU LOSS THE BIDING";
					}
				}else {
					currentBidStateDetails = "YOU LOSS THE BIDING";
				}
			}
			
		}else {
			if(selectedCarPage.getUserMaxBidId()==null) {
				
				currentBidStateDetails="No One Bid";
				showCurrentStatues=true;
			}else {
				showCurrentStatues=false;
			}
		}
		
		
		
		
		
//		if(currentBiding==null) {
//			if(selectedCarPage.isActive()) {
//				if(selectedCarPage.getUserMaxBidId()==null) {
//					currentBidStateDetails="No One Bid";
//				}
//			}
//			else {
//				
//				currentBidStateDetails="";
//			}
//		}else if(currentBiding.getUserId().getId()==selectedCarPage.getUserMaxBidId().getId()) {
//			if(selectedCarPage.isActive()) {
//				currentBidStateDetails=stateOfCar.values()[selectedCarPage.getState()].getName();
//			}else {
//				currentBidStateDetails = "YOU WIN THE BIDING";
//			}
//		}else {
//			if(selectedCarPage.isActive()) {
//				currentBidStateDetails=stateOfCar.values()[selectedCarPage.getState()].getName();
//			}else {
//				currentBidStateDetails = "YOU LOSS THE BIDING";
//			}
//		}
		
		
	}
	public List<carLanding> completeText(String query) {
		
		allCarsString =new ArrayList<carLanding>();

		allCarsString = carLandingFacade.getAllWithPagination(0, 7, query);
		
        return allCarsString;
    }
	
	public void deleteCar() {
		 FacesContext context = FacesContext.getCurrentInstance();
		 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		 Integer carId = Integer.valueOf((String) map.get("carId"));
		 
		 carLanding deletedCar = carLandingFacade.getById(carId);
		 deletedCar.setDeleted(true);
		 carLandingFacade.addcarLanding(deletedCar);
		 
			PrimeFaces.current().executeScript("swal(\"Action Done\", \"The Car Has Been Deleted\", \"success\");");
			
		 try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleList.jsf?faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}
	public void onCarSelect() {
		
		System.out.println(String.valueOf(selectedCarSearch));
		
		carLanding selectedCar = carLandingFacade.getByVin(selectedCarSearch);
				
				HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    		origRequest.getRequestURL();
	    			try {
						FacesContext.getCurrentInstance().getExternalContext().redirect
						("/pages/public/carsForDetails.jsf?id="+selectedCar.getId());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		
	}
	
public boolean isCarInFavourites(int num) {
		
		if(loginBean.isLoggedIn()) {
		mycars watchListCarNew=mycarsFacade.getByUserIdAndCarIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST, num);
		
		if(watchListCarNew!=null) {
			
			return true;
		}
		}
		return false;
	}

public boolean isNotCarInFavourites(int num) {
	
	if(loginBean.isLoggedIn()) {
	mycars watchListCarNew=mycarsFacade.getByUserIdAndCarIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST, num);
	
	if(watchListCarNew!=null) {
		
		return false;
	}
	}
	return true;
}
	
	public void setCarToWatchListNum(int num) {
		
		carLanding carId=carLandingFacade.getById(num);
		if(loginBean.isLoggedIn()) {
			System.out.println("Ahmed CarBid3");
			//Make this car added to my watch List
			mycars watchListCarNew=mycarsFacade.getByUserIdAndCarIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST, num);
			if(watchListCarNew==null) {
				carInWatchList=true;
				watchListCarNew =new mycars();
				watchListCarNew.setCarLandingId(carId);
				watchListCarNew.setType(mycars.TYPE_WATCH_LIST);
				watchListCarNew.setUserId(loginBean.getTheUserOfThisAccount());
				mycarsFacade.addmycars(watchListCarNew);

			}else {
				carInWatchList=false;
				try {
					mycarsFacade.delete(watchListCarNew);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("setWatchListData");
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("setWatchListDataMobile");
			}else {
			
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Problem',\r\n" + 
					"			text: 'Please Login ',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
		}
	}
	
	public void setCarToWatchList() {
		if(loginBean.isLoggedIn()) {
			System.out.println("Ahmed CarBid3");
			//Make this car added to my watch List
			mycars watchListCarNew=mycarsFacade.getByUserIdAndCarIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST, carViewId);
			if(watchListCarNew==null) {
				carInWatchList=true;
				watchListCarNew =new mycars();
				watchListCarNew.setCarLandingId(selectedCarPage);
				watchListCarNew.setType(mycars.TYPE_WATCH_LIST);
				watchListCarNew.setUserId(loginBean.getTheUserOfThisAccount());
				mycarsFacade.addmycars(watchListCarNew);

			}else {
				carInWatchList=false;
				try {
					mycarsFacade.delete(watchListCarNew);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("setWatchListData");
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("setWatchListDataMobile");
		}else {
			
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Problem',\r\n" + 
					"			text: 'Please Login ',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
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
		System.out.println("Fine Data");
		isfileUploaded=false;
		if(loginBean.getTheUserOfThisAccount()!=null) {
			
if(loginBean.getTheUserOfThisAccount().getId()!=null) {
	
			//Get All Watch List
			allCarsWatchList = mycarsFacade.getAllByUserIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST);
			

			//Get All Favorites List
			allCarsFavorits = mycarsFacade.getAllByUserIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_FAVORITE);
			
			//Get All Landing Car i have make a bid on it
			allCurrentBidCars=bidingFacade.getAllByuserId(loginBean.getTheUserOfThisAccount().getId());
		
}
}
		incrementBid=25;
		totalBid=0;
		listOfCarsLandingScroller=carLandingFacade.getAllForLanding();
		listOfCarsGroupByMake=carLandingFacade.getAllGroupsOfMake();
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRequest();
		
		try{
			Integer id=Integer.parseInt(origRequest.getParameterValues("id")[0]);

			System.out.println("Done");
				if(id!=null){

	    			System.out.println("Play");
					images=new ArrayList<String>();
					selectedCarPage=carLandingFacade.getById(id);
					refreshTheCurrentBidStatue();
					carViewId=id;
					//Here Get the images For the main 
					/**
					 * 
					 */

					String lotImagesLink=selectedCarPage.getAllImagesLink();
					 try {

				        	images = new ArrayList<String>();
				        	String newLinkUrlWithSlach = lotImagesLink.substring(0, lotImagesLink.indexOf('?'))+"/"+lotImagesLink.substring( lotImagesLink.indexOf('?'),lotImagesLink.length());
				    		APIInterface apiInterface = APIClient.getClientForCopartImages(newLinkUrlWithSlach).create(APIInterface.class);
				    		  Call<copartReturnImages> call = apiInterface.getAllImagesFromCopart();

				    			System.out.println("Ahmed handle Try");
				        	copartReturnImages carImages= call.execute().body();
				        	if(carImages != null) {
				    			System.out.println("Ahmed handled:");
				        		selectedCarPage.setMainImage(carImages.lotImages.get(0).link.get(0).url);
				        		for(int i=1;i<carImages.lotImages.size();i++) {

					    			System.out.println("Ahmed handled:"+String.valueOf(carImages.lotImages.get(i).link.get(0).url));
				    				images.add(carImages.lotImages.get(i).link.get(0).url);
				    			}
				        		
				        		
				        	}

				        	

				        	

				    		//FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("myCarousel");
				          	  
						} catch (IOException e1) {

				        	System.out.println("ImagesLoadedError: "+e1.toString());
				        	
						} catch (Exception e1) {

				        	System.out.println("ImagesLoadedError: "+e1.toString());
				        	
						}catch (Error e1) {

				        	System.out.println("ImagesLoadedError: "+e1.toString());
				        	
					
						}
					 List<carlandingimage> allImageFromDatabase = carlandingimageFacade.getAllByCarIdAndType(id, carlandingimage.TYPE_AUCTION);
			        	
					 System.out.println("Here One");
					
					 if(allImageFromDatabase!=null) {
			        	for(int i=0;i<allImageFromDatabase.size();i++) {
		    				images.add("/images/?file="+allImageFromDatabase.get(i).getUrl());
		    			}
					 }
		    			

		    			if(images.size()==0) {
		    				images.add("https://almzzad.com/resources/Image/caromoto logo-04.png");
				    		
		    			}
		    			
		    			

			        	System.out.println("Images Loaded Error: "+String.valueOf(images.size()));
			        	
					valueOfCarBid=Float.valueOf(selectedCarPage.getCurrentBid());
					updateAllFees();
					//Get the userBid if applicable
					if(loginBean.isLoggedIn()) {

						
						System.out.println("Ahmed CarBid3");
						//Make this car added to my watch List
						mycars watchListCarNew=mycarsFacade.getByUserIdAndCarIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST, id);
						if(watchListCarNew==null) {
							carInWatchList=false;
						}else {
							carInWatchList=true;
						}

						FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("setWatchListData");
						FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("setWatchListDataMobile");
						
						currentBiding=bidingFacade.getByCarIdAnduserIdAndType(selectedCarPage.getId(), loginBean.getTheUserOfThisAccount().getId(),biding.TYPE_BIDING);
						if(currentBiding!=null) {
						totalBid=currentBiding.getFullAmount();
//						incrementBid=currentBiding.getIncrement();
						incrementBid=25;
						currentBidingMaximum = bidingFacade.getByCarIdandMaxAmountAndType(selectedCarPage.getId() ,biding.TYPE_BIDING);
						
						}
						
					}
					
					
					
					
					
					
					
					
					
				}
			}
		catch(Exception ex){
			 
		}
		
		try{
			String categories=String.valueOf(origRequest.getParameterValues("category")[0]);
				if(categories!=null){
//					listOfAddedCars=carLandingFacade.getAllForCategories(categories);
				}
			}
		catch(Exception ex){
			 
		}
		
		

		
		
		allLocation = transportfeeFacade.getAllGroupsOfLocation();
		allCity=new ArrayList<transportfee>();
		allState=new ArrayList<transportfee>();
		selectedLocation=allLocation.get(0).getLocation();
		
		refreshCityList();
		selectedCity=allCity.get(0).getCity();
		
		refreshStateList();
	}
	
	
	public void refreshCityList() {

		allCity = transportfeeFacade.getAllGroupsOfCityWithLocation(selectedLocation);
		if(allCity!=null) {
			selectedCity=allCity.get(0).getCity();
			refreshStateList();
		}
	}
	
	public void refreshStateList() {

		allState = transportfeeFacade.getAllGroupsOfstateWithCity(selectedCity);
		if(allState!=null) {
			if(allState.size()>0) {
				allLanding =new HashedMap<>();
				
				if(allState.get(0).getNjPortCost()!=0) {
					allLanding.put(0, "Newark, NJ");					
				}
				
				if(allState.get(0).getGaPortCost()!=0) {
					allLanding.put(1, "Savannah, GA");					
				}
				
				if(allState.get(0).getTxPortCost()!=0) {
					allLanding.put(2, "Houston, TX");					
				}
				
				if(allState.get(0).getCaPortCost()!=0) {
					allLanding.put(3, "Los Angeles, CA");					
				}
				
			}
		}
	}
	
	
	
	public void selectCarRowForMain2(SelectEvent event) {
		selectedFreight2 = ((mycars) event.getObject());
		System.out.println("Selected Id: "+selectedFreight2.getCarLandingId().getLot());
		updateImagesWithLink(selectedFreight2.getCarLandingId().getAllImagesLink());
		int level=calcBean.getLevel(Float.valueOf(selectedFreight2.getCarLandingId().getCurrentBid()));
		bidBean.setCopartFees( calcBean.CalculateCopart(level, Float.valueOf(selectedFreight2.getCarLandingId().getCurrentBid())));
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/public/carsForDetails.jsf?id="+selectedFreight2.getCarLandingId().getId()+"&faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void goNextCar(int id) {

		carLanding car = carLandingFacade.getNextRecord(id);
			
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/public/carsForDetails.jsf?id="+car.getId()+"&faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void goPreviousCar(int id) {

		carLanding car = carLandingFacade.getPreviousRecord(id);
			
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/public/carsForDetails.jsf?id="+car.getId()+"&faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void makeIncrementBid() {
		if(loginBean.getThisAccountMoneyBox()!=null) {
			if(loginBean.getTheUserOfThisAccount().getId()!=null) {
				System.out.println("DataUSer:"+String.valueOf(loginBean.getTheUserOfThisAccount().getId()));
			 loginBean.setThisAccountMoneyBox(loginBean.getMoneyboxDataFacede().getByUserId(loginBean.getTheUserOfThisAccount().getId()));
			System.out.println("Data:"+String.valueOf(loginBean.getThisAccountMoneyBox().isActive()));
			 if(loginBean.getThisAccountMoneyBox().isActive()) {
				//You can make a bid
				if(selectedCarPage.isActive()) {
					
					//You are able to do it
					if(!(incrementBid<=0)&&!(totalBid<=0)) {
						
						currentBiding=bidingFacade.getByCarIdAnduserIdAndType(selectedCarPage.getId(), loginBean.getTheUserOfThisAccount().getId(),biding.TYPE_BIDING);
						if(currentBiding==null) {
							currentBiding=new biding();
						currentBiding.setFullAmount(totalBid);
						currentBiding.setIncrement(incrementBid);
						currentBiding.setType(biding.TYPE_BIDING);
						currentBiding.setLastDateBid(new Date());
						currentBiding.setUserId(loginBean.getTheUserOfThisAccount());
						currentBiding.setCarlandingId(selectedCarPage);
						bidingFacade.addbiding(currentBiding);
						currentBidingMaximum = bidingFacade.getByCarIdandMaxAmountAndType(selectedCarPage.getId() ,biding.TYPE_BIDING);
						
						arrangeBidingForThisCar(selectedCarPage);

						updateAllFees();

					    FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:formCalc");
						}else {
							if(totalBid>currentBiding.getFullAmount()) {
							currentBiding.setFullAmount(totalBid);
							currentBiding.setIncrement(incrementBid);
							currentBiding.setLastDateBid(new Date());
							currentBiding.setType(biding.TYPE_BIDING);
							currentBiding.setUserId(loginBean.getTheUserOfThisAccount());
							currentBiding.setCarlandingId(selectedCarPage);
							bidingFacade.addbiding(currentBiding);
							currentBidingMaximum = bidingFacade.getByCarIdandMaxAmountAndType(selectedCarPage.getId() ,biding.TYPE_BIDING);
							
							arrangeBidingForThisCar(selectedCarPage);

							updateAllFees();

						    FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:formCalc");
							}else {
								PrimeFaces.current().executeScript("new PNotify({\r\n" + 
										"			title: 'Your Biding ',\r\n" + 
										"			text: 'Please Make a Biding Higher than the last time ',\r\n" + 
										"			left:\"2%\"\r\n" + 
										"		});");
							}
						}
						
						
					
					
					
					}else {
						PrimeFaces.current().executeScript("new PNotify({\r\n" + 
								"			title: 'Your Bid ',\r\n" + 
								"			text: 'Please increase the amount to not be zero or less',\r\n" + 
								"			left:\"2%\"\r\n" + 
								"		});");
					}
				}
				
			}else {
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Your Account is not active ',\r\n" + 
						"			text: 'Please Make a Deposite to Activate your Account, So You can Bid',\r\n" + 
						"			left:\"2%\"\r\n" + 
						"		});");
			}
		}else {
			
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Problem',\r\n" + 
					"			text: 'Please Login to be able to Bid',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
		}
			
		}

	}
	
	
public void calcValueOfTotalFeesCarSelected() {
		

		updateAllFees();
		selectedTansportFees=transportfeeFacade.getWithSpecs(selectedLocation, selectedCity, selectedState);

		if(selectedTansportFees!=null) {
			double price=valueOfCarBid;
			int level=getLevel(price);
			onlineFees=onlineBid[level];
			seaFees=0;
			if(carSize==0) {
				seaFees=selectedTansportFees.getLowCost();
			}else {
				seaFees=selectedTansportFees.getHighCost();
			}
			
			landFees=0;
			
			if(portType==0) {
				landFees=selectedTansportFees.getNjPortCost();
			}else if(portType==1) {
				landFees=selectedTansportFees.getGaPortCost();
			}else if(portType==2) {
				landFees=selectedTansportFees.getTxPortCost();
			}else if(portType==3) {
				landFees=selectedTansportFees.getCaPortCost();
			}

			totalFees=copartFees+onlineFees+GateFees+seaFees+landFees+ourFees;
			
		}
		
		
		
	}

	private void arrangeBidingForThisCar(carLanding selectedCarPage2) {
		//Get all maximum Bids for each car
		List<biding> allBidingMax = bidingFacade.getAllMaxCarBidingsAndType(biding.TYPE_BIDING);
		
		for(int i=0;i<allBidingMax.size();i++) {
			carLanding car=carLandingFacade.getById(allBidingMax.get(i).getCarlandingId().getId());

			System.out.println("Dakrory: "+car.getCurrentBid());
			boolean checkMoney = checkBidMoney(car.getCurrentBid(),allBidingMax.get(i).getFullAmount());
			System.out.println("DakroryNewAA: "+car.getCurrentBid());
			System.out.println("DakroryNEwRRR: "+allBidingMax.get(i).getFullAmount());
			
			if(checkMoney) {
				System.out.println("Dakrory11: "+allBidingMax.get(i).getUserId().getEmail());
				if(allowPersonToMakeBid(allBidingMax.get(i),car)) {
					System.out.println("Dakrory0: "+allBidingMax.get(i).getUserId().getEmail());
				
				biding theLastManWhoBidLessThanMe = bidingFacade.getByCarIdLessThanFullAmountAndType(allBidingMax.get(i).getCarlandingId().getId(), allBidingMax.get(i).getFullAmount(),biding.TYPE_BIDING);
				System.out.println("Dakrory883: "+allBidingMax.get(i).getUserId().getEmail());
				float theTotalForTheManWhoLastMe = 0;
				if(car.getCurrentBid()==null) {
					 theTotalForTheManWhoLastMe = 0;
				}else {
					theTotalForTheManWhoLastMe=Float.valueOf(car.getCurrentBid());
				}
				
				System.out.println("Dakrory09797: "+allBidingMax.get(i).getUserId().getEmail());
				
				if(theLastManWhoBidLessThanMe!=null) {

					 theTotalForTheManWhoLastMe=theLastManWhoBidLessThanMe.getFullAmount();
				}
				
				
				System.out.println("Dakrory2: "+allBidingMax.get(i).getUserId().getEmail());
				float theNewAmount = allBidingMax.get(i).getIncrement()+theTotalForTheManWhoLastMe;
				if(theNewAmount>allBidingMax.get(i).getFullAmount()) {
					theNewAmount = allBidingMax.get(i).getFullAmount();
				}
				System.out.println("Dakrory3: "+allBidingMax.get(i).getUserId().getEmail());
				car.setCurrentBid(String.valueOf(theNewAmount));
				car.setUserMaxBidId(allBidingMax.get(i).getUserId());
				int level = calcBean.getLevel(Float.valueOf(car.getCurrentBid()));
				float copartFees=(float) calcBean.CalculateCopart(level, Float.valueOf(car.getCurrentBid()));

				System.out.println("Dakrory4: "+allBidingMax.get(i).getUserId().getEmail());
				
				car.setCopartFees(String.valueOf(copartFees));
				car.setOurFees(String.valueOf((new calcBean()).getOurFees()));
				valueOfCarBid=Float.valueOf(car.getCurrentBid());
				System.out.println("Dakrory5: "+allBidingMax.get(i).getUserId().getEmail());
				carLandingFacade.addcarLanding(car);
				System.out.println("Dakrory6: "+allBidingMax.get(i).getUserId().getEmail());
				
				}
			}
		}
		updateAllFees();
	}

	
	private boolean checkBidMoney(String currentBid, float fullAmount) {
		// TODO Auto-generated method stub
		if(currentBid!=null) {
			if(Float.valueOf(currentBid)<fullAmount) {
				return true;
			}
		}else {
			return true;
		}
		return false;
	}


	public boolean allowPersonToMakeBid(biding biding,carLanding car) {
		
		
		if(car.getUserMaxBidId()==null) {
			return true;
		}
		
		if(car.getUserMaxBidId().getId()!=biding.getUserId().getId()) {
			return true;
		}
		
		return false;
		
	}
	
	public void reloadLiveBidingParamenters(boolean isLiveMode) {
		if(isLiveMode) {
			
			biding bidingMax = bidingFacade.getByCarIdandMaxAmountAndType(selectedCarPage.getId(),biding.TYPE_BIDING);
			if(bidingMax!=null) {
			Date timeNow = Calendar.getInstance().getTime();
			diffFromMaxToNow = timeNow.getTime()-bidingMax.getLastDateBid().getTime();
			System.out.println("Ahmed NowNow 1:"+String.valueOf(timeNow));
			long diffSecondsFromMaxToNow = diffFromMaxToNow / 1000 % 60;
			long diffMinutesFromMaxToNow = diffFromMaxToNow / (60 * 1000) % 60;
			
			timeInSecondsForLive = String.valueOf(timeLiveEnd-1 - diffMinutesFromMaxToNow)+" : "+String.valueOf(60 - diffSecondsFromMaxToNow);

			System.out.println("Ahmed DakroryNew: "+timeInSecondsForLive);
			System.out.println("Ahmed DakroryNew: "+diffMinutesFromMaxToNow);
			System.out.println("Ahmed DakroryNew: "+String.valueOf(timeLiveEnd-1 - diffMinutesFromMaxToNow));

			diffFromBidToEnd = selectedCarPage.getEndDate().getTime()+bias-timeNow.getTime();
			if(diffMinutesFromMaxToNow >= timeLiveEnd && diffFromBidToEnd<0 ) {
				selectedCarPage.setActive(false);
				selectedCarPage.setState(stateOfCar.ProcessState.getType());
				carLandingFacade.addcarLanding(selectedCarPage);
				if(selectedCarPage.getUserMaxBidId()!=null) {
					sendNotificationForUser(selectedCarPage.getUserMaxBidId().getId(),"You have win the Biding Waiting the Admin....","/pages/secured/normalUsers/vehicleList.jsf?faces-redirect=true");
					FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:buy-now-block");
					PrimeFaces.current().executeScript("window.location.reload(true);");
				}
			}
			
		}else {

			Date timeNow = Calendar.getInstance().getTime();
			diffFromBidToEnd = selectedCarPage.getEndDate().getTime()+bias-timeNow.getTime();
			
			long diffSecondsFromMaxToNow = diffFromBidToEnd / 1000 % 60;
			long diffMinutesFromMaxToNow = diffFromBidToEnd / (60 * 1000) % 60;
			
			timeInSecondsForLive = String.valueOf(timeLiveEnd-1 - diffMinutesFromMaxToNow)+" : "+String.valueOf(60 - diffSecondsFromMaxToNow);

			diffFromMaxToNow = (long) 0;
			if(diffFromBidToEnd<0) {
				selectedCarPage.setActive(false);
				selectedCarPage.setState(stateOfCar.ProcessState.getType());
				carLandingFacade.addcarLanding(selectedCarPage);
				if(selectedCarPage.getUserMaxBidId()!=null) {
					sendNotificationForUser(selectedCarPage.getUserMaxBidId().getId(),"You have win the Biding Waiting the Copart....","/pages/secured/normalUsers/vehicleList.jsf?faces-redirect=true");
		
				}
				}
			
		}
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:liveBidDisplay");
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("buy-now-block");
		}
		
	}
	
	public int bias = -(3600000+3360000+180000+60000);
	
	public void reloadedParametersAndPanelRefresh() {
		System.out.println(String.valueOf(selectedCarPage.getId()));
		selectedCarPage=carLandingFacade.getById(selectedCarPage.getId());
		Calendar nowTime=Calendar.getInstance();
		Date timeNow=new Date();
		timeNow.setTime(nowTime.getTimeInMillis());
		

		
		diffFromBidToEnd = selectedCarPage.getEndDate().getTime()+bias-timeNow.getTime();
		diffFromStartToBid = selectedCarPage.getBidingDate().getTime()+bias-timeNow.getTime();
		
		

		System.out.println("Ahmed NowNow 31:"+String.valueOf(nowTime.get(Calendar.HOUR_OF_DAY)));
		System.out.println("Ahmed NowNow 32:"+String.valueOf(nowTime));
		System.out.println("Ahmed NowNow 33:"+String.valueOf(timeNow));
		System.out.println("Ahmed NowNow 33:"+String.valueOf(diffFromBidToEnd));
		
		
		long diffSecondsFromBidToEnd = diffFromBidToEnd / 1000 % 60;
		long diffMinutesFromBidToEnd = diffFromBidToEnd / (60 * 1000) % 60;
		long diffHoursFromBidToEnd = diffFromBidToEnd / (60 * 60 * 1000) % 24;
		long diffDaysFromBidToEnd = diffFromBidToEnd / (24 * 60 * 60 * 1000);
		
		

		System.out.println("Ahmed NowNow 34:"+String.valueOf(diffHoursFromBidToEnd));
		
		
		long diffSecondsFromStartToBid = diffFromStartToBid / 1000 % 60;
		long diffMinutesFromStartToBid = diffFromStartToBid / (60 * 1000) % 60;
		long diffHoursFromStartToBid = diffFromStartToBid / (60 * 60 * 1000) % 24;
		long diffDaysFromStartToBid = diffFromStartToBid / (24 * 60 * 60 * 1000);
		
		System.out.println("Ahmed: "+timeNow);
		System.out.println("Ahmed: "+selectedCarPage.getEndDate());
		System.out.println("Ahmed: "+timeNow.getTime());
		System.out.println("Ahmed: "+selectedCarPage.getEndDate().getTime());

		System.out.println("AhmedTime: "+diffFromBidToEnd);
		
		
		if(selectedCarPage.isActive()) {
//			if(diffFromStartToBid<0) {
//				if(diffFromBidToEnd>0) {
					//Live Mode
					
					reloadLiveBidingParamenters(true);
//				}
//			}
		}
		
		
//		if(diffFromBidToEnd<0) {
//			selectedCarPage.setActive(false);
//			selectedCarPage.setState(stateOfCar.ProcessState.getType());
//			carLandingFacade.addcarLanding(selectedCarPage);
//			if(selectedCarPage.getUserMaxBidId()!=null) {
//				sendNotificationForUser(selectedCarPage.getUserMaxBidId().getId(),"You have win the Biding Waiting the Copart....","/pages/secured/normalUsers/vehicleList.jsf?faces-redirect=true");
//	
//			}
//			}
//		
		

		differenceTimeDateFromBidToEnd=String.valueOf(diffDaysFromBidToEnd)+"D: "+
				String.valueOf(diffHoursFromBidToEnd)+"H: "+
				String.valueOf(diffMinutesFromBidToEnd)+"M: "+
				String.valueOf(diffSecondsFromBidToEnd)+" S";
		
		differenceTimeDateFromStartToBid=String.valueOf(diffDaysFromStartToBid)+"D: "+
				String.valueOf(diffHoursFromStartToBid)+"H: "+
				String.valueOf(diffMinutesFromStartToBid)+"M: "+
				String.valueOf(diffSecondsFromStartToBid)+" S";
		
		if(diffFromBidToEnd<0) {
			differenceTimeDateFromBidToEnd="Biding Finished";
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:buy-now-block");
			
		}

		
		//Get the userBid if applicable
		if(loginBean.isLoggedIn()) {
			currentBiding=bidingFacade.getByCarIdAnduserIdAndType(selectedCarPage.getId(), loginBean.getTheUserOfThisAccount().getId(),biding.TYPE_BIDING);
			if(currentBiding!=null) {
				totalBid=currentBiding.getFullAmount();
//				incrementBid=currentBiding.getIncrement();
				incrementBid=25;
				currentBidingMaximum = bidingFacade.getByCarIdandMaxAmountAndType(selectedCarPage.getId() ,biding.TYPE_BIDING);
				
				if(currentBiding.getUserId().getId().equals(selectedCarPage.getUserMaxBidId().getId())) {
					showNotificationOfBidOutBid=false;
					showNotificationOfBidWinning=true;
				}else {
					showNotificationOfBidOutBid=true;
					showNotificationOfBidWinning=false;
				}
			}else {
				showNotificationOfBidOutBid=false;
				showNotificationOfBidWinning=false;
			}
		}else {
			showNotificationOfBidOutBid=false;
			showNotificationOfBidWinning=false;
		}
		
		refreshTheCurrentBidStatue();
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:differenceTime");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:currentPriceDisplay");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:CurrentPriceSmall");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:CurrentBidAmount");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:bidDetails");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("buy-now-block");
		
	}
	
	
	
	public void sendNotificationForCustomerCopartWinning(int id) {
		carLanding car = carLandingFacade.getById(id);
		
		sendNotificationForUser(car.getUserMaxBidId().getId(), "You have win From Copart", "/pages/secured/normalUsers/vehicleList.jsf?faces-redirect=true");
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'User Notification ',\r\n" + 
				"			text: 'User Has Been Notified',\r\n" + 
				"			left:\"2%\"\r\n" + 
				"		});");
	}
	
	
	
	public void buyCarNow() {
		System.out.println("Done");
			if(loginBean.getTheUserOfThisAccount().getId()!=null) {

				System.out.println("Done2");
			 loginBean.setThisAccountMoneyBox(loginBean.getMoneyboxDataFacede().getByUserId(loginBean.getTheUserOfThisAccount().getId()));

				if(loginBean.getThisAccountMoneyBox()!=null) {
			 if(loginBean.getThisAccountMoneyBox().isActive()) {
				//You can make a bid
				if(selectedCarPage.isActive()) {
					

					selectedCarPage.setActive(false);
					selectedCarPage.setUserMaxBidId(loginBean.getTheUserOfThisAccount());
					selectedCarPage.setCurrentBid(selectedCarPage.getBuyItNowPrice());
					selectedCarPage.setState(stateOfCar.ProcessState.getType());
					
					
					int level = calcBean.getLevel(Float.valueOf(selectedCarPage.getCurrentBid()));
					float copartFees=(float) calcBean.CalculateCopart(level, Float.valueOf(selectedCarPage.getCurrentBid()));
					
					
					selectedCarPage.setCopartFees(String.valueOf(copartFees));
					selectedCarPage.setOurFees(String.valueOf((new calcBean()).getOurFees()));
					
					
					carLandingFacade.addcarLanding(selectedCarPage);
			biding bid=new biding();
			bid.setFullAmount(Float.valueOf(selectedCarPage.getCurrentBid()));
			bid.setLastDateBid(new Date());
			bid.setIncrement(0);
			bid.setType(biding.TYPE_BUY_IT_NOW);
			bid.setUserId(loginBean.getTheUserOfThisAccount());
			bid.setCarlandingId(selectedCarPage);
			bidingFacade.addbiding(bid);
		
		sendNotificationForUser(selectedCarPage.getUserMaxBidId().getId(), "You have Buyed a Car From Copart", "/pages/secured/normalUsers/vehicleList.jsf?faces-redirect=true");
		PrimeFaces.current().executeScript(" swal(\"Car Has been Added\", {\n" + 
				"		      icon: \"success\",\n" + 
				"		    });");
				}

				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("differenceTimeForm:differenceTime");
				FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData");
			 }else {
					PrimeFaces.current().executeScript("new PNotify({\r\n" + 
							"			title: 'Your Account is not active ',\r\n" + 
							"			text: 'Please Make a Deposite to Activate your Account, So You can Bid',\r\n" + 
							"			left:\"2%\"\r\n" + 
							"		});");
				}
				}
			 }else {
				
				PrimeFaces.current().executeScript("new PNotify({\r\n" + 
						"			title: 'Problem',\r\n" + 
						"			text: 'Please Login to be able to Bid',\r\n" + 
						"			left:\"2%\"\r\n" + 
						"		});");
			}
	
			 
				
	}
	
	public void sendNotificationForUser(Integer id, String msg, String url) {
		// TODO Auto-generated method stub
		
		notification n1 = new notification();
		n1.setData(msg);
		n1.setDate(new Date());
		n1.setReaded(false);
		n1.setUrl(url);
		n1.setUserId(loginBean.getUserDataFacede().getById(id));
		notificationFacade.addnotification(n1);
		System.out.println("Done Ya Notification");
		
	}

	public void getTheAllListOfCarsWithDates() {
		listOfAddedCars=new ArrayList<carLanding>();
		listOfAddedCars = carLandingFacade.getAllBetweenDates(toCalendar(bidingDate), toCalendar(endDate));

	}
	
	public void getTheBidListOfCarsWithDates() {
		listOfAddedCars=new ArrayList<carLanding>();
		listOfAddedCars = carLandingFacade.getAllBidBetweenDates(toCalendar(bidingDate), toCalendar(endDate));

	}
	public void makeSearch() {
		//listOfAddedCars=carLandingFacade.getAllForSearch(searchStartYear, searchEndYear, searchMake,searchModel, searchType);

		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("panelCarsToUpdate");
	}
	
	public void makeSearchOutSide() {
		//listOfAddedCars=carLandingFacade.getAllForSearch(searchStartYear, searchEndYear, searchMake,searchModel, searchType);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/public/carsForType.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		}
	
	
	public void updateAllFees() {
		int level = calcBean.getLevel(valueOfCarBid);
		 copartFees = calcBean.CalculateCopart(level, valueOfCarBid);
		 ourFees= 100;
		 System.out.println("Ahmed Dakrory Done");
		 
		 
	}
	public void updateImagesWithLink(String lotImagesLink) {
		
	        try {
	        	images = new ArrayList<String>();
	        	String newLinkUrlWithSlach = lotImagesLink.substring(0, lotImagesLink.indexOf('?'))+"/"+lotImagesLink.substring( lotImagesLink.indexOf('?'),lotImagesLink.length());
	    		System.out.println(newLinkUrlWithSlach);
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
	
	public void updateTheLotImages(String lotImagesLink) {
		
		updateImagesWithLink(lotImagesLink);
	    FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
	    PrimeFaces.current().executeScript("hideDialog()");
	       
	
		
		
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
	
	public void theloaderFirst() {
		
		progress=true;
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:tableRendered");
		
	
}
	
	public void getCarWithVinNew() {
		if(!selectedFreight.getUuid().equals("")) {
			
		APIInterface apiInterface = APIClient.getClient(selectedFreight.getUuid()+"/").create(APIInterface.class);
		  Call<OrderOutDetails> call = apiInterface.performOrder();
	        try {
	        	OrderOutDetails car= call.execute().body();

	        	selectedFreight.setMake(car.Results.get(0).Make);
	        	selectedFreight.setModel(car.Results.get(0).Model);
	        	selectedFreight.setYear(car.Results.get(0).ModelYear);
	        	selectedFreight.setBodyStyle(car.Results.get(0).DriveType);
	        	selectedFreight.setFuel(car.Results.get(0).FuelTypePrimary);
	        	selectedFreight.setCylinder(car.Results.get(0).EngineConfiguration+"- "+car.Results.get(0).EngineCylinders+" Cylinders");


				progress=false;

	    		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:tableRendered");
	          	  
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				progress=false;

	    		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:tableRendered");
			}
		}else {
			progress=false;

    		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:tableRendered");
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Check this ',\r\n" + 
					"			text: 'Please enter the Vin number',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
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
	
	public void addCarForMain() {
			
			
			selectedFreight=new carLanding();
			
			images=new ArrayList<String>();
			
			try {
				FacesContext.getCurrentInstance()
				   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vitView.jsf?faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	public void decreaseBy10Dollars() {
		
		incrementBid-=10;
		if(incrementBid<0) {
			incrementBid=0;
		}
	}
	
	public void increaseBy10Dollars() {
		System.out.println(String.valueOf(incrementBid));
		incrementBid+=10;
		
	}
	
	public void decreaseBy100DollarsMain() {
		
		totalBid-=25;
		if(totalBid<0) {
			totalBid=0;
		}
	}
	
	public void increaseBy100DollarsMain() {
		System.out.println(String.valueOf(totalBid));
		totalBid+=25;
		
	}
	
	public void selectCarForMain(int selectedCarId) {
		
		PrimeFaces.current().executeScript("showDialog('car');");
		selectedFreight=carLandingFacade.getById(selectedCarId);
		updateImagesWithLink(selectedFreight.getAllImagesLink());

		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vitView.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void selectCarRowForMain(SelectEvent event) {
		selectedFreight = ((carLanding) event.getObject());
		System.out.println("Selected Id: "+selectedFreight.getLot());
		PrimeFaces.current().executeScript("showDialog('car');");
		updateImagesWithLink(selectedFreight.getAllImagesLink());

		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vitView.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void selectCarRowForUpload(SelectEvent event) {
		selectedFreight = ((carLanding) event.getObject());
		System.out.println("Selected Id: "+selectedFreight.getLot());
		PrimeFaces.current().executeScript("showDialog('car');");
		updateImagesWithLink(selectedFreight.getAllImagesLink());

		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vitViewUpload.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

	public boolean isDateValidated() {
		if(endDate==null || bidingDate==null || startDate==null) {
			return false;
		}
			return true;
		
	}
	
	 public void parseFile(FileUploadEvent event) {
	       //if(isDateValidated()) {
	       UploadedFile fileUploaded = event.getFile();
		 	try {
		 		if(fileUploaded!=null) {
		 		if(fileUploaded.getSize()!=0) {
		 			InputStream fileData =fileUploaded.getInputstream(); 
		 	       System.out.println("File streamed");
		 	     // PrimeFaces.current().executeScript("showDialog(' and saving Cars');PF('pbAjax').start();");
		 		 parseUsersFile(fileData);
		 		

	    		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:tableForm");

				 isfileUploaded = true;
					PrimeFaces.current().executeScript("swal(\"Action Done\", \"The Cars Has Been uploaded\", \"success\");");

					 PrimeFaces.current().executeScript("hideDialog()");
					 
					
		 		}
		 		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				 PrimeFaces.current().executeScript("hideDialog()");
			}catch (Error e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				 PrimeFaces.current().executeScript("hideDialog()");
			}
	       }
	 
	 /*else {
				 PrimeFaces.current().executeScript("hideDialog()");
		 	PrimeFaces.current().executeScript("swal({\n" + 
		 			"    								  title: \"Problem\",\n" + 
		 			"    								  text: \"Please Fill the Time for start and the end!\",\n" + 
		 			"    								  icon: \"warning\",\n" + 
		 			"    								  dangerMode: true,\n" + 
		 			"    								}).then((willDelete) => {\n" + 
		 			"    									  if (willDelete) {\n" + 
		 			"    										  location.reload()\n" + 
		 			"    										  } \n" + 
		 			"    										});");
	       }
	    }*/
	 
	 public String getTheValueFromCell(Cell cell) {
		 String returnedValue="";
		 switch (cell.getCellType()) {
		 case _NONE:
        	 returnedValue = "";
             break;
		 case BLANK:
        	 returnedValue = "";
             break;
         case STRING:
        	 returnedValue = String.valueOf(cell.getStringCellValue());
             break;
         case NUMERIC:
        	 Long number = (long) cell.getNumericCellValue();
        	 returnedValue = String.valueOf(number);
             break;
         case BOOLEAN:
        	 returnedValue = String.valueOf(cell.getBooleanCellValue());
             break;
         
         default :

         }
		 return returnedValue;
	 }
	 
	 //-2-3
	 
//	 public TimeZone getTypeOfTime(String type) {
//			if(type.equalsIgnoreCase("CDT")) {//
//				return TimeZone.getTimeZone("GMT-10:00");
//			}else if(type.equalsIgnoreCase("ADT")) {//
//				return TimeZone.getTimeZone("GMT-08:00");
//			}else if(type.equalsIgnoreCase("PDT")) {//
//				return TimeZone.getTimeZone("GMT-12:00");
//			}else if(type.equalsIgnoreCase("EDT")) {//
//				return TimeZone.getTimeZone("GMT-07:00");
//			}else if(type.equalsIgnoreCase("AKDT")) {
//				return TimeZone.getTimeZone("GMT-13:00");
//			}else if(type.equalsIgnoreCase("HST")) {
//				return TimeZone.getTimeZone("GMT-15:00");
//			}else if(type.equalsIgnoreCase("MDT")) {
//				TimeZone.getTimeZone("GMT-11:00");
//			}else if(type.equalsIgnoreCase("PST")) {
//				return TimeZone.getTimeZone("GMT-12:00");
//			}else{
//				return TimeZone.getDefault();
//			}
//			return TimeZone.getDefault();
//		}
	 
	 
	 public TimeZone getTypeOfTime(String type) {
			if(type.equalsIgnoreCase("CDT")) {//
				return TimeZone.getTimeZone("GMT-8:00");
			}else if(type.equalsIgnoreCase("ADT")) {//
				return TimeZone.getTimeZone("GMT-06:00");
			}else if(type.equalsIgnoreCase("PDT")) {//
				return TimeZone.getTimeZone("GMT-10:00");
			}else if(type.equalsIgnoreCase("EDT")) {//
				return TimeZone.getTimeZone("GMT-07:00");
			}else if(type.equalsIgnoreCase("AKDT")) {
				return TimeZone.getTimeZone("GMT-11:00");
			}else if(type.equalsIgnoreCase("HST")) {
				return TimeZone.getTimeZone("GMT-13:00");
			}else if(type.equalsIgnoreCase("MDT")) {
				TimeZone.getTimeZone("GMT-10:00");
			}else if(type.equalsIgnoreCase("PST")) {
				return TimeZone.getTimeZone("GMT-10:00");
			}else{
				return TimeZone.getDefault();
			}
			return TimeZone.getDefault();
		}
	 
	 
	 public void parseUsersFile(InputStream input) {
			try {
				//inputStream = resource.getInputStream();
				// Create Workbook instance holding reference to .xlsx file
				Workbook  workbook = WorkbookFactory.create(input);

		 	       System.out.println("workBook");
				// Get first/desired sheet from the workbook
		 	      Sheet sheet = workbook.getSheetAt(0);
				int rowsNumbers = sheet.getLastRowNum();
		 	       System.out.println("Sheet");

					int listNumTotal = rowsNumbers;
		 	       for(int i=1;i<rowsNumbers+1;i++) {
					Row row = sheet.getRow(i);
					// For each row, iterate through all the columns
					carLanding data=new carLanding();
					String timezone = "";
					String dateMain = "";
					String timeMain = "";
					for (int count=0;count<row.getLastCellNum();count++) {
						Cell cell = row.getCell(count);
						
						int withNewCount = count+1;
					
	                  switch(withNewCount) {
	                  
	                  case 3:
		                	try {
								data.setSaleName(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
		                
		                	  
	                  case 4:
		                	try {
		                		
								dateMain = getTheValueFromCell(cell);
							} catch (Exception ex) { //
								
							}
		                	  break;
		                	  
	                  case 6:
		                	try {
		                		
								timeMain = getTheValueFromCell(cell);
							} catch (Exception ex) { //
								
							}
		                	  break;
		                	  
	                  case 7:
		                	try {
		                		
								timezone = getTheValueFromCell(cell);
							} catch (Exception ex) { //
								
							}
		                	  break;
		                	  
		                	  
		                case 8:
		                	try {
		                		
								data.setItemNumber(getTheValueFromCell(cell));
							} catch (Exception ex) { //
								
							}
		                	  break;
		                	  
		                case 9:
		                	try {
								data.setLot(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
		                case 10:
		                	try {
		                		String valueOfType= getTheValueFromCell(cell);
		                		
		                		if(valueOfType.equalsIgnoreCase("U")) {
									data.setCategory("Heavy Duties");
		                		}else if(valueOfType.equalsIgnoreCase("V")) {
									data.setCategory("SMALL CARS");
		                		}else if(valueOfType.equalsIgnoreCase("C")) {
									data.setCategory("Motor Cycle");
		                		}else if(valueOfType.equalsIgnoreCase("S")) {
									data.setCategory("Snow Mobile");
		                		}else if(valueOfType.equalsIgnoreCase("M")||valueOfType.equalsIgnoreCase("J")) {
									data.setCategory("JetSkies");
		                		}else if(valueOfType.equalsIgnoreCase("Q")) {
									data.setCategory("Korean");
		                		}else{
									data.setCategory("SUV");
								}
		                		
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 11:
							try {
								data.setYear(getTheValueFromCell(cell));
							}catch (Exception ex) { 
								//
							}
		                	  break;
						case 12:
							try {
								data.setMake(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
						case 14:
							try {
								data.setModel(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
						case 15:
							try {

								data.setBodyStyle(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
						case 16:
							try {
								data.setColor(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
						case 17:
							try {
								data.setDamageDescription(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
						case 18:
							try {
								data.setSecondaryDamage(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 19:
							try {
								data.setDocState(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 20:
							try {
								String docField = getTheValueFromCell(cell);
								
								data.setDocType(allPapers.get(docField));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 21:
							try {
								data.setKeyExist(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
							  
						case 23:
							try {
								data.setUuid(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 24:
							try {
								data.setOdoMeter(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
		                	  
						case 25:
							try {
								data.setOdoDescription(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 26:
							try {
								data.setEstRetailValue(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 27:
							try {
								data.setRepairEstimate(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;

						case 28:
							try {
								data.setEngineType(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 30:
							try {
								data.setTransmission(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 31:
							try {
								data.setFuel(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 32:
							try {
								data.setCylinder(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
		               
						case 33:
							try {
								data.setRunsDrives(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
		                	  
						case 37:
							try {
								data.setAuctionLocation(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
		                	  
						case 38:
							try {
								String state = getTheValueFromCell(cell);
								data.setAuctionLocation(state+"-"+data.getAuctionLocation());
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
		                	  
						case 42:
							try {
								data.setMainImage("http://"+getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 44:
							try {
								data.setGridRow(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 45:
							try {
								String b =getTheValueFromCell(cell);
								if(b.equalsIgnoreCase("Y")) {
									data.setActivebuyItNow(true);
								}else {
									data.setActivebuyItNow(false);
									
								}
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
		                	  
						case 46:
							try {
								
								if(!getTheValueFromCell(cell).equals("0")) {
									data.setActivebuyItNow(true);
								}
								data.setBuyItNowPrice(getTheValueFromCell(cell));
								int level = calcBean.getLevel(Float.valueOf(getTheValueFromCell(cell)));
								float copartFees=(float) calcBean.CalculateCopart(level, Float.valueOf(getTheValueFromCell(cell)));
								
								
								data.setCopartFees(String.valueOf(copartFees));
								data.setOurFees(String.valueOf((new calcBean()).getOurFees()));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
						case 47:
							try {
								data.setAllImagesLink(getTheValueFromCell(cell));
							}catch (Exception ex) { //
							}
		                	  break;
		                	  
							
	                  }
						
							
				
						
					
				
					}
					
					if(!timeMain.equalsIgnoreCase("") && !dateMain.equalsIgnoreCase("") && ! timezone.equalsIgnoreCase("")) {
						String timeFormat = dateMain+timeMain;
//						System.out.println(data.getUuid()+"  :  "+timeFormat+":"+timezone);
						SimpleDateFormat isoFormat = new SimpleDateFormat("yyyyMMddHHmm");
//						TimeZone.setDefault(TimeZone.getTimeZone("GMT+02:00"));
						
						isoFormat.setTimeZone(getTypeOfTime(timezone));
						Date date = isoFormat.parse(timeFormat);
						Calendar calbiding=Calendar.getInstance();
						Calendar calEnd=Calendar.getInstance();

						
						calbiding.setTime(date);
						calbiding.set(Calendar.HOUR_OF_DAY, calbiding.get(Calendar.HOUR_OF_DAY)-6);
						
						
						calEnd.setTime(date);
						calEnd.set(Calendar.HOUR_OF_DAY, calEnd.get(Calendar.HOUR_OF_DAY)-3);
						

						data.setStartDate(new Date());
						data.setBidingDate(calbiding.getTime());
						data.setEndDate(calEnd.getTime());
						
						data.setAuctionType(carLanding.AUTCION_COPART);
						data.setDeleted(false);
						data.setPaymentDone(false);
						data.setActive(true);
						data.setMainId(loginBean.getTheUserOfThisAccount());
						data.setShowenInLanding(false);
						data.setState(stateOfCar.BidingState.getType());
						
						
							carLandingFacade.addcarLanding(data);
					}

					
						double newPercent = ((100 *(i-listNumTotal))/(listNumTotal-1))+100; 

						progressLoading = (int) + newPercent; 

						System.out.println("Data: Done: "+i+" , "+String.valueOf(progressLoading));
					
					
				}
				input.close();
			
			 
		
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	 
	 
//	public void saveTheNewUploadedList() {
//		System.out.println("Data: Saving");
//		int listNumTotal = listOfUploadedDataCars.size();
//		for(int i=0;i<listOfUploadedDataCars.size();i++) {
//			carLandingFacade.addcarLanding(listOfUploadedDataCars.get(i));
//			double newPercent = ((100 *(i-listNumTotal))/(listNumTotal-1))+100; 
//
//			progressLoading = (int) + newPercent; 
//
//			System.out.println("Data: Done: "+i+" , "+String.valueOf(progressLoading));
//		}
//		try {
//			FacesContext.getCurrentInstance()
//			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleList.jsf?faces-redirect=true");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Error: "+e.toString());
//		}
//	}
	public void updateCarDataMain() {
		selectedFreight.setMainId(loginBean.getTheUserOfThisAccount());
		carLandingFacade.addcarLanding(selectedFreight);
		
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleList.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateCarDataUpload() {
		selectedFreight.setMainId(loginBean.getTheUserOfThisAccount());
		
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleListUpload.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goToCarDataUpload() {
		listOfUploadedDataCars=new ArrayList<carLanding>();
		endDate=new Date();
		bidingDate=new Date();
		startDate=new Date();
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleListUpload.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cancel() {
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleList.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void cancel2() {
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleListUpload.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public main.com.carService.loginNeeds.loginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(main.com.carService.loginNeeds.loginBean loginBean) {
		this.loginBean = loginBean;
	}

	public carLandingAppServiceImpl getCarLandingFacade() {
		return carLandingFacade;
	}

	public void setCarLandingFacade(carLandingAppServiceImpl carLandingFacade) {
		this.carLandingFacade = carLandingFacade;
	}

	public List<carLanding> getListOfAddedCars() {
		return listOfAddedCars;
	}

	public void setListOfAddedCars(List<carLanding> listOfAddedCars) {
		this.listOfAddedCars = listOfAddedCars;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public carLanding getSelectedFreight() {
		return selectedFreight;
	}


	public void setSelectedFreight(carLanding selectedFreight) {
		this.selectedFreight = selectedFreight;
	}


	public boolean isProgress() {
		return progress;
	}


	public void setProgress(boolean progress) {
		this.progress = progress;
	}

	

	
	public String getDifferenceTimeDateFromBidToEnd() {
		return differenceTimeDateFromBidToEnd;
	}

	public void setDifferenceTimeDateFromBidToEnd(String differenceTimeDateFromBidToEnd) {
		this.differenceTimeDateFromBidToEnd = differenceTimeDateFromBidToEnd;
	}

	public String getDifferenceTimeDateFromStartToBid() {
		return differenceTimeDateFromStartToBid;
	}

	public void setDifferenceTimeDateFromStartToBid(String differenceTimeDateFromStartToBid) {
		this.differenceTimeDateFromStartToBid = differenceTimeDateFromStartToBid;
	}

	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	

	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getBidingDate() {
		return bidingDate;
	}


	public void setBidingDate(Date bidingDate) {
		this.bidingDate = bidingDate;
	}


	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}



	public List<carLanding> getListOfCarsLandingScroller() {
		return listOfCarsLandingScroller;
	}


	public void setListOfCarsLandingScroller(List<carLanding> listOfCarsLandingScroller) {
		this.listOfCarsLandingScroller = listOfCarsLandingScroller;
	}


	

	public carLanding getSelectedCarPage() {
		return selectedCarPage;
	}


	public void setSelectedCarPage(carLanding selectedCarPage) {
		this.selectedCarPage = selectedCarPage;
	}





	public List<carLanding> getListOfCarsGroupByMake() {
		return listOfCarsGroupByMake;
	}


	public void setListOfCarsGroupByMake(List<carLanding> listOfCarsGroupByMake) {
		this.listOfCarsGroupByMake = listOfCarsGroupByMake;
	}


	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getSearchMake() {
		return searchMake;
	}


	public void setSearchMake(String searchMake) {
		this.searchMake = searchMake;
	}


	public String getSearchStartYear() {
		return searchStartYear;
	}


	public void setSearchStartYear(String searchStartYear) {
		this.searchStartYear = searchStartYear;
	}


	public String getSearchEndYear() {
		return searchEndYear;
	}


	public void setSearchEndYear(String searchEndYear) {
		this.searchEndYear = searchEndYear;
	}


	public List<carLanding> getListOfUploadedDataCars() {
		return listOfUploadedDataCars;
	}


	public void setListOfUploadedDataCars(List<carLanding> listOfUploadedDataCars) {
		this.listOfUploadedDataCars = listOfUploadedDataCars;
	}


	public UploadedFile getFileExcel() {
		return fileExcel;
	}


	public void setFileExcel(UploadedFile fileExcel) {
		this.fileExcel = fileExcel;
	}


	public List<carLanding> getListOfFilteredCars() {
		return listOfFilteredCars;
	}

	public void setListOfFilteredCars(List<carLanding> listOfFilteredCars) {
		this.listOfFilteredCars = listOfFilteredCars;
	}

	
	public float getIncrementBid() {
		return incrementBid;
	}

	public notificationAppServiceImpl getNotificationFacade() {
		return notificationFacade;
	}

	public void setNotificationFacade(notificationAppServiceImpl notificationFacade) {
		this.notificationFacade = notificationFacade;
	}


	

	public List<carLanding> getAllCarsString() {
		return allCarsString;
	}

	public void setAllCarsString(List<carLanding> allCarsString) {
		this.allCarsString = allCarsString;
	}

	public String getSelectedCarSearch() {
		return selectedCarSearch;
	}

	public void setSelectedCarSearch(String selectedCarSearch) {
		this.selectedCarSearch = selectedCarSearch;
	}

	public bidingAppServiceImpl getBidingFacade() {
		return bidingFacade;
	}

	public void setBidingFacade(bidingAppServiceImpl bidingFacade) {
		this.bidingFacade = bidingFacade;
	}

	public float getTotalBid() {
		return totalBid;
	}

	public void setTotalBid(float totalBid) {
		this.totalBid = totalBid;
	}

	public void setIncrementBid(float incrementBid) {
		this.incrementBid = incrementBid;
	}

	public biding getCurrentBiding() {
		return currentBiding;
	}

	public void setCurrentBiding(biding currentBiding) {
		this.currentBiding = currentBiding;
	}

	public Long getDiffFromBidToEnd() {
		return diffFromBidToEnd;
	}

	public void setDiffFromBidToEnd(Long diffFromBidToEnd) {
		this.diffFromBidToEnd = diffFromBidToEnd;
	}

	public Long getDiffFromStartToBid() {
		return diffFromStartToBid;
	}

	public void setDiffFromStartToBid(Long diffFromStartToBid) {
		this.diffFromStartToBid = diffFromStartToBid;
	}

	public mycarsAppServiceImpl getMycarsFacade() {
		return mycarsFacade;
	}

	public void setMycarsFacade(mycarsAppServiceImpl mycarsFacade) {
		this.mycarsFacade = mycarsFacade;
	}

	public List<biding> getAllCurrentBidCars() {
		return allCurrentBidCars;
	}

	public void setAllCurrentBidCars(List<biding> allCurrentBidCars) {
		this.allCurrentBidCars = allCurrentBidCars;
	}

	public List<mycars> getAllCarsWatchList() {
		return allCarsWatchList;
	}

	public void setAllCarsWatchList(List<mycars> allCarsWatchList) {
		this.allCarsWatchList = allCarsWatchList;
	}

	public List<mycars> getAllCarsFavorits() {
		return allCarsFavorits;
	}

	public void setAllCarsFavorits(List<mycars> allCarsFavorits) {
		this.allCarsFavorits = allCarsFavorits;
	}

	public bidBean getBidBean() {
		return bidBean;
	}

	public void setBidBean(bidBean bidBean) {
		this.bidBean = bidBean;
	}

	public List<mycars> getListOfFilteredCars2() {
		return listOfFilteredCars2;
	}

	public void setListOfFilteredCars2(List<mycars> listOfFilteredCars2) {
		this.listOfFilteredCars2 = listOfFilteredCars2;
	}

	public mycars getSelectedFreight2() {
		return selectedFreight2;
	}

	public void setSelectedFreight2(mycars selectedFreight2) {
		this.selectedFreight2 = selectedFreight2;
	}

	public biding getSelectedFreight3() {
		return selectedFreight3;
	}

	public void setSelectedFreight3(biding selectedFreight3) {
		this.selectedFreight3 = selectedFreight3;
	}

	public List<biding> getListOfFilteredCars3() {
		return listOfFilteredCars3;
	}

	public void setListOfFilteredCars3(List<biding> listOfFilteredCars3) {
		this.listOfFilteredCars3 = listOfFilteredCars3;
	}

	

	public int getCarViewId() {
		return carViewId;
	}

	public void setCarViewId(int carViewId) {
		this.carViewId = carViewId;
	}

	public boolean isCarInWatchList() {
		return carInWatchList;
	}

	public void setCarInWatchList(boolean carInWatchList) {
		this.carInWatchList = carInWatchList;
	}

	public String getCurrentBidStateDetails() {
		return currentBidStateDetails;
	}

	public void setCurrentBidStateDetails(String currentBidStateDetails) {
		this.currentBidStateDetails = currentBidStateDetails;
	}

	public float getValueOfCarBid() {
		return valueOfCarBid;
	}

	public void setValueOfCarBid(float valueOfCarBid) {
		this.valueOfCarBid = valueOfCarBid;
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

	public String getTimeInSecondsForLive() {
		return timeInSecondsForLive;
	}

	public void setTimeInSecondsForLive(String timeInSecondsForLive) {
		this.timeInSecondsForLive = timeInSecondsForLive;
	}

	public double getTimeLiveEnd() {
		return timeLiveEnd;
	}

	public void setTimeLiveEnd(double timeLiveEnd) {
		this.timeLiveEnd = timeLiveEnd;
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


	public transportfee getSelectedTansportFees() {
		return selectedTansportFees;
	}


	public void setSelectedTansportFees(transportfee selectedTansportFees) {
		this.selectedTansportFees = selectedTansportFees;
	}


	public String getSelectedLocation() {
		return selectedLocation;
	}


	public void setSelectedLocation(String selectedLocation) {
		this.selectedLocation = selectedLocation;
	}


	public String getSelectedCity() {
		return selectedCity;
	}


	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}


	public String getSelectedState() {
		return selectedState;
	}


	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}


	public int getCarSize() {
		return carSize;
	}


	public void setCarSize(int carSize) {
		this.carSize = carSize;
	}


	public String getCarPrice() {
		return carPrice;
	}


	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}


	public int getPortType() {
		return portType;
	}


	public void setPortType(int portType) {
		this.portType = portType;
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


	public static double[] getOnlineBid() {
		return onlineBid;
	}


	public static void setOnlineBid(double[] onlineBid) {
		carLandingBean.onlineBid = onlineBid;
	}


	public boolean isIsfileUploaded() {
		return isfileUploaded;
	}


	public void setIsfileUploaded(boolean isfileUploaded) {
		this.isfileUploaded = isfileUploaded;
	}


	public String getSearchModel() {
		return searchModel;
	}


	public void setSearchModel(String searchModel) {
		this.searchModel = searchModel;
	}


	public carlandingimageAppServiceImpl getCarlandingimageFacade() {
		return carlandingimageFacade;
	}


	public void setCarlandingimageFacade(carlandingimageAppServiceImpl carlandingimageFacade) {
		this.carlandingimageFacade = carlandingimageFacade;
	}


	public Map<Integer, String> getAllLanding() {
		return allLanding;
	}


	public void setAllLanding(Map<Integer, String> allLanding) {
		this.allLanding = allLanding;
	}


	public Long getDiffFromMaxToNow() {
		return diffFromMaxToNow;
	}


	public void setDiffFromMaxToNow(Long diffFromMaxToNow) {
		this.diffFromMaxToNow = diffFromMaxToNow;
	}


	public boolean isShowCurrentStatues() {
		return showCurrentStatues;
	}


	public void setShowCurrentStatues(boolean showCurrentStatues) {
		this.showCurrentStatues = showCurrentStatues;
	}


	public biding getCurrentBidingMaximum() {
		return currentBidingMaximum;
	}


	public void setCurrentBidingMaximum(biding currentBidingMaximum) {
		this.currentBidingMaximum = currentBidingMaximum;
	}


	public int getBias() {
		return bias;
	}


	public void setBias(int bias) {
		this.bias = bias;
	}


	public boolean isShowNotificationOfBidWinning() {
		return showNotificationOfBidWinning;
	}


	public void setShowNotificationOfBidWinning(boolean showNotificationOfBidWinning) {
		this.showNotificationOfBidWinning = showNotificationOfBidWinning;
	}


	public boolean isShowNotificationOfBidOutBid() {
		return showNotificationOfBidOutBid;
	}


	public void setShowNotificationOfBidOutBid(boolean showNotificationOfBidOutBid) {
		this.showNotificationOfBidOutBid = showNotificationOfBidOutBid;
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

	
	
	
	
	
	public HashMap<String, String> getAllPapers() {
		return allPapers;
	}


	public void setAllPapers(HashMap<String, String> allPapers) {
		this.allPapers = allPapers;
	}


	
	
	public HashMap<String, Integer> getAllPapersCodes() {
		return allPapersCodes;
	}


	public void setAllPapersCodes(HashMap<String, Integer> allPapersCodes) {
		this.allPapersCodes = allPapersCodes;
	}


	public void fillAllPapers() {
		allPapers.put("1R","BOS - NONREPAIRABLE");
		allPapers.put("1S","BOS - ACTIVE");
		allPapers.put("AC","ACQ BOS - POST SALE - SALVAGE");
		allPapers.put("AD","ABANDONMENT-CERT OF SALES");
		allPapers.put("AL","AFFIDAVIT IN LIEU OF TITLE");
		allPapers.put("AM","AUCUNE MENTION");
		allPapers.put("AN","NON-REVIVABLE ACQUISITION");
		allPapers.put("AQ","SALVAGE ACQUISITION BILLOFSALE");
		allPapers.put("AR","AUCTION SALES RECEIPT - NONREP");
		allPapers.put("AT","AFF IN LIEU OF TITLE - REBUILT");
		allPapers.put("B1","DERELICT BOS/PARTS ONLY");
		allPapers.put("B2","CERT OF TITLE-MANUFCTR BUYBACK");
		allPapers.put("B3","CERT OF TTL-SLVG MANUFCTR BYBK");
		allPapers.put("BA","BILL OF SALE - ABANDONED");
		allPapers.put("BB","BOAT CERT OF TITLE-SALVAGE");
		allPapers.put("BD","BILL OF SALE - DESTRUCTION");
		allPapers.put("BE","CERT OF TITLE-MANUF BUY BACK");
		allPapers.put("BF","CERT OF SALVAGE-W=FLOOD VEH");
		allPapers.put("BI","BILL OF SALE");
		allPapers.put("BJ","BILL OF SALE-PARTS ONLY/SCRAP");
		allPapers.put("BL","BOS - REBUILT");
		allPapers.put("BM","BOAT MOTOR TITLE");
		allPapers.put("BP","BILL OF SALE - PARTS ONLY");
		allPapers.put("BR","BOS - NONREPAIRABLE");
		allPapers.put("BS","BILL OF SALE");
		allPapers.put("BT","CERT OF TITLE TO A BOAT");
		allPapers.put("BV","BOS - SALVAGE");
		allPapers.put("C0","CT CLEAN TITLE PUBLIC ELIG");
		allPapers.put("C4","CERT OF TITLE-SALVAGE COURT OR");
		allPapers.put("CB","CERT OF SALV-R=RECONSTRUCTED");
		allPapers.put("CC","CERT OF TITLE-COURT ORDER");
		allPapers.put("CD","CERTIFICATE OF DESTRUCTION");
		allPapers.put("CE","CLEAN TITLE-OVER 25% DAMAGE");
		allPapers.put("CF","DLR ONLY CERT OF DESTRUCTION");
		allPapers.put("CH","CLEAN TITLE WITH THEFT HISTORY");
		allPapers.put("CI","CERTIFICAT-VEH. A INSPECTER");
		allPapers.put("CJ","CERT TO OBTAIN TTL-NON-HIGHWAY");
		allPapers.put("CL","CERT OF TITLE W/LEMON LAW HIST");
		allPapers.put("CO","CERTIFICATE OF ORIGIN");
		allPapers.put("CP","CERT OF TITLE-SALVAGE HISTORY");
		allPapers.put("CQ","CERT OF TITLE OR SALVAGE ACQ");
		allPapers.put("CR","CERT OF TITLE-CORRECTED");
		allPapers.put("CS","CERT OF TITLE FOR A SNOWMOBILE");
		allPapers.put("CT","CERTIFICATE OF TITLE");
		allPapers.put("CV","VESSEL-CERTIFICATE OF TITLE");
		allPapers.put("CW","CERT OF TITLE-RECOVERED THEFT");
		allPapers.put("CY","CLEAN TITLE EXPORT ONLY");
		allPapers.put("CZ","DEALER ONLY CLEAN TITLE");
		allPapers.put("D1","DLR ONLY SALVAGE CERTIFICATE");
		allPapers.put("D2","NC DLR ONLY SLVG REBUILT");
		allPapers.put("DC","DLR ONLY-CLEAN/OVER 25% DAMAGE");
		allPapers.put("DE","DLR ONLY - CLN TTL EXCEEDS 25%");
		allPapers.put("DL","DIS/DLR/EXP ONLY LIEN PAPERS");
		allPapers.put("DM","PERMIT TO DISMANTLE");
		allPapers.put("DN","DNR REGISTRATION");
		allPapers.put("DO","DLR ONLY, CLEAN W/ SALV HIST");
		allPapers.put("DP","DUPLICATE TITLE");
		allPapers.put("DQ","APP FOR DUP CLEAN OR AC");
		allPapers.put("DS","DLR ONLY SALVAGE TITLE");
		allPapers.put("DT","APP FOR DUP SALVAGE OR AC");
		allPapers.put("DU","MV907A W/UNSATISFIED LIEN");
		allPapers.put("DV","DIS/DLR/EXP ONLY CLEAN TITLE");
		allPapers.put("DY","DIS/DLR/EXP ONLY CLEAN TITLE");
		allPapers.put("DZ","DIS/DLR/EXP ONLY SALVAGE CERT");
		allPapers.put("EC","ELECTRONIC CERT OF TITLE");
		allPapers.put("EN","CERT OF TITLE-STOLEN");
		allPapers.put("EO","EXPORT ONLY");
		allPapers.put("ET","ELECTRONIC CERT OF TITLE");
		allPapers.put("EU","E-TITLE STOLEN/UNRECOVERED VEH");
		allPapers.put("F1","SCRAP CERT OF TITLE-FLOOD VEH");
		allPapers.put("FC","CERT OF TITLE-FLOOD VEHICLE");
		allPapers.put("FJ","FBI JUNK");
		allPapers.put("FL","CERT OF TITLE-FLOOD DAMAGED");
		allPapers.put("FN","NON-REPAIRABLE CERT-FLOOD");
		allPapers.put("FR","SALVAGE/FIRE DAMAGE");
		allPapers.put("FS","CERT OF TITLE-SALVAGE FIRE");
		allPapers.put("FT","CERT OF TITLE-SALVAGE FLOOD");
		allPapers.put("FV","SALVAGE TITLE-FLOOD DAMAGE");
		allPapers.put("GM","FOREIGN OWNERSHIP DOCUMENTS");
		allPapers.put("GP","GOVERNMENT PARTS ONLY-SALVAGE");
		allPapers.put("GS","GOVERNMENT SALVAGE");
		allPapers.put("GT","GOVERNMENT TITLE");
		allPapers.put("HD","CERT OF TITLE-HAIL DAMAGED");
		allPapers.put("HS","CERT OF TITLE - HAIL SALVAGE");
		allPapers.put("IC","CERT OF TITLE-CLAIM PAID");
		allPapers.put("IL","CERT OF TITLE-FLOOD/REBUILT");
		allPapers.put("IP","IRRECUPERABLE");
		allPapers.put("IR","PERMIT IRREPAIRABLE");
		allPapers.put("JB","SCRAP-BILL OF SALE");
		allPapers.put("JC","JUNKING CERTIFICATE");
		allPapers.put("JK","CERTIFICATE OF TITLE - JUNK");
		allPapers.put("JN","JUNKING CERTIFICATE");
		allPapers.put("JR","JUNK RECEIPT");
		allPapers.put("JT","CERTIFICATE OF TITLE-JUNKED");
		allPapers.put("KL","CASH FOR CLUNKERS");
		allPapers.put("KR","CERT OF TITLE - REBUILT - RED");
		allPapers.put("KV","NONREPAIRABLE TITLE-FLOOD");
		allPapers.put("LB","DIS/DLR/EXP ONLY LEMON LAW");
		allPapers.put("LD","LETTER OF DESTRUCTION");
		allPapers.put("LH","CERT OF TITLE-LEMON LAW HISTOR");
		allPapers.put("LL","CERT OF TITLE-LEMON LAW BUYBAC");
		allPapers.put("LP","T-42 POSSESSORY LIEN SALE");
		allPapers.put("LQ","LIEN PAPERS-CLEAN OR AC");
		allPapers.put("LR","CERT OF SLVG-LEMON LAW REPURCH");
		allPapers.put("LS","DIS/DLR/EXP LIEN PAPERS-SALV");
		allPapers.put("LU","LIEN PAPERS-SALVAGE OR AC");
		allPapers.put("MB","CERT OF TITLE-MANUFACTURER BUY");
		allPapers.put("MC","CLEAN - MULTIPLE BRANDS");
		allPapers.put("MD","DLR ONLY MUNICIPAL CERT OF TTL");
		allPapers.put("MF","MV-51");
		allPapers.put("ML","LIEN PAPERS");
		allPapers.put("MS","MV-51B/STMT OF OWNERSHIP");
		allPapers.put("MT","MARINE CERTIFICATE OF TITLE");
		allPapers.put("MU","CERT OF TITLE-MUNICIPAL");
		allPapers.put("NA","NOT ACTUAL MILES");
		allPapers.put("NB","NON-REB & REM SALVAGE TITLE");
		allPapers.put("NC","NONREPAIR-CERT OF AUTHORITY");
		allPapers.put("NF","PERMIT NO BRAND - FIT");
		allPapers.put("NH","CERT OF TITLE-NON HIGHWAY");
		allPapers.put("NL","VEHICLE PERMIT");
		allPapers.put("NP","MV-907A - NO PUBLIC VIN");
		allPapers.put("NQ","DEALER ONLY NON-REPAIRABLE");
		allPapers.put("NR","NON-REPAIRABLE CERTIFICATE");
		allPapers.put("NS","CERT OF TITLE-SALVAGE NON-REM");
		allPapers.put("NT","PARTS ONLY-NO TITLE LETTER");
		allPapers.put("NU","PERMIT NO BRAND - UNFIT");
		allPapers.put("NX","NONREPAIRABLE TITLE");
		allPapers.put("OS","CERT OF TITLE-OHV SALVAGE");
		allPapers.put("OT","SALVAGE CERTIFICATE");
		allPapers.put("PC","CERT OF TITLE-POLICE");
		allPapers.put("PF","PREVIOUS FLOOD CLEAN");
		allPapers.put("PL","MV907A-PARTS ONLY W/LIENHOLDER");
		allPapers.put("PN","PUBLIC - NONREPAIRABLE");
		allPapers.put("PO","SCRAP CERTIFICATE OF TITLE");
		allPapers.put("PP","POUR PIECE SEULEMENT");
		allPapers.put("PR","CLEAN TITLE");
		allPapers.put("PS","PUBLIC - SALVAGE TITLE");
		allPapers.put("PT","PUBLIC - SALVAGE TITLE");
		allPapers.put("PV","PERMIT TO SELL-SALVAGE VEHICLE");
		allPapers.put("PX","NON PUBLIC CLEAN TITLE");
		allPapers.put("R1","CERT OF TITLE-SLVG REBLD FLOOD");
		allPapers.put("R2","CERT OF TITLE-REBLT SLVG FLOOD");
		allPapers.put("RA","BOAT/ATV/SNOWMOBILE REG CERT");
		allPapers.put("RB","CERT OF TITLE SLVG REBUILDABLE");
		allPapers.put("RC","RECONSTRUCTED CLEAN");
		allPapers.put("RD","DLR ONLY REPOSSESSION");
		allPapers.put("RF","ERT OF TITLE-W&R=FLOOD&RECNST");
		allPapers.put("RG","REGISTRATION DOCUMENT CARD");
		allPapers.put("RH","CERT OF TITLE-REBUILDABLE SLVG");
		allPapers.put("RP","CERT OF TITLE-REPOSSESSED");
		allPapers.put("RR","CERT OF TTL-REBUILT&SLVG RBLD");
		allPapers.put("RS","CERT OF TITLE-RECONSTRCTD COLL");
		allPapers.put("RT","CERT OF TITLE-REBUILT VEHICLE");
		allPapers.put("RU","PERMIT REBUILT - UNFIT");
		allPapers.put("RW","CERT OF TITLE-SALVAGE");
		allPapers.put("S1","CERT OF TITLE-SALVAGE");
		allPapers.put("S2","CERT OF SALVAGE < 75% DAMAGE");
		allPapers.put("SB","SALVAGE BILL OF SALE");
		allPapers.put("SC","CERT OF TITLE-SALVAGE VEHICLE");
		allPapers.put("SD","DIS/DLR/EXP ONLY SALVAGE TITLE");
		allPapers.put("SH","CERT OF TITLE W/SALVAGE HISTOR");
		allPapers.put("SL","SALVAGE CERT-LEMON LAW BUYBACK");
		allPapers.put("SM","MV-907A SALVAGE CERTIFICATE");
		allPapers.put("SN","CERT OF TTL-SLVG SNOWMOBILE");
		allPapers.put("SP","CERT OF TITLE-SALVG PARTS ONLY");
		allPapers.put("SQ","DEALER ONLY SALVAGE TITLE");
		allPapers.put("SR","GRAVEMENT ACCIDENTE");
		allPapers.put("SS","CERT OF SALVAGE-RECOVERED STOL");
		allPapers.put("ST","CERT OF TITLE-SALVAGE");
		allPapers.put("SV","SALVAGE VEHICLE TITLE");
		allPapers.put("SW","CERT OF TITLE-SALVAGE WATER");
		allPapers.put("TA","CERT OF TITLE ALL-TERRAIN VEH");
		allPapers.put("TB","CERT OF TITLE-INDIAN TRIBAL");
		allPapers.put("TC","CERT OF TITLE - TAXI");
		allPapers.put("TE","TR52 - ABANDONMENT");
		allPapers.put("TH","CERT OF TITLE-SALVAGE REC THFT");
		allPapers.put("TR","TRAILER REGISTRATION");
		allPapers.put("TS","CERT OF TTL-SLVG ALL-TERRAIN V");
		allPapers.put("UC","CERT OF TITLE W/UNSATISFD LIEN");
		allPapers.put("UT","CERT OF TITLE-STOLEN/UNRECOVRD");
		allPapers.put("VC","VEH REGISTRATION-NO BRAND");
		allPapers.put("VD","CERT OF DESTRUCT-NO VIN PLATE");
		allPapers.put("VN","VEH REGISTRATION-NON-REPAIRABL");
		allPapers.put("VR","VR-112");
		allPapers.put("VS","VEH REGISTRATION-SALVAGE");
		allPapers.put("WF","SALVAGE CERT-WATER/FLOOD DAMAG");
		allPapers.put("WS","WILDLIFE CERT OF OWNSHP SALV");
		allPapers.put("WT","WATERCRAFT CERT OF TITLE");
		
		
		
		allPapersCodes.put("BOS - NONREPAIRABLE",0);
		allPapersCodes.put("BOS - ACTIVE",0);
		allPapersCodes.put("ACQ BOS - POST SALE - SALVAGE",0);
		allPapersCodes.put("ABANDONMENT-CERT OF SALES",0);
		allPapersCodes.put("AFFIDAVIT IN LIEU OF TITLE",0);
		allPapersCodes.put("AUCUNE MENTION",0);
		allPapersCodes.put("NON-REVIVABLE ACQUISITION",0);
		allPapersCodes.put("SALVAGE ACQUISITION BILLOFSALE",0);
		allPapersCodes.put("AUCTION SALES RECEIPT - NONREP",0);
		allPapersCodes.put("AFF IN LIEU OF TITLE - REBUILT",0);
		allPapersCodes.put("DERELICT BOS/PARTS ONLY",0);
		allPapersCodes.put("CERT OF TITLE-MANUFCTR BUYBACK",0);
		allPapersCodes.put("CERT OF TTL-SLVG MANUFCTR BYBK",0);
		allPapersCodes.put("BILL OF SALE - ABANDONED",0);
		allPapersCodes.put("BOAT CERT OF TITLE-SALVAGE",1);
		allPapersCodes.put("BILL OF SALE - DESTRUCTION",1);
		allPapersCodes.put("CERT OF TITLE-MANUF BUY BACK",1);
		allPapersCodes.put("CERT OF SALVAGE-W=FLOOD VEH",1);
		allPapersCodes.put("BILL OF SALE",1);
		allPapersCodes.put("BILL OF SALE-PARTS ONLY/SCRAP",0);
		allPapersCodes.put("BOS - REBUILT",0);
		allPapersCodes.put("BOAT MOTOR TITLE",1);
		allPapersCodes.put("BILL OF SALE - PARTS ONLY",0);
		allPapersCodes.put("BOS - NONREPAIRABLE",0);
		allPapersCodes.put("BILL OF SALE",0);
		allPapersCodes.put("CERT OF TITLE TO A BOAT",0);
		allPapersCodes.put("BOS - SALVAGE",0);
		allPapersCodes.put("CT CLEAN TITLE PUBLIC ELIG",1);
		allPapersCodes.put("CERT OF TITLE-SALVAGE COURT OR",0);
		allPapersCodes.put("CERT OF SALV-R=RECONSTRUCTED",1);
		allPapersCodes.put("CERT OF TITLE-COURT ORDER",1);
		allPapersCodes.put("CERTIFICATE OF DESTRUCTION",1);
		allPapersCodes.put("CLEAN TITLE-OVER 25% DAMAGE",1);
		allPapersCodes.put("DLR ONLY CERT OF DESTRUCTION",1);
		allPapersCodes.put("CLEAN TITLE WITH THEFT HISTORY",1);
		allPapersCodes.put("CERTIFICAT-VEH. A INSPECTER",0);
		allPapersCodes.put("CERT TO OBTAIN TTL-NON-HIGHWAY",0);
		allPapersCodes.put("CERT OF TITLE W/LEMON LAW HIST",1);
		allPapersCodes.put("CERTIFICATE OF ORIGIN",1);
		allPapersCodes.put("CERT OF TITLE-SALVAGE HISTORY",1);
		allPapersCodes.put("CERT OF TITLE OR SALVAGE ACQ",1);
		allPapersCodes.put("CERT OF TITLE-CORRECTED",1);
		allPapersCodes.put("CERT OF TITLE FOR A SNOWMOBILE",1);
		allPapersCodes.put("CERTIFICATE OF TITLE",1);
		allPapersCodes.put("VESSEL-CERTIFICATE OF TITLE",1);
		allPapersCodes.put("CERT OF TITLE-RECOVERED THEFT",1);
		allPapersCodes.put("CLEAN TITLE EXPORT ONLY",1);
		allPapersCodes.put("DEALER ONLY CLEAN TITLE",1);
		allPapersCodes.put("DLR ONLY SALVAGE CERTIFICATE",1);
		allPapersCodes.put("NC DLR ONLY SLVG REBUILT",1);
		allPapersCodes.put("DLR ONLY-CLEAN/OVER 25% DAMAGE",1);
		allPapersCodes.put("DLR ONLY - CLN TTL EXCEEDS 25%",1);
		allPapersCodes.put("DIS/DLR/EXP ONLY LIEN PAPERS",0);
		allPapersCodes.put("PERMIT TO DISMANTLE",0);
		allPapersCodes.put("DNR REGISTRATION",0);
		allPapersCodes.put("DLR ONLY, CLEAN W/ SALV HIST",1);
		allPapersCodes.put("DUPLICATE TITLE",1);
		allPapersCodes.put("APP FOR DUP CLEAN OR AC",0);
		allPapersCodes.put("DLR ONLY SALVAGE TITLE",1);
		allPapersCodes.put("APP FOR DUP SALVAGE OR AC",0);
		allPapersCodes.put("MV907A W/UNSATISFIED LIEN",0);
		allPapersCodes.put("DIS/DLR/EXP ONLY CLEAN TITLE",1);
		allPapersCodes.put("DIS/DLR/EXP ONLY CLEAN TITLE",1);
		allPapersCodes.put("DIS/DLR/EXP ONLY SALVAGE CERT",1);
		allPapersCodes.put("ELECTRONIC CERT OF TITLE",1);
		allPapersCodes.put("CERT OF TITLE-STOLEN",1);
		allPapersCodes.put("EXPORT ONLY",0);
		allPapersCodes.put("ELECTRONIC CERT OF TITLE",1);
		allPapersCodes.put("E-TITLE STOLEN/UNRECOVERED VEH",0);
		allPapersCodes.put("SCRAP CERT OF TITLE-FLOOD VEH",0);
		allPapersCodes.put("CERT OF TITLE-FLOOD VEHICLE",0);
		allPapersCodes.put("FBI JUNK",0);
		allPapersCodes.put("CERT OF TITLE-FLOOD DAMAGED",0);
		allPapersCodes.put("NON-REPAIRABLE CERT-FLOOD",0);
		allPapersCodes.put("SALVAGE/FIRE DAMAGE",1);
		allPapersCodes.put("CERT OF TITLE-SALVAGE FIRE",0);
		allPapersCodes.put("CERT OF TITLE-SALVAGE FLOOD",0);
		allPapersCodes.put("SALVAGE TITLE-FLOOD DAMAGE",1);
		allPapersCodes.put("FOREIGN OWNERSHIP DOCUMENTS",0);
		allPapersCodes.put("GOVERNMENT PARTS ONLY-SALVAGE",0);
		allPapersCodes.put("GOVERNMENT SALVAGE",0);
		allPapersCodes.put("GOVERNMENT TITLE",0);
		allPapersCodes.put("CERT OF TITLE-HAIL DAMAGED",1);
		allPapersCodes.put("CERT OF TITLE - HAIL SALVAGE",1);
		allPapersCodes.put("CERT OF TITLE-CLAIM PAID",1);
		allPapersCodes.put("CERT OF TITLE-FLOOD/REBUILT",1);
		allPapersCodes.put("IRRECUPERABLE",0);
		allPapersCodes.put("PERMIT IRREPAIRABLE",1);
		allPapersCodes.put("SCRAP-BILL OF SALE",0);
		allPapersCodes.put("JUNKING CERTIFICATE",0);
		allPapersCodes.put("CERTIFICATE OF TITLE - JUNK",0);
		allPapersCodes.put("JUNKING CERTIFICATE",0);
		allPapersCodes.put("JUNK RECEIPT",0);
		allPapersCodes.put("CERTIFICATE OF TITLE-JUNKED",0);
		allPapersCodes.put("CASH FOR CLUNKERS",0);
		allPapersCodes.put("CERT OF TITLE - REBUILT - RED",0);
		allPapersCodes.put("NONREPAIRABLE TITLE-FLOOD",0);
		allPapersCodes.put("DIS/DLR/EXP ONLY LEMON LAW",1);
		allPapersCodes.put("LETTER OF DESTRUCTION",0);
		allPapersCodes.put("CERT OF TITLE-LEMON LAW HISTOR",0);
		allPapersCodes.put("CERT OF TITLE-LEMON LAW BUYBAC",0);
		allPapersCodes.put("T-42 POSSESSORY LIEN SALE",0);
		allPapersCodes.put("LIEN PAPERS-CLEAN OR AC",0);
		allPapersCodes.put("CERT OF SLVG-LEMON LAW REPURCH",1);
		allPapersCodes.put("DIS/DLR/EXP LIEN PAPERS-SALV",0);
		allPapersCodes.put("LIEN PAPERS-SALVAGE OR AC",0);
		allPapersCodes.put("CERT OF TITLE-MANUFACTURER BUY",1);
		allPapersCodes.put("CLEAN - MULTIPLE BRANDS",1);
		allPapersCodes.put("DLR ONLY MUNICIPAL CERT OF TTL",1);
		allPapersCodes.put("MV-51",0);
		allPapersCodes.put("LIEN PAPERS",0);
		allPapersCodes.put("MV-51B/STMT OF OWNERSHIP",0);
		allPapersCodes.put("MARINE CERTIFICATE OF TITLE",1);
		allPapersCodes.put("CERT OF TITLE-MUNICIPAL",1);
		allPapersCodes.put("NOT ACTUAL MILES",1);
		allPapersCodes.put("NON-REB & REM SALVAGE TITLE",1);
		allPapersCodes.put("NONREPAIR-CERT OF AUTHORITY",0);
		allPapersCodes.put("PERMIT NO BRAND - FIT",1);
		allPapersCodes.put("CERT OF TITLE-NON HIGHWAY",1);
		allPapersCodes.put("VEHICLE PERMIT",1);
		allPapersCodes.put("MV-907A - NO PUBLIC VIN",0);
		allPapersCodes.put("DEALER ONLY NON-REPAIRABLE",0);
		allPapersCodes.put("NON-REPAIRABLE CERTIFICATE",0);
		allPapersCodes.put("CERT OF TITLE-SALVAGE NON-REM",1);
		allPapersCodes.put("PARTS ONLY-NO TITLE LETTER",0);
		allPapersCodes.put("PERMIT NO BRAND - UNFIT",0);
		allPapersCodes.put("NONREPAIRABLE TITLE",0);
		allPapersCodes.put("CERT OF TITLE-OHV SALVAGE",1);
		allPapersCodes.put("SALVAGE CERTIFICATE",1);
		allPapersCodes.put("CERT OF TITLE-POLICE",1);
		allPapersCodes.put("PREVIOUS FLOOD CLEAN",0);
		allPapersCodes.put("MV907A-PARTS ONLY W/LIENHOLDER",0);
		allPapersCodes.put("PUBLIC - NONREPAIRABLE",0);
		allPapersCodes.put("SCRAP CERTIFICATE OF TITLE",0);
		allPapersCodes.put("POUR PIECE SEULEMENT",0);
		allPapersCodes.put("CLEAN TITLE",1);
		allPapersCodes.put("PUBLIC - SALVAGE TITLE",1);
		allPapersCodes.put("PUBLIC - SALVAGE TITLE",1);
		allPapersCodes.put("PERMIT TO SELL-SALVAGE VEHICLE",0);
		allPapersCodes.put("NON PUBLIC CLEAN TITLE",1);
		allPapersCodes.put("CERT OF TITLE-SLVG REBLD FLOOD",0);
		allPapersCodes.put("CERT OF TITLE-REBLT SLVG FLOOD",0);
		allPapersCodes.put("BOAT/ATV/SNOWMOBILE REG CERT",1);
		allPapersCodes.put("CERT OF TITLE SLVG REBUILDABLE",1);
		allPapersCodes.put("RECONSTRUCTED CLEAN",1);
		allPapersCodes.put("DLR ONLY REPOSSESSION",1);
		allPapersCodes.put("ERT OF TITLE-W&R=FLOOD&RECNST",0);
		allPapersCodes.put("REGISTRATION DOCUMENT CARD",1);
		allPapersCodes.put("CERT OF TITLE-REBUILDABLE SLVG",1);
		allPapersCodes.put("CERT OF TITLE-REPOSSESSED",1);
		allPapersCodes.put("CERT OF TTL-REBUILT&SLVG RBLD",1);
		allPapersCodes.put("CERT OF TITLE-RECONSTRCTD COLL",1);
		allPapersCodes.put("CERT OF TITLE-REBUILT VEHICLE",1);
		allPapersCodes.put("PERMIT REBUILT - UNFIT",1);
		allPapersCodes.put("CERT OF TITLE-SALVAGE",1);
		allPapersCodes.put("CERT OF TITLE-SALVAGE",1);
		allPapersCodes.put("CERT OF SALVAGE < 75% DAMAGE",1);
		allPapersCodes.put("SALVAGE BILL OF SALE",0);
		allPapersCodes.put("CERT OF TITLE-SALVAGE VEHICLE",1);
		allPapersCodes.put("DIS/DLR/EXP ONLY SALVAGE TITLE",1);
		allPapersCodes.put("CERT OF TITLE W/SALVAGE HISTOR",1);
		allPapersCodes.put("SALVAGE CERT-LEMON LAW BUYBACK",1);
		allPapersCodes.put("MV-907A SALVAGE CERTIFICATE",1);
		allPapersCodes.put("CERT OF TTL-SLVG SNOWMOBILE",1);
		allPapersCodes.put("CERT OF TITLE-SALVG PARTS ONLY",0);
		allPapersCodes.put("DEALER ONLY SALVAGE TITLE",1);
		allPapersCodes.put("GRAVEMENT ACCIDENTE",1);
		allPapersCodes.put("CERT OF SALVAGE-RECOVERED STOL",1);
		allPapersCodes.put("CERT OF TITLE-SALVAGE",1);
		allPapersCodes.put("SALVAGE VEHICLE TITLE",1);
		allPapersCodes.put("CERT OF TITLE-SALVAGE WATER",0);
		allPapersCodes.put("CERT OF TITLE ALL-TERRAIN VEH",1);
		allPapersCodes.put("CERT OF TITLE-INDIAN TRIBAL",1);
		allPapersCodes.put("CERT OF TITLE - TAXI",1);
		allPapersCodes.put("TR52 - ABANDONMENT",0);
		allPapersCodes.put("CERT OF TITLE-SALVAGE REC THFT",1);
		allPapersCodes.put("TRAILER REGISTRATION",1);
		allPapersCodes.put("CERT OF TTL-SLVG ALL-TERRAIN V",1);
		allPapersCodes.put("CERT OF TITLE W/UNSATISFD LIEN",0);
		allPapersCodes.put("CERT OF TITLE-STOLEN/UNRECOVRD",0);
		allPapersCodes.put("VEH REGISTRATION-NO BRAND",1);
		allPapersCodes.put("CERT OF DESTRUCT-NO VIN PLATE",1);
		allPapersCodes.put("VEH REGISTRATION-NON-REPAIRABL",0);
		allPapersCodes.put("VR-112",0);
		allPapersCodes.put("VEH REGISTRATION-SALVAGE",1);
		allPapersCodes.put("SALVAGE CERT-WATER/FLOOD DAMAG",0);
		allPapersCodes.put("WILDLIFE CERT OF OWNSHP SALV",0);
		allPapersCodes.put("WATERCRAFT CERT OF TITLE",1);

		
		
		
	}
	
}
