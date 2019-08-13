package github.lightningcreations.lcjei;

import java.awt.Container;


public interface IEngineInterface<GameType> extends Runnable {
	
	/**
	 * Initializes the Engine referenced by this Interface.<br/>
	 * The Engine is requested, though not strictly required, to render the graphical output to Component.<br/>
	 * 
	 * If c is null, has the same effect as {@link IEngineInterface#initialize()}.<br/>
	 * This may not be called after a call to initialize, unless a subsequent call to destroy has been made.
	 * If this method is called in violation of this requirement, an {@link java.lang.IllegalStateException} is thrown.<br/>
	 * 
	 * After this call, the engine MUST be in an Initialized State unless this method threw an exception<br/>
	 * It must be possible to draw to c, and c MUST be visible or an {@link java.lang.IllegalArgumentException} is thrown.<br/>
	 * 
	 * @param c The container to draw to. This parameter may be ignored and may be null. If ignored or null, false is returned, and the results are identical to {@link IEngineInterface#initialize()}. 
	 * @return true iff c is not null, and the implementation initializes the state to draw to c.
	 * 
	 * @throws IllegalStateException if the Engine has already been initialized and not yet destroyed.
	 * @throws IllegalArgumentException if c is not visible, cannot be made visible by a call to setVisible(true), or it is otherwise not possible to paint c.
	 */
	public boolean initialize(Container c) throws IllegalStateException;
	
	/**
	 * Initializes the Engine in an Implementation-Specific manner.<br/>
	 * Usually the drawing pane is a frame created by the implementation.<br/>
	 * This method may not be called after it or {@link IEngineInterface#initialize(Container)} succesfully returns, until a call to destroy() sucessfully returns. 
	 * Attempts to call this method in violation of this contract causes a {@link java.lang.IllegalStateException} to be thrown.<br/>
	 * After this call, the engine shall be in an initialized state.
	 * 
	 * @throws IllegalStateException if the Engine has already been initialized and not yet destroyed
	 */
	public void initialize() throws IllegalStateException;
	
	/**
	 * Finalizes the engine and destroys any implementation created frame. <br/>
	 * If the engine is drawing to a user supplied Container passed via {@link IEngineInterface#initialize(Container)}, this container is not freed or cleared.<br/>
	 * This method may only be called after a call to any initialize that succesfully returns, but not after a call to destroy that succesfully returns which is not followed by a such a call to initialized.
	 * Attempting to call this method in violation of this contract causes a {@link java.lang.IllegalStateException}.
	 * After this call, the engine shall be in an uninitialized state and can be discarded or reused.<br/>
	 * 
	 * The results of garbage collecting an EngineInterface object that refers to an engine that has been initialized but not destroyed are undefined.
	 * @throws IllegalStateException if the Engine has not yet been initialized or has been destroyed without being reinitialized
	 */
	public void destroy()throws IllegalStateException;
	
	/**
	 * Executes the game.<br/>
	 * This method MUST only be called between succesful calls of initialize and destroy.
	 * 
	 * @throws IllegalStateException If the method is called before the Engine is initialized, or after it is destroyed but before it has been reininitialized.
	 */
	public void run()throws IllegalStateException;
	
	/**
	 * Obtains a container, that when drawn to, draws to the same pane as the Engine is currently drawing to.<br/>
	 * If the engine was initialized with a call to {@link IEngineInterface#initialize(Container)} for a non-null Container c,
	 *  and that call returned true, then Container returns c or a child of c. <br/>
	 * Otherwise the result is unspecified. Such a container is invalidated by a call to {@link IEngineInterface#destroy()},
	 *  and use of that object passed that point has undefined results.<br/>
	 * This method MUST be called between successful calls of initialize and destroy. 
	 *  Calling this method in violation of this contract causes a {@link java.lang.IllegalStateException} to be thrown.
	 *  
	 *  @throws IllegalStateException If the method is called before the Engine is initialized, or after it is destroyed but before it has been reininitialized.
	 */
	public Container getCurrentDrawContainer();
	
	/**
	 * Gets the internal game object. <br/>
	 * The result of this method is unspecified. <br/>
	 * Unlike other methods, this can be called at any time.
	 */
	public GameType getGameObject();
	
	/**
	 * Obtains information about the current game object.<br/>
	 * This is an optional operation. By default it returns null.<br/>
	 * Implementations are permitted to throw a {@link java.lang.UnsupportedOperationException}.
	 */
	public default IGameInfo<GameType> getGameInfo() throws UnsupportedOperationException{
		return null;
	}
}
