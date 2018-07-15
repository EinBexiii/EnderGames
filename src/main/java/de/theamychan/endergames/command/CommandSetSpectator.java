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

@Name( "eg setspectator" )
@Description( "Setzte den Spectatorspawn" )
@Permission( "endergames.setspectator" )
public class CommandSetSpectator extends Command {

    @Override
    public CommandOutput execute( CommandSender commandSender, String alias, Map<String, Object> arguments ) {
        CommandOutput output = new CommandOutput();

        if ( commandSender instanceof PlayerCommandSender ) {
            EntityPlayer player = (EntityPlayer) commandSender;

            EnderGames.getInstance().getLocationAPI().addLocation( player.getLocation(), "Spectator", true );
            output.success( "Du hast den Spectatorspawn gesetzt!" );
            
        }

        return output;
    }
}
