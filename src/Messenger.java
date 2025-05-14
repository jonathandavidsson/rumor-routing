/**
 * An interface used by Agent and Request.
 */
public interface Messenger {

    /**
     * traverses in the map
     */
    void traverse();


    /**
     * Gets the messengers Event
     * @return Event
     */
    Event getEvent();

    /**
     * Gets the messengers Node
     * @return Node
     */
    Node getNode();
}
