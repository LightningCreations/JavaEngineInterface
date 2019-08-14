package github.lightningcreations.lcjei;

import java.util.UUID;

/**
 * Class that represents information about a Game.
 * @author chorm
 *
 * @param <GameType> The type of the game
 */
public interface IGameInfo<GameType> {
	/**
	 * Gets the actual class of the game this represents.
	 */
	public Class<? extends GameType> getGameClass();
	/**
	 * Gets the class of the Engine Interface.
	 */
	public Class<? extends IEngineInterface<GameType>> getEngineInterfaceClass();
	/**
	 * Gets the Game's Name.
	 */
	public String getName();
	/**
	 * Gets the version of the game. Returns null if the engine does not specify versioned games.
	 */
	public String getVersion();
	/**
	 * Gets the Unique Id of the game. If the engine does not specify uuids for games, 
	 * 	then this Shall be a Version 3 or 5 uuid. The Id Should be generated using the Engine Name as a prefix followed by some separater, then the game name or id.<br/>
	 */
	public UUID getGameId();
}
