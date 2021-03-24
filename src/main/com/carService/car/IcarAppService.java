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
	public List<car> getAllWithAllowSendState(boolean state);
	
	public List<car> getAllWareHouseForMainUser(int userId);
	public List<car> getAllWareHouseForMainUserTwo(int mainTwoId);
	public List<car> getAllWareHouseForVendor(int vendorId);
	public List<car> getAllWareHouseForShipper(int shipperId);
	public List<car> getAllWareHouseForCustomer(int custmerId);
	public List<car> getAllWareHouseForConsignee(int consigneeId);
	public List<car> getAllWareHouseFornormalUserId(int normalUserId);

	public List<car> getAllDryCargoForMainUser(int userId);
	public List<car> getAllDryCargoForMainUserTwo(int mainTwoId);
	public List<car> getAllDryCargoForVendor(int vendorId);
	public List<car> getAllDryCargoForShipper(int shipperId);
	public List<car> getAllDryCargoForCustomer(int custmerId);
	public List<car> getAllDryCargoForConsignee(int consigneeId);
	public List<car> getAllDryCargoFornormalUserId(int normalUserId);
	

	public List<car> getAllFrightInTransitForMainUser(int userId);
	public List<car> getAllFrightInTransitForMainUserTwo(int mainTwoId);
	public List<car> getAllFrightInTransitForVendor(int vendorId);
	public List<car> getAllFrightInTransitForShipper(int shipperId);
	public List<car> getAllFrightInTransitForCustomer(int custmerId);
	public List<car> getAllFrightInTransitForConsignee(int consigneeId);
	public List<car> getAllFrightInTransitFornormalUserId(int normalUserId);
	

	public List<car> getAllFrightSentForPaymentForMainUser(int userId);
	public List<car> getAllFrightSentForPaymentForMainUserTwo(int mainTwoId);
	public List<car> getAllFrightSentForPaymentForVendor(int vendorId);
	public List<car> getAllFrightSentForPaymentForShipper(int shipperId);
	public List<car> getAllFrightSentForPaymentForCustomer(int custmerId);
	public List<car> getAllFrightSentForPaymentForConsignee(int consigneeId);
	public List<car> getAllFrightSentForPaymentFornormalUserId(int normalUserId);
	
	

	public List<car> getAllFrightAddByCustomerFornormalUserId(int normalUserId);
	public List<car> getAllFrightPayedByCustomerFornormalUserId(int normalUserId);
	public List<car> getAllFrightDelieveredFornormalUserId(int normalUserId);
	
	

	public List<car> getAllAddByCustomerForMainUser(int userId);
	public List<car> getAllPayedByCustomerForMainUser(int userId);
	public List<car> getAllDelieveredForMainUser(int userId);
	
	public car addcar(car data);
	public car getById(int id);
	public car getByVin(String vinId);
	public boolean delete(car data)throws Exception;
}
