package de.theamychan.endergames.countdown;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.GoMint;
import io.gomint.math.Location;
import io.gomint.scheduler.Task;

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
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§7Die Schutzzeit endet in §e" + time + " §7Sekunden" ) );
                        break;
                    case 5:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§7Die Schutzzeit endet in §e" + time + " §7Sekunden" ) );
                        this.plugin.getSchematicSystem().getSchematicManager().destroy( this.plugin.getWorld().getSpawnLocation().add( 0, 50 , 0 ),"EnderGames", success -> {
                            if(success){
                                this.plugin.getLogger().info( "Das Schematic wurde entfernt!" );
                            }else{
                                this.plugin.getLogger().info( "Das Schematic konnte nicht entfernt werden!" );
                            }
                        } );
                        break;
                    case 4: case 3: case 2:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§7Die Schutzzeit endet in §e" + time + " §7Sekunden" ) );
                        break;
                    case 1:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§7Die Schutzzeit endet in §e" + time + " §7Sekunde" ) );
                        break;

                    case 0:
                        stop();
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§eDie Schutzzeit ist vorbei!" ) );
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
