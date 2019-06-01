package exception;

public enum ResponseCode {
    NOT_FOUND(404),
    OBJECT_EXIST(302),
    VALIDATION_ERROR(301),
    WRONG_DATA_TYPE(206),
    NO_CONTENT(204, "The data does not exists in the database."),

    SERVER_ERROR(500);

    private int code;
    private String str;

    ResponseCode(int code, String str) {
        this.code = code;
        this.str = str;
    }

    ResponseCode(int code) {
        this.code = code;
    }
    public String getStr() {
        return str;
    }

    public int getCode() {
        return code;
    }
}
