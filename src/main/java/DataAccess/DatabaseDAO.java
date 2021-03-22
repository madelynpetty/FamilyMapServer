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

    /*
    public void clearTables() throws DataAccessException {
        String sql = "DELETE FROM event;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear events table in the database");
        }

        sql = "DELETE FROM user;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear users table in the database");
        }

        sql = "DELETE FROM person;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear person table in the database");
        }

        sql = "DELETE FROM authToken;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear authToken table in the database");
        }
    }
    */

    public void clearTables() throws DataAccessException {
        String sql = "drop table if exists event;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear events table in the database");
        }

        sql = "drop table if exists user;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear users table in the database");
        }

        sql = "drop table if exists person;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear person table in the database");
        }

        sql = "drop table if exists authToken;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to clear authToken table in the database");
        }

        createTables();
    }

    public void createTables() throws DataAccessException {
        String sql = "create table if not exists event (" +
                "    eventID string not null unique," +
                "    associatedUsername string not null," +
                "    personID string not null," +
                "    latitude float not null," +
                "    longitude float not null," +
                "    country string not null," +
                "    city string not null," +
                "    eventType string not null," +
                "    year int not null," +
                "    primary key(\"eventID\")" +
                ");";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to create events table in the database");
        }

        sql = "create table if not exists user (" +
                "    username string not null unique," +
                "    password string not null," +
                "    email string not null," +
                "    firstName string not null," +
                "    lastName string not null," +
                "    gender string not null," +
                "    personID string not null," +
                "    primary key(\"username\")" +
                ");";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to create users table in the database");
        }

        sql = "create table if not exists person (" +
                "    personID string not null," +
                "    associatedUsername string not null," +
                "    firstName string not null," +
                "    lastName string not null," +
                "    gender string not null," +
                "    fatherID string," +
                "    motherID string," +
                "    spouseID string," +
                "    primary key(\"personID\")" +
                ");";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to create person table in the database");
        }

        sql = "create table if not exists authToken (" +
                "    username string not null," +
                "    token string not null unique," +
                "    primary key(\"token\")" +
                ");";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error: Unable to create authToken table in the database");
        }
    }
}
