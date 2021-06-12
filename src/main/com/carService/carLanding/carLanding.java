package main.com.carService.carLanding;


import java.text.SimpleDateFormat;
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

import com.google.gson.JsonObject;

import main.com.carService.loginNeeds.user;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="carLanding.getAll",
		     query="SELECT c FROM carLanding c where c.deleted = false"
		     )
	,
	@NamedQuery(name="carLanding.getById",
	query = "from carLanding d where d.id = :id and d.deleted = false"
			)
	,
	@NamedQuery(name="carLanding.getByVin",
	query = "from carLanding d where d.uuid = :uuid and d.deleted = false"
			)
	
	,
	@NamedQuery(name="carLanding.getAllForLanding",
	query = "from carLanding d where d.isShowenInLanding = true and d.deleted = false and d.active = 1"
			)
	
	,
	@NamedQuery(name="carLanding.getAllForUserBiding",
	query = "from carLanding d where d.userMaxBidId.id = :id and d.deleted = false"
			)
	
	,
	@NamedQuery(name="carLanding.getAllForCategories",
	query = "from carLanding d where d.category = :category and d.deleted = false and d.active = 1"
			)
	,
	@NamedQuery(name="carLanding.getAllGroupsOfMake",
	query = "from carLanding d where d.deleted = false and d.active = 1 group by d.make "
			)
	
	,
	@NamedQuery(name="carLanding.getAllGroupsOfCategory",
	query = "from carLanding d where d.deleted = false and d.active = 1 group by d.category"
			)
	
	,
	@NamedQuery(name="carLanding.getAllGroupsOfModelWithMake",
	query = "from carLanding d where d.deleted = false and d.make=:make and d.active = 1 group by d.model "
			)
	
	,
	@NamedQuery(name="carLanding.getAllForSearch",
	query = "from carLanding d where d.year <= :yearEnd and d.year >= :yearStart and d.make like :make and d.model like :model and d.category like :category and d.deleted = false and d.active = 1"
			)
	,
	@NamedQuery(name="carLanding.getAllBetweenDates",
	query = "from carLanding d where d.bidingDate > :date1 and d.endDate < :date2 and d.deleted = false"
			)
	
	,
	@NamedQuery(name="carLanding.getAllBidBetweenDates",
	query = "from carLanding d where d.userMaxBidId.id > 0 and  d.bidingDate > :date1 and d.endDate < :date2 and d.deleted = false"
			)
	
	
})

@Entity
@Table(name = "carLanding")
public class carLanding {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name = "mainId")
	private user mainId;
	
	
	@Column(name = "uuid")
	private String uuid;
	

	@Column(name = "lot")
	private String lot;
	

	@Column(name = "model")
	private String model;
	
	@Column(name = "make")
	private String make;

	@Column(name = "year")
	private String year;
	

	@Column(name = "docType")
	private String docType;
	

	@Column(name = "docState")
	private String docState;
	

	@Column(name = "buyItNowPrice")
	private String buyItNowPrice;

	@Column(name = "bodyStyle")
	private String bodyStyle;
	

	@Column(name = "secondaryDamage")
	private String secondaryDamage;

	@Column(name = "odoMeter")
	private String odoMeter;
	

	@Column(name = "transmission")
	private String transmission;
	

	@Column(name = "damageDescription")
	private String damageDescription;
	

	@Column(name = "itemNumber")
	private String itemNumber;
	
	@Column(name = "color")
	private String color;
	

	@Column(name = "cylinder")
	private String cylinder;
	

	public static String AUTCION_COPART = "Copart";
	public static String AUTCION_IAAI = "IAAI";
	public static String AUTCION_LIBYA = "Libya";
	public static String AUTCION_KOREA = "Korean";
	

	public static boolean SELECT_UPLOADED = true;
	public static boolean SELECT_FROM_AUCTION = false;
	
	@Column(name = "auctionType")
	private String auctionType;
	
	

	@Column(name = "runsDrives")
	private String runsDrives;
	
	
	@Column(name = "fuel")
	private String fuel;
	
	
	@Column(name = "currentBid")
	private String currentBid;

	@Column(name = "saleName")
	private String saleName;

	@Column(name = "auctionLocation")
	private String auctionLocation;

	@Column(name = "dateAdd")
	private Calendar dateAdd;

	@Column(name = "bidingDate")
	private Date bidingDate;
	

	@Column(name = "startDate")
	private Date startDate;
	
	
	@Column(name = "estRetailValue")
	private String estRetailValue;

	@Column(name = "mainImage")
	private String mainImage;
	
	

	@Column(name = "mainImageUpload")
	private String mainImageUpload;


	@Column(name = "selectUploadedOneOrAuction")
	private boolean selectUploadedOneOrAuction;

	@Column(name = "category")
	private String category;
	
	@Column(name = "isShowenInLanding")
	private boolean isShowenInLanding;

	

	@Column(name = "engineType")
	private String engineType;
	
	

	@Column(name = "gridRow")
	private String gridRow;
	

	@Column(name = "odoDescription")
	private String odoDescription;
	

	@Column(name = "repairEstimate")
	private String repairEstimate;
	

	@Column(name = "allImagesLink")
	private String allImagesLink;
	

	@ManyToOne
	@JoinColumn(name = "userMaxBidId")
	private user userMaxBidId;
	


	@Column(name = "active")
	private boolean active;
	
	

	@Column(name = "activebuyItNow")
	private boolean activebuyItNow;
	

	@Column(name = "paymentDone")
	private boolean paymentDone;
	


	@Column(name = "endDate")
	private Date endDate;

	@Column(name = "state")
	private Integer state;

	

	@Column(name = "copartFees")
	private String copartFees;
	

	@Column(name = "ourFees")
	private String ourFees;
	
	
	@Column(name = "deleted")
	private boolean deleted;
	
	
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



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public String getLot() {
		return lot;
	}



	public void setLot(String lot) {
		this.lot = lot;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public Date getBidingDate() {
		return bidingDate;
	}



	public void setBidingDate(Date bidingDate) {
		this.bidingDate = bidingDate;
	}



	public String getMake() {
		return make;
	}



	public void setMake(String make) {
		this.make = make;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getBodyStyle() {
		return bodyStyle;
	}



	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}



	public String getSecondaryDamage() {
		return secondaryDamage;
	}



	public void setSecondaryDamage(String secondaryDamage) {
		this.secondaryDamage = secondaryDamage;
	}



	public String getOdoMeter() {
		return odoMeter;
	}



	public void setOdoMeter(String odoMeter) {
		this.odoMeter = odoMeter;
	}



	public String getTransmission() {
		return transmission;
	}



	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}



	public String getDamageDescription() {
		return damageDescription;
	}



	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}



	public String getItemNumber() {
		return itemNumber;
	}



	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public String getCylinder() {
		return cylinder;
	}



	public void setCylinder(String cylinder) {
		this.cylinder = cylinder;
	}


	

	public String getRunsDrives() {
		return runsDrives;
	}



	public void setRunsDrives(String runsDrives) {
		this.runsDrives = runsDrives;
	}



	public String getFuel() {
		return fuel;
	}



	public void setFuel(String fuel) {
		this.fuel = fuel;
	}



	public String getCurrentBid() {
		return currentBid;
	}



	public void setCurrentBid(String currentBid) {
		this.currentBid = currentBid;
	}



	public String getSaleName() {
		return saleName;
	}



	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}



	public String getAuctionLocation() {
		return auctionLocation;
	}



	public void setAuctionLocation(String auctionLocation) {
		this.auctionLocation = auctionLocation;
	}



	public Calendar getDateAdd() {
		return dateAdd;
	}



	public void setDateAdd(Calendar dateAdd) {
		this.dateAdd = dateAdd;
	}




	





	public String getEstRetailValue() {
		return estRetailValue;
	}



	public void setEstRetailValue(String estRetailValue) {
		this.estRetailValue = estRetailValue;
	}



	public String getMainImage() {
		return mainImage;
	}



	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public boolean isShowenInLanding() {
		return isShowenInLanding;
	}



	public void setShowenInLanding(boolean isShowenInLanding) {
		this.isShowenInLanding = isShowenInLanding;
	}



	


	public String getEngineType() {
		return engineType;
	}



	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}



	public String getGridRow() {
		return gridRow;
	}



	public void setGridRow(String gridRow) {
		this.gridRow = gridRow;
	}



	public String getOdoDescription() {
		return odoDescription;
	}



	public void setOdoDescription(String odoDescription) {
		this.odoDescription = odoDescription;
	}



	public String getRepairEstimate() {
		return repairEstimate;
	}



	public void setRepairEstimate(String repairEstimate) {
		this.repairEstimate = repairEstimate;
	}



	public String getAllImagesLink() {
		return allImagesLink;
	}



	public void setAllImagesLink(String allImagesLink) {
		this.allImagesLink = allImagesLink;
	}



	public user getUserMaxBidId() {
		return userMaxBidId;
	}



	public void setUserMaxBidId(user userMaxBidId) {
		this.userMaxBidId = userMaxBidId;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	public Integer getState() {
		return state;
	}



	public void setState(Integer state) {
		this.state = state;
	}

	
	


	public String getCopartFees() {
		return copartFees;
	}



	public void setCopartFees(String copartFees) {
		this.copartFees = copartFees;
	}



	public String getOurFees() {
		return ourFees;
	}



	public void setOurFees(String ourFees) {
		this.ourFees = ourFees;
	}





	public boolean isPaymentDone() {
		return paymentDone;
	}



	public void setPaymentDone(boolean paymentDone) {
		this.paymentDone = paymentDone;
	}





	public String getBuyItNowPrice() {
		return buyItNowPrice;
	}



	public void setBuyItNowPrice(String buyItNowPrice) {
		this.buyItNowPrice = buyItNowPrice;
	}



	public boolean isActivebuyItNow() {
		return activebuyItNow;
	}



	public void setActivebuyItNow(boolean activebuyItNow) {
		this.activebuyItNow = activebuyItNow;
	}



	public boolean isDeleted() {
		return deleted;
	}



	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}





	public String getDocType() {
		return docType;
	}



	public void setDocType(String docType) {
		this.docType = docType;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}





	public String getDocState() {
		return docState;
	}



	public void setDocState(String docState) {
		this.docState = docState;
	}


    public JsonObject toJson() {
    	JsonObject obj=new JsonObject();
    	  obj.addProperty("id", String.valueOf(this.id));
	      obj.addProperty("color", String.valueOf(this.color));
	      obj.addProperty("mainId", String.valueOf(this.mainId.getId()));
	      obj.addProperty("uuid", String.valueOf(this.uuid));
	      obj.addProperty("make", String.valueOf(this.make));
	      obj.addProperty("model", String.valueOf(this.model));
	      obj.addProperty("year", String.valueOf(this.year));
	      obj.addProperty("transmission", String.valueOf(this.transmission));
	      obj.addProperty("mainImage", String.valueOf(this.mainImage));
	      obj.addProperty("category", String.valueOf(this.category));
	      obj.addProperty("endDate", String.valueOf(this.endDate));
	      
	      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      String dateString = format.format( new Date()   );
	      obj.addProperty("DateTimeNowRealTime", String.valueOf(dateString));
	      obj.addProperty("docType", String.valueOf(this.docType));
	      obj.addProperty("odoMeter", String.valueOf(this.odoMeter));
	      obj.addProperty("startDate", String.valueOf(this.startDate));
	      obj.addProperty("bidingDate", String.valueOf(this.bidingDate));
	      obj.addProperty("buyItNowPrice", String.valueOf(this.buyItNowPrice));
	      obj.addProperty("runsDrives", String.valueOf(this.runsDrives));
	      obj.addProperty("auctionType", String.valueOf(this.auctionType));
	      
	      if(this.userMaxBidId!=null) {
	      obj.addProperty("userMaxBidId", String.valueOf(this.userMaxBidId.getId()));
	      obj.addProperty("userMaxBidFirstName", String.valueOf(this.userMaxBidId.getFirstName()));
	      obj.addProperty("userMaxBidLasttName", String.valueOf(this.userMaxBidId.getLastName()));
	      }else {
	    	  obj.addProperty("userMaxBidId", String.valueOf(""));
		      obj.addProperty("userMaxBidFirstName", String.valueOf(""));
		      obj.addProperty("userMaxBidLasttName", String.valueOf(""));
		        
	      }
	      obj.addProperty("selectUploadedOneOrAuction", String.valueOf(this.selectUploadedOneOrAuction));
	      obj.addProperty("mainImage", String.valueOf(this.mainImage));
	      obj.addProperty("mainImageUpload", String.valueOf(this.mainImageUpload));
	      obj.addProperty("odoDescription", String.valueOf(this.odoDescription));
	      obj.addProperty("currentBid", String.valueOf(this.currentBid));
	      obj.addProperty("active", String.valueOf(this.active));
	      obj.addProperty("paymentDone", String.valueOf(this.paymentDone));
	      obj.addProperty("activebuyItNow", String.valueOf(this.activebuyItNow));
	      obj.addProperty("auctionLocation", String.valueOf(this.auctionLocation));
	      obj.addProperty("lot", String.valueOf(this.lot));
	      obj.addProperty("state", String.valueOf(this.state));
	      obj.addProperty("damageDescription", String.valueOf(this.damageDescription));
	      obj.addProperty("secondaryDamage", String.valueOf(this.secondaryDamage));
	      return obj;
    	
    }


	public enum stateOfCar {

		BidingState(0,"Still in Biding"),ProcessState(1,"In Process..."),
		PapersStates(2,"In Main Auction"),Delivered(3,"Delivered");
		
		stateOfCar(int type,String name){
			this.type=type;
			this.name=name;
		}
		
		int type=0;
		
		String name="";

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}


	public String getAuctionType() {
		return auctionType;
	}



	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}



	public String getMainImageUpload() {
		return mainImageUpload;
	}



	public void setMainImageUpload(String mainImageUpload) {
		this.mainImageUpload = mainImageUpload;
	}



	public boolean isSelectUploadedOneOrAuction() {
		return selectUploadedOneOrAuction;
	}



	public void setSelectUploadedOneOrAuction(boolean selectUploadedOneOrAuction) {
		this.selectUploadedOneOrAuction = selectUploadedOneOrAuction;
	}


	
	
}
