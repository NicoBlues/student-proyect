package cl.awakelab.bootcamp.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "Name is required")
	@Size(min = 2, message="Name should have at least 2 characters.")
	@Column(name="name", nullable=false, length=50)
	private String name;
	
	@NotEmpty(message="Email is required")
	@Email(message = "Invalid email format")
	@Column(name="email", nullable=false, length=50, unique=false)
	private String email;
	
	@NotEmpty(message="message is required")
	@Size(min=10, message="min 10 characters")
	@Column(name="message", nullable=false, length=255)
	private String message;
	
	public Contact() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
