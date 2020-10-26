package io.github.guillex7.factionschat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import io.github.guillex7.factionschat.FactionsChat;

public class CommandReload {
	public static boolean executor(CommandSender sender, Command cmd, String label, String[] args) {
		// Check for permission
		if (!sender.hasPermission("factionschat.reload")) {
			sender.sendMessage(FactionsChat.getConfiguration().getString("message.nopermission"));
			return true;
		}

		FactionsChat.reload();

		sender.sendMessage(FactionsChat.getConfiguration().getString("message.reload"));
		return true;
	}
}
