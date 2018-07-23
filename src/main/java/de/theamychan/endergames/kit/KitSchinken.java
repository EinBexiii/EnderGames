package de.theamychan.endergames.kit;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import de.theamychan.endergames.kit.manager.Kit;
import de.theamychan.endergames.util.ItemBuilder;
import io.gomint.entity.EntityPlayer;
import io.gomint.entity.potion.PotionEffect;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.entity.EntityDamageByEntityEvent;
import io.gomint.event.player.PlayerInteractEvent;
import io.gomint.inventory.item.ItemRawPorkchop;
import io.gomint.inventory.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KitSchinken implements Kit, EventListener {

    private EnderGames plugin;

    public KitSchinken( EnderGames plugin ) {
        this.plugin = plugin;
        this.plugin.registerListener( this );
    }

    private List<EntityPlayer> interact = new ArrayList<>();

    @Override
    public String getName() {
        return "Schinken";
    }

    @Override
    public String getDescription() {
        return "Ausrüstung: 1x Schinken \n Fähigkeit: Mehr Damage und für 6 Sekunden Speed und einen Regenerations Effect";
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void setContent( EntityPlayer player ) {
        player.getInventory().addItem( new ItemBuilder( ItemRawPorkchop.create( 1 ) ).setCustomName( "§cSchinken" ).build() );
    }

    @EventHandler
    public void onPlayerInteract( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if(plugin.getKitManager().getKit( player ) == this){
            if(item instanceof ItemRawPorkchop && item.getCustomName().equalsIgnoreCase( "§cSchinken" )){
                if(player.getHunger() < 20){
                    player.setHunger( 20 );
                    player.addEffect( PotionEffect.REGENERATION, 1, 7, TimeUnit.SECONDS );
                    player.addEffect( PotionEffect.SPEED, 1, 7, TimeUnit.SECONDS );
                    player.addEffect( PotionEffect.STRENGTH, 1, 7, TimeUnit.SECONDS );
                    e.setCancelled( true );
                }
            }
        }
    }

}
