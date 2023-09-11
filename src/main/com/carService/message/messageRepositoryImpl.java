/**
 * 
 */
package main.com.carService.message;

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
public class messageRepositoryImpl implements messageRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public message addmessage(message data) {
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
	public List<message> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("message.getAll");

				 @SuppressWarnings("unchecked")
				List<message> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(message data)throws Exception {
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
	public message getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("message.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<message> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}



	


	@Override
	public List<message> getAllByto_userId(int to_userId) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("message.getAllByto_userId").setInteger("to_userId",to_userId);

				 @SuppressWarnings("unchecked")
				List<message> results=query.list();
				
					 return results;
				
	}

	
	
	@Override
	public List<message> getAllByfrom_userId(int from_userId) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("message.getAllByfrom_userId").setInteger("from_userId",from_userId);

				 @SuppressWarnings("unchecked")
				List<message> results=query.list();
				
					 return results;
				
	}

	

	
	


}
