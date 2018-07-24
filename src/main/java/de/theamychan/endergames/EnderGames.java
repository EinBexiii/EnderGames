package de.theamychan.endergames;

import de.theamychan.endergames.border.WorldBorder;
import de.theamychan.endergames.countdown.LobbyCountdown;
import de.theamychan.endergames.countdown.PeacefulCountdown;
import de.theamychan.endergames.countdown.RestartCountdown;
import de.theamychan.endergames.countdown.WaitCountdown;
import de.theamychan.endergames.gamestate.GameState;
import de.theamychan.endergames.kit.manager.KitManager;
import de.theamychan.endergames.listener.entity.EntityDamageByDamageListener;
import de.theamychan.endergames.listener.entity.EntityDamageListener;
import de.theamychan.endergames.listener.entity.EntityExplodeListener;
import de.theamychan.endergames.listener.inventory.InventoryTransactionListener;
import de.theamychan.endergames.listener.player.*;
import de.theamychan.endergames.listener.projectile.ProjectileHitEntityListener;
import de.theamychan.endergames.listener.world.BlockBreakListener;
import de.theamychan.endergames.listener.world.BlockPlaceListener;
import de.theamychan.endergames.manager.ChestManager;
import de.theamychan.endergames.manager.ChestTeleport;
import de.theamychan.endergames.manager.PlayerTeleport;
import de.theamychan.endergames.manager.SpeedBlockTeleport;
import de.theamychan.endergames.util.LocationAPI;
import de.theamychan.schematic.SchematicSystem;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.inventory.item.ItemStack;
import io.gomint.math.Location;
import io.gomint.plugin.Depends;
import io.gomint.plugin.Plugin;
import io.gomint.plugin.PluginName;
import io.gomint.plugin.Version;
import io.gomint.world.World;
import io.gomint.world.block.Block;
import io.gomint.world.generator.CreateOptions;
import io.gomint.world.generator.integrated.NormalGenerator;
import lombok.Getter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Depends( "SchematicSystem" )
@PluginName( "EnderGames" )
@Version( major = 1, minor = 0 )
public class EnderGames extends Plugin {

    @Getter
    private static EnderGames instance;

    @Getter
    private SchematicSystem schematicSystem;
    @Getter
    private LocationAPI locationAPI;
    @Getter
    private WorldBorder worldBorder;
    @Getter
    private ChestManager chestManager;
    @Getter
    private ChestTeleport chestTeleportManager;
    @Getter
    private SpeedBlockTeleport speedBlockTeleport;
    @Getter
    private KitManager kitManager;
    @Getter
    private PlayerTeleport playerTeleport;

    @Getter
    private LobbyCountdown lobbyCountdown;
    @Getter
    private WaitCountdown waitCountdown;
    @Getter
    private PeacefulCountdown peacefulCountdown;
    @Getter
    private RestartCountdown restartCountdown;

    @Getter
    private World world;

    @Getter
    private String prefix = "§f[§5EnderGames§f] ";

    @Getter
    private int minPlayers = 2;
    @Getter
    private int maxPlayers = 24;
    @Getter
    private int radius = 350;

    @Getter
    private List<EntityPlayer> ingame = new ArrayList<>();
    @Getter
    private List<EntityPlayer> teleport = new ArrayList<>();
    @Getter
    private List<EntityPlayer> spectator = new ArrayList<>();

    @Getter
    private Map<EntityPlayer, EntityPlayer> lastDamager = new HashMap<>();
    @Getter
    private Map<Block, List<ItemStack>> itemsMap = new HashMap<>();

    @Override
    public void onInstall() {
        instance = this;
        schematicSystem = SchematicSystem.getInstance();

        GameState.setGameState( GameState.LOBBY );

        this.locationAPI = new LocationAPI( this );
        this.worldBorder = new WorldBorder( this );
        this.chestManager = new ChestManager( this );
        this.chestTeleportManager = new ChestTeleport( this );
        this.speedBlockTeleport = new SpeedBlockTeleport( this );
        this.kitManager = new KitManager( this );
        this.playerTeleport = new PlayerTeleport( this );

        //Countdown
        this.lobbyCountdown = new LobbyCountdown( this );
        this.waitCountdown = new WaitCountdown( this );
        this.peacefulCountdown = new PeacefulCountdown( this );
        this.restartCountdown = new RestartCountdown( this );

        //World
        this.registerListener( new BlockBreakListener( this ) );
        this.registerListener( new BlockPlaceListener( this ) );

        //Entity
        this.registerListener( new EntityDamageListener( this ) );
        this.registerListener( new EntityDamageByDamageListener( this ) );
        this.registerListener( new EntityExplodeListener() );

        //Player
        this.registerListener( new PlayerJoinListener( this ) );
        this.registerListener( new PlayerDropItemListener( this ) );
        this.registerListener( new PlayerFoodLevelChangeListener( this ) );
        this.registerListener( new PlayerDeathListener( this ) );
        this.registerListener( new PlayerQuitListener( this ) );
        this.registerListener( new PlayerRespawnListener( this ) );
        this.registerListener( new PlayerInteractListener( this ) );
        this.registerListener( new PlayerMoveListener() );

        //Inventory
        this.registerListener( new InventoryTransactionListener( this ) );

        //Projectile
        this.registerListener( new ProjectileHitEntityListener( this ) );

        try {
            if ( new File( "Arena" ).exists() ) {
                this.getLogger().info( "EnderGames Arena wird gelöscht..." );
                FileUtils.deleteDirectory( new File( "Arena" ) );
                this.getLogger().info( "EnderGames Arena wurde gelöscht!" );
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        if ( !new File( "Arena" ).exists() ) {
            this.getLogger().info( "EnderGames Arena wird erstellt..." );
            this.world = GoMint.instance().createWorld( "Arena", new CreateOptions().generator( NormalGenerator.class ) );
            this.getLogger().info( "EnderGames Arena wurde erstellt!" );
            Location location = this.world.getSpawnLocation().add( 0, 50, 0 );
            this.schematicSystem.getSchematicManager().paste( location, "EnderGames", success -> {
                if ( success ) {
                    this.getLogger().info( "Schematic wurde erfolgreich plaziert!" );
                }
            } );
        }
    }

    @Override
    public void onUninstall() {
    }

    public int randomInt( double min, double max ) {
        return (int) Math.round( min + Math.random() * (max - min) );
    }

}
