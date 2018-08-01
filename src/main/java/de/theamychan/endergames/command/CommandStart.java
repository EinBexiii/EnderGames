package de.theamychan.endergames.command;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.CommandSender;
import io.gomint.command.PlayerCommandSender;
import io.gomint.command.annotation.Description;
import io.gomint.command.annotation.Name;
import io.gomint.command.annotation.Permission;
import io.gomint.entity.EntityPlayer;

import java.util.Map;

@Name( "start" )
@Description( "Starte das Spiel" )
@Permission( "endergames.start" )
public class CommandStart extends Command {

    @Override
    public CommandOutput execute( CommandSender commandSender, String alias, Map<String, Object> arguments ) {
        CommandOutput output = new CommandOutput();

        if(commandSender instanceof PlayerCommandSender){
            EntityPlayer player = (EntityPlayer) commandSender;
            if ( GameState.getGameState().equals( GameState.LOBBY ) ) {
                if(EnderGames.getInstance().getLobbyCountdown().getTime() >= 5){
                    if(EnderGames.getInstance().getLobbyCountdown().isRunning()){
                        EnderGames.getInstance().getLobbyCountdown().setTime( 4 );
                        output.success( EnderGames.getInstance().getPrefix() + EnderGames.getInstance().getLocaleManager().translate( player.getLocale(), "command-start-success" ) );
                    }else{
                        EnderGames.getInstance().getLobbyCountdown().start();
                        EnderGames.getInstance().getLobbyCountdown().setTime( 4 );
                        output.success( EnderGames.getInstance().getPrefix() + EnderGames.getInstance().getLocaleManager().translate( player.getLocale(), "command-start-success" ) );
                    }
                }else{
                    output.fail( EnderGames.getInstance().getPrefix() + EnderGames.getInstance().getLocaleManager().translate( player.getLocale(), "command-start-fail" ) );
                }
            }else{
                output.fail( EnderGames.getInstance().getPrefix() + EnderGames.getInstance().getLocaleManager().translate( player.getLocale(), "command-start-fail2" ) );
            }
        }
            return output;
    }
}