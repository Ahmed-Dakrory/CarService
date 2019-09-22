/**
 * 
 */
package main.com.carService.notification;

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
public class notificationRepositoryImpl implements notificationRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public notification addnotification(notification data) {
		try{
			
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
	public List<notification> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("notification.getAll");

				 @SuppressWarnings("unchecked")
				List<notification> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(notification data)throws Exception {
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
	public notification getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("notification.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<notification> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}


	@Override
	public List<notification> getAllByuserId(int userId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("notification.getAllByuserId").setInteger("id",userId);

		 @SuppressWarnings("unchecked")
		 List<notification> results=query.list();
		
			 return results;
		
	}

	@Override
	public List<notification> getAllByuserIdAndState(int id, boolean state,int start,int end) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("notification.getAllByuserIdAndState").setInteger("id",id)
				 .setBoolean("state",state).setFirstResult(start).setMaxResults(start);

		 @SuppressWarnings("unchecked")
		 List<notification> results=query.list();
		
			 return results;
	}

	@Override
	public List<notification> getAllByuserIdAndLimit(int id, int start, int end) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("notification.getAllByuserIdAndLimit").setInteger("id",id)
				 .setFirstResult(start).setMaxResults(start);

		 @SuppressWarnings("unchecked")
		 List<notification> results=query.list();
		
			 return results;
	}
	


}
