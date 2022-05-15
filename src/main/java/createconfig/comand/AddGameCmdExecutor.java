package createconfig.comand;

import me.elijahproductions.bridgesmg.command.CommandsListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddGameCmdExecutor implements CommandsListener {

    @Override
    public boolean onCommand
            (@NotNull CommandSender sender,
             @NotNull Command command,
             @NotNull String label,
             @NotNull String[] args
            ) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        String name = args[0];
        int num = Integer.parseInt(args[1]);

        return false;
    }

    @Override
    public String getLabel() {
        return "addgame";
    }
}
