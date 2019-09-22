package main.com.carService.notification;


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

import main.com.carService.loginNeeds.user;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="notification.getAll",
		     query="SELECT c FROM notification c"
		     )
	,
	@NamedQuery(name="notification.getById",
	query = "from notification d where d.id = :id"
			)
	,
	@NamedQuery(name="notification.getAllByuserId",
	query = "from notification d where d.userId.id = :id order by d.date desc"
			)
	
	,
	@NamedQuery(name="notification.getAllByuserIdAndLimit",
	query = "from notification d where d.userId.id = :id order by d.date desc"
			)
	
	,
	@NamedQuery(name="notification.getAllByuserIdAndState",
	query = "from notification d where d.userId.id = :id and d.readed = :state order by d.date desc"
			)

})

@Entity
@Table(name = "notification")
public class notification {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	

	@ManyToOne
	@JoinColumn(name = "userId")
	private user userId;
	

	@Column(name = "data")
	private String data;
	
	@Column(name = "url")
	private String url;
	
	
	@Column(name = "readed")
	private boolean readed;
	
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

	public boolean isReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
}
