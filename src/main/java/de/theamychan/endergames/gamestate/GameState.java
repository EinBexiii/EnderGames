package de.theamychan.endergames.gamestate;

import lombok.Getter;
import lombok.Setter;

public enum GameState {

    LOBBY,
    WAIT,
    PEACEFUL,
    INGAME,
    RESTART;

    @Getter @Setter
    private static GameState gameState;

}
