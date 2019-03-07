package reviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CategoryRespository categoryRepo;

	@Override
	public void run(String... args) throws Exception {
		
		Category travel = new Category("travel");
		travel = categoryRepo.save(travel);
		Category movies = new Category("movies");
		movies = categoryRepo.save(movies);
		Category food = new Category("food");
		food = categoryRepo.save(food);	
		
		Review furious7 = new Review("Furios 7", "As far as action films go you could not get more action than the Furious franchise! However not many action films made you feel the way that only Furious 7 can. You got all the action packed furry the franchise has to offer and you also get the a sadness not many movies can offer. The filmmakers did a great job at honoring a fallen cast mate that is unmatched by any other film.", "/images/movie.jpg", movies);
		furious7 = reviewRepo.save(furious7);
		Review pizza = new Review("Pizza", "Pizza is the best comfort food. It is completely versatile. People fight over Chicago Style or New York Style, but I say yes please!", "/images/food.jpg", food);
		pizza = reviewRepo.save(pizza);
		Review beach = new Review("Beach", "Who can resist a beach vacation? The beach offers so many different activities, from relaxation to action packed fun on and off the water. My favorite thing to do at the beach is to with my feet in the water and watch the sun rise or set and just enjoy the wonder that is the beach.", "/images/travel.jpg", travel);
		beach = reviewRepo.save(beach);
	}

}
