package me.elijahproductions.bridgesmg.repository;

import me.elijahproductions.bridgesmg.entity.game.GameMatch;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameMatchesRepository {

    private final List<GameMatch> matches;

    public GameMatchesRepository() {
        this.matches = new ArrayList<>();
    }

    public void addMatch(GameMatch match) {
        matches.add(match);
    }

    @Nullable
    public GameMatch getMatch(UUID uuid) {
        for (GameMatch match : matches) {
            if (uuid == match.getUuid())
                return match;
        }
        return null;
    }

    @Nullable
    public GameMatch removeMatch(UUID uuid) {
        for (GameMatch match : matches) {
            if (uuid == match.getUuid())
                matches.remove(match);
            return match;
        }
        return null;
    }

    public List<GameMatch> getMatches() {
        return matches;
    }
}
