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
  //  private static final Scanner searchID = new Scanner(System.in);

    private static int generator = 0;

    public ContactDaoImpl() {

    }
    private List<Contact> store = new ArrayList<>();

    public void saveContact(Contact contact) throws AddressBookException {
        searchSameContact(contact);
        //contact.setId(store.size() + 1);
        generator++;
        contact.setId(generator);
        store.add(contact);
        System.out.println("This contact was added into your contact book");
        System.out.println(contact.toString());
    }

    @Override
    public Contact updateContactById(int contactId)
    {
       // store.set(contact.getId()-1, contact);
       // Object[] ints = Stream.builder().add(1).add(2).build().toArray();
       // return contact;

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
       // store.forEach(item -> {
       //     if(item.getId())
       // })
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


    //private void searchSameContact(Contact contact) throws AddressBookException{
    //    for (Contact contactFromStore : getStore()){
    //        if(Objects.nonNull(contactFromStore)
    //                && contact.getName().equals(contact1.getName())
    //                && contact.getPhoneNumber().equals(contact1.getPhoneNumber())
    //                && contact.getSurName().equals(contact1.getSurName())){
    //            throw new AddressBookException(ResponseCode.OBJECT_EXIST,
    //                    "This contact was added early");
    //        }
    //
    //    }

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




