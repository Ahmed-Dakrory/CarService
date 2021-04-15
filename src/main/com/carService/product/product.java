package main.com.carService.product;


import java.util.Calendar;

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
	
	
	@NamedQuery(name="product.getAll",
		     query="SELECT c FROM product c where c.deleted = false"
		     )
	,
	@NamedQuery(name="product.getById",
	query = "from product d where d.id = :id and d.deleted = false"
			)
	,
	@NamedQuery(name="product.getAllByState",
	query = "from product d where (d.state = :state) and d.deleted = false"
			)

	,
	@NamedQuery(name="product.getAllForMainUser",
	query = "from product d where mainId = :userId and d.deleted = false"
			)
	,
	@NamedQuery(name="product.getAllByStateForMainUser",
	query = "from product d where (d.state = :state) and mainId = :userId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="product.getAllForNormalUser",
	query = "from product d where  normalUserId = :normalUserId and d.deleted = false"
			)
	,
	@NamedQuery(name="product.getAllByStateForNormalUser",
	query = "from product d where (d.state = :state) and normalUserId = :normalUserId and d.deleted = false"
			)

	,
	@NamedQuery(name="product.getAllBytypeOfOrderForNormalUser",
	query = "from product d where  d.typeOfOrder = :typeOfOrder and normalUserId = :normalUserId and d.deleted = false"
			)
	
	,
	@NamedQuery(name="product.getAllBytypeOfOrderAndStateForNormalUser",
	query = "from product d where d.typeOfOrder = :typeOfOrder and d.state = :state and normalUserId = :normalUserId and d.deleted = false"
			)
	
	
	
	
	
})

@Entity
@Table(name = "product")
public class product {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	

	@Column(name = "trackingNumber")
	private String trackingNumber;
	
	
	@ManyToOne
	@JoinColumn(name = "mainId")
	private user mainId;
	
	
	@ManyToOne
	@JoinColumn(name = "normalUserId")
	private user normalUserId;
	
	@Column(name = "heightOfProduct")
	private String heightOfProduct;
	
	@Column(name = "widthOfProduct")
	private String widthOfProduct;

	@Column(name = "lengthOfProduct")
	private String lengthOfProduct;
	

	@Column(name = "volumeOfProduct")
	private String volumeOfProduct;
	

	@Column(name = "weightOfProduct")
	private String weightOfProduct;
	
	

	
	@Column(name = "delieverIn")
	private String delieverIn;

	@Column(name = "address")
	private String address;
	

	@Column(name = "shipmentSymbol")
	private String shipmentSymbol;
	

	
	@Column(name = "description")
	private String description;
	
	
	@Column(name = "container")
	private String container;

	@Column(name = "containerLink")
	private String containerLink;

	@Column(name = "typeOfOrder")
	private Integer typeOfOrder;

	public static int TYPE_SHIPPING = 0;
	public static int TYPE_BUY_SHIPPING = 1;
	
	@Column(name = "orderNotesFromCustomer")
	private String orderNotesFromCustomer;


	@Column(name = "orderLink")
	private String orderLink;

	@Column(name = "mainImageLink")
	private String mainImageLink;
	
	

	@Column(name = "orderPrice")
	private float orderPrice;
	

	

	@Column(name = "commision")
	private float commision;
	

	@Column(name = "shipmentFees")
	private float shipmentFees;
	

	@Column(name = "fees")
	private float fees;
	

	@Column(name = "deleted")
	private boolean deleted;
	
	@Column(name = "commentToSend")
	private String commentToSend;
	
	@Column(name = "lastUpdate")
	private Calendar lastUpdate;
	
	@Column(name = "estimatedDateOfDelievery")
	private Calendar estimatedDateOfDelievery;
	
	@Column(name = "deliveryDate")
	private Calendar deliveryDate;
	
	
	@Column(name = "add_datetime")
	private Calendar add_datetime;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "photoExist")
	private boolean photoExist;
	
	@Column(name = "docExist")
	private boolean docExist;
	

	
	@Column(name = "state")
	private Integer state;

	
	public static int STATE_SHIPMENT=0;
	public static int STATE_Sent_For_Payment=1;
	public static int STATE_AddedByCustomer_REVISE=2;
	public static int STATE_Rejected=3;
	public static int STATE_PayedByCustomer=4;
	public static int STATE_DeliveredByUs=5;
	public static int STATE_DeliveredByCustomer=6;
	
	

	public static String stateString[]= {"شحن السلعة",
										 "ارسلت للدفع",
										 "جديد",
										 "رفض",
										 "تم الدفع من قبل العميل",
										 "تم استلام السلعة فى الشحن",
										 "تم تسليم السلعة للعميل"};



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getTrackingNumber() {
		return trackingNumber;
	}



	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}



	public user getMainId() {
		return mainId;
	}



	public void setMainId(user mainId) {
		this.mainId = mainId;
	}



	public user getNormalUserId() {
		return normalUserId;
	}



	public void setNormalUserId(user normalUserId) {
		this.normalUserId = normalUserId;
	}



	public String getHeightOfProduct() {
		return heightOfProduct;
	}



	public void setHeightOfProduct(String heightOfProduct) {
		this.heightOfProduct = heightOfProduct;
	}



	public String getWidthOfProduct() {
		return widthOfProduct;
	}



	public void setWidthOfProduct(String widthOfProduct) {
		this.widthOfProduct = widthOfProduct;
	}



	public String getLengthOfProduct() {
		return lengthOfProduct;
	}



	public void setLengthOfProduct(String lengthOfProduct) {
		this.lengthOfProduct = lengthOfProduct;
	}



	public String getVolumeOfProduct() {
		return volumeOfProduct;
	}



	public void setVolumeOfProduct(String volumeOfProduct) {
		this.volumeOfProduct = volumeOfProduct;
	}



	public String getWeightOfProduct() {
		return weightOfProduct;
	}



	public void setWeightOfProduct(String weightOfProduct) {
		this.weightOfProduct = weightOfProduct;
	}



	public String getDelieverIn() {
		return delieverIn;
	}



	public void setDelieverIn(String delieverIn) {
		this.delieverIn = delieverIn;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getShipmentSymbol() {
		return shipmentSymbol;
	}



	public void setShipmentSymbol(String shipmentSymbol) {
		this.shipmentSymbol = shipmentSymbol;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getContainer() {
		return container;
	}



	public void setContainer(String container) {
		this.container = container;
	}



	public String getContainerLink() {
		return containerLink;
	}



	public void setContainerLink(String containerLink) {
		this.containerLink = containerLink;
	}



	public Integer getTypeOfOrder() {
		return typeOfOrder;
	}



	public void setTypeOfOrder(Integer typeOfOrder) {
		this.typeOfOrder = typeOfOrder;
	}



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



	public String getMainImageLink() {
		return mainImageLink;
	}



	public void setMainImageLink(String mainImageLink) {
		this.mainImageLink = mainImageLink;
	}



	public float getOrderPrice() {
		return orderPrice;
	}



	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}



	public float getCommision() {
		return commision;
	}



	public void setCommision(float commision) {
		this.commision = commision;
	}



	public float getShipmentFees() {
		return shipmentFees;
	}



	public void setShipmentFees(float shipmentFees) {
		this.shipmentFees = shipmentFees;
	}



	public float getFees() {
		return fees;
	}



	public void setFees(float fees) {
		this.fees = fees;
	}



	public boolean isDeleted() {
		return deleted;
	}



	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}



	public String getCommentToSend() {
		return commentToSend;
	}



	public void setCommentToSend(String commentToSend) {
		this.commentToSend = commentToSend;
	}



	public Calendar getLastUpdate() {
		return lastUpdate;
	}



	public void setLastUpdate(Calendar lastUpdate) {
		this.lastUpdate = lastUpdate;
	}



	public Calendar getEstimatedDateOfDelievery() {
		return estimatedDateOfDelievery;
	}



	public void setEstimatedDateOfDelievery(Calendar estimatedDateOfDelievery) {
		this.estimatedDateOfDelievery = estimatedDateOfDelievery;
	}



	public Calendar getDeliveryDate() {
		return deliveryDate;
	}



	public void setDeliveryDate(Calendar deliveryDate) {
		this.deliveryDate = deliveryDate;
	}



	public Calendar getAdd_datetime() {
		return add_datetime;
	}



	public void setAdd_datetime(Calendar add_datetime) {
		this.add_datetime = add_datetime;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
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



	public Integer getState() {
		return state;
	}



	public void setState(Integer state) {
		this.state = state;
	}

	




//	private int getDaysDifference(Calendar end, Calendar start) {
//		// TODO Auto-generated method stub
//
//		Date endDate=end.getTime();
//		Date startDate=start.getTime();
//		
//		return (int)(endDate.getTime()-startDate.getTime());
//	}






	
}
