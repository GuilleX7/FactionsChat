package io.github.guillex7.factionschat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.guillex7.factionschat.chat.ChatChannel;
import io.github.guillex7.factionschat.chat.ChatChannelStorage;

public class PlayerListener implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		// Check if player is online
		if (!player.isOnline()) {
			return;
		}

		ChatChannelStorage.getInstance().putIfAbsent(player, ChatChannel.getInitialState());
	}
}
