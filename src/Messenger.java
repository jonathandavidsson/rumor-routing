/**
 * An interface used by Agent and Request.
 */
public interface Messenger {

    /**
     * traverses in the map
     */
    public  void traverse();


    /**
     * Gets the messengers Event
     * @return Event
     */
    public Event getEvent();

    /**
     * Gets the messengers Node
     * @return Node
     */
    public Node getNode();
}
