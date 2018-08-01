package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerQuitEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerQuitListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onPlayerQuit( PlayerQuitEvent e ){
        EntityPlayer player = e.getPlayer();

        if(GameState.getGameState().equals( GameState.LOBBY ) ){
            GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "player-quit", player.getNameTag()) ) );
            if(plugin.getIngame().contains( player )) plugin.getIngame().remove( player );
            if(plugin.getSpectator().contains( player )) plugin.getSpectator().remove( player );
            if(plugin.getTeleport().contains( player )) plugin.getTeleport().remove( player );

            if(plugin.getIngame().size() < plugin.getMinPlayers()){
                if(plugin.getLobbyCountdown().isRunning()){
                    plugin.getLobbyCountdown().stop();
                }
            }

        }else{
            GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( player.getLocale(), "player-death-message", player.getNameTag() ) ) );
            if(plugin.getIngame().contains( player )) plugin.getIngame().remove( player );
            if(plugin.getSpectator().contains( player )) plugin.getSpectator().remove( player );

            if(plugin.getIngame().size() == 1){
                EntityPlayer winner = plugin.getIngame().get( 0 );
                for(EntityPlayer all : GoMint.instance().getPlayers()){
                    all.sendTitle( plugin.getLocaleManager().translate( all.getLocale(), "player-winner-title", winner.getNameTag() ) );
                    all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "player-winner-message", winner.getNameTag() ) );
                }

                GameState.setGameState( GameState.RESTART );
                plugin.getRestartCountdown().start();
            }

        }

    }

}
