package guillex7.github.io.factionschat.message;

import com.massivecraft.factions.entity.MPlayer;

import guillex7.github.io.factionschat.FactionsChat;
import guillex7.github.io.factionschat.chat.ChatChannel;

public class MessageFormatter {
	public static String getFormatKey(String key, Boolean titled) {
		return "format." + key + (titled ? ".titled" : ".untitled");
	}

	public static String getMessageKey(String key) {
		return "message." + key;
	}

	public static String formatMessage(String message, MPlayer player, ChatChannel channel) {
		String fMessage = FactionsChat.getConfiguration().getString(getFormatKey(channel.getKey(), player.hasTitle()));

		fMessage = fMessage.replaceAll("%PLAYERNAME%", player.getName());
		fMessage = fMessage.replaceAll("%DISPLAYNAME%", player.getPlayer().getDisplayName());
		fMessage = fMessage.replaceAll("%FACTIONNAME%", player.getFactionName());
		fMessage = fMessage.replaceAll("%TITLE%", (!player.hasTitle() ? "" : player.getTitle()));
		fMessage = fMessage.replaceAll("%PREFIX%", player.getRank().getPrefix());
		fMessage = fMessage.replaceAll("%MESSAGE%", message);
		return fMessage;
	}

	public static String colorize(String message) {
		return message.replaceAll("&([0-9a-fk-or])", "\u00A7$1");
	}
}
