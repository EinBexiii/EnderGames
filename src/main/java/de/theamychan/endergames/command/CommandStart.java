package de.theamychan.endergames.command;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.CommandSender;
import io.gomint.command.annotation.Description;
import io.gomint.command.annotation.Name;
import io.gomint.command.annotation.Permission;

import java.util.Map;

@Name( "start" )
@Description( "Starte das Spiel" )
@Permission( "endergames.start" )
public class CommandStart extends Command {

    @Override
    public CommandOutput execute( CommandSender commandSender, String alias, Map<String, Object> arguments ) {
        CommandOutput output = new CommandOutput();

        if ( GameState.getGameState().equals( GameState.LOBBY ) ) {
            if(EnderGames.getInstance().getLobbyCountdown().getTime() >= 5){
                if(EnderGames.getInstance().getLobbyCountdown().isRunning()){
                    EnderGames.getInstance().getLobbyCountdown().setTime( 4 );
                    output.success( EnderGames.getInstance().getPrefix() + "§aDu hast das Spiel gestartet!" );
                }else{
                    EnderGames.getInstance().getLobbyCountdown().start();
                    EnderGames.getInstance().getLobbyCountdown().setTime( 4 );
                    output.success( EnderGames.getInstance().getPrefix() + "§aDu hast das Spiel gestartet!" );
                }
            }else{
                output.fail( EnderGames.getInstance().getPrefix() + "§cDas Spiel startet bereits!" );
            }

        }else{
            output.fail( EnderGames.getInstance().getPrefix() + "§cDas Spiel läuft bereits!" );
        }

            return output;
    }
}