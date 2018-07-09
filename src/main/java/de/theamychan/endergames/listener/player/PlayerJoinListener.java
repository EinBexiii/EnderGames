package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import de.theamychan.endergames.util.ItemBuilder;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerJoinEvent;
import io.gomint.inventory.item.ItemChest;
import io.gomint.world.Gamemode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerJoinListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onPlayerJoin( PlayerJoinEvent e ) {
        EntityPlayer player = e.getPlayer();

        if ( GameState.getGameState().equals( GameState.LOBBY ) ) {
            GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§r" + player.getNameTag() + " §7hat das Spiel betreten" ) );

            player.setHealth( 20 );
            player.setHunger( 20 );
            player.setXP( 0 );
            player.setLevel( 0 );
            player.setGamemode( Gamemode.ADVENTURE );
            player.setOnFire( false );
            player.getArmorInventory().clear();
            player.getInventory().clear();

            player.getInventory().setItem( 0, new ItemBuilder( ItemChest.create( 1 ) ).setCustomName( "§6Kits" ).build() );

            try{
                player.teleport( plugin.getLocationAPI().getLocation( "Lobby", true ) );
            }catch ( Exception ex ) {
                player.teleport( player.getWorld().getSpawnLocation() );
            }

            if(plugin.getIngame().size() >= plugin.getMinPlayers()){
                if(!plugin.getLobbyCountdown().isRunning()){
                    plugin.getLobbyCountdown().start();
                }
            }
        }
    }

}
