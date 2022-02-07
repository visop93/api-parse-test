package files.h2sql;

import items.Item;
import org.example.App;

import java.io.Closeable;
import java.sql.*;
import java.util.HashMap;

import static logcreator.LogCreator.logger;

public class H2SqlUtils implements Closeable {
    Connection connection;
    String tableName;

    public H2SqlUtils(String tableName) throws SQLException {
        this.tableName = tableName;
        this.connection = App.getConnection();
    }

    //create table in SQL DB
    public void createTable() throws SQLException {
        logger.info("Creating table 'items' in the SQL DB.");
        reopenConnection();
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS items(" +
                "id INT PRIMARY KEY," +
                "name varchar(255) NOT NULL," +
                "buy_price INT NOT NULL," +
                "sell_price INT NOT NULL)";
        statement.execute(sql);
        statement.close();
        logger.info("'items' table was created successfully in the database.");
    }

    //write item to the SQL DB or update its info
    public void fillTable(HashMap<Integer, Item> map) throws SQLException {
        logger.info("Adding all items from the MAP to the SQL DB.");
        reopenConnection();
        Statement statement = connection.createStatement();
        String sql;
        for (var i : map.values()) {
            try {
                sql = "INSERT INTO items VALUES(" +
                        i.getId() + ", " +
                        "'" + i.getName() + "', " +
                        i.getBuyPrice() + ", " +
                        i.getSellPrice() + ")";
                statement.execute(sql);
                logger.info("{} has been added to SQL DB.", i.toString());
            } catch (SQLException e) {
                logger.error("{} is already in SQL DB", i.toString());
                sql = "UPDATE items" +
                " set buy_price = " + i.getBuyPrice() +
                ", sell_price = " + i.getSellPrice() +
                " where id = " + i.getId();
                statement.executeUpdate(sql);
                logger.error("{} was updated", i.toString());
            }
        }
        statement.close();
        logger.info("Adding items to the SQL DB is finished.");
    }

    //read SQL DB and add absent items
    public HashMap<Integer, Item> readTable(HashMap<Integer, Item> map) throws SQLException {
        logger.info("Filling table with items from current session.");
        reopenConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM items";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if (!map.containsKey(resultSet.getInt("id"))) {
                new Item(resultSet.getString("name"),
                        resultSet.getInt("id"),
                        resultSet.getInt("buy_price"),
                        resultSet.getInt("sell_price"));
            }
        }
        return map;
    }


    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("SQL connection error!");
        }
    }

    public void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = App.getConnection();
        }
    }

}
