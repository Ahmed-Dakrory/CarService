/**
 * 
 */
package main.com.carService.carLanding;

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
public class carLandingRepositoryImpl implements carLandingRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public carLanding addcarLanding(carLanding data) {
		try{

			data.setDateAdd(Calendar.getInstance());
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
	public List<carLanding> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carLanding.getAll");

				 @SuppressWarnings("unchecked")
				List<carLanding> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(carLanding data)throws Exception {
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
	public carLanding getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carLanding.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<carLanding> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}

	

	
	@Override
	public carLanding getByVin(String vinId) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carLanding.getByVin").setString("uuid",vinId);

				 @SuppressWarnings("unchecked")
				List<carLanding> results=query.list();
				 if(results.size()!=0){
					 return results.get(0);
				 }else{
					 return null;
				 }
	}

	@Override
	public List<carLanding> getAllForLanding() {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carLanding.getAllForLanding");

		 @SuppressWarnings("unchecked")
		List<carLanding> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<carLanding> getAllForCategories(int categories) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carLanding.getAllForCategories").setInteger("category",categories);

		 @SuppressWarnings("unchecked")
		List<carLanding> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<carLanding> getAllGroupsOfMake() {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carLanding.getAllGroupsOfMake");

		 @SuppressWarnings("unchecked")
		List<carLanding> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
}

	@Override
	public List<carLanding> getAllForSearch(String yearStart, String yearEnd, String make, Integer category) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carLanding.getAllForSearch")
				 .setInteger("category",category)
				 .setString("yearStart",yearStart)
				 .setString("yearEnd",yearEnd)
		 		 .setString("make",make);

		 @SuppressWarnings("unchecked")
		List<carLanding> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}


}
