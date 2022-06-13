package me.elijahproductions.bridgesmg.domain.createconfig.service;

import org.bukkit.command.CommandSender;

public interface GameCreateProcessor {
     void set(CommandSender sender,String[] args);
     void save(CommandSender sender, String[] args);
     void create(CommandSender sender,String[] args);
}
