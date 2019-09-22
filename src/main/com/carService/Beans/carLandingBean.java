package main.com.carService.Beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
import main.com.carService.carLanding.carLanding;
import main.com.carService.carLanding.carLandingAppServiceImpl;
import main.com.carService.carLanding.categoriesEnum;
import main.com.carService.notification.notification;
import main.com.carService.notification.notificationAppServiceImpl;
import retrofit2.Call;


@ManagedBean(name = "carLandingBean")
@SessionScoped
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
	 


	@ManagedProperty(value = "#{carLandingFacadeImpl}")
	private carLandingAppServiceImpl carLandingFacade;
	
	List<carLanding> listOfAddedCars;
	
	List<carLanding> listOfCarsLandingScroller;
	
	List<carLanding> listOfCarsGroupByMake;
	
	carLanding selectedFreight;
	private boolean progress=false;
	
	
	private Date bidingDate;
	private  Date endDate;
	

	private List<String> images;
	
	private carLanding selectedCarPage;

	@ManagedProperty(value = "#{notificationFacadeImpl}")
	private notificationAppServiceImpl notificationFacade;
	

	private Integer searchType;
	private String searchMake;
	private String searchStartYear;
	private String searchEndYear;

	private List<carLanding> listOfUploadedDataCars;
	private List<carLanding> listOfFilteredCars;

	private UploadedFile fileExcel;
	
	private Integer progressLoading;
	private String differenceTimeDate;
	private int incrementBid;
	
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
	
	
	public void refresh(){
		incrementBid=10;
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
				    	
				        	System.out.println("ImagesLoaded");

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
					if(!(incrementBid<=0)) {
					selectedCarPage.setCurrentBid(String.valueOf(Integer.valueOf(selectedCarPage.getCurrentBid())+incrementBid));
					selectedCarPage.setUserMaxBidId(loginBean.getTheUserOfThisAccount());
					carLandingFacade.addcarLanding(selectedCarPage);
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
	public void reloadedParametersAndPanelRefresh() {
		selectedCarPage=carLandingFacade.getById(selectedCarPage.getId());
		Calendar nowTime=Calendar.getInstance();
		Date timeNow=new Date();
		timeNow.setTime(nowTime.getTimeInMillis());
		
		
		Calendar TimeOfFreight = Calendar.getInstance();
		TimeOfFreight.setTime(selectedCarPage.getEndDate());
		Long diff = selectedCarPage.getEndDate().getTime()-timeNow.getTime();
		


		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		System.out.println("Ahmed: "+timeNow);
		System.out.println("Ahmed: "+selectedCarPage.getEndDate());
		System.out.println("Ahmed: "+timeNow.getTime());
		System.out.println("Ahmed: "+selectedCarPage.getEndDate().getTime());
		
		if(diff<0&&selectedCarPage.isActive()) {
			selectedCarPage.setActive(false);
			carLandingFacade.addcarLanding(selectedCarPage);
			sendNotificationForUser(selectedCarPage.getUserMaxBidId().getId(),"You have win the Biding Waiting the Copart....","/pages/secured/userData/userProfile.jsf");
		}
		
		

		differenceTimeDate=String.valueOf(diffDays)+"D: "+
				String.valueOf(diffHours)+"H: "+
				String.valueOf(diffMinutes)+"M: "+
				String.valueOf(diffSeconds)+" S";
		
		if(diff<0) {
			differenceTimeDate="Biding Finished";
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:buy-now-block");
			
		}

		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:differenceTime");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:currentPriceDisplay");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:CurrentPriceSmall");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("RightColumnData:CurrentBidAmount");
		
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

	
	
/*
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
	*/
	public boolean isDateValidated() {
		if(endDate==null || bidingDate==null) {
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
					data.setActive(true);
					data.setMainId(loginBean.getTheUserOfThisAccount());
					data.setShowenInLanding(false);
					data.setBidingDate((bidingDate));
					data.setEndDate((endDate));
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
		listOfUploadedDataCars=new ArrayList<carLanding>();
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

	

	
	public String getDifferenceTimeDate() {
		return differenceTimeDate;
	}

	public void setDifferenceTimeDate(String differenceTimeDate) {
		this.differenceTimeDate = differenceTimeDate;
	}

	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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


	public Integer getSearchType() {
		return searchType;
	}


	public void setSearchType(Integer searchType) {
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

	public int getIncrementBid() {
		return incrementBid;
	}

	public void setIncrementBid(int incrementBid) {
		this.incrementBid = incrementBid;
	}

	public notificationAppServiceImpl getNotificationFacade() {
		return notificationFacade;
	}

	public void setNotificationFacade(notificationAppServiceImpl notificationFacade) {
		this.notificationFacade = notificationFacade;
	}


	
	
}
