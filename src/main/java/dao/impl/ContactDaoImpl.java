package dao.impl;

import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;

import java.util.Objects;

public class ContactDaoImpl implements ContactDao {

    private static int generator = 0;
    private Contact[] store = new Contact[10];

    public ContactDaoImpl() {
    }

    public void saveContact(Contact contact) throws AddressBookException {
        for (int argument = 1; argument <= store.length; argument++){
            if(store[argument] == null){
                generator = argument;
                contact.setId(++generator);
                store[argument] = contact;
                System.out.println("This contact was added into your contact book");
                System.out.println(contact.toString());
                break;
            }
        }
    }

    @Override
    public Contact updateContact(Contact contact)
    {
        for (Contact storeContacts : getStore()){
        if (Objects.equals(storeContacts.getId(), contact.getId())){
            storeContacts = contact;
            return storeContacts;
        }
    }
        return contact;
    }

    @Override
    public void showContacts() {
        for (Contact storeContacts : store) {
            if (Objects.nonNull(storeContacts)) {
                System.out.println(storeContacts);
            }
        }

    }

    @Override
    public void deleteContactById(int id) {
        for (Contact storeContacts : getStore()) {
            if (storeContacts.getId() == id) {
                storeContacts = null;
            }
        }
    }

    @Override
    public Contact getContactById(int contactId){
        for (Contact storeContacts : getStore()){
            if(storeContacts.getId() == contactId){
                return storeContacts;
            }
        }
        return null;
    }
    @Override
    public Contact getContactByName(String name) {
        for (Contact storeContacts : getStore()) {
            if (storeContacts.getName().toLowerCase().equals(name.toLowerCase())) {
                return storeContacts;
            }
        }
        return null;
    }

    @Override
    public void deleteContactByEntity(Contact contact){
        for (Contact storeContacts : getStore()){
            if(storeContacts.equals(contact)){
                storeContacts = null;
            }
        }
    }

    public Contact[] getStore(){
        return store;
    }

    private void searchSameContact(Contact contact) throws AddressBookException {
        for (Contact contactFromStore : getStore()) {
            if (Objects.nonNull(contactFromStore)
                    && contact.getName().equals(contactFromStore.getName())
                    && contact.getPhoneNumber().equals(contactFromStore.getPhoneNumber())
                    && contact.getSurName().equals(contactFromStore.getSurName())) {
                throw new AddressBookException(ResponseCode.OBJECT_EXIST,
                        "This contact was added early");
            }
        }
    }
}




