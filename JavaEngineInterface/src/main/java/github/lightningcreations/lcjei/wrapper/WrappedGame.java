package github.lightningcreations.lcjei.wrapper;

import github.lightningcreations.lcjei.IEngineInterface;

/**
 * The Game Type used by a WrappedEngine.
 * @author chorm
 *
 * @param <GuestGameType> The type of the Game used by the Guest Engine.
 * @param <HostGameType> The type of the Game used by the Host Engine.
 */
public final class WrappedGame<GuestGameType, HostGameType> {
	
	private final GuestGameType guest;
	private final HostGameType host;
	private final IEngineInterface<GuestGameType> guestEngine;
	private final IEngineInterface<HostGameType> hostEngine;
	
	WrappedGame(IEngineInterface<GuestGameType> guestEngine,IEngineInterface<HostGameType> hostEngine,HostGameType hostGame) {
		this.guest = guestEngine.getGameObject();
		this.guestEngine = guestEngine;
		this.host = hostGame;
		this.hostEngine = hostEngine;
	}
	
	/**
	 * @return the Guest Game object.
	 */
	public GuestGameType getGuestGame() {
		return guest;
	}
	/**
	 * @return the Host Game object.
	 */
	public HostGameType getHostGame() {
		return host;
	}
	
	/**
	 * Obtains and returns an interface to the guest Engine.
	 */
	public IEngineInterface<GuestGameType> getGuestEngine(){
		return guestEngine;
	}
	
	/**
	 * Obtains and returns an interface to the host Engine.
	 */
	public IEngineInterface<HostGameType> getHostEngine(){
		return hostEngine;
	}
}
