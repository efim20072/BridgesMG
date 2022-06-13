package me.elijahproductions.bridgesmg.common.entity;

public enum TeamType {
    BLUE("Blue"),
    RED("Red");

    private final String title;

    TeamType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
