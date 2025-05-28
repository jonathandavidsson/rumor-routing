import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * An interface used by Agent and Request.
 */
public interface Messenger {

    /**
     * traverses in the map
     */
    boolean traverse();


    /**
     * Gets the messengers Node
     * @return Node
     */
    Node getNode();
}
