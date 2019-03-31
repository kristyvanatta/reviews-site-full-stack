package reviews;


import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
	
	@Resource
	ReviewRepository reviewRepo;
	
	@Resource
	CategoryRespository categoryRepo;
	
	@Resource
	CommentRepository commentRepo;
	
	
	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value="id")long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
	
		if(review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return "review";
		}
		throw new ReviewNotFoundException();
			
	}

	@RequestMapping("/reviews")
	public String findAllreviews(Model model) {
		model.addAttribute("reviews",reviewRepo.findAll());
		return ("reviews");
		
	}
	@RequestMapping("/category")
	public String findOneCategory(Long id, Model model) throws CategoryNotFoundException {
		Optional<Category> category =categoryRepo.findById(id);
		
		if(category.isPresent()) {
			model.addAttribute("categories", category.get());
			return "category";
		}
		throw new CategoryNotFoundException();
		
	}
	@RequestMapping("/categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return ("categories");
		
	}
	
	@RequestMapping("/add-review")
	public String addReview(String reviewName, String reviewDescription, String reviewImage, String categoryName) {
		Category category = categoryRepo.findByName(categoryName);
		Review newReview = reviewRepo.findByName(reviewName);
		
		if(newReview==null) {
			newReview = new Review(reviewName, reviewDescription, reviewImage, category);
			reviewRepo.save(newReview);
		}
		return "redirect:/reviews"; 
		
	}

	@RequestMapping("/delete-review")
	public String deleteReviewByName(String reviewName) {
		
		if(reviewRepo.findByName(reviewName) != null) {
			Review deletedReview = reviewRepo.findByName(reviewName);
			reviewRepo.delete(deletedReview);
		}
		
		return "redirect:/reviews";
		
	}
	
	@RequestMapping("/del-review")
	public String deleteReviewById(Long reviewId) {
		
		reviewRepo.deleteById(reviewId);
		
		return "redirect:/reviews";
		
	}
	
	@RequestMapping("/find-by-category")
	public String findReviewsByCategory(String categoryName, Model model) {
		Category category = categoryRepo.findByName(categoryName);
		model.addAttribute("reviews", reviewRepo.findByCategoriesContains(category));
		
		return "/category";
	}
	
	@RequestMapping("/comment")
	public String findOneComment(@RequestParam(value="id")long id, Model model) throws CommentNotFoundException {
		Optional<Comment> comment = commentRepo.findById(id);
		
		if(comment.isPresent()) {
			model.addAttribute("comments", comment.get());
			return "comment";
		}
		throw new CommentNotFoundException();
			
	}
	
	@RequestMapping("/add-comment")
	public String addComment(Long reviewId, String comment) {
		
		Optional<Review> review = reviewRepo.findById(reviewId);
		if (review.isPresent()) {
		Comment comment1 = new Comment(comment);
		review.get().setComments(comment1);
		}
		
		return "redirect:/reviews";
		
	}

}


