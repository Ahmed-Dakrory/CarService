/**
 * 
 */
package main.com.carService.productImage;

import java.util.List;


/**
 * 
 * @author Ahmed.Dakrory
 *
 */
public interface productimageRepository {

	public List<productimage> getAll();
	public List<productimage> getAllByproductIdAndType(int idproduct,int type);
	public productimage addproductimage(productimage data);
	public productimage getByUrl(String url);
	public productimage getById(int id);
	public boolean delete(productimage data)throws Exception;
}
