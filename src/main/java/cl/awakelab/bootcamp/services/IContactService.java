package cl.awakelab.bootcamp.services;

import java.util.List;
import java.util.Optional;

import cl.awakelab.bootcamp.entitys.Contact;

public interface IContactService {

	public List<Contact> listAllContact();
	
	public Contact saveContact(Contact contact);
	
	public Optional<Contact> getContactById(Long id);
	
	public void deleteContactById(Long id);
}
