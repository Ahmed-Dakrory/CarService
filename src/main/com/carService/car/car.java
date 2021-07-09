package main.com.carService.car;


import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import main.com.carService.consignee.consignee;
import main.com.carService.customer.customer;
import main.com.carService.loginNeeds.user;
import main.com.carService.mainTwo.mainTwo;
import main.com.carService.shipper.shipper;
import main.com.carService.vendor.vendor;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="car.getAll",
		     query="SELECT c FROM car c where c.deleted = false"
		     )
	,
	@NamedQuery(name="car.getById",
	query = "from car d where d.id = :id and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getByVin",
	query = "from car d where d.uuid = :uuid and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForMainUser",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and mainId = :userId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForMainUserTwo",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and mainTwoId = :mainTwoId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForVendor",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and vendorId = :vendorId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForShipper",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and shipperId = :shipperId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForCustomer",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and customerId = :customerId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForConsignee",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and consigneeId = :consigneeId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllWareHouseFornormalUserId",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and normalUserId = :normalUserId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForMainUser",
	query = "from car d where (d.state = 4 or d.state = 5) and mainId = :userId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForMainUserTwo",
	query = "from car d where (d.state = 4 or d.state = 5) and mainTwoId = :mainTwoId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForVendor",
	query = "from car d where (d.state = 4 or d.state = 5) and vendorId = :vendorId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForShipper",
	query = "from car d where (d.state = 4 or d.state = 5) and shipperId = :shipperId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForCustomer",
	query = "from car d where (d.state = 4 or d.state = 5) and customerId = :customerId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForConsignee",
	query = "from car d where (d.state = 4 or d.state = 5) and consigneeId = :consigneeId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllDryCargoFornormalUserId",
	query = "from car d where (d.state = 4 or d.state = 5) and normalUserId = :normalUserId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightInTransitForMainUser",
	query = "from car d where (d.state = 6 or d.state = 7) and mainId = :userId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForMainUserTwo",
	query = "from car d where (d.state = 6 or d.state = 7) and mainTwoId = :mainTwoId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForVendor",
	query = "from car d where (d.state = 6 or d.state = 7) and vendorId = :vendorId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForShipper",
	query = "from car d where (d.state = 6 or d.state = 7) and shipperId = :shipperId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForCustomer",
	query = "from car d where (d.state = 6 or d.state = 7) and customerId = :customerId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForConsignee",
	query = "from car d where (d.state = 6 or d.state = 7) and consigneeId = :consigneeId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitFornormalUserId",
	query = "from car d where (d.state = 6 or d.state = 7) and normalUserId = :normalUserId and d.deleted = false"
			)
,
	
	@NamedQuery(name="car.getAllFrightSentForPaymentForMainUser",
	query = "from car d where (d.state = 8) and mainId = :userId and d.deleted = false"
			)
	

	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForMainUserTwo",
	query = "from car d where (d.state = 8) and mainTwoId = :mainTwoId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightWithStateForMainUserTwo",
	query = "from car d where (d.state = :state) and mainTwoId = :mainTwoId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightForMainUserTwo",
	query = "from car d where  mainTwoId = :mainTwoId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForVendor",
	query = "from car d where (d.state = 8) and vendorId = :vendorId and d.deleted = false"
			)
	
	
	,
	@NamedQuery(name="car.getAllFrightWithStateForVendor",
	query = "from car d where (d.state = :state) and vendorId = :vendorId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightForVendor",
	query = "from car d where  vendorId = :vendorId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForShipper",
	query = "from car d where (d.state = 8) and shipperId = :shipperId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightWithStateForShipper",
	query = "from car d where (d.state = :state) and shipperId = :shipperId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightForShipper",
	query = "from car d where shipperId = :shipperId and d.deleted = false"
			)
	
	
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForCustomer",
	query = "from car d where (d.state = 8) and customerId = :customerId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightWithStateForCustomer",
	query = "from car d where (d.state = :state) and customerId = :customerId and d.deleted = false"
			)
	
	
	,
	@NamedQuery(name="car.getAllFrightForCustomer",
	query = "from car d where  customerId = :customerId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForConsignee",
	query = "from car d where (d.state = 8) and consigneeId = :consigneeId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightWithStateForConsignee",
	query = "from car d where (d.state = :state) and consigneeId = :consigneeId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllFrightForConsignee",
	query = "from car d where consigneeId = :consigneeId and d.deleted = false"
			)
	
	
	,
	@NamedQuery(name="car.getAllByStateForNormalUser",
	query = "from car d where (d.state = :state) and normalUserId = :normalUserId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllForNormalUser",
	query = "from car d where  normalUserId = :normalUserId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllByStateForMainUser",
	query = "from car d where (d.state = :state) and mainId = :userId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllBytypeOfOrderForNormalUser",
	query = "from car d where  d.typeOfOrder = :typeOfOrder and normalUserId = :normalUserId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllBytypeOfOrderAndStateForNormalUser",
	query = "from car d where d.typeOfOrder = :typeOfOrder and d.state = :state and normalUserId = :normalUserId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllBytypeOfOrderAndShippingStateForNormalUser",
	query = "from car d where d.typeOfOrder = :typeOfOrder and d.state <8 and normalUserId = :normalUserId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="car.getAllForMainUser",
	query = "from car d where mainId = :userId and d.deleted = false"
			)
	,
	@NamedQuery(name="car.getAllWithAllowSendState",
	query = "from car d where d.allowSendEmail = :state and d.deleted = false"
			)
	
	
})

@Entity
@Table(name = "car")
public class car {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name = "mainId")
	private user mainId;
	
	@ManyToOne
	@JoinColumn(name = "shipperId")
	private shipper shipperId;
	
	@ManyToOne
	@JoinColumn(name = "vendorId")
	private vendor vendorId;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private customer customerId;
	
	@ManyToOne
	@JoinColumn(name = "normalUserId")
	private user normalUserId;
	
	@ManyToOne
	@JoinColumn(name = "mainTwoId")
	private mainTwo mainTwoId;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "make")
	private String make;

	@Column(name = "model")
	private String model;
	

	@Column(name = "assemlyCountry")
	private String assemlyCountry;
	
	@Column(name = "bodyStyle")
	private String bodyStyle;

	@Column(name = "engineType")
	private String engineType;
	

	@Column(name = "engineLiters")
	private String engineLiters;
	

	@Column(name = "color")
	private String color;
	
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "container")
	private String container;

	@Column(name = "containerLink")
	private String containerLink;
	

	@Column(name = "imagesLink")
	private String imagesLink;

	@Column(name = "seal")
	private String seal;
	

	@Column(name = "buyerNumber")
	private String buyerNumber;
	
	@Column(name = "inlandStatus")
	private String inlandStatus;
	
	@Column(name = "cargoRecieved")
	private Calendar cargoRecieved;

	
	@Column(name = "add_datetime")
	private Calendar add_datetime;
	
	@Column(name = "titleRecieved")
	private Integer titleRecieved;
	
	

	@Column(name = "typeOfOrder")
	private Integer typeOfOrder;
	

	@Column(name = "orderNotesFromCustomer")
	private String orderNotesFromCustomer;


	@Column(name = "orderLink")
	private String orderLink;

	@Column(name = "mainImageLink")
	private String mainImageLink;
	
	
	@Column(name = "lotNumber")
	private String lotNumber;
	
	
	

	@Column(name = "orderPrice")
	private float orderPrice;
	
	@Column(name = "dvl")
	private Calendar dvl;
	
	@Column(name = "stRecieved")
	private Calendar stRecieved;
	

	@Column(name = "origin")
	private Integer origin;
	

	@Column(name = "destination")
	private Integer destination;
	

	@Column(name = "etd")
	private Calendar etd;

	@Column(name = "eta")
	private Calendar eta;
	
	@Column(name = "loadrequest")
	private boolean loadrequest;

	@Column(name = "releaseOption")
	private boolean releaseOption;

	@ManyToOne
	@JoinColumn(name = "consigneeId")
	private consignee consigneeId;

	@Column(name = "allowSendEmail")
	private boolean allowSendEmail;
	
	

	@Column(name = "emailToSendComment")
	private String emailToSendComment;
	

	@Column(name = "deleted")
	private boolean deleted;
	
	@Column(name = "commentToSend")
	private String commentToSend;
	
	public static int STATE_WARE_HOUSE_1=0;
	public static int STATE_WARE_HOUSE_2=1;
	public static int STATE_WARE_HOUSE_3=2;
	public static int STATE_WARE_HOUSE_4=3;
	public static int STATE_DRY_CARGO_1=4;
	public static int STATE_DRY_CARGO_2=5;
	public static int STATE_In_TRANSIT_1=6;
	public static int STATE_In_TRANSIT_2=7;
	public static int STATE_Sent_For_Payment=8;
	public static int STATE_AddedByCustomer_REVISE=9;
	public static int STATE_Rejected=10;
	public static int STATE_PayedByCustomer=11;
	public static int STATE_DeliveredByUs=12;
	public static int STATE_DeliveredByCustomer=13;
	
	
	public static int Title_Missing=0;
	public static int Title_Pending=1;
	public static int Title_Requested=2;
	public static int Title_Ok=3;
	public static int Title_InLien=4;
	public static int Title_Paid=4;
	
	
	@Column(name = "state")
	private Integer state;

	@Column(name = "landcost")
	private Integer landcost;

	@Column(name = "seacost")
	private Integer seacost;

	@Column(name = "fees")
	private Integer fees;

	@Column(name = "commision")
	private Integer commision;
	

	@Column(name = "lastUpdate")
	private Calendar lastUpdate;
	
	@Column(name = "storageStartDate")
	private Calendar storageStartDate;
	
	@Column(name = "storageEndDate")
	private Calendar storageEndDate;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "photoExist")
	private boolean photoExist;
	
	@Column(name = "docExist")
	private boolean docExist;
	

	public static int TYPE_Vehicle = 0;
	public static int TYPE_DRY_Cargo = 0;
	
	@Column(name = "type")
	private Integer type;

	

	public static String stateString[]= {"شحن السيارة (Title uploaded, VIR uploaded, Load Request NO)",
										 "شحن السيارة ( VIR and Title uploaded, Load Request YES)",
										 "شحن السيارة (Title uploaded, VIR uploaded, Priority OK TO LOAD"
										 + " / LOAD ASAP, Job Order Form assigned to freight)",
										 "شحن السيارة (No documents uploaded (Vehicle Inspection Report, Title or both))",
										 "Dry Cargo (Load Request NO)",
										 "Dry Cargo (Load Request YES)",
										 "الشحنة فى الطريق ( ETA present)",
										 "الشحنة فى الطريق  (No ETA)",
										 "ارسلت للدفع",
										 "جديد",
										 "رفض",
										 "تم الدفع من قبل العميل",
										 "تم استلام السيارة فى الشحن",
										 "تم تسليم السيارة للعميل"};

	public String getTitleString() {
		String title="";
		
		if(titleRecieved==Title_Missing) {
			title="Missing";
		}else if(titleRecieved==Title_Pending) {
			title="Title Pending";
		}else if(titleRecieved==Title_Requested) {
			title="Requested";
		}else if(titleRecieved==Title_Ok) {
			title="Title ok";
		}else if(titleRecieved==Title_InLien) {
			title="In Lien";
		}else if(titleRecieved==Title_Paid) {
			title="PAID";
		}
		return title;
	}






	public Integer getId() {
		return id;
	}








	public void setId(Integer id) {
		this.id = id;
	}








	public user getMainId() {
		return mainId;
	}








	public void setMainId(user mainId) {
		this.mainId = mainId;
	}








	public shipper getShipperId() {
		return shipperId;
	}








	public void setShipperId(shipper shipperId) {
		this.shipperId = shipperId;
	}








	public vendor getVendorId() {
		return vendorId;
	}








	public void setVendorId(vendor vendorId) {
		this.vendorId = vendorId;
	}








	public customer getCustomerId() {
		return customerId;
	}








	public void setCustomerId(customer customerId) {
		this.customerId = customerId;
	}








	public String getUuid() {
		return uuid;
	}








	public void setUuid(String uuid) {
		this.uuid = uuid;
	}








	public String getDescription() {
		return description;
	}








	public void setDescription(String description) {
		this.description = description;
	}








	public String getYear() {
		return year;
	}








	public void setYear(String year) {
		this.year = year;
	}








	public String getContainer() {
		return container;
	}








	public void setContainer(String container) {
		this.container = container;
	}








	public String getSeal() {
		return seal;
	}








	public void setSeal(String seal) {
		this.seal = seal;
	}








	public String getInlandStatus() {
		return inlandStatus;
	}








	public void setInlandStatus(String inlandStatus) {
		this.inlandStatus = inlandStatus;
	}








	public Calendar getCargoRecieved() {
		return cargoRecieved;
	}








	public void setCargoRecieved(Calendar cargoRecieved) {
		this.cargoRecieved = cargoRecieved;
	}








	public Integer getTitleRecieved() {
		return titleRecieved;
	}








	public void setTitleRecieved(Integer titleRecieved) {
		this.titleRecieved = titleRecieved;
	}








	public Calendar getDvl() {
		return dvl;
	}








	public void setDvl(Calendar dvl) {
		this.dvl = dvl;
	}








	public Calendar getStRecieved() {
		return stRecieved;
	}








	public void setStRecieved(Calendar stRecieved) {
		this.stRecieved = stRecieved;
	}








	public Integer getOrigin() {
		return origin;
	}








	public void setOrigin(Integer origin) {
		this.origin = origin;
	}








	public Integer getDestination() {
		return destination;
	}








	public void setDestination(Integer destination) {
		this.destination = destination;
	}








	public Calendar getEtd() {
		return etd;
	}








	public void setEtd(Calendar etd) {
		this.etd = etd;
	}








	public Calendar getEta() {
		return eta;
	}








	public void setEta(Calendar eta) {
		this.eta = eta;
	}








	public boolean getLoadrequest() {
		return loadrequest;
	}








	public void setLoadrequest(boolean loadrequest) {
		this.loadrequest = loadrequest;
	}

















	public boolean isReleaseOption() {
		return releaseOption;
	}








	public void setReleaseOption(boolean releaseOption) {
		this.releaseOption = releaseOption;
	}








	public consignee getConsigneeId() {
		return consigneeId;
	}








	public void setConsigneeId(consignee consigneeId) {
		this.consigneeId = consigneeId;
	}








	public Integer getState() {
		return state;
	}








	public void setState(Integer state) {
		this.state = state;
	}








	public Integer getLandcost() {
		return landcost;
	}








	public void setLandcost(Integer landcost) {
		this.landcost = landcost;
	}








	public Integer getSeacost() {
		return seacost;
	}








	public void setSeacost(Integer seacost) {
		this.seacost = seacost;
	}








	public Integer getFees() {
		return fees;
	}








	public void setFees(Integer fees) {
		this.fees = fees;
	}








	public Integer getCommision() {
		return commision;
	}








	public void setCommision(Integer commision) {
		this.commision = commision;
	}








	public Calendar getLastUpdate() {
		return lastUpdate;
	}








	public void setLastUpdate(Calendar lastUpdate) {
		this.lastUpdate = lastUpdate;
	}











	public static String[] getStateString() {
		return stateString;
	}








	public static void setStateString(String[] stateString) {
		car.stateString = stateString;
	}








	public Calendar getStorageStartDate() {
		return storageStartDate;
	}








	public void setStorageStartDate(Calendar storageStartDate) {
		this.storageStartDate = storageStartDate;
	}








	public Calendar getStorageEndDate() {
		return storageEndDate;
	}








	public void setStorageEndDate(Calendar storageEndDate) {
		this.storageEndDate = storageEndDate;
	}








	public String getNote() {
		return note;
	}








	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	public int getNumberOfDays() {
		if(state!=STATE_WARE_HOUSE_1||state!=STATE_WARE_HOUSE_2||state!=STATE_WARE_HOUSE_3||
				state!=STATE_WARE_HOUSE_4) {

			return getDaysDifference(Calendar.getInstance(),storageStartDate);
		}
		return getDaysDifference(storageEndDate,storageStartDate);
	}








	private int getDaysDifference(Calendar end, Calendar start) {
		// TODO Auto-generated method stub

		Date endDate=end.getTime();
		Date startDate=start.getTime();
		
		return (int)(endDate.getTime()-startDate.getTime());
	}








	public String getMake() {
		return make;
	}








	public void setMake(String make) {
		this.make = make;
	}








	public String getModel() {
		return model;
	}








	public void setModel(String model) {
		this.model = model;
	}








	public String getBodyStyle() {
		return bodyStyle;
	}








	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}








	public String getEngineType() {
		return engineType;
	}








	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}








	public String getEngineLiters() {
		return engineLiters;
	}








	public void setEngineLiters(String engineLiters) {
		this.engineLiters = engineLiters;
	}








	public String getColor() {
		return color;
	}








	public void setColor(String color) {
		this.color = color;
	}








	public String getAssemlyCountry() {
		return assemlyCountry;
	}








	public void setAssemlyCountry(String assemlyCountry) {
		this.assemlyCountry = assemlyCountry;
	}








	public boolean isPhotoExist() {
		return photoExist;
	}








	public void setPhotoExist(boolean photoExist) {
		this.photoExist = photoExist;
	}








	public boolean isDocExist() {
		return docExist;
	}








	public void setDocExist(boolean docExist) {
		this.docExist = docExist;
	}








	public Integer getType() {
		return type;
	}








	public void setType(Integer type) {
		this.type = type;
	}






	public String getContainerLink() {
		return containerLink;
	}






	public void setContainerLink(String containerLink) {
		this.containerLink = containerLink;
	}






	public boolean isAllowSendEmail() {
		return allowSendEmail;
	}






	public void setAllowSendEmail(boolean allowSendEmail) {
		this.allowSendEmail = allowSendEmail;
	}






	public String getEmailToSendComment() {
		return emailToSendComment;
	}






	public void setEmailToSendComment(String emailToSendComment) {
		this.emailToSendComment = emailToSendComment;
	}






	public String getCommentToSend() {
		return commentToSend;
	}






	public void setCommentToSend(String commentToSend) {
		this.commentToSend = commentToSend;
	}






	public mainTwo getMainTwoId() {
		return mainTwoId;
	}






	public void setMainTwoId(mainTwo mainTwoId) {
		this.mainTwoId = mainTwoId;
	}






	public boolean isDeleted() {
		return deleted;
	}






	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}






	public String getImagesLink() {
		return imagesLink;
	}






	public void setImagesLink(String imagesLink) {
		this.imagesLink = imagesLink;
	}






	public user getNormalUserId() {
		return normalUserId;
	}






	public void setNormalUserId(user normalUserId) {
		this.normalUserId = normalUserId;
	}






	public Integer getTypeOfOrder() {
		return typeOfOrder;
	}






	public void setTypeOfOrder(Integer typeOfOrder) {
		this.typeOfOrder = typeOfOrder;
	}



	public static int TYPE_SHIPPING = 0;
	public static int TYPE_BUY_SHIPPING = 1;


	public String getOrderNotesFromCustomer() {
		return orderNotesFromCustomer;
	}






	public void setOrderNotesFromCustomer(String orderNotesFromCustomer) {
		this.orderNotesFromCustomer = orderNotesFromCustomer;
	}






	public String getOrderLink() {
		return orderLink;
	}






	public void setOrderLink(String orderLink) {
		this.orderLink = orderLink;
	}






	public float getOrderPrice() {
		return orderPrice;
	}






	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}






	public String getMainImageLink() {
		return mainImageLink;
	}






	public void setMainImageLink(String mainImageLink) {
		this.mainImageLink = mainImageLink;
	}






	public Calendar getAdd_datetime() {
		return add_datetime;
	}






	public void setAdd_datetime(Calendar add_datetime) {
		this.add_datetime = add_datetime;
	}






	public String getLotNumber() {
		return lotNumber;
	}






	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}






	public String getBuyerNumber() {
		return buyerNumber;
	}






	public void setBuyerNumber(String buyerNumber) {
		this.buyerNumber = buyerNumber;
	}
	

	
	
	
	
}
