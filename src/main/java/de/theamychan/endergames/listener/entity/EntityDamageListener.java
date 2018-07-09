package de.theamychan.endergames.listener.entity;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.entity.EntityDamageEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityDamageListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onEntityDamage( EntityDamageEvent e ) {
        if(e.getEntity() instanceof EntityPlayer ) {
            EntityPlayer player = (EntityPlayer) e.getEntity();

            if( GameState.getGameState().equals( GameState.LOBBY ) || GameState.getGameState().equals( GameState.WAIT ) || GameState.getGameState().equals( GameState.PEACEFUL ) || GameState.getGameState().equals( GameState.RESTART ) ){
                e.setCancelled( true );
            }else{
                if(!plugin.getIngame().contains( player )){
                    e.setCancelled( true );
                }
            }

        }
    }

}
