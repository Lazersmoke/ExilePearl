package com.devotedmc.ExilePearl;

import org.bukkit.entity.Player;

public interface PearlManager extends PearlAccess {

	/**
	 * Loads all pearls from storage
	 */
	void loadPearls();
	
	/**
	 * Performs a health decay operation on all pearls
	 */
	void decayPearls();

	/**
	 * Adds a pearl broadcast request for a player
	 * @param player The player requested
	 * @param pearl The pearl instance
	 */
	void addBroadcastRequest(Player player, ExilePearl pearl);
	
	/**
	 * Gets a pending broadcast request for a player
	 * @param player The player to check
	 * @return The pearl that requested a broadcast if it exists
	 */
	ExilePearl getBroadcastRequest(Player player);
	
	/**
	 * Removes a broadcast request for a player
	 * @param player The player to remove
	 */
	void removeBroadcastRequest(Player player);

	/**
	 * Requests the player in the pearl to be summoned
	 * @param summoner The player requesting the summon
	 * @param pearl The pearl instance
	 */
	void requestSummon(Player summoner,ExilePearl pearl);
	
	/**
	 * Checks if the given pearl has been requested to summon
	 * @param pearl The pearl instance
	 * @return The player that summoned the pearl, or null for no summon requested
	 */
	Player getSummoner(ExilePearl pearl);
}
