package dao.impl;

import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;
import org.omg.CORBA.portable.ApplicationException;

import java.lang.reflect.Array;
import java.util.Objects;

public class ContactDaoImpl implements ContactDao {
  //  private static final Scanner searchID = new Scanner(System.in);

    private static int generator = 0;
    private Contact[] store = new Contact[7];

    public ContactDaoImpl() {

    }
   // private List<Contact> store = new ArrayList<>();

    public void saveContact(Contact contact) throws AddressBookException {
        searchSameContact(contact);
        //contact.setId(store.size() + 1);
        for (int argument = 0; argument <= store.length; argument++){
            if(Objects.isNull(store[argument])){
                generator = argument;
                contact.setId(++generator);
                store[argument] = contact;
                System.out.println("This contact was added into your contact book");
                System.out.println(contact.toString());
                break;
            }
        }

        //generator++;
        //contact.setId(generator);
        //store.add(contact);
        //System.out.println("This contact was - = -");
        //System.out.println(contact.toString());
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
                 contactStore = storeElement;
            break;
            }
        }
        return contactStore;
    }

    @Override
    public void showContacts() {
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
        for(int parameter = 0; parameter < store.length; parameter++){
            if (store[parameter].getId() == contactId){
                store[parameter] = null;
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
        for (int parameter = 0; parameter < store.length; parameter++) {
            if (store[parameter].equals(contact)) {
                store[parameter] = null;
                break;
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
        for(Contact contact : getStore()){
            if (Objects.nonNull(contact)){
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




