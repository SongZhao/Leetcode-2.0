
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
}
