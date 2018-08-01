package de.theamychan.endergames.config;

import io.gomint.config.YamlConfig;
import lombok.Getter;

@Getter
public class Config extends YamlConfig {

    private String prefix = "§f[§5EnderGames§f] ";

    private String arenaName = "Arena";
    private String defaultLocale = "GERMANY";

    private int minPlayers = 2;
    private int maxPlayers = 24;

    private boolean transferAfterMatch = false;
    private String address = "localhost";
    private int port = 19132;

    private String mySQLHost = "localhost";
    private String mySQLDatabase = "database";
    private String mySQLUser = "root";
    private String mySQLPassword = "password";

}
