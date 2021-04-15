/**
 * 
 */
package main.com.carService.productImage;

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
public class productimageRepositoryImpl implements productimageRepository{

	@Autowired
	private SessionFactory sessionFactory;
	Session session; 
	

	

	@Override
	public productimage addproductimage(productimage data) {
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
	public List<productimage> getAll() {
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("productimage.getAll");

				 @SuppressWarnings("unchecked")
				List<productimage> results=query.list();
				 if(results.size()!=0){
					 return results;
				 }else{
					 return null;
				 }
	}

	
	@Override
	public boolean delete(productimage data)throws Exception {
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
	public productimage getById(int id) {
		// TODO Auto-generated method stub
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("productimage.getById").setInteger("id",id);

		 @SuppressWarnings("unchecked")
		List<productimage> results=query.list();
		 if(results.size()!=0){
			 return results.get(0);
		 }else{
			 return null;
		 }
	}



	@Override
	public List<productimage> getAllByproductIdAndType(int idproduct, int type) {
		 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("productimage.getAllByproductIdAndType")
				 .setInteger("id",idproduct)
				 .setInteger("type", type);

		 @SuppressWarnings("unchecked")
		List<productimage> results=query.list();
		 if(results.size()!=0){
			 return results;
		 }else{
			 return null;
		 }
	}

	@Override
	public productimage getByUrl(String url) {
		// TODO Auto-generated method stub
				 Query query 	=sessionFactory.getCurrentSession().getNamedQuery("productimage.getByUrl").setString("url",url);

				 @SuppressWarnings("unchecked")
				List<productimage> results=query.list();
				 if(results.size()!=0){
					 return results.get(0);
				 }else{
					 return null;
				 }
	}



	
	


}
