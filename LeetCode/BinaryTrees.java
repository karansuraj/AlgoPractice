import java.util.Arrays;

//This code is unfinished and must be fixed up to work
public class BinaryTrees {
    //Class for binary tree
    BinaryTrees left;
    BinaryTrees right;
    int val;
    public BinaryTrees(int x) {
        val = x;
    }
    void createTreeFromList(int[] lst){
        BinaryTrees root = this;
        if(lst.length>=1) root.val = lst[0];
        for(int i = 1; i<lst.length; i++){
            if(root.left==null) { //Populate left leaf
                root.left = new BinaryTrees(lst[i]);
                continue;
            } else if(root.right==null){ //Populate right leaf
                root.right = new BinaryTrees(lst[i]);
                continue;
            } else{
                if(root.left.right!=null){
                    root.left.createTreeFromList(Arrays.copyOfRange(lst, i, lst.length));
                } else{
                    root.right.createTreeFromList(Arrays.copyOfRange(lst, i, lst.length));
                }
            }

        }
        //eturn root;
    }
}
