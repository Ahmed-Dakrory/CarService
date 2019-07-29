/**
 * 
 */
package main.com.carService.loginNeeds;

import java.util.List;

/**
 * @author Dakrory
 *
 */
public interface userRepository {

	public List<user> getAll();
	public List<user> getAllWithRole(int role);
	public user adduser(user data);
	public user getById(int id);
	public user getByEmail(String email);
	public user getByEmailAndRole(String email,int role);
	public user getByEmailAndPassword(String email,String password);
	public user getByEmailAndPasswordNotActivated(String email,String password);
	public boolean delete(user data);
}
