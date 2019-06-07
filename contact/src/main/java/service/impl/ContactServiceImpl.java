package service.impl;

import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;
import service.ContactService;

import java.util.Scanner;

public class ContactServiceImpl implements ContactService{

    private ContactDaoImpl contactDaoImpl;
    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDaoImpl = (ContactDaoImpl) contactDao;
    }
    @Override
    public Contact addContact(Scanner scanner) throws AddressBookException {

        Contact contact = new Contact();

        System.out.println("Please enter your contact`s name");
        String name = scanner.next();
        contact.setName(name);

        System.out.println("Please enter your contact`s surname");
        String surName = scanner.next();
        contact.setSurName(surName);

        System.out.println("Please enter your contact`s phone number");
        String phoneNumber = scanner.next().replaceAll("[^0-9+]", ""); 
        contact.setPhoneNumber(phoneNumber);

        contactDaoImpl.saveContact(contact);
        System.out.println("We are thankful that you saved your contact in our contact book app");
        return contact;
    }

    @Override
    public Contact getContact(Scanner scanner) throws AddressBookException{
        System.out.println("Please enter number of contact in your address book");
        if(scanner.hasNext()){
            if(scanner.hasNextInt()){
                int id = scanner.nextInt();
                return contactDaoImpl.getContactById(id);
            } else{
                System.out.println("Please enter a number");
                scanner.next();
            }
        }
        throw new AddressBookException(ResponseCode.SERVER_ERROR, "Please enter a number");
    }
    @Override
    public void updateContactById(Scanner scanner) throws AddressBookException {
        if (contactDaoImpl.emptyStore()) {
            throw new AddressBookException(ResponseCode.NOT_FOUND);
        } else {
            contactDaoImpl.showContacts();
            System.out.println("Enter please contacts ID what you want update");
            int contactId = scanner.nextInt();
            Contact contact = contactDaoImpl.updateContactById(contactId);
            boolean exit = true;
            do {
                System.out.println("Choose field which you would like to update: ");
                System.out.println("1 - Name");
                System.out.println("2 - Surname");
                System.out.println("3 - Phone number");
                System.out.println("0 - Done");
                int numberOfMenu = scanner.nextInt();
                switch (numberOfMenu) {
                    case 1: {
                        System.out.println("Updated contact's name:");
                        contact.setName(scanner.next());
                        break;
                    }
                    case 2: {
                        System.out.println("Updated contact's surname:");
                        contact.setSurName(scanner.next());
                        break;
                    }
                    case 3: {
                        System.out.println("Updated contact's phone number:");
                        contact.setPhoneNumber(scanner.next());
                        break;
                    }
                    case 0: {
                        System.out.println("Updated contact: ");
                        System.out.println(contact.toString());
                        exit = false;
                        break;
                    }
                    default: {
                        System.out.println("Sorry, this number is not appropriate.");
                    } } } while (exit);
        }
    }



    @Override
    public void deleteContactById(Scanner scanner) throws AddressBookException {

        if (contactDaoImpl.emptyStore()) {
            throw new AddressBookException(ResponseCode.NO_CONTENT);

        } else {
            contactDaoImpl.showContacts();
            System.out.println("Enter number of contact that will be deleted:");
            int deleteId = scanner.nextInt();
            contactDaoImpl.deleteContactById(deleteId);
        }
    }

    @Override
    public void showContacts() {
        contactDaoImpl.showContacts();
    }

    @Override
    public void deleteContactByEntity(Scanner scanner) throws AddressBookException {
        if (contactDaoImpl.emptyStore()) {
            throw new AddressBookException(ResponseCode.NO_CONTENT);

        } else {
            contactDaoImpl.showContacts();
            System.out.println("Enter number of contact that will be deleted:");
            contactDaoImpl.deleteContactByEntity(getContact(scanner));
        }
    }
}

