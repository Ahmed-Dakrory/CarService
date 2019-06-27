/**
 * 
 */
package main.com.carService.invoice;

import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface IinvoiceAppService {

	public List<invoice> getAll();
	public invoice getByUserIdCustomer(int userId);
	public invoice addinvoice(invoice data);
	public invoice getById(int id);
	public boolean delete(invoice data)throws Exception;
}
