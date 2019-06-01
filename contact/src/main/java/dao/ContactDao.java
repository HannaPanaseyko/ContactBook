package dao;

import entity.Contact;
import exception.AddressBookException;


public interface ContactDao {

    /**
     * Save created  contact in database.
     *
     * @param contact contact of person
     */
    void saveContact(Contact contact) throws AddressBookException;

    /**
     * Search contact by id.
     *
     * @param contactId id of contact
     * @return contact
     */
    Contact getContactById(int contactId) throws AddressBookException;

    /**
            * Update contact in your store.
     *
             * @param contactId contact
     * @return updated contact
     */
    Contact updateContactById(int contactId);

    /**
     * Show all contacts.
     */
    void showContacts();

    /**
     * Delete contact by id.
     *
     * @param id id of contact
     */
    void deleteContactById(int id) throws AddressBookException;

    /**
     * Delete contact by entity.
     *
     * @param contact contact that will be deleted
     */
    void deleteContactByEntity(Contact contact) throws AddressBookException;

    /**
     * Search contact by name.
     *
     * @param name name of contact
     * @return contact
     */
    Contact getContactByName(String name) throws AddressBookException;


}
