package de.theamychan.endergames.manager;

import de.theamychan.endergames.EnderGames;
import io.gomint.math.Location;
import io.gomint.world.Sound;
import io.gomint.world.WorldLayer;
import io.gomint.world.block.Block;
import io.gomint.world.block.BlockAir;
import io.gomint.world.block.BlockEnderChest;
import io.gomint.world.block.BlockObsidian;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SpeedBlockTeleport {

    private EnderGames plugin;

    public SpeedBlockTeleport( EnderGames plugin ) {
        this.plugin = plugin;
    }

    private List<Block> blocks = new ArrayList<>();

    public void start(){
        for(int i = 0; i < 50; i++){
            Location location = getRandomLocation();
            Block chest = plugin.getWorld().getBlockAt( location.toBlockPosition() );
            chest.setType( BlockObsidian.class );
            blocks.add( chest );
        }
        for(int i = 0; i < 15; i++){
            teleport();
        }
    }

    private void teleport(){
        Block oldChest = blocks.get( plugin.randomInt( 0, blocks.size() -1 ) );
        oldChest.getLocation().getWorld().playSound( oldChest.getLocation(), Sound.TELEPORT, (byte) 1 );
        oldChest.setType( BlockAir.class );
        blocks.remove( oldChest );

        Block newBlock = plugin.getWorld().getBlockAt( getRandomLocation().toBlockPosition() );
        newBlock.getLocation().getWorld().playSound( oldChest.getLocation(), Sound.TELEPORT, (byte) 1 );
        newBlock.setType( BlockEnderChest.class );
        blocks.add( newBlock );

        plugin.getScheduler().schedule( this::teleport, plugin.randomInt( 45, 75 ), TimeUnit.SECONDS );

    }

    private Location getRandomLocation(){
        int x = plugin.randomInt( plugin.getWorld().getSpawnLocation().getX() - plugin.getRadius(), plugin.getWorld().getSpawnLocation().getX() + plugin.getRadius() );
        int z = plugin.randomInt( plugin.getWorld().getSpawnLocation().getZ() - plugin.getRadius(), plugin.getWorld().getSpawnLocation().getZ() + plugin.getRadius() );
        int y = (int) plugin.getWorld().getHighestBlockAt( x, z, WorldLayer.NORMAL ).getLocation().getY() + 1;
        return new Location( plugin.getWorld(), x, y, z );
    }

    public void removeChests(){
        if(blocks.size() >= 1){
            for(Block block : blocks){
                block.setType( BlockAir.class );
            }
        }
    }

}
