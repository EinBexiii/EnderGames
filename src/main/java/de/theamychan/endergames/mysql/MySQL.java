package de.theamychan.endergames.mysql;

import de.theamychan.endergames.EnderGames;
import lombok.Getter;

import java.sql.*;
import java.util.Locale;

public class MySQL {

    @Getter
    private EnderGames plugin;

    @Getter
    private Connection connection;

    public MySQL( EnderGames plugin ) {
        this.plugin = plugin;
        try {
            connection = DriverManager.getConnection( "jdbc:mysql://" + plugin.getConfig().getMySQLHost()  + ":3306/" + plugin.getConfig().getMySQLDatabase() + "?autoReconnect=true", plugin.getConfig().getMySQLUser(), plugin.getConfig().getMySQLPassword() );
            plugin.getLogger().info( plugin.getLocaleManager().translate( Locale.forLanguageTag( plugin.getConfig().getDefaultLocale() ), "mysql-connect-success" ) );
        } catch ( SQLException e ) {
            plugin.getLogger().info( plugin.getLocaleManager().translate( Locale.forLanguageTag( plugin.getConfig().getDefaultLocale() ), "mysql-connect-failed" ) );
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
