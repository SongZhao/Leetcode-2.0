
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
}
