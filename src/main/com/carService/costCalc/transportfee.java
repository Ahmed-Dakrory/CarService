package main.com.carService.costCalc;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.google.gson.JsonObject;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="transportfee.getAll",
		     query="SELECT c FROM transportfee c"
		     )
	,
	@NamedQuery(name="transportfee.getById",
	query = "from transportfee d where d.id = :id"
			)
	,
	@NamedQuery(name="transportfee.getAllGroupsOfLocation",
	query = "from transportfee d group by d.location"
			)
	,
	@NamedQuery(name="transportfee.getAllGroupsOfCityWithLocation",
	query = "from transportfee d where d.location = :location group by d.city"
			)
	,
	@NamedQuery(name="transportfee.getAllGroupsOfstateWithCity",
	query = "from transportfee d where d.city = :city group by d.state"
			)
	,
	@NamedQuery(name="transportfee.getWithSpecs",
	query = "from transportfee d where d.location = :location and d.city = :city and d.state = :state"
			)
	
	
})

@Entity
@Table(name = "transportfee")
public class transportfee {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	
	@Column(name = "njPortCost")
	private Integer njPortCost;
	
	@Column(name = "gaPortCost")
	private Integer gaPortCost;
		
	@Column(name = "txPortCost")
	private Integer txPortCost;

	@Column(name = "caPortCost")
	private Integer caPortCost;
	

	@Column(name = "floridaPortCost")
	private Float floridaPortCost;
	

	@Column(name = "paltimorPortCost")
	private Float paltimorPortCost;
	

	@Column(name = "indianaPortCost")
	private Float indianaPortCost;
	

	@Column(name = "lowCost")
	private Integer lowCost;
	

	@Column(name = "highCost")
	private Integer highCost;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Integer getNjPortCost() {
		return njPortCost;
	}


	public void setNjPortCost(Integer njPortCost) {
		this.njPortCost = njPortCost;
	}


	public Integer getGaPortCost() {
		return gaPortCost;
	}


	public void setGaPortCost(Integer gaPortCost) {
		this.gaPortCost = gaPortCost;
	}


	public Integer getTxPortCost() {
		return txPortCost;
	}


	public void setTxPortCost(Integer txPortCost) {
		this.txPortCost = txPortCost;
	}


	public Integer getCaPortCost() {
		return caPortCost;
	}


	public void setCaPortCost(Integer caPortCost) {
		this.caPortCost = caPortCost;
	}


	
	
	public Float getFloridaPortCost() {
		return floridaPortCost;
	}


	public void setFloridaPortCost(Float floridaPortCost) {
		this.floridaPortCost = floridaPortCost;
	}


	public Float getPaltimorPortCost() {
		return paltimorPortCost;
	}


	public void setPaltimorPortCost(Float paltimorPortCost) {
		this.paltimorPortCost = paltimorPortCost;
	}


	public Float getIndianaPortCost() {
		return indianaPortCost;
	}


	public void setIndianaPortCost(Float indianaPortCost) {
		this.indianaPortCost = indianaPortCost;
	}


	public Integer getLowCost() {
		return lowCost;
	}


	public void setLowCost(Integer lowCost) {
		this.lowCost = lowCost;
	}


	public Integer getHighCost() {
		return highCost;
	}


	public void setHighCost(Integer highCost) {
		this.highCost = highCost;
	}


	
	

	
	



	public JsonObject toJson() {
    	JsonObject obj=new JsonObject();
    	  obj.addProperty("id", String.valueOf(this.id));
	      
	      obj.addProperty("lowCost", String.valueOf(this.lowCost));
	      obj.addProperty("highCost", String.valueOf(this.highCost));
	      obj.addProperty("location", String.valueOf(this.location));
	      obj.addProperty("city", String.valueOf(this.city));
	      obj.addProperty("state", String.valueOf(this.state));
	      obj.addProperty("njPortCost", String.valueOf(this.njPortCost));
	      obj.addProperty("gaPortCost", String.valueOf(this.gaPortCost));
	      obj.addProperty("caPortCost", String.valueOf(this.caPortCost));
	      obj.addProperty("txPortCost", String.valueOf(this.txPortCost));
	      
	      return obj;
    	
    }

	
	
}
