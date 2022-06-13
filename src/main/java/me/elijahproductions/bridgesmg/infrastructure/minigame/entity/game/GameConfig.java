package me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class GameConfig {

    public static class Builder {
        private int id;
        private String name;
        private int teamSize;
        private String lobbyWorldName;
        private String gameWorldName;
        private GamePortals gamePortals;
        private GameWaitingRooms waitingRooms;

        public static Builder create(){
            return new Builder();
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setTeamSize(int teamSize){
            this.teamSize = teamSize;
            return this;
        }

        public Builder setLobby(String lobby){
            this.lobbyWorldName = lobby;
            return this;
        }

        public Builder setGameWorldName(String gameWorld){
            this.gameWorldName = gameWorld;
            return this;
        }

        public Builder setGamePortals(GamePortals gamePortals){
            this.gamePortals = gamePortals;
            return this;
        }

        public Builder setWaitingRooms(GameWaitingRooms waitingRooms){
            this.waitingRooms = waitingRooms;
            return this;
        }

        public GameConfig build() {
            return new GameConfig(
                    id,
                    name,
                    teamSize,
                    lobbyWorldName,
                    gameWorldName,
                    gamePortals,
                    waitingRooms
            );
        }
    }

    int id;

    String name;
    int teamSize;

    String lobbyWorldName;
    String gameWorldName;

    GamePortals gamePortals;

    GameWaitingRooms waitingRooms;

    @Override
    public String toString() {
        return "GameConfig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamSize=" + teamSize +
                ", gamePortals=" + gamePortals +
                ", waitingRooms=" + waitingRooms +
                '}';
    }
}
