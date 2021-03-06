
public class things_to_do_with_linkedlist {
	//Copy List with Random Pointer
	//A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
	//Return a deep copy of the list.
	//idea: every node in the list create a new node and insert it behind the original node.
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)
            return null;
        RandomListNode VirtureHead = head;
        RandomListNode res = null;
        RandomListNode temp = null;
        while(head!= null)              //create and insert the copy node in the original list 
        {
            
            RandomListNode newnode = new RandomListNode(head.label);
            if(res == null)
                res = newnode;
            RandomListNode tmp = head.next;
            head.next = newnode;
            newnode.next = tmp;
            head = head.next.next;
        }
        head = VirtureHead;
        while(head != null)             //assign the random pointer to the copy node
        {
            if(head.random != null)
                head.next.random = head.random.next;
            else
                head.next.random = null;
            head = head.next.next;
        }
        head = VirtureHead;
        while(head != null && head.next != null)  //sperate the original node and the copy node
        {
            temp = head.next;
            head.next = head.next.next;
            head = temp;
        }
        return res;
    }
    
    
    
    
    
    
    
    //Add two numbers
    //you are given two linked lists representing two non-negative numbers. 
    //Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    //Output: 7 -> 0 -> 8
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
        int carry = 0;
  		ListNode head = new ListNode(0);
  		ListNode res = head;
  		int first;
  		int second;
  		while(l1 != null||l2 != null||carry != 0)
  		{
  			first = (l1 == null)?0:l1.val;
  			second = (l2 == null)?0:l2.val;
  			carry = carry + first + second;
  			head.next = new ListNode(carry%10);
  			head = head.next;
  			carry = carry/10;
  			l1 = (l1 == null)?null:l1.next;
  			l2 = (l2 == null)?null:l2.next;
  			
  		}
  		return res.next;
    }
    
    //remove the nth node
    public ListNode removeNthFromEnd(ListNode head, int n) 
    {
        ListNode first = head;
        ListNode second = head;
        while(n > 0)
        {
            first = first.next;
            n--;
        }
        if(first == null)
            return second.next;
        while(first.next != null)
        {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return head;
    }
    
    //Merge two lists
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a==null)
        {
	     return b;
        }
		 if (b==null)
		 {
		     return a;
		 }
		 if (a.val<b.val)
		 {
		     a.next = mergeTwoLists(a.next,b);
		     return a;
		 }
		 else 
		 {
		     b.next = mergeTwoLists(a,b.next);
		     return b;
		 }
	 }
    
    //Merge k lists
    public ListNode mergeKLists(ListNode[] lists) 
    {
        int len = lists.length;
        if (len==0){
            return null;
        }
        if (len==1){
            return lists[0];
        }
        while (len>1){
            for (int i=0;i<len/2;i++){					//use something like binary search.
                lists[i] = mergeTwList(lists[i],lists[len-1-i]);
            }
            len = (len/2+len%2);
        }
        return lists[0];
    }

    public ListNode mergeTwList(ListNode a,ListNode b)
    {
        if (a==null){
            return b;
        }
        if (b==null){
            return a;
        }
        if (a.val<b.val){
            a.next = mergeTwList(a.next,b);
            return a;
        }
        else {
            b.next = mergeTwList(a,b.next);
            return b;
        }
    }
    
    //swap node in pairs
    //Given 1->2->3->4, you should return the list as 2->1->4->3.
    //Your algorithm should use only constant space. You may not modify the values in the list, 
    //only nodes itself can be changed.
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        //create a two reference nodes and a dummy node.
        ListNode p = new ListNode(0);
        p.next = head;
        ListNode left = p;
        ListNode right = head;
        

        while(right != null && right.next != null)
        {
            ListNode tmp = new ListNode(0);
            ListNode tmp2 = new ListNode(0);
            tmp = right.next;
            tmp2 = tmp.next;
            
            left.next = tmp;
            tmp.next = right;
            right.next = tmp2;
            right = right.next;     //right node only needs to move one slot
            left = left.next.next;  //left node needs to move two slot
        }
        return p.next;      
    }
    
    //Reverse Nodes in k-group
    //Given this linked list: 1->2->3->4->5
    //For k = 2, you should return: 2->1->4->3->5
    //For k = 3, you should return: 3->2->1->4->5
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode v = new ListNode(0);
        ListNode p = v;
        p.next = head;
        ListNode s1, s2;
        ListNode end = head;
        if(head == null || k == 1)
            return head;
        while(true)
        {
            int i = k;
            while(i > 0)
            {
                if(end == null)
                    return v.next;
                end = end.next;
                i--;
                if(end == null && i != 0)
                    return v.next;
            }       
            s1 = p.next;
            while(s1.next != end)
            {
                s2 = s1;
                while(s2.next.next != end)
                {
                    s2 = s2.next;
                }
                p.next = s2.next;
                p.next.next = s1;
                s2.next = end;
                p = p.next;
            }
            p = s1;
        }
    }
    
    //rotate list
    public ListNode rotateRight(ListNode head, int k) 
    {
        if(head == null)
            return head;
        ListNode v = new ListNode(0);
        ListNode first = v, second = v;
        v.next = head;
        int len = 0;
        int n = k;
        while(k > 0) //varify the k value.
        {
            if(first.next == null) //handle the case where k is larger than the list size, and we dont have a system method for the list size so we gonna count by our own.
            {
                k %= len;
                n = k;
                first = v;
                break;
            }
            first = first.next;
            
            len ++;
            k--;
        }
        if(first.next == null) //handle the case which we dont have to move the list at all.
            return head;
        while(k>0)       //in linked list, if we want to modify ith node, we would like to make the pointer points to the (i-1)th node. 
        {
            first = first.next;
            k--;
        }
        while(first.next != null)
        {
            first = first.next;
            second = second.next;
        }
        first.next = v.next;
        v.next = second.next;
        second.next = null;
        return v.next;
    }
    
    //remove duplicates
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return null;
        ListNode notDuplic = head;
        ListNode index = head.next;
        
        while(index != null)
        {
            if(notDuplic.val != index.val)
            {
                notDuplic.next =index;
                notDuplic = index;
            }
            index = index.next;
        }
        notDuplic.next = null;
        return head;
    }
    
    
    //Remove duplicates II
    //Given 1->2->3->3->4->4->5, return 1->2->5.
    //Given 1->1->1->2->3, return 2->3.
    public ListNode deleteDuplicates(ListNode head) {
       if(head == null)
            return null;
        ListNode v = new ListNode(0);
        v.next = head;
        ListNode pre = v;
        ListNode cur = head;
        while(cur != null)
        {
            while(cur.next != null && cur.val == cur.next.val)
                cur = cur.next;
            if(pre.next == cur)
                pre = pre.next;
            else
                pre.next = cur.next;
            
            cur = cur.next;
            
        }
        return v.next;
    }
    
    //Partition list
    //Given 1->4->3->2->5->2 and x = 3,
    //return 1->2->2->4->3->5.
    public ListNode partition(ListNode head, int x) {
        ListNode v = new ListNode(0), v2 = new ListNode(0);
        ListNode ptr = v, ptr2 = v2;
        while(head != null)
        {
            if(head.val < x)
            {
                ptr.next = head;
                ptr = ptr.next;
            }
            else
            {
                ptr2.next = head;
                ptr2 = ptr2.next;
            }
            head = head.next;
        }
        ptr.next = v2.next;
        ptr2.next = null;
        return v.next;
    }
    
    //Convert Sorted List to Binary Search Tree
    //Given a singly linked list where elements are sorted in 
    //ascending order, convert it to a height balanced BST.
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev =null; 
        while(fast != null && fast.next != null)
        {
            fast = fast.next.next;
            prev =slow;
            slow=slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        if(prev != null)
            prev.next = null;
        else
            head  = null;

        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
}
