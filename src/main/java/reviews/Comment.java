package reviews;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
