/**
 * 
 */
package main.com.carService.biding;

import java.util.List;

/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface IbidingAppService {

	public List<biding> getAll();
	public List<biding> getAllByuserId(int idUser);
	public biding getByCarIdAnduserId(int idCar,int idUser);
	public biding getByCarIdLessThanFullAmount(int idCar,float amount);
	public biding addbiding(biding data);
	public List<biding> getAllMaxCarBidings();
	public biding getById(int id);
	public boolean delete(biding data)throws Exception;
}
