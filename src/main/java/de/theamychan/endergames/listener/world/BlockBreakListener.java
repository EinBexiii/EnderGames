package de.theamychan.endergames.listener.world;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.world.BlockBreakEvent;
import io.gomint.inventory.item.ItemApple;
import io.gomint.world.Gamemode;
import io.gomint.world.block.Block;
import io.gomint.world.block.BlockLeaves;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BlockBreakListener implements EventListener {

    private EnderGames plugin;

    @EventHandler
    public void onBlockBreak( BlockBreakEvent e ) {
        EntityPlayer player = e.getPlayer();

        if(GameState.getGameState().equals( GameState.LOBBY ) || GameState.getGameState().equals( GameState.WAIT ) || GameState.getGameState().equals( GameState.RESTART ) ) {
            if(!player.getGamemode().equals( Gamemode.CREATIVE )) {
                e.setCancelled( true );
            }else{
                e.setCancelled( false );
            }
        }else{
            if(!plugin.getIngame().contains( player )){
                e.setCancelled( true );
            }else{
                e.setCancelled( false );
            }
        }

    }

    @EventHandler
    public void onBreakLeaves(BlockBreakEvent e) {
        EntityPlayer player = e.getPlayer();
        Block block = e.getBreakBlock();

        if(block instanceof BlockLeaves){
            e.getDrops().clear();

            int rnd = plugin.randomInt( 1, 20 );

            if(rnd == 1 || rnd == 20){
                player.getWorld().createItemDrop( block.getLocation(), ItemApple.create( 1 ) );
            }
        }
    }

}
