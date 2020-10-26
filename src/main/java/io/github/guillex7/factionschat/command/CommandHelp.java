package io.github.guillex7.factionschat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import io.github.guillex7.factionschat.FactionsChat;

public class CommandHelp {
	public static boolean executor(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(FactionsChat.getConfiguration().getString("message.usage"));
		return true;
	}
}
