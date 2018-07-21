package de.theamychan.endergames.kit;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.kit.manager.Kit;
import de.theamychan.endergames.util.ItemBuilder;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.player.PlayerDeathEvent;
import io.gomint.inventory.ArmorInventory;
import io.gomint.inventory.Inventory;
import io.gomint.inventory.item.ItemArrow;
import io.gomint.inventory.item.ItemBow;

public class KitArcher implements Kit, EventListener {

    private EnderGames plugin;

    public KitArcher( EnderGames plugin ) {
        this.plugin = plugin;
        this.plugin.registerListener( this );
    }

    @Override
    public String getName() {
        return "Archer";
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void setContent( EntityPlayer player ) {
        Inventory inventory = player.getInventory();
        ArmorInventory armorInventory = player.getArmorInventory();

        inventory.addItem(  new ItemBuilder( ItemBow.create( 1 ) ).build() );
        inventory.addItem(  new ItemBuilder( ItemArrow.create( 10 ) ).build() );
    }

    @EventHandler
    public void onPlayerDeath( PlayerDeathEvent e ) {
        EntityPlayer player = e.getPlayer();
        EntityPlayer killer = plugin.getLastDamager().get( player );

        if(killer != null){
            killer.getInventory().addItem( new ItemBuilder( ItemArrow.create( 2 ) ).build() );
        }

    }

}
