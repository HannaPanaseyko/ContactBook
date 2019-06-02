package service.impl;

import dao.ContactDao;
import dao.impl.ContactDaoImpl;
import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;
import service.CommandLineService;
import service.ContactService;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

public class ContactServiceImpl extends CommandLineServiceImpl implements ContactService {

    private static final String ID = "ID: ";
    private static final String NAME = "Name: ";
    private static final String SUR_NAME = "Surname: ";
    private static final String PHONE_NUMBER = "Phone Number: ";
    private static final String AGE = "Age: ";
    private static final String HEIGHT = "Height: ";
    private static final String MARITAL_STATUS = "Marital status: ";
    private static final String CREATE_DATE = "Create date: ";
    private static final String WORD_SEPARATOR = "; ";
    private static final String SET_PATH = "./backup/";
    private static final String FILE_NAME = "contacts_" +
            LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ".txt";
    private ContactDaoImpl contactDaoImpl;

    ContactServiceImpl(ContactDao contactDao) {
        this.contactDaoImpl = (ContactDaoImpl) contactDao;
    }

    @Override
    public void addContact(BufferedReader reader) throws IOException {

        Contact contact = new Contact();

        System.out.println("Please enter your contact`s name");
        String name = reader.readLine();
        contact.setName(name);

        System.out.println("Please enter your contact`s surname");
        String surName = reader.readLine();
        contact.setSurName(surName);

        System.out.println("Please enter your contact`s phone number");
        String phoneNumber = reader.readLine().replaceAll("[^0-9+]", ""); //заменяем символы, которые
        //не находятся между 0 и 9 и плюсом на пустоту(удаляем)
        contact.setPhoneNumber(phoneNumber);


        System.out.println("Please enter your contact's age");
        String stringAge = reader.readLine();
        if (CommandLineService.isCorrectInteger(stringAge)) {
            int age = Integer.parseInt(stringAge);
            contact.setAge(age);
        }

        System.out.println("Please enter you contact's height");
        String stringHeight = reader.readLine();
        if (CommandLineService.isCorrectDouble(stringHeight)) {
            double height = Double.parseDouble(stringHeight);
            contact.setHeight(height);
        }

        System.out.println("Is your marital status married?");
        System.out.println("1 - Single");
        System.out.println("2 - Married");
        boolean maritalStatus;
        String enterString = null;
        enterString = reader.readLine();
        if (enterString.equals("1")) {
            maritalStatus = false;
        } else {
            maritalStatus = true;
        }
        contact.setMaritalStatus(maritalStatus);
        contactDaoImpl.saveContact(contact);
        System.out.println("We are thankful that you saved your contact in our contact book app");

    }

    @Override
    public Contact updateContactById(BufferedReader reader) throws AddressBookException, IOException {
        contactDaoImpl.showContacts();
        System.out.println("Enter please contacts ID what you want update");
        String string = reader.readLine();
        if (CommandLineService.isCorrectInteger(string)) {
            int index = Integer.parseInt(string);
            Contact contact = contactDaoImpl.getContactById(index);
            editContact(reader, contact);
            contactDaoImpl.updateContactById(contact);
        }
        throw new AddressBookException(ResponseCode.NO_CONTENT);
    }

    private Contact editContact(BufferedReader reader, Contact contact) throws AddressBookException, IOException {
        boolean exit = true;
        do {
            System.out.println("Choose field which you would like to update: ");
            System.out.println("1 - Name");
            System.out.println("2 - Surname");
            System.out.println("3 - Phone number");
            System.out.println("4 - Age");
            System.out.println("5 - Height");
            System.out.println("6 - Marital status");
            System.out.println("0 - Done");
            String string = reader.readLine();
            if (CommandLineService.isCorrectInteger(string)) {
                //  String stringUpdate = reader.readLine();
                int number = Integer.parseInt(string);
                switch (number) {
                    case ContactService.NAME: {
                        return editField(1, contact, reader);
                    }
                    case ContactService.SUR_NAME: {
                        return editField(2, contact, reader);
                    }
                    case ContactService.PHONE_NUMBER: {
                        return editField(3, contact, reader);
                    }
                    case ContactService.AGE: {
                        return editField(4, contact, reader);
                    }
                    case ContactService.HEIGHT: {
                        return editField(5, contact, reader);
                    }
                    case ContactService.MARITAL_STATUS: {
                        return editField(6, contact, reader);
                    }
                    case EXIT: {
                        exit = true;
                        break;
                    }
                    default: {
                        System.out.println("Sorry, this number is not appropriate.");
                    }
                }
            }
        } while (exit);
        return contact;
    }

    private Contact editField(int numberOfField, Contact contact, BufferedReader reader)
            throws AddressBookException, IOException {
        System.out.println("Choose field which you would like to update: ");
        String string = reader.readLine();
        switch (numberOfField) {
            case ContactService.NAME: {
                contact.setName(string);
                break;
            }
            case ContactService.SUR_NAME: {
                contact.setSurName(string);
                break;
            }
            case ContactService.PHONE_NUMBER: {
                contact.setPhoneNumber(string);
                break;
            }
            case ContactService.AGE: {
                if (CommandLineService.isCorrectInteger(string)) {
                    contact.setAge(Integer.parseInt(string));
                }
                break;
            }
            case ContactService.HEIGHT: {
                if (CommandLineService.isCorrectDouble(string)) {
                    contact.setHeight(Double.parseDouble(string));
                }
                break;
            }
            case ContactService.MARITAL_STATUS: {
                contact.setMaritalStatus(Boolean.parseBoolean(string));
                break;
            }
            default: {
                System.out.println("123");
            }
        }
        return contact;
    }

    @Override
    public void deleteContactById(BufferedReader reader) throws AddressBookException, IOException {
        contactDaoImpl.showContacts();
        System.out.println("Enter number of contact that will be deleted:");
        String string = reader.readLine();
        int deleteId = Integer.parseInt(string);
        contactDaoImpl.deleteContactById(deleteId);
    }

    @Override
    public void showContacts() {
        contactDaoImpl.showContacts();
    }


}


