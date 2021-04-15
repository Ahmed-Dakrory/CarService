package main.com.carService.productImage;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import main.com.carService.product.product;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="productimage.getAll",
		     query="SELECT c FROM productimage c where c.deleted = false"
		     )
	,
	@NamedQuery(name="productimage.getById",
	query = "from productimage d where d.id = :id and d.deleted = false"
			)
	,
	@NamedQuery(name="productimage.getAllByproductIdAndType",
	query = "from productimage d where d.productId.id = :id and d.type = :type and d.deleted = false"
			)
	,
	
	@NamedQuery(name="productimage.getByUrl",
	query = "from productimage d where d.url = :url and d.deleted = false"
			)
	
	
})

@Entity
@Table(name = "productimage")
public class productimage {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "url")
	private String url;
	
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private product productId;


	public static int TYPE_PIC=0;
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



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public product getProductId() {
		return productId;
	}



	public void setProductId(product productId) {
		this.productId = productId;
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
