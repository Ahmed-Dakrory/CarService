/**
 * 
 */
package main.com.carService.car;

import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface IcarAppService {

	public List<car> getAll();
	public List<car> getAllWareHouseForMainUser(int userId);
	public List<car> getAllWareHouseForVendor(int vendorId);
	public List<car> getAllWareHouseForShipper(int shipperId);
	public List<car> getAllWareHouseForCustomer(int custmerId);
	public List<car> getAllWareHouseForConsignee(int consigneeId);

	public List<car> getAllDryCargoForMainUser(int userId);
	public List<car> getAllDryCargoForVendor(int vendorId);
	public List<car> getAllDryCargoForShipper(int shipperId);
	public List<car> getAllDryCargoForCustomer(int custmerId);
	public List<car> getAllDryCargoForConsignee(int consigneeId);
	

	public List<car> getAllFrightInTransitForMainUser(int userId);
	public List<car> getAllFrightInTransitForVendor(int vendorId);
	public List<car> getAllFrightInTransitForShipper(int shipperId);
	public List<car> getAllFrightInTransitForCustomer(int custmerId);
	public List<car> getAllFrightInTransitForConsignee(int consigneeId);
	public car addcar(car data);
	public car getById(int id);
	public car getByVin(String vinId);
	public boolean delete(car data)throws Exception;
}
