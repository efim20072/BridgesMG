package me.elijahproductions.bridgesmg.entity;

import lombok.Data;
import me.elijahproductions.bridgesmg.utility.LocationContainer;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;

@Data
public class Corners implements LocationContainer {

    private TeamType teamType;
    private Location first;
    private Location second;

    public Corners() { }

    public Corners(TeamType teamType, Location first, Location second) {
        this.teamType = teamType;
        this.first = first;
        this.second = second;
    }

    @Override
    public List<Location> getAllLocations() {
        return Arrays.asList(first, second);
    }
}
