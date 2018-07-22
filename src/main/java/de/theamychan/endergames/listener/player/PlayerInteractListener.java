package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import de.theamychan.endergames.kit.KitBabar;
import io.gomint.GoMint;
import io.gomint.entity.Entity;
import io.gomint.entity.EntityPlayer;
import io.gomint.entity.projectile.EntityArrow;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerInteractEvent;
import io.gomint.gui.ButtonList;
import io.gomint.gui.FormListener;
import io.gomint.gui.FormResponse;
import io.gomint.inventory.item.ItemArrow;
import io.gomint.inventory.item.ItemChest;
import io.gomint.inventory.item.ItemCompass;
import io.gomint.inventory.item.ItemStack;
import io.gomint.math.Location;
import io.gomint.math.Vector;
import io.gomint.world.Gamemode;
import io.gomint.world.block.Block;
import io.gomint.world.block.BlockEnderChest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PlayerInteractListener implements EventListener {

    private EnderGames plugin;

    public PlayerInteractListener( EnderGames plugin ) {
        this.plugin = plugin;
    }

    private List<Location> locations = new ArrayList<>();

    @EventHandler
    public void onPlayerInteract( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();
        Block block = e.getBlock();

        if( GameState.getGameState().equals( GameState.LOBBY )){

            if(item instanceof ItemChest && item.getCustomName().equalsIgnoreCase( "§6Kits" ) ){
                ButtonList buttonList = ButtonList.create( "§6Kits" );
                buttonList.addButton( "kitBabar", "Babar" );
                buttonList.addButton( "kitArcher", "Archer" );
                buttonList.addButton( "kitDieb", "Dieb" );
                player.showForm( buttonList ).onResponse( id ->  {
                    if(id.equals( "kitBabar" )){
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitBabar() );
                    }else if(id.equals( "kitArcher" )){
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitArcher() );
                    }else if(id.equals( "kitDieb" )){
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitDieb() );
                    }
                    player.sendMessage( plugin.getPrefix() + "§7Du hast das Kit §e" + plugin.getKitManager().getKit( player ).getName() + " §7ausgewählt" );
                } );

            }

        }else if ( !GameState.getGameState().equals( GameState.LOBBY ) ) {
            if(block instanceof BlockEnderChest ){
                BlockEnderChest chest = (BlockEnderChest) block;
                if(!locations.contains( chest.getLocation() )){
                    plugin.getChestManager().fillChest( chest );
                    locations.add( block.getLocation() );
                }
            }
            if(item instanceof ItemCompass && item.getCustomName().equalsIgnoreCase( "§5Tracker" ) ){
                if(getNearbyPlayer( player ) != null){
                    player.sendMessage( plugin.getPrefix() + "§7Spieler §r" + getNearbyPlayer( player ).getNameTag() + " §7getrackt: §e" + (int) player.getLocation().distance( getNearbyPlayer( player ).getLocation() ) + " Blöcke");
                }else{
                    player.sendMessage( plugin.getPrefix() + "§cEs konnte kein Spieler getrackt werden!" );
                }
            }
        }

    }

    @EventHandler
    public void onPlayerShootArrow(PlayerInteractEvent e){
        EntityPlayer player = e.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInHand();

        if(!GameState.getGameState().equals( GameState.LOBBY )){
            if(itemStack instanceof ItemArrow ){
                EntityArrow entityArrow = GoMint.instance().createEntity( EntityArrow.class );
                entityArrow.spawn( player.getLocation().add( 0, (float) 1.5, 0 ) );
                entityArrow.setVelocity( player.getDirection().multiply( 2 ) );
            }
        }
    }

    private EntityPlayer getNearbyPlayer(EntityPlayer target) {
        EntityPlayer nearestPlayer = null;
        float nearestDistance = -1;

        for(EntityPlayer player : target.getWorld().getPlayers()) {
            if(player != target) {
                if(!plugin.getSpectator().contains( player ) && !player.getGamemode().equals( Gamemode.SPECTATOR )){
                    if(nearestDistance == -1) {
                        nearestDistance = player.getLocation().distance(target.getLocation());
                        nearestPlayer = player;
                    } else
                    if(nearestDistance > (player.getLocation().distance(target.getLocation()))) {
                        nearestDistance = player.getLocation().distance(target.getLocation());
                        nearestPlayer = player;
                    }
                }
            }
        }

        return nearestPlayer;
    }

}
