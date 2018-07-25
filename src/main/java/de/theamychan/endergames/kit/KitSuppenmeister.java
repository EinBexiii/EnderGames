package de.theamychan.endergames.kit;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.kit.manager.Kit;
import de.theamychan.endergames.util.ItemBuilder;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerInteractEvent;
import io.gomint.inventory.item.ItemAir;
import io.gomint.inventory.item.ItemMushroomStew;
import io.gomint.inventory.item.ItemStack;

public class KitSuppenmeister implements Kit, EventListener {

    private EnderGames plugin;

    public KitSuppenmeister( EnderGames plugin ) {
        this.plugin = plugin;
        this.plugin.registerListener( this );
    }

    @Override
    public String getName() {
        return "Suppenmeister";
    }

    @Override
    public String getDescription() {
        return "Ausrüstung: 10x Suppen " + "\n" + "\n" + "Fähigkeiten: Beim essen der Suppe bekommst du 2 Herzen dazu!";
    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void setContent( EntityPlayer player ) {
        player.getInventory().addItem( new ItemBuilder( ItemMushroomStew.create( 10 ) ).build() );
    }

    @EventHandler
    public void onPlayerInteract( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if(plugin.getKitManager().getKit( player ) instanceof KitSuppenmeister ){
            if(item instanceof ItemMushroomStew){
                if(item.getAmount() > 1){
                    item.setAmount( item.getAmount() - 1 );
                    player.getInventory().setItem( player.getInventory().getItemInHandSlot(), item );
                    player.setHealth( player.getHealth() + 4 );
                }else{
                    player.getInventory().setItem( player.getInventory().getItemInHandSlot(), ItemAir.create( 1 ) );
                    player.setHealth( player.getHealth() + 4 );
                }
            }
        }
    }

}
