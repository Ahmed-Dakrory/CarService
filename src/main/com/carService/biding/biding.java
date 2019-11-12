package main.com.carService.biding;


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

import main.com.carService.carLanding.carLanding;
import main.com.carService.loginNeeds.user;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="biding.getAll",
		     query="SELECT c FROM biding c"
		     )
	,
	@NamedQuery(name="biding.getById",
	query = "from biding d where d.id = :id"
			)
	,
	@NamedQuery(name="biding.getByCarIdAnduserId",
	query = "from biding d where d.carlandingId.id = :idcar and d.userId.id = :idUser"
			)
	
	,
	@NamedQuery(name="biding.getAllByuserId",
	query = "from biding d where d.userId.id = :idUser"
			)
	
	,
	@NamedQuery(name="biding.getByCarIdLessThanFullAmount",
	query = "from biding d where d.carlandingId.id = :idcar and d.fullAmount < :fullAmount order by d.fullAmount desc"
			)
	
	,
	@NamedQuery(name="biding.getByCarIdandMaxAmount",
	query = "FROM biding T WHERE T.fullAmount = (  SELECT MAX( T1.fullAmount ) FROM biding T1  WHERE T1.carlandingId.id = :idcar )"
			)
	,
	
	@NamedQuery(name="biding.getAllMaxCarBidings",
	query = "FROM biding T WHERE T.fullAmount in (  SELECT MAX( T1.fullAmount ) FROM biding T1  WHERE T1.carlandingId = T.carlandingId )"
			)
	
	
})

@Entity
@Table(name = "biding")
public class biding {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;


	
	@ManyToOne
	@JoinColumn(name = "userId")
	private user userId;


	@Column(name = "lastDateBid")
	private Date lastDateBid;
	
	
	@Column(name = "fullAmount")
	private float fullAmount;
	

	@Column(name = "increment")
	private float increment;
	
	
	@ManyToOne
	@JoinColumn(name = "carlandingId")
	private carLanding carlandingId;


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


	public Date getLastDateBid() {
		return lastDateBid;
	}


	public void setLastDateBid(Date lastDateBid) {
		this.lastDateBid = lastDateBid;
	}


	public float getFullAmount() {
		return fullAmount;
	}


	public void setFullAmount(float fullAmount) {
		this.fullAmount = fullAmount;
	}


	public float getIncrement() {
		return increment;
	}


	public void setIncrement(float increment) {
		this.increment = increment;
	}


	public carLanding getCarlandingId() {
		return carlandingId;
	}


	public void setCarlandingId(carLanding carlandingId) {
		this.carlandingId = carlandingId;
	}

	

	
	
}
