package io.github.guillex7.factionschat.message;

import org.bukkit.entity.Player;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;

import io.github.guillex7.factionschat.chat.ChatChannel;

public class MessageBroadcaster {
	public static void broadcastToChannel(String message, MPlayer sender, ChatChannel channel) {
		for (Faction faction : FactionColl.get().getAll()) {
			Rel receiverRelation = faction.getRelationTo(sender.getFaction());
			// Check the faction relation with the sender
			if (channel.shouldBroadcastToRelation(receiverRelation)) {
				// Broadcast message to all members
				for (Player receiver : faction.getOnlinePlayers()) {
					receiver.sendMessage(message);
				}
			}
		}
	}
}
