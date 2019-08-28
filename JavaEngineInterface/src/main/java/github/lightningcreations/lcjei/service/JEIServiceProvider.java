package github.lightningcreations.lcjei.service;

import java.util.Optional;

import github.lightningcreations.lcjei.IEngineInterface;

/**
 * Provider type for the JaveEngineInterface
 * @author chorm
 *
 * @param <GameType> The type of games used by the underlying provider
 */
public interface JEIServiceProvider<GameType> {
	/**
	 * Runtime mapping of GameType.<br/>
	 * If GameType is com.example.ExampleClass, then returns com.example.ExampleClass.class.
	 */
	public Class<GameType> getGameClass();
	/**
	 * Returns the name of the Engine.
	 */
	public String getEngineName();
	/**
	 * Returns a new engine interface that will start with game, in an uninitialized state.<br/>
	 * This method may check an unspecified permission with the System or some other unspecified SecurityManager. This can result in an {@link SecurityException} being thrown.<br/>
	 */
	public IEngineInterface<GameType> newEngine(GameType game);
	
	/**
	 * If there is an active engine of this type, returns an optional containing an interface to that engine. 
	 *  Otherwise returns an empty optional.<br/>
	 * If this Service Provider does not support this method, an {@link UnsupportedOperationException} is thrown.<br/>
	 * This method may check an unspecified permission with the System or some other unspecified SecurityManager. This can result in an {@link SecurityException} being thrown.<br/>
	 * Even if the active engine does not change, return values of this method are not required to compare equal. Do not rely on this behavior in any case.<br/>
	 * If there is a returned engine, then it is in an unspecified state. 
	 * If there are multiple active engines for this engine type, the one for which an interface is returned is unspecified.<br/>
	 * @throws UnsupportedOperationException if this provider does not support obtaining an interface to an active engine.
	 */
	public Optional<IEngineInterface<GameType>> getActiveEngineInterface();
}
