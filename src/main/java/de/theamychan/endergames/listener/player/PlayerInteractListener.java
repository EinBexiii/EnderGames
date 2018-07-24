package de.theamychan.endergames.listener.player;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.gamestate.GameState;
import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;
import io.gomint.entity.potion.PotionEffect;
import io.gomint.entity.projectile.EntityArrow;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.EventPriority;
import io.gomint.event.player.PlayerInteractEvent;
import io.gomint.gui.ButtonList;
import io.gomint.inventory.item.*;
import io.gomint.math.Location;
import io.gomint.world.Gamemode;
import io.gomint.world.block.Block;
import io.gomint.world.block.BlockAir;
import io.gomint.world.block.BlockEnderChest;
import io.gomint.world.block.BlockObsidian;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerInteractListener implements EventListener {

    private EnderGames plugin;

    public PlayerInteractListener( EnderGames plugin ) {
        this.plugin = plugin;
    }

    private List<Location> locations = new ArrayList<>();

    @EventHandler
    public void onPlayerSelectKit( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();
        Block block = e.getBlock();

        if ( GameState.getGameState().equals( GameState.LOBBY ) ) {
            if ( item instanceof ItemChest && item.getCustomName().equalsIgnoreCase( "§6Kits" ) ) {
                ButtonList buttonList = ButtonList.create( "§6Kits" );
                buttonList.addButton( "kitBabar", "Babar" );
                buttonList.addButton( "kitArcher", "Archer" );
                buttonList.addButton( "kitDieb", "Dieb" );
                buttonList.addButton( "kitSchinken", "Schinken" );
                buttonList.addButton( "kitSuppenmeister", "Suppenmeister" );
                buttonList.addButton( "kitBomber", "Bomber" );

                player.showForm( buttonList ).onResponse( id -> {
                    if ( id.equals( "kitBabar" ) ) {
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitBabar() );
                    } else if ( id.equals( "kitArcher" ) ) {
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitArcher() );
                    } else if ( id.equals( "kitDieb" ) ) {
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitDieb() );
                    } else if ( id.equals( "kitSchinken" ) ) {
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitSchinken() );
                    } else if ( id.equals( "kitSuppenmeister" ) ) {
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitSuppenmeister() );
                    } else if ( id.equals( "kitBomber" ) ) {
                        plugin.getKitManager().setKit( player, plugin.getKitManager().getKitBomber() );
                    }
                    player.sendMessage( plugin.getPrefix() + "§7Du hast das Kit §e" + plugin.getKitManager().getKit( player ).getName() + " §7ausgewählt" );
                } );
            }
        }
    }

    @EventHandler( priority = EventPriority.HIGH )
    public void onPlayerOpenEnderchest( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        Block block = e.getBlock();
        ItemStack item = player.getInventory().getItemInHand();

        if ( !GameState.getGameState().equals( GameState.LOBBY ) ) {
            if ( block instanceof BlockEnderChest ) {
                BlockEnderChest chest = (BlockEnderChest) block;
                if ( !locations.contains( chest.getLocation() ) ) {
                    List<ItemStack> items = plugin.getChestManager().fillChest( chest );
                    locations.add( block.getLocation() );
                    plugin.getItemsMap().put( block, items );
                }
            }
        }
    }

    @EventHandler
    public void onPlayerShootArrow( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if ( !GameState.getGameState().equals( GameState.LOBBY ) ) {
            if ( item instanceof ItemArrow ) {
                if ( item.getAmount() > 1 ) {
                    EntityArrow entityArrow = GoMint.instance().createEntity( EntityArrow.class );
                    entityArrow.spawn( player.getLocation().add( 0, (float) 1.5, 0 ) );
                    entityArrow.setVelocity( player.getDirection().multiply( 2 ) );
                    item.setAmount( item.getAmount() - 1 );
                    player.getInventory().setItem( player.getInventory().getItemInHandSlot(), item );
                } else {
                    EntityArrow entityArrow = GoMint.instance().createEntity( EntityArrow.class );
                    entityArrow.spawn( player.getLocation().add( 0, (float) 1.5, 0 ) );
                    entityArrow.setVelocity( player.getDirection().multiply( 2 ) );
                    player.getInventory().setItem( player.getInventory().getItemInHandSlot(), ItemAir.create( 1 ) );
                }
            }
        }
    }

    @EventHandler
    public void onPlayerHitSpeedBlock( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        Block block = e.getBlock();

        if ( block instanceof BlockObsidian && plugin.getSpeedBlockTeleport().getBlocks().contains( block ) ) {
            block.setType( BlockAir.class );
            player.addEffect( PotionEffect.SPEED, 1, 31, TimeUnit.SECONDS );
        }
    }

    @EventHandler
    public void onPlayerTrackTarget( PlayerInteractEvent e ) {
        EntityPlayer player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if ( item instanceof ItemCompass && item.getCustomName().equalsIgnoreCase( "§5Tracker" ) ) {
            if ( getNearbyPlayer( player ) != null ) {
                player.sendMessage( plugin.getPrefix() + "§7Spieler §r" + getNearbyPlayer( player ).getNameTag() + " §7getrackt: §e" + (int) player.getLocation().distance( getNearbyPlayer( player ).getLocation() ) + " Blöcke" );
            } else {
                player.sendMessage( plugin.getPrefix() + "§cEs konnte kein Spieler getrackt werden!" );
            }
        }
    }

    private EntityPlayer getNearbyPlayer( EntityPlayer target ) {
        EntityPlayer nearestPlayer = null;
        float nearestDistance = -1;

        for (EntityPlayer player : target.getWorld().getPlayers()) {
            if ( player != target ) {
                if ( !plugin.getSpectator().contains( player ) && !player.getGamemode().equals( Gamemode.SPECTATOR ) ) {
                    if ( nearestDistance == -1 ) {
                        nearestDistance = player.getLocation().distance( target.getLocation() );
                        nearestPlayer = player;
                    } else if ( nearestDistance > (player.getLocation().distance( target.getLocation() )) ) {
                        nearestDistance = player.getLocation().distance( target.getLocation() );
                        nearestPlayer = player;
                    }
                }
            }
        }

        return nearestPlayer;
    }

}
