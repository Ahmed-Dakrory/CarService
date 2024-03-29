/**
 * 
 */
package main.com.carService.costCalc;

import java.util.List;


/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface ItransportfeeAppService {

	public List<transportfee> getAll();
	public List<transportfee> getAllGroupsOfLocation();

	public long getAllCount(String searchValue);
	public List<transportfee> getAllWithPagination(int start, int number,String searchValue);
	public List<transportfee> getAllGroupsOfCityWithLocation(String location);
	public List<transportfee> getAllGroupsOfstateWithCity(String city);
	public transportfee getWithSpecs(String location,String city,String state);
	public transportfee addtransportfee(transportfee data);
	public transportfee getById(int id);
	public boolean delete(transportfee data)throws Exception;
}
