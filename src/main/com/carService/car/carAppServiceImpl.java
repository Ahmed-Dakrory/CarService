/**
 * 
 */
package main.com.carService.car;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.carService.log_info.log_infoRepository;

/**
 * @author Dakrory
 *
 */
@Service("carFacadeImpl")
public class carAppServiceImpl implements IcarAppService{

	@Autowired
	carRepository carDataRepository;
	

	@Autowired
	log_infoRepository log_infoDataRepository;
	
	@Override
	public List<car> getAll() {
		try{
			List<car> course=carDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public car addcar(car data)throws Exception {
		try{
			
			car data2=carDataRepository.addcar(data);
			return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				throw ex;
			}
	}
	
	


	@Override
	public boolean delete(car data)throws Exception {
		// TODO Auto-generated method stub
		try{
			carDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public car getById(int id) {
		// TODO Auto-generated method stub
		try{
			car so=carDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	

	@Override
	public List<car> getAllWareHouseForMainUser(int userId) {
		try{
			List<car> course=carDataRepository.getAllWareHouseForMainUser(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllWareHouseForVendor(int vendorId) {
		try{
			List<car> course=carDataRepository.getAllWareHouseForVendor(vendorId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllWareHouseForShipper(int shipperId) {
		try{
			List<car> course=carDataRepository.getAllWareHouseForShipper(shipperId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllWareHouseForCustomer(int custmerId) {
		try{
			List<car> course=carDataRepository.getAllWareHouseForCustomer(custmerId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllWareHouseForConsignee(int consigneeId) {
		try{
			List<car> course=carDataRepository.getAllWareHouseForConsignee(consigneeId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllDryCargoForMainUser(int userId) {
		try{
			List<car> course=carDataRepository.getAllDryCargoForMainUser(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllDryCargoForVendor(int vendorId) {
		try{
			List<car> course=carDataRepository.getAllDryCargoForVendor(vendorId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllDryCargoForShipper(int shipperId) {
		try{
			List<car> course=carDataRepository.getAllDryCargoForShipper(shipperId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllDryCargoForCustomer(int custmerId) {
		try{
			List<car> course=carDataRepository.getAllDryCargoForCustomer(custmerId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllDryCargoForConsignee(int consigneeId) {
		try{
			List<car> course=carDataRepository.getAllDryCargoForConsignee(consigneeId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightInTransitForMainUser(int userId) {
		try{
			List<car> course=carDataRepository.getAllFrightInTransitForMainUser(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightInTransitForVendor(int vendorId) {
		try{
			List<car> course=carDataRepository.getAllFrightInTransitForVendor(vendorId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightInTransitForShipper(int shipperId) {
		try{
			List<car> course=carDataRepository.getAllFrightInTransitForShipper(shipperId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightInTransitForCustomer(int custmerId) {
		try{
			List<car> course=carDataRepository.getAllFrightInTransitForCustomer(custmerId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightInTransitForConsignee(int consigneeId) {
		try{
			List<car> course=carDataRepository.getAllFrightInTransitForConsignee(consigneeId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public car getByVin(String vinId) {
		// TODO Auto-generated method stub
				try{
					car so=carDataRepository.getByVin(vinId);
					
					return so;
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						return null;
					}
	}



	@Override
	public List<car> getAllWithAllowSendState(boolean state) {
		try{
			List<car> course=carDataRepository.getAllWithAllowSendState(state);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightSentForPaymentForMainUser(int userId) {
		try{
			List<car> course=carDataRepository.getAllFrightSentForPaymentForMainUser(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightSentForPaymentForVendor(int vendorId) {
		try{
			List<car> course=carDataRepository.getAllFrightSentForPaymentForVendor(vendorId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightSentForPaymentForShipper(int shipperId) {
		try{
			List<car> course=carDataRepository.getAllFrightSentForPaymentForShipper(shipperId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightSentForPaymentForCustomer(int custmerId) {
		try{
			List<car> course=carDataRepository.getAllFrightSentForPaymentForCustomer(custmerId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightSentForPaymentForConsignee(int consigneeId) {
		try{
			List<car> course=carDataRepository.getAllFrightSentForPaymentForConsignee(consigneeId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllWareHouseForMainUserTwo(int mainTwoId) {
		try{
			List<car> course=carDataRepository.getAllWareHouseForMainUserTwo(mainTwoId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllDryCargoForMainUserTwo(int mainTwoId) {
		try{
			List<car> course=carDataRepository.getAllDryCargoForMainUserTwo(mainTwoId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightInTransitForMainUserTwo(int mainTwoId) {
		try{
			List<car> course=carDataRepository.getAllFrightInTransitForMainUserTwo(mainTwoId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightSentForPaymentForMainUserTwo(int mainTwoId) {
		try{
			List<car> course=carDataRepository.getAllFrightSentForPaymentForMainUserTwo(mainTwoId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllWareHouseFornormalUserId(int normalUserId) {
		try{
			List<car> course=carDataRepository.getAllWareHouseFornormalUserId(normalUserId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllDryCargoFornormalUserId(int normalUserId) {
		try{
			List<car> course=carDataRepository.getAllDryCargoFornormalUserId(normalUserId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightInTransitFornormalUserId(int normalUserId) {
		try{
			List<car> course=carDataRepository.getAllFrightInTransitFornormalUserId(normalUserId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightSentForPaymentFornormalUserId(int normalUserId) {
		try{
			List<car> course=carDataRepository.getAllFrightSentForPaymentFornormalUserId(normalUserId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public List<car> getAllForNormalUser(int normalUserId) {
		try{
			List<car> course=carDataRepository.getAllForNormalUser(normalUserId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllByStateForNormalUser(int normalUserId, int state) {
		try{
			List<car> course=carDataRepository.getAllByStateForNormalUser(normalUserId,state);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllForMainUser(int userId) {
		try{
			List<car> course=carDataRepository.getAllForMainUser(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllByStateForMainUser(int userId, int state) {
		try{
			List<car> course=carDataRepository.getAllByStateForMainUser(userId,state);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllBytypeOfOrderForNormalUser(int normalUserId, int typeOfOrder) {
		try{
			List<car> course=carDataRepository.getAllBytypeOfOrderForNormalUser(normalUserId,typeOfOrder);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllBytypeOfOrderAndStateForNormalUser(int normalUserId, int state, int typeOfOrder) {
		try{
			List<car> course=carDataRepository.getAllBytypeOfOrderAndStateForNormalUser(normalUserId,state,typeOfOrder);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightWithStateForMainUserTwo(int state, int mainTwoId) {
		try{
			List<car> course=carDataRepository.getAllFrightWithStateForMainUserTwo(state,mainTwoId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightWithStateForVendor(int state, int vendorId) {
		try{
			List<car> course=carDataRepository.getAllFrightWithStateForVendor(state,vendorId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightWithStateForShipper(int state, int shipperId) {
		try{
			List<car> course=carDataRepository.getAllFrightWithStateForShipper(state,shipperId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightWithStateForCustomer(int state, int custmerId) {
		try{
			List<car> course=carDataRepository.getAllFrightWithStateForCustomer(state,custmerId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightWithStateForConsignee(int state, int consigneeId) {
		try{
			List<car> course=carDataRepository.getAllFrightWithStateForConsignee(state,consigneeId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightForMainUserTwo(int mainTwoId) {
		try{
			List<car> course=carDataRepository.getAllFrightForMainUserTwo(mainTwoId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightForVendor(int vendorId) {
		try{
			List<car> course=carDataRepository.getAllFrightForVendor(vendorId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightForShipper(int shipperId) {
		try{
			List<car> course=carDataRepository.getAllFrightForShipper(shipperId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightForCustomer(int custmerId) {
		try{
			List<car> course=carDataRepository.getAllFrightForCustomer(custmerId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllFrightForConsignee(int consigneeId) {
		try{
			List<car> course=carDataRepository.getAllFrightForConsignee(consigneeId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllBytypeOfOrderAndShippingStateForNormalUser(int normalUserId, int typeOfOrder) {
		try{
			List<car> course=carDataRepository.getAllBytypeOfOrderAndShippingStateForNormalUser(normalUserId,typeOfOrder);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<car> getAllWithPagination(int start, int number, String searchValue, int role, int state,
			int useridAny ,int col_order_number, String col_ordering) {
		try{
			List<car> course=carDataRepository.getAllWithPagination( start,  number,  searchValue,  role,  state,
					 useridAny,col_order_number,  col_ordering);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public long getAllCountSearch(int start, int number, String searchValue, int role, int state, int useridAny ,int col_order_number, String col_ordering) {
		try{
			long course=carDataRepository.getAllCountSearch( start,  number,  searchValue,  role,  state,
					 useridAny,col_order_number,  col_ordering);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return 0;
			}
	}



	public car addcar_no(car data) {
			
				
				car data2=carDataRepository.addcar_no(data);
				return data2;
				
		
	}


		
	
}
		
		

	
		
	


