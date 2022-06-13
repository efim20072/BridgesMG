package me.elijahproductions.bridgesmg.infrastructure.minigame.helper;

import lombok.val;

import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FileDatabaseHelper {
    private static final String DB_FILE_NAME = "database.db";

    public static Connection getConnection(String path) {
        String url ="jdbc:sqlite:" + path + DB_FILE_NAME;
        System.out.println(Color.RED + url);
        try {
            boolean mkResult = new File(path).mkdirs();
            //if (mkResult) initDatabase(connection);
            return DriverManager.getConnection(url);
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return null;
    }

    private static void initDatabase(Connection connection) {
        try {
            Statement st = connection.createStatement();
            st.execute("");
            /*
            """
                CREATE TABLE "configs" (
                    "id" INTEGER,
                    "name" TEXT,
                    "team_size" INTEGER,
                    PRIMARY KEY("id" AUTOINCREMENT)
                );
            """

             */
            st.executeUpdate("insert into \"configs\" values (null, \"adadad\", 3);");
            st.executeUpdate("insert into \"configs\" values (null, \"231234234\", 5);");
            st.executeUpdate("insert into \"configs\" values (null, \"asdfa4234\", 2);");
            st.close();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
