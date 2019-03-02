package reviews;

import java.util.Optional;

import javax.annotation.Resource;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.runner.Runwith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Runwith(SpringJUnit4ClassRunner.class)
@DataJpaTest

public class JPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CategoryRespository categoryRepo;
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		result.get();
		assertThat(category.getName(), is("category"));
		
	}

}
