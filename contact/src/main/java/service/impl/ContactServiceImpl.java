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
        String phoneNumber = scanner.next().replaceAll("[^0-9+]", ""); //заменяем символы, которые
        //не находятся между 0 и 9 и плюсом на пустоту(удаляем)
        contact.setPhoneNumber(phoneNumber);

        contactDaoImpl.saveContact(contact);
        System.out.println("We are thankful that you saved your contact in our contact book app");
        //return contact;
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
       // int id = scanner.nextInt();
        // return contactDao.getContactById(id);
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



    //@Override
   //public Contact updateContactById(Scanner scanner) throws AddressBookException {
   //   // Contact contact = new Contact();
   //   // if (Objects.isNull(getContact(scanner))) {
   //   //     contact = getContactByName(scanner);
   //   //     if (Objects.isNull(contact)) {
   //   //         System.out.println("Contact not found");
   //   //         System.out.println("Maybe you want to create rhis contact?\n1.Yes\n2.No");
   //   //         if (scanner.hasNextInt()) {
   //   //             int choosing = scanner.nextInt();
   //   //             if (choosing == 1) {
   //   //                 return addContact(scanner);
   //   //             } else {
   //   //                 return null;
   //   //             }
   //   //         }else {
   //   //             System.out.println("Please enter a number");
   //   //             scanner.next();
   //   //         }
   //   //
   //   //     }
   //   // }
   //
   //    if(contactDaoImpl.emptyStore()){
   //        throw new AddressBookException(ResponseCode.NOT_FOUND);
   //    } else {
   //        contactDaoImpl.showContacts();
   //        System.out.println("Please enter the number of Id of the contact you want to change");
   //        int contactId = scanner.nextInt();
   //        Contact contact = contactDaoImpl.updateContactById(contactId);
   //        boolean exit = true;
   //        do{
   //            modifierFields(scanner, contact);
   //
   //
   //        } while (exit);
   //    }
   //
   //private Contact getContactByName(Scanner scanner) throws AddressBookException {
   //    return contactDaoImpl.getContactByName(scanner.next());
   //}
   //

   // @Override
   // public void deleteContactByEntity(Scanner scanner) throws AddressBookException {
   //     System.out.println("Enter number of contact for deleting:");
   //     contactDaoImpl.deleteContactByEntity(getContact(scanner));
   // }
   //
   // private void modifierFields(Scanner scanner, Contact contact); {
   //     showFieldToModifier();
   //     if (scanner.hasNextInt()) {
   //
   //         int number = scanner.nextInt();
   //         switch (number) {
   //             case ContactService.NAME: {
   //                 System.out.println("Please update your contact's name: ");
   //                 modifierOneField(ContactService.NAME, contact, scanner);
   //                 break;;
   //             }
   //             case ContactService.SUR_NAME: {
   //                 System.out.println("Please update your contact's surname: ");
   //                 modifierOneField(ContactService.SUR_NAME, contact, scanner);
   //                 break;
   //             }
   //             case ContactService.PHONE_NUMBER: {
   //                 System.out.println("Please update your contact's phone number: ");
   //                 modifierOneField(ContactService.PHONE_NUMBER, contact, scanner);
   //                 break;;
   //             }
   //             default: {
   //                 System.out.println("Sorry, nothing to change");
   //             }
   //         }
   //     } else {
   //         System.out.println("Please enter a number");
   //         scanner.next();
   //     }
   // }

   // private Contact modifierOneField(int field, Contact contact, Scanner scanner) {
   //     System.out.println("Enter new value of the chosen field:");
   //     String variable = scanner.next();
   //     switch (field) {
   //         case ContactService.NAME: {
   //             contact.setName(variable);
   //             break;
   //         }
   //         case ContactService.SUR_NAME: {
   //             contact.setSurName(variable);
   //             break;
   //         }
   //         case ContactService.PHONE_NUMBER: {
   //             contact.setPhoneNumber(variable);
   //             break;
   //         }
   //
   //     }
   //     System.out.println("Thank you for updating your contact.");
   //     return contact;
   // }

//
//    private void showFieldToModifier() {
//        System.out.println("1.Name");
//        System.out.println("2.Surname");
//        System.out.println("3.Phone number");
//        System.out.println("0.Exit without update");
//    }

}

