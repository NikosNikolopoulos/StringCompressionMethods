
/**
 * Node class is the basic structure 
 * of each node present in the Huffman-tree. 
 * @author Nikos Nikolopoulos
 * @version 09/12/2018
 */
public class HuffmanNode 
{ 
    int data;           // data corresponds to the frequencies of each symbol.
    char c;             // c corresponds to the encrypted symbol.
    int index;          // index field is useful during node enumeration.
  
    HuffmanNode left;   // left corresponds to the left child of the node.
    HuffmanNode right;  // right corresponds to the right child of the node. 
    HuffmanNode parent; // parent corresponds to the parent of the node. 
    
    public HuffmanNode()
    {
        data=0;
        left=null;
        right=null;
    }
    
    public void setLeftHuffmanNode(HuffmanNode l)
    {
        // This constraint of adding only leaf-Nodes to the 
        // tree prevents the user from selecting illegal nodes 
        // as children.
        if(left==null && isLeaf(l))
            left=l;
        else 
            {
                System.out.println("This action is illegal because left child already exists");
                System.out.println("or the node you selected to add is not a leaf!");
            }
    }
    
    public void setRightHuffmanNode(HuffmanNode r)
    {
        // This constraint of adding only leaf-Nodes to the 
        // tree prevents the user from selecting illegal nodes 
        // as children.
        if(right==null && isLeaf(r))
            right=r;
        else 
            {
                System.out.println("This action is illegal because right child already exists");
                System.out.println("or the node you selected to add is not a leaf!");
            }
    }
    
    public void setDataHuffmanNode(int data)
    {
        this.data=data;
    }
    
    public void setCharHuffmanNode(char c)
    {
        this.c=c;
    }
    
    public void setIndexHuffmanNode(int index)
    {
        this.index=index;
    }
    
    public HuffmanNode getParent(HuffmanNode n)
    {
        return parent;
    }
    
    public boolean isLeaf(HuffmanNode node)
    {
        if(node.left==null && node.right==null)
            return true;
        else
            return false;
    }
} 
