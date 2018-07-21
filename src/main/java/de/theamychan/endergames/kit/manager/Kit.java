package de.theamychan.endergames.kit.manager;

import io.gomint.entity.EntityPlayer;

public interface Kit {

    String getName();

    int getID();

    int getPrice();

    void setContent( EntityPlayer player );
}
