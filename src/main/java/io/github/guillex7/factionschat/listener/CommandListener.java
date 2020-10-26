package io.github.guillex7.factionschat.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import io.github.guillex7.factionschat.chat.ChatChannel;
import io.github.guillex7.factionschat.command.CommandAbout;
import io.github.guillex7.factionschat.command.CommandFC;
import io.github.guillex7.factionschat.command.CommandHelp;
import io.github.guillex7.factionschat.command.CommandReload;

public class CommandListener implements TabExecutor {
	private final static String[] SUBCOMMANDS = { "help", "about", "reload" };

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fchat")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("about")) {
					// fchat about
					return CommandAbout.executor(sender, cmd, label, args);
				} else if (args[0].equalsIgnoreCase("reload")) {
					// fchat reload
					return CommandReload.executor(sender, cmd, label, args);
				} else if (args[0].equalsIgnoreCase("help")) {
					// fchat help
					return CommandHelp.executor(sender, cmd, label, args);
				}
			}
			
			// fchat [channel]?
			return CommandFC.executor(sender, cmd, label, args);
		}

		// This is not even our command!
		return CommandHelp.executor(sender, cmd, label, args);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		List<String> results = null;

		if (cmd.getName().equalsIgnoreCase("fchat")) {
			results = new ArrayList<String>();

			if (args.length > 0) {
				if (args[0].length() > 0) {
					// Add channel suggestions
					for (ChatChannel channel : ChatChannel.values()) {
						if (channel.getPrefix().startsWith(args[0])) {
							results.add(channel.getPrefix());
							results.add(channel.getKey());
						} else if (channel.getKey().startsWith(args[0])) {
							results.add(channel.getKey());
						}
					}

					// Add subcommand suggestions
					for (String subcommand : CommandListener.SUBCOMMANDS) {
						if (subcommand.startsWith(args[0])) {
							results.add(subcommand);
						}
					}
				} else {
					// Add all channels
					for (ChatChannel channel : ChatChannel.values()) {
						results.add(channel.getPrefix());
						results.add(channel.getKey());
					}

					// Add all subcommands
					for (String subcommand : CommandListener.SUBCOMMANDS) {
						results.add(subcommand);
					}
				}
			}
		}

		return results;
	}
}
