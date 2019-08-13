package github.lightningcreations.lcjei;

import java.util.UUID;

public interface IGameInfo<GameType> {
	public Class<? extends GameType> getGameClass();
	public Class<? extends IEngineInterface<GameType>> getEngineInterfaceClass();
	public String getName();
	public String getVersion();
	public UUID getGameId();
}
