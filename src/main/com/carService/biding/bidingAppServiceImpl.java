/**
 * 
 */
package main.com.carService.biding;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("bidingFacadeImpl")
public class bidingAppServiceImpl implements IbidingAppService{

	@Autowired
	bidingRepository bidingDataRepository;
	
	
	@Override
	public List<biding> getAll() {
		try{
			List<biding> course=bidingDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public biding addbiding(biding data) {
		try{
				biding data2=bidingDataRepository.addbiding(data);
				return data2;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(biding data)throws Exception {
		// TODO Auto-generated method stub
		try{
			bidingDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public biding getById(int id) {
		// TODO Auto-generated method stub
		try{
			biding so=bidingDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	

	@Override
	public biding getByCarIdAnduserId(int idCar, int idUser) {
		try{
			biding course=bidingDataRepository.getByCarIdAnduserId(idCar, idUser);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<biding> getAllMaxCarBidings() {
		try{
			List<biding> so=bidingDataRepository.getAllMaxCarBidings();
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public biding getByCarIdLessThanFullAmount(int idCar, float amount) {
		try{
			biding course=bidingDataRepository.getByCarIdLessThanFullAmount(idCar, amount);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public List<biding> getAllByuserId(int idUser) {
		try{
			List<biding> course=bidingDataRepository.getAllByuserId(idUser);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public biding getByCarIdandMaxAmount(int idCar) {
		// TODO Auto-generated method stub
				try{
					biding so=bidingDataRepository.getByCarIdandMaxAmount(idCar);
					
					return so;
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						return null;
					}
	}




	
	
	
}
		
		

	
		
	


