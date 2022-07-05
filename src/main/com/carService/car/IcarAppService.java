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
	

	public List<car> getAllWithPagination(int start, int number,String searchValue,int role,int state,int useridAny ,int col_order_number, String col_ordering);
	public long getAllCountSearch(int start, int number,String searchValue,int role,int state,int useridAny ,int col_order_number, String col_ordering);
	
	
	
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
	
	
	public List<car> getAllFrightWithStateForMainUserTwo(int state,int mainTwoId);
	public List<car> getAllFrightWithStateForVendor(int state,int vendorId);
	public List<car> getAllFrightWithStateForShipper(int state,int shipperId);
	public List<car> getAllFrightWithStateForCustomer(int state,int custmerId);
	public List<car> getAllFrightWithStateForConsignee(int state,int consigneeId);
	
	

	public List<car> getAllFrightForMainUserTwo(int mainTwoId);
	public List<car> getAllFrightForVendor(int vendorId);
	public List<car> getAllFrightForShipper(int shipperId);
	public List<car> getAllFrightForCustomer(int custmerId);
	public List<car> getAllFrightForConsignee(int consigneeId);
	
	public List<car> getAllForNormalUser(int normalUserId);
	public List<car> getAllByStateForNormalUser(int normalUserId,int state);
	
	
	public List<car> getAllBytypeOfOrderForNormalUser(int normalUserId,int typeOfOrder);
	public List<car> getAllBytypeOfOrderAndStateForNormalUser(int normalUserId,int state,int typeOfOrder);
	public List<car> getAllBytypeOfOrderAndShippingStateForNormalUser(int normalUserId,int typeOfOrder);
	
	

	public List<car> getAllForMainUser(int userId);
	public List<car> getAllByStateForMainUser(int userId,int state);
	
	public car addcar(car data)throws Exception;
	public car getById(int id);
	public car getByVin(String vinId);
	public boolean delete(car data)throws Exception;
}
