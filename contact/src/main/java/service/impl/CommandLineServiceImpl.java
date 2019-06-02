package service.impl;

import dao.impl.ContactDaoImpl;
import dao.impl.DB;
import exception.AddressBookException;
import service.CommandLineService;
import service.ContactService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CommandLineServiceImpl implements CommandLineService {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final ContactServiceImpl service = new ContactServiceImpl(new ContactDaoImpl());

    public static void run(){
        DB.createAndConnectDB();
        CommandLineService.run(reader, service);
    }

}
