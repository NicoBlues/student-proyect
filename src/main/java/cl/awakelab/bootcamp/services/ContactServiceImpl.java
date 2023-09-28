package cl.awakelab.bootcamp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.awakelab.bootcamp.entitys.Contact;
import cl.awakelab.bootcamp.repositories.IContactRepository;

@Service
public class ContactServiceImpl implements IContactService {

	private final IContactRepository repository;
	
	@Autowired
	public ContactServiceImpl(IContactRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Contact> listAllContact() {
		return (List<Contact>) repository.findAll();
	}

	@Override
	public Contact saveContact(Contact contact) {
		return repository.save(contact);
	}

	@Override
	public Optional<Contact> getContactById(Long id) {
		return repository.findById(id);
	}

	@Override
	public void deleteContactById(Long id) {
		repository.deleteById(id);
		
	}

}
