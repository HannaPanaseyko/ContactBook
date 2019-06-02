package dao.impl;

import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;
import org.omg.CORBA.portable.ApplicationException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ContactDaoImpl implements ContactDao {

    private static int generator = 0;

    public ContactDaoImpl() {

    }
    private List<Contact> store = new ArrayList<>();

    public void saveContact(Contact contact) throws AddressBookException {
        searchSameContact(contact);
        generator++;
        contact.setId(generator);
        store.add(contact);
        System.out.println("This contact was added into your contact book");
        System.out.println(contact.toString());
    }

    @Override
    public Contact updateContactById(int contactId)
    {
        Contact contactStore = null;
        for (Contact storeElement : store) {
            if (Objects.equals(storeElement.getId(), contactId)){
                storeElement.toString();
                 contactStore = storeElement;
            break;
            }
        }
        return contactStore;
    }

    @Override
    public void showContacts() {
        Collections.sort(store);
        for(Contact contactStore : store){
            if(Objects.nonNull(contactStore)){
                System.out.println(contactStore);
            }
        }
    }
    @Override
    public void deleteContactById(int contactId) throws AddressBookException {
        if (idExists(contactId)){
            throw new AddressBookException(ResponseCode.NO_CONTENT);
        }
        for(Contact storeContacts : getStore()){
            if (storeContacts.getId() == contactId){
                store.remove(storeContacts);
                break;
            }
        }
    }

    @Override
    public Contact getContactById(int contactId) throws AddressBookException{
            if(idExists(contactId)) {
                throw new AddressBookException(ResponseCode.NO_CONTENT);
            }

            for (Contact storeContacts : getStore()) {
                if(storeContacts.getId() == contactId) {
                    return storeContacts;
                }
            }
            return null;
    }
    @Override
    public Contact getContactByName(String name) {
        for (Contact storeElement : store) {
            if (Objects.equals(storeElement.getName().toLowerCase(), name.toLowerCase())) {
                return storeElement;
            }
        }
        return null;
    }

    @Override
    public void deleteContactByEntity(Contact contact) {
        for (Contact storeContacts : getStore()) {
            if (Objects.equals(storeContacts, contact)) {
                store.remove(contact);
                break;
            }
        }
    }

    public List<Contact> getStore(){
        return store;
    }

    private void searchSameContact(Contact contact) throws AddressBookException {
        for (Contact storeContact : getStore()) {
            if (Objects.nonNull(storeContact)
                    && contact.getName().equals(storeContact.getName())
                    && contact.getPhoneNumber().equals(storeContact.getPhoneNumber())
                    && contact.getSurName().equals(storeContact.getSurName())) {
                throw new AddressBookException(ResponseCode.OBJECT_EXIST,
                        "This contact was added earlier");
            }
        }
    }

    public boolean idExists(int contactId) {
        for(Contact storeContacts : getStore()){
            if (Objects.nonNull(storeContacts)){
                if (storeContacts.getId() == contactId)
                return false;
            }
        }
        return true;
    }

    public boolean emptyStore(){
        for(Contact contact : getStore()){
            if (Objects.nonNull(contact)){
                return false;
            }
        }
        return true;
    }


}




