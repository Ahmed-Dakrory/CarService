package main.com.carService.myCars;


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
	
	
	@NamedQuery(name="mycars.getAll",
		     query="SELECT c FROM mycars c"
		     )
	,
	@NamedQuery(name="mycars.getById",
	query = "from mycars d where d.id = :id"
			)
	,
	@NamedQuery(name="mycars.getAllByUserIdAndType",
	query = "from mycars d where d.userId.id = :id and d.carLandingId.deleted = false and d.type =:type"
			)
	
	,
	@NamedQuery(name="mycars.getByUserIdAndCarIdAndType",
	query = "from mycars d where d.userId.id = :id and d.carLandingId.deleted = false and d.carLandingId.id =:carId and d.type =:type"
			)

})

@Entity
@Table(name = "mycars")
public class mycars {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	

	@ManyToOne
	@JoinColumn(name = "carLandingId")
	private carLanding carLandingId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private user userId;

	
	@Column(name = "type")
	private Integer type;

	public static Integer TYPE_WATCH_LIST=0;
	public static Integer TYPE_FAVORITE=1;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public carLanding getCarLandingId() {
		return carLandingId;
	}
	public void setCarLandingId(carLanding carLandingId) {
		this.carLandingId = carLandingId;
	}
	public user getUserId() {
		return userId;
	}
	public void setUserId(user userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
