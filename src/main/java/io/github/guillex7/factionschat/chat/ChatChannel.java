package io.github.guillex7.factionschat.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

import com.massivecraft.factions.Rel;

import io.github.guillex7.factionschat.faction.FactionRelationBipredicates;

public enum ChatChannel {
	FACTION(null, "f", "faction", Rel.FACTION, new FactionRelationBipredicates.AtLeast()),
	ALLY(FACTION, "a", "ally", Rel.ALLY, new FactionRelationBipredicates.AtLeast()),
	PUBLIC(ALLY, "p", "public", Rel.NEUTRAL, new FactionRelationBipredicates.AtLeast());

	private static Map<String, ChatChannel> byPrefix = new HashMap<>();
	private static Map<String, ChatChannel> byKey = new HashMap<>();
	static {
		for (ChatChannel channel : values()) {
			byPrefix.put(channel.prefix, channel);
			byKey.put(channel.key, channel);
		}
	}

	private final ChatChannel nextChannel;
	private final String prefix;
	private final String key;
	private final Rel relation;
	private final BiPredicate<Rel, Rel> broadcastBipredicate;

	ChatChannel(ChatChannel next, String prefix, String key, Rel relation, BiPredicate<Rel, Rel> broadcastBipredicate) {
		this.nextChannel = next;
		this.prefix = prefix;
		this.key = key;
		this.relation = relation;
		this.broadcastBipredicate = broadcastBipredicate;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getKey() {
		return key;
	}

	public Rel getRelation() {
		return relation;
	}
	
	public boolean shouldBroadcastToRelation(Rel receiverRelation) {
		return broadcastBipredicate.test(receiverRelation, relation);
	}

	private boolean hasNext() {
		return nextChannel != null;
	}

	public ChatChannel getNextChannel() {
		if (hasNext()) {
			return nextChannel;
		} else {
			return ChatChannel.getInitialState();
		}
	}

	public static ChatChannel getByPrefix(String prefix) {
		return byPrefix.get(prefix);
	}

	public static ChatChannel getByKey(String key) {
		return byKey.get(key);
	}

	public static ChatChannel getInitialState() {
		return PUBLIC;
	}
}
