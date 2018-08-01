package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerDeathEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerDeathListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onPlayerDeath( PlayerDeathEvent e ) {
        EntityPlayer player = e.getPlayer();
        EntityPlayer killer = plugin.getLastDamager().get( player );
        e.setDeathMessage( null );

        if ( killer != null ) {
            GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§r" + player.getNameTag() + " §7wurde von §r" + killer.getNameTag() + " §7getötet" ) );
        } else {
            GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§r" + player.getNameTag() + " §7ist gestorben" ) );
        }

        if ( plugin.getIngame().contains( player ) ) plugin.getIngame().remove( player );
        if ( !plugin.getSpectator().contains( player ) ) plugin.getSpectator().add( player );

        if ( plugin.getIngame().size() == 1 ) {
            EntityPlayer winner = plugin.getIngame().get( 0 );
            for (EntityPlayer all : GoMint.instance().getPlayers()) {
                all.sendTitle( plugin.getLocaleManager().translate( all.getLocale(), "player-winner-title", winner.getNameTag() ) );
                all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "player-winner-message", winner.getNameTag() ) );
            }

            GameState.setGameState( GameState.RESTART );
            plugin.getRestartCountdown().start();
        }

    }

}
