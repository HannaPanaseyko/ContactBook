package service;

import dao.impl.ContactDaoImpl;
import entity.Contact;
import exception.AddressBookException;
import service.impl.ContactServiceImpl;

import java.util.Scanner;

public interface ContactService {

    /**
     * Name parameter constant
     */
    int NAME = 1;
    /**
     * Sur name parameter constant
     */
    int SUR_NAME = 2;
    /**
     * Phone number parameter constant
     */
    int PHONE_NUMBER = 3;

    /**
     * Save created  contact in store.
     *
     * @param scanner scanner of console input.
     * @return contact that will be created.
     */

    Contact addContact(Scanner scanner) throws AddressBookException;

    /**
     * Return contact by id in address book
     *
     * @param scanner scanner of console input.
     * @return searched contact
     */
    Contact getContact(Scanner scanner) throws AddressBookException;

    /**
     * Update contact .
     *
     * @param scanner scanner of console input.
     * @return updated contact
     */
    Contact updateContactById(Scanner scanner) throws AddressBookException;

    /**
     * Delete contact.
     *
     * @param scanner scanner of console input.
     */
    void deleteContactById(Scanner scanner) throws AddressBookException;

    /**
     * Show all contacts.
     */
    void showContacts();

    /**
     * Delete contact by entity.
     *
     * @param scanner scanner of console input.
     */
    void deleteContactByEntity(Scanner scanner) throws AddressBookException;
}

