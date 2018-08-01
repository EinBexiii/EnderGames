package de.theamychan.endergames.countdown;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.scheduler.Task;
import io.gomint.world.Gamemode;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

public class LobbyCountdown {

    @Getter @Setter
    private int time = 61;
    private Task task;
    @Getter
    private boolean isRunning = false;

    private EnderGames plugin;

    public LobbyCountdown( EnderGames plugin ) {
        this.plugin = plugin;
    }

    public void start(){
        isRunning = true;

        task = plugin.getScheduler().schedule( () -> {

            if(time != 0){
                time--;
                GoMint.instance().getPlayers().forEach( all -> all.setLevel( time ) );
                switch ( time ) {
                    case 60: case 50: case 40: case 30: case 20: case 10: case 5: case 4: case 3: case 2:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-lobby-seconds", time ) ) );
                        break;
                    case 1:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-lobby-second", time ) ) );
                        break;
                    case 0:
                        stop();
                        int id = 0;
                        for(EntityPlayer player : GoMint.instance().getPlayers()){
                            player.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( player.getLocale(), "countdown-lobby-teleport" ) );
                            id++;
                            player.teleport( plugin.getLocationAPI().getLocation( "Spawn." + id, true ) );
                            player.getInventory().clear();
                            player.setGamemode( Gamemode.SURVIVAL );
                        }
                        GameState.setGameState( GameState.WAIT );

                        plugin.getWaitCountdown().start();
                        plugin.getPlayerTeleport().startTeleport();

                        break;
                        default:
                            break;
                }
            }
        }, 0, 1, TimeUnit.SECONDS );
    }

    public void stop(){
        isRunning = false;
        task.cancel();
        time = 61;
        GoMint.instance().getPlayers().forEach( all -> all.setLevel( 0 ) );
    }

}
