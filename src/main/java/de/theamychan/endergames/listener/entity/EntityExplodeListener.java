package de.theamychan.endergames.listener.entity;

import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements EventListener {

    @EventHandler
    public void onEntityExplode( EntityExplodeEvent e ){
        e.getAffectedBlocks().clear();
    }

}
