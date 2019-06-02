package dao.impl;

import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;

import java.time.LocalDateTime;
import java.util.*;

public class ContactDaoImpl implements ContactDao {
    private static int generator = 0;

    public ContactDaoImpl() {

    }
    public Set<Contact> store = new TreeSet<>(Comparator.comparing(Contact::getName)
            .thenComparing(Contact::getSurName)
            .thenComparing(Contact::getPhoneNumber));

    public void saveContact(Contact contact) throws AddressBookException {
        searchSameContact(contact);
        generator++;
        contact.setId(generator);
        contact.setCreateDate(LocalDateTime.now());
        store.add(contact);

        System.out.println("This contact was added into your contact book");
        System.out.println(contact.toString());
    }

    @Override
    public Contact updateContactById(int contactId) throws AddressBookException {
            return Optional.of(store
            .stream()
            .filter(storeElement -> storeElement.getId() == contactId)
            .findFirst())
            .get()
            .orElseThrow(() -> new AddressBookException(ResponseCode.NOT_FOUND));
    }
    @Override
    public void showContacts(){
        store
                .stream()
                .sorted(Comparator.comparing(Contact::getId))
                .forEach(System.out::println);
    }

    @Override
    public void deleteContactById(int contactId) throws AddressBookException {
        {
            store
                    .removeIf(contact -> contact.getId() == contactId);
        }
    }

    @Override
    public Contact getContactByName(String name) throws AddressBookException {
        return Optional.of(store
                .stream()
                .filter(contactStore -> contactStore.toString() == name)
                .findFirst())
                .get()
                .orElseThrow(() -> new AddressBookException(ResponseCode.NOT_FOUND));
    }

    @Override
    public Contact getContactById(int contactId) throws AddressBookException{
            return Optional.of(store.stream()
            .filter(storeContact -> storeContact.getId() == contactId)
            .findFirst())
            .get()
            .orElseThrow(() -> new AddressBookException(ResponseCode.NOT_FOUND));
    }

    public Set getStore(){
        return store;
    }

    private void searchSameContact(Contact contact) throws AddressBookException {
        if (store
                .stream()
                .anyMatch(contact::equals)) {
                throw new AddressBookException(ResponseCode.OBJECT_EXIST,
                        "This contact was added earlier");
            }
        }

    public boolean emptyStore(){
        return store
                .stream()
                .noneMatch(contact -> contact.equals(contact));
    }
}




