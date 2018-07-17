package de.theamychan.endergames.kit.manager;

import de.theamychan.endergames.EnderGames;
import de.theamychan.endergames.kit.KitArcher;
import de.theamychan.endergames.kit.KitBabar;
import io.gomint.entity.EntityPlayer;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class KitManager {

    @Getter
    private KitBabar kitBabar;
    @Getter
    private KitArcher kitArcher;

    private Map<EntityPlayer, Kit> kit = new HashMap<>();

    public KitManager( EnderGames plugin  ) {
        this.kitBabar = new KitBabar();
        this.kitArcher = new KitArcher( plugin );
    }

    public void setKit( EntityPlayer player, Kit kit){
        this.kit.put( player, kit );
    }

    public Kit getKit(EntityPlayer player){
        return this.kit.get( player );
    }

}
