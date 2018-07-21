package de.theamychan.endergames.manager;

import de.theamychan.endergames.EnderGames;
import io.gomint.entity.EntityPlayer;
import io.gomint.math.Location;
import io.gomint.scheduler.Task;
import io.gomint.world.Sound;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlayerTeleport {

    private EnderGames plugin;

    public PlayerTeleport( EnderGames plugin ) {
        this.plugin = plugin;
    }

    private Task task;

    public void start(){
        task = plugin.getScheduler().schedule( () -> {

            EntityPlayer player1 = plugin.getIngame().get( new Random().nextInt(plugin.getIngame().size() - 1) );
            EntityPlayer player2 = plugin.getIngame().get( new Random().nextInt(plugin.getIngame().size() - 1) );

            Location loc1 = player1.getLocation();
            Location loc2 = player2.getLocation();

            if(player1 != player2){
                player1.teleport( loc2 );
                player1.playSound( player1.getLocation(), Sound.TELEPORT, (byte) 1 );
                player2.teleport( loc1 );
                player2.playSound( player2.getLocation(), Sound.TELEPORT, (byte) 1 );
            }

        }, plugin.randomInt( 60, 180 ), TimeUnit.SECONDS );
    }

    public void stop(){
        task.cancel();
    }

}
