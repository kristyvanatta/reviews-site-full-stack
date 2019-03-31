package reviews;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {
	
	
	@InjectMocks
	private ReviewController underTest;
	
	@Mock
	private Review review;
	Long reviewId;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private ReviewRepository reviewRepo;
		
	@Mock
	private CategoryRespository categoryRepo;
	
	@Mock
	private CommentRepository commentRepo;
	
	@Mock
	private Comment comment;
	Long commentId;
	 
	@Mock
	private Category category;
	
	@Mock
	private Category anotherCategory;
	
	@Mock
	private Model model;

	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		
		underTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("reviews", review);
	}
	
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.findAllreviews(model);
		verify(model).addAttribute("reviews", allReviews);
	}
	
	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException { 
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		
		underTest.findOneCategory(arbitraryCategoryId, model);
		
		verify(model).addAttribute("categories", category);
		
	}
	
	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		underTest.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}
	
	@Test
	public void shouldAddAdditionalReviewsToModel() {
		String categoryName = "category name";
		Category newCategory = categoryRepo.findByName(categoryName);
		String reviewName = "new review";
		String reviewDescription = "new review description";
		String reviewImage = "new review image";
		underTest.addReview(reviewName, reviewDescription, reviewImage, categoryName);
		Review newReview = new Review(reviewName, reviewDescription, reviewImage, newCategory);
		when(reviewRepo.save(newReview)).thenReturn(newReview);
	}
	
	@Test
	public void shouldRemoveReviewFromModelByName() {
		String reviewName = review.getName();
		when(reviewRepo.findByName(reviewName)).thenReturn(review);
		underTest.deleteReviewByName(reviewName);
		verify(reviewRepo).delete(review);
	}
	
	@Test
	public void shouldRemoveReviewFromMobdelById() {
		underTest.deleteReviewById(reviewId);
		verify(reviewRepo).deleteById(reviewId);
	}
	
	@Test
	public void shouldAddSingleCommentToModel() throws CommentNotFoundException { 
		long arbitraryCommentId = 1;
		when(commentRepo.findById(arbitraryCommentId)).thenReturn(Optional.of(comment));
		
		underTest.findOneComment(arbitraryCommentId, model);
		
		verify(model).addAttribute("comments", comment);
		
	}
	
	
	
	
		
	}
	

	
	
	
	
	
	
	
	
	
	
	

