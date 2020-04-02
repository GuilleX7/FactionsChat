package guillex7.github.io.factionschat;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import guillex7.github.io.factionschat.chat.ChatChannel;
import guillex7.github.io.factionschat.chat.ChatChannelStorage;
import guillex7.github.io.factionschat.listener.ChatListener;
import guillex7.github.io.factionschat.listener.CommandListener;
import guillex7.github.io.factionschat.listener.FactionListener;
import guillex7.github.io.factionschat.listener.PlayerListener;
import guillex7.github.io.factionschat.message.MessageFormatter;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public final class FactionsChat extends JavaPlugin {
	private static Configuration config;
	private static PluginDescriptionFile description;

	@Override
	public void onEnable() {
		// Reload everything
		reload();

		// Register listeners
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new ChatListener(), this);
		pm.registerEvents(new FactionListener(), pm.getPlugin("Factions"));
		getCommand("fchat").setExecutor(new CommandListener());

		getLogger().log(Level.INFO, getPluginDescription().getFullName() + " enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().log(Level.INFO, getPluginDescription().getFullName() + " disabled!");
	}

	public static void reload() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("FactionsChat");
		reloadConfiguration(plugin);
		description = plugin.getDescription();

		// (Re)load current online users
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			ChatChannelStorage.getInstance().putIfAbsent(player, ChatChannel.getInitialState());
		}
	}

	private static void reloadConfiguration(Plugin plugin) {
		plugin.saveDefaultConfig();
		plugin.reloadConfig();
		config = plugin.getConfig();

		// Colorize strings at startup
		for (String path : config.getValues(true).keySet()) {
			if (config.isString(path)) {
				config.set(path, MessageFormatter.colorize(config.getString(path)));
			}
		}
	}

	public static Configuration getConfiguration() {
		return config;
	}

	public static PluginDescriptionFile getPluginDescription() {
		return description;
	}
}
