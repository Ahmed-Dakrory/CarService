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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import helpers.retrofit.mainFiles.APIClient;
import helpers.retrofit.mainFiles.APIInterface;
import helpers.retrofit.mainFiles.OrderOutDetails;
import helpers.retrofit.mainFiles.copartReturnImages;
import main.com.carService.carLanding.carLanding;
import main.com.carService.costCalc.transportfeeAppServiceImpl;
import main.com.carService.invoiceLanding.invoicelanding;
import main.com.carService.invoiceLanding.invoicelandingAppServiceImpl;
import main.com.carService.loginNeeds.user;
import main.com.carService.moneyBox.moneybox;
import main.com.carService.carLanding.carLandingAppServiceImpl;
import main.com.carService.carLanding.carLanding.stateOfCar;
import main.com.carService.carLandingImage.carlandingimage;
import main.com.carService.carLandingImage.carlandingimageAppServiceImpl;
import main.com.carService.myCars.mycars;
import main.com.carService.myCars.mycarsAppServiceImpl;
import main.com.carService.notification.notification;
import main.com.carService.notification.notificationAppServiceImpl;
import retrofit2.Call;


@ManagedBean(name = "carPageBean")
@SessionScoped
public class carPageBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440239969354416L;


	/**
	 * 
	 */
	
	
 
	

	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	



	@ManagedProperty(value = "#{carLandingFacadeImpl}")
	private carLandingAppServiceImpl carLandingFacade;
	
	List<carLanding> listOfAddedCars;


	@ManagedProperty(value = "#{carlandingimageFacadeImpl}")
	private carlandingimageAppServiceImpl carlandingimageFacade;
	

	private List<String> images_deleted;
	private List<String> imagesLanding;

	private Integer progressLoading;

	@ManagedProperty(value = "#{mycarsFacadeImpl}")
	private mycarsAppServiceImpl mycarsFacade;
	
	@ManagedProperty(value = "#{notificationFacadeImpl}")
	private notificationAppServiceImpl notificationFacade;
	
	private List<carLanding> listOfFilteredCars;
	
	private String searchType;
	private String searchMake;
	private String searchModel;
	private String searchStartYear;
	private String searchEndYear;

	private boolean progress=false;

	private carLanding selectedFreight;


	List<carLanding> listOfCarsGroupByCategory;
	List<carLanding> listOfCarsGroupByMake;
	List<carLanding> listOfCarsGroupByModel;

	@ManagedProperty(value = "#{transportfeeFacadeImpl}")
	private transportfeeAppServiceImpl transportfeeFacade;
	

	private Date bidingDate;
	private  Date endDate;
	
	private List<String> images;
	
	private user userForInvoice;
	private invoicelanding invoiceData;
	private moneybox invoiceMoneyBoxData;
	private List<carLanding> carsForthisAccount;
	private carLanding carForInvoice;
	private Integer selectedCarIdToBeAddedInInvoice;
	private List<invoicelanding> allInvoice;

	@ManagedProperty(value = "#{invoicelandingFacadeImpl}")
	private invoicelandingAppServiceImpl invoicelandingFacade;
	
	
	@PostConstruct
	public void init() {

		searchStartYear = "1960";
		searchEndYear = "2020";
		listOfAddedCars=new ArrayList<carLanding>();
		refresh();
	}
	
	
	public void getAllModelsByMakes() {
		listOfCarsGroupByModel=carLandingFacade.getAllGroupsOfModelWithMake(searchMake);
	}
	
public void refresh() {
		// TODO Auto-generated method stub
	HttpServletRequest origRequest = (HttpServletRequest)FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequest();
	listOfCarsGroupByMake=carLandingFacade.getAllGroupsOfMake();
	listOfCarsGroupByCategory = carLandingFacade.getAllGroupsOfCategory();
	if(loginBean.getTheUserOfThisAccount()!=null) {
		if(loginBean.getTheUserOfThisAccount().getRole()!=null) {
				allInvoice=invoicelandingFacade.getAllByUserIdCustomer(loginBean.getTheUserOfThisAccount().getId());
			
		}

}
	try{
		searchStartYear = "1960";
		searchEndYear = "2020";
		String categories=String.valueOf(origRequest.getParameterValues("category")[0]);
			if(categories!=null){
				searchType = categories;
				searchStartYear = "1960";
				searchEndYear = "2020";
				listOfAddedCars=new ArrayList<carLanding>();
				listOfAddedCars=carLandingFacade.getAllForCategories(categories);
			}
		}
	catch(Exception ex){
		 
	}
	}

public void refreshForBiding() {

	
	selectedFreight=new carLanding();
	
	images=new ArrayList<String>();

	images_deleted=new ArrayList<String>();
 imagesLanding=new ArrayList<String>();
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
		   .getExternalContext().redirect("/pages/secured/normalUsers/invoice/invoice2.jsf?faces-redirect=true");
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


public void sendNotificationForCustomerCopartWinning() {

	 FacesContext context = FacesContext.getCurrentInstance();
	 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	 Integer carId = Integer.valueOf((String) map.get("carId"));
	carLanding car = carLandingFacade.getById(carId);
	if(car.getUserMaxBidId()!=null) {
	sendNotificationForUser(car.getUserMaxBidId().getId(), "You have win From Copart", "/pages/secured/normalUsers/vehicleList.jsf?faces-redirect=true");
	PrimeFaces.current().executeScript("new PNotify({\r\n" + 
			"			title: 'User Notification ',\r\n" + 
			"			text: 'User Has Been Notified',\r\n" + 
			"			left:\"2%\"\r\n" + 
			"		});");
	}else{
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'User Notification ',\r\n" + 
				"			text: 'No User Assigned',\r\n" + 
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
public boolean isCarInFavourites() {
	FacesContext context = FacesContext.getCurrentInstance();
	 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	 String num = (String) map.get("num");
		if(loginBean.isLoggedIn()) {
		mycars watchListCarNew=mycarsFacade.getByUserIdAndCarIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST, Integer.parseInt(num));
		
		if(watchListCarNew!=null) {
			
			return true;
		}
		}
		return false;
	}

public boolean isNotCarInFavourites() {

	 FacesContext context = FacesContext.getCurrentInstance();
	 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	 String num = (String) map.get("num");
	if(loginBean.isLoggedIn()) {
	mycars watchListCarNew=mycarsFacade.getByUserIdAndCarIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST, Integer.parseInt(num));
	
	if(watchListCarNew!=null) {
		
		return false;
	}
	}
	return true;
}
	
	public void setCarToWatchListNum() {
		FacesContext context = FacesContext.getCurrentInstance();
		 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		 String num = (String) map.get("num");
		carLanding carId=carLandingFacade.getById(Integer.parseInt(num));
		if(loginBean.isLoggedIn()) {
			System.out.println("Ahmed CarBid3");
			//Make this car added to my watch List
			mycars watchListCarNew=mycarsFacade.getByUserIdAndCarIdAndType(loginBean.getTheUserOfThisAccount().getId(), mycars.TYPE_WATCH_LIST, Integer.parseInt(num));
			if(watchListCarNew==null) {
				watchListCarNew =new mycars();
				watchListCarNew.setCarLandingId(carId);
				watchListCarNew.setType(mycars.TYPE_WATCH_LIST);
				watchListCarNew.setUserId(loginBean.getTheUserOfThisAccount());
				mycarsFacade.addmycars(watchListCarNew);

			}else {
				try {
					mycarsFacade.delete(watchListCarNew);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			PrimeFaces.current().executeScript("updatedWithId("+String.valueOf(num)+")");
		}else {
			
			PrimeFaces.current().executeScript("new PNotify({\r\n" + 
					"			title: 'Problem',\r\n" + 
					"			text: 'Please Login ',\r\n" + 
					"			left:\"2%\"\r\n" + 
					"		});");
		}
	}
	
	
	
		public void makeSearch() {

			listOfAddedCars=new ArrayList<carLanding>();
		listOfAddedCars=carLandingFacade.getAllForSearch(searchStartYear, searchEndYear, searchMake,searchModel, searchType);

		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("panelCarsToUpdate");
	}
	
	public void makeSearchOutSide() {

		listOfAddedCars=new ArrayList<carLanding>();
		listOfAddedCars=carLandingFacade.getAllForSearch(searchStartYear, searchEndYear, searchMake,searchModel, searchType);
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/public/carsForType.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	

public void theloaderFirst() {
	System.out.println("Print NOW");
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




public void previewImage(FileUploadEvent event) {
	byte[] image =event.getFile().getContents();
	String fileName =saveImageToDirectory(image, System.getProperty("catalina.base")+"/images/");
	imagesLanding.add(fileName);
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:imagesPanelpreviewImage");
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


	public void selectCarRowForMain() {

		 FacesContext context = FacesContext.getCurrentInstance();
		 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		 Integer carId = Integer.valueOf((String) map.get("carId"));
		 selectedFreight=carLandingFacade.getById(carId);
		 

			images_deleted=new ArrayList<String>();
		 imagesLanding=new ArrayList<String>();
			List<carlandingimage> allImages = carlandingimageFacade.getAllByCarIdAndType(selectedFreight.getId(),carlandingimage.TYPE_AUCTION);
			if(allImages!=null) {
			for(int i=0;i<allImages.size();i++) {
				imagesLanding.add(allImages.get(i).getUrl());
			}
			}
			
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
	
	public void deleteFile() {
		 FacesContext context = FacesContext.getCurrentInstance();
		 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		 Integer typeOfFile = Integer.valueOf((String) map.get("typeOfFile"));
		 String fileURL = (String) map.get("fileURL");

			System.out.println(fileURL);
			System.out.println(typeOfFile);
		if(typeOfFile==carlandingimage.TYPE_AUCTION) {
			
				removeFileFromImages(fileURL);
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:imagesPanelpreviewImage");
			PrimeFaces.current().executeScript("swal(\"Action Done\", \"The Image Has Been Deleted\", \"success\");");
		}
		
	}

	
	private void removeFileFromImages(String fileURL) {
		// TODO Auto-generated method stub
		for(int i=0;i<imagesLanding.size();i++) {
			if(imagesLanding.get(i).equalsIgnoreCase(fileURL)) {
				imagesLanding.remove(i);
				images_deleted.add(fileURL);
				return;
			}
		}
	}

	public void invoiceDetails(int idUser) {

		carForInvoice=new carLanding();
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
	
	
	
	public void updateCarDataMain() {
		selectedFreight.setMainId(loginBean.getTheUserOfThisAccount());
		carLandingFacade.addcarLanding(selectedFreight);
		
		for(int i=0;i<images_deleted.size();i++) {

			carlandingimage cImage=new carlandingimage();
			cImage.setCarId(selectedFreight);
			cImage.setUrl(images_deleted.get(i));
			cImage.setType(carlandingimage.TYPE_AUCTION);
			cImage.setDeleted(true);
			carlandingimageFacade.addcarlandingimage(cImage);

		}
		
		for(int i=0;i<imagesLanding.size();i++) {
			carlandingimage cImage=new carlandingimage();
			cImage.setCarId(selectedFreight);
			cImage.setUrl(imagesLanding.get(i));
			cImage.setType(carlandingimage.TYPE_AUCTION);
			carlandingimageFacade.addcarlandingimage(cImage);
		}
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vehicleList.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
	public void addCarToAuction() {
		selectedFreight.setMainId(loginBean.getTheUserOfThisAccount());
		selectedFreight.setSelectUploadedOneOrAuction(true);

		selectedFreight.setState(stateOfCar.BidingState.getType());
		selectedFreight.setOurFees("100");
//		selectedFreight.set("100");
		carLandingFacade.addcarLanding(selectedFreight);
		
		
		for(int i=0;i<images_deleted.size();i++) {

			carlandingimage cImage=new carlandingimage();
			cImage.setCarId(selectedFreight);
			cImage.setUrl(images_deleted.get(i));
			cImage.setType(carlandingimage.TYPE_AUCTION);
			cImage.setDeleted(true);
			carlandingimageFacade.addcarlandingimage(cImage);

		}
		
		for(int i=0;i<imagesLanding.size();i++) {
			carlandingimage cImage=new carlandingimage();
			cImage.setCarId(selectedFreight);
			cImage.setUrl(imagesLanding.get(i));
			cImage.setType(carlandingimage.TYPE_AUCTION);
			carlandingimageFacade.addcarlandingimage(cImage);
		}
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/biding/vehicleList.jsf?faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void previewImageMain(FileUploadEvent event) {
		byte[] image =event.getFile().getContents();
		String fileName =saveImageToDirectory(image, System.getProperty("catalina.base")+"/images/");
		selectedFreight.setMainImageUpload(fileName);
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:imagesPanelMainUpload");
	}
	
	
	public void getTheAllListOfCarsWithDates() {
		
		listOfAddedCars=new ArrayList<carLanding>();
		listOfAddedCars = carLandingFacade.getAllBetweenDates(toCalendar(bidingDate), toCalendar(endDate));

	}
	
	public void deactiveAllListOfCarsWithDates() {
		listOfAddedCars=new ArrayList<carLanding>();
		listOfAddedCars = carLandingFacade.getAllBetweenDates(toCalendar(bidingDate), toCalendar(endDate));

		int listNumTotal = listOfAddedCars.size();
		for(int i=0;i<listOfAddedCars.size();i++) {
			listOfAddedCars.get(i).setActive(false);
			carLandingFacade.addcarLanding(listOfAddedCars.get(i));
			
			double newPercent = ((100 *(i-listNumTotal))/(listNumTotal-1))+100; 

			progressLoading = (int) + newPercent; 
		}
		progressLoading = 0;
		PrimeFaces.current().executeScript("location.reload();");


	}
	
	public void getTheBidListOfCarsWithDates() {
		listOfAddedCars=new ArrayList<carLanding>();
		listOfAddedCars = carLandingFacade.getAllBidBetweenDates(toCalendar(bidingDate), toCalendar(endDate));

	}
	
	
	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
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
	public String getFormatedDate(Calendar c) {
		String dateTime="";
		if(c!=null) {
		dateTime = String.valueOf(c.get(Calendar.DAY_OF_MONTH)) +"/"+
				   String.valueOf(c.get(Calendar.MONTH)+1) +"/"+
				   String.valueOf(c.get(Calendar.YEAR));
		}
		return dateTime;
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
	
public void updateTheLotImages(String lotImagesLink) {
	System.out.println("Dakrory: "+lotImagesLink);
	updateImagesWithLink(lotImagesLink);
    FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
    PrimeFaces.current().executeScript("hideDialog()");
       

	
	
}
public void addCarForMain() {
	
	
	selectedFreight=new carLanding();
	
	images=new ArrayList<String>();

	images_deleted=new ArrayList<String>();
 imagesLanding=new ArrayList<String>();
	try {
		FacesContext.getCurrentInstance()
		   .getExternalContext().redirect("/pages/secured/shipper/CarLandingPage/vitView.jsf?faces-redirect=true");
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


	public mycarsAppServiceImpl getMycarsFacade() {
		return mycarsFacade;
	}

	public void setMycarsFacade(mycarsAppServiceImpl mycarsFacade) {
		this.mycarsFacade = mycarsFacade;
	}

	

	public transportfeeAppServiceImpl getTransportfeeFacade() {
		return transportfeeFacade;
	}


	public void setTransportfeeFacade(transportfeeAppServiceImpl transportfeeFacade) {
		this.transportfeeFacade = transportfeeFacade;
	}


	public boolean isProgress() {
		return progress;
	}


	public void setProgress(boolean progress) {
		this.progress = progress;
	}

	

	public List<carLanding> getListOfCarsGroupByMake() {
		return listOfCarsGroupByMake;
	}


	public void setListOfCarsGroupByMake(List<carLanding> listOfCarsGroupByMake) {
		this.listOfCarsGroupByMake = listOfCarsGroupByMake;
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




	public Date getBidingDate() {
		return bidingDate;
	}




	public void setBidingDate(Date bidingDate) {
		this.bidingDate = bidingDate;
	}




	public Date getEndDate() {
		return endDate;
	}




	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}




	public notificationAppServiceImpl getNotificationFacade() {
		return notificationFacade;
	}




	public void setNotificationFacade(notificationAppServiceImpl notificationFacade) {
		this.notificationFacade = notificationFacade;
	}




	public List<carLanding> getListOfFilteredCars() {
		return listOfFilteredCars;
	}




	public void setListOfFilteredCars(List<carLanding> listOfFilteredCars) {
		this.listOfFilteredCars = listOfFilteredCars;
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


	public String getSearchModel() {
		return searchModel;
	}


	public void setSearchModel(String searchModel) {
		this.searchModel = searchModel;
	}


	public List<carLanding> getListOfCarsGroupByModel() {
		return listOfCarsGroupByModel;
	}


	public void setListOfCarsGroupByModel(List<carLanding> listOfCarsGroupByModel) {
		this.listOfCarsGroupByModel = listOfCarsGroupByModel;
	}


	public List<carLanding> getListOfCarsGroupByCategory() {
		return listOfCarsGroupByCategory;
	}


	public void setListOfCarsGroupByCategory(List<carLanding> listOfCarsGroupByCategory) {
		this.listOfCarsGroupByCategory = listOfCarsGroupByCategory;
	}


	public carlandingimageAppServiceImpl getCarlandingimageFacade() {
		return carlandingimageFacade;
	}


	public void setCarlandingimageFacade(carlandingimageAppServiceImpl carlandingimageFacade) {
		this.carlandingimageFacade = carlandingimageFacade;
	}


	public List<String> getImages_deleted() {
		return images_deleted;
	}


	public void setImages_deleted(List<String> images_deleted) {
		this.images_deleted = images_deleted;
	}


	public List<String> getImagesLanding() {
		return imagesLanding;
	}


	public void setImagesLanding(List<String> imagesLanding) {
		this.imagesLanding = imagesLanding;
	}

	
	
	
}
