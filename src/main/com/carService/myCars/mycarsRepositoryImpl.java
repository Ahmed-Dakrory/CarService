/**
 * 
 */
package main.com.carService.myCars;

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
public class mycarsRepositoryImpl implements mycarsRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public mycars addmycars(mycars data) {
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
	public List<mycars> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("mycars.getAll");

				 @SuppressWarnings("unchecked")
				List<mycars> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(mycars data)throws Exception {
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
	public mycars getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("mycars.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<mycars> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}


	@Override
	public List<mycars> getAllByUserIdAndType(int userId,int type) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("mycars.getAllByUserIdAndType").setInteger("id",userId).setInteger("type",type);

		 @SuppressWarnings("unchecked")
		 List<mycars> results=query.list();
		
			 return results;
		
	}

	@Override
	public mycars getByUserIdAndCarIdAndType(int id, int type, int carId) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("mycars.getByUserIdAndCarIdAndType").setInteger("id",id).setInteger("carId",carId).setInteger("type",type);

				 @SuppressWarnings("unchecked")
				List<mycars> results=query.list();
				 if(results.size()!=0){
					 return results.get(0);
				 }else{
					 return null;
				 }
	}
	


}
