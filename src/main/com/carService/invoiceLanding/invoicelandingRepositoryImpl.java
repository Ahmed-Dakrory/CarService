/**
 * 
 */
package main.com.carService.invoiceLanding;

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
public class invoicelandingRepositoryImpl implements invoicelandingRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public invoicelanding addinvoicelanding(invoicelanding data) {
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
	public List<invoicelanding> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("invoicelanding.getAll");

				 @SuppressWarnings("unchecked")
				List<invoicelanding> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(invoicelanding data)throws Exception {
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
	public invoicelanding getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("invoicelanding.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<invoicelanding> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}


	@Override
	public List<invoicelanding> getAllByUserIdCustomer(int userId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("invoicelanding.getAllByUserIdCustomer").setInteger("id",userId);

		 @SuppressWarnings("unchecked")
		 List<invoicelanding> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<invoicelanding> getAllByUserId(int userId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("invoicelanding.getAllByUserId").setInteger("id",userId);

		 @SuppressWarnings("unchecked")
		List<invoicelanding> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<invoicelanding> getAllByUserIdBetweenDates(int userId, Calendar dateLower, Calendar dateHigh) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("invoicelanding.getAllByUserIdBetweenDates")
				 .setInteger("id",userId).setCalendar("dateLower", dateLower).setCalendar("dateHigher", dateHigh);

		 @SuppressWarnings("unchecked")
		List<invoicelanding> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}
	


}
