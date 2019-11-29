package com.mystudy.ex01_arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListExam {

	public static void main(String[] args) {
		//List �迭 : ArrayList - �迭�� �Ӽ��� List �Ӽ��� ����
		//  - List �������̽��� ��� ����(implements)�� Ŭ����
		//  - List �������̽��� ��� Ȯ��(extends)�ؼ� �������̽�
		ArrayList list1 = new ArrayList();
		System.out.println("list1.size() : " + list1.size());
		
		list1.add(new Integer(5));
		list1.add(1); //int -> Integer �ڵ�����ȯ �Ǿ� �Էµ�
		list1.add(new Integer(5)); //���� ���� �ߺ��� ���
		list1.add(new Integer(6));
		list1.add(new Integer(7));
		System.out.println(list1.toString());
		System.out.println("list1.size() : " + list1.size());
		
		list1.add(0, 100); //�ε��� ��ġ�� ������ �߰�(����)
		System.out.println("0�ε��� 100 add�� : " + list1.toString());
		
		list1.set(0, 999); //�ε��� ��ġ�� ������ ����(����)
		System.out.println("0�ε��� 999 set�� : " + list1);
		
		list1.remove(0); //�ε��� ��ġ�� ������ ����
		System.out.println("0�ε��� remove�� : " + list1);
		
		//list1.remove(5); //���� 5�� �����ϰڴٰ� �ƴ� 5�� �ε��� ������ ����
		list1.remove(new Integer(5));
		System.out.println("����Integer 5 ������ : " + list1);
		
		Object obj = list1.get(0);
		System.out.println("0�� �ε��� �� : " + obj.toString());
		
		Collections.reverse(list1);
		System.out.println("reverse ������ : " + list1.toString());
		
		System.out.println("------------------------");
		ArrayList list2 
				= new ArrayList(list1.subList(1, 4));
		System.out.println("list2 : " + list2);
		
		System.out.println("---- Collections.sort() ---");
		System.out.println("list1 : " + list1);
		
		Collections.sort(list1); //�������� ����
		System.out.println("sort() �� list1 : " + list1);
		
		Collections.sort(list2);
		System.out.println("sort() �� list2 : " + list2);
		
		System.out.println("---- Collections.reverse() ----");
		//reverse() : ó���� �� ��ġ�� ���� �ٲٴ� ���·� ����(���İ��� ����)
		Collections.reverse(list1);
		System.out.println("reverse() �� list1 : " + list1);

		System.out.println("==========================");
		System.out.println("list1 : " + list1);
		System.out.println("list2 : " + list2);
		
		list2.add("B");
		list2.add("TEST");
		System.out.println("list2 : " + list2);
		System.out.println("ù��° ����Ÿ ��ȸ : " + list2.get(0));
		System.out.println("������ ����Ÿ ��ȸ : " + list2.get(list2.size() - 1));
		
		System.out.println("-----------");
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " - " + list2.get(i));
		}
		System.out.println("-----------");
		printData(list1);
		printData("list1", list1);
		printData("list2", list2);
		printData(list1, list2);
		
		System.out.println("================================");
		//�ǽ� : �ݺ���(for)�� ����ؼ� list2�� �ִ� ��ü ����Ÿ ����
		
		//(��������) ����Ÿ�� ��� �������� �ʰ� ���� ����
//		for (int i = 0; i < list2.size(); i++) {
//			printData(">> list2", list2);
//			list2.remove(i);
//		}
//		printData("list2", list2);
		
/*		
		System.out.println("----");
		//(��������) IndexOutOfBoundsException �߻�
		int list2Cnt = list2.size();
		for (int i = 0; i < list2Cnt; i++) {
			printData(">> list2", list2);
			list2.remove(i);
		}
		printData("list2", list2);
*/
/*		
		//�ذ�1 : ������ŭ 0�� �ε��� �� ���� ó��(ũ�Ⱚ ����)
		int list2Cnt = list2.size();
		for (int i = 0; i < list2Cnt; i++) {
			printData("��������>> list2", list2);
			list2.remove(0);
		}
		printData("���� ��� list2", list2);		
*/		
//		//�ذ�2 : ������ŭ �ڿ������� ���� ó��
//		//�Ǹ����� ������ ����, �ڿ���2��°, �ڿ���3��°....0������ ����
//		int lastIndex = list2.size() - 1;
//		for (int i = lastIndex; i >= 0; i--) {
//			printData("��������>> list2", list2);
//			list2.remove(i);
//		}
//		printData("���� ��� list2", list2);	
		
		printData("������ list2", list2);	
		list2.clear();
		printData("clear() ������ list2", list2);	
	}
	static void printData(ArrayList list1, ArrayList list2) {
		printData("list1", list1);
		printData("list2", list2);
		System.out.println();
	}
	
	static void printData(List list) {
		//null ���� Ȯ��
		if (list == null) return;
		if (list.size() > 0) {//�ּ� �ϳ� �̻��� ����Ÿ�� �ֳ�?
			System.out.print(list.get(0));
		}
		for (int i = 1; i < list.size(); i ++) {
			System.out.print(", " + list.get(i));
		}
		System.out.println();
	}
	
	static void printData(String title, List list) {
		//null ���� Ȯ��
		if (list == null) {
			System.out.println(title + " : null");
			return;
		}
		if (list.size() == 0) {//null�� �ƴ����� ����Ÿ�� ����
			System.out.println(title + " : ����Ÿ �Ǽ� 0");
			return;
		}
		System.out.print(title + " : " + list.get(0));
		for (int i = 1; i < list.size(); i ++) {
			System.out.print(", " + list.get(i));
		}
		System.out.println();
	}

}









