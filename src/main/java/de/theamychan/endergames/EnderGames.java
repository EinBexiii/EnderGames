package de.theamychan.endergames;

import de.theamychan.endergames.countdown.LobbyCountdown;
import de.theamychan.endergames.gamestate.GameState;
import de.theamychan.endergames.listener.entity.EntityDamageByDamageListener;
import de.theamychan.endergames.listener.entity.EntityDamageListener;
import de.theamychan.endergames.listener.inventory.InventoryTransactionListener;
import de.theamychan.endergames.listener.player.PlayerDropItemListener;
import de.theamychan.endergames.listener.player.PlayerFoodLevelChangeListener;
import de.theamychan.endergames.listener.player.PlayerJoinListener;
import de.theamychan.endergames.util.LocationAPI;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.plugin.Plugin;
import io.gomint.plugin.PluginName;
import io.gomint.plugin.Version;
import io.gomint.world.World;
import io.gomint.world.WorldType;
import io.gomint.world.generator.CreateOptions;
import io.gomint.world.generator.integrated.NormalGenerator;
import lombok.Getter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@PluginName( "EnderGames" )
@Version( major = 1, minor = 0 )
public class EnderGames extends Plugin {

    @Getter
    private static EnderGames instance;
    @Getter
    private LocationAPI locationAPI;

    @Getter
    private LobbyCountdown lobbyCountdown;

    @Getter
    private World world;

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
        try {
            if(new File( "Arena" ).exists()){
                this.getLogger().info( "EnderGames Arena wird gelöscht..." );
                FileUtils.deleteDirectory( new File( "Arena" ) );
                this.getLogger().info( "EnderGames Arena wurde gelöscht!" );
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        if(!new File( "Arena" ).exists()){
            this.getLogger().info( "EnderGames Arena wird erstellt..." );
            GoMint.instance().createWorld( "Arena", new CreateOptions().worldType( WorldType.GOMINT ).generator( NormalGenerator.class ) );
            this.getLogger().info( "EnderGames Arena wurde erstellt!" );
        }

        this.locationAPI = new LocationAPI( this );

        //Countdown
        this.lobbyCountdown = new LobbyCountdown( this );

        //Entity
        this.registerListener( new EntityDamageListener( this ) );
        this.registerListener( new EntityDamageByDamageListener( this ) );

        //Player
        this.registerListener( new PlayerJoinListener( this ) );
        this.registerListener( new PlayerDropItemListener( this ) );
        this.registerListener( new PlayerFoodLevelChangeListener( this ) );

        //Inventory
        this.registerListener( new InventoryTransactionListener( this ) );
    }

    @Override
    public void onUninstall() {

    }

}
