package github.lightningcreations.lcjei.resources;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Represents a Set of Resources in some unspecified repository.<br/>
 * The ResourceSet is guaranteed to reflect the state of the underlying repository at creation type
 *  and after each reload.<br/>
 * @author chorm
 * @param <ResourceKey>
 */
public interface ResourceSet<ResourceKey> {
	/**
	 * If key is the name of a resource defined by this set, returns the resource with that name. Otherwise returns an empty optional.
	 * This method MUST be thread safe with {@link #reload()}
	 * @param key
	 */
	public Optional<Resource<ResourceKey>> getResource(ResourceKey key);
	
	/**
	 * Streams the keys of this repository. This method shall be late binding, and the full result of all terminal operations shall be atomic to all updates to the resource set,
	 *  either through modification of the underlying resource set, or calls to {@link #reload()}<br/>
	 */
	public Stream<ResourceKey> keys();
	
	/**
	 * Reloads the resource set from the underlying repository. This invalidates all resources returned by {@link #getResource(Object)}.
	 * This method is thread-safe with {@link #getResource(Object)}.
	 */
	public void reload();
}
