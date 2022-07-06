package main.com.carService.log_info;


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

import com.google.gson.JsonObject;

import main.com.carService.car.car;
import main.com.carService.loginNeeds.user;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="log_info.getAll",
		     query="SELECT c FROM log_info c"
		     )
	,
	@NamedQuery(name="log_info.getById",
	query = "from log_info d where d.id = :id"
			)
	,
	@NamedQuery(name="log_info.getAllByuserId",
	query = "from log_info d where d.userId.id = :id order by d.date desc"
			)
	


})

@Entity
@Table(name = "log_info")
public class log_info {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	

	@ManyToOne
	@JoinColumn(name = "userId")
	private user userId;
	

	@Column(name = "data")
	private String data;

	@Column(name = "object_id")
	private String object_id;
	

	@ManyToOne
	@JoinColumn(name = "carId")
	private car carId;

	@Column(name = "object")
	private String object;
	
	
	@Column(name = "date")
	private Date date;


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


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getObject_id() {
		return object_id;
	}


	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}


	public String getObject() {
		return object;
	}


	public void setObject(String object) {
		this.object = object;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public car getCarId() {
		return carId;
	}


	public void setCarId(car carId) {
		this.carId = carId;
	}

	
	

	
	 public JsonObject toJson() {
	    	JsonObject obj=new JsonObject();
	    	  obj.addProperty("id", String.valueOf(this.id));
		      obj.addProperty("data", String.valueOf(this.data));
		      if(this.userId!=null) {
			      obj.addProperty("userId", String.valueOf(this.userId.getId()));
			      }else {
				      obj.addProperty("userId", String.valueOf("null"));
			    	  
			      }
		      
		      
		      if(this.userId!=null) {
			      obj.addProperty("username", String.valueOf(this.userId.getUserName()));
			      }else {
				      obj.addProperty("username", String.valueOf("null"));
			    	  
			      }
		      
		      if(this.userId!=null) {
			      obj.addProperty("email", String.valueOf(this.userId.getEmail()));
			      }else {
				      obj.addProperty("email", String.valueOf("null"));
			    	  
			      }
		      
		      
		      if(this.userId!=null) {
			      obj.addProperty("role", String.valueOf(this.userId.getRoleString()));
			      }else {
				      obj.addProperty("role", String.valueOf("null"));
			    	  
			      }
		      
		      if(this.userId!=null) {
			      obj.addProperty("firstname", String.valueOf(this.userId.getFirstName()));
			      }else {
				      obj.addProperty("firstname", String.valueOf("null"));
			    	  
			      }
		      
		      
		      if(this.carId!=null) {
			      obj.addProperty("uuid", String.valueOf(this.getCarId().getUuid()));
			      }else {
				      obj.addProperty("uuid", String.valueOf("null"));
			    	  
			      }
		      
		     
		     obj.addProperty("formatedDate", String.valueOf((this.date)));
		      
		      
		      return obj;
	    	
	    }
	
	

	
	
}
