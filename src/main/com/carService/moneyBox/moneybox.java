package main.com.carService.moneyBox;


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
	
	
	@NamedQuery(name="moneybox.getAll",
		     query="SELECT c FROM moneybox c"
		     )
	,
	@NamedQuery(name="moneybox.getById",
	query = "from moneybox d where d.id = :id"
			)
	,
	@NamedQuery(name="moneybox.getByUserId",
	query = "from moneybox d where d.userId.id = :id"
			)


})

@Entity
@Table(name = "moneybox")
public class moneybox {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	

	@ManyToOne
	@JoinColumn(name = "userId")
	private user userId;
	

	
	@Column(name = "availableMoney")
	private float availableMoney;
	
	
	@Column(name = "totalUsed")
	private float totalUsed;
	
	
	@Column(name = "isActive")
	private boolean isActive;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public user getUserId() {
		return userId;
	}


	public void setUserId(user userId) {
		this.userId = userId;
	}


	

	public float getAvailableMoney() {
		return availableMoney;
	}


	public void setAvailableMoney(float availableMoney) {
		this.availableMoney = availableMoney;
	}


	public float getTotalUsed() {
		return totalUsed;
	}


	public void setTotalUsed(float totalUsed) {
		this.totalUsed = totalUsed;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

	
}
