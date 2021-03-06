package de.theamychan.endergames.kit.manager;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.kit.*;
import io.gomint.entity.EntityPlayer;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class KitManager {

    private EnderGames plugin;

    @Getter
    private KitBabar kitBabar;
    @Getter
    private KitArcher kitArcher;
    @Getter
    private KitDieb kitDieb;
    @Getter
    private KitSchinken kitSchinken;
    @Getter
    private KitSuppenmeister kitSuppenmeister;
    @Getter
    private KitBomber kitBomber;

    private Map<EntityPlayer, Kit> kit = new HashMap<>();

    public KitManager( EnderGames plugin  ) {
        this.plugin = plugin;
        this.kitBabar = new KitBabar();
        this.kitArcher = new KitArcher( plugin );
        this.kitDieb = new KitDieb( plugin );
        this.kitSchinken = new KitSchinken( plugin );
        this.kitSuppenmeister = new KitSuppenmeister( plugin );
        this.kitBomber = new KitBomber( plugin );
    }

    public void setKit( EntityPlayer player, Kit kit){
        this.kit.put( player, kit );
        player.sendMessage( plugin.getPrefix() + plugin.getLocaleManager().translate( player.getLocale(), "kit-selected", getKit( player ).getName() ) );
    }

    public Kit getKit(EntityPlayer player){
        return this.kit.get( player );
    }

    public boolean haveKit(EntityPlayer player, Kit kit){
        for(String ids : plugin.getStats().getKits( player )){
            Integer kitID = Integer.parseInt( ids );
            if(kitID == kit.getID()){
                return true;
            }
        }
        return false;
    }

}
