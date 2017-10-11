package com.ecc.hibernate_xml.service;

import java.util.List;

import com.ecc.hibernate_xml.dao.DaoException;
import com.ecc.hibernate_xml.dao.ContactDao;
import com.ecc.hibernate_xml.model.Contact;
import com.ecc.hibernate_xml.model.Person;

public class ContactService {
	private ContactDao contactDao;

	public ContactService() {
		contactDao = new ContactDao();
	}

	public List<Contact> listContacts(Person person) {
		return contactDao.list(person);
	}

	public void createContact(Contact contact, Person person) throws DaoException {
		contact.setPerson(person);
		contactDao.create(contact);
	}

	public void updateContact(Contact contact) throws DaoException {
		contactDao.update(contact);
	}

	public void deleteContact(Integer contactId) throws DaoException {
		contactDao.delete(contactDao.get(contactId));
	}

	public Contact getContact(Integer contactId) throws DaoException {
		return contactDao.get(contactId);
	}
}