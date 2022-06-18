package me.elijahproductions.bridgesmg.domain.createconfig;

import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.common.entity.WaitingRoom;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface CreateController {
    void createConfig(String configName, String worldName, Player player);
    void addWaitingRoom(WaitingRoom waitingRoom);
    void addCorner(TeamType type, Location location, int number);
    void addTeamSpawn(TeamType type, Location location);
    void setLobby(Location location);
    void setMaxScore(int maxScore);
    void setTeamSize(int teamSize);

    void save();
    void showProgress();
}
