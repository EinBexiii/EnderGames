package de.theamychan.endergames.kit;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.kit.manager.Kit;
import de.theamychan.endergames.util.ItemBuilder;
import io.gomint.entity.EntityPlayer;
import io.gomint.entity.active.EntityPrimedTNT;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.EventPriority;
import io.gomint.event.entity.EntityDamageByEntityEvent;
import io.gomint.event.entity.EntityDamageEvent;
import io.gomint.event.player.PlayerDeathEvent;
import io.gomint.event.world.BlockPlaceEvent;
import io.gomint.inventory.item.ItemAir;
import io.gomint.inventory.item.ItemStack;
import io.gomint.inventory.item.ItemTNT;
import io.gomint.math.Location;
import io.gomint.world.block.BlockFlowingLava;
import io.gomint.world.block.BlockFlowingWater;
import io.gomint.world.block.BlockStationaryLava;
import io.gomint.world.block.BlockStationaryWater;

public class KitBomber implements Kit, EventListener {

    private EnderGames plugin;

    public KitBomber( EnderGames plugin ) {
        this.plugin = plugin;
        this.plugin.registerListener( this );
    }

    @Override
    public String getName() {
        return "Bomber";
    }

    @Override
    public String getDescription( EntityPlayer player ) {
        return plugin.getLocaleManager().translate( player.getLocale(), "kit-bomber-equipment" ) + "\n" + "\n" + plugin.getLocaleManager().translate( player.getLocale(), "kit-bomber-ability" );
    }

    @Override
    public int getID() {
        return 5;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void setContent( EntityPlayer player ) {
        player.getInventory().addItem( new ItemBuilder( ItemTNT.create( 10 ) ).build() );
    }

    @EventHandler
    public void onEntityDamage( EntityDamageEvent e ) {
        if ( e.getEntity() instanceof EntityPlayer ) {
            EntityPlayer player = (EntityPlayer) e.getEntity();

            if ( plugin.getKitManager().getKit( player ) instanceof KitBomber) {
                if(e.getDamageSource().equals( EntityDamageEvent.DamageSource.ENTITY_EXPLODE )){
                    e.setCancelled( true );
                }
            }
        }

    }

    @EventHandler
    public void onEntityDamageByEntity( EntityDamageByEntityEvent e ) {
        if ( e.getEntity() instanceof EntityPlayer ) {
            EntityPlayer player = (EntityPlayer) e.getEntity();

            if ( plugin.getKitManager().getKit( player ) instanceof KitBomber) {
                if(e.getDamageSource().equals( EntityDamageEvent.DamageSource.ENTITY_EXPLODE )){
                    e.setCancelled( true );
                }
            }
        }

    }

    @EventHandler
    public void onPlayerDeath( PlayerDeathEvent e ) {
        EntityPlayer player = e.getPlayer();
        EntityPlayer killer = plugin.getLastDamager().get( player );

        if ( plugin.getKitManager().getKit( player ) instanceof KitBomber) {
            if(killer != null){
                EntityPrimedTNT primedTNT = EntityPrimedTNT.create();
                primedTNT.setFuse( 0 );
                primedTNT.spawn( killer.getLocation().add( 0, 1, 0 ) );
            }
        }
    }

    @EventHandler
    public void onBlockPlace( BlockPlaceEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = e.getItem();

        if ( plugin.getKitManager().getKit( player ) instanceof KitBomber) {
            if ( item instanceof ItemTNT ) {
                // TNT automatic explode
                e.setCancelled( true );

                Location location = e.getShouldReplace().getLocation();

                if(location.getBlock() instanceof BlockFlowingWater || location.getBlock() instanceof BlockStationaryWater
                        || location.getBlock() instanceof BlockFlowingLava || location.getBlock() instanceof BlockStationaryLava ){
                    e.setCancelled( true );
                    return;
                }

                location = location.add( 0.5f, 0.5f, 0.5f );

                EntityPrimedTNT tnt = EntityPrimedTNT.create();
                tnt.setFuse( 2 );
                tnt.spawn( location );

                // Setting item to AIR if the count is 0
                byte newAmount = (byte) ( e.getItem().getAmount() - 1 );
                if ( newAmount == 0 ) {
                    player.getInventory().setItem( player.getInventory().getItemInHandSlot(), ItemAir.create( 0 ) );
                    return;
                } else {
                    e.getItem().setAmount( newAmount );
                    player.getInventory().setItem( player.getInventory().getItemInHandSlot(), e.getItem() );
                }
                return;
            }
        }
    }
}
