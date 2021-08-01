
/**
 * Comparator makes it possible to compare the node
 * on the basis of one of its attributes. 
 * Here the attribute compared will be the 
 * data values of the nodes.
 * @author Nikos Nikolopoulos
 * @version 09/12/20118
 */
import java.util.Comparator; 
public class MyComparator implements Comparator<HuffmanNode> 
{ 
    public int compare(HuffmanNode x, HuffmanNode y) 
    { 
        return x.data - y.data; 
    } 
} 
