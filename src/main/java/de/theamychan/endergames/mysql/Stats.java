package de.theamychan.endergames.mysql;

import de.theamychan.endergames.EnderGames;
import io.gomint.entity.EntityPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stats {

    private EnderGames plugin;

    public Stats( EnderGames plugin ) {
        this.plugin = plugin;
    }

    public boolean playerExists( String id ) {
        ResultSet resultSet = plugin.getMySQL().query( "SELECT * FROM EnderGames WHERE UUID= '" + id + "'" );
        try {
            if( resultSet.next() ) {
                return resultSet.getString( "UUID" ) != null;
            }
            return false;
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public void createPlayer( String id, String name, int kills, int deaths, int wins, int games, int coins,  String kit, int lastKit ) {
        if( !playerExists( id ) ) {
            plugin.getMySQL().update( "INSERT INTO EnderGames(UUID, Name, Kills, Deaths, Wins, Games, Coins, Kits, LastKit) VALUES ('" + id + "', '" + name + "', '" + kills + "', '" + deaths + "', '" + wins + "', '" + games + "', '" + coins + "', '" + kills + "', '" + lastKit + "')" );
        }
    }

    public String getName( EntityPlayer player ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            ResultSet resultSet = plugin.getMySQL().query( "SELECT * FROM EnderGames WHERE UUID= '" + id + "'" );
            try {
                if( (!resultSet.next()) || (String.valueOf( resultSet.getString( "Name" ) ) == null) ) ;
                 return resultSet.getString( "Name" );
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            getName( player );
        }
        return null;
    }

    public Integer getKills( EntityPlayer player ) {
        String id = player.getUUID().toString();

        if( playerExists( id ) ) {
            ResultSet resultSet = plugin.getMySQL().query( "SELECT * FROM EnderGames WHERE UUID= '" + id + "'" );
            try {
                if( (!resultSet.next()) || resultSet.getInt( "Kills" ) == -1 ) ;
                return resultSet.getInt( "Kills" );
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            getKills( player );
        }
        return -1;
    }

    public Integer getDeaths( EntityPlayer player ) {
        String id = player.getUUID().toString();

        if( playerExists( id ) ) {
            ResultSet resultSet = plugin.getMySQL().query( "SELECT * FROM EnderGames WHERE UUID= '" + id + "'" );
            try {
                if( (!resultSet.next()) || resultSet.getInt( "Deaths" ) == -1 ) ;
                return resultSet.getInt( "Deaths" );
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            getDeaths( player );
        }
        return -1;
    }

    public Integer getWins( EntityPlayer player ) {
        String id = player.getUUID().toString();

        if( playerExists( id ) ) {
            ResultSet resultSet = plugin.getMySQL().query( "SELECT * FROM EnderGames WHERE UUID= '" + id + "'" );
            try {
                if( (!resultSet.next()) || resultSet.getInt( "Wins" ) == -1 ) ;
                return resultSet.getInt( "Wins" );
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            getWins( player );
        }
        return -1;
    }

    public Integer getGames( EntityPlayer player ) {
        String id = player.getUUID().toString();

        if( playerExists( id ) ) {
            ResultSet resultSet = plugin.getMySQL().query( "SELECT * FROM EnderGames WHERE UUID= '" + id + "'" );
            try {
                if( (!resultSet.next()) || resultSet.getInt( "Games" ) == -1 ) ;
                return resultSet.getInt( "Games" );
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            getGames( player );
        }
        return -1;
    }

    public Integer getCoins( EntityPlayer player ) {
        String id = player.getUUID().toString();

        if( playerExists( id ) ) {
            ResultSet resultSet = plugin.getMySQL().query( "SELECT * FROM EnderGames WHERE UUID= '" + id + "'" );
            try {
                if( (!resultSet.next()) || resultSet.getInt( "Coins" ) == -1 ) ;
                return resultSet.getInt( "Coins" );
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            getCoins( player );
        }
        return -1;
    }

    public String getKit( EntityPlayer player ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            ResultSet resultSet = plugin.getMySQL().query( "SELECT * FROM EnderGames WHERE UUID= '" + id + "'" );
            try {
                if( (!resultSet.next()) || (String.valueOf( resultSet.getString( "Kits" ) ) == null) ) ;
                return resultSet.getString( "Kits" );
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            getKit( player );
        }
        return null;
    }

    public List<String> getKits( EntityPlayer player ) {
        String id = player.getUUID().toString();
        String[] data = getKit( player ).split( "~" );
        return new ArrayList<>( Arrays.asList( data ) );
    }


    public Integer getLastKit( EntityPlayer player ) {
        String id = player.getUUID().toString();

        if( playerExists( id ) ) {
            ResultSet resultSet = plugin.getMySQL().query( "SELECT * FROM EnderGames WHERE UUID= '" + id + "'" );
            try {
                if( (!resultSet.next()) || resultSet.getInt( "LastKit" ) == -1 ) ;
                return resultSet.getInt( "LastKit" );
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            getLastKit( player );
        }
        return -1;
    }

    public void setKills( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            plugin.getMySQL().update( "UPDATE EnderGames SET Kills= '" + value + "' WHERE UUID= '" + id + "'" );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            setKills( player, value );
        }
    }

    public void setDeaths( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            plugin.getMySQL().update( "UPDATE EnderGames SET Deaths= '" + value + "' WHERE UUID= '" + id + "'" );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            setDeaths( player, value );
        }
    }

    public void setWins( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            plugin.getMySQL().update( "UPDATE EnderGames SET Wins= '" + value + "' WHERE UUID= '" + id + "'" );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            setWins( player, value );
        }
    }

    public void setGames( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            plugin.getMySQL().update( "UPDATE EnderGames SET Games= '" + value + "' WHERE UUID= '" + id + "'" );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            setGames( player, value );
        }
    }

    public void setCoins( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            plugin.getMySQL().update( "UPDATE EnderGames SET Coins= '" + value + "' WHERE UUID= '" + id + "'" );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            setCoins( player, value );
        }
    }

    public void setKit( EntityPlayer player, String value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            plugin.getMySQL().update( "UPDATE EnderGames SET Kits= '" + value + "' WHERE UUID= '" + id + "'" );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            setKit( player, value );
        }
    }

    public void addKills( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            setKills( player, getKills( player ) + value );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            addKills( player, value );
        }
    }

    public void addDeaths( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            setDeaths( player, getDeaths( player ) + value );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            addDeaths( player, value );
        }
    }

    public void addWins( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            setWins( player, getWins( player ) + value );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            addWins( player, value );
        }
    }

    public void addCoins( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            setCoins( player, getCoins( player ) + value );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            addCoins( player, value );
        }
    }

    public void addKit( EntityPlayer player, String kit ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            setKit( player, getKit( player ) + "~" + kit );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            addKit( player, kit );
        }
    }

    public void removeCoins( EntityPlayer player, int value ) {
        String id = player.getUUID().toString();
        if( playerExists( id ) ) {
            setCoins( player, getCoins( player ) - value );
        } else {
            createPlayer( id, player.getName(), 0, 0,0,0,0,"0",0 );
            removeCoins( player, value );
        }
    }

}
