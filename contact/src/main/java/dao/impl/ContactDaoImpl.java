package dao.impl;

import dao.ContactDao;
import entity.Contact;
import exception.AddressBookException;
import exception.ResponseCode;
import service.CommandLineService;

import java.sql.*;

public class ContactDaoImpl extends DB implements ContactDao, CommandLineService {
  //  private static final Scanner searchID = new Scanner(System.in);

    private Connection connection = DB.getConnect();
    private static int generator = 0;

    public ContactDaoImpl() {

    }

    public void saveContact(Contact contact){
        try(PreparedStatement preparedStatement = connection.prepareStatement((INSERT_CONTACT))){
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurName());
            preparedStatement.setString(3, contact.getPhoneNumber());
            preparedStatement.setInt(4, contact.getAge());
            preparedStatement.setDouble(5, contact.getHeight());
            preparedStatement.setBoolean(6, contact.getMaritalStatus());
            preparedStatement.setTimestamp(7, contact.getCreateDate());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateContactById(Contact contact){
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTACT)) {
            preparedStatement.setInt(1, contact.getId());
            preparedStatement.setString(2, contact.getName());
            preparedStatement.setString(3, contact.getSurName());
            preparedStatement.setString(4, contact.getPhoneNumber());
            preparedStatement.setInt(5, contact.getAge());
            preparedStatement.setDouble(6, contact.getHeight());
            preparedStatement.setBoolean(7, contact.getMaritalStatus());
            preparedStatement.setInt(8, contact.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void showContacts(){
        try(Statement statement = connection.createStatement()){
            statement.execute(SELECT_ALL);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                System.out.println(
                         "ID: " + resultSet.getInt(1) + "  " +
                         "NAME: " + resultSet.getString(2) + "  " +
                         "SURNAME: " + resultSet.getString(3) + "  " +
                         "PHONE NUMBER: " + resultSet.getString(4) + "  " +
                         "AGE: " + resultSet.getInt(5) + "  " +
                         "HEIGHT: " + resultSet.getDouble(6) + "  " +
                         "MARITAL STATUS: " + resultSet.getBoolean(7) + "  " +
                         "CREATE DATE: " + resultSet.getTimestamp(8));

            }
            }catch (SQLException e){
                e.printStackTrace();
        }
        }


    @Override
    public void deleteContactById(int contactId) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONTACT)){
            preparedStatement.setInt(1, contactId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public Contact getContactById(int contactId) throws AddressBookException {
        Contact updateContact = null;
        try (Statement statement = connection.createStatement()) {
            statement.execute("SELECT * " +
                    "FROM contacts " +
                    "WHERE id = " + contactId);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                updateContact = new Contact();
                updateContact.setId(resultSet.getInt(1));
                updateContact.setName(resultSet.getString(2));
                updateContact.setSurName(resultSet.getString(3));
                updateContact.setPhoneNumber(resultSet.getString(4));
                updateContact.setAge(resultSet.getInt(5));
                updateContact.setHeight(resultSet.getDouble(6));
                updateContact.setMaritalStatus(resultSet.getBoolean(7));
                updateContact.setCreateDate(resultSet.getTimestamp(8));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (updateContact == null) {
            throw new AddressBookException(ResponseCode.NOT_FOUND);
        } else {
            return updateContact;
        }
    }
}




