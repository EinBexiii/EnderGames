package de.theamychan.endergames.listener.projectile;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.entity.projectile.ProjectileHitEntityEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProjectileHitEntityListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onProjectileHitEntity( ProjectileHitEntityEvent e ) {
        if(e.getEntity() instanceof EntityPlayer && e.getProjectile().getShooter() instanceof EntityPlayer ){
            EntityPlayer player = (EntityPlayer) e.getEntity();
            EntityPlayer shooter = (EntityPlayer) e.getProjectile().getShooter();
            if( GameState.getGameState().equals( GameState.LOBBY ) || GameState.getGameState().equals( GameState.WAIT ) || GameState.getGameState().equals( GameState.PEACEFUL ) || GameState.getGameState().equals( GameState.RESTART ) ){
                e.setCancelled( true );
            }else{
                if(!plugin.getIngame().contains( player ) || !plugin.getIngame().contains( shooter )){
                    e.setCancelled( true );
                }
            }
        }
    }

}
