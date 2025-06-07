package projeto_integrado.controllers.Entidades.Repositories;

import org.springframework.data.repository.CrudRepository;

import projeto_integrado.controllers.Entidades.User;

public interface RepositorioUser extends CrudRepository<User, Integer>{

	 User findByEmail(String email);
	
}
