package main.com.carService.invoiceCars;


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
import main.com.carService.invoice.invoice;



/**
 * 
 * @author Ahmed.Dakrory
 *
 */


@NamedQueries({
	
	
	@NamedQuery(name="invoiceCar.getAll",
		     query="SELECT c FROM invoiceCar c"
		     )
	,
	@NamedQuery(name="invoiceCar.getById",
	query = "from invoiceCar d where d.id = :id"
			)
	,
	@NamedQuery(name="invoiceCar.getAllByinvoiceId",
	query = "from invoiceCar d where d.invoiceId.id = :id"
			)

})

@Entity
@Table(name = "invoiceCar")
public class invoiceCar {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	

	@ManyToOne
	@JoinColumn(name = "carId")
	private car carId;
	
	@ManyToOne
	@JoinColumn(name = "invoiceId")
	private invoice invoiceId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public car getCarId() {
		return carId;
	}

	public void setCarId(car carId) {
		this.carId = carId;
	}

	public invoice getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(invoice invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	

	
	
}
