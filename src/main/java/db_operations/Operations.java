package db_operations;

import java.sql.SQLException;
import java.text.ParseException;

public abstract class Operations {
    /**
     * Defines class operations for each Execution menu option
     * */
    public abstract void invoke(int choice) throws SQLException, ParseException;

}
