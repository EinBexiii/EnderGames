package de.theamychan.endergames.kit;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.kit.manager.Kit;
import io.gomint.entity.EntityPlayer;
import io.gomint.entity.potion.PotionEffect;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerInteractEvent;
import io.gomint.inventory.item.ItemStack;
import io.gomint.world.block.Block;
import io.gomint.world.block.BlockChest;
import io.gomint.world.block.BlockEnderChest;

import java.util.concurrent.TimeUnit;

public class KitDieb implements Kit, EventListener {

    private EnderGames plugin;

    public KitDieb( EnderGames plugin ) {
        this.plugin = plugin;
        this.plugin.registerListener( this );
    }

    @Override
    public String getName() {
        return "Dieb";
    }

    @Override
    public String getDescription( EntityPlayer player ) {
        return plugin.getLocaleManager().translate( player.getLocale(), "kit-dieb-ability" );
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void setContent( EntityPlayer player ) {

    }

    @EventHandler
    public void onPlayerInteract( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        Block block = e.getBlock();

        if(plugin.getKitManager().getKit( player ) instanceof KitDieb){
            if ( block instanceof BlockEnderChest ) {
                BlockEnderChest enderChest = (BlockEnderChest) block;
                player.addEffect( PotionEffect.SPEED, 1, 16, TimeUnit.SECONDS );
                e.setCancelled( true );
                plugin.getScheduler().schedule( () -> {
                    for(ItemStack itemStack : plugin.getItemsMap().get( enderChest )){
                        player.getInventory().addItem( itemStack );
                    }
                }, 20, TimeUnit.MILLISECONDS );
            }
        }

    }

}
