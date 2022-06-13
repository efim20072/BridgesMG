package me.elijahproductions.bridgesmg.infrastructure.createconfig.comand.type;

public enum CommandType {
    SET("set");

    private final String title;

    CommandType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
