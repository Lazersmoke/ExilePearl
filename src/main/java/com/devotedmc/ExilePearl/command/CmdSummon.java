package com.devotedmc.ExilePearl.command;

import com.devotedmc.ExilePearl.ExilePearl;
import com.devotedmc.ExilePearl.ExilePearlApi;
import com.devotedmc.ExilePearl.Lang;

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


		if(plugin.summonPearl(pearl, player())) {
			msg(Lang.pearlSummoned, pearl.getPlayerName());
		} else {
			msg(Lang.pearlCantSummon);
		}
	}
}
