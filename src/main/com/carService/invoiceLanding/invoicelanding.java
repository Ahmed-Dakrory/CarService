package main.com.carService.invoiceLanding;


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

import main.com.carService.carLanding.carLanding;
import main.com.carService.loginNeeds.user;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="invoicelanding.getAll",
		     query="SELECT c FROM invoicelanding c"
		     )
	,
	@NamedQuery(name="invoicelanding.getById",
	query = "from invoicelanding d where d.id = :id"
			)
	,
	@NamedQuery(name="invoicelanding.getAllByUserIdCustomer",
	query = "from invoicelanding d where d.userIdCustomer.id = :id"
			)
	
	,
	@NamedQuery(name="invoicelanding.getAllByUserId",
	query = "from invoicelanding d where d.userIdIssuer.id = :id"
			)
	
	,
	@NamedQuery(name="invoicelanding.getAllByUserIdBetweenDates",
	query = "from invoicelanding d where d.userIdIssuer.id = :id and d.date > :dateLower and d.date < :dateHigher"
			)

})

@Entity
@Table(name = "invoicelanding")
public class invoicelanding {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "date")
	private Calendar date;


	@ManyToOne
	@JoinColumn(name = "userIdIssuer")
	private user userIdIssuer;
	

	@ManyToOne
	@JoinColumn(name = "userIdCustomer")
	private user userIdCustomer;
	

	@Column(name = "bankName")
	private String bankName;
	
	@Column(name = "bankTelephone")
	private String bankTelephone;
	
	
	@Column(name = "bankAddress")
	private String bankAddress;
	
	
	@Column(name = "bankAccountNumber")
	private String bankAccountNumber;
	

	@ManyToOne
	@JoinColumn(name = "carLandingId")
	private carLanding carLandingId;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Calendar getDate() {
		return date;
	}


	public void setDate(Calendar date) {
		this.date = date;
	}


	public user getUserIdIssuer() {
		return userIdIssuer;
	}


	public void setUserIdIssuer(user userIdIssuer) {
		this.userIdIssuer = userIdIssuer;
	}


	public user getUserIdCustomer() {
		return userIdCustomer;
	}


	public void setUserIdCustomer(user userIdCustomer) {
		this.userIdCustomer = userIdCustomer;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankTelephone() {
		return bankTelephone;
	}


	public void setBankTelephone(String bankTelephone) {
		this.bankTelephone = bankTelephone;
	}


	public String getBankAddress() {
		return bankAddress;
	}


	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}


	public String getBankAccountNumber() {
		return bankAccountNumber;
	}


	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}



	public carLanding getCarLandingId() {
		return carLandingId;
	}


	public void setCarLandingId(carLanding carLandingId) {
		this.carLandingId = carLandingId;
	}
	
	
	
	
	
}
