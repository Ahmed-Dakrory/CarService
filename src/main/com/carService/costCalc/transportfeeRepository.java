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
public interface transportfeeRepository {

	public List<transportfee> getAll();
	public List<transportfee> getAllGroupsOfLocation();
	public List<transportfee> getAllGroupsOfCity();
	public List<transportfee> getAllGroupsOfstate();
	public transportfee getWithSpecs(String location,String city,String state);
	public transportfee addtransportfee(transportfee data);
	public transportfee getById(int id);
	public boolean delete(transportfee data)throws Exception;
}
