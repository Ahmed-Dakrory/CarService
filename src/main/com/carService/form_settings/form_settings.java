package main.com.carService.form_settings;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="form_settings.getAll",
		     query="SELECT c FROM form_settings c"
		     )
	,
	@NamedQuery(name="form_settings.getById",
	query = "from form_settings d where d.id = :id"
			)
	,
	@NamedQuery(name="form_settings.getByName",
	query = "from form_settings d where d.name = :name"
			)
	,
	@NamedQuery(name="form_settings.getAllByType",
	query = "from form_settings d where d.type = :type"
			)

	
})

@Entity
@Table(name = "form_settings")
public class form_settings {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	

	@Column(name = "name")
	private String name;
	

	@Column(name = "value")
	private String value;
	

	@Column(name = "value1")
	private String value1;

	
	@Column(name = "type")
	private int type;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getValue1() {
		return value1;
	}


	public void setValue1(String value1) {
		this.value1 = value1;
	}
	
	
	
	
	
	
}
