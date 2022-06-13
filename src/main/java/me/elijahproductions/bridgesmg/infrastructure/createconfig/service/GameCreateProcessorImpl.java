package me.elijahproductions.bridgesmg.infrastructure.createconfig.service;

import lombok.val;
import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.common.entity.WaitingRoom;
import me.elijahproductions.bridgesmg.domain.createconfig.CreateController;
import me.elijahproductions.bridgesmg.infrastructure.createconfig.CreateControllerImpl;
import me.elijahproductions.bridgesmg.infrastructure.createconfig.comand.type.CommandObjectType;
import me.elijahproductions.bridgesmg.domain.createconfig.service.GameCreateProcessor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Locale;

public class GameCreateProcessorImpl implements GameCreateProcessor {
    private static final int COMMAND_TYPE = 0;
    private static final int OBJECT_TYPE = 1;
    private static final int TEAM_TYPE = 2;
    private static final int MAX_SCORE = 2;
    private static final int TEAM_SIZE = 2;
    private static final int CONFIG_NAME = 2;
    private static final int OBJECT_NUMBER = 3;

    CreateController controller = CreateControllerImpl.get();

    @Override
    public void set(CommandSender sender, String[] args) {
        val objectTypeTitle = args[OBJECT_TYPE];
        CommandObjectType objectType = commandObjectOff(objectTypeTitle);

        try {
            if (objectType == null) throw new IllegalArgumentException(objectTypeTitle + " is not object type.");

            Player player = (Player) sender;
            Location location = player.getLocation();
            switch (objectType) {
                case CORNER -> setCorner(location, args);
                case TEAM_SPAWN -> addTeamSpawn(location, args);
                case LOBBY -> setLobby(location,args);
                case WAITING_ROOM -> addWaitingRoom(location,args);
                case MAX_SCORE -> setMaxScore(args);
                case TEAM_SIZE -> setTeamSize(args);

            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        /*
        if (equals(objectType,"corner")){
            //нужная хуйня.addCorner
        }
        if (equals(objectType,"lobby")){
            //нужная хуйня.setLobby
        }


        if (args[OBJECT_TYPE].equalsIgnoreCase("waiting_room")){
            //нужная хуйня.addWaitingRoom
        }
        if (args[OBJECT_TYPE].equalsIgnoreCase("spawn")){
            //нужная хуйня.addSpawn
        }
        if (args[OBJECT_TYPE].equalsIgnoreCase("maxscore")){
            //нужная хуйня.setMaxscore
        }
        */

    }

    private void addTeamSpawn(Location location, String[] args) {
        TeamType teamType = teamTypeOff(args[TEAM_TYPE]);
        controller.addTeamSpawn(teamType, location);
    }

    private void setMaxScore(String[] args){
        int maxScore = Integer.parseInt(args[MAX_SCORE]);
        controller.setMaxScore(maxScore);
    }

    private void setTeamSize(String[] args){
        int teamSize = Integer.parseInt(args[TEAM_SIZE]);
        controller.setTeamSize(teamSize);
    }

    private void setCorner(Location location, String[] args) {
        int number = Integer.parseInt(args[OBJECT_NUMBER]);
        String teamTypeTitle = args[TEAM_TYPE];
        TeamType teamType = teamTypeOff(teamTypeTitle);
        if (teamType == null) throw new IllegalStateException(teamTypeTitle + " is not team type.");
        controller.addCorner(teamType, location, number);
    }

    private void setLobby(Location location, String[] args){
        controller.setLobby(location);
    }

    private void addWaitingRoom(Location location, String[] args){
        TeamType teamType = teamTypeOff(args[TEAM_TYPE]);
        controller.addWaitingRoom(new WaitingRoom(teamType,location));
    }

    @Override
    public void save(CommandSender sender, String[] args) {

        //TODO Проверка на то что все введено (спрашивать у контроллера)
        controller.save();
    }

    @Override
    public void create(CommandSender sender, String[] args) {
        val objectTypeTitle = args[OBJECT_TYPE];
        CommandObjectType objectType = commandObjectOff(objectTypeTitle);

        try {
            if (objectType == null) throw new IllegalArgumentException(objectTypeTitle + " is not object type.");

            Player player = (Player) sender;
            switch (objectType) {
                case CONFIG -> controller.createConfig(args[CONFIG_NAME], player);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private boolean equals(String arg, String v) {
        return arg.equalsIgnoreCase(v);
    }

    private CommandObjectType commandObjectOff(String type) {
        // TODO разобраться почему CommandObjectType.valueOf(type.toUpperCase(Locale.ROOT)); не работает
        System.out.println("ARARARAR " + Arrays.toString(CommandObjectType.values()));
        for (CommandObjectType objectType : CommandObjectType.values()) {
            if (objectType.getTitle().equals(type)) return objectType;
        }
        return null;
    }

    private TeamType teamTypeOff(String type) {
        // TODO разобраться почему TeamType.valueOf(args[TEAM_TYPE].toUpperCase(Locale.ROOT)); не работает
        for (TeamType teamType : TeamType.values()) {
            if (teamType.getTitle().toLowerCase(Locale.ROOT).equals(type)) return teamType;
        }
        return null;
    }
}
