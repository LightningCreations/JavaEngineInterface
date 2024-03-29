package github.lightningcreations.lcjei.wrapper;

import java.awt.Container;

import github.lightningcreations.lcjei.IEngineInterface;

/**
 * Represents a Engine that can be used to insert a Game belonging to a different Engine, called the Guest Engine, into a Host Engine. <br/>
 * Most methods are forwarded to the Guest, possible gathering resources from the host.
 * @author chorm
 *
 * @param <GuestGameType> The type of the Game used by the Guest Engine.
 * @param <HostGameType> The type of the Game used by the Host Engine.
 */
public final class WrappedEngine<GuestGameType, HostGameType>
		implements IEngineInterface<WrappedGame<GuestGameType, HostGameType>> {
	
	private IEngineInterface<GuestGameType> guest;
	private IEngineInterface<HostGameType> host;
	private WrappedGame<GuestGameType,HostGameType> wrapped;
	private Container drawContainer;
	/**
	 * Constructs a new Wrapped Engine with a given guest engine, host engine, and host game.
	 * @param guest The Interface to the Guest Engine.
	 * @param host The Interface to the Host Engine.
	 * @param hostGame The Host Game that is wrapping/embedding the Guest Game.
	 */
	public WrappedEngine(IEngineInterface<GuestGameType> guest,IEngineInterface<HostGameType> host,HostGameType hostGame) {
		this.guest = guest;
		this.host = host;
		this.wrapped = new WrappedGame<>(guest,host,hostGame);
	}
	
	@Override
	public boolean initialize(Container c) throws IllegalStateException {
		if(guest.initialize(c)) {
			drawContainer = c;
			return true;
		}else
			drawContainer = guest.getCurrentDrawContainer();
		return false;
	}

	/**
	 * Initializes the guest to draw to the host's current draw container as though by {{@link #initialize(Container)}.
	 */
	@Override
	public void initialize() throws IllegalStateException {
		initialize(host.getCurrentDrawContainer());
	}

	@Override
	public void destroy() throws IllegalStateException {
		guest.destroy();
	}

	@Override
	public void run() throws IllegalStateException {
		guest.run();
	}

	@Override
	public Container getCurrentDrawContainer() {
		// TODO Auto-generated method stub
		return drawContainer;
	}

	@Override
	public WrappedGame<GuestGameType, HostGameType> getGameObject() {
		// TODO Auto-generated method stub
		return wrapped;
	}

	@Override
	public void suspend() throws IllegalStateException {
		guest.suspend();
	}

	@Override
	public void resume() throws IllegalStateException {
		guest.resume();
	}

}
