package de.theamychan.endergames.util;

import io.gomint.enchant.Enchantment;
import io.gomint.inventory.item.ItemBucket;
import io.gomint.inventory.item.ItemStack;

public class ItemBuilder {

    private ItemStack itemStack;

    public ItemBuilder( ItemStack itemStack ) {
        this.itemStack = itemStack;
    }

    public ItemBuilder setCustomName( String customName ) {
        this.itemStack.setCustomName( customName );
        return this;
    }

    public ItemBuilder setLore( String... lore ) {
        this.itemStack.setLore( lore );
        return this;
    }

    public ItemBuilder setAmount( int amount ) {
        this.itemStack.setAmount( amount );
        return this;
    }

    public ItemBuilder setData( short data ) {
        this.itemStack.setData( data );
        return this;
    }

    public ItemBuilder setContent( ItemBucket.Content content ){
        if(itemStack instanceof ItemBucket){
            ((ItemBucket) itemStack).setContent( content );
        }
        return this;
    }

    public ItemBuilder addEnchantment( Class<? extends Enchantment> clazz, short value){
        this.itemStack.addEnchantment( clazz, value );
        return this;
    }

    public ItemStack build(){
        return this.itemStack;
    }
}
