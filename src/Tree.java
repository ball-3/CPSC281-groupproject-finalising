import java.util.Stack;

class Node {
    public char iData; // data item (key)
    public Node leftChild; // this node’s left child
    public Node rightChild; // this node’s right child
    private int bf;// balance factor
    public int h; // height
    public Node parent = null; // parent

    Node() {
        h = 0;
    }

    Node(Node parent) {
        this.parent = parent;
    }

    public void displayNode() // display the node
    {
        System.out.print("{");
        System.out.print(iData);
        System.out.print("} ");
    }

    public void updateH() {

        if (leftChild != null) {
            if (rightChild != null) {
                if (leftChild.h >= rightChild.h) {
                    h = leftChild.h + 1;
                } else
                    h = rightChild.h + 1;
            } else
                h = leftChild.h + 1;

        } else {
            if (rightChild != null)
                h = rightChild.h + 1;
            else {
                h = 0;
            }
        }
    }

    public int getBf() {
        // leftChild.h - rightChild.h

        if (leftChild == null) {
            if (rightChild == null) {
                bf = 0;

            } else {
                bf = -(rightChild.h + 1);

            }
        } else {
            if (rightChild == null) {
                bf = leftChild.h + 1;

            } else {
                bf = (leftChild.h + 1) - (rightChild.h + 1);

            }
        }

        return bf;
    }

}

// class BinarySearchTree {

//     private Node root; // first node of tree
//     private String output = "";
//     private int size = 0;

//     public int getSize() {
//         return size;
//     }

//     public BinarySearchTree() // constructor
//     {
//         root = null;
//     }

//     public Node getRoot() {
//         return root;
//     }

//     public Node contains(char key) {
//         Node current = root; // start at root
//         while (!(current.iData == key)) {
//             if (Character.compare(key, current.iData) < 0) // key < current.iData =-1 go left
//                 current = current.leftChild;
//             else // go right if bigger
//                 current = current.rightChild;
//             if (current == null) // if no child,
//                 return null; // didn’t find node
//         }
//         return current; // found it
//     }

//     public void insert(char id) {
//         if (root != null && contains(id) != null) {
//             System.out.println("you already have " + id);
//             return;
//         }
//         System.out.println("insert " + id);
//         Node newNode = new Node(); // make new node
//         newNode.iData = id; // insert data
//         if (root == null) // if no node in root, new node is the root
//             root = newNode;
//         else {
//             Node current = root; // start at root
//             Node parent;
//             while (true) {
//                 parent = current;
//                 if (Character.compare(id, current.iData) < 0) // id < current.iData = -1 go left (smaller)
//                 {
//                     current = current.leftChild;
//                     if (current == null) { // null means leaf, insert on left
//                         parent.leftChild = newNode;
//                         size++;
//                         return;
//                     }
//                 } else // or go right if bigger
//                 {
//                     current = current.rightChild;
//                     if (current == null) { // null means leaf, insert on right
//                         parent.rightChild = newNode;
//                         size++;
//                         return;
//                     }
//                 }
//             }
//         }
//     }

//     public boolean delete(char key) // delete node with given key
//     {
//         Node current = root;
//         Node parent = root;
//         boolean isLeftChild = true;
//         while (!(current.iData == key)) // search for node
//         {
//             parent = current;
//             if (Character.compare(key, current.iData) < 0) // key < current.iData(smaller ) ---->left
//             {
//                 isLeftChild = true;
//                 current = current.leftChild;
//             } else // bigger ---> right
//             {
//                 isLeftChild = false;
//                 current = current.rightChild;
//             }
//             if (current == null)
//                 return false; // didn’t find it
//         }
//         // found node to delete
//         // 0 children
//         if (current.leftChild == null &&
//                 current.rightChild == null) {
//             if (current == root) // if root,
//                 root = null; // tree is empty
//             else if (isLeftChild)
//                 parent.leftChild = null; // disconnect
//             else // from parent
//                 parent.rightChild = null;
//         }
//         // if no right child, replace with left subtree
//         else if (current.rightChild == null)
//             if (current == root)
//                 root = current.leftChild;
//             else if (isLeftChild)
//                 parent.leftChild = current.leftChild;
//             else
//                 parent.rightChild = current.leftChild;
//         // if no left child, replace with right subtree
//         else if (current.leftChild == null)
//             if (current == root)
//                 root = current.rightChild;
//             else if (isLeftChild)
//                 parent.leftChild = current.rightChild;
//             else
//                 parent.rightChild = current.rightChild;
//         else // two children
//         {
//             // get successor of node to delete (current)
//             Node successor = getSuccessor(current);
//             // relink
//             // connect parent of current to successor instead
//             if (current == root)
//                 root = successor;
//             else if (isLeftChild)
//                 parent.leftChild = successor;
//             else
//                 parent.rightChild = successor;
//             // connect successor to current’s left child
//             successor.leftChild = current.leftChild;
//         }
//         size--;
//         return true;
//     }

//     private Node getSuccessor(Node delNode) {
//         Node successorParent = delNode;
//         Node successor = delNode;
//         Node current = delNode.rightChild; // go to right child until no more
//         while (current != null) { // left children,
//             successorParent = successor;
//             successor = current;
//             current = current.leftChild; // go to left child
//         }
//         // if successor not
//         if (successor != delNode.rightChild) // right child,
//         { // relink
//             successorParent.leftChild = successor.rightChild;
//             successor.rightChild = delNode.rightChild;
//         }
//         return successor;
//     }

//     // recursion, display the node with inprder
//     private void inOrder(Node localRoot) {
//         if (localRoot != null) {

//             inOrder(localRoot.leftChild);
//             output = output + localRoot.iData + " ";

//             inOrder(localRoot.rightChild);
//         }
//     }

//     // return preorder traversal
//     public String toString() {
//         output = "";
//         inOrder(root);
//         return output;
//     }

// }

class AVL {
    private Node root;
    Stack<Character> RorL = new Stack<Character>();
    private Node pA;
    private boolean pALeftChild = true;
    private String output = "";
    private int size = 0;

    public int getSize() {
        return size;
    }


    public AVL() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    //same code that I used for binary search tree
    public Node contains(char key) {
        Node current = root;
        //search for the node
        while (!(current.iData==key)) {
            if (Character.compare(key, current.iData) < 0)  // key < current.iData =-1   go left
                current = current.leftChild;
            else                            // go right if bigger
                current = current.rightChild;
            if (current == null)             // if no child,
                return null;                 // didn't find the node
        }
        return current;                    // found it
    }

    public void insert(char id) {


        if (root != null && contains(id) != null) {
            System.out.println("you already have " + id);
            return;
        }


        Node newNode = new Node();    // make new node
        newNode.iData = id;           // insert data
        if (root == null) {              // if no node in root, new node is the root
            root = newNode;
        } else {

            Node A = null;
            pA = null;
            Node current = root;       // start at root
            Node parent = null;
            //find A

            while (!(current.leftChild == null && current.rightChild == null)) {
                if (current.getBf() == 1 || current.getBf() == -1) {
                    A = current;
                    if (A != root) {
                        pA = A.parent;
                        if (pA.leftChild == A) {
                            pALeftChild = true;
                        } else pALeftChild = false;

                    }

                }
                if (Character.compare(id, current.iData) < 0) { // if smaller
                    if (current.leftChild != null) {
                        parent = current;
                        current = current.leftChild;
                    } else break;

                } else {
                    if (current.rightChild != null) { // if bigger
                        parent = current;
                        current = current.rightChild;
                    } else break;

                }

            }


            //insert
            if (Character.compare(id, current.iData) < 0) {
                current.leftChild = newNode;
                newNode.parent = current;
                RorL.push('L');
                size++;
            } else {
                current.rightChild = newNode;
                newNode.parent = current;
                RorL.push('R');
                size++;
            }


            if (A == null) {
                updateHeight(newNode);
            } else if ((A.getBf() == 1 && Character.compare(id, A.iData) < 0) || (A.getBf() == -1 && Character.compare(id, A.iData) > 0)) {
                updateHeight(newNode);
            }

            //check balance

            if (A != null) {

                if (A.getBf() > 1 || A.getBf() < -1) { // if balance factor over two
                    String rotation = "" + RorL.peek() + RorL.peek();

                    switch (rotation) {
                        case "RR":
                            LLorRR(A, 'R');
                            break;
                        case "LL":
                            LLorRR(A, 'L');

                            break;
                        case "RL": //LR type
                            /*
                             * w is new node
                             *           n
                             *       n       n
                             *    n     n
                             *        w
                             *
                             * and
                             *           n
                             *               n
                             *             w
                             *
                             * both RL rotation but b change
                             *
                             *
                             * */
                            if (A.h > 2)
                                LRorRL(A, 'L');
                            else {
                                LRorRL(A, 'R');

                            }
                            break;

                        case "LR": //RL type
                            if (A.h > 2) {
                                LRorRL(A, 'R');
                            } else {
                                LRorRL(A, 'L');
                            }


                            break;
                        default:
                            System.out.println("error of char");
                            break;
                    }

                }
            }
            //in case it does not update, the parents will need height update
            current.updateH();


        }
    }

    //rotation
    private void LLorRR(Node a, char type) {
        Node b;
        Node bl;
        Node br;
        switch (type) {
            case 'R':
                b = a.rightChild;
                bl = b.leftChild;
                b.leftChild = a;
                a.rightChild = bl;
                //parent & height
                a.parent = b;
                a.h = b.h - 1;
                if (bl != null)
                    bl.parent = a;
                if (pA == null) {
                    root = b;
                    b.parent = null;

                } else {
                    b.parent = pA;
                    if (pALeftChild) {
                        pA.leftChild = b;
                    } else pA.rightChild = b;
                    updateHeight(b);

                }


                break;
            case 'L':
                b = a.leftChild;
                br = b.rightChild;
                b.rightChild = a;
                a.leftChild = br;
                //parent
                a.parent = b;
                a.h = b.h - 1;
                if (br != null)
                    br.parent = a;
                if (pA == null) {
                    root = b;
                    b.parent = null;
                } else {
                    b.parent = pA;
                    if (pALeftChild) {
                        pA.leftChild = b;
                    } else pA.rightChild = b;
                    updateHeight(b);
                }
                break;
        }



    }

    private void LRorRL(Node a, char type) {
        Node b;
        Node bl;
        Node br;
        Node c;
        Node cl;
        Node cr;

        switch (type) {
            case 'L'://RL
                //name everything
                b = a.rightChild;
                c = b.leftChild;
                cl = c.leftChild;
                cr = c.rightChild;
                //relink
                //replace c and b
                c.rightChild = b;
                b.leftChild = cr;
                a.rightChild = c;
                //rotate
                c.leftChild = a;
                a.rightChild = cl;
                //parent & height change
                a.parent = c;
                b.parent = c;

                c.h = c.h + 1;
                a.h = a.h - 2;
                b.h = b.h - 1;

                if (cr != null)
                    cr.parent = b;
                if (cl != null)
                    cl.parent = a;
                if (pA == null) {
                    root = c;
                    c.parent = null;
                } else {
                    c.parent = pA;
                    if (pALeftChild) {
                        pA.leftChild = c;
                    } else pA.rightChild = c;
                }
                if (c.parent != root)
                    updateHeight(c);

                break;
            case 'R'://RL
                //name everything
                b = a.leftChild;
                //br = b.rightChild;
                c = b.rightChild;
                cl = c.leftChild;
                cr = c.rightChild;
                //relink
                //replace c and b
                c.leftChild = b;
                b.rightChild = cl;
                a.leftChild = c;
                //rotate
                c.rightChild = a;
                a.leftChild = cr;
                //parent & height
                a.parent = c;
                b.parent = c;

                c.h = c.h + 1;
                a.h = a.h - 2;
                b.h = b.h - 1;

                if (cr != null)
                    cr.parent = a;
                if (cl != null)
                    cl.parent = b;
                if (pA == null||a ==root) {
                    root = c;
                    c.parent = null;
                } else {
                    c.parent = pA;

                    if (pALeftChild) {
                        pA.leftChild = c;
                    } else pA.rightChild = c;
                }
                if (c.parent != root)
                    updateHeight(c);
                break;
        }



    }


    private void updateHeight(Node c) {
        Node current = c;
//        current.displayNode();
//        System.out.print("(updateHeight)parent is ");
//        current.parent.displayNode();
        while (current.parent != null) {
            current.parent.updateH();
            current = current.parent;
        }
    }


    // recursion, display the node with inprder
    private void inOrder(Node localRoot) {
        if (localRoot != null) {

            inOrder(localRoot.leftChild);
            output = output + localRoot.iData + " ";

            inOrder(localRoot.rightChild);
        }
    }

    // return preorder traversal
    public String toString() {
        output = "";
        inOrder(root);
        return output;
    }


}

