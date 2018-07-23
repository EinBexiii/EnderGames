package de.theamychan.endergames.kit;

import de.theamychan.endergames.kit.manager.Kit;
import de.theamychan.endergames.util.ItemBuilder;
import io.gomint.entity.EntityPlayer;
import io.gomint.inventory.ArmorInventory;
import io.gomint.inventory.Inventory;
import io.gomint.inventory.item.*;

public class KitBabar implements Kit {

    @Override
    public String getName() {
        return "Babar";
    }

    @Override
    public String getDescription() {
        return "Ausrüstung: 1x Holzschwert Komplette Lederrüstung";
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void setContent( EntityPlayer player ) {
        Inventory inventory = player.getInventory();
        ArmorInventory armorInventory = player.getArmorInventory();

        armorInventory.setHelmet( new ItemBuilder( ItemLeatherHelmet.create( 1 ) ).build() );
        armorInventory.setChestplate( new ItemBuilder( ItemLeatherChestplate.create( 1 ) ).build() );
        armorInventory.setLeggings( new ItemBuilder( ItemLeatherLeggings.create( 1 ) ).build() );
        armorInventory.setBoots( new ItemBuilder( ItemLeatherBoots.create( 1 ) ).build() );

        inventory.addItem( new ItemBuilder( ItemWoodenSword.create( 1 ) ).build() );

    }


}
