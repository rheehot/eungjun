package com.mystudy.jdbc_dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberDAO_Test {

	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.selectAll();
		for (MemberVO mvo : list ) {
			System.out.println(mvo);
		}
		
		boolean isExist = dao.checkIdPassword("hong11", "1234");
		if (isExist) {
			System.out.println("--> ȸ������ ���� - �α��� ����");
		} else {
			System.out.println("--> ȸ������ ���� - ���̵�, ��ȣ Ȯ���ϼ���");
		}
		
		//�������� ������ �Է�(List �� �����ؼ�)
		List<MemberVO> insertList = new ArrayList<MemberVO>();
		insertList.add(new MemberVO("hong9", "ȫ�浿9", "1234", "010-1111-2222"));
		insertList.add(new MemberVO("hong10", "ȫ�浿10", "1234", "010-1111-2222"));
		insertList.add(new MemberVO("hong8", "ȫ�浿10", "1234", "010-1111-2222"));
		insertList.add(new MemberVO("hong11", "ȫ�浿10", "1234", "010-1111-2222"));
		int cnt = dao.insertList(insertList);
		System.out.println(">> �ϰ��Է°Ǽ� : " + cnt);

		//�ϰ� ���� �۾�
		
		//============================
//		MemberVO newMember = makeMember();
//		dao.insertOne(newMember);
//		MemberVO member = dao.selectOne(newMember);
//		System.out.println(member);
//		System.out.println(dao.selectOne("test"));
		
	}
	private static MemberVO makeMember() {
		Scanner scan = new Scanner(System.in);
		System.out.print("���̵� : ");
		String id = scan.nextLine();
		System.out.print("�̸� : ");
		String name = scan.nextLine();
		System.out.print("��ȣ : ");
		String password = scan.nextLine();
		System.out.print("��ȭ��ȣ : ");
		String phone = scan.nextLine();
		
		return new MemberVO(id, name, password, phone);
	}

}