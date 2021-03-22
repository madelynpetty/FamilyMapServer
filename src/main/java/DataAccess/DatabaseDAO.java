package DataAccess;
import Models.Event;

import java.sql.*;
import java.util.ArrayList;

/**
 * Data Access Object for the event. Inserts, deletes, and clears the event table in the database.
 */
public class DatabaseDAO {
    private final Connection conn;

    public DatabaseDAO(Connection conn)
    {
        this.conn = conn;
    }

    public void clearTables() throws DataAccessException {
        String sql = "DELETE FROM event";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear events table in the database");
        }

        sql = "DELETE FROM user";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear users table in the database");
        }

        sql = "DELETE FROM person";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear person table in the database");
        }

        sql = "DELETE FROM authToken";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear authToken table in the database");
        }
    }
}
