package io.github.guillex7.factionschat.command;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.MPlayer;

import io.github.guillex7.factionschat.FactionsChat;
import io.github.guillex7.factionschat.chat.ChatChannel;
import io.github.guillex7.factionschat.chat.ChatChannelStorage;
import io.github.guillex7.factionschat.message.MessageFormatter;

public class CommandFC {
	public static boolean executor(CommandSender sender, Command cmd, String label, String[] args) {
		// Check for player sender
		if (!(sender instanceof Player)) {
			Bukkit.getLogger().log(Level.WARNING, "Only players can use faction chat commands!");
			return true;
		}

		Player player = (Player) sender;

		// Check for online player sender
		if (!player.isOnline()) {
			Bukkit.getLogger().log(Level.WARNING, "Only online players can use faction chat commands!");
			return true;
		}

		// Check for permission
		if (!player.hasPermission("factionschat.chat")) {
			player.sendMessage(FactionsChat.getConfiguration().getString("message.nopermission"));
			return true;
		}

		// Check if has faction
		MPlayer mPlayer = MPlayer.get(player);
		if (!mPlayer.hasFaction()) {
			player.sendMessage(FactionsChat.getConfiguration().getString("message.nofaction"));
			return true;
		}

		ChatChannelStorage pcs = ChatChannelStorage.getInstance();
		ChatChannel currentChannel = pcs.get(player);
		ChatChannel selectedChannel = null;
		boolean valid = false;

		// Why is player not registered yet?...
		if (currentChannel == null) {
			pcs.put(player, ChatChannel.getInitialState());
		}

		if (args.length == 0) {
			// Go to next channel
			selectedChannel = currentChannel.getNextChannel();
			valid = true;
		} else if (args.length == 1) {
			// Try to go to selected channel
			String arg = args[0].toLowerCase();
			selectedChannel = ChatChannel.getByPrefix(arg);
			if (selectedChannel != null) {
				valid = true;
			} else {
				selectedChannel = ChatChannel.getByKey(arg);
				if (selectedChannel != null) {
					valid = true;
				}
			}
		}

		if (valid) {
			pcs.put(player, selectedChannel);
			player.sendMessage(FactionsChat.getConfiguration()
					.getString(MessageFormatter.getMessageKey(selectedChannel.getKey())));
		} else {
			// No valid channel detected
			return CommandHelp.executor(sender, cmd, label, args);
		}

		return valid;
	}
}
