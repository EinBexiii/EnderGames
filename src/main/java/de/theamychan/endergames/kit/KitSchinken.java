package de.theamychan.endergames.kit;

import de.theamychan.endergames.kit.manager.Kit;
import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventListener;

public class KitSchinken implements Kit, EventListener {

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
        return 0;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public void setContent( EntityPlayer player ) {

    }
}
