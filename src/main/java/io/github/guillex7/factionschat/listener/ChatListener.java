package io.github.guillex7.factionschat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.massivecraft.factions.entity.MPlayer;

import io.github.guillex7.factionschat.chat.ChatChannel;
import io.github.guillex7.factionschat.chat.ChatChannelStorage;
import io.github.guillex7.factionschat.message.MessageBroadcaster;
import io.github.guillex7.factionschat.message.MessageFormatter;

public class ChatListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onPlayerChat(AsyncPlayerChatEvent e) {
		if (e.isCancelled()) {
			return;
		}

		Player player = e.getPlayer();

		// Check whether the player is online or not
		if (!player.isOnline()) {
			return;
		}

		MPlayer mPlayer = MPlayer.get(player);

		// Check whether the player has a faction or not
		if (!mPlayer.hasFaction()) {
			return;
		}

		ChatChannelStorage pcs = ChatChannelStorage.getInstance();
		ChatChannel currentChannel = pcs.get(player);

		// Why is he not registered yet?...
		if (currentChannel == null) {
			currentChannel = ChatChannel.getInitialState();
			pcs.put(player, currentChannel);
			return;
		}

		switch (currentChannel) {
		case PUBLIC:
			// Do not interfere with global chat
			return;
		default:
			MessageBroadcaster.broadcastToChannel(
					MessageFormatter.formatMessage(e.getMessage(), mPlayer, currentChannel), mPlayer, currentChannel);
			e.setCancelled(true);
			break;
		}
	}
}
