package me.elijahproductions.bridgesmg.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<T> {
    public abstract T parseSingle(ResultSet set);
    public List<T> parseList(ResultSet set) {
        List<T> list = new ArrayList<>();
        try {
            while (set.next()) list.add(parseSingle(set));
            set.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;
    }
}
