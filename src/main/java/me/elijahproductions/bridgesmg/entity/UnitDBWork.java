package me.elijahproductions.bridgesmg.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UnitDBWork {

    private Connection connection;

    public UnitDBWork(Connection connection) {
        this.connection = connection;
    }


    public ResultSet selectQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Long insertQuery(String query) {
        try(Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
