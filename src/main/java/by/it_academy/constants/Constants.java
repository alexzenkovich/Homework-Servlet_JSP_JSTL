package by.it_academy.constants;

public interface Constants {

    String DATABASE_PROPERTIES_FILE_PATH = "/database/datasource.properties";

    String DATABASE_DRIVER_KEY = "driver";
    String DATABASE_FILE_URL_KEY = "url";
    String DATABASE_USER_KEY = "userName";
    String DATABASE_PASSWORD_KEY = "password";

    String DDL_INITIALIZATION_SCRIPT_PATH = "/database/script/DDL-initialization.sql";
    String DML_INITIALIZATION_SCRIPT_PATH = "/database/script/DML-initialization.sql";

    String BOOK_WAS_ADDED_TO_BASKET = "Book was added to your basket!";
    String BOOK_WAS_DELETED_FROM_BASKET = "Book was deleted from your basket!";
}
