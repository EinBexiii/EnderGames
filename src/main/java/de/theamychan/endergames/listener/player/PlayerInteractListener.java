package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.entity.potion.PotionEffect;
import io.gomint.entity.projectile.EntityArrow;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.EventPriority;
import io.gomint.event.player.PlayerInteractEvent;
import io.gomint.gui.ButtonList;
import io.gomint.gui.FormListener;
import io.gomint.gui.Modal;
import io.gomint.inventory.Inventory;
import io.gomint.inventory.item.*;
import io.gomint.math.Location;
import io.gomint.world.Gamemode;
import io.gomint.world.block.Block;
import io.gomint.world.block.BlockAir;
import io.gomint.world.block.BlockEnderChest;
import io.gomint.world.block.BlockObsidian;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerInteractListener implements EventListener {

    private EnderGames plugin;

    public PlayerInteractListener( EnderGames plugin ) {
        this.plugin = plugin;
    }

    private List<Location> locations = new ArrayList<>();

    @EventHandler
    public void onPlayerSelectKit( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();
        Block block = e.getBlock();

        if ( GameState.getGameState().equals( GameState.LOBBY ) ) {
            if ( item instanceof ItemChest && item.getCustomName().equalsIgnoreCase( "§6Kits" ) ) {
                ButtonList buttonList = ButtonList.create( "§6Kits" );
                buttonList.addButton( "kitBabar", plugin.getLocaleManager().translate( player.getLocale(), "kit-name-babar" ) );
                buttonList.addButton( "kitArcher", plugin.getLocaleManager().translate( player.getLocale(), "kit-name-archer" ) );
                buttonList.addButton( "kitDieb", plugin.getLocaleManager().translate( player.getLocale(), "kit-name-thief" ) );
                buttonList.addButton( "kitSchinken", plugin.getLocaleManager().translate( player.getLocale(), "kit-name-ham" ) );
                buttonList.addButton( "kitSuppenmeister", plugin.getLocaleManager().translate( player.getLocale(), "kit-name-soupsmaster" ) );
                buttonList.addButton( "kitBomber", plugin.getLocaleManager().translate( player.getLocale(), "kit-name-bomber" ) );

                player.showForm( buttonList ).onResponse( id -> {
                    if ( id.equals( "kitBabar" ) ) {
                        Modal modal = Modal.create( plugin.getLocaleManager().translate( player.getLocale(), "kit-select-menu-title" ), plugin.getKitManager().getKitBabar().getDescription( player ));
                        modal.setTrueButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-chose" ) );
                        modal.setFalseButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-cancel" ) );
                        player.showForm( modal ).onResponse( bool ->{
                            Boolean success = Boolean.valueOf( String.valueOf( bool ) );
                            if(success){
                                plugin.getKitManager().setKit( player, plugin.getKitManager().getKitBabar() );
                            }
                        } );
                    } else if ( id.equals( "kitArcher" ) ) {
                        Modal modal = Modal.create( plugin.getLocaleManager().translate( player.getLocale(), "kit-select-menu-title" ), plugin.getKitManager().getKitArcher().getDescription( player ));
                        modal.setTrueButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-chose" ) );
                        modal.setFalseButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-cancel" ) );
                        player.showForm( modal ).onResponse( bool ->{
                            Boolean success = Boolean.valueOf( String.valueOf( bool ) );
                            if(success){
                                plugin.getKitManager().setKit( player, plugin.getKitManager().getKitArcher() );
                            }
                        } );
                    } else if ( id.equals( "kitDieb" ) ) {
                        Modal modal = Modal.create( plugin.getLocaleManager().translate( player.getLocale(), "kit-select-menu-title" ), plugin.getKitManager().getKitDieb().getDescription( player ));
                        modal.setTrueButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-chose" ) );
                        modal.setFalseButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-cancel" ) );
                        player.showForm( modal ).onResponse( bool ->{
                            Boolean success = Boolean.valueOf( String.valueOf( bool ) );
                            if(success){
                                plugin.getKitManager().setKit( player, plugin.getKitManager().getKitDieb() );
                            }
                        } );
                    } else if ( id.equals( "kitSchinken" ) ) {
                        Modal modal = Modal.create( plugin.getLocaleManager().translate( player.getLocale(), "kit-select-menu-title" ), plugin.getKitManager().getKitSchinken().getDescription( player ));
                        modal.setTrueButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-chose" ) );
                        modal.setFalseButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-cancel" ) );
                        player.showForm( modal ).onResponse( bool ->{
                            Boolean success = Boolean.valueOf( String.valueOf( bool ) );
                            if(success){
                                plugin.getKitManager().setKit( player, plugin.getKitManager().getKitSchinken() );
                            }
                        } );
                    } else if ( id.equals( "kitSuppenmeister" ) ) {
                        Modal modal = Modal.create( plugin.getLocaleManager().translate( player.getLocale(), "kit-select-menu-title" ), plugin.getKitManager().getKitSuppenmeister().getDescription( player ));
                        modal.setTrueButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-chose" ) );
                        modal.setFalseButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-cancel" ) );
                        player.showForm( modal ).onResponse( bool ->{
                            Boolean success = Boolean.valueOf( String.valueOf( bool ) );
                            if(success){
                                plugin.getKitManager().setKit( player, plugin.getKitManager().getKitSuppenmeister() );
                            }
                        } );
                    } else if ( id.equals( "kitBomber" ) ) {
                        Modal modal = Modal.create( plugin.getLocaleManager().translate( player.getLocale(), "kit-select-menu-title" ), plugin.getKitManager().getKitBomber().getDescription( player ));
                        modal.setTrueButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-chose" ) );
                        modal.setFalseButtonText( plugin.getLocaleManager().translate( player.getLocale(), "kit-cancel" ) );
                        player.showForm( modal ).onResponse( bool ->{
                            Boolean success = Boolean.valueOf( String.valueOf( bool ) );
                            if(success){
                                plugin.getKitManager().setKit( player, plugin.getKitManager().getKitBomber() );
                            }
                        } );
                    }
                } );
            }
        }
    }

    @EventHandler( priority = EventPriority.HIGH )
    public void onPlayerOpenEnderchest( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        Block block = e.getBlock();
        ItemStack item = player.getInventory().getItemInHand();

        if ( !GameState.getGameState().equals( GameState.LOBBY ) ) {
            if ( block instanceof BlockEnderChest ) {
                if(plugin.getIngame().contains( player )){
                    BlockEnderChest enderChest = (BlockEnderChest) block;
                    enderChest.setCustomName( "Enderchest" );
                    Inventory inventory = enderChest.getInventory();
                    if ( !locations.contains( enderChest.getLocation() ) ) {
                        List<ItemStack> items = plugin.getChestManager().fillChest( enderChest );
                        locations.add( block.getLocation() );
                        plugin.getItemsMap().put( block, items );
                    }
                }else{
                    e.setCancelled( true );
                }
            }
        }
    }

    @EventHandler
    public void onPlayerShootArrow( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if ( !GameState.getGameState().equals( GameState.LOBBY ) ) {
            if ( item instanceof ItemArrow ) {
                if ( item.getAmount() > 1 ) {
                    EntityArrow entityArrow = GoMint.instance().createEntity( EntityArrow.class );
                    entityArrow.spawn( player.getLocation().add( 0, (float) 1.5, 0 ) );
                    entityArrow.setVelocity( player.getDirection().multiply( 2 ) );
                    item.setAmount( item.getAmount() - 1 );
                    player.getInventory().setItem( player.getInventory().getItemInHandSlot(), item );
                } else {
                    EntityArrow entityArrow = GoMint.instance().createEntity( EntityArrow.class );
                    entityArrow.spawn( player.getLocation().add( 0, (float) 1.5, 0 ) );
                    entityArrow.setVelocity( player.getDirection().multiply( 2 ) );
                    player.getInventory().setItem( player.getInventory().getItemInHandSlot(), ItemAir.create( 1 ) );
                }
            }
        }
    }

    @EventHandler
    public void onPlayerHitSpeedBlock( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        Block block = e.getBlock();

        if ( block instanceof BlockObsidian && plugin.getSpeedBlockTeleport().getBlocks().contains( block ) ) {
            block.setType( BlockAir.class );
            player.addEffect( PotionEffect.SPEED, 1, 31, TimeUnit.SECONDS );
        }
    }

    @EventHandler
    public void onPlayerTrackTarget( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if ( item instanceof ItemCompass && item.getCustomName().equalsIgnoreCase( "§5Tracker" ) ) {
            if ( getNearbyPlayer( player ) != null ) {
                player.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( player.getLocale(), "tracker-message", getNearbyPlayer( player ).getNameTag(), (int) player.getLocation().distance( getNearbyPlayer( player ).getLocation() ) ) );
            } else {
                player.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( player.getLocale(), "trackker-message-fail" ) );
            }
        }
    }

    @EventHandler
    public void onPlayerInteractWithTeleporter(PlayerInteractEvent e){
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if(item instanceof ItemCompass && item.getCustomName().equalsIgnoreCase( "§6Teleporter" )){
            if(plugin.getSpectator().contains( player )){
                ButtonList buttonList = ButtonList.create( "§6Teleporter" );
                for(EntityPlayer ingamePlayers : plugin.getIngame()){
                    if( !player.getName().equals( ingamePlayers.getName() ) ){
                        buttonList.addButton( ingamePlayers.getName(), ingamePlayers.getNameTag() );
                    }
                }
                FormListener<String> formListener = player.showForm( buttonList );
                formListener.onResponse( button -> {
                    EntityPlayer target = GoMint.instance().findPlayerByName( button );
                    player.teleport( target.getLocation() );
                } );
            }
        }

    }

    private EntityPlayer getNearbyPlayer( EntityPlayer target ) {
        EntityPlayer nearestPlayer = null;
        float nearestDistance = -1;

        for (EntityPlayer player : target.getWorld().getPlayers()) {
            if ( player != target ) {
                if ( !plugin.getSpectator().contains( player ) && !player.getGamemode().equals( Gamemode.SPECTATOR ) ) {
                    if ( nearestDistance == -1 ) {
                        nearestDistance = player.getLocation().distance( target.getLocation() );
                        nearestPlayer = player;
                    } else if ( nearestDistance > (player.getLocation().distance( target.getLocation() )) ) {
                        nearestDistance = player.getLocation().distance( target.getLocation() );
                        nearestPlayer = player;
                    }
                }
            }
        }

        return nearestPlayer;
    }

}
