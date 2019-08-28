package github.lightningcreations.lcjei.service;

import java.util.Optional;
import java.util.ServiceLoader;

/**
 * Class for looking up the provider for a particular engine type
 * @author chorm
 *
 */
public class EngineLookup {

	private EngineLookup() {
	}
	
	/**
	 * Lookups the JEI Provider for a particular engine name.
	 *  Returns an optional which contains an instance of JEIServiceProvider for which {@link JEIServiceProvider#getEngineName()} returns the same string as name (case-sensitive),
	 *   if such a provider can be found. Otherwise returns an empty optional.
	 */
	public static Optional<JEIServiceProvider<?>> getByEngineName(String name){
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ServiceLoader<JEIServiceProvider<?>> loader = ServiceLoader.load((Class<JEIServiceProvider<?>>)(Class<? extends JEIServiceProvider>)JEIServiceProvider.class);
		for(JEIServiceProvider<?> provider:loader)
			if(provider.getEngineName().equals(name))
				return Optional.of(provider);
		return Optional.empty();
	}
	
	
	/**
	 * Looks up the Provider for a particular engine that uses games of the type GameType. 
	 * Returns an optional containing an instance of JEIServiceProvider, such that {@link JEIServiceProvider#getGameClass()} returns the same class as type
	 *  if such a provider can be found. Otherwise returns an empty Optional.
	 * @param <GameType> The type of Games used by the engine
	 */
	@SuppressWarnings("unchecked")
	public static <GameType> Optional<JEIServiceProvider<GameType>> getByEngineClass(Class<GameType> type) {
		@SuppressWarnings("rawtypes")
		ServiceLoader<JEIServiceProvider<?>> loader = ServiceLoader.load((Class<JEIServiceProvider<?>>)(Class<? extends JEIServiceProvider>)JEIServiceProvider.class);
		for(JEIServiceProvider<?> provider:loader)
			if(provider.getGameClass().equals(type))
				return Optional.of((JEIServiceProvider<GameType>)provider);
		return Optional.empty();
	}

}
