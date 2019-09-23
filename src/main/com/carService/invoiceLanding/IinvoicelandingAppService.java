/**
 * 
 */
package main.com.carService.invoiceLanding;

import java.util.Calendar;
import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface IinvoicelandingAppService {

	public List<invoicelanding> getAll();
	public List<invoicelanding> getAllByUserIdCustomer(int userId);
	public List<invoicelanding> getAllByUserId(int userId);
	public List<invoicelanding> getAllByUserIdBetweenDates(int userId,Calendar dateLower,Calendar dateHigh);
	public invoicelanding addinvoicelanding(invoicelanding data);
	public invoicelanding getById(int id);
	public boolean delete(invoicelanding data)throws Exception;
	
}
