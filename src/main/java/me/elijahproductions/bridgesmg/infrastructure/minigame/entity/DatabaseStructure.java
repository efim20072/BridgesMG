package me.elijahproductions.bridgesmg.infrastructure.minigame.entity;

public class DatabaseStructure {

    public static class GameConfig {
        public static final String TITLE = "configs";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String WIN_SCORE = "win_score";
        public static final String TEAM_SIZE = "team_size";
        public static final String WORLD_NAME = "world_name";
    }

    public static class Location {
        public static final String TITLE = "location";
        public static final String ID = "id";
        public static final String X = "x";
        public static final String Y = "y";
        public static final String Z = "z";
    }

    public static class Lobby {
        public static final String TITLE = "lobby";
        public static final String ID = "id";
        public static final String CONFIG_ID = "config_id";
        public static final String LOCATION_ID = "location_id";
    }

    public static class Spawn {
        public static final String TITLE = "spawn";
        public static final String ID = "id";
        public static final String TYPE = "type";
        public static final String CONFIG_ID = "config_id";
        public static final String LOCATION_ID = "location_id";
    }

    public static class Corners {
        public static final String TITLE = "corners";
        public static final String ID = "id";
        public static final String CONFIG_ID = "config_id";
        public static final String TYPE = "type";
        public static final String FIRST_LOCATION_ID = "first_loc_id";
        public static final String SECOND_LOCATION_ID = "second_loc_id";
    }

    public static class WaitingRoom {
        public static final String TITLE = "waiting_room";
        public static final String ID = "id";
        public static final String CONFIG_ID = "config_id";
        public static final String TYPE = "type";
        public static final String LOCATION_ID = "location_id";
    }
}
