package com.devotedmc.ExilePearl.command;

import com.devotedmc.ExilePearl.ExilePearl;
import com.devotedmc.ExilePearl.ExilePearlApi;
import com.devotedmc.ExilePearl.Lang;
import com.devotedmc.ExilePearl.PearlType;

public class CmdSummon extends PearlCommand {

	public CmdSummon(ExilePearlApi pearlApi) {
		super(pearlApi);
		this.aliases.add("summon");
		
		this.senderMustBePlayer = true;
		this.setHelpShort("Summon a prisoner");
	}
	
	@Override
	public void perform() {
		if(!plugin.getPearlConfig().allowSummoning()) {
			msg(Lang.summoningNotEnabled);
			return;
		}

		ExilePearl pearl = plugin.getPearlFromItemStack(player().getInventory().getItemInMainHand());
		if(pearl == null) {
			msg(Lang.pearlMustBeHoldingPearl);
			return;
		}
		if (pearl.getPearlType() != PearlType.PRISON) {
			msg("<b>You can only do that with prion pearls.");
			return;
		}
		if(pearl.getPlayer() == null || !pearl.getPlayer().isOnline()) {
			msg("<b>That player is offline, and can't be summoned.");
			return;
		}
		plugin.getPearlManager().requestSummon(player(),pearl);
		msg(Lang.summonRequested,pearl.getPlayerName());
		msg(pearl.getPlayer(),Lang.hasRequestedToSummon,player().getName());
	}
}
