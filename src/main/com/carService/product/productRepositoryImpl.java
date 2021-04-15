/**
 * 
 */
package main.com.carService.product;

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
public class productRepositoryImpl implements productRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public product addproduct(product data)throws Exception {
		try{

			data.setLastUpdate(Calendar.getInstance());
			if(data.getId()==null || data.getId()==0) {
				data.setAdd_datetime(Calendar.getInstance());
			}
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
	public List<product> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("product.getAll");

				 @SuppressWarnings("unchecked")
				List<product> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(product data)throws Exception {
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
	public product getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("product.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<product> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}

	



	@Override
	public List<product> getAllForNormalUser(int normalUserId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("product.getAllForNormalUser").setInteger("normalUserId",normalUserId);

		 @SuppressWarnings("unchecked")
		List<product> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<product> getAllByStateForNormalUser(int normalUserId, int state) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("product.getAllByStateForNormalUser")
				.setInteger("normalUserId",normalUserId)
				.setInteger("state",state);

		 @SuppressWarnings("unchecked")
		List<product> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<product> getAllForMainUser(int userId) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("product.getAllForMainUser").setInteger("userId",userId);

		 @SuppressWarnings("unchecked")
		List<product> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<product> getAllByStateForMainUser(int userId, int state) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("product.getAllByStateForMainUser")
				.setInteger("userId",userId)
				.setInteger("state",state);

		 @SuppressWarnings("unchecked")
		List<product> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<product> getAllBytypeOfOrderForNormalUser(int normalUserId, int typeOfOrder) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("product.getAllBytypeOfOrderForNormalUser")
				.setInteger("normalUserId",normalUserId)
				.setInteger("typeOfOrder",typeOfOrder);

		 @SuppressWarnings("unchecked")
		List<product> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<product> getAllBytypeOfOrderAndStateForNormalUser(int normalUserId, int state, int typeOfOrder) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("product.getAllBytypeOfOrderAndStateForNormalUser")
				.setInteger("normalUserId",normalUserId)
				.setInteger("state",state)
				.setInteger("typeOfOrder",typeOfOrder);

		 @SuppressWarnings("unchecked")
		List<product> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<product> getAllByState(int state) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("product.getAllByState")
				.setInteger("state",state);
		 @SuppressWarnings("unchecked")
		List<product> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}




}
