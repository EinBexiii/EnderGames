package de.theamychan.endergames.manager;

import de.theamychan.endergames.EnderGames;
import io.gomint.math.Location;
import io.gomint.world.Sound;
import io.gomint.world.WorldLayer;
import io.gomint.world.block.Block;
import io.gomint.world.block.BlockAir;
import io.gomint.world.block.BlockEnderChest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChestTeleport {

    private EnderGames plugin;

    public ChestTeleport( EnderGames plugin ) {
        this.plugin = plugin;
    }

    private List<Block> enderchests = new ArrayList<>();

    public void start(){
        for(int i = 0; i < 100; i++){
            Location location = getRandomLocation();
            Block chest = plugin.getWorld().getBlockAt( location.toBlockPosition() );
            chest.setType( BlockEnderChest.class );
            enderchests.add( chest );
        }
        for(int i = 0; i < 15; i++){
            teleport();
        }
    }

    private void teleport(){
        Block oldChest = enderchests.get( plugin.randomInt( 0, enderchests.size() -1 ) );
        oldChest.getLocation().getWorld().playSound( oldChest.getLocation(), Sound.TELEPORT, (byte) 1 );
        oldChest.setType( BlockAir.class );
        enderchests.remove( oldChest );

        Block newBlock = plugin.getWorld().getBlockAt( getRandomLocation().toBlockPosition() );
        newBlock.getLocation().getWorld().playSound( oldChest.getLocation(), Sound.TELEPORT, (byte) 1 );
        newBlock.setType( BlockEnderChest.class );
        enderchests.add( newBlock );

        plugin.getScheduler().schedule( this::teleport, plugin.randomInt( 30, 60 ), TimeUnit.SECONDS );

    }

    private Location getRandomLocation(){
        int x = plugin.randomInt( plugin.getWorld().getSpawnLocation().getX() - plugin.getRadius(), plugin.getWorld().getSpawnLocation().getX() + plugin.getRadius() );
        int z = plugin.randomInt( plugin.getWorld().getSpawnLocation().getZ() - plugin.getRadius(), plugin.getWorld().getSpawnLocation().getZ() + plugin.getRadius() );
        int y = (int) plugin.getWorld().getHighestBlockAt( x, z, WorldLayer.NORMAL ).getLocation().getY() + 1;
        return new Location( plugin.getWorld(), x, y, z );
    }

    public void removeChests(){
        if(enderchests.size() >= 1){
            for(Block block : enderchests){
                block.setType( BlockAir.class );
            }
        }
    }
}
