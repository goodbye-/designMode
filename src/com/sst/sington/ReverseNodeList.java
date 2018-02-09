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
		Node result = reverse1(head);
		System.out.println(result);
	}

	public static Node reverse(Node node) {

		if (node == null || node.next == null) {
			return node; // 如果node为空或者只有node一个节点，则返回node本身
		}
		Node pp, pc, pn; // pp前一个节点，pc当前节点，pn下一个节点
		pp = node;
		pc = node.next;
		pn = pc.next;
		pp.next = null;
		while (pn != null) {
			pc.next = pp;
			pp = pc;
			pc = pn;
			pn = pn.next;
		}
		pc.next = pp; // 如果最后pn为空，则循环里的第一句话，将当前节点指向前一个节点不能忘记。
		return pc;
	}

	/**
	 * @param node
	 * 	之前方法太蠢，可以假设一个null节点指向node节点，就不用分情况了
	 * @return
	 */
	public static Node reverse1(Node node) {

		Node pp = null,pn,pc;
		pc = node;
		while(pc != null){
			pn = pc.next; //记录下一个节点
			pc.next = pp;//当前节点只想前一个节点
			pp = pc;//当前节点变成前一个节点
			pc = pn;//下一个节点变成当前节点
		}
		//等到下一个当前节点为空时，将前一个节点也就是正序的最后一个节点返回，妈的，变得这么简洁？？
		return pp;
	}

}

class Node implements Cloneable {

	public int index;
	public Node next;

	public Node(int index) {
		super();
		this.index = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() 原先的头String直接用node会破坏结构，所以clone了一个,或者定义一个temp指向都节点也行
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