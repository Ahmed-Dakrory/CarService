/**
 * 
 */
package main.com.carService.log_info;

import java.util.ArrayList;
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
public class log_infoRepositoryImpl implements log_infoRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public log_info addlog_info(log_info data) {
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
	public List<log_info> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("log_info.getAll");

				 @SuppressWarnings("unchecked")
				List<log_info> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(log_info data)throws Exception {
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
	public log_info getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("log_info.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<log_info> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}


	@Override
	public List<log_info> getAllByuserId(int userId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("log_info.getAllByuserId").setInteger("id",userId);

		 @SuppressWarnings("unchecked")
		 List<log_info> results=query.list();
		
			 return results;
		
	}

	@Override
	public List<log_info> getAllWithPagination(int start, int number, String searchValue, int role, int state,
			int useridAnyint, int col_order_number, String col_ordering) {
		 
		 try {
				session = sessionFactory.openSession();
				Transaction tx1 = session.beginTransaction();
				
				
				Query query =null;
				String stateQuery = "";
				
				
				
				String searchQuery = "";
				searchQuery = " ( lower(useraccount.userName) like lower('%"+searchValue+"%') or "
						+ " lower(useraccount.firstName) like lower('%"+searchValue+"%') or "
						+ " lower(useraccount.lastName) like lower('%"+searchValue+"%') or "
						+ " lower(log.data) like lower('%"+searchValue+"%') or "
						+ " lower(vehicle.uuid) like lower('%"+searchValue+"%') ) ";
				
				
				String ordering_Quer="";
				if(col_order_number==5) {
					//Year
					ordering_Quer = "  order by log.date "+col_ordering;
				}else if(col_order_number==12) {
					// Cargo Received
					ordering_Quer = "  order by log.date "+col_ordering;
					
				}else {
					ordering_Quer = "  order by log.date desc";
					
				}
				System.out.println(stateQuery);
				if(searchValue.equalsIgnoreCase("")) {
					if(role==user.ROLE_MAIN) {
						 query = session.createQuery("select log FROM log_info log left JOIN log.userId useraccount left JOIN log.carId vehicle  "+ordering_Quer);
					}else if(role==user.ROLE_MAIN2) {
						 query = session.createQuery("select log FROM log_info log left JOIN log.userId useraccount left JOIN log.carId vehicle where log.userId= "+String.valueOf(useridAnyint)+" "+ordering_Quer);
					}
				}else {
					if(role==user.ROLE_MAIN) {
						 query = session.createQuery("select log FROM log_info log left JOIN log.userId useraccount left JOIN log.carId vehicle where "+searchQuery+" "+ordering_Quer);
					}else if(role==user.ROLE_MAIN2) {
						 query = session.createQuery("select log FROM log_info log left JOIN log.userId useraccount left JOIN log.carId vehicle where "+searchQuery+" and log.userId= "+String.valueOf(useridAnyint)+" "+ordering_Quer);
					}
				}
				System.out.println("-----------------------------------");
				System.out.println(query.getQueryString());
				
				 query.setFirstResult(start);
				 query.setMaxResults(number);
				 
				 @SuppressWarnings("unchecked")
				List<log_info> results=query.list();

				tx1.commit();
				session.close();
				return results;
			} catch (Exception ex) {
				
				System.out.println("-----------------------------------");
				System.out.println(ex.toString());
				return new ArrayList<log_info>();
			}
	
	}

	@Override
	public long getAllCountSearch(int start, int number, String searchValue, int role, int state, int useridAnyint,
			int col_order_number, String col_ordering) {
		try {
			session = sessionFactory.openSession();
			Transaction tx1 = session.beginTransaction();
			
			Query query =null;
			String stateQuery = "";
			
			String searchQuery = "";
			searchQuery = " ( lower(useraccount.userName) like lower('%"+searchValue+"%') or "
					+ " lower(useraccount.firstName) like lower('%"+searchValue+"%') or "
					+ " lower(useraccount.lastName) like lower('%"+searchValue+"%') or "
					+ " lower(log.data) like lower('%"+searchValue+"%') or "
					+ " lower(vehicle.uuid) like lower('%"+searchValue+"%') ) ";
			
			
			String ordering_Quer="";
			if(col_order_number==5) {
				//Year
				ordering_Quer = "  order by log.date "+col_ordering;
			}else if(col_order_number==12) {
				// Cargo Received
				ordering_Quer = "  order by log.date "+col_ordering;
				
			}else {
				ordering_Quer = "  order by log.date desc";
				
			}
			System.out.println(stateQuery);
			if(searchValue.equalsIgnoreCase("")) {
				if(role==user.ROLE_MAIN) {
					 query = session.createQuery("select count(*) FROM log_info log left JOIN log.userId useraccount left JOIN log.carId vehicle  "+ordering_Quer);
				}else if(role==user.ROLE_MAIN2) {
					 query = session.createQuery("select count(*) FROM log_info log left JOIN log.userId useraccount left JOIN log.carId vehicle where log.userId= "+String.valueOf(useridAnyint)+" "+ordering_Quer);
				}
			}else {
				if(role==user.ROLE_MAIN) {
					 query = session.createQuery("select count(*) FROM log_info log left JOIN log.userId useraccount left JOIN log.carId vehicle where "+searchQuery+" "+ordering_Quer);
				}else if(role==user.ROLE_MAIN2) {
					 query = session.createQuery("select count(*) FROM log_info log left JOIN log.userId useraccount left JOIN log.carId vehicle where "+searchQuery+" and log.userId= "+String.valueOf(useridAnyint)+" "+ordering_Quer);
				}
			}
			
			
			System.out.println("-----------------------------------");
			System.out.println(query.getQueryString());
			

			
			Number results=(Number) (query).uniqueResult();

			tx1.commit();
			session.close();
			return (long) results;
		} catch (Exception ex) {
			return 0;
		}
		
	}


}
