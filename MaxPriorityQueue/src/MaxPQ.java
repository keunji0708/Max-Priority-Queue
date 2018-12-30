import java.io.*;
import java.util.*;

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
	
	public static void insert(List<Node> list, Node n) {
		list.add(n);
		buildMaxHeap(list);
	}
	
	public static Node max(List<Node> list) {
		if (list.size() <= 0) { 
			System.out.println("Empty");
			return null;
		} else {
			Node max_node = list.get(0); 
			return max_node;
		}
	}
	
	public static void extract_max(List<Node> list) {
		if (list.size() <= 0) { 
			System.out.println("Empty");
			return;
		} 
		else if (list.size() == 1){ 
			Node ex_node = list.get(0); 
			list.remove(0);
			System.out.println(ex_node.getkey() + ex_node.getvalue());
			return;
		}
		Node ex_node = list.get(0);
		list.set(0, list.get(list.size() - 1)); 
		list.remove(list.size() - 1);
		buildMaxHeap(list);
		System.out.println(ex_node.getkey() + ex_node.getvalue());
	}
	
	public static void increase_key(List<Node> list, Node x, int k) {
		int temp = x.getkey();
		x.setkey(k);
		buildMaxHeap(list);
		System.out.println("키값 수정 전 : "+ temp + x.getvalue() 
		+ "//  키값 수정 후: " + x.getkey() + x.getvalue());
	}
	
	public static void delete(List<Node> list, Node x, int count) {
		Node temp = x;
		list.set(count,list.get(list.size()-1));
		list.remove(list.size()-1);
		buildMaxHeap(list);
		System.out.println("제거한 작업 : "+temp.getkey() + temp.getvalue());		
	}
	
	public static void main(String args[]) throws IOException{
		List list = new ArrayList<>();
		Node node;
		int num = 0;
		String name = "";
		
		BufferedReader br = new BufferedReader(new FileReader("data03.txt"));
		String line = br.readLine();
		int temp = 0;
		
		while (line != null) {
			StringTokenizer st = new StringTokenizer(line, ", ");
			int size = st.countTokens();
			while (st.hasMoreTokens()) {
				node = new Node();
				num = Integer.parseInt(st.nextToken());
				node.setkey(num);
				name = st.nextToken("\n");
				node.setvalue(name);
				list.add(temp, node);
				temp++;
			}
			line = br.readLine();
		}
		
		buildMaxHeap(list);
		//print(list);
	}
	
}

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
