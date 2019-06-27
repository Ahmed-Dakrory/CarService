package main.com.carService.invoice;


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
	
	
	@NamedQuery(name="invoice.getAll",
		     query="SELECT c FROM invoice c"
		     )
	,
	@NamedQuery(name="invoice.getById",
	query = "from invoice d where d.id = :id"
			)
	,
	@NamedQuery(name="invoice.getByUserIdCustomer",
	query = "from invoice d where d.userIdCustomer.id = :id"
			)

})

@Entity
@Table(name = "invoice")
public class invoice {
	
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
	
	
	
	
	
}
