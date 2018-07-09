package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerFoodLevelChangeEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerFoodLevelChangeListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onPlayerFoodLevelChange( PlayerFoodLevelChangeEvent e ) {
        EntityPlayer player = e.getPlayer();

        if( GameState.getGameState().equals( GameState.LOBBY ) || GameState.getGameState().equals( GameState.WAIT ) || GameState.getGameState().equals( GameState.PEACEFUL ) || GameState.getGameState().equals( GameState.RESTART ) ){
            e.setCancelled( true );
        }else{
            if(!plugin.getIngame().contains( player )){
                e.setCancelled( true );
            }
        }
    }

}
