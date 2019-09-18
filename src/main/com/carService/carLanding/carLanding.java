package main.com.carService.carLanding;


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

import main.com.carService.loginNeeds.user;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="carLanding.getAll",
		     query="SELECT c FROM carLanding c"
		     )
	,
	@NamedQuery(name="carLanding.getById",
	query = "from carLanding d where d.id = :id"
			)
	,
	@NamedQuery(name="carLanding.getByVin",
	query = "from carLanding d where d.uuid = :uuid"
			)
	
	,
	@NamedQuery(name="carLanding.getAllForLanding",
	query = "from carLanding d where d.isShowenInLanding = true"
			)
	
	,
	@NamedQuery(name="carLanding.getAllForCategories",
	query = "from carLanding d where d.category = :category"
			)
	,
	@NamedQuery(name="carLanding.getAllGroupsOfMake",
	query = "from carLanding d group by d.make"
			)
	,
	@NamedQuery(name="carLanding.getAllForSearch",
	query = "from carLanding d where d.year <= :yearEnd and d.year >= :yearStart and d.make = :make and d.category = :category"
			)
	,
	@NamedQuery(name="carLanding.getAllBetweenDates",
	query = "from carLanding d where d.bidingDate > :date1 and d.endDate < :date2"
			)
	
	,
	@NamedQuery(name="carLanding.getAllBidBetweenDates",
	query = "from carLanding d where d.userMaxBidId.id > 0 and  d.bidingDate > :date1 and d.endDate < :date2"
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
	
	
	@Column(name = "estRetailValue")
	private String estRetailValue;
	
	@Column(name = "mainImage")
	private String mainImage;

	

	@Column(name = "category")
	private Integer category;
	
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
	


	@Column(name = "endDate")
	private Date endDate;



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



	public Integer getCategory() {
		return category;
	}



	public void setCategory(Integer category) {
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
	
	
	

	
	
}
