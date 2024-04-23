package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Contact;
import org.example.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(int id) throws Exception {
        return contactRepository.findById(id).orElseThrow(Exception::new);
    }

    public Contact updateContact(int id, Contact contactDetails) throws Exception {
        Contact contact = contactRepository.findById(id).orElseThrow(Exception::new);

        contact.setCssClass(contactDetails.getCssClass());
        contact.setName(contactDetails.getName());

        return contactRepository.save(contact);
    }

    public void deleteContact(int id) throws Exception {
        Contact contact = contactRepository.findById(id).orElseThrow(Exception::new);

        contactRepository.delete(contact);
    }
}
