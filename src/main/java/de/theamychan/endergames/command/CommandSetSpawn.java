package de.theamychan.endergames.command;

import de.theamychan.endergames.EnderGames;
import io.gomint.command.Command;
import io.gomint.command.CommandOutput;
import io.gomint.command.CommandSender;
import io.gomint.command.PlayerCommandSender;
import io.gomint.command.annotation.*;
import io.gomint.command.validator.IntegerValidator;
import io.gomint.entity.EntityPlayer;

import java.util.Map;

@Name("eg setspawn")
@Description("Setzte den Spawn")
@Permission( "endergames.setspawn" )
@Overload({
        @Parameter( name = "spawn", validator = IntegerValidator.class, arguments = {".*"}, optional = true )
})
public class CommandSetSpawn extends Command {

    @Override
    public CommandOutput execute( CommandSender commandSender, String alias, Map<String, Object> arguments ) {
        CommandOutput output = new CommandOutput();

        if(commandSender instanceof PlayerCommandSender ){
            EntityPlayer player = (EntityPlayer) commandSender;
            Integer id = (Integer) arguments.get( "spawn" );

            EnderGames.getInstance().getLocationAPI().addLocation( player.getLocation(), "Spawn." + id, true );
            output.success( "Du hast den Spawn " + id + " gesetzt!" );

        }

        return output;
    }
}
