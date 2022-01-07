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

import main.com.carService.product.product;
import main.com.carService.product.productAppServiceImpl;
import main.com.carService.productImage.productimage;
import main.com.carService.productImage.productimageAppServiceImpl;
import main.com.carService.tools.Constants;
import main.com.carService.form_settings.form_settingsAppServiceImpl;
import main.com.carService.loginNeeds.user;
import main.com.carService.moneyBox.moneybox;
import main.com.carService.moneyBox.moneyboxConfig;

@ManagedBean(name = "normalUserProductBean")
@SessionScoped
public class normalUserProductBean implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842440236767354796L;


	@ManagedProperty(value = "#{loginBean}")
	private main.com.carService.loginNeeds.loginBean loginBean; 
	 

	
	

	@ManagedProperty(value = "#{productFacadeImpl}")
	private productAppServiceImpl productFacade;
	

	
	@ManagedProperty(value = "#{productimageFacadeImpl}")
	private productimageAppServiceImpl productimageFacade;
	
	private int selectedProductState=0; 
	private List<product> allProducts;
	
	private product selectedProduct;
	private product addNewProduct;
	
	
	private List<String> productStates;
	

	private  String estimatedDateOfDelivery;
	private  String deliveryDate;
	private  String added_date;
	private  String lastupdate_date;


	private List<String> images;
	private List<String> docs;


	
	private List<String> images_deleted;
	private List<String> docs_deleted;

	private int products_Ship_UnderReview = 0;
	private int products_PayAndShip_UnderReview=0;
	

	private int products_Ship_Rejected = 0;
	private int products_PayAndShip_Rejected=0;
	

	private int products_Ship_DeliveredByUs = 0;
	private int products_PayAndShip_PayedByCustomer=0;
	

	private int products_Ship_InShipping = 0;
	private int products_PayAndShip_InShipping=0;
	

	private float dollarToDinar = 0;
	


	@ManagedProperty(value = "#{form_settingsFacadeImpl}")
	private form_settingsAppServiceImpl form_settingsFacade;

	private int products_Ship_DeliveredByCustomer = 0;
	private int products_PayAndShip_DeliveredByCustomer=0;
	

	private int totalNumberOfShippingOnly = 0;
	private int totalNumberOfShippingAndBuy=0;
	

	private float totalPrice=0;
	private float cbm_constant=0;
	
	private List<user> allUsers;
	private user selectedUser;
	@PostConstruct
	public void init() {
		
		productStates = new ArrayList<String>();
		for(int i=0;i<product.stateString.length;i++) {
			productStates.add(product.stateString[i]);
		}
		
		
		
		refresh();
		
		

		selectedProductState=0;
	}
	
	
	
	
public void refreshForProduct(){
		
		try {
		
		dollarToDinar = Float.valueOf(form_settingsFacade.getById(1).getValue());
		cbm_constant = Float.valueOf(form_settingsFacade.getById(2).getValue());
		}catch(NullPointerException exp) {
			
		}
	
		
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
					selectedProduct=productFacade.getById(id);
					//Here Get the images For the main 
					/**
					 * 
					 */

					 try {

				        	images = new ArrayList<String>();
				        	

				        	images.add(selectedProduct.getMainImageLink());

				        	

				    		//FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("myCarousel");
				          	  
						} catch (Exception e1) {

				        	System.out.println("ImagesLoadedError: "+e1.toString());
				        	
						}catch (Error e1) {

				        	System.out.println("ImagesLoadedError: "+e1.toString());
				        	
					
						}
					 List<productimage> allImageFromDatabase = productimageFacade.getAllByproductIdAndType(id, productimage.TYPE_PIC);
			        	
						
					 if(allImageFromDatabase!=null) {
			        	for(int i=0;i<allImageFromDatabase.size();i++) {
		    				images.add("/images/?file="+allImageFromDatabase.get(i).getUrl());
		    			}
					 }
					 
					 
					 allImageFromDatabase = productimageFacade.getAllByproductIdAndType(id, productimage.TYPE_DOC);
			        	
						
					 if(allImageFromDatabase!=null) {
			        	for(int i=0;i<allImageFromDatabase.size();i++) {
		    				images.add("/images/?file="+allImageFromDatabase.get(i).getUrl());
		    			}
					 }
		    			

		    			if(images.size()==0) {
		    				images.add("https://almzzad.com/resources/Image/caromoto logo-04.png");
				    		
		    			}
		    			
		    			

			        	System.out.println("Images Loaded Error: "+String.valueOf(images.size()));
			        	
					//Get the userBid if applicable
					if(loginBean.isLoggedIn()) {

						
						System.out.println("Ahmed CarBid3");
						//Make this car added to my watch List
						

						FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("setWatchListData");
						FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("setWatchListDataMobile");
						
					
					}
					
					
					
					
					
					
					
					
					
				}
			}
		catch(Exception ex){
			 
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
	
	
	
	public void goNextproduct(int id) {

		product product = productFacade.getNextRecord(id);
			
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/public/productsForDetails.jsf?id="+product.getId()+"&faces-redirect=true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void goPreviousproduct(int id) {

		product product = productFacade.getPreviousRecord(id);
			
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/public/productsForDetails.jsf?id="+product.getId()+"&faces-redirect=true");
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
		if(productFacade.getAllBytypeOfOrderForNormalUser(userNewId.getId(), product.TYPE_SHIPPING)!=null) {
		totalNumberOfShippingOnly = productFacade.getAllBytypeOfOrderForNormalUser(userNewId.getId(), product.TYPE_SHIPPING).size();
		}else {
			totalNumberOfShippingOnly =0;
		}
		
		if(productFacade.getAllBytypeOfOrderForNormalUser(userNewId.getId(), product.TYPE_BUY_SHIPPING)!=null) {
		totalNumberOfShippingAndBuy = productFacade.getAllBytypeOfOrderForNormalUser(userNewId.getId(), product.TYPE_BUY_SHIPPING).size();
		}else {
			totalNumberOfShippingAndBuy=0;
		}
		
		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_AddedByCustomer_REVISE, product.TYPE_SHIPPING)!=null) {
			products_Ship_UnderReview =  productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_AddedByCustomer_REVISE, product.TYPE_SHIPPING).size();
		}else {
			products_Ship_UnderReview=0;
		}
		
		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_AddedByCustomer_REVISE, product.TYPE_BUY_SHIPPING)!=null) {
		products_PayAndShip_UnderReview=productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_AddedByCustomer_REVISE, product.TYPE_BUY_SHIPPING).size();
		}else {
			products_PayAndShip_UnderReview=0;
		}
		
		
		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_Rejected, product.TYPE_SHIPPING)!=null) {
		products_Ship_Rejected =  productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_Rejected, product.TYPE_SHIPPING).size();
		}else{
			products_Ship_Rejected=0;
		}
		
		
		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_Rejected, product.TYPE_BUY_SHIPPING)!=null) {
		products_PayAndShip_Rejected=productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_Rejected, product.TYPE_BUY_SHIPPING).size();
		}else {
			products_PayAndShip_Rejected=0;
		}

		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_DeliveredByUs, product.TYPE_SHIPPING)!=null) {
		products_Ship_DeliveredByUs =  productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_DeliveredByUs, product.TYPE_SHIPPING).size();
		}else {
			products_Ship_DeliveredByUs=0;
		}
		
		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_PayedByCustomer, product.TYPE_BUY_SHIPPING)!=null) {
		products_PayAndShip_PayedByCustomer=productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_PayedByCustomer, product.TYPE_BUY_SHIPPING).size();
		}else {
			products_PayAndShip_PayedByCustomer=0;
		}
		
		
		
		
		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_DeliveredByCustomer, product.TYPE_SHIPPING)!=null) {
		products_Ship_DeliveredByCustomer =  productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_DeliveredByCustomer, product.TYPE_SHIPPING).size();
		}else {
			products_Ship_DeliveredByCustomer=0;
		}
		
		
		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_DeliveredByCustomer, product.TYPE_BUY_SHIPPING)!=null) {
		products_PayAndShip_DeliveredByCustomer=productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_DeliveredByCustomer, product.TYPE_BUY_SHIPPING).size();
		}else {
			products_PayAndShip_DeliveredByCustomer=0;
		}
		
		
		
		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_SHIPMENT, product.TYPE_SHIPPING)!=null) {
			products_Ship_InShipping =  productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_SHIPMENT, product.TYPE_SHIPPING).size();
		}else {
			products_Ship_InShipping=0;
		}
		
		
		if(productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_SHIPMENT, product.TYPE_BUY_SHIPPING)!=null) {
			products_PayAndShip_InShipping=productFacade.getAllBytypeOfOrderAndStateForNormalUser(userNewId.getId(),  product.STATE_SHIPMENT, product.TYPE_BUY_SHIPPING).size();
		}else {
			products_PayAndShip_InShipping=0;
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
	
	public void getProductWithId() {

		

		dollarToDinar = Float.valueOf(form_settingsFacade.getById(1).getValue());
		cbm_constant = Float.valueOf(form_settingsFacade.getById(2).getValue());
		
		
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
					
					images=new ArrayList<String>();
					docs=new ArrayList<String>();
					
					images_deleted=new ArrayList<String>();
					docs_deleted=new ArrayList<String>();
					
					estimatedDateOfDelivery="";
					deliveryDate="";
					
					addNewProduct=new product();
					
					addNewProduct=productFacade.getById(id);
					List<productimage> imagesPics = productimageFacade.getAllByproductIdAndType(id, productimage.TYPE_PIC);
					if(imagesPics!=null) {
					for(int i=0;i<imagesPics.size();i++) {
						images.add(imagesPics.get(i).getUrl());

					}
					}
					
					List<productimage> docssPics = productimageFacade.getAllByproductIdAndType(id, productimage.TYPE_DOC);
					if(docssPics!=null) {
					for(int i=0;i<docssPics.size();i++) {
						docs.add(docssPics.get(i).getUrl());

					}
					}

					
					estimatedDateOfDelivery=getStringFromCalendar(addNewProduct.getEstimatedDateOfDelievery());
					deliveryDate=getStringFromCalendar(addNewProduct.getDeliveryDate());
					lastupdate_date=getStringFromCalendar(addNewProduct.getLastUpdate());
					added_date=getStringFromCalendar(addNewProduct.getAdd_datetime());
					if(addNewProduct.getTypeOfOrder().equals(product.TYPE_BUY_SHIPPING)) {
						float number = 0;
						
						try {
							number = Float.parseFloat(addNewProduct.getLengthOfProduct());
						}catch(Exception ex) {
							
						}
					totalPrice = addNewProduct.getCommision()
							+addNewProduct.getFees()
							+addNewProduct.getShipmentFees()
							+(addNewProduct.getOrderPrice()*number);
					}else {
						totalPrice = addNewProduct.getCommision()
								+addNewProduct.getFees()
								+addNewProduct.getShipmentFees();
					}

					System.out.println("Ahmed Load3");
					FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm");
					
				}
		
	}
	
public void refresh(){
		

		releaseVariablesForMain();
			
		
	}
	





public void filterProductBySelect() {
	filterProductBySelectFirstTime();
	
	try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/secured/normalUsers/products/productsList.jsf?faces-redirect=true");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public void filterProductBySelectForAdmin() {
	System.out.print("Ahmed Select:");
	System.out.print(String.valueOf(selectedProductState));
	filterProductBySelectFirstTime();
	
	try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/secured/userData/productList.jsf?faces-redirect=true");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




private Calendar setCalendarFromString(String productgoRecievedDate2) {

	Calendar cal = null;
	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-dd-MM HH:mm:ss"); 
	try {
		if(!productgoRecievedDate2.equals("")) {
			cal=Calendar.getInstance();
			Date date=formatter.parse(productgoRecievedDate2);
			cal.setTime(date);
		}
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	return cal;
}


public void updateDataMain() {


	
	addNewProduct.setEstimatedDateOfDelievery(setCalendarFromString(estimatedDateOfDelivery));
	addNewProduct.setLastUpdate(setCalendarFromString(lastupdate_date));
	addNewProduct.setDeliveryDate(setCalendarFromString(deliveryDate));

	
	

	
	boolean isValid=true;
	if(isValid) {
	
	try {
		addNewProduct.setLandingCheck(false);
		productFacade.addproduct(addNewProduct);
		for(int i=0;i<images_deleted.size();i++) {
			System.out.println("Ahmed File: "+String.valueOf(images_deleted.get(i)));
			productimage cImage=new productimage();
			cImage.setProductId(addNewProduct);
			cImage.setUrl(images_deleted.get(i));
			cImage.setType(productimage.TYPE_PIC);
			cImage.setDeleted(true);
			productimageFacade.addproductimage(cImage);
			addNewProduct.setPhotoExist(false);

		}
		
		for(int i=0;i<docs_deleted.size();i++) {
			productimage cDocs=new productimage();
			cDocs.setProductId(addNewProduct);
			cDocs.setUrl(docs_deleted.get(i));
			cDocs.setType(productimage.TYPE_DOC);
			cDocs.setDeleted(true);
			productimageFacade.addproductimage(cDocs);
			addNewProduct.setDocExist(false);
		}
		
		
		for(int i=0;i<images.size();i++) {
			productimage cImage=new productimage();
			cImage.setProductId(addNewProduct);
			cImage.setUrl(images.get(i));
			cImage.setType(productimage.TYPE_PIC);
			productimageFacade.addproductimage(cImage);
			addNewProduct.setPhotoExist(true);
		}
		
		for(int i=0;i<docs.size();i++) {
			productimage cDocs=new productimage();
			cDocs.setProductId(addNewProduct);
			cDocs.setUrl(docs.get(i));
			cDocs.setType(productimage.TYPE_DOC);
			productimageFacade.addproductimage(cDocs);
			addNewProduct.setDocExist(true);
		}
		
		
		

		addNewProduct.setLandingCheck(false);
		
		productFacade.addproduct(addNewProduct);
			
		
		
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your product has been added.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");

		sendUpdateToAll(addNewProduct);
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/userData/productList.jsf?faces-redirect=true");
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
				"			text: 'Check the Madatory fields',\r\n" + 
				"			left:\"2%\"\r\n" + 
				"		});");
	}
}



private void sendUpdateToAll(product addNewProduct2) {
	
	user shipperObject =addNewProduct2.getNormalUserId();
	user shipperIdMail =null;
	if(shipperObject!=null) {
		Integer shipperId = shipperObject.getId();
		shipperIdMail=loginBean.getUserDataFacede().getById(shipperId);
	}
	
	
	
	
	
	
	
	
	
	
	if(shipperIdMail!=null)
		Constants.sendEmailUpdateFormatProduct(addNewProduct2,shipperIdMail.getFirstName(), shipperIdMail.getEmail(), shipperIdMail.getEmail());
	
	
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


public void deleteProduct() {
	 FacesContext context = FacesContext.getCurrentInstance();
	 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	 Integer productId = Integer.valueOf((String) map.get("productId"));
	 
	 product deletedCar = productFacade.getById(productId);
	 deletedCar.setDeleted(true);
	 try {

			addNewProduct.setLandingCheck(false);
		productFacade.addproduct(deletedCar);
		PrimeFaces.current().executeScript("swal(\"Action Done\", \"The Product Has Been Deleted\", \"success\");");
		
		 try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/secured/userData/productList.jsf?faces-redirect=true");
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
	 
		
	 
}



public void addProductForMain() {
	
	
	addNewProduct=new product();
	
	images=new ArrayList<String>();

	images_deleted=new ArrayList<String>();
	 docs=new ArrayList<String>();
	 docs_deleted=new ArrayList<String>();
	try {
		FacesContext.getCurrentInstance()
		   .getExternalContext().redirect("/pages/secured/shipper/productPage/vitView.jsf?faces-redirect=true");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public void selectproductRowForMain() {

	 FacesContext context = FacesContext.getCurrentInstance();
	 Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	 Integer productId = Integer.valueOf((String) map.get("productId"));
	
	 
	 addNewProduct=productFacade.getById(productId);
	 
		images_deleted=new ArrayList<String>();
	 images=new ArrayList<String>();
	 List<productimage> allImages = productimageFacade.getAllByproductIdAndType(addNewProduct.getId(),productimage.TYPE_PIC);
		
	 System.out.println("Done");
	 
	 if(allImages!=null) {
		for(int i=0;i<allImages.size();i++) {
			images.add(allImages.get(i).getUrl());
		}
		}
		
		
		List<productimage> allDoc = productimageFacade.getAllByproductIdAndType(addNewProduct.getId(),productimage.TYPE_DOC);
		if(allDoc!=null) {
		for(int i=0;i<allDoc.size();i++) {
			docs.add(allDoc.get(i).getUrl());
		}
		}
		
	PrimeFaces.current().executeScript("showDialog('Product');");

	
	try {
		FacesContext.getCurrentInstance()
		   .getExternalContext().redirect("/pages/secured/shipper/productPage/vitViewEdit.jsf?faces-redirect=true");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




public void saveNewProductDataMainForLanding() {
	loginBean.getTheUserOfThisAccount();
	
	addNewProduct.setEstimatedDateOfDelievery(setCalendarFromString(estimatedDateOfDelivery));
	addNewProduct.setDeliveryDate(setCalendarFromString(deliveryDate));

	float height = 0;
	float width = 0;
	float depth = 0;
	float number = 0;
	
	try {
		height = Float.parseFloat(addNewProduct.getHeightOfProduct());
	}catch(Exception ex) {
		
	}
	try {
		width = Float.parseFloat(addNewProduct.getWidthOfProduct());
	}catch(Exception ex) {
		
	}
	try {
		depth = Float.parseFloat(addNewProduct.getDepthOfProduct());
	}catch(Exception ex) {
		
	}
	try {
		number = Float.parseFloat(addNewProduct.getLengthOfProduct());
	}catch(Exception ex) {
		
	}
	
	addNewProduct.setShipmentFees(cbm_constant*height*width*depth*number);
	
	
	boolean isValid=true;
	if(isValid) {
		
		if(true) {
	
	try {
		addNewProduct.setState(product.STATE_AddedByCustomer_REVISE);
		
		
		if(addNewProduct.getTypeOfOrder().equals(product.TYPE_BUY_SHIPPING)) {
			try {
				number = Float.parseFloat(addNewProduct.getLengthOfProduct());
			}catch(Exception ex) {
				
			}
			addNewProduct.setFees((float) (addNewProduct.getOrderPrice()*0.1*number));
			}else {

				addNewProduct.setFees((float) (0));
			}
		selectedProductState = -1;

		addNewProduct.setLandingCheck(true);
		productFacade.addproduct(addNewProduct);
		
		for(int i=0;i<images_deleted.size();i++) {
			productimage cImage=new productimage();
			cImage.setProductId(addNewProduct);
			cImage.setUrl(images_deleted.get(i));
			cImage.setType(productimage.TYPE_PIC);
			cImage.setDeleted(true);
			productimageFacade.addproductimage(cImage);
			addNewProduct.setPhotoExist(false);

		}
		
		for(int i=0;i<docs_deleted.size();i++) {
			productimage cDocs=new productimage();
			cDocs.setProductId(addNewProduct);
			cDocs.setUrl(docs_deleted.get(i));
			cDocs.setType(productimage.TYPE_DOC);
			cDocs.setDeleted(true);
			productimageFacade.addproductimage(cDocs);
			addNewProduct.setDocExist(false);
		}
		
		
		
		
		for(int i=0;i<images.size();i++) {
			productimage cImage=new productimage();
			cImage.setProductId(addNewProduct);
			cImage.setUrl(images.get(i));
			cImage.setType(productimage.TYPE_PIC);
			productimageFacade.addproductimage(cImage);
			addNewProduct.setPhotoExist(true);

		}
		
		for(int i=0;i<docs.size();i++) {
			productimage cDocs=new productimage();
			cDocs.setProductId(addNewProduct);
			cDocs.setUrl(docs.get(i));
			cDocs.setType(productimage.TYPE_DOC);
			productimageFacade.addproductimage(cDocs);
			addNewProduct.setDocExist(true);
		}
		

		
		
		
		selectedProductState = product.STATE_AddedByCustomer_REVISE;

		addNewProduct.setLandingCheck(true);
		productFacade.addproduct(addNewProduct);
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your product has been added.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/shipper/productPage/productList.jsf?faces-redirect=true");
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
					"			text: 'This product is already Registered',\r\n" + 
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

public void saveNewProductDataMain() {
	loginBean.getTheUserOfThisAccount();
	
	addNewProduct.setEstimatedDateOfDelievery(setCalendarFromString(estimatedDateOfDelivery));
	addNewProduct.setDeliveryDate(setCalendarFromString(deliveryDate));
	
	
	
	float height = 0;
	float width = 0;
	float depth = 0;
	float number = 0;
	
	try {
		height = Float.parseFloat(addNewProduct.getHeightOfProduct());
	}catch(Exception ex) {
		
	}
	try {
		width = Float.parseFloat(addNewProduct.getWidthOfProduct());
	}catch(Exception ex) {
		
	}
	try {
		depth = Float.parseFloat(addNewProduct.getDepthOfProduct());
	}catch(Exception ex) {
		
	}
	try {
		number = Float.parseFloat(addNewProduct.getLengthOfProduct());
	}catch(Exception ex) {
		
	}
	
	addNewProduct.setShipmentFees(cbm_constant*height*width*depth*number);
	
	boolean isValid=true;
	if(isValid) {
		
		if(true) {
	
	try {
		addNewProduct.setState(product.STATE_AddedByCustomer_REVISE);
		if(addNewProduct.getTypeOfOrder().equals(product.TYPE_BUY_SHIPPING)) {
		try {
			number = Float.parseFloat(addNewProduct.getLengthOfProduct());
		}catch(Exception ex) {
			
		}
		addNewProduct.setFees((float) (addNewProduct.getOrderPrice()*0.1*number));
		}else {

			addNewProduct.setFees((float) (0));
		}
		selectedProductState = -1;

		addNewProduct.setLandingCheck(false);
		productFacade.addproduct(addNewProduct);
		
		for(int i=0;i<images_deleted.size();i++) {
			productimage cImage=new productimage();
			cImage.setProductId(addNewProduct);
			cImage.setUrl(images_deleted.get(i));
			cImage.setType(productimage.TYPE_PIC);
			cImage.setDeleted(true);
			productimageFacade.addproductimage(cImage);
			addNewProduct.setPhotoExist(false);

		}
		
		for(int i=0;i<docs_deleted.size();i++) {
			productimage cDocs=new productimage();
			cDocs.setProductId(addNewProduct);
			cDocs.setUrl(docs_deleted.get(i));
			cDocs.setType(productimage.TYPE_DOC);
			cDocs.setDeleted(true);
			productimageFacade.addproductimage(cDocs);
			addNewProduct.setDocExist(false);
		}
		
		
		
		
		for(int i=0;i<images.size();i++) {
			productimage cImage=new productimage();
			cImage.setProductId(addNewProduct);
			cImage.setUrl(images.get(i));
			cImage.setType(productimage.TYPE_PIC);
			productimageFacade.addproductimage(cImage);
			addNewProduct.setPhotoExist(true);

		}
		
		for(int i=0;i<docs.size();i++) {
			productimage cDocs=new productimage();
			cDocs.setProductId(addNewProduct);
			cDocs.setUrl(docs.get(i));
			cDocs.setType(productimage.TYPE_DOC);
			productimageFacade.addproductimage(cDocs);
			addNewProduct.setDocExist(true);
		}
		

		
		
		
		selectedProductState = product.STATE_AddedByCustomer_REVISE;

		addNewProduct.setLandingCheck(false);
		productFacade.addproduct(addNewProduct);
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Your product has been added.',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
		
		try {
			FacesContext.getCurrentInstance()
			   .getExternalContext().redirect("/pages/secured/normalUsers/products/productsList.jsf?faces-redirect=true");
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
					"			text: 'This product is already Registered',\r\n" + 
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



public void makePaymentForAProduct() {
	moneybox mB1 = loginBean.moneyboxDataFacede.getByUserId(addNewProduct.getNormalUserId().getId());
	
	if((mB1.getDepositedMoney())-totalPrice>=0) {
		moneyboxConfig.makeaPayment(totalPrice, addNewProduct.getNormalUserId(), loginBean.getUserDataFacede(), loginBean.moneyboxDataFacede, loginBean.getMoneybox_transaction_detailsDataFacede());
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Success',\r\n" + 
				"			text: 'Payment Done',\r\n" + 
				"			type: 'success'\r\n" + 
				"		});");
	}else {
		PrimeFaces.current().executeScript("new PNotify({\r\n" + 
				"			title: 'Check this ',\r\n" + 
				"			text: 'No amount available',\r\n" + 
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
		   .getExternalContext().redirect("/pages/secured/userData/productsList.jsf?faces-redirect=true");
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
	if(typeOfFile==productimage.TYPE_DOC) {
			removeFileFromDoc(fileURL);
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("aspnetForm:docsPanel");
		PrimeFaces.current().executeScript("swal(\"Action Done\", \"The Document Has Been Deleted\", \"success\");");
	}else if(typeOfFile==productimage.TYPE_PIC) {
		
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

	estimatedDateOfDelivery="";
	deliveryDate="";
	
	addNewProduct=new product();

	addNewProduct.setMainId(loginBean.getUserDataFacede().getById(1));
	addNewProduct.setNormalUserId(loginBean.getTheUserOfThisAccount());

	allProducts=new ArrayList<product>();
	
	filterProductBySelectFirstTime();
}
	


public void filterProductBySelectFirstTime() {

	allProducts=new ArrayList<product>();

		user userNewId=loginBean.getTheUserOfThisAccount();
		if(userNewId.getId()!=null) {
		if(userNewId.getRole() == user.ROLE_MAIN ) {
			if(selectedProductState==-1) {
				//this for all

				allProducts = productFacade.getAllForMainUser(userNewId.getId());
			}else {
				List<product> SentMain = productFacade.getAllByStateForMainUser(userNewId.getId(),selectedProductState);
				
				if(SentMain!=null)
					allProducts.addAll(SentMain);
				}
		}else {
			if(selectedProductState==-1) {
				//this for all

				allProducts = productFacade.getAllForNormalUser(userNewId.getId());
			}else {
				List<product> SentMain = productFacade.getAllByStateForNormalUser(userNewId.getId(),selectedProductState);
				
				if(SentMain!=null)
					allProducts.addAll(SentMain);
				}
		}

		}
	
	}
	
	



public main.com.carService.loginNeeds.loginBean getLoginBean() {
	return loginBean;
}


public void setLoginBean(main.com.carService.loginNeeds.loginBean loginBean) {
	this.loginBean = loginBean;
}


public productAppServiceImpl getProductFacade() {
	return productFacade;
}


public void setProductFacade(productAppServiceImpl productFacade) {
	this.productFacade = productFacade;
}


public productimageAppServiceImpl getProductimageFacade() {
	return productimageFacade;
}


public void setProductimageFacade(productimageAppServiceImpl productimageFacade) {
	this.productimageFacade = productimageFacade;
}


public int getSelectedProductState() {
	return selectedProductState;
}


public void setSelectedProductState(int selectedProductState) {
	this.selectedProductState = selectedProductState;
}


public List<product> getAllProducts() {
	return allProducts;
}


public void setAllProducts(List<product> allProducts) {
	this.allProducts = allProducts;
}


public product getSelectedProduct() {
	return selectedProduct;
}


public void setSelectedProduct(product selectedProduct) {
	this.selectedProduct = selectedProduct;
}


public product getAddNewProduct() {
	return addNewProduct;
}


public void setAddNewProduct(product addNewProduct) {
	this.addNewProduct = addNewProduct;
}


public List<String> getProductStates() {
	return productStates;
}


public void setProductStates(List<String> productStates) {
	this.productStates = productStates;
}


public String getEstimatedDateOfDelivery() {
	return estimatedDateOfDelivery;
}


public void setEstimatedDateOfDelivery(String estimatedDateOfDelivery) {
	this.estimatedDateOfDelivery = estimatedDateOfDelivery;
}


public String getDeliveryDate() {
	return deliveryDate;
}


public void setDeliveryDate(String deliveryDate) {
	this.deliveryDate = deliveryDate;
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


public int getProducts_Ship_UnderReview() {
	return products_Ship_UnderReview;
}


public void setProducts_Ship_UnderReview(int products_Ship_UnderReview) {
	this.products_Ship_UnderReview = products_Ship_UnderReview;
}


public int getProducts_PayAndShip_UnderReview() {
	return products_PayAndShip_UnderReview;
}


public void setProducts_PayAndShip_UnderReview(int products_PayAndShip_UnderReview) {
	this.products_PayAndShip_UnderReview = products_PayAndShip_UnderReview;
}


public int getProducts_Ship_Rejected() {
	return products_Ship_Rejected;
}


public void setProducts_Ship_Rejected(int products_Ship_Rejected) {
	this.products_Ship_Rejected = products_Ship_Rejected;
}


public int getProducts_PayAndShip_Rejected() {
	return products_PayAndShip_Rejected;
}


public void setProducts_PayAndShip_Rejected(int products_PayAndShip_Rejected) {
	this.products_PayAndShip_Rejected = products_PayAndShip_Rejected;
}


public int getProducts_Ship_DeliveredByUs() {
	return products_Ship_DeliveredByUs;
}


public void setProducts_Ship_DeliveredByUs(int products_Ship_DeliveredByUs) {
	this.products_Ship_DeliveredByUs = products_Ship_DeliveredByUs;
}


public int getProducts_PayAndShip_PayedByCustomer() {
	return products_PayAndShip_PayedByCustomer;
}


public void setProducts_PayAndShip_PayedByCustomer(int products_PayAndShip_PayedByCustomer) {
	this.products_PayAndShip_PayedByCustomer = products_PayAndShip_PayedByCustomer;
}


public int getProducts_Ship_InShipping() {
	return products_Ship_InShipping;
}


public void setProducts_Ship_InShipping(int products_Ship_InShipping) {
	this.products_Ship_InShipping = products_Ship_InShipping;
}


public int getProducts_PayAndShip_InShipping() {
	return products_PayAndShip_InShipping;
}


public void setProducts_PayAndShip_InShipping(int products_PayAndShip_InShipping) {
	this.products_PayAndShip_InShipping = products_PayAndShip_InShipping;
}


public int getProducts_Ship_DeliveredByCustomer() {
	return products_Ship_DeliveredByCustomer;
}


public void setProducts_Ship_DeliveredByCustomer(int products_Ship_DeliveredByCustomer) {
	this.products_Ship_DeliveredByCustomer = products_Ship_DeliveredByCustomer;
}


public int getProducts_PayAndShip_DeliveredByCustomer() {
	return products_PayAndShip_DeliveredByCustomer;
}


public void setProducts_PayAndShip_DeliveredByCustomer(int products_PayAndShip_DeliveredByCustomer) {
	this.products_PayAndShip_DeliveredByCustomer = products_PayAndShip_DeliveredByCustomer;
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


public float getTotalPrice() {
	return totalPrice;
}


public void setTotalPrice(float totalPrice) {
	this.totalPrice = totalPrice;
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



public String getAdded_date() {
	return added_date;
}


public void setAdded_date(String added_date) {
	this.added_date = added_date;
}


public String getLastupdate_date() {
	return lastupdate_date;
}


public void setLastupdate_date(String lastupdate_date) {
	this.lastupdate_date = lastupdate_date;
}


public static long getSerialversionuid() {
	return serialVersionUID;
}




public float getCbm_constant() {
	return cbm_constant;
}




public void setCbm_constant(float cbm_constant) {
	this.cbm_constant = cbm_constant;
}




public float getDollarToDinar() {
	return dollarToDinar;
}




public void setDollarToDinar(float dollarToDinar) {
	this.dollarToDinar = dollarToDinar;
}




public form_settingsAppServiceImpl getForm_settingsFacade() {
	return form_settingsFacade;
}




public void setForm_settingsFacade(form_settingsAppServiceImpl form_settingsFacade) {
	this.form_settingsFacade = form_settingsFacade;
}




}
