package de.theamychan.endergames.command;

import de.theamychan.endergames.EnderGames;
import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.CommandSender;
import io.gomint.command.PlayerCommandSender;
import io.gomint.command.annotation.Description;
import io.gomint.command.annotation.Name;
import io.gomint.command.annotation.Permission;
import io.gomint.entity.EntityPlayer;

import java.util.Map;

@Name( "eg setlobby" )
@Description( "Setzte den EnderGames Lobbyspawn" )
@Permission( "endergames.setspawn" )
public class CommandSetLobby extends Command {

    @Override
    public CommandOutput execute( CommandSender sender, String alias, Map<String, Object> arguments ) {
        CommandOutput output = new CommandOutput();

        if ( sender instanceof PlayerCommandSender ) {
            EntityPlayer player = (EntityPlayer) sender;

            EnderGames.getInstance().getLocationAPI().addLocation( player.getLocation(), "Lobby", true );
            output.success( "Du hast den Lobbyspawn gesetzt!" );

        } else {
            output.fail( "Du musst diesen Command Ingame ausführen!" );
        }

            return output;
    }
}
