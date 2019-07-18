/**
 * 
 */
package main.com.carService.carLanding;

import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface IcarLandingAppService {

	public List<carLanding> getAll();
	public List<carLanding> getAllForLanding();
	public List<carLanding> getAllGroupsOfMake();
	public List<carLanding> getAllForSearch(String yearStart,String yearEnd,String make,Integer category);
	public List<carLanding> getAllForCategories(int categories);
	public carLanding addcarLanding(carLanding data);
	public carLanding getById(int id);
	public carLanding getByVin(String vinId);
	public boolean delete(carLanding data)throws Exception;
}
