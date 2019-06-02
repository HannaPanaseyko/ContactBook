package service;

import entity.Contact;
import exception.AddressBookException;

import java.io.BufferedReader;
import java.io.IOException;

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
     * Age parameter constant
     */
    int AGE = 4;
    /**
     * Height parameter constant
     */
    int HEIGHT = 5;
    /**
     * Marital status parameter constant
     */
    int MARITAL_STATUS = 6;
    /**
     * Exit parameter constant
     */
    int EXIT = 0;

    /**
     * Save created  contact in store.
     *
     * @param reader receive console input.
     * @return contact that will be created.
     */

    Contact addContact(BufferedReader reader) throws AddressBookException, IOException;

    /**
     * Update contact .
     *
     * @param reader receive console input.
     * @return updated contact
     */
    Contact updateContactById(BufferedReader reader) throws AddressBookException, IOException;

    /**
     * Delete contact.
     *
     * @param reader receive console input.
     */
    void deleteContactById(BufferedReader reader) throws AddressBookException, IOException;

    /**
     * Show all contacts.
     */
    void showContacts();
}

