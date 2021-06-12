package main.com.carService.carLandingImage;


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



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="carlandingimage.getAll",
		     query="SELECT c FROM carlandingimage c where c.deleted = false"
		     )
	,
	@NamedQuery(name="carlandingimage.getById",
	query = "from carlandingimage d where d.id = :id and d.deleted = false"
			)
	,
	@NamedQuery(name="carlandingimage.getAllByCarIdAndType",
	query = "from carlandingimage d where d.carId.id = :id and d.type = :type and d.deleted = false"
			)
	,
	
	@NamedQuery(name="carlandingimage.getByUrl",
	query = "from carlandingimage d where d.url = :url and d.deleted = false"
			)
	
	
})

@Entity
@Table(name = "carlandingimage")
public class carlandingimage {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "url")
	private String url;
	
	
	@ManyToOne
	@JoinColumn(name = "carId")
	private carLanding carId;


	public static int TYPE_AUCTION=0;
	
	@Column(name = "type")
	private Integer type;

	

	@Column(name = "deleted")
	private boolean deleted;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public carLanding getCarId() {
		return carId;
	}


	public void setCarId(carLanding carId) {
		this.carId = carId;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
	public boolean isDeleted() {
		return deleted;
	}






	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
