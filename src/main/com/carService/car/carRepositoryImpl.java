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
	public car addcar(car data) {
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
				return null;
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
	


}
