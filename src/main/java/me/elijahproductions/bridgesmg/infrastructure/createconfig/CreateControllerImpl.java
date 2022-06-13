package me.elijahproductions.bridgesmg.infrastructure.createconfig;

import lombok.val;
import me.elijahproductions.bridgesmg.common.entity.Corners;
import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.common.entity.WaitingRoom;
import me.elijahproductions.bridgesmg.domain.createconfig.CreateController;
import me.elijahproductions.bridgesmg.domain.createconfig.entity.CreateState;
import me.elijahproductions.bridgesmg.domain.createconfig.service.SaveConfigService;
import me.elijahproductions.bridgesmg.infrastructure.SDK;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;

//TODO проверка создания конфига для 1 игрока
public class CreateControllerImpl implements CreateController {

    private static final String INSTALLED_MESSAGE = ChatColor.GREEN + "Установлено";
    private static final String UNINSTALLED_MESSAGE = ChatColor.DARK_GRAY + "Не установлено";
    private static final String NEW_LINE = "\n";
    private static final String NEW_LINE_WITH_TAB = "\n     ";

    private static CreateControllerImpl instance = null;

    private final SaveConfigService saveConfigService;

    private CreateControllerImpl() {
        saveConfigService = SDK.get().getSaveConfigService();
    }

    public static CreateControllerImpl get() {
        if (instance == null) instance = new CreateControllerImpl();
        return instance;
    }

    private Player player;
    private CreateState state;

    @Override
    public void createConfig(String name, Player player) {
        if (this.player != null) {
            player.sendMessage("Конфиг создается другим игроком.");
            return;
        }
        this.player = player;
        state = new CreateState();
        state.setName(name);
        initPortalCorners();

        showProgress();
    }

    private void initPortalCorners() {
        for (TeamType type : TeamType.values())
            state.getPortals().put(type, new Corners());
    }

    @Override
    public void addWaitingRoom(WaitingRoom waitingRoom) {
        state.getWaitingRooms().put(waitingRoom.getTeamType(), waitingRoom);
        showProgress();
    }

    @Override
    public void addCorner(TeamType type, Location location, int number) {
        Corners corners = state.getPortals().get(type);
        if (corners == null) return;
        switch (number) {
            case 1 -> corners.setFirst(location);
            case 2 -> corners.setSecond(location);
        }
        showProgress();
    }

    @Override
    public void addTeamSpawn(TeamType type, Location location) {
        state.getTeamSpawns().put(type, location);
        showProgress();
    }

    @Override
    public void setLobby(Location location) {
        state.setLobby(location);
        showProgress();
    }

    @Override
    public void setMaxScore(int maxScore) {
        state.setMaxScore(maxScore);
        showProgress();
    }

    @Override
    public void setTeamSize(int teamSize) {
        state.setTeamSize(teamSize);
        showProgress();
    }


    @Override
    public void save() {
        if (!isStateFilled()) {
            showFilledErrorMessage();
            return;
        }
        player = null;
        saveConfigService.save(state);
    }

    private void showFilledErrorMessage() {
        player.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "Заполни все");
    }

    private boolean isStateFilled() {
        return state.getName() != null &&
                state.getLobby() != null &&
                isMapFilledByTeamType(state.getPortals().keySet()) &&
                isMapFilledByTeamType(state.getTeamSpawns().keySet()) &&
                isMapFilledByTeamType(state.getWaitingRooms().keySet());
    }

    private boolean isMapFilledByTeamType(Set<TeamType> teamTypes) {
        for (TeamType type : TeamType.values()) {
            if (!teamTypes.contains(type)) return false;
        }
        return true;
    }

    @Override
    public void showProgress() {
        val portals = state.getPortals();

        val blueCorners = portals.get(TeamType.BLUE);
        val blueCount = blueCorners != null ?
                blueCorners.getFillingCount() : 0;

        val redCorners = portals.get(TeamType.RED);
        val redCount = redCorners != null ?
                redCorners.getFillingCount() : 0;

        val lobbyResult = state.getLobby() != null ?
                INSTALLED_MESSAGE : UNINSTALLED_MESSAGE;

        val spawns = state.getTeamSpawns();
        val blueSpawn = spawns.get(TeamType.BLUE);
        val blueSpawnMessage = blueSpawn == null ?
                UNINSTALLED_MESSAGE : INSTALLED_MESSAGE;

        val redSpawn = spawns.get(TeamType.RED);
        val redSpawnMessage = redSpawn == null ?
                UNINSTALLED_MESSAGE : INSTALLED_MESSAGE;

        val waitingRooms = state.getWaitingRooms();
        val blueWaitingRoom = waitingRooms.get(TeamType.BLUE);
        val blueWaitingRoomMessage = blueWaitingRoom == null ?
                UNINSTALLED_MESSAGE : INSTALLED_MESSAGE;

        val redWaitingRoom = waitingRooms.get(TeamType.RED);
        val redWaitingRoomMessage = redWaitingRoom == null ?
                UNINSTALLED_MESSAGE : INSTALLED_MESSAGE;

        player.sendMessage(
                String.format(
                        userAlertMessagePattern(),
                        blueCount,
                        redCount,
                        lobbyResult,
                        blueSpawnMessage,
                        redSpawnMessage,
                        blueWaitingRoomMessage,
                        redWaitingRoomMessage
                )
        );
    }

    private String userAlertMessagePattern() {
        return ChatColor.WHITE + "1. Портал:" +
                ChatColor.BLUE + NEW_LINE_WITH_TAB + "Синий - %d/2" +
                ChatColor.RED + NEW_LINE_WITH_TAB + "Красный - %d/2" +
                ChatColor.WHITE + NEW_LINE + "2. Лобби - %s" +
                ChatColor.WHITE + NEW_LINE + "3. Спавн:" +
                ChatColor.BLUE + NEW_LINE_WITH_TAB + "Синий - %s" +
                ChatColor.RED + NEW_LINE_WITH_TAB + "Красный - %s" +
                ChatColor.WHITE + NEW_LINE + "4. Комната ожидания:" +
                ChatColor.BLUE + NEW_LINE_WITH_TAB + "Синяя - %s" +
                ChatColor.RED + NEW_LINE_WITH_TAB + "Красная - %s";
    }
}
