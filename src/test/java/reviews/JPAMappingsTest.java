package reviews;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

import java.util.Collection;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CategoryRespository categoryRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category"));
		Long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Category result = categoryRepo.findById(categoryId).get();
		assertThat(result.getName(), is("category"));
	}
	
	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category"));
		Long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(categoryId, is (greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadReview() {
		Review review = reviewRepo.save(new Review("rev1","desc1", "image"));
		Long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Review result = reviewRepo.findById(reviewId).get();
		assertThat(review.getName(), is("rev1"));
	}
	
	@Test
	public void shouldEstablishReviewToCategoryRelationships() {
		Category category1 = categoryRepo.save(new Category("category1"));
		Category category2 = categoryRepo.save(new Category("category2"));
		
		Review review = new Review("name", "description", "image", category1, category2);
		review = reviewRepo.save(review);
		Long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Review result = reviewRepo.findById(reviewId).get();
		
		assertThat(review.getCategories(), containsInAnyOrder(category1, category2));
	}
	
	@Test
	public void shouldFindReviewsForCategory() {
		Category category1 = categoryRepo.save(new Category("category1"));
		
		Review review1 = reviewRepo.save(new Review("review1", "Description", "image", category1));
		Review review2 = reviewRepo.save(new Review("review2", "Description", "image", category1));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategoriesContains(category1);
		
		assertThat(reviewsForCategory, containsInAnyOrder(review1, review2));
	}
	
	@Test
	public void shouldFindReviewsForCategoryId() {
		Category category1 = categoryRepo.save(new Category ("category1"));
		Long categoryId = category1.getId();
		
		Review review1 = reviewRepo.save(new Review("review1", "Description", "image", category1));
		Review review2 = reviewRepo.save(new Review("review2", "Description", "image", category1));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategoriesId(categoryId);
		
		assertThat(reviewsForCategory, containsInAnyOrder(review1, review2));
	}
	
	@Test
	public void shouldSaveAndLoadComment() {
		Comment comment = commentRepo.save(new Comment("comment"));
		Long commentId = comment.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Comment result = commentRepo.findById(commentId).get();
		assertThat(result.getName(), is("comment"));
	}
	
	

}
