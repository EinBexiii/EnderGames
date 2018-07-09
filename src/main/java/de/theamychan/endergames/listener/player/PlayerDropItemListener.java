package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerDropItemEvent;
import io.gomint.world.Gamemode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerDropItemListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onPlayerDropItem( PlayerDropItemEvent e ) {
        EntityPlayer player = e.getPlayer();

        if( GameState.getGameState().equals( GameState.LOBBY ) || GameState.getGameState().equals( GameState.WAIT ) || GameState.getGameState().equals( GameState.RESTART ) ){
            if(!player.getGamemode().equals( Gamemode.CREATIVE )){
                e.setCancelled( true );
            }
        }else{
            if(!plugin.getIngame().contains( player )){
                e.setCancelled( true );
            }
        }
    }

}
