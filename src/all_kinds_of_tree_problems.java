import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class all_kinds_of_tree_problems {

	
	//Binary Tree Inorder Traversal
	//in-order traversal for a BST means from the smallest node iterate to its' largest node
	//use inorder to traversal left sub tree
	//visit root
	//use inorder to traversal right sub tree
	//使用stack，从root开始，一直像里面push当前node然后将cur = cur.left（）。这样当我们
	//遇到null的时候说明到了当前sub tree的最左端。这时候开始pop， 然后将cur node设成pop
	//出来的node的rightchild。  repeat over
	public List<Integer> inorderTraversal(TreeNode curr)
	{
		Stack<TreeNode> todo = new Stack<TreeNode>();
		ArrayList<Integer> res = new ArrayList<Integer>();
		while(!todo.isEmpty()||curr!= null)
		{
			if(curr != null)
			{
				todo.add(curr);
				curr = curr.left;
			}
			else
			{
				curr = (TreeNode)todo.pop();
				res.add(new Integer(curr.val));
				curr = curr.right;
			}
		}
		return res;
	}
	
	//Unique Binary Search Trees
	//Given n, how many structurally unique BST's 
	//(binary search trees) that store values 1...n?
	//1到n的任意一个数都有可能是root，当前root的不同structure = 1到（root-1）不同的structure乘上root+1到n不同的structure
	//用一个cache去加速
	
	public static int numTrees(int n)
	{
		int[] map = new int[n+1];
		return numTrees_helper(n, map);
	}
	public static int numTrees_helper(int n, int[] map)
	{
		if(map[n] != 0)
			return map[n];
		if(n == 1)
		{	
			map[1] = 1;  //base case
			return 1;
		}
		int count = 0;
		for(int i = 1; i < n; i++)
		{
			count += numTrees_helper(i-1,map) + numTrees_helper(n-i, map);
		}
		map[n] = count;
		return count;
	}

	//unique Binary search tree II
	//list out all the possibilities;
	//use recursion and assemble the whole tree by adding 2 node in each layer.
	//two layers works in parallel for each current root.
	public List<TreeNode> generate(int start, int end)
	{
		List<TreeNode> trees = new ArrayList<TreeNode>();
		if(start > end)
		{
			trees.add(null);   //base case
			return trees;
		}
		else
		{
			for(int rootValue = start; rootValue <= end; rootValue++)
			{
				List<TreeNode> Lsub = generate(start,rootValue-1);
				List<TreeNode> Rsub = generate(rootValue+1, end);
				
				for(TreeNode L : Lsub)
					for(TreeNode R : Rsub)
					{
						TreeNode root = new TreeNode(rootValue);
						root.left = L;
						root.right = R;
						trees.add(root);
					}
			}
		}
		return trees;
	}
	
	//Validate Binary Search tree
	//there is a corner case where the right node = Integer.MAX_Value or the left node = Integer.MIN_Value
	//use long to make the left/right boundary more wide.
	 public boolean isValidBST(TreeNode root) {
	        if(root == null) return true;
	        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	    }

	    private boolean isValidBST(TreeNode node, long min, long max) {
	        if(node == null) return true;
	        if(node.val <= min || node.val >= max) return false;
	        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
	    }
	    
	    
	 //recover a binary tree
	   
	    
	 /*The basic idea is to use stack to do in-order traversal. In the processing of traversal, keep 
	    comparing the current value with the previous value. Since each previous value should be less 
	    than the current value, once an exception is found, record the previous node as the First Mistaken 
	    Node and the current node as Second. If one more exceptions are found, override the current node to 
	    the Second Mistaken Node. Because if a series of mistaken nodes are found, the only possible way to 
	    correct them with one swap is to switch the head and tail node.*/
	    public void recoverTree(TreeNode root) {
	        TreeNode pre = null, first = null, second = null;
	        Deque<TreeNode> stack = new LinkedList<TreeNode>();
	        while (root != null) {
	            stack.push(root);
	            root = root.left;
	        }
	        while (!stack.isEmpty()) {
	            TreeNode temp = stack.pop();
	            if (pre != null)
	                if (pre.val > temp.val) {
	                    if (first == null)
	                        first = pre;
	                    second = temp;
	                }
	            pre = temp;
	            if (temp.right != null) {
	                temp = temp.right;
	                while (temp != null) {
	                    stack.push(temp);
	                    temp = temp.left;
	                }
	            }
	        }

	        int temp = first.val;
	        first.val = second.val;
	        second.val = temp;
	    }
	    
	    //same tree
	    //the thing to care about is make sure the node is not null if u want to call the node's function
	    public boolean isSameTree(TreeNode p, TreeNode q) {
	        if(q == null && p == null)
	            return true;
	        else if(q != null && p != null && q.val == p.val)
	            return isSameTree(p.left, q.left)&&isSameTree(p.right, q.right);
	        return false;
	    }
	    
	    //binary tree level order traversal
	    public List<List<Integer>> levelOrder(TreeNode root) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if(root==null) return result;
	        Queue<TreeNode> q = new LinkedList<>();
	        q.add(root);
	        while(q.size()>0){
	            List<Integer> list = new ArrayList<>();
	            int size = q.size(); //counter to count current level's number
	            for(int i=0; i<size; i++){
	                TreeNode node = q.poll();
	                list.add(node.val);
	                if(node.left!=null) q.add(node.left);
	                if(node.right!=null) q.add(node.right);
	            }
	            result.add(list);
	        }
	        return result;
	    }
	    
	    
	    //binary tree zig zeg traversal
	    //iterator way is just like the level order traversal
	    //this is the recursive way
	    //every time we reach a new level we add a new list to contain that level
	    //to decide the direction we use level%2 = 0 just like what we would use in iterator way.
	    private void help(List<List<Integer>> rst, TreeNode root, int level) {
	        if (root == null) return;
	        if (rst.size() == level) rst.add(new LinkedList<Integer>());
	        if (level % 2 == 0) rst.get(level).add(root.val);
	        else rst.get(level).add(0, root.val);
	        help(rst, root.left, level + 1);
	        help(rst, root.right, level + 1);
	    }

	    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	        List<List<Integer>> rst = new ArrayList<List<Integer>>();
	        help(rst, root, 0);
	        return rst;
	    }
	    
	    //pre-order
	    //recursive
	    
	    public List<Integer> preorderTraversal(TreeNode root) {
	        List<Integer> result = new ArrayList<Integer>();
	        if (root != null){
	            result.add(root.val);
	            result.addAll(preorderTraversal(root.left));  //use addALl to add the list.
	            result.addAll(preorderTraversal(root.right));
	        }
	        return result;
	    }
	    
	    
	    //iterative  root->left->right
	    //we have to use the stack because we always want to go through the whole left-sub tree.
	    public List<Integer> preorderTraversal2(TreeNode root) {
	        List<Integer> result = new ArrayList<Integer>();
	        if (root == null) return result;
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        stack.push(root);
	        while (!stack.isEmpty()){
	            TreeNode node = stack.pop();
	            result.add(node.val);
	            if (node.right != null) stack.push(node.right);
	            if (node.left != null) stack.push(node.left);  //because we use stack, we'd want push the left node 
	            												//after the right node.
	        }
	        return result;
	    }
	    
	    
	    //when asking to do something that would always traverse the left/right sub tree b4 went to another
	    //its better to use STACK.
	    
	    //post order   left->right->root
	    //a little different from the pre-order,since we want to place the root the last
	    //its easier to add the results to the beginning of the list. that way we dont have to reverse the results.
	    public List<Integer> postorderTraversal(TreeNode root) {
	        LinkedList<Integer> ans = new LinkedList<>();
	    Stack<TreeNode> stack = new Stack<>();
	    if (root == null) return ans;

	    stack.push(root);
	    while (!stack.isEmpty()) {
	        TreeNode cur = stack.pop();
	        ans.add(0,cur.val);
	        if (cur.left != null) {
	            stack.push(cur.left);
	        }
	        if (cur.right != null) {
	            stack.push(cur.right);
	        } 
	    }
	    return ans;
	    }
	    
	    /*isBalancedHelper returns the actual depth of tree if the tree is balanced, or -2 if unbalanced. 
	    Why -2? The depth of tree is defined as number of edges from root to its deepest leaf node. 
	    So if a node is null, its depth of -1, instead of 0. The root with no children has a depth 
	    of 0. it is calculated as max depth of its children, which is -1, plus 1. Therefore -1 is 
	    considered as a valid depth, while -2 is not. Once a subtree is found unbalanced, return -2 
	    immediately to avoid useless computing. */
	    
	    public boolean isBalanced(TreeNode root) {
            return isBalancedHelper(root) >= -1 ? true : false;
        }

        private int isBalancedHelper(TreeNode root) {
            if (root == null)
                return -1;
            int leftDepth = isBalancedHelper(root.left);
            if (leftDepth == -2)
                return -2;
            int rightDepth = isBalancedHelper(root.right);
            if (rightDepth == -2)
                return -2;
            return Math.abs(leftDepth - rightDepth) > 1 ? -2 :
                    Math.max(leftDepth, rightDepth) + 1;
        }
        
        
        //find the min depth
        //the trick for this compare to the find max depth is that if this node has only one child
        //then we need to find the maximum depth of it's only child, otherwise we have to find the 
        //minimum depth of left/right.
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int minDepth = 0;
            if (root.left == null || root.right == null) {
                minDepth = Math.max(minDepth(root.left), minDepth(root.right));
            } else {
                minDepth = Math.min(minDepth(root.left), minDepth(root.right));
            }
            return 1 + minDepth;
        }
        /*
        1.The Root of the tree is the first element in Preorder Array.
        2.Find the position of the Root in Inorder Array.
        3.Elements to the left of Root element in Inorder Array are the left subtree.
        4.Elements to the right of Root element in Inorder Array are the right subtree.
        5.Call recursively buildTree on the subarray composed by the elements in the left subtree. Attach returned left subtree root as left child of Root node.
        6.Call recursively buildTree on the subarray composed by the elements in the right subtree. Attach returned right subtree root as right child of Root node.
        7.return Root.
        */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if(preorder==null || inorder==null || inorder.length==0 || preorder.length==0) return null;
            TreeNode root = new TreeNode(preorder[0]);
            if(preorder.length==1) 
            	return root;
            int breakindex = -1;
            for(int i=0;i<inorder.length;i++) 
            { 
            	if(inorder[i]==preorder[0]) 
            	{ 
            		breakindex=i; 
            		break;
            	} 
            }
            
            int[] subleftpre  = Arrays.copyOfRange(preorder,1,breakindex+1);
            int[] subleftin   = Arrays.copyOfRange(inorder,0,breakindex);
            int[] subrightpre = Arrays.copyOfRange(preorder,breakindex+1,preorder.length);
            int[] subrightin  = Arrays.copyOfRange(inorder,breakindex+1,inorder.length);
            root.left  = buildTree(subleftpre,subleftin);
            root.right = buildTree(subrightpre,subrightin);
            return root;
        }
        
        //find the max path sum.
        int maxPath = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
           maxP(root);
           return maxPath;
        }
        public int maxP(TreeNode root)
        {
             if(root == null)
                return 0;
            else
            {
                int left = maxP(root.left);
                int right = maxP(root.right);
                int maxSP =Math.max(root.val,Math.max(root.val+left,root.val+right));
                int tmp = 0;
                if(left > 0)
                    tmp += left;
                if(right > 0)
                    tmp += right;
                tmp += root.val;
                maxPath = Math.max(maxPath, tmp);
                return maxSP;
            }
        }
        
        //Convert Sorted Array to Binary Search Tree
        //Given an array where elements are sorted in ascending order, 
        //convert it to a height balanced BST.
        //it like the construct a tree using in-order and pre-order.  
        public TreeNode sortedArrayToBST(int[] nums) {
            if(nums.length == 0)
                return null;
            if(nums.length == 1)
                return new TreeNode(nums[0]);
            int cur = nums.length/2;
            int val = nums[cur];
            TreeNode root = new TreeNode(val);
            int[] left = Arrays.copyOfRange(nums,0,cur);
            int[]right = Arrays.copyOfRange(nums,nums.length/2+1,nums.length);
            root.left = sortedArrayToBST(left);
            root.right = sortedArrayToBST(right);
            return root;
        }
        //Flatten Binary Tree to Linked List
        //use pre-order to do it.
        public void flatten(TreeNode root) {
            if(root == null)
                return;
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while(!stack.isEmpty())
            {

                TreeNode cur = stack.pop();
                System.out.print(cur.val + " ");
                if(cur.right != null)
                {
                    stack.push(cur.right);
                    System.out.print(stack.peek().val + " ");
                    
                }
                if(cur.left != null)
                {
                    stack.push(cur.left);
                    System.out.println(stack.peek().val + " ");
                }
                if(!root.equals(cur))
                {
                    root.left = null;			//must clear the left side!!!!
                    root.right = cur;
                    root = root.right;             
                }
            }
        }
        //Binary Search Tree Iterator
        //its like a in-order traversal
        Stack<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            stack = new Stack<TreeNode>();
            setNext(root);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            if(stack.isEmpty()) return -1;
            TreeNode node = stack.pop();
            int val = node.val;
            setNext(node.right);
            return val;
        }
        //add a private helper function
        private void setNext(TreeNode root){
           while(root != null){
               stack.push(root);
               root = root.left;
           }
        }
        
        //Binary Tree Paths    #rec
        public List<String> binaryTreePaths(TreeNode root) {
            ArrayList<String> res = new ArrayList<String>();        
        DFS(root, "", res);
        return res;        
        }

	    public void DFS(TreeNode root, String solution, ArrayList<String> res) 
	    {
	        if (root == null) 
	            return;
	        //we know it a leaf then add it to res
	        if (root.left==null && root.right==null) 
	        	res.add(solution + root.val);
	        DFS(root.left, solution + root.val + "->", res);        
	        DFS(root.right, solution + root.val + "->", res);        
	    }
	    
	    //Path Sum II  #rec
	    //Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	    //use recursive, need backtrace.
	    public List<List<Integer>> pathSum(TreeNode root, int sum) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        List<Integer> temp = new ArrayList<Integer>();
	        doSearch(res, temp, root, sum);
	        return res;
	    }
	    private void doSearch(List<List<Integer>> res, List<Integer> temp, TreeNode root, int sum) {
	        if(root == null) {
	            return;
	        }
	        sum -= root.val;
	        temp.add(root.val);
	        if(sum == 0 && root.left == null && root.right == null) {
	            res.add(new ArrayList<Integer>(temp)); //can not add return statement here otherwise the buff wont get cleared
	        }
	        doSearch(res, temp, root.left, sum);
	        doSearch(res, temp, root.right, sum);
	        temp.remove(temp.size() - 1);
	    }
	    //Binary Tree Right Side View  #bfs
	    //Given a binary tree, imagine yourself standing on the right side of it, 
	    //return the values of the nodes you can see ordered from top to bottom.
	    //use bfs to iterate through the tree and add the last node of each layer to the result.
	   
	    public List<Integer> rightSideView(TreeNode root) {
	        // Write your solution here.
	        // 1:39pm - 1:44pm
	        List<Integer> result = new ArrayList<Integer>();
	        if (root == null) {
	            return result;
	        }
	        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	        queue.offer(root);
	        while (! queue.isEmpty() ) {
	            int size = queue.size();
	            for (int i = 0; i < size ; i++) {
	                TreeNode curr = queue.poll();
	                if (i == size - 1) {
	                    result.add(curr.val);
	                }
	                if (curr.left != null) {
	                    queue.offer(curr.left);
	                }
	                if (curr.right != null) {
	                    queue.offer(curr.right);
	                }
	            }
	        }
	        return result;
	    }
	    
	    //Populating Next Right Pointers in Each Node
	    //just like previous one, use bfs.
	    public void connect(TreeLinkNode root) {
	        if(root == null)
	            return;
	        Queue<TreeLinkNode> que = new LinkedList<TreeLinkNode>();
	        que.offer(root);
	        while(!que.isEmpty())
	        {
	            TreeLinkNode pre = null;
	            int size = que.size();
	            for(int i = 0; i < size; i++)
	            {
	                TreeLinkNode cur = que.poll();
	                if(i == size)
	                    cur.next = null;
	                if(pre != null)
	                    pre.next = cur;
	                if(cur.left != null)
	                    que.offer(cur.left);
	                if(cur.right != null)
	                    que.offer(cur.right);
	                pre = cur;
	                
	            }
	            
	        }
	    }
	    // //Populating Next Right Pointers in Each Node II
	    //didnt use bfs
	    //use dummyhead point to the leftest node of each layer(start from the second layer)
	    //use pre to connet the node in the same layer(start from second layer)
	    //if(iterate root using root.next(), if we get null value that means it's time to get to next layer
	    //
	    public void connectII(TreeLinkNode root) {
	        TreeLinkNode dummyHead = new TreeLinkNode(0);
	        TreeLinkNode pre = dummyHead;
	        while (root != null) {
	            if (root.left != null) {
	                pre.next = root.left;
	                pre = pre.next;
	            }
	            if (root.right != null) {
	                pre.next = root.right;
	                pre = pre.next;
	            }
	            root = root.next;
	            if (root == null) {
	                pre = dummyHead;  			//get back to the far left of this layer
	                root = dummyHead.next;
	                dummyHead.next = null;	//pre and dummyhead is now pointed to nothing.
	            }
	        }
	        
	        //Sum Root to Leaf Numbers
	        //regular routine, the base case is at leaf which is 
	        //			root.left == null && root.right == null
	        public int sumNumbers(TreeNode root) {
	            ArrayList<Integer> res = new ArrayList<Integer>();
	            if(root == null)
	                return 0;
	            sumNumbers(root, 0,res);
	            int result = 0;
	            Iterator<Integer> itr = res.iterator();
	            while(itr.hasNext())
	                result += itr.next();
	            return result;
	        }
	        public void sumNumbers(TreeNode root, int pre, List<Integer> res)
	        {
	            if(root == null)
	                return;
	            pre = pre*10 + root.val;
	            if(root.left == null && root.right == null)
	            {
	                res.add(pre);
	                return;
	            }
	            sumNumbers(root.left, pre,res);
	            sumNumbers(root.right,pre,res);
	        }
	        
	        
	        //Serialize and Deserialize Binary Tree
	        // Encodes a tree to a single string.
	        public String serialize(TreeNode root) {
	            StringBuilder str = new StringBuilder();

	            helperSerialize(root, str);
	            return str.toString();
	        }
	        //print the tree in string using pre order
	        private void helperSerialize(TreeNode root, StringBuilder str){
	            if(root == null) {
	                str.append("null").append(",");
	                return;
	            }

	            str.append(root.val).append(",");
	            helperSerialize(root.left, str);
	            helperSerialize(root.right, str);
	        }

	        int index = -1; //global variable to maintain the current index in deserialization
	        // Decodes your encoded data to tree.
	        public TreeNode deserialize(String data) {
	            TreeNode result = null;

	            if(data.length() == 0) return result;

	            String[] buf = data.split(",");
	            result = helper(buf);

	            return result;
	        }

	        private TreeNode helper(String[] buf){
	            if(index >= buf.length) return null;	//base case
	            TreeNode root = null;  //if the corresponding node in the buf is null
	            						//we wont do any more recursive calls
	            						//thus just return the node as null

	            index++;   //we set index = -1 at begging to save all the trouble
	            		   //because everytime we call this function we need to increase index by 1.
	            if(!buf[index].equals("null")) {
	                root = new TreeNode(Integer.parseInt(buf[index]));
	                root.left = helper(buf);			//we are return a node at corresponding index at a time
	                root.right = helper(buf);			//	and its just the way pre-order sorted.
	            }

	            return root;
	        }
	        
	        
	        //Lowest Common Ancestor of a Binary Search Tree
	        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	            //since its a binary tree, and the LCA is somewhere in the shortest path
	            //thus it cant be smaller/larger than both of the target node.
	            
	            
	            if(root.val<Math.min(p.val,q.val)) 
	                return lowestCommonAncestor(root.right,p,q);
	            if(root.val>Math.max(p.val,q.val)) 
	                return lowestCommonAncestor(root.left,p,q);
	            return root;

	        }
	      //Lowest Common Ancestor of a Binary  Tree
	        //the key to this question is that one node must be in the left sub tree
	        //and another one must be in the right sub tree.
	        //so we r finding a node that fits this character or itself = p or q.
	        public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
	            if(root == null)
	                 return null ;
	            TreeNode left = lowestCommonAncestorII(root.left, p, q);
	            TreeNode right = lowestCommonAncestorII(root.right, p, q);
	            //if we find a root = p or q, then it must be LCA
	            //or its a node in LCA's path
	            if(root == p || root == q)	
	                return root;
	            
	            //use to keep propagate the recursive function
	            if(left != null && right == null)
	                return left;
	            else if(left == null && right != null)
	                return right;
	            else if(left == null && right == null)//this means a dead end, there is no q or p in this branch.
	                return null;
	            else                                //else left sub tree would contain a node
	                return root;                    //and right sub tree would contain another.
	            									//notice that it returns itself, not the value returned from 
	            									//child recursive calls.
	        }
}
