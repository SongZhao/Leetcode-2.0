
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
}
