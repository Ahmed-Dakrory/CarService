/**
 * 
 */
package main.com.carService.productImage;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dakrory
 *
 */
@Service("productimageFacadeImpl")
public class productimageAppServiceImpl implements IproductimageAppService{

	@Autowired
	productimageRepository productimageDataRepository;
	
	
	@Override
	public List<productimage> getAll() {
		try{
			List<productimage> course=productimageDataRepository.getAll();
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}

	

	@Override
	public productimage addproductimage(productimage data) {
		try{
			productimage existData = productimageDataRepository.getByUrl(data.getUrl());
			if(existData==null) {
				productimage data2=productimageDataRepository.addproductimage(data);
				return data2;
			}else {
				existData.setDeleted(data.isDeleted());
				productimageDataRepository.addproductimage(existData);
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
	public boolean delete(productimage data)throws Exception {
		// TODO Auto-generated method stub
		try{
			productimageDataRepository.delete(data);
			return true;
			}
			catch(Exception ex)
			{
				throw ex;
			}
	}

	@Override
	public productimage getById(int id) {
		// TODO Auto-generated method stub
		try{
			productimage so=productimageDataRepository.getById(id);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	

	@Override
	public List<productimage> getAllByproductIdAndType(int idproduct, int type) {
		try{
			List<productimage> course=productimageDataRepository.getAllByproductIdAndType(idproduct, type);
			
			return course;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}



	@Override
	public productimage getByUrl(String url) {
		try{
			productimage so=productimageDataRepository.getByUrl(url);
			
			return so;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
	}




	
	
	
}
		
		

	
		
	


