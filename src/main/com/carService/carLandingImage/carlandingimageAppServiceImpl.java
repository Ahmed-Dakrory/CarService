/**
 * 
 */
package main.com.carService.carLandingImage;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("carlandingimageFacadeImpl")
public class carlandingimageAppServiceImpl implements IcarlandingimageAppService{

	@Autowired
	carlandingimageRepository carlandingimageDataRepository;
	
	
	@Override
	public List<carlandingimage> getAll() {
		try{
			List<carlandingimage> course=carlandingimageDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public carlandingimage addcarlandingimage(carlandingimage data) {
		try{
			carlandingimage existData = carlandingimageDataRepository.getByUrl(data.getUrl());
			if(existData==null) {
				carlandingimage data2=carlandingimageDataRepository.addcarlandingimage(data);
				return data2;
			}else {
				existData.setDeleted(data.isDeleted());
				carlandingimageDataRepository.addcarlandingimage(existData);
				return existData;
			}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}


	@Override
	public boolean delete(carlandingimage data)throws Exception {
		// TODO Auto-generated method stub
		try{
			carlandingimageDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public carlandingimage getById(int id) {
		// TODO Auto-generated method stub
		try{
			carlandingimage so=carlandingimageDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	

	@Override
	public List<carlandingimage> getAllByCarIdAndType(int idCar, int type) {
		try{
			List<carlandingimage> course=carlandingimageDataRepository.getAllByCarIdAndType(idCar, type);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public carlandingimage getByUrl(String url) {
		try{
			carlandingimage so=carlandingimageDataRepository.getByUrl(url);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}




	
	
	
}
		
		

	
		
	


