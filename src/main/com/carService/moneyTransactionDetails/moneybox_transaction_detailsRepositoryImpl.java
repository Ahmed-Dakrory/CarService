/**
 * 
 */
package main.com.carService.moneyTransactionDetails;

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
public class moneybox_transaction_detailsRepositoryImpl implements moneybox_transaction_detailsRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public moneybox_transaction_details addmoneybox_transaction_details(moneybox_transaction_details data) {
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
	public List<moneybox_transaction_details> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("moneybox_transaction_details.getAll");

				 @SuppressWarnings("unchecked")
				List<moneybox_transaction_details> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(moneybox_transaction_details data)throws Exception {
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
	public moneybox_transaction_details getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("moneybox_transaction_details.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<moneybox_transaction_details> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}


	@Override
	public List<moneybox_transaction_details> getAllByUserMoneyBoxId(int userId) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("moneybox_transaction_details.getAllByUserMoneyBoxId").setInteger("id",userId);

		 @SuppressWarnings("unchecked")
		 List<moneybox_transaction_details> results=query.list();
		  return results;
		
	}
	
	
	@Override
	public List<moneybox_transaction_details> getAllByUserMoneyBoxId(int userId,int start,int limit) {
		 Query query 	=sessionFactory.getCurrentSession()
				 .getNamedQuery("moneybox_transaction_details.getAllByUserMoneyBoxId")
				 .setInteger("id",userId)
				 .setFirstResult(start)
				 .setMaxResults(limit);

		 @SuppressWarnings("unchecked")
		 List<moneybox_transaction_details> results=query.list();
		  return results;
		
	}

	@Override
	public List<moneybox_transaction_details> getAllByCarId(int carId) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("moneybox_transaction_details.getAllByCarId").setInteger("carId",carId);

				 @SuppressWarnings("unchecked")
				 List<moneybox_transaction_details> results=query.list();
				  return results;
				
	}

	@Override
	public List<moneybox_transaction_details> getAllBy_wire_transfer_number(String wire_transfer_number) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("moneybox_transaction_details.getAllBy_wire_transfer_number").setString("wire_transfer_number",wire_transfer_number);

				 @SuppressWarnings("unchecked")
				 List<moneybox_transaction_details> results=query.list();
				  return results;
				
	}

	@Override
	public List<moneybox_transaction_details> getAllByCarIdAndMoneyBoxId(int moneyBoxId, int carId) {
		// TODO Auto-generated method stub
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("moneybox_transaction_details.getAllByCarIdAndMoneyBoxId").setInteger("moneyBoxId", moneyBoxId).setInteger("carId",carId);

		 @SuppressWarnings("unchecked")
		 List<moneybox_transaction_details> results=query.list();
		  return results;
	}

	@Override
	public List<moneybox_transaction_details> getAllBy_wire_transfer_numberAndMoneyBoxId(int moneyBoxId,
			String wire_transfer_number) {
		// TODO Auto-generated method stub
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("moneybox_transaction_details.getAllBy_wire_transfer_numberAndMoneyBoxId").setInteger("moneyBoxId", moneyBoxId).setString("wire_transfer_number",wire_transfer_number);

		 @SuppressWarnings("unchecked")
		 List<moneybox_transaction_details> results=query.list();
		  return results;
	}

	
	


}
