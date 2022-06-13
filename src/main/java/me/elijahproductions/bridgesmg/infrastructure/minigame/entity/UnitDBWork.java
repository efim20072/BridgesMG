package me.elijahproductions.bridgesmg.infrastructure.minigame.entity;

import java.sql.*;

public class UnitDBWork {

    private Connection connection;

    public UnitDBWork(Connection connection) {
        this.connection = connection;
    }

    String generatedColumns[] = { "id" };

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
        try(PreparedStatement statement = connection.prepareStatement(query, generatedColumns)) {
            statement.execute();
            return statement.getGeneratedKeys().getLong(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
