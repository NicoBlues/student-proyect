package cl.awakelab.bootcamp.repositories;

import org.springframework.data.repository.CrudRepository;

import cl.awakelab.bootcamp.entitys.Student;

//Interface que hereda de otra interface
public interface IStudentRepository extends CrudRepository<Student, Long> {
//Esta interface hereda los metodos Crud desde crudrepo...
	
}