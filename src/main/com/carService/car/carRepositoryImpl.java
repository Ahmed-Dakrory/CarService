/**
 * 
 */
package main.com.carService.car;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author A7med Al-Dakrory
 *
 */
@Repository
@Transactional
public class carRepositoryImpl implements carRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public car addcar(car data)throws Exception {
		try{

			data.setLastUpdate(Calendar.getInstance());
			session = sessionFactory.openSession();
			Transaction tx1 = session.beginTransaction();
			session.saveOrUpdate(data);
			tx1.commit();
			session.close(); 
			return data; 
			}
			catch(Exception ex)
			{
				System.out.println(">>>>>>>>>>");
				ex.printStackTrace();
				throw ex;
			}
	}

	@Override
	public List<car> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAll");

				 @SuppressWarnings("unchecked")
				List<car> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(car data)throws Exception {
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.openSession();
			Transaction tx1 = session.beginTransaction();
			session.delete(data);
			tx1.commit();
			session.close();
			return true;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public car getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}

	

	@Override
	public List<car> getAllWareHouseForMainUser(int userId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllWareHouseForMainUser").setInteger("userId",userId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllWareHouseForVendor(int vendorId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllWareHouseForVendor").setInteger("vendorId",vendorId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllWareHouseForShipper(int shipperId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllWareHouseForShipper").setInteger("shipperId",shipperId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllWareHouseForCustomer(int customerId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllWareHouseForCustomer").setInteger("customerId",customerId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllWareHouseForConsignee(int consigneeId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllWareHouseForConsignee").setInteger("consigneeId",consigneeId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllDryCargoForMainUser(int userId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllDryCargoForMainUser").setInteger("userId",userId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllDryCargoForVendor(int vendorId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllDryCargoForVendor").setInteger("vendorId",vendorId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllDryCargoForShipper(int shipperId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllDryCargoForShipper").setInteger("shipperId",shipperId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllDryCargoForCustomer(int customerId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllDryCargoForCustomer").setInteger("customerId",customerId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllDryCargoForConsignee(int consigneeId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllDryCargoForConsignee").setInteger("consigneeId",consigneeId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightInTransitForMainUser(int userId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightInTransitForMainUser").setInteger("userId",userId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightInTransitForVendor(int vendorId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightInTransitForVendor").setInteger("vendorId",vendorId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightInTransitForShipper(int shipperId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightInTransitForShipper").setInteger("shipperId",shipperId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightInTransitForCustomer(int customerId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightInTransitForCustomer").setInteger("customerId",customerId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightInTransitForConsignee(int consigneeId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightInTransitForConsignee").setInteger("consigneeId",consigneeId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public car getByVin(String vinId) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getByVin").setString("uuid",vinId);

				 @SuppressWarnings("unchecked")
				List<car> results=query.list();
				 if(results.size()!=0){
					 return results.get(0);
				 }else{
					 return null;
				 }
	}

	@Override
	public List<car> getAllWithAllowSendState(boolean state) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllWithAllowSendState").setBoolean("state", state);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightSentForPaymentForMainUser(int userId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightSentForPaymentForMainUser").setInteger("userId",userId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightSentForPaymentForVendor(int vendorId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightSentForPaymentForVendor").setInteger("vendorId",vendorId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightSentForPaymentForShipper(int shipperId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightSentForPaymentForShipper").setInteger("shipperId",shipperId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightSentForPaymentForCustomer(int custmerId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightSentForPaymentForCustomer").setInteger("custmerId",custmerId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightSentForPaymentForConsignee(int consigneeId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightSentForPaymentForConsignee").setInteger("consigneeId",consigneeId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllWareHouseForMainUserTwo(int mainTwoId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllWareHouseForMainUserTwo").setInteger("mainTwoId",mainTwoId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllDryCargoForMainUserTwo(int mainTwoId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllDryCargoForMainUserTwo").setInteger("mainTwoId",mainTwoId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightInTransitForMainUserTwo(int mainTwoId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightInTransitForMainUserTwo").setInteger("mainTwoId",mainTwoId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightSentForPaymentForMainUserTwo(int mainTwoId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightSentForPaymentForMainUserTwo").setInteger("mainTwoId",mainTwoId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllWareHouseFornormalUserId(int normalUserId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllWareHouseFornormalUserId").setInteger("normalUserId",normalUserId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllDryCargoFornormalUserId(int normalUserId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllDryCargoFornormalUserId").setInteger("normalUserId",normalUserId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightInTransitFornormalUserId(int normalUserId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightInTransitFornormalUserId").setInteger("normalUserId",normalUserId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightSentForPaymentFornormalUserId(int normalUserId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllByStateForNormalUser").setInteger("normalUserId",normalUserId)
				.setInteger("state",car.STATE_Sent_For_Payment);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllForNormalUser(int normalUserId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllForNormalUser").setInteger("normalUserId",normalUserId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllByStateForNormalUser(int normalUserId, int state) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllByStateForNormalUser")
				.setInteger("normalUserId",normalUserId)
				.setInteger("state",state);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllForMainUser(int userId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllForMainUser").setInteger("userId",userId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllByStateForMainUser(int userId, int state) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllByStateForMainUser")
				.setInteger("userId",userId)
				.setInteger("state",state);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllBytypeOfOrderForNormalUser(int normalUserId, int typeOfOrder) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllBytypeOfOrderForNormalUser")
				.setInteger("normalUserId",normalUserId)
				.setInteger("typeOfOrder",typeOfOrder);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllBytypeOfOrderAndStateForNormalUser(int normalUserId, int state, int typeOfOrder) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllBytypeOfOrderAndStateForNormalUser")
				.setInteger("normalUserId",normalUserId)
				.setInteger("state",state)
				.setInteger("typeOfOrder",typeOfOrder);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}
//////////////////////////////////////////////////
	@Override
	public List<car> getAllFrightWithStateForMainUserTwo(int state, int mainTwoId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightWithStateForMainUserTwo")
				.setInteger("mainTwoId",mainTwoId)
				.setInteger("state",state);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightWithStateForVendor(int state, int vendorId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightWithStateForVendor")
				.setInteger("vendorId",vendorId)
				.setInteger("state",state);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightWithStateForShipper(int state, int shipperId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightWithStateForShipper")
				.setInteger("shipperId",shipperId)
				.setInteger("state",state);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightWithStateForCustomer(int state, int custmerId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightWithStateForCustomer")
				.setInteger("custmerId",custmerId)
				.setInteger("state",state);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightWithStateForConsignee(int state, int consigneeId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightWithStateForConsignee")
				.setInteger("consigneeId",consigneeId)
				.setInteger("state",state);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightForMainUserTwo(int mainTwoId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightForMainUserTwo")
				.setInteger("mainTwoId",mainTwoId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightForVendor(int vendorId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightForVendor")
				.setInteger("vendorId",vendorId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightForShipper(int shipperId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightForShipper")
				.setInteger("shipperId",shipperId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightForCustomer(int custmerId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightForCustomer")
				.setInteger("custmerId",custmerId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllFrightForConsignee(int consigneeId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllFrightForConsignee")
				.setInteger("consigneeId",consigneeId);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<car> getAllBytypeOfOrderAndShippingStateForNormalUser(int normalUserId, int typeOfOrder) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("car.getAllBytypeOfOrderAndShippingStateForNormalUser")
				.setInteger("normalUserId",normalUserId)
				.setInteger("typeOfOrder",typeOfOrder);

		 @SuppressWarnings("unchecked")
		List<car> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}



}
