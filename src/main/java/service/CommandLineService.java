package service;

import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;

import java.util.Scanner;

public interface CommandLineService {
    /**
     * Method that shows items of the menu.
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
     * @param scanner scanner of console input
     * @param service service that works with dao layer.
     */
    static void run(Scanner scanner, ContactService service) {
        boolean exit = false;
        do {
            try {
                System.out.println("Choose your wish: ");
                showMenu();
                if (scanner.hasNextInt()) {
                    switch (scanner.nextInt()) {
                        case 1: {
                            service.addContact(scanner);
                            break;
                        }
                        case 2: {
                            System.out.println(service.updateContactById(scanner));
                            break;
                        }
                        case 3: {
                            service.deleteContactById(scanner);
                            break;
                        }
                        case 4: {
                            service.showContacts();
                            break;
                        }
                        case 5: {
                            System.out.println(service.getContact(scanner));
                            break;
                        }
                        case 0: {
                            System.out.println("Thank you for using our app. Good bye");
                            exit = false;
                            break;
                        }
                        default: {
                            throw new AddressBookException(ResponseCode.WRONG_DATA_TYPE,
                                    "Sorry, you chose wrong number of menu. Choose another one.");
                        }
                    }
                } else {
                    System.out.println("You entered a wrong number");
                    scanner.next();
                }

        } catch(AddressBookException e){
            System.out.println(e.getMessage());
        }
    } while (!exit);
}
}
