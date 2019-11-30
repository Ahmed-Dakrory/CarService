/**
 * 
 */
package main.com.carService.carLanding;

import java.util.Calendar;
import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface carLandingRepository {

	public List<carLanding> getAll();
	public List<carLanding> getAllForLanding();
	public List<carLanding> getAllGroupsOfMake();
	public List<carLanding> getAllGroupsOfModelWithMake(String model);
	public List<carLanding> getAllBetweenDates(Calendar date1,Calendar date2);
	public List<carLanding> getAllBidBetweenDates(Calendar date1,Calendar date2);
	public List<carLanding> getAllForSearch(String yearStart,String yearEnd,String make,String model,String category);
	public List<carLanding> getAllForCategories(int categories);
	public List<carLanding> getAllForUserBiding(int id);
	public carLanding addcarLanding(carLanding data);
	public carLanding getById(int id);
	public carLanding getByVin(String vinId);
	public boolean delete(carLanding data)throws Exception;
}
