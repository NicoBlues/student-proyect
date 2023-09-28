package cl.awakelab.bootcamp.services;

import java.util.List;
import java.util.Optional;

import cl.awakelab.bootcamp.entitys.Student;
public interface IStudentService {
	//Interfaz abstracta metodos publicos
	//Metodos CRUD
	//Listar a todos (Crea una lista con el objeto student)
	public List<Student> listAllStudents();
	//Guardar Student (guarda los datos de student en el objeto student)
	public Student saveStudent(Student student);
	//Buscar Student (busca por id en el objeto student)
	public Optional<Student> getStudentById(Long id);
	//Actualizar student (actualiza por id)
	public Student updateStudent(Student student);
	//Eliminar Student (Elimina por id)
	public void deleteStudentById(Long id);
}
