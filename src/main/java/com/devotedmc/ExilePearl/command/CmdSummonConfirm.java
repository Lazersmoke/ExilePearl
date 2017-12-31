package com.devotedmc.ExilePearl.command;

import org.bukkit.entity.Player;

import com.devotedmc.ExilePearl.ExilePearl;
import com.devotedmc.ExilePearl.ExilePearlApi;
import com.devotedmc.ExilePearl.Lang;
import com.devotedmc.ExilePearl.PearlType;

public class CmdSummonConfirm extends PearlCommand {

	public CmdSummonConfirm(ExilePearlApi pearlApi) {
		super(pearlApi);
		this.aliases.add("confirm");

		this.senderMustBePlayer = true;
		this.setHelpShort("Confirms a summon request.");
	}

	@Override
	public void perform() {
		if(!plugin.getPearlConfig().allowSummoning()) {
			msg(Lang.summoningNotEnabled);
			return;
		}

		ExilePearl pearl = plugin.getPearl(player().getUniqueId());
		if (pearl == null || pearl.getPearlType() != PearlType.PRISON) {
			msg(Lang.onlyPrisonedPlayers);
			return;
		}

		Player summoner = plugin.getPearlManager().getSummoner(pearl);
		if(summoner == null) {
			msg(Lang.noSummonRequested);
			return;
		}
		
		if(!summoner.isOnline()) {
			msg("<c>%s <b> is offline, so you couldn't be summoned.", summoner.getName());
			return;
		}
		
		if(plugin.summonPearl(pearl, summoner)) {
			msg(summoner,Lang.pearlSummoned, pearl.getPlayerName());
			msg(Lang.pearlYouWereSummoned, summoner.getName());
		} else {
			msg(summoner,Lang.pearlCantSummon);
			msg("<b>Summon failed.");
		}
	}
}
