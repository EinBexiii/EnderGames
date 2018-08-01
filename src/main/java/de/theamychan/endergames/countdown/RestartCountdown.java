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
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-restart-seconds", time ) ) );
                        break;
                    case 1:
                        if(plugin.getConfig().isTransferAfterMatch()){
                            GoMint.instance().getPlayers().forEach( all -> all.transfer( plugin.getConfig().getAddress(), plugin.getConfig().getPort() ) );
                        }else{
                            GoMint.instance().getPlayers().forEach( all -> all.disconnect( plugin.getLocaleManager().translate( all.getLocale(), "countdown-restart-kick" ) ) );
                        }
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
