/**
 * 
 */
package main.com.carService.costCalc;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.com.carService.loginNeeds.user;

/**
 * @author A7med Al-Dakrory
 *
 */
@Repository
@Transactional
public class costCalcRepositoryImpl implements costCalcRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public costCalc addcostCalc(costCalc data) {
		try{
			user addedUser=data.getUserId();
			addedUser.setLastUpdate(Calendar.getInstance());
			data.setUserId(addedUser);
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
	public List<costCalc> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("costCalc.getAll");

				 @SuppressWarnings("unchecked")
				List<costCalc> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(costCalc data)throws Exception {
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
	public costCalc getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("costCalc.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<costCalc> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}

	@Override
	public List<costCalc> getAllByParentId(int idParent) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("costCalc.getAllByParentId").setInteger("id",idParent);

		 @SuppressWarnings("unchecked")
		List<costCalc> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<costCalc> getAllByUserOfParentOfParentId(int idParent) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("costCalc.getAllByUserOfParentOfParentId").setInteger("id",idParent);

		 @SuppressWarnings("unchecked")
		List<costCalc> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<costCalc> getAllByShipperOfParentId(int idParent) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("costCalc.getAllByShipperOfParentId").setInteger("id",idParent);

		 @SuppressWarnings("unchecked")
		List<costCalc> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}
	


}
