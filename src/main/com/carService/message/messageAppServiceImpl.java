/**
 * 
 */
package main.com.carService.message;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("messageFacadeImpl")
public class messageAppServiceImpl implements ImessageAppService{

	@Autowired
	messageRepository messageDataRepository;
	
	
	@Override
	public List<message> getAll() {
		try{
			List<message> course=messageDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public message addmessage(message data) {
		try{
				message data2=messageDataRepository.addmessage(data);
				return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(message data)throws Exception {
		// TODO Auto-generated method stub
		try{
			messageDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public message getById(int id) {
		// TODO Auto-generated method stub
		try{
			message so=messageDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	





	@Override
	public List<message> getAllByfrom_userId(int from_userId) {
		try{
			List<message> course=messageDataRepository.getAllByfrom_userId(from_userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}




	@Override
	public List<message> getAllByto_userId(int to_userId) {
		try{
			List<message> course=messageDataRepository.getAllByto_userId(to_userId);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	
	
	
}
		
		

	
		
	


