package de.theamychan.endergames.border;

import de.theamychan.endergames.EnderGames;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.entity.EntityDamageEvent;
import io.gomint.math.Location;
import io.gomint.math.Vector;
import io.gomint.world.Particle;
import io.gomint.world.Sound;
import io.gomint.world.SoundData;
import io.gomint.world.block.BlockWoodenDoor;

import java.util.concurrent.TimeUnit;

public class WorldBorder {

    private EnderGames plugin;

    public WorldBorder( EnderGames plugin ) {
        this.plugin = plugin;
    }

    public void start() {

        plugin.getScheduler().schedule( () -> {

            for (EntityPlayer player : GoMint.instance().getPlayers()) {
                if ( player.getLocation().distance( player.getWorld().getSpawnLocation() ) >= plugin.getRadius() ) {
                    Vector plV = player.getLocation();
                    Vector spV = player.getWorld().getSpawnLocation();
                    Vector vector = spV.subtract( plV ).multiply( (float) (1.0 / spV.distance( plV )) );
                    vector.setY( (float) 0.5 );
                    player.setVelocity( vector );
                    player.playSound( player.getLocation(), Sound.BREAK, (byte) 1, SoundData.block( BlockWoodenDoor.class ) );
                    player.sendMessage( plugin.getPrefix() + "§cDu hast das §eEnde der Welt §cerreicht " );
                    player.attack( 2, EntityDamageEvent.DamageSource.ENTITY_ATTACK );
                }
            }

        }, 0, 1, TimeUnit.MILLISECONDS );

        plugin.getScheduler().schedule( () -> {

            for (EntityPlayer player : GoMint.instance().getPlayers()) {
                if ( player.getLocation().distance( player.getWorld().getSpawnLocation() ) >= plugin.getRadius() - 15 ) {
                    Location min = player.getLocation().add( -10, -10, -10 );
                    Location max = player.getLocation().add( 10, 10, 10 );

                    for (int x = (int) min.getBlock().getLocation().getX(); x < max.getBlock().getLocation().getX(); x++) {
                        for (int y = (int) min.getBlock().getLocation().getY(); y < max.getBlock().getLocation().getY(); y++) {
                            for (int z = (int) min.getBlock().getLocation().getZ(); z < max.getBlock().getLocation().getZ(); z++) {
                                Location location = new Location( player.getWorld(), x, y, z );
                                if ( location.distance( player.getWorld().getSpawnLocation() ) > plugin.getRadius() && location.distance( player.getWorld().getSpawnLocation() ) < plugin.getRadius() + 4 ) {
                                    if(plugin.randomInt( 0, 20 ) == 0 || plugin.randomInt( 0, 30 ) == 5){
                                        player.getWorld().sendParticle( location, Particle.PORTAL );
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }, 0, 15, TimeUnit.MILLISECONDS );
    }
}
