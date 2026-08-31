/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int min = Integer.MAX_VALUE;
        int max=-1;
        ListNode prev = head;
        ListNode curr = head.next;
        int position = 1;
        int first = -1;
        int last = -1;

        while(curr.next != null){
            if(curr.val > prev.val && curr.val>curr.next.val || curr.val<prev.val && curr.val < curr.next.val){
                if(first == -1){
                    first = position;
                }
                if(last != -1){
                    min = Math.min(min , position - last);
                }
                last = position;
                max = position-first;
            }
            prev = curr;
            curr = curr.next;
            position++;
        }
        if(min == Integer.MAX_VALUE){
            return new int []{-1,-1};
        }
        return new int[]{min , max};
    }
}