package reviews;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Collection<Review> reviews;
	
	private String comment;

	public Comment(String comment) {
		this.comment = comment; 
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return comment;
	}

}
