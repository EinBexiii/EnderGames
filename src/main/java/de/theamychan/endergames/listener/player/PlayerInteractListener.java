package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import de.theamychan.endergames.kit.KitBabar;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerInteractEvent;
import io.gomint.gui.ButtonList;
import io.gomint.gui.FormListener;
import io.gomint.gui.FormResponse;
import io.gomint.inventory.item.ItemChest;
import io.gomint.inventory.item.ItemStack;
import io.gomint.math.Location;
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
                player.showForm( buttonList ).onResponse( id ->  {
                    if(id.equals( "kitBabar" )){
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitBabar() );
                    }else if(id.equals( "kitArcher" )){
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitArcher() );
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
        }

    }

}
