/**
 * 
 */
package main.com.carService.product;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("productFacadeImpl")
public class productAppServiceImpl implements IproductAppService{

	@Autowired
	productRepository productDataRepository;
	
	
	@Override
	public List<product> getAll() {
		try{
			List<product> course=productDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public product addproduct(product data)throws Exception {
		try{
			product data2=productDataRepository.addproduct(data);
			return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				throw ex;
			}
	}


	@Override
	public boolean delete(product data)throws Exception {
		// TODO Auto-generated method stub
		try{
			productDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public product getById(int id) {
		// TODO Auto-generated method stub
		try{
			product so=productDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	


	@Override
	public List<product> getAllForNormalUser(int normalUserId) {
		try{
			List<product> course=productDataRepository.getAllForNormalUser(normalUserId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<product> getAllByStateForNormalUser(int normalUserId, int state) {
		try{
			List<product> course=productDataRepository.getAllByStateForNormalUser(normalUserId,state);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<product> getAllForMainUser(int userId) {
		try{
			List<product> course=productDataRepository.getAllForMainUser(userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<product> getAllByStateForMainUser(int userId, int state) {
		try{
			List<product> course=productDataRepository.getAllByStateForMainUser(userId,state);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<product> getAllBytypeOfOrderForNormalUser(int normalUserId, int typeOfOrder) {
		try{
			List<product> course=productDataRepository.getAllBytypeOfOrderForNormalUser(normalUserId,typeOfOrder);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<product> getAllBytypeOfOrderAndStateForNormalUser(int normalUserId, int state, int typeOfOrder) {
		try{
			List<product> course=productDataRepository.getAllBytypeOfOrderAndStateForNormalUser(normalUserId,state,typeOfOrder);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<product> getAllByState(int state) {
		try{
			List<product> course=productDataRepository.getAllByState(state);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public long getAllCount() {
		// TODO Auto-generated method stub
		return productDataRepository.getAllCount();
		
	}



	@Override
	public List<product> getAllWithPagination(int start, int number, String searchValue) {
		try{
			List<product> course=productDataRepository.getAllWithPagination(start,number,searchValue);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public product getNextRecord(int id) {
		// TODO Auto-generated method stub
		try{
			product so=productDataRepository.getNextRecord(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public product getPreviousRecord(int id) {
		// TODO Auto-generated method stub
				try{
					product so=productDataRepository.getPreviousRecord(id);
					
					return so;
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						return null;
					}
	}



		
	
}
		
		

	
		
	


