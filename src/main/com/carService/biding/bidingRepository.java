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
public interface bidingRepository {

	public List<biding> getAll();
	public List<biding> getAllByuserId(int idUser);
	public List<biding> getAllByCarId(int idcar);
	public biding getByCarIdAnduserIdAndType(int idCar,int idUser,int type);
	public biding getByCarIdandMaxAmountAndType(int idCar,int type);
	public biding getByCarIdLessThanFullAmountAndType(int idCar,float amount,int type);
	public biding addbiding(biding data);
	public List<biding> getAllMaxCarBidingsAndType(int type);
	public biding getById(int id);
	public boolean delete(biding data)throws Exception;
}
