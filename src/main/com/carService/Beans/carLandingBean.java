package main.com.carService.Beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
import main.com.carService.carLanding.carLandingAppServiceImpl;
import main.com.carService.carLanding.categoriesEnum;
import main.com.carService.invoiceLanding.invoicelanding;
import main.com.carService.invoiceLanding.invoicelandingAppServiceImpl;
import main.com.carService.loginNeeds.user;
import main.com.carService.moneyBox.moneybox;
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
	
	List<carLanding> listOfAddedCars;

	List<carLanding> listOfCarsLandingScroller;
	List<carLanding> listOfCarsLandingRelatedCars;
	
	List<carLanding> listOfCarsGroupByMake;
	
	carLanding selectedFreight;
	private boolean progress=false;
	

	private Date startDate;
	private Date bidingDate;
	private  Date endDate;
	

	private List<String> images;
	
	private carLanding selectedCarPage;

	@ManagedProperty(value = "#{notificationFacadeImpl}")
	private notificationAppServiceImpl notificationFacade;
	

	@ManagedProperty(value = "#{invoicelandingFacadeImpl}")
	private invoicelandingAppServiceImpl invoicelandingFacade;
	

	@ManagedProperty(value = "#{bidingFacadeImpl}")
	private bidingAppServiceImpl bidingFacade;
	

	@ManagedProperty(value = "#{mycarsFacadeImpl}")
	private mycarsAppServiceImpl mycarsFacade;
	

	private String searchType;
	private String searchMake;
	private String searchStartYear;
	private String searchEndYear;

	private List<carLanding> listOfUploadedDataCars;
	private List<carLanding> listOfFilteredCars;
	private List<carLanding> listOfAllCars;

	private UploadedFile fileExcel;
	
	private Integer progressLoading;
	private String differenceTimeDateFromBidToEnd;
	private String differenceTimeDateFromStartToBid;
	private float incrementBid;
	private float totalBid;
	

	private user userForInvoice;
	private invoicelanding invoiceData;
	private moneybox invoiceMoneyBoxData;
	private List<carLanding> carsForthisAccount;
	private carLanding carForInvoice;
	private Integer selectedCarIdToBeAddedInInvoice;
	private List<invoicelanding> allInvoice;

	private List<carLanding> allCarsString;
	private String selectedCarSearch;
	private biding currentBiding;
	
	private Long diffFromBidToEnd;
	private Long diffFromStartToBid;
	

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
	
    public Integer getProgressLoading() {
        if(progressLoading == null) {
        	progressLoading = 0;
        }
        else {
        	progressLoading = progressLoading + (int)(Math.random() * 35);
             
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
		
		
		refresh();
		
		
	}
	
	public void refreshTheCurrentBidStatue() {
		if(currentBiding==null) {
			currentBidStateDetails="You haven't Bid";
		}else if(currentBiding.getUserId().getId()==selectedCarPage.getUserMaxBidId().getId()) {
			currentBidStateDetails=stateOfCar.values()[selectedCarPage.getState()].getName();
		}
	}
	public List<carLanding> completeText(String query) {
		allCarsString =new ArrayList<carLanding>();
		for(int i=0;i<listOfAllCars.size();i++) {
			if((listOfAllCars.get(i).getMake().toLowerCase()).contains(query.toLowerCase())||
					(listOfAllCars.get(i).getYear().toLowerCase()).contains(query.toLowerCase())||
					(listOfAllCars.get(i).getModel().toLowerCase()).contains(query.toLowerCase())||
					(listOfAllCars.get(i).getLot().toLowerCase()).contains(query.toLowerCase())) {
				allCarsString.add(listOfAllCars.get(i));
			}
		}
		
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
		}else {
			
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Problem',\r\n" + 
					"			text: 'Please Login ',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
		}
	}
	public void refresh(){
		if(loginBean.getTheUserOfThisAccount()!=null) {
			if(loginBean.getTheUserOfThisAccount().getRole()!=null) {
				if(loginBean.getTheUserOfThisAccount().getRole()==user.ROLE_NormalUser) {
					allInvoice=invoicelandingFacade.getAllByUserIdCustomer(loginBean.getTheUserOfThisAccount().getId());
				}
			}
if(loginBean.getTheUserOfThisAccount().getId()!=null) {
	
			//Get All Watch List
			allCarsWatchList = mycarsFacade.getAllByUserIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST);
			

			//Get All Favorites List
			allCarsFavorits = mycarsFacade.getAllByUserIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_FAVORITE);
			
			//Get All Landing Car i have make a bid on it
			allCurrentBidCars=bidingFacade.getAllByuserId(loginBean.getTheUserOfThisAccount().getId());
		
}
}
		incrementBid=10;
		totalBid=0;
		listOfAllCars=carLandingFacade.getAll();
		listOfCarsLandingScroller=carLandingFacade.getAllForLanding();
		listOfCarsGroupByMake=carLandingFacade.getAllGroupsOfMake();
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRequest();
		
		try{
			Integer id=Integer.parseInt(origRequest.getParameterValues("id")[0]);
				if(id!=null){
					images=new ArrayList<String>();
					selectedCarPage=carLandingFacade.getById(id);
					listOfCarsLandingRelatedCars = carLandingFacade.getAllForCategories(Integer.valueOf(selectedCarPage.getCategory()));
					carViewId=id;
					valueOfCarBid=Float.valueOf(selectedCarPage.getCurrentBid());
					
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
						
						
						currentBiding=bidingFacade.getByCarIdAnduserId(selectedCarPage.getId(), loginBean.getTheUserOfThisAccount().getId());
						if(currentBiding!=null) {
						totalBid=currentBiding.getFullAmount();
						incrementBid=currentBiding.getIncrement();
						}
						
					}
					
					
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
				    		 
				        	copartReturnImages carImages= call.execute().body();
				        	
				        	if(carImages != null) {
				    			
				        		selectedCarPage.setMainImage(carImages.lotImages.get(0).link.get(0).url);
				    			for(int i=1;i<carImages.lotImages.size();i++) {
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
					
					
					
					
					
					
				}
			}
		catch(Exception ex){
			 
		}
		
		try{
			Integer categories=Integer.parseInt(origRequest.getParameterValues("category")[0]);
				if(categories!=null){
					listOfAddedCars=carLandingFacade.getAllForCategories(categories);
				}
			}
		catch(Exception ex){
			 
		}
		
		
		refreshTheCurrentBidStatue();
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
					if(!(incrementBid<=0)&&!(totalBid<0)) {
						
						currentBiding=bidingFacade.getByCarIdAnduserId(selectedCarPage.getId(), loginBean.getTheUserOfThisAccount().getId());
						if(currentBiding==null) {
							currentBiding=new biding();
						currentBiding.setFullAmount(totalBid);
						currentBiding.setIncrement(incrementBid);
						currentBiding.setLastDateBid(new Date());
						currentBiding.setUserId(loginBean.getTheUserOfThisAccount());
						currentBiding.setCarlandingId(selectedCarPage);
						bidingFacade.addbiding(currentBiding);
						arrangeBidingForThisCar(selectedCarPage);
						}else {
							if(totalBid>currentBiding.getFullAmount()) {
							currentBiding.setFullAmount(totalBid);
							currentBiding.setIncrement(incrementBid);
							currentBiding.setLastDateBid(new Date());
							currentBiding.setUserId(loginBean.getTheUserOfThisAccount());
							currentBiding.setCarlandingId(selectedCarPage);
							bidingFacade.addbiding(currentBiding);
							arrangeBidingForThisCar(selectedCarPage);
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
	private void arrangeBidingForThisCar(carLanding selectedCarPage2) {
		//Get all maximum Bids for each car
		List<biding> allBidingMax = bidingFacade.getAllMaxCarBidings();
		
		for(int i=0;i<allBidingMax.size();i++) {
			carLanding car=carLandingFacade.getById(allBidingMax.get(i).getCarlandingId().getId());
			
			if(Float.valueOf(car.getCurrentBid())<allBidingMax.get(i).getFullAmount()) {
				if(allowPersonToMakeBid(allBidingMax.get(i),car)) {
				
				biding theLastManWhoBidLessThanMe = bidingFacade.getByCarIdLessThanFullAmount(allBidingMax.get(i).getCarlandingId().getId(), allBidingMax.get(i).getFullAmount());
				
				float theTotalForTheManWhoLastMe=Float.valueOf(car.getCurrentBid());
				if(theLastManWhoBidLessThanMe!=null) {

					 theTotalForTheManWhoLastMe=theLastManWhoBidLessThanMe.getFullAmount();
				}
				float theNewAmount = allBidingMax.get(i).getIncrement()+theTotalForTheManWhoLastMe;
				if(theNewAmount>allBidingMax.get(i).getFullAmount()) {
					theNewAmount = allBidingMax.get(i).getFullAmount();
				}
				car.setCurrentBid(String.valueOf(theNewAmount));
				car.setUserMaxBidId(allBidingMax.get(i).getUserId());
				int level = calcBean.getLevel(Float.valueOf(car.getCurrentBid()));
				float copartFees=(float) calcBean.CalculateCopart(level, Float.valueOf(car.getCurrentBid()));
				
				
				car.setCopartFees(String.valueOf(copartFees));
				car.setOurFees(String.valueOf((new calcBean()).getOurFees()));
				valueOfCarBid=Float.valueOf(car.getCurrentBid());
				carLandingFacade.addcarLanding(car);
				
				}
			}
		}
		
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
			
			biding bidingMax = bidingFacade.getByCarIdandMaxAmount(selectedCarPage.getId());
			if(bidingMax!=null) {
			Date timeNow = Calendar.getInstance().getTime();
			Long diffFromMaxToNow = timeNow.getTime()-bidingMax.getLastDateBid().getTime();
			
			long diffSecondsFromMaxToNow = diffFromMaxToNow / 1000 % 60;
			long diffMinutesFromMaxToNow = diffFromMaxToNow / (60 * 1000) % 60;
			
			timeInSecondsForLive = String.valueOf(timeLiveEnd-1 - diffMinutesFromMaxToNow)+" : "+String.valueOf(60 - diffSecondsFromMaxToNow);
			
			if(diffMinutesFromMaxToNow >= timeLiveEnd) {
				selectedCarPage.setActive(false);
				selectedCarPage.setState(stateOfCar.ProcessState.getType());
				carLandingFacade.addcarLanding(selectedCarPage);
				if(selectedCarPage.getUserMaxBidId()!=null) {
					sendNotificationForUser(selectedCarPage.getUserMaxBidId().getId(),"You have win the Biding Waiting the Copart....","/pages/secured/normalUsers/vehicleList.jsf?faces-redirect=true");
					FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:buy-now-block");
					PrimeFaces.current().executeScript("window.location.reload(true);");
				}
			}
			
		}else {
			timeInSecondsForLive = "N ...";
		}
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:liveBidDisplay");
		}
		
	}
	public void reloadedParametersAndPanelRefresh() {
		System.out.println(String.valueOf(selectedCarPage.getId()));
		selectedCarPage=carLandingFacade.getById(selectedCarPage.getId());
		Calendar nowTime=Calendar.getInstance();
		Date timeNow=new Date();
		timeNow.setTime(nowTime.getTimeInMillis());
		
		
		Calendar TimeOfFreight = Calendar.getInstance();
		TimeOfFreight.setTime(selectedCarPage.getEndDate());
		diffFromBidToEnd = selectedCarPage.getEndDate().getTime()-timeNow.getTime();
		diffFromStartToBid = selectedCarPage.getBidingDate().getTime()-timeNow.getTime();
		if(selectedCarPage.isActive()) {
		if(diffFromStartToBid<0) {
			if(diffFromBidToEnd>0) {
				//Live Mode
				
				reloadLiveBidingParamenters(true);
			}
		}
		}
		long diffSecondsFromBidToEnd = diffFromBidToEnd / 1000 % 60;
		long diffMinutesFromBidToEnd = diffFromBidToEnd / (60 * 1000) % 60;
		long diffHoursFromBidToEnd = diffFromBidToEnd / (60 * 60 * 1000) % 24;
		long diffDaysFromBidToEnd = diffFromBidToEnd / (24 * 60 * 60 * 1000);
		
		long diffSecondsFromStartToBid = diffFromStartToBid / 1000 % 60;
		long diffMinutesFromStartToBid = diffFromStartToBid / (60 * 1000) % 60;
		long diffHoursFromStartToBid = diffFromStartToBid / (60 * 60 * 1000) % 24;
		long diffDaysFromStartToBid = diffFromStartToBid / (24 * 60 * 60 * 1000);
		
		System.out.println("Ahmed: "+timeNow);
		System.out.println("Ahmed: "+selectedCarPage.getEndDate());
		System.out.println("Ahmed: "+timeNow.getTime());
		System.out.println("Ahmed: "+selectedCarPage.getEndDate().getTime());
		
		if(diffFromBidToEnd<0&&selectedCarPage.isActive()) {
			selectedCarPage.setActive(false);
			selectedCarPage.setState(stateOfCar.ProcessState.getType());
			carLandingFacade.addcarLanding(selectedCarPage);
			if(selectedCarPage.getUserMaxBidId()!=null) {
				sendNotificationForUser(selectedCarPage.getUserMaxBidId().getId(),"You have win the Biding Waiting the Copart....","/pages/secured/normalUsers/vehicleList.jsf?faces-redirect=true");
	
			}
			}
		
		

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
			currentBiding=bidingFacade.getByCarIdAnduserId(selectedCarPage.getId(), loginBean.getTheUserOfThisAccount().getId());
			if(currentBiding!=null) {
				totalBid=currentBiding.getFullAmount();
				incrementBid=currentBiding.getIncrement();
			}
		}
		
		
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("differenceTimeForm:differenceTime");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:currentPriceDisplay");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:CurrentPriceSmall");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:CurrentBidAmount");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:bidDetails");
		
	}
	
	public void cancelInvoice() {
		System.out.println("Cancel");
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/Bid/userOfBiding.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void goToInvoice(int id) {
		invoiceData =new invoicelanding();
		invoiceData = invoicelandingFacade.getById(id);
		selectedCarIdToBeAddedInInvoice=-1;
		selectedCarIdToBeAddedInInvoice=invoiceData.getCarLandingId().getId();
		carForInvoice= carLandingFacade.getById(selectedCarIdToBeAddedInInvoice);
		

		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice/invoice.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void saveInvoiceData() {

		invoiceData.setUserIdCustomer(userForInvoice);
		invoiceData.setUserIdIssuer(loginBean.getTheUserOfThisAccount());
		invoiceData.setDate(Calendar.getInstance());
		carLanding car=carLandingFacade.getById(selectedCarIdToBeAddedInInvoice);
		invoiceData.setCarLandingId(car);
		invoicelandingFacade.addinvoicelanding(invoiceData);
		
		
		
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice/invoice.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addCarToInvoice() {
		carForInvoice= carLandingFacade.getById(selectedCarIdToBeAddedInInvoice);
		

		try {

			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice/invoiceAdd.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void invoiceDetails(int idUser) {
System.out.println("Data: "+String.valueOf(idUser));
		invoiceData=new invoicelanding();
		userForInvoice=loginBean.getUserDataFacede().getById(idUser);
		invoiceMoneyBoxData = loginBean.getThisAccountMoneyBox();
		
		carsForthisAccount=carLandingFacade.getAllForUserBiding(idUser);

		invoiceData.setBankAccountNumber(invoiceMoneyBoxData.getBankAccountNumber());
		invoiceData.setBankAddress(invoiceMoneyBoxData.getBankAddress());
		invoiceData.setBankName(invoiceMoneyBoxData.getBankName());
		invoiceData.setBankTelephone(invoiceMoneyBoxData.getBankTelephone());
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/invoice/invoiceAdd.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		listOfAddedCars=carLandingFacade.getAllForSearch(searchStartYear, searchEndYear, searchMake, searchType);

		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("panelCarsToUpdate");
	}
	
	public void makeSearchOutSide() {
		listOfAddedCars=carLandingFacade.getAllForSearch(searchStartYear, searchEndYear, searchMake, searchType);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/public/carsForType.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public categoriesEnum getCategoryEnum(int type) {
        return categoriesEnum.values()[type];
    }
	
	public categoriesEnum[] getCategoriesEnum() {
        return categoriesEnum.values();
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
		
		totalBid-=100;
		if(totalBid<0) {
			totalBid=0;
		}
	}
	
	public void increaseBy100DollarsMain() {
		System.out.println(String.valueOf(totalBid));
		totalBid+=100;
		
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
	       if(isDateValidated()) {
	       PrimeFaces.current().executeScript("showDialog('car');");
	       UploadedFile fileUploaded = event.getFile();
		 	try {
		 		if(fileUploaded!=null) {
		 		if(fileUploaded.getSize()!=0) {
		 			InputStream fileData =fileUploaded.getInputstream(); 
		 	       System.out.println("File streamed");
		 		listOfUploadedDataCars = parseUsersFile(fileData);
		 		

	    		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:tableForm");

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
	       }else {
		 	PrimeFaces.current().executeScript("ErrorDialog2("+"'Please Fill the Time for start and the end'"+")");
	       }
	    }
	 
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
	 
	 public List<carLanding> parseUsersFile(InputStream input) {
			List<carLanding> dataList = new ArrayList<carLanding>();
			try {
				//inputStream = resource.getInputStream();
				// Create Workbook instance holding reference to .xlsx file
				Workbook  workbook = WorkbookFactory.create(input);

		 	       System.out.println("workBook");
				// Get first/desired sheet from the workbook
		 	      Sheet sheet = workbook.getSheetAt(0);
				int rowsNumbers = sheet.getLastRowNum();
		 	       System.out.println("Sheet");
				
		 	       for(int i=1;i<rowsNumbers+1;i++) {
					Row row = sheet.getRow(i);
					// For each row, iterate through all the columns
					carLanding data=new carLanding();
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
									data.setCategory(categoriesEnum.HeavyDuties.getType());
		                		}else if(valueOfType.equalsIgnoreCase("V")) {
									data.setCategory(categoriesEnum.SMALLCARS.getType());
		                		}else if(valueOfType.equalsIgnoreCase("C")) {
									data.setCategory(categoriesEnum.MotorCycle.getType());
		                		}else if(valueOfType.equalsIgnoreCase("S")) {
									data.setCategory(categoriesEnum.SnowMobile.getType());
		                		}else if(valueOfType.equalsIgnoreCase("M")||valueOfType.equalsIgnoreCase("J")) {
									data.setCategory(categoriesEnum.JetSkies.getType());
		                		}else if(valueOfType.equalsIgnoreCase("Q")) {
									data.setCategory(categoriesEnum.Korean.getType());
		                		}else{
									data.setCategory(categoriesEnum.SUV.getType());
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
		                	  
		                	  
						case 46:
							try {
								data.setCurrentBid(getTheValueFromCell(cell));
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
					data.setDeleted(false);
					data.setPaymentDone(false);
					data.setActive(true);
					data.setMainId(loginBean.getTheUserOfThisAccount());
					data.setShowenInLanding(false);
					data.setStartDate(startDate);
					data.setBidingDate((bidingDate));
					data.setEndDate((endDate));
					data.setState(stateOfCar.BidingState.getType());
					dataList.add(data);
				}
				input.close();
			 dataList.remove(0);
			
			return dataList;
			 
		
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

	public void saveTheNewUploadedList() {
		System.out.println("Data: Saving");
		int listNumTotal = listOfUploadedDataCars.size();
		for(int i=0;i<listOfUploadedDataCars.size();i++) {
			carLandingFacade.addcarLanding(listOfUploadedDataCars.get(i));
			int newPercent = (listNumTotal - (i+1))/listNumTotal*100; 
			progressLoading = + newPercent; 
		}
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleList.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error: "+e.toString());
		}
	}
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

	public invoicelandingAppServiceImpl getInvoicelandingFacade() {
		return invoicelandingFacade;
	}

	public void setInvoicelandingFacade(invoicelandingAppServiceImpl invoicelandingFacade) {
		this.invoicelandingFacade = invoicelandingFacade;
	}

	public user getUserForInvoice() {
		return userForInvoice;
	}

	public void setUserForInvoice(user userForInvoice) {
		this.userForInvoice = userForInvoice;
	}

	public invoicelanding getInvoiceData() {
		return invoiceData;
	}

	public void setInvoiceData(invoicelanding invoiceData) {
		this.invoiceData = invoiceData;
	}

	public moneybox getInvoiceMoneyBoxData() {
		return invoiceMoneyBoxData;
	}

	public void setInvoiceMoneyBoxData(moneybox invoiceMoneyBoxData) {
		this.invoiceMoneyBoxData = invoiceMoneyBoxData;
	}

	public List<carLanding> getCarsForthisAccount() {
		return carsForthisAccount;
	}

	public void setCarsForthisAccount(List<carLanding> carsForthisAccount) {
		this.carsForthisAccount = carsForthisAccount;
	}

	public carLanding getCarForInvoice() {
		return carForInvoice;
	}

	public void setCarForInvoice(carLanding carForInvoice) {
		this.carForInvoice = carForInvoice;
	}

	public Integer getSelectedCarIdToBeAddedInInvoice() {
		return selectedCarIdToBeAddedInInvoice;
	}

	public void setSelectedCarIdToBeAddedInInvoice(Integer selectedCarIdToBeAddedInInvoice) {
		this.selectedCarIdToBeAddedInInvoice = selectedCarIdToBeAddedInInvoice;
	}

	public List<invoicelanding> getAllInvoice() {
		return allInvoice;
	}

	public void setAllInvoice(List<invoicelanding> allInvoice) {
		this.allInvoice = allInvoice;
	}

	public List<carLanding> getListOfAllCars() {
		return listOfAllCars;
	}

	public void setListOfAllCars(List<carLanding> listOfAllCars) {
		this.listOfAllCars = listOfAllCars;
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

	public List<carLanding> getListOfCarsLandingRelatedCars() {
		return listOfCarsLandingRelatedCars;
	}

	public void setListOfCarsLandingRelatedCars(List<carLanding> listOfCarsLandingRelatedCars) {
		this.listOfCarsLandingRelatedCars = listOfCarsLandingRelatedCars;
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

	
	
	
	
}
