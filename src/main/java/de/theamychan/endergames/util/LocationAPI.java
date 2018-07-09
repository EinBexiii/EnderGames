package de.theamychan.endergames.util;

import de.theamychan.endergames.EnderGames;
import io.gomint.GoMint;
import io.gomint.config.InvalidConfigurationException;
import io.gomint.config.YamlConfig;
import io.gomint.math.Location;
import io.gomint.world.World;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocationAPI extends YamlConfig {

    public LocationAPI( EnderGames plugin ) {
        File file = new File( plugin.getDataFolder(), "location.yml" );
        if ( !file.getParentFile().exists() ) {
            file.getParentFile().mkdirs();
        }
        try {
            this.init( file );
        } catch ( InvalidConfigurationException e ) {
            e.printStackTrace();
        }
    }

    @Getter
    private List<String> list = new ArrayList<>();

    public void addLocation( Location location, String path, boolean direct ) {
        list = new ArrayList<>( list );
        if ( direct ) {
            if ( getLocation( path, true ) == null ) {
                list.add( path + "," + location.getWorld().getWorldName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch() );
            } else {
                removeLocation( getLocation( path, true ), path, true );
                addLocation( location, path, true );
            }
        } else {
            if ( getLocation( path, false ) == null ) {
                list.add( path + "," + location.getWorld().getWorldName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() );
            } else {
                removeLocation( getLocation( path, false ), path, false );
                addLocation( location, path, false );
            }
        }
        try {
            save();
        } catch ( InvalidConfigurationException e ) {
            e.printStackTrace();
        }
    }

    private void removeLocation( Location location, String path, boolean direct ) {
        if ( direct ) {
            list.remove( path + "," + location.getWorld().getWorldName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch() );
        } else {
            list.remove( path + "," + location.getWorld().getWorldName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() );
        }
        try {
            save();
        } catch ( InvalidConfigurationException e ) {
            e.printStackTrace();
        }
    }

    public Location getLocation( String path, boolean direct ) {
        Location location = null;

        for (String data : list) {
            String[] loc = data.split( "," );
            if ( loc[0].equals( path ) ) {
                World world = GoMint.instance().getWorld( loc[1] );
                float x = Float.parseFloat( loc[2] );
                float y = Float.parseFloat( loc[3] );
                float z = Float.parseFloat( loc[4] );

                if ( !direct ) {
                    location = new Location( world, x, y, z );
                } else {
                    float yaw = Float.parseFloat( loc[5] );
                    float pitch = Float.parseFloat( loc[6] );
                    location = new Location( world, x, y, z, yaw, pitch );
                }

            }
        }

        return location;
    }

}
