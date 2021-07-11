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
public interface IcarLandingAppService {

	public List<carLanding> getAll();
	public long getAllCount();
	public long getAllCount(int id);
	public List<carLanding> getAllForLanding();
	public List<carLanding> getAllGroupsOfMake();
	public List<carLanding> getAllGroupsOfCategory();
	public List<carLanding> getAllWithPagination(int start, int number,String searchValue);
	public List<carLanding> getAllWithPaginationWithId(int start, int number,String searchValue,int id);
	public List<carLanding> getAllWithPaginationSearch(int start, int number,String searchValue,String yearStart,String yearEnd,String make,String model,String category,String auctionType);
	public long getAllCountSearch(String searchValue,String yearStart,String yearEnd,String make,String model,String category,String auctionType);
	public long getAllWithPaginationCount(int start, int number);
	public List<carLanding> getAllGroupsOfModelWithMake(String model);
	public List<carLanding> getAllForSearch(String yearStart,String yearEnd,String make,String model,String category);
	public List<carLanding> getAllForCategories(String categories);
	public List<carLanding> getAllBetweenDates(Calendar date1,Calendar date2);
	public List<carLanding> getAllBidBetweenDates(Calendar date1,Calendar date2);
	public List<carLanding> getAllForUserBiding(int id);
	public carLanding addcarLanding(carLanding data);
	public carLanding getNextRecord(int id);
	public carLanding getPreviousRecord(int id);
	public carLanding getById(int id);
	public carLanding getByVin(String vinId);
	public boolean delete(carLanding data)throws Exception;
}
