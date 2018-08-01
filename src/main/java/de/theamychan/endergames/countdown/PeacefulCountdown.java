package de.theamychan.endergames.countdown;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.GoMint;
import io.gomint.math.Location;
import io.gomint.scheduler.Task;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PeacefulCountdown {

    private int time = 61;
    private Task task;
    private boolean isRunning = false;

    private EnderGames plugin;

    public PeacefulCountdown( EnderGames plugin ) {
        this.plugin = plugin;
    }

    public void start(){
        task = plugin.getScheduler().schedule( () -> {

            if(time != 0){
                time--;

                switch ( time ) {

                    case 60: case 45: case 30: case 15: case 10:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-peacefull-seconds", time ) ) );
                        break;
                    case 5:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-peacefull-seconds", time ) ) );
                        this.plugin.getSchematicSystem().getSchematicManager().destroy( this.plugin.getWorld().getSpawnLocation().add( 0, 50 , 0 ),"EnderGames", success -> {
                            if(success){
                                this.plugin.getLogger().info( plugin.getLocaleManager().translate( Locale.GERMANY, "schematic-destroy-success" ) );
                            }else{
                                this.plugin.getLogger().info( plugin.getLocaleManager().translate( Locale.GERMANY, "schematic-destroy-fail" ) );
                            }
                        } );
                        break;
                    case 4: case 3: case 2:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-peacefull-seconds", time ) ) );
                        break;
                    case 1:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-peacefull-second", time ) ) );
                        break;
                    case 0:
                        stop();
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-peaceful-end" ) ) );
                        GameState.setGameState( GameState.INGAME );
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
    }

}
