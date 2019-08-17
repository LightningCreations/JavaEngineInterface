package github.lightningcreations.lcjei.resources;

import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;
import java.util.Optional;

/**
 * Represents a Raw object somewhere which can be read and is named by a ResourceKey.
 * @author chorm
 *
 * @param <ResourceKey> The type of the Key used to identify the resource.
 */
public interface Resource<ResourceKey> {
	/**
	 * Returns a Stream that can read all bytes of the resource.
	 */
	public InputStream getReadStream();
	/**
	 * Returns a Channel that can read all bytes of the resource.
	 */
	public SeekableByteChannel getReadChannel();
	/**
	 * Returns the name of the resource as a ResourceKey.
	 */
	public ResourceKey getKey();
}
