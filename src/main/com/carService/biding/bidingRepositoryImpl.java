/**
 * 
 */
package main.com.carService.biding;

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
public class bidingRepositoryImpl implements bidingRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public biding addbiding(biding data) {
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
	public List<biding> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("biding.getAll");

				 @SuppressWarnings("unchecked")
				List<biding> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(biding data)throws Exception {
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
	public biding getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("biding.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<biding> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}



	@Override
	public biding getByCarIdAnduserIdAndType(int idCar, int idUser,int type) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("biding.getByCarIdAnduserIdAndType")
				 .setInteger("idcar",idCar)
				 .setInteger("idUser", idUser)
				 .setInteger("type",type);

		 @SuppressWarnings("unchecked")
		List<biding> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}

	@Override
	public List<biding> getAllMaxCarBidingsAndType(int type) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("biding.getAllMaxCarBidingsAndType")
						 .setInteger("type",type);

				 @SuppressWarnings("unchecked")
				List<biding> results=query.list();
					 return results;
				
	}

	@Override
	public biding getByCarIdLessThanFullAmountAndType(int idCar, float amount,int type) {
		Query query 	=sessionFactory.getCurrentSession().getNamedQuery("biding.getByCarIdLessThanFullAmountAndType")
				 .setInteger("idcar",idCar)
				 .setFloat("fullAmount", amount)
				 .setInteger("type",type);

		 @SuppressWarnings("unchecked")
		List<biding> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}

	@Override
	public List<biding> getAllByuserId(int idUser) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("biding.getAllByuserId").setInteger("idUser",idUser);

				 @SuppressWarnings("unchecked")
				List<biding> results=query.list();
				
					 return results;
				
	}

	@Override
	public biding getByCarIdandMaxAmountAndType(int idCar,int type) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("biding.getByCarIdandMaxAmountAndType")
				 .setInteger("idcar",idCar)
				 .setInteger("type",type);

		 @SuppressWarnings("unchecked")
		List<biding> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}



	
	


}
