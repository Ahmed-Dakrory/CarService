/**
 * 
 */
package main.com.carService.carLanding;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("carLandingFacadeImpl")
public class carLandingAppServiceImpl implements IcarLandingAppService{

	@Autowired
	carLandingRepository carLandingDataRepository;
	
	
	@Override
	public List<carLanding> getAll() {
		try{
			List<carLanding> course=carLandingDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public carLanding addcarLanding(carLanding data) {
		try{
			carLanding data2=carLandingDataRepository.addcarLanding(data);
			return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(carLanding data)throws Exception {
		// TODO Auto-generated method stub
		try{
			carLandingDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public carLanding getById(int id) {
		// TODO Auto-generated method stub
		try{
			carLanding so=carLandingDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}






	@Override
	public carLanding getByVin(String vinId) {
		// TODO Auto-generated method stub
				try{
					carLanding so=carLandingDataRepository.getByVin(vinId);
					
					return so;
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						return null;
					}
	}



	@Override
	public List<carLanding> getAllForLanding() {
		try{
			List<carLanding> course=carLandingDataRepository.getAllForLanding();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<carLanding> getAllForCategories(int categories) {
		try{
			List<carLanding> course=carLandingDataRepository.getAllForCategories(categories);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<carLanding> getAllGroupsOfMake() {
		try{
			List<carLanding> course=carLandingDataRepository.getAllGroupsOfMake();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<carLanding> getAllForSearch(String yearStart, String yearEnd, String make, Integer category) {
		try{
			List<carLanding> course=carLandingDataRepository.getAllForSearch( yearStart,  yearEnd,  make,  category);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}
	
	
}
		
		

	
		
	


