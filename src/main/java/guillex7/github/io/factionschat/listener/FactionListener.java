package guillex7.github.io.factionschat.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.massivecraft.factions.event.EventFactionsMembershipChange;
import com.massivecraft.factions.event.EventFactionsMembershipChange.MembershipChangeReason;

import guillex7.github.io.factionschat.chat.ChatChannel;
import guillex7.github.io.factionschat.chat.ChatChannelStorage;

public class FactionListener implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerMembershipChange(EventFactionsMembershipChange e) {
		if (e.isCancelled()) {
			return;
		}
		
		if (e.getReason().equals(MembershipChangeReason.DISBAND) ||
			e.getReason().equals(MembershipChangeReason.KICK) ||
			e.getReason().equals(MembershipChangeReason.LEAVE)) {
			ChatChannelStorage.getInstance().put(e.getMPlayer().getPlayer(), ChatChannel.getInitialState());
		}
	}
}
