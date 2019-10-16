/**
 * 
 */
package main.com.carService.myCars;

import java.util.List;


/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface mycarsRepository {

	public List<mycars> getAll();
	public List<mycars> getAllByUserIdAndType(int id,int type);
	public mycars addmycars(mycars data);
	public mycars getById(int id);
	public mycars getByUserIdAndCarIdAndType(int id,int type,int carId);
	public boolean delete(mycars data)throws Exception;
}
