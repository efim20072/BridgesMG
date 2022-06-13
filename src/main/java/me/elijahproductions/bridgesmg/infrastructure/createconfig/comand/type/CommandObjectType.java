package me.elijahproductions.bridgesmg.infrastructure.createconfig.comand.type;

public enum CommandObjectType {
    CONFIG("config"),
    CORNER("corner"),
    TEAM_SPAWN("spawn"),
    LOBBY("lobby"),
    MAX_SCORE("maxscore"),
    TEAM_SIZE("teamsize"),
    WAITING_ROOM("waiting_room");

    private final String title;

    CommandObjectType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
