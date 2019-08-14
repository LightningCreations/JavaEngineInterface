package github.lightningcreations.lcjei.resources;

import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Defines a set of resources mapped from one key type, to annother.
 * @author chorm
 *
 * @param <GuestKeyType> The type of keys for the resource set being exposed
 * @param <HostKeyType> The type of keys for the resource set being mapped.
 */
public final class MappedResourceSet<GuestKeyType, HostKeyType> implements ResourceSet<GuestKeyType> {
	
	private final ResourceSet<HostKeyType> hostSet;
	private final ResourceKeyMapper<GuestKeyType,HostKeyType> keyMapper;
	
	/**
	 * Constructs a new MappedResourceSet with a given hostSet and a bidirectional key mapping function.<br/>
	 * Neither hostSet or keyMapper may be null.
	 * @param hostSet The resource set used by the Host Engine.
	 * @param keyMapper The mapping function between the Host Key Set and the Guest Key Set.
	 * @throws NullPointerException if either hostSet or keyMapper are null.
	 */
	public MappedResourceSet(ResourceSet<HostKeyType> hostSet,ResourceKeyMapper<GuestKeyType,HostKeyType> keyMapper) {
		this.hostSet = Objects.requireNonNull(hostSet);
		this.keyMapper = Objects.requireNonNull(keyMapper);
	}
	
	
	
	private static final class MappedResource<GuestKeyType,HostKeyType> implements Resource<GuestKeyType>{
		private Resource<HostKeyType> hostResource;
		private GuestKeyType key;
		
		MappedResource(Resource<HostKeyType> hostResource,GuestKeyType key){
			this.hostResource = hostResource;
			this.key = key;
		}
		
		@Override
		public InputStream getReadStream() {
			// TODO Auto-generated method stub
			return hostResource.getReadStream();
		}

		@Override
		public SeekableByteChannel getReadChannel() {
			// TODO Auto-generated method stub
			return hostResource.getReadChannel();
		}

		@Override
		public GuestKeyType getKey() {
			// TODO Auto-generated method stub
			return key;
		}
		
	}
	
	/**
	 * If the guest key maps to a key which is present on the host, then returns a Resource for that guest key, that is backed by the resource on the host set for the equivalent host key.<br/>
	 * Otherwise returns an empty optional.<br/>
	 */
	@Override
	public Optional<Resource<GuestKeyType>> getResource(GuestKeyType key) {
		Objects.requireNonNull(key);
		return hostSet.getResource(keyMapper.mapToHost(key))
				.map(host->new MappedResource<GuestKeyType,HostKeyType>(host,key));
	}
	
	/**
	 * Returns the set of Host Keys, mapped to the guest Key space.
	 * If any key in the Host Key space of the underlying set is not mappable to the guest key set, that key is dropped from the result stream.
	 */
	@Override
	public Stream<GuestKeyType> keys() {
		// TODO Auto-generated method stub
		return hostSet.keys().map(keyMapper::mapToGuest).filter(k->k!=null);
	}
	
	/**
	 * Causes the hostSet to be reloaded.
	 */
	@Override
	public void reload() {
		hostSet.reload();
	}

}
