/**
 * 
 */
package main.com.carService.carLandingImage;

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
public class carlandingimageRepositoryImpl implements carlandingimageRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public carlandingimage addcarlandingimage(carlandingimage data) {
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
	public List<carlandingimage> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carlandingimage.getAll");

				 @SuppressWarnings("unchecked")
				List<carlandingimage> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(carlandingimage data)throws Exception {
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
	public carlandingimage getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carlandingimage.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<carlandingimage> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}



	@Override
	public List<carlandingimage> getAllByCarIdAndType(int idCar, int type) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carlandingimage.getAllByCarIdAndType")
				 .setInteger("id",idCar)
				 .setInteger("type", type);

		 @SuppressWarnings("unchecked")
		List<carlandingimage> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public carlandingimage getByUrl(String url) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("carlandingimage.getByUrl").setString("url",url);

				 @SuppressWarnings("unchecked")
				List<carlandingimage> results=query.list();
				 if(results.size()!=0){
					 return results.get(0);
				 }else{
					 return null;
				 }
	}



	
	


}
