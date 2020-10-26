package io.github.guillex7.factionschat.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import io.github.guillex7.factionschat.FactionsChat;
import io.github.guillex7.factionschat.message.MessageFormatter;

public class CommandAbout {
	public static boolean executor(CommandSender sender, Command cmd, String label, String[] args) {
		PluginDescriptionFile description = FactionsChat.getPluginDescription();
		sender.sendMessage(MessageFormatter.colorize("&f" + description.getFullName() + "\n&7Developed by "
				+ String.join(", ", description.getAuthors()) + "\n&7" + description.getWebsite()));
		return true;
	}
}
