package main.com.carService.item;


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
	
	
	@NamedQuery(name="item.getAll",
		     query="SELECT c FROM item c where c.deleted = false"
		     )
	,
	@NamedQuery(name="item.getById",
	query = "from item d where d.id = :id and d.deleted = false"
			)
	,
	@NamedQuery(name="item.getAllByCarIdAndType",
	query = "from item d where d.carId.id = :id and d.type = :type and d.deleted = false"
			)
	,
	
	@NamedQuery(name="item.getByValue",
	query = "from item d where d.value = :value and d.deleted = false"
			)
	
	
})

@Entity
@Table(name = "item")
public class item {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "value")
	private String value;
	

	@Column(name = "description")
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name = "carId")
	private car carId;


	public static int TYPE_PRICE=0;
	public static int TYPE_DOC=1;
	
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


	


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
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
	
	
	
	public boolean isDeleted() {
		return deleted;
	}






	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
