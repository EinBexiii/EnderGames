package de.theamychan.endergames.manager;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.entity.EntityPlayer;
import io.gomint.math.Location;
import io.gomint.scheduler.Task;
import io.gomint.world.Sound;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlayerTeleport {

    /**
     * Prototype
     */

    private EnderGames plugin;

    public PlayerTeleport( EnderGames plugin ) {
        this.plugin = plugin;
    }

    private Task task;

    public void startTeleport() {
        if(plugin.getTeleport().size() > 1){
            task = plugin.getScheduler().schedule( () -> {

                EntityPlayer player1 = plugin.getTeleport().get( new Random().nextInt( plugin.getTeleport().size() ) );
                plugin.getTeleport().remove( player1 );
                EntityPlayer player2 = plugin.getTeleport().get( new Random().nextInt( plugin.getTeleport().size() ) );
                plugin.getTeleport().remove( player2 );

                Location loc1 = player1.getLocation();
                Location loc2 = player2.getLocation();

                if ( player1 != player2 ) {
                    player1.teleport( loc2 );
                    player1.playSound( player1.getLocation(), Sound.TELEPORT, (byte) 1 );
                    player1.sendMessage( plugin.getPrefix() + "§7Du wurdest mit §r" + player2.getNameTag() + " §7getauscht" );
                    plugin.getTeleport().add( player1 );
                    player2.teleport( loc1 );
                    player2.playSound( player2.getLocation(), Sound.TELEPORT, (byte) 1 );
                    player2.sendMessage( plugin.getPrefix() + "§7Du wurdest mit §r" + player1.getNameTag() + " §7getauscht" );
                    plugin.getTeleport().add( player2 );
                }

                startTeleport();
            }, GameState.getGameState() == GameState.WAIT ? 1 : plugin.randomInt( 45, 120 ), TimeUnit.SECONDS );
        }
    }

    public void stop() {
        task.cancel();
    }

}
