/**
 * 
 */
package main.com.carService.carLandingImage;

import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface IcarlandingimageAppService {

	public List<carlandingimage> getAll();
	public List<carlandingimage> getAllByCarIdAndType(int idCar,int type);
	public carlandingimage addcarlandingimage(carlandingimage data);
	public carlandingimage getByUrl(String url);
	public carlandingimage getById(int id);
	public boolean delete(carlandingimage data)throws Exception;
}
