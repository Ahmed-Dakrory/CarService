/**
 * 
 */
package main.com.carService.product;

import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface productRepository {

	public List<product> getAll();

	public List<product> getAllByState(int state);

	public List<product> getAllForNormalUser(int normalUserId);
	public List<product> getAllByStateForNormalUser(int normalUserId,int state);
	
	public List<product> getAllBytypeOfOrderForNormalUser(int normalUserId,int typeOfOrder);
	public List<product> getAllBytypeOfOrderAndStateForNormalUser(int normalUserId,int state,int typeOfOrder);
	
	
	public List<product> getAllForMainUser(int userId);
	public List<product> getAllByStateForMainUser(int userId,int state);
	
	public product addproduct(product data) throws Exception;
	public product getById(int id);
	public boolean delete(product data)throws Exception;
}
