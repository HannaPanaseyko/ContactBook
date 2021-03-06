package exception;
import entity.Contact;


public class AddressBookException extends Exception {

    private ResponseCode code;
    private String message;

    public AddressBookException(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public AddressBookException(ResponseCode code) {
        this.code = code;
    }

    public AddressBookException() {
        this.code = ResponseCode.NOT_FOUND;
        this.message = "This contact not found";
    }

    public ResponseCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
