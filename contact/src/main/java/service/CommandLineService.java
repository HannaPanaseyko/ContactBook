package service;

import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public interface CommandLineService {
    /**
     * Method that shows items of the menu.
     *
     */

    // static final Scanner scanner = new Scanner(System.in);

    //static final ContactServiceImpl service = new ContactServiceImpl(new ContactDaoImpl());
    static void showMenu() {
        //выводит взаимодействия
        System.out.println("1.Add contact.");
        System.out.println("2.Update contact.");
        System.out.println("3.Delete contact.");
        System.out.println("4.Show all contacts.");
        System.out.println("0.Exit.");
    }

    /**
     * Endpoint for begin our program.
     *
     * @param reader receive console input
     * @param service service that works with dao layer.
     */
    static void run(BufferedReader reader, ContactService service) {
        int numberOfMenu;
        boolean exit = false;
        do {
            System.out.println("Choose your wish: ");
            showMenu();
            try {
                String string = reader.readLine();
                if (isCorrectInteger(string)) {
                    numberOfMenu = Integer.parseInt(string);
                    switch (numberOfMenu) {
                        case 1: {
                            service.addContact(reader);
                            break;
                        }
                        case 2: {
                            service.updateContactById(reader);
                            break;
                        }
                        case 3: {
                            service.deleteContactById(reader);
                            break;
                        }
                        case 4: {
                            service.showContacts();
                            break;
                        }
                        case 0: {
                            System.out.println("Thank you for using our app. Good bye");
                            exit = true;
                            break;
                        }
                        default: {
                            throw new AddressBookException(ResponseCode.WRONG_DATA_TYPE,
                                    "Sorry, you chose wrong number of menu. Choose another one.");
                        }
                    }
                } else {
                    System.out.println("You entered a wrong number");
                }

        } catch(AddressBookException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!exit);
}
    static boolean isCorrectInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(ResponseCode.WRONG_DATA_TYPE);
            return false;
        }
        return true;
    }
    static boolean isCorrectDouble(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(ResponseCode.WRONG_DATA_TYPE);
            return false;
        }
        return true;
    }
}
