package main.com.carService.message;


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
	
	
	@NamedQuery(name="message.getAll",
		     query="SELECT c FROM message c"
		     )
	,
	@NamedQuery(name="message.getById",
	query = "from message d where d.id = :id"
			)
	,
	@NamedQuery(name="message.getAllByfrom_userId",
	query = "from message d where d.from_userId.id = :from_userId "
			)
	
	
	,
	@NamedQuery(name="message.getAllByto_userId",
	query = "from message d where d.to_userId.id = :to_userId"
			)
	

	
	
})

@Entity
@Table(name = "message")
public class message {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;



	@ManyToOne
	@JoinColumn(name = "from_userId")
	private user from_userId;
	
	

	@ManyToOne
	@JoinColumn(name = "to_userId")
	private user to_userId;


	@Column(name = "date")
	private Date date;

	@Column(name = "readed")
	private boolean readed;
	

	@Column(name = "subject")
	private String subject;
	

	@Column(name = "content")
	private String content;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public user getFrom_userId() {
		return from_userId;
	}


	public void setFrom_userId(user from_userId) {
		this.from_userId = from_userId;
	}


	public user getTo_userId() {
		return to_userId;
	}


	public void setTo_userId(user to_userId) {
		this.to_userId = to_userId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public boolean isReaded() {
		return readed;
	}


	public void setReaded(boolean readed) {
		this.readed = readed;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	

	
	

	
}
