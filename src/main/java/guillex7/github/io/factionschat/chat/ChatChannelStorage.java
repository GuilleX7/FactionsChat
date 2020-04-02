package guillex7.github.io.factionschat.chat;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class ChatChannelStorage {
	private static ChatChannelStorage instance;

	Map<Player, ChatChannel> storage = new HashMap<Player, ChatChannel>();

	private ChatChannelStorage() {
	}

	public void put(Player player, ChatChannel channel) {
		storage.put(player, channel);
	}

	public ChatChannel putIfAbsent(Player player, ChatChannel channel) {
		return storage.putIfAbsent(player, channel);
	}

	public boolean containsPlayer(Player player) {
		return storage.containsKey(player);
	}

	public ChatChannel get(Player player) {
		return storage.get(player);
	}

	public static ChatChannelStorage getInstance() {
		if (instance == null) {
			instance = new ChatChannelStorage();
		}

		return instance;
	}

}
