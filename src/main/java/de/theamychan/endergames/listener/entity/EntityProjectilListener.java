package de.theamychan.endergames.listener.entity;

import de.theamychan.endergames.gamestate.GameState;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.entity.projectile.ProjectileHitEntityEvent;

public class EntityProjectilListener implements EventListener {

    @EventHandler
    public void onProjectilHit( ProjectileHitEntityEvent e ){

        if(GameState.getGameState().equals( GameState.PEACEFUL ) || GameState.getGameState().equals( GameState.INGAME ) ){

        }
    }

}
