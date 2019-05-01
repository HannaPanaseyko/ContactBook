package dao.impl;

import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;

import java.util.Objects;

public class ContactDaoImpl implements ContactDao {
  //  private static final Scanner searchID = new Scanner(System.in);

    private static int generator = 0;
    private Contact[] store = new Contact[10];

    public ContactDaoImpl() {

    }
   // private List<Contact> store = new ArrayList<>();

    public void saveContact(Contact contact) throws AddressBookException {
        //searchSameContact(contact);
        //contact.setId(store.size() + 1);
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

        //generator++;
        //contact.setId(generator);
        //store.add(contact);
        //System.out.println("This contact was - = -");
        //System.out.println(contact.toString());
    }

    @Override
    public Contact updateContact(Contact contact)
    {
       // store.set(contact.getId()-1, contact);
       // Object[] ints = Stream.builder().add(1).add(2).build().toArray();
       // return contact;

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


    }

    @Override
    public void deleteContactById(int id) {
        for (Contact storeContacts : getStore()) {
            if (storeContacts.getId() == id) {
                storeContacts = null;
            }
        }
       // store.forEach(item -> {
       //     if(item.getId())
       // })
    }

    @Override
    public Contact getContactById(int contactId){
       // int search = searchID.nextInt();
       // for (int argument =1; argument <= store.length; argument++){
       //     if (argument == search){
       //         store[argument] = contact;
       //         System.out.println(contact);
       //     } else {
       //         System.out.println("Contact with this ID does not exist.");
       //     }
       // }
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
}




