package de.theamychan.endergames.mysql;

import de.theamychan.endergames.EnderGames;
import lombok.Getter;

import java.sql.*;

public class MySQL {

    @Getter
    private EnderGames plugin;

    @Getter
    private Connection connection;

    private String host = "localhost";
    private String database = "gomint";
    private String username = "root";
    private String password = "";

    public MySQL( EnderGames plugin ) {
        this.plugin = plugin;
        try {
            connection = DriverManager.getConnection( "jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", username, password );
            plugin.getLogger().info( "Die Verbindung zu MySQL wurde hergestellt!" );
        } catch ( SQLException e ) {
            plugin.getLogger().info( "Es konnte KEINE Verbindung hergestellt werden!" );
        }
    }

    //  UUID, Name, Kills, Deaths, Wins, Games, Coins, Kits, LastKit

    public void createTables( ) {
        update( "CREATE TABLE IF NOT EXISTS EnderGames(UUID TEXT, Name TEXT, Kills INT, Deaths INT, Wins INT, Games INT, Coins INT, Kits TEXT, LastKit INT);" );
    }

    public void update( String update ) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate( update );
            statement.close();
        } catch ( SQLException e ) {
            new MySQL( plugin );
            e.printStackTrace();
        }
    }

    public ResultSet query( String qry ) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery( qry );
        } catch ( SQLException e ) {
            new MySQL( plugin );
            e.printStackTrace();
        }
        return resultSet;
    }

    public void close( ) {
        if( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected( ) {
        return connection != null;
    }

}
