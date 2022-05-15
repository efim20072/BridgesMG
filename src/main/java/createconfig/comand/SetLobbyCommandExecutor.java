package createconfig.comand;

import me.elijahproductions.bridgesmg.command.CommandsListener;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetLobbyCommandExecutor implements CommandsListener {
    @Override
    public String getLabel() {
        return null;
    }

    private Location loc;

    /*
    1. Создать лобби с именем (уникальным)
    2. CreatorLobby
        2.1 добавить в базе данных
        2.2 получить id из базы данных
    3. когда создаешь локацию то в Executor из CreatorLobby подягиеваешь ID лоби
        и получается
            3.1 добавить локацию в таблицу в базе данных
            3.2 получить id локацию из базы данных
            3.3 обновить поле location в тоблице lobby по id лобби который в CreatorLobby
     */

    @Override
    public boolean onCommand
            (@NotNull CommandSender sender,
             @NotNull Command command,
             @NotNull String label,
             @NotNull String[] args
            ) {
         if (!(sender instanceof Player)) return false;
         Player player = (Player) sender;
         loc = player.getLocation();

        String game = args[0];
        // получить параметр - имя лобби
        return true;
    }
}
