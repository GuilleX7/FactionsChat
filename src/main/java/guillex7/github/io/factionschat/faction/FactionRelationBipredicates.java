package guillex7.github.io.factionschat.faction;

import java.util.function.BiPredicate;

import com.massivecraft.factions.Rel;

public class FactionRelationBipredicates {
	public static class AtLeast implements BiPredicate<Rel, Rel> {
		@Override
		public boolean test(Rel receiverRelation, Rel channelRelation) {
			return receiverRelation.isAtLeast(channelRelation);
		}
	}
}
