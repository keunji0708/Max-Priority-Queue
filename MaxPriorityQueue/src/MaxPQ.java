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
			System.out.println("1. 작업 추가          2. 최대값          3.최대 우선순위 작업 처리\n"
					+ "4. 원소 키값 증가   5. 작업 제거                                 6.종료");
			System.out.println("------------------------------------\n");
			Scanner scan = new Scanner(System.in);
			String input = scan.next();
			
			if (input.equals("1")) {
				System.out.println("<<<<<  작업 추가    >>>>>");
				System.out.print("우선 순위 입력 : ");
				int key = scan.nextInt();
				System.out.print("과목명 입력 : ");
				String subject = scan.nextLine();
				subject = ", " + subject;
				Node newNode = new Node(key, subject);
				insert(list, newNode);
			}
			else if (input.equals("2")) {
				System.out.println("<<<<<  최대값    >>>>>");
				Node max_node = max(list); 
				System.out.println("우선순위가 최대인 원소 : "+max_node.getkey() + max_node.getvalue());
			}
			else if (input.equals("3")) {
				System.out.println("<<<<<  최대 우선순위 작업 처리    >>>>>");
				System.out.print("처리된 최대 우선순위 작업 : ");
				extract_max(list);
			}
			else if (input.equals("4")) {
				Node x = new Node();
				System.out.println("<<<<<  원소 키값 증가    >>>>>");
				System.out.println("현재 마지막 우선 순위의 번호는 " + (list.size()-1) +"입니다.");
				while (true) {
					System.out.print("키 값을 증가시킬 원소의 우선 순위를 입력하세요 : ");
					scan = new Scanner(System.in);
					int key = scan.nextInt();
					
					if (key >= list.size() || key < 0) {
						System.out.println("---------- 우선순위가 범위를 벗어났습니다!! ----------");
						continue;
						}
					
					x = (Node) list.get(key);
					break;
					}
				int key = 0;
				while (true) {
					System.out.print("키 값을 얼마로 하시겠나요 [범위 1 ~ 1000] : ");
					key = scan.nextInt();
					if (key <= x.getkey()) {
						System.out.println("---------- 현재 키 값보다 작아지면 안됩니다!! ----------");
						continue;
					}
					else break; 
				}
				increase_key(list, x, key); 
			}			
			else if (input.equals("5")) {
				System.out.println("<<<<<  작업 제거    >>>>>");
				System.out.print("제거할 작업의 우선 순위를 입력하세요 : ");
				int count = scan.nextInt(); 
				Node x = (Node) list.get(count);
				delete(list, x, count); 
			}
			else if (input.equals("6")) {
				System.out.println("<<<<<  프로그램을 종료합니다    >>>>>");
				break;
			}			
			else {
				System.out.println("--------- 다시 입력하세요 ---------");
			}
			print(list);
		}
	}
	
	public static void print(List list) {
		System.out.println("\n**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****\n");
		Node node;
		for(int i = 0; i <= list.size() - 1; i++) {
			node = (Node) list.get(i);
			System.out.println((i)+"번 -> " + node.getkey() + node.getvalue());
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
