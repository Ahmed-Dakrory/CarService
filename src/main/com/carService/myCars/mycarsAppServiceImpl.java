/**
 * 
 */
package main.com.carService.myCars;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("mycarsFacadeImpl")
public class mycarsAppServiceImpl implements ImycarsAppService{

	@Autowired
	mycarsRepository mycarsDataRepository;
	
	
	@Override
	public List<mycars> getAll() {
		try{
			List<mycars> course=mycarsDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public mycars addmycars(mycars data) {
		try{
			mycars data2=mycarsDataRepository.addmycars(data);
			return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(mycars data)throws Exception {
		// TODO Auto-generated method stub
		try{
			mycarsDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public mycars getById(int id) {
		// TODO Auto-generated method stub
		try{
			mycars so=mycarsDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}




	



	@Override
	public List<mycars> getAllByUserIdAndType(int userId,int type) {
		try{
			List<mycars> course=mycarsDataRepository.getAllByUserIdAndType(userId,type);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public mycars getByUserIdAndCarIdAndType(int id, int type, int carId) {
		// TODO Auto-generated method stub
				try{
					mycars so=mycarsDataRepository.getByUserIdAndCarIdAndType( id,  type,  carId);
					
					return so;
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						return null;
					}
	}
	
	
}
		
		

	
		
	


