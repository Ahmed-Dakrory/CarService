package main.com.carService.Beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import helpers.retrofit.mainFiles.APIClient;
import helpers.retrofit.mainFiles.APIInterface;
import helpers.retrofit.mainFiles.OrderOutDetails;
import helpers.retrofit.mainFiles.copartReturnImages;
import main.com.carService.carLanding.carLanding;
import main.com.carService.carLanding.carLandingAppServiceImpl;
import main.com.carService.carLanding.categoriesEnum;
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
	
	
	private String bidingDate;
	private  String endDate;
	

	private List<String> images;
	
	private carLanding selectedCarPage;

	

	private Integer searchType;
	private String searchMake;
	private String searchStartYear;
	private String searchEndYear;
	
	private List<carLanding> listOfUploadedDataCars;

	private UploadedFile fileExcel;
	
	
	@PostConstruct
	public void init() {
		
		refresh();
		
		
	}
	
	
	public void refresh(){

		
		listOfAddedCars=carLandingFacade.getAll();
		listOfCarsLandingScroller=carLandingFacade.getAllForLanding();
		listOfCarsGroupByMake=carLandingFacade.getAllGroupsOfMake();
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRequest();
		
		try{
			Integer id=Integer.parseInt(origRequest.getParameterValues("id")[0]);
				if(id!=null){
					selectedCarPage=carLandingFacade.getById(id);
					//Here Get the images For the main 
					/**
					 * 
					 */
					
					
					
					
					
					
					
					
					
					
					
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
		endDate="";
		bidingDate="";
		
		images=new ArrayList<String>();
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vitView.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectCarForMain(int selectedCarId) {
		
		PrimeFaces.current().executeScript("showDialog('car');");
		selectedFreight=carLandingFacade.getById(selectedCarId);
		endDate=getStringFromCalendar(selectedFreight.getEndDate());
		bidingDate=getStringFromCalendar(selectedFreight.getBidingDate());
		updateImagesWithLink(selectedFreight.getAllImagesLink());

		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vitView.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	
	 public void parseFile(FileUploadEvent event) {
	       System.out.println("File uploaded");
	       UploadedFile fileUploaded = event.getFile();
		 	try {
		 		if(fileUploaded!=null) {
		 		if(fileUploaded.getSize()!=0) {
		 			InputStream fileData =fileUploaded.getInputstream(); 
		 	       System.out.println("File streamed");
		 		listOfUploadedDataCars = parseUsersFile(fileData);
		 		
		 		}
		 		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
	    }
	 
	 
	 public List<carLanding> parseUsersFile(InputStream input) {
			List<carLanding> dataList = new ArrayList<carLanding>();
			try {
				//inputStream = resource.getInputStream();
				// Create Workbook instance holding reference to .xlsx file
				@SuppressWarnings("resource")
				XSSFWorkbook workbook = new XSSFWorkbook(input);

		 	       System.out.println("workBook");
				// Get first/desired sheet from the workbook
				XSSFSheet sheet = workbook.getSheetAt(0);

		 	       System.out.println("Sheet");
				// Iterate through each rows one by one

				Iterator<Row> rowIterator = sheet.iterator();

				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					carLanding data=new carLanding();
					
					int count = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						count++;
					
	                  switch(count) {
		                case 8:
		                	try {
								data.setItemNumber(String.valueOf(cell.getNumericCellValue()));
							} catch (Exception ex) { System.out.println(ex.toString());
								
							}
		                	  break;
		                	  
		                case 9:
		                	try {
								data.setLot(String.valueOf(cell.getNumericCellValue()));
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
						case 11:
							try {
								data.setYear(String.valueOf(cell.getNumericCellValue()));
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
						case 12:
							try {
								data.setMake(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
						case 14:
							try {
								data.setModel(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
						case 15:
							try {
								data.setBodyStyle(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
						case 16:
							try {
								data.setColor(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
						case 17:
							try {
								data.setDamageDescription(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
						case 18:
							try {
								data.setSecondaryDamage(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
							  
						case 23:
							try {
								data.setUuid(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
						case 24:
							try {
								data.setOdoMeter(String.valueOf(cell.getNumericCellValue()));
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
		                	  
						case 25:
							try {
								data.setOdoDescription(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
						case 26:
							try {
								data.setEstRetailValue(String.valueOf(cell.getNumericCellValue()));
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
						case 27:
							try {
								data.setRepairEstimate(String.valueOf(cell.getNumericCellValue()));
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;

						case 28:
							try {
								data.setEngineType(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
						case 30:
							try {
								data.setTransmission(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
						case 31:
							try {
								data.setFuel(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
						case 32:
							try {
								data.setCylinder(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
		                	  
						case 37:
							try {
								data.setAuctionLocation(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
		                	  
						case 38:
							try {
								String state = cell.getStringCellValue();
								data.setAuctionLocation(state+"-"+data.getAuctionLocation());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
		                	  
						case 42:
							try {
								data.setMainImage(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
						case 44:
							try {
								data.setGridRow(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
		                	  
						case 46:
							try {
								data.setCurrentBid(String.valueOf(cell.getNumericCellValue()));
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
						case 47:
							try {
								data.setAllImagesLink(cell.getStringCellValue());
							}catch (Exception ex) { System.out.println(ex.toString());
							}
		                	  break;
		                	  
							
	                  }
						
							
				
						
					
				
					}
					data.setActive(true);
					data.setCategory(categoriesEnum.SMALLCARS.getType());
					data.setMainId(loginBean.getTheUserOfThisAccount());
					data.setShowenInLanding(false);
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

	
	public void updateCarDataMain() {
		selectedFreight.setEndDate(setCalendarFromString(endDate));
		selectedFreight.setBidingDate(setCalendarFromString(bidingDate));
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
	
	
	public void cancel() {
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleList.jsf?faces-redirect=true");
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

	

	
	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	

	

	public String getBidingDate() {
		return bidingDate;
	}


	public void setBidingDate(String bidingDate) {
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


	
	
}
