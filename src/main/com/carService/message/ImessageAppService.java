/**
 * 
 */
package main.com.carService.message;

import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface ImessageAppService {

	public List<message> getAll();
	public List<message> getAllByfrom_userId(int from_userId);
	public List<message> getAllByto_userId(int to_userId);
	public message addmessage(message data);
	public message getById(int id);
	public boolean delete(message data)throws Exception;
}
