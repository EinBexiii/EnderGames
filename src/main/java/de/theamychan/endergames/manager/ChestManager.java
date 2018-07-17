package de.theamychan.endergames.manager;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.util.ItemBuilder;
import io.gomint.inventory.Inventory;
import io.gomint.inventory.item.*;
import io.gomint.world.block.BlockEnderChest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChestManager {

    private EnderGames plugin;

    public ChestManager( EnderGames plugin ) {
        this.plugin = plugin;
    }

    public void fillChest( BlockEnderChest chest ) {
        Inventory inventory = chest.getInventory();
        inventory.clear();

        List<ItemStack> items = new ArrayList<>();

        int rn = plugin.randomInt( 4, 8 );

        //Ore and Leather
        items.add( new ItemBuilder( ItemDiamond.create( plugin.randomInt( 1, 5 ) ) ).build() );
        items.add( new ItemBuilder( ItemIronIngot.create( plugin.randomInt( 1, 9 ) ) ).build() );
        items.add( new ItemBuilder( ItemGoldIngot.create( plugin.randomInt( 1, 8 ) ) ).build() );
        items.add( new ItemBuilder( ItemLeather.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemDiamond.create( plugin.randomInt( 1, 5 ) ) ).build() );

        items.add( new ItemBuilder( ItemCookedBeef.create( plugin.randomInt( 1, 4 ) ) ).build() );
        items.add( new ItemBuilder( ItemApple.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemBakedPotato.create( plugin.randomInt( 1, 4 ) ) ).build() );
        items.add( new ItemBuilder( ItemCarrot.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemPumpkinPie.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemWheat.create( plugin.randomInt( 1, 4 ) ) ).build() );
        items.add( new ItemBuilder( ItemGoldenApple.create( 1 ) ).build() );
        Collections.shuffle( items );

        //Bucket
        items.add( new ItemBuilder( ItemBucket.create( 1 ) ).setContent( ItemBucket.Content.MILK ).build() );
        items.add( new ItemBuilder( ItemBucket.create( 1 ) ).setContent( ItemBucket.Content.WATER ).build() );
        items.add( new ItemBuilder( ItemBucket.create( 1 ) ).setContent( ItemBucket.Content.LAVA ).build() );
        items.add( new ItemBuilder( ItemBucket.create( 1 ) ).setContent( ItemBucket.Content.MILK ).build() );
        items.add( new ItemBuilder( ItemBucket.create( 1 ) ).setContent( ItemBucket.Content.WATER ).build() );
        items.add( new ItemBuilder( ItemBucket.create( 1 ) ).setContent( ItemBucket.Content.LAVA ).build() );

        //Ore and Leather
        items.add( new ItemBuilder( ItemDiamond.create( plugin.randomInt( 1, 5 ) ) ).build() );
        items.add( new ItemBuilder( ItemIronIngot.create( plugin.randomInt( 1, 9 ) ) ).build() );
        items.add( new ItemBuilder( ItemIronIngot.create( plugin.randomInt( 1, 9 ) ) ).build() );

        //Spezial
        items.add( new ItemBuilder( ItemTNT.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemFlintAndSteel.create( 1 ) ).build() );
        items.add( new ItemBuilder( ItemFishingRod.create( 1 ) ).build() );
        items.add( new ItemBuilder( ItemCobweb.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemCobweb.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemCobweb.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemExperienceBottle.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemExperienceBottle.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemExperienceBottle.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemBone.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemRedstone.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemSnowball.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemSnowball.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemSnowball.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemFlint.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemFlint.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemEnderPearl.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemEnderPearl.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemEnderPearl.create( plugin.randomInt( 1, 3 ) ) ).build() );
        items.add( new ItemBuilder( ItemFishingRod.create( 1 ) ).build() );
        Collections.shuffle( items );

        //Weapons
        items.add( new ItemBuilder( ItemBow.create( 1 ) ).build() );
        items.add( new ItemBuilder( ItemArrow.create( plugin.randomInt( 1, 8 ) ) ).build() );

        Collections.shuffle( items );

        //Ore and Leather
        items.add( new ItemBuilder( ItemIronIngot.create( plugin.randomInt( 1, 9 ) ) ).build() );
        items.add( new ItemBuilder( ItemGoldIngot.create( plugin.randomInt( 1, 8 ) ) ).build() );
        items.add( new ItemBuilder( ItemDiamond.create( plugin.randomInt( 1, 5 ) ) ).build() );
        items.add( new ItemBuilder( ItemIronIngot.create( plugin.randomInt( 1, 9 ) ) ).build() );
        items.add( new ItemBuilder( ItemGoldIngot.create( plugin.randomInt( 1, 8 ) ) ).build() );
        items.add( new ItemBuilder( ItemLeather.create( plugin.randomInt( 1, 3 ) ) ).build() );

        //Weapon
        items.add( new ItemBuilder( ItemArrow.create( plugin.randomInt( 1, 8 ) ) ).build() );
        items.add( new ItemBuilder( ItemArrow.create( plugin.randomInt( 1, 8 ) ) ).build() );
        items.add( new ItemBuilder( ItemArrow.create( plugin.randomInt( 1, 8 ) ) ).build() );

        //Food

        items.add( new ItemBuilder( ItemCookedBeef.create( plugin.randomInt( 1, 4 ) ) ).build() );
        items.add( new ItemBuilder( ItemApple.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemBakedPotato.create( plugin.randomInt( 1, 4 ) ) ).build() );
        items.add( new ItemBuilder( ItemCarrot.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemPumpkinPie.create( plugin.randomInt( 1, 2 ) ) ).build() );
        items.add( new ItemBuilder( ItemWheat.create( plugin.randomInt( 1, 4 ) ) ).build() );
        items.add( new ItemBuilder( ItemGoldenApple.create( 1 ) ).build() );

        Collections.shuffle( items );

        while (rn != 0) {
            rn--;
            ItemStack itemStack = items.get( new Random().nextInt( items.size() ) );
            inventory.setItem( plugin.randomInt( 0, 26 ), itemStack );
        }
    }
}
