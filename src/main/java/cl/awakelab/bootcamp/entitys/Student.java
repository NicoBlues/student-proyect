package cl.awakelab.bootcamp.entitys;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//ESTO ES UN BEAN
//El bean tiene los metodos get y set, constructor vacio, todo en private
@Entity
@Table(name="students")
public class Student {
//Estas etiquetas son para convertir la clase en una tabla en nuestra bbdd 	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "Name is required")
	@Size(min = 2, message="Name should have at least 2 characters")
	@Column(name="name", nullable=false, length=50)
	private String name;
	
	@NotEmpty(message = "Last name is required")
	@Size(min = 2, message="Last name should have at least 2 characters")
	@Column(name="last_name", nullable=false, length=50)
	private String lastName;
	
	@NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
	@Column(name="email", nullable=false, length=50, unique=true)
	private String email;
	
	@Column(name="photo_url", nullable=true, length=255)
	private String photoUrl;
	
	@Transient
	private MultipartFile studentPhoto;
	
	public Student() {
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public MultipartFile getStudentPhoto() {
		return studentPhoto;
	}

	public void setStudentPhoto(MultipartFile studentPhoto) {
		this.studentPhoto = studentPhoto;
	}
}
