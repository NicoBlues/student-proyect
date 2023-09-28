package cl.awakelab.bootcamp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.awakelab.bootcamp.entitys.Contact;

import cl.awakelab.bootcamp.services.IContactService;

@Controller
public class ContactController {

	private final IContactService service;
	
	@Autowired
	public ContactController(IContactService service) {
		this.service = service;
	}
	
	
	 @GetMapping("/contact/contact-form")
	    public String createContactForm(Model model) {
	        Contact contact = new Contact();
	        model.addAttribute("contact", contact);
	        return "contact/contact-form";
	    }
	
	 @PostMapping("/contact")
	    public String saveContact(
	        @ModelAttribute("contact") @Valid Contact contact,
	        BindingResult bindingResult,
	        RedirectAttributes redirectAttributes) {

	        if (bindingResult.hasErrors()) {
	            return "contact/contact-form";
	        }

	        // Guardar el contacto en la base de datos utilizando el servicio
	        service.saveContact(contact);

	        // Redirigir a una página de confirmación o a donde desees
	        return "redirect:/home";
	    }
		
	}
