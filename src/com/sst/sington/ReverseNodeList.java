package com.sst.sington;

public class ReverseNodeList {
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(7);
		System.out.println(head);
		System.out.println(head);
		Node result = reverse(head);
		System.out.println(result);
	}
	
	public static Node reverse(Node node){
		
		if(node == null || node.next == null){
			return node; //如果node为空或者只有node一个节点，则返回node本身
		}
		Node pp,pc,pn; //pp前一个节点，pc当前节点，pn下一个节点
		pp = node;
		pc = node.next;
		pn = pc.next;
		pp.next = null;
		while(pn != null){
			pc.next = pp;
			pp = pc;
			pc = pn;
			pn = pn.next;
		}
		pc.next = pp; //如果最后pn为空，则循环里的第一句话，将当前节点指向前一个节点不能忘记。
		return pc;
	}

	
	
}


class Node implements Cloneable{

	public int index;
	public Node next;

	public Node(int index) {
		super();
		this.index = index;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 原先的头String直接用node会破坏结构，所以clone了一个
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = index + "";
		Node clone = null;
		try {
			clone = (Node) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (clone.next != null) {
			result += "--->" + clone.next.index;
			clone.next = clone.next.next;
		}
		return result;
	}

}