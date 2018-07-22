package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.gamestate.GameState;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements EventListener {

    @EventHandler
    public void onPlayerMove( PlayerMoveEvent e ) {
        EntityPlayer player = e.getPlayer();

        if ( GameState.getGameState().equals( GameState.WAIT ) ) {
            if(e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ() || e.getFrom().getY() != e.getTo().getY()) e.setCancelled( true );
        }
    }

}
