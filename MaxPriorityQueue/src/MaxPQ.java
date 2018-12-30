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
		System.out.println("Ű�� ���� �� : "+ temp + x.getvalue() 
		+ "//  Ű�� ���� ��: " + x.getkey() + x.getvalue());
	}
	
	public static void delete(List<Node> list, Node x, int count) {
		Node temp = x;
		list.set(count,list.get(list.size()-1));
		list.remove(list.size()-1);
		buildMaxHeap(list);
		System.out.println("������ �۾� : "+temp.getkey() + temp.getvalue());		
	}
	
	public static void main(String args[]) throws IOException{
		List list = new ArrayList<>();
		Node node;
		
		BufferedReader br = new BufferedReader(new FileReader("data03.txt"));
		String line = br.readLine();
		int temp = 0;
		int num = 0;
		String name = "";
		
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
		print(list);
		
		while(true) {
			System.out.println("\n------------------------------------");
			System.out.println("1. �۾� �߰�          2. �ִ밪          3.�ִ� �켱���� �۾� ó��\n"
					+ "4. ���� Ű�� ����   5. �۾� ����                                 6.����");
			System.out.println("------------------------------------\n");
			Scanner scan = new Scanner(System.in);
			String input = scan.next();
			
			if (input.equals("1")) {
				System.out.println("<<<<<  �۾� �߰�    >>>>>");
				System.out.print("�켱 ���� �Է� : ");
				int key = scan.nextInt();
				System.out.print("����� �Է� : ");
				String subject = scan.nextLine();
				subject = ", " + subject;
				Node newNode = new Node(key, subject);
				insert(list, newNode);
			}
			else if (input.equals("2")) {
				System.out.println("<<<<<  �ִ밪    >>>>>");
				Node max_node = max(list); 
				System.out.println("�켱������ �ִ��� ���� : "+max_node.getkey() + max_node.getvalue());
			}
			else if (input.equals("3")) {
				System.out.println("<<<<<  �ִ� �켱���� �۾� ó��    >>>>>");
				System.out.print("ó���� �ִ� �켱���� �۾� : ");
				extract_max(list);
			}
			else if (input.equals("4")) {
				Node x = new Node();
				System.out.println("<<<<<  ���� Ű�� ����    >>>>>");
				System.out.println("���� ������ �켱 ������ ��ȣ�� " + (list.size()-1) +"�Դϴ�.");
				while (true) {
					System.out.print("Ű ���� ������ų ������ �켱 ������ �Է��ϼ��� : ");
					scan = new Scanner(System.in);
					int key = scan.nextInt();
					
					if (key >= list.size() || key < 0) {
						System.out.println("---------- �켱������ ������ ������ϴ�!! ----------");
						continue;
						}
					
					x = (Node) list.get(key);
					break;
					}
				int key = 0;
				while (true) {
					System.out.print("Ű ���� �󸶷� �Ͻðڳ��� [���� 1 ~ 1000] : ");
					key = scan.nextInt();
					if (key <= x.getkey()) {
						System.out.println("---------- ���� Ű ������ �۾����� �ȵ˴ϴ�!! ----------");
						continue;
					}
					else break; 
				}
				increase_key(list, x, key); 
			}			
			else if (input.equals("5")) {
				System.out.println("<<<<<  �۾� ����    >>>>>");
				System.out.print("������ �۾��� �켱 ������ �Է��ϼ��� : ");
				int count = scan.nextInt(); 
				Node x = (Node) list.get(count);
				delete(list, x, count); 
			}
			else if (input.equals("6")) {
				System.out.println("<<<<<  ���α׷��� �����մϴ�    >>>>>");
				break;
			}			
			else {
				System.out.println("--------- �ٽ� �Է��ϼ��� ---------");
			}
			print(list);
		}
	}
	
	public static void print(List list) {
		System.out.println("\n**** ���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ� ****\n");
		Node node;
		for(int i = 0; i <= list.size() - 1; i++) {
			node = (Node) list.get(i);
			System.out.println((i)+"�� -> " + node.getkey() + node.getvalue());
		}
		
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
