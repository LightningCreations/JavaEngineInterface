package github.lightningcreations.lcjei.resources;

/**
 * Represents a bi-directional function between the keys of a Host resource set and a Guest resource set.<br/>
 * This interface defines a strict contract that {@link #mapToGuest(Object)} and {@link #mapToHost(Object)} must be inverse operations. 
 *  That is, for any host key k, mapToHost(mapToGuest(k)) must be an identitiy operation.<br/>
 * Additionally, the function must be a perfect function from the guest, that is the domain of the function MUST be the entire Guest keyspace.
 * @author chorm
 *
 * @param <GuestKeyType>
 * @param <HostKeyType>
 */
public interface ResourceKeyMapper<GuestKeyType, HostKeyType> {
	/**
	 * Returns the host key that backs the guest key in a MappedResourceSet.<br/>
	 * This method MUST be equality preserving and a perfect inverse to {@link #mapToGuest(Object)}.<br/>
	 * Failure to abide by this contract may have unexpected results.<br/>
	 * The mapping function does not permit null inputs and MUST NOT return null.
	 * @param guestKey the key used for the guest ResourceSet.
	 * @throws NullPointerException if guestKey is null.
	 */
	public HostKeyType mapToHost(GuestKeyType guestKey);
	/**
	 * Returns the guest key that can be used with a MappedResourceSet to get the resource with hostKey.<br/>
	 * This method MUST be equality preserving and a perfect inverse to {{@link #mapToHost(Object)}.
	 * Failure to abide by this contract may have unexpected results.<br/>
	 * The mapping function does not permit null inputs and MUST NOT return null.<br/>
	 * If hostKey is not in the range of the function, {{@link #mapToGuest(Object)} returns null.
	 * @param hostKey the key used for the host ResourceSet.
	 * @throws NullPointerExcept if hostKey is null.
	 */
	public GuestKeyType mapToGuest(HostKeyType hostKey);
}
