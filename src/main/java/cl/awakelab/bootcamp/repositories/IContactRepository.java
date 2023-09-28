package cl.awakelab.bootcamp.repositories;

import org.springframework.data.repository.CrudRepository;

import cl.awakelab.bootcamp.entitys.Contact;

public interface IContactRepository extends CrudRepository<Contact, Long> {

}
