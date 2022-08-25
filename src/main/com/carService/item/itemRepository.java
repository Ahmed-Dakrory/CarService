/**
 * 
 */
package main.com.carService.item;

import java.util.List;


/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface itemRepository {

	public List<item> getAll();
	public List<item> getAllByCarIdAndType(int idCar,int type);
	public item additem(item data);
	public item getByValue(String url);
	public item getById(int id);
	public boolean delete(item data)throws Exception;
}
