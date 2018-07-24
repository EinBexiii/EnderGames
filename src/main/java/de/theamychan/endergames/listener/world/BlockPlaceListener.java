package de.theamychan.endergames.listener.world;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.world.BlockPlaceEvent;
import io.gomint.world.Gamemode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BlockPlaceListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onBlockPlace( BlockPlaceEvent e ) {
        EntityPlayer player = e.getPlayer();

        if(GameState.getGameState().equals( GameState.LOBBY ) || GameState.getGameState().equals( GameState.WAIT ) || GameState.getGameState().equals( GameState.RESTART ) ) {
            if(!player.getGamemode().equals( Gamemode.CREATIVE )) {
                e.setCancelled( true );
            }else{
                e.setCancelled( false );
            }
        }else{
            if(plugin.getIngame().contains( player )){
                e.setCancelled( false );
                System.out.println(1 );
            }else{
                e.setCancelled( true );
                System.out.println( 2 );
            }
        }
    }

}
