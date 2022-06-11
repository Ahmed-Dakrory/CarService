/**
 * 
 */
package main.com.carService.costCalc;

import java.util.ArrayList;
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
public class transportfeeRepositoryImpl implements transportfeeRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public transportfee addtransportfee(transportfee data) {
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
	public List<transportfee> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("transportfee.getAll");

				 @SuppressWarnings("unchecked")
				List<transportfee> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(transportfee data)throws Exception {
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
	public transportfee getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("transportfee.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<transportfee> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}

	

	

	@Override
	public List<transportfee> getAllGroupsOfLocation() {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("transportfee.getAllGroupsOfLocation");

		 @SuppressWarnings("unchecked")
		List<transportfee> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	
	
	@Override
	public transportfee getWithSpecs(String location, String city, String state) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("transportfee.getWithSpecs").setString("location",location)
				 				.setString("city",city)
				 				.setString("state",state);

		 @SuppressWarnings("unchecked")
		List<transportfee> results=query.list();
		 if(results!=null){
			 if(results.size()>0) {
			 return results.get(0);
			 }else {
				 return null;
			 }
		 }else{
			 return null;
		 }
	}

	@Override
	public List<transportfee> getAllGroupsOfCityWithLocation(String location) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("transportfee.getAllGroupsOfCityWithLocation").setString("location", location);

		 @SuppressWarnings("unchecked")
		List<transportfee> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<transportfee> getAllGroupsOfstateWithCity(String city) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("transportfee.getAllGroupsOfstateWithCity").setString("city", city);

		 @SuppressWarnings("unchecked")
		List<transportfee> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public List<transportfee> getAllWithPagination(int start, int number, String searchValue) {
		 try {
				session = sessionFactory.openSession();
				Transaction tx1 = session.beginTransaction();
				
				
				Query query =null;
				if(searchValue.equalsIgnoreCase("")) {
					 query = session.createQuery("FROM transportfee order by id desc");
				}else {
				 query = session.createQuery("FROM transportfee where "
						+ " lower(location) like lower('%"+searchValue+"%') or "
						+ " lower(city) like lower('%"+searchValue+"%') or "
						+ " lower(state) like lower('%"+searchValue+"%') or "
						+ " lower(njPortCost) like lower('%"+searchValue+"%') or "
						+ " lower(gaPortCost) like lower('%"+searchValue+"%') or "
						+ " lower(txPortCost) like lower('%"+searchValue+"%') or "
						+ " lower(caPortCost) like lower('%"+searchValue+"%') or "
						+ " lower(lowCost) like lower('%"+searchValue+"%') or "
						+ " lower(highCost) like lower('%"+searchValue+"%') "
						+ " order by id desc");
				}
				
				 query.setFirstResult(start);
				 query.setMaxResults(number);
				 
				 @SuppressWarnings("unchecked")
				List<transportfee> results=query.list();

				tx1.commit();
				session.close();
				return results;
			} catch (Exception ex) {
				return new ArrayList<transportfee>();
			}

	}

	@Override
	public long getAllCount(String searchValue) {
		try {
			session = sessionFactory.openSession();
			Transaction tx1 = session.beginTransaction();
			 

			Query query =null;
			
			if(searchValue.equalsIgnoreCase("")) {
				 query = session.createQuery("select count(*) FROM transportfee order by id desc");
			}else {
			 query = session.createQuery("select count(*) FROM transportfee where "
					+ " lower(location) like lower('%"+searchValue+"%') or "
					+ " lower(city) like lower('%"+searchValue+"%') or "
					+ " lower(state) like lower('%"+searchValue+"%') or "
					+ " lower(njPortCost) like lower('%"+searchValue+"%') or "
					+ " lower(gaPortCost) like lower('%"+searchValue+"%') or "
					+ " lower(txPortCost) like lower('%"+searchValue+"%') or "
					+ " lower(caPortCost) like lower('%"+searchValue+"%') or "
					+ " lower(lowCost) like lower('%"+searchValue+"%') or "
					+ " lower(highCost) like lower('%"+searchValue+"%') "
					+ " order by id desc");
			}

			
			Number results=(Number) (query).uniqueResult();

			tx1.commit();
			session.close();
			return (long) results;
		} catch (Exception ex) {
			return 0;
		}
	}
	


}
