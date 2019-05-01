package service.impl;

import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;
import service.ContactService;

import java.util.Objects;
import java.util.Scanner;

import static exception.ResponseCode.*;

public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao;
    public ContactDaoImpl contactDaoImpl = new ContactDaoImpl();

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public Contact addContact(Scanner scanner) throws AddressBookException {

        Contact contact = new Contact();

        System.out.println("Please enter your contact`s name");
        String name = scanner.next();
        contact.setName(name);

        System.out.println("Please enter your contact`s surname");
        String surName = scanner.next();
        contact.setSurName(surName);

        System.out.println("Please enter your contact`s phone number");
        String phoneNumber = scanner.next().replaceAll("[^0-9+]", ""); //заменяем символы, которые
        //не находяться между 0 и 9 и плюсом на пустоту(удаляем)
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
                return contactDao.getContactById(id);
            } else{
                System.out.println("Please enter a number");
                scanner.next();
            }
        }
        throw new AddressBookException(ResponseCode.SERVER_ERROR, "Please enter a number");
       // int id = scanner.nextInt();
        // return contactDao.getContactById(id);
    }

    @Override
    public Contact updateContactById(Scanner scanner) throws AddressBookException {
       // Contact contact = new Contact();
       // if (Objects.isNull(getContact(scanner))) {
       //     contact = getContactByName(scanner);
       //     if (Objects.isNull(contact)) {
       //         System.out.println("Contact not found");
       //         System.out.println("Maybe you want to create rhis contact?\n1.Yes\n2.No");
       //         if (scanner.hasNextInt()) {
       //             int choosing = scanner.nextInt();
       //             if (choosing == 1) {
       //                 return addContact(scanner);
       //             } else {
       //                 return null;
       //             }
       //         }else {
       //             System.out.println("Please enter a number");
       //             scanner.next();
       //         }
       //
       //     }
       // }

        Contact contact = null;
        if(scanner.hasNextInt()){
            contact = getContactByName(scanner);
        }else{
            System.out.println("Please enter a number");
        }
        System.out.println("Please enter the field which you want to change");
        return contactDao.updateContact(modifierFields(scanner, contact));
    }

    private Contact getContactByName(Scanner scanner) throws AddressBookException {
        return contactDao.getContactByName(scanner.next());
    }


    @Override
    public void deleteContactById(Scanner scanner) throws AddressBookException {
        System.out.println("Enter number of contact that will be deleted:");

        //contactDao.deleteContactById(scanner.nextInt());
        if(scanner.hasNextInt()){
            contactDao.deleteContactById(scanner.nextInt());
        } else {
            System.out.println("Please enter a number");
        }
    }

    @Override
    public void showContacts() {
        contactDao.showContacts();
    }

    @Override
    public void deleteContactByEntity(Scanner scanner) throws AddressBookException {
        System.out.println("Enter number of contact for deleting:");
        contactDao.deleteContactByEntity(getContact(scanner));
    }

    private Contact modifierFields(Scanner scanner, Contact contact) {
        showFieldToModifier();
        if (scanner.hasNextInt()) {

            int number = scanner.nextInt();
            switch (number) {
                case ContactService.NAME: {
                    return modifierOneField(ContactService.NAME, contact, scanner);
                }
                case ContactService.SUR_NAME: {
                    return modifierOneField(ContactService.SUR_NAME, contact, scanner);
                }
                case ContactService.PHONE_NUMBER: {
                    return modifierOneField(ContactService.SUR_NAME, contact, scanner);
                }
                default: {
                    System.out.println("Sorry, nothing to change");
                    return contact;
                }
            }


        } else {
            System.out.println("Please enter a number");
            scanner.next();
        }
        return contact;
    }

    private Contact modifierOneField(int field, Contact contact, Scanner scanner) {
        System.out.println("Enter new value of the chosen field:");
        String variable = scanner.nextLine();
        switch (field) {
            case ContactService.NAME: {
                contact.setName(variable);
                break;
            }
            case ContactService.SUR_NAME: {
                contact.setSurName(variable);
                break;
            }
            case ContactService.PHONE_NUMBER: {
                contact.setPhoneNumber(variable);
                break;
            }
        }
        System.out.println("Thank you for update your contact.");
        return contact;
    }

    private void showFieldToModifier() {
        System.out.println("1.Name");
        System.out.println("2.Sur name");
        System.out.println("3.Phone number");
    }


}
