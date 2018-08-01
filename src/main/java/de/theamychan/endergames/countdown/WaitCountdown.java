package de.theamychan.endergames.countdown;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import de.theamychan.endergames.util.ItemBuilder;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.inventory.item.ItemCompass;
import io.gomint.scheduler.Task;
import io.gomint.world.Sound;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

public class WaitCountdown {

    private int time = 11;
    private Task task;
    @Getter
    private boolean isRunning = false;

    private EnderGames plugin;

    public WaitCountdown( EnderGames plugin ) {
        this.plugin = plugin;
    }

    public void start(){
        isRunning = true;

        task = plugin.getScheduler().schedule( () -> {

            if(time != 0){
                time--;

                switch ( time ) {

                    case 10: case 5: case 4:
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-wait-seconds", time ) ) );
                        break;
                    case 3:
                        GoMint.instance().getPlayers().forEach( all -> all.playSound( all.getLocation(), Sound.NOTE, (byte) 2 ) );
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-wait-seconds", time ) ) );
                        break;
                    case 2:
                        GoMint.instance().getPlayers().forEach( all -> all.playSound( all.getLocation(), Sound.NOTE, (byte) 2 ) );
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-wait-seconds", time ) ) );
                        break;
                    case 1:
                        GoMint.instance().getPlayers().forEach( all -> all.playSound( all.getLocation(), Sound.NOTE, (byte) 2 ) );
                        GoMint.instance().getPlayers().forEach( all -> all.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( all.getLocale(), "countdown-wait-second", time ) ) );
                        break;
                    case 0:
                        stop();
                        for(EntityPlayer player : GoMint.instance().getPlayers()){
                            player.playSound( player.getLocation(), Sound.NOTE, (byte) 3 );
                            player.getInventory().setItem( 0, new ItemBuilder( ItemCompass.create( 1 ) ).setCustomName( "§5Tracker" ).build() );
                            if(plugin.getKitManager().getKit( player ) != null){
                                plugin.getKitManager().getKit( player ).setContent( player );
                            }else{
                                plugin.getKitManager().setKit( player, plugin.getKitManager().getKitBabar() );
                                plugin.getKitManager().getKit( player ).setContent( player );
                            }
                            player.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( player.getLocale(), "countdown-wait-start" ) );
                            player.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( player.getLocale(), "kit-selected", plugin.getKitManager().getKit( player ).getName() ));
                        }
                        GameState.setGameState( GameState.PEACEFUL );
                        plugin.getPeacefulCountdown().start();
                        plugin.getWorldBorder().start();
                        plugin.getChestTeleportManager().start();
                        plugin.getSpeedBlockTeleport().start();

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
        time = 11;
    }
}
