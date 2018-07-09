package de.theamychan.endergames;

import de.theamychan.endergames.gamestate.GameState;
import de.theamychan.endergames.listener.PlayerJoinListener;
import io.gomint.entity.EntityPlayer;
import io.gomint.plugin.Plugin;
import io.gomint.plugin.PluginName;
import io.gomint.plugin.Version;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@PluginName( "EnderGames" )
@Version( major = 1, minor = 0 )
public class EnderGames extends Plugin {

    @Getter
    private static EnderGames instance;

    @Getter
    private String prefix = "§f[§5EnderGames§f] ";

    @Getter
    private int minPlayers = 1;
    @Getter
    private int maxPlayers = 16;

    @Getter
    private List<EntityPlayer> ingame = new ArrayList<>();
    @Getter
    private List<EntityPlayer> spectator = new ArrayList<>();

    @Override
    public void onInstall() {
        instance = this;

        GameState.setGameState( GameState.LOBBY );

        this.registerListener( new PlayerJoinListener( this ) );
    }

    @Override
    public void onUninstall() {

    }

}
