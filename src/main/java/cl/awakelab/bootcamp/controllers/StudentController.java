package cl.awakelab.bootcamp.controllers;


import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cl.awakelab.bootcamp.entitys.Student;
import cl.awakelab.bootcamp.services.IStudentService;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

@Controller
public class StudentController {
//Este controlador tiene el metodo que retorna la vista de students (en resources/templates/students) se solicita sin extencion
	//Al mismo tiempo nos mapea la direccion donde se cargaran esos resultados
	
	//Inyectamos el servicio
	private final IStudentService service;
	//Inicializamos la inyeccion
	@Autowired
	public StudentController(IStudentService service) {
		this.service = service;
	}
	
	
	@GetMapping({"/", "/home"})
	public String home(Model model) {
		
		
		 List<Student> studentsFull = service.listAllStudents();

		    // Ordena la lista de estudiantes por ID de forma descendente
		    studentsFull.sort(Comparator.comparingLong(Student::getId).reversed());

		    // Toma los primeros dos estudiantes de la lista (los dos con IDs más altos)
		    List<Student> students = studentsFull.stream()
		            .limit(1)
		            .collect(Collectors.toList());

		    model.addAttribute("students", students);
		    System.out.println(students);    // Se retorna la vista
		
		return "home/index";
	}
	
	
	
	//LISTAR STUDENTS
	//localhost:8080/students
	@GetMapping({"/students"})
	public String getAllStudents(Model model) {
		List<Student> students = service.listAllStudents();
		//model.addAttribute("students", service.listAllStudents());
	    String defaultPhotoName = "defaultStudentPhoto.png";
		
		 for (Student student : students) {
		        String photoUrl = student.getPhotoUrl();
		        if (photoUrl == null || photoUrl.isEmpty()) {
		            student.setPhotoUrl("images/studentPhotos/"+defaultPhotoName);
		        }
		    }
		model.addAttribute("students", students);
		System.out.println(students);		//Se retorna la vista		
		return "students-views/students/students";
	}
	
	
	
	//NUEVO STUDENT
	@GetMapping("/students/new-student")
	public String createStudentForm(Model model) {
		//Creamos instancia del entity student
		Student student = new Student();
		model.addAttribute("student", student);
		return "students-views/create-student/create-student";
	}
	
	//GUARDAR STUDENT
	@PostMapping("/students")
	public String saveStudent(
	    @Valid @ModelAttribute("student") Student student,
	    BindingResult bindingResult,
	    @RequestParam("studentPhoto") MultipartFile studentPhoto,
	    RedirectAttributes redirectAttributes
	) {
	    if (bindingResult.hasErrors()) {
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            System.out.println("Campo: " + error.getField());
	            System.out.println("Mensaje de error: " + error.getDefaultMessage());
	        }
	        return "students-views/create-student/create-student";
	    }

	    if (!studentPhoto.isEmpty()) {
	        try {
	        	  String originalFileName = studentPhoto.getOriginalFilename();
	              String fileExtension = StringUtils.getFilenameExtension(originalFileName); // Obtener la extensión del archivo
	              String sanitizedFileName = UUID.randomUUID().toString() + "." + fileExtension; // Generar un nombre único para el archivo
	              String photoFilePath = new ClassPathResource("static/images/studentPhotos/").getFile().getAbsolutePath() + "/" + sanitizedFileName;

	              studentPhoto.transferTo(new File(photoFilePath));
	              student.setPhotoUrl("images/studentPhotos/" + sanitizedFileName);

	              service.saveStudent(student);
	            System.out.println("Estudiante guardado con éxito.");
	        } catch (IOException e) {
	            e.printStackTrace();
	            redirectAttributes.addFlashAttribute("error", "Error al subir la foto.");
	            return "redirect:/students/new-student";
	        }
	    }

	    return "redirect:/students";
	}
	
	//EDITAR STUDENT
	@GetMapping("/students/edit/{id}")
	public String showFormEditStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
		Optional<Student> optionalStudent = service.getStudentById(id);
		
		if(!optionalStudent.isPresent()) {
			throw new StudentNotFoundException("No se encontró un estudiante con ese id " + id);
		}
		Student studentExists = optionalStudent.get();
		model.addAttribute("student", studentExists);
		return "students-views/edit-student/edit-student";
	}
	//ACUTALIZAR STUDENT
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
		Optional<Student> optionalStudent = service.getStudentById(id);
		
		if(!optionalStudent.isPresent()) {
			throw new StudentNotFoundException("No se encontró un estudiante con ese id " + id);
		}
		Student studentExists = optionalStudent.get();
		studentExists.setId(id);
		studentExists.setName(student.getName());
		studentExists.setLastName(student.getLastName());
		studentExists.setEmail(student.getEmail());
		
		service.updateStudent(studentExists);
		return "redirect:/students";
	}
	//ELIMINAR STUDENT
	@GetMapping("/students/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		service.deleteStudentById(id);
		return "redirect:/students";
	}
	
	@GetMapping({"/login"})
	public String login() {
		
	return "login/login";
}

}
class StudentNotFoundException extends RuntimeException {
	public StudentNotFoundException(String message) {
		super(message);
	}
}