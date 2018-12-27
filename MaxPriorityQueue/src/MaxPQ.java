import java.io.*;
import java.util.*;

class Node {
	private int key;
	private String value;

	public Node() {
	}

	public Node(int k, String v) {
		key = k;
		value = v;
	}

	public int getkey() {
		return key;
	}

	public String getvalue() {
		return value;
	}

	public void setkey(int k) {
		key = k;
	}

	public void setvalue(String v) {
		value = v;
	}
}

public class MaxPQ {
	public static void buildMaxHeap(List<Node> list) {
		if (list == null) {
			return;
		}
		for (int i = list.size() / 2; i >= 0; i--) {
			maxHeapify(list, i);
		}
	}
	
	public static void maxHeapify(List<Node> list, int i) {
		int left_c = (2 * i);
		int right_c = (2 * i) + 1;
		int large = 0;
		
		if (left_c < list.size() && list.get(left_c).getkey() > list.get(i).getkey()) {
			large = left_c;
		}
		else {
			large = i;
		}
		if (right_c < list.size() && list.get(right_c).getkey() > list.get(large).getkey()) {
			large = right_c;
		}
		if (large != i) {
			Collections.swap(list, i, large);
			maxHeapify(list, large);
		}
	}
}
