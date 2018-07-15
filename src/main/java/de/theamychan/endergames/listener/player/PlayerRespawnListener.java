package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.util.ItemBuilder;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerRespawnEvent;
import io.gomint.inventory.item.ItemCompass;
import io.gomint.world.Gamemode;
import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class PlayerRespawnListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onPlayerRespawn( PlayerRespawnEvent e ) {
        EntityPlayer player = e.getPlayer();

        if ( plugin.getSpectator().contains( player ) ) {
            e.setRespawnLocation( plugin.getLocationAPI().getLocation( "Spectator", true ) );

            player.setGamemode( Gamemode.ADVENTURE );

            player.setAllowFlight( true );
            player.setFlying( true );

            player.teleport( e.getRespawnLocation() );
            player.getInventory().setItem( 0, new ItemBuilder( ItemCompass.create( 1 ) ).setCustomName( "§6Teleporter" ).build() );

            plugin.getScheduler().scheduleAsync( () -> {

                for (EntityPlayer ingamePlayers : plugin.getIngame()) {
                    ingamePlayers.hidePlayer( player );
                }
            }, 500, TimeUnit.MILLISECONDS );


        }


    }

}
