package de.theamychan.endergames.listener.entity;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.entity.EntityPlayer;
import io.gomint.entity.active.EntityPrimedTNT;
import io.gomint.entity.projectile.EntityArrow;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.entity.EntityDamageByEntityEvent;
import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class EntityDamageByDamageListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onEntityDamageByEntity( EntityDamageByEntityEvent e ) {
        if(e.getEntity() instanceof EntityPlayer && e.getAttacker() instanceof EntityPlayer ) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            EntityPlayer damager = (EntityPlayer) e.getAttacker();

            if( GameState.getGameState().equals( GameState.LOBBY ) || GameState.getGameState().equals( GameState.WAIT ) || GameState.getGameState().equals( GameState.PEACEFUL ) || GameState.getGameState().equals( GameState.RESTART ) ){
                e.setCancelled( true );
            }else{
                if(!plugin.getIngame().contains( player ) || !plugin.getIngame().contains( damager )){
                    e.setCancelled( true );
                }
            }
            plugin.getLastDamager().put( player, damager );

            plugin.getScheduler().scheduleAsync( ( ) -> plugin.getLastDamager().remove( player ), 30, TimeUnit.SECONDS );
        }else if(e.getAttacker() instanceof EntityArrow ){
            if(GameState.getGameState().equals( GameState.PEACEFUL )){
                e.setCancelled( true );
            }else{
                if(((EntityArrow) e.getAttacker()).getShooter() instanceof EntityPlayer){
                    EntityPlayer player = (EntityPlayer) e.getEntity();
                    EntityPlayer shootPlayer = (EntityPlayer) ((EntityArrow) e.getAttacker()).getShooter();

                    plugin.getLastDamager().put( player, shootPlayer );
                    plugin.getScheduler().scheduleAsync( ( ) -> plugin.getLastDamager().remove( player ), 15, TimeUnit.SECONDS );
                }
            }

        }else if(e.getAttacker() instanceof EntityPrimedTNT){
            if(GameState.getGameState().equals( GameState.PEACEFUL )){
                e.setCancelled( true );
            }
        }
    }

}
