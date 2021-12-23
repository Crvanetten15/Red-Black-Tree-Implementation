//Some code below was provided by the Professor (Dr. Hadong Wang)
//Marked code is my implementation


import java.util.Comparator;

public class RBTreeMap extends AbstractMap {
    private int n;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private RBNode root;
    private RBNode NIL = new RBNode();
    private RBNodeComparator comp = new RBNodeComparator();

    //My Implementation
    private String list = "| Preorder-Traversal | START || ";

    RBTreeMap() { //AVL Tree Map Implementation

    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    //My Implementation
    public Object get(Object key) { //changed from AVL Tree Map
        String placeholder = (String) key;
        RBNode RBcheck = root;
        int checker = placeholder.compareTo((String) root.getKey());
        do {
            if (RBcheck == NIL) {
                break;
            }

            if (checker == 0) {
                return RBcheck.getValue();
            } else if (checker < 0) {
                RBcheck = RBcheck.getLeft();
            } else {
                RBcheck = RBcheck.getRight();
            }
            checker = placeholder.compareTo((String) RBcheck.getKey());
        } while (true);

        return null;
    }

    //My Interpretation of PsuedoCode Provided
    public Object put(Object key, Object value) {
        RBNode newNode = new RBNode((String) key, (String) value);
        newNode.setLeft(NIL);
        newNode.setRight(NIL);
        if (root == null) {
            root = newNode;
            n++;
        } else {
            insertion(root, newNode);
            insert_fixup(newNode);
        }
        return newNode;
    }
    //My Interpretation of PsuedoCode Provided
    private void insertion(RBNode r, RBNode z) {
        //Insertion
        if(((String)z.getKey()).compareTo((String)r.getKey()) == 0){
            r.setValue(z.getValue());
        }
        else if(((String)z.getKey()).compareTo((String)r.getKey()) < 0){

            if(r.getLeft() == NIL){
                r.setLeft(z);
                z.setParent(r);
                n++;
            }
            else {
                insertion(r.getLeft(), z);
            }
        }
        else{
            if(r.getRight() == NIL){
                r.setRight(z);
                z.setParent(r);
                n++;
            }
            else {
                insertion(r.getRight(), z);
            }
        }
    }

    //My Interpretation of PsuedoCode Provided
    private void insert_fixup(RBNode z) {
        //Insert FixUp Method
        RBNode p = z.getParent();
        if(p == null) return;
        if(p.getColor() == BLACK) return;
        RBNode Pp = p.getParent();
        if (Pp == null){
            p.setColor(BLACK);
            return;
        }
        if (p == Pp.getLeft()) {
            RBNode y = Pp.getRight();
            if (y != null && y.getColor() == RED) { //Case 1 (left) -- Uncle Red --
                // Change parent to black, uncle to black, Grandparent to Red
                p.setColor(BLACK);
                y.setColor(BLACK);
                Pp.setColor(RED);
                insert_fixup(Pp);
            } else {
                if (z == p.getRight()) { //case 2 (left) --
                    // left rotation at parent, set parent to child to set up for case 3
                    left_rotate(p);
                    p = z;
                }
                right_rotate(Pp);        //case 3 (Left) --
                // After case 2 -- Rotation at Grandpa, then set parent to black and grandpa to Red
                p.setColor(BLACK);
                Pp.setColor(RED);
            }
        }
        else {
            RBNode y = Pp.getLeft();
            if (y != null && y.getColor() == RED) { //Case 1 (Right) -- Uncle Red --
                // Change parent to black, uncle to black, Grandparent to Red
                p.setColor(BLACK);
                y.setColor(BLACK);
                Pp.setColor(RED);
                insert_fixup(Pp);
            } else {
                if (z == p.getLeft()) { //case 2 (Right) --
                    // Right rotation at parent, set parent to child to set up for case 3
                    right_rotate(p);
                    p = z;
                }
                left_rotate(Pp);        //case 3 (Right) --
                // After case 2 -- Rotation at Grandpa, then set parent to black and grandpa to Red
                p.setColor(BLACK);
                Pp.setColor(RED);
            }
        }
    }

    //My Interpretation of PsuedoCode Provided
    //Performs Rotations
    private RBNode right_rotate(RBNode x) {
        RBNode p = x.getParent();
        RBNode y = x.getLeft();
        RBNode v = y.getRight();
        x.setLeft(v);

        if (v != NIL) {
            v.setParent(x);
        }
        y.setParent(p);
        if (p == null) {
            root = y;
        } else if (x == p.getRight()) {
            p.setRight(y);
        } else {
            p.setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
        return y;
    }

    //My Interpretation of PsuedoCode Provided
    //Performs Rotations
    private RBNode left_rotate(RBNode x) {
        RBNode p = x.getParent();
        RBNode y = x.getRight();
        RBNode v = y.getLeft();
        x.setRight(v);

        if (v != NIL) {
            v.setParent(x);
        }
        y.setParent(p);
        if (p == null) {
            root = y;
        } else if (x == p.getLeft()) {
            p.setLeft(y);
        } else {
            p.setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
        return y;
    }

    public Object remove(Object key) {
        return null;
    }

    private class RBNode extends MapEntry {
        private boolean color;
        private RBNode left;
        private RBNode right;
        private RBNode parent;

        RBNode() {// NIL Node
            super(null, null);
            color = BLACK;
            parent = null;
            left = null;
            right = null;
        }

        RBNode(String key, String value) {
            super(key, value);
            color = RED;
        }

        protected RBNode getLeft() {
            return left;
        }

        protected RBNode getRight() {
            return right;
        }

        protected RBNode getParent() {
            return parent;
        }

        protected void setLeft(RBNode l) {
            left = l;
        }

        protected void setRight(RBNode r) {
            right = r;
        }

        protected void setParent(RBNode p) {
            parent = p;
        }

        protected boolean getColor() {
            return color;
        }

        protected void setColor(boolean c) {
            color = c;
        }
    }

    private class RBNodeComparator implements Comparator {
        public int compare(Object a, Object b) {
            String aa = ((RBNode) a).getKey();
            String bb = ((RBNode) b).getKey();
            if (a == null && b == null) return 0;
            else if (a == null) return -1;
            else if (b == null) return 1;
            else {
                int size = Math.min(aa.length(), bb.length());
                for (int i = 0; i < size; i++) {
                    if (aa.charAt(i) > bb.charAt(i))
                        return 1;
                    else if (aa.charAt(i) < bb.charAt(i))
                        return -1;
                }
                // first size characters are equal to each other
                if (size == aa.length()) return -1;
                return 1;
            }
        }
    }

    //My Implementation
    //String Method to print the order
    public String toString() {
        PreOrderTraversal(root, this.list);
        return (this.list + "END ||");
        //return (root + root.getKey());
    }


    //My Implementation
    //Method to Traverse // Also adds color
    private void PreOrderTraversal(RBNode place, String list) {
        if (place == NIL) {
            return;
        }

        this.list = this.list.concat(place.getKey() + " - "+ (place.getColor() ? "[Red]":"[Black]") + " || ");
        if (place.getLeft() != NIL) {
            PreOrderTraversal(place.getLeft(), list);
        }
        if (place.getRight() != NIL) {
            PreOrderTraversal(place.getRight(), list);
        }

    }
}