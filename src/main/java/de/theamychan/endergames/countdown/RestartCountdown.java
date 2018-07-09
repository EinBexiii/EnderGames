package de.theamychan.endergames.countdown;

import de.theamychan.endergames.EnderGames;
import io.gomint.GoMint;
import io.gomint.scheduler.Task;

import java.util.concurrent.TimeUnit;

public class RestartCountdown {

    private int time = 16;
    private Task task;
    private boolean isRunning = false;

    private EnderGames plugin;

    public RestartCountdown( EnderGames plugin ) {
        this.plugin = plugin;
    }

    public void start(){
        isRunning = true;
        task = plugin.getScheduler().schedule( () -> {

            if(time != 0){
                time--;

                switch ( time ) {

                    case 15: case 10: case 5: case 4: case 3: case 2:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§cDer Server startet in §e" + time + " §cSekunden neu" ) );
                        break;

                    case 1:
                        GoMint.instance().getPlayers().forEach( all -> all.disconnect( "§cServer restart!" ) );
                        break;

                    case 0:
                        stop();
                        plugin.getServer().shutdown();
                        break;
                        default:
                            break;

                }
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    public void stop(){
        isRunning = false;
        task.cancel();
        time = 16;
    }

}
