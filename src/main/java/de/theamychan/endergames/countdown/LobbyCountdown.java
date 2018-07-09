package de.theamychan.endergames.countdown;

import de.theamychan.endergames.EnderGames;
import io.gomint.GoMint;
import io.gomint.scheduler.Task;
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

                switch ( time ) {

                    case 60: case 50: case 40: case 30: case 20: case 10: case 5: case 4: case 3: case 2:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§7Die Runde startet in §e" + time + " §7Sekunden" ) );
                        break;
                    case 1:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§7Die Runde startet in §e" + time + " §7Sekunde" ) );
                        break;
                    case 0:
                        stop();
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + "§eAlle Spieler werden in die Arena teleportiert..." ) );

                        //TODO Teleport
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
