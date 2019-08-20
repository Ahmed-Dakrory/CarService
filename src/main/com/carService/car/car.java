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
		     query="SELECT c FROM car c"
		     )
	,
	@NamedQuery(name="car.getById",
	query = "from car d where d.id = :id"
			)
	,
	@NamedQuery(name="car.getByVin",
	query = "from car d where d.uuid = :uuid"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForMainUser",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and mainId = :userId"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForMainUserTwo",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and mainTwoId = :mainTwoId"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForVendor",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and vendorId = :vendorId"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForShipper",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and shipperId = :shipperId"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForCustomer",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and customerId = :customerId"
			)
	,
	@NamedQuery(name="car.getAllWareHouseForConsignee",
	query = "from car d where (d.state = 0 or d.state = 1 or d.state = 2 or d.state = 3 ) and consigneeId = :consigneeId"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForMainUser",
	query = "from car d where (d.state = 4 or d.state = 5) and mainId = :userId"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForMainUserTwo",
	query = "from car d where (d.state = 4 or d.state = 5) and mainTwoId = :mainTwoId"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForVendor",
	query = "from car d where (d.state = 4 or d.state = 5) and vendorId = :vendorId"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForShipper",
	query = "from car d where (d.state = 4 or d.state = 5) and shipperId = :shipperId"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForCustomer",
	query = "from car d where (d.state = 4 or d.state = 5) and customerId = :customerId"
			)
	,
	@NamedQuery(name="car.getAllDryCargoForConsignee",
	query = "from car d where (d.state = 4 or d.state = 5) and consigneeId = :consigneeId"
			)
	
	,
	@NamedQuery(name="car.getAllFrightInTransitForMainUser",
	query = "from car d where (d.state = 6 or d.state = 7) and mainId = :userId"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForMainUserTwo",
	query = "from car d where (d.state = 6 or d.state = 7) and mainTwoId = :mainTwoId"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForVendor",
	query = "from car d where (d.state = 6 or d.state = 7) and vendorId = :vendorId"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForShipper",
	query = "from car d where (d.state = 6 or d.state = 7) and shipperId = :shipperId"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForCustomer",
	query = "from car d where (d.state = 6 or d.state = 7) and customerId = :customerId"
			)
	,
	@NamedQuery(name="car.getAllFrightInTransitForConsignee",
	query = "from car d where (d.state = 6 or d.state = 7) and consigneeId = :consigneeId"
			)
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForMainUser",
	query = "from car d where (d.state = 8) and mainId = :userId"
			)
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForMainUserTwo",
	query = "from car d where (d.state = 8) and mainTwoId = :mainTwoId"
			)
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForVendor",
	query = "from car d where (d.state = 8) and vendorId = :vendorId"
			)
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForShipper",
	query = "from car d where (d.state = 8) and shipperId = :shipperId"
			)
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForCustomer",
	query = "from car d where (d.state = 8) and customerId = :customerId"
			)
	,
	@NamedQuery(name="car.getAllFrightSentForPaymentForConsignee",
	query = "from car d where (d.state = 8) and consigneeId = :consigneeId"
			)
	,
	@NamedQuery(name="car.getAllWithAllowSendState",
	query = "from car d where d.allowSendEmail = :state"
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
	
	@Column(name = "seal")
	private String seal;
	
	@Column(name = "inlandStatus")
	private String inlandStatus;
	
	@Column(name = "cargoRecieved")
	private Calendar cargoRecieved;
	
	@Column(name = "titleRecieved")
	private Integer titleRecieved;
	
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

	

	public static String stateString[]= {"Warehouse (Title uploaded, VIR uploaded, Load Request NO)",
										 "Warehouse ( VIR and Title uploaded, Load Request YES)",
										 "Warehouse (Title uploaded, VIR uploaded, Priority OK TO LOAD"
										 + " / LOAD ASAP, Job Order Form assigned to freight)",
										 "Warehouse (No documents uploaded (Vehicle Inspection Report, Title or both))",
										 "Dry Cargo (Load Request NO)",
										 "Dry Cargo (Load Request YES)",
										 "Fright In Transit ( ETA present)",
										 "Fright In Transit  (No ETA)",
										 "Sent For Payment"};

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
	

	
	
	
	
}
