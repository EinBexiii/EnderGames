package de.theamychan.endergames.command;

import de.theamychan.endergames.EnderGames;
import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.CommandSender;
import io.gomint.command.PlayerCommandSender;
import io.gomint.command.annotation.*;
import io.gomint.command.validator.IntegerValidator;
import io.gomint.command.validator.StringValidator;
import io.gomint.entity.EntityPlayer;

import java.util.Map;

@Name( "setspawn" )
@Description( "Setze die EnderGames spawns." )
@Permission( "endergames.setspawn" )
@Overload( {
        @Parameter( name = "spectator", validator = StringValidator.class, arguments = {".*"}, optional = true )
} )
@Overload( {
        @Parameter( name = "lobby", validator = StringValidator.class, arguments = {".*"}, optional = true )
} )
@Overload( {
        @Parameter( name = "id", validator = IntegerValidator.class, arguments = {".*"}, optional = true )
} )

public class CommandSetSpawn extends Command {

    @Override
    public CommandOutput execute( CommandSender sender, String alias, Map<String, Object> arguments ) {
        CommandOutput output = new CommandOutput();

        String spectator = (String) arguments.get( "spectator" );
        String lobby = (String) arguments.get( "lobby" );
        Integer id = (Integer) arguments.get( "id" );

        if ( sender instanceof PlayerCommandSender ) {
            EntityPlayer player = (EntityPlayer) sender;

            if( spectator.equals( "" ) ) {
                output.fail( "Usage: /setspawn <spectator>" );
            } else {
                EnderGames.getInstance().getLocationAPI().addLocation( player.getLocation(), "Spectator", true );
                output.success( "Du hast den Spectatorspawn gesetzt!" );
            }

            if( lobby.equals( "" ) ) {
                output.fail( "Usage: /setspawn <lobby>" );
            } else {
                EnderGames.getInstance().getLocationAPI().addLocation( player.getLocation(), "Lobby", true );
                output.success( "Du hast den Lobbyspawn gesetzt!" );
            }

            if( id == null ) {
                output.fail( "Usage: /setspawn <id>" );
            } else {
                EnderGames.getInstance().getLocationAPI().addLocation( player.getLocation(), "Spawn." + id, true );
                output.success( "Du hast den Spawn " + id + " gesetzt!" );
            }
        } else {
            output.fail( "Du musst diesen Command Ingame ausführen!" );
        }
        return output;
    }
}
