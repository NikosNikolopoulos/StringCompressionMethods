 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

//import adaptiveHuffman.BitByteOutputStream;
//import adaptiveHuffman.tree.*;

public class Encoder {
    
    public FileInputStream in = null;
    public BitByteOutputStream out = null;
    
    public static void main(String[] args) {
        if(args.length < 2) {
            System.err.println("Usage: adaptiveHuffman.encoder inputFile outputFile");
        }
        else {
            Encoder enc = new Encoder(args[0],args[1]);
            Tree tree = new Tree();
            File in = new File(args[0]);
            long t = System.nanoTime();
            enc.encode(tree);
            long at = System.nanoTime();
            File out = new File(args[1]);
            System.out.println("Finished compression of: "+in.getName()+" in "+(float)(at-t)/1000000+" ms");
            System.out.println("Original size: "+in.length()+" bytes");
            System.out.println("Compressed size: "+out.length()+" bytes");
            System.out.println("Compression ratio: "+((float)in.length()/(float)out.length()));
        }
    }
    
    public Encoder(String in, String out) {
        try {
            this.in = new FileInputStream(in);
            this.out = new BitByteOutputStream(new FileOutputStream(out));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public void encode(Tree tree) {
        
        try {
            
            int c = 0;
            
            while((c = in.read()) != -1) {
                ArrayList<Boolean> buffer = new ArrayList<Boolean>();
                if (tree.contains(c)) {
                    
                    int len = tree.getCode(c,true,buffer);
                    for(len=len-1 ;len>=0;len--){
                        out.writeBit(buffer.get(len));
                    }
                    tree.insertInto((int)c);
                }
                else {
                    int len = tree.getCode(c, false,buffer);
                    for(len=len-1 ;len>=0;len--){
                        out.writeBit(buffer.get(len));
                    }
                    out.writeByte(c);
                    tree.insertInto(c);
                }
            }
            out.flush();
        }
        catch (IOException e) {
            System.err.println("Error reading from input");
            e.printStackTrace();
        }
        finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null) {
                out.close();
            }
        }
        printCode(tree.root," ");
    }

    // Recursive function to print the 
    // huffman-code through the tree traversal. 
    // Here s is the huffman - code generated. 
    public static void printCode(Node root, String s) 
    { 
  
        // Base case: if the left and right are null 
        // then its a leaf node and we print 
        // the code s generated by traversing the tree. 
        if (root.left == null && root.right == null ) 
        { 
            // c is the character in the node 
            System.out.println(":" + s); 
            return; 
        } 
  
        // If we go to left then add "0" to the code. 
        // If we go to the right add "1" to the code. 
  
        // Recursive calls for left and 
        // right sub-tree of the generated tree. 
        printCode(root.left, s + "0"); 
        printCode(root.right, s + "1"); 
    } 
}