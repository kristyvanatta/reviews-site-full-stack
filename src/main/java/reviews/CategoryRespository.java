package reviews;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends CrudRepository<Category, Long> {
	
	

}
