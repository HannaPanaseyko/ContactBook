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
     * @param contact contact
     * @return updated contact
     */
    void updateContactById(Contact contact) throws AddressBookException;

    /**
     * Show all contacts.
     */
    void showContacts() throws AddressBookException;

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

}
