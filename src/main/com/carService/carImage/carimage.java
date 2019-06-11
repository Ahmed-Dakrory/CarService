package main.com.carService.carImage;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import main.com.carService.car.car;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="carimage.getAll",
		     query="SELECT c FROM carimage c"
		     )
	,
	@NamedQuery(name="carimage.getById",
	query = "from carimage d where d.id = :id"
			)
	,
	@NamedQuery(name="carimage.getAllByCarIdAndType",
	query = "from carimage d where d.carId.id = :id and d.type = :type"
			)
	,
	
	@NamedQuery(name="carimage.getByUrl",
	query = "from carimage d where d.url = :url"
			)
	
	
})

@Entity
@Table(name = "carimage")
public class carimage {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "url")
	private String url;
	
	
	@ManyToOne
	@JoinColumn(name = "carId")
	private car carId;


	public static int TYPE_PIC=0;
	public static int TYPE_DOC=1;
	
	@Column(name = "type")
	private Integer type;

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


	public car getCarId() {
		return carId;
	}


	public void setCarId(car carId) {
		this.carId = carId;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}
	
	
	

	
	
}
