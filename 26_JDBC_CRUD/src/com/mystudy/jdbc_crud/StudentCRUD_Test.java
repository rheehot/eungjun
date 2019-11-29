package com.mystudy.jdbc_crud;

import java.util.ArrayList;
import java.util.List;

public class StudentCRUD_Test {

	public static void main(String[] args) {
		StudentCRUD crud = new StudentCRUD();
		crud.dispData("2019002");
		
		System.out.println("--------");
		String id = "2019004";
		StudentVO vo = crud.selectId(id);
		//�����Ͱ� ������ null ���� ���ϵǴ� ���
		if (vo != null) {
			System.out.println("ID: " + vo.getId() + ", �̸�: " + vo.getName());
			crud.dispData(vo.getId());
		} else {
			System.out.println(id +" : " + "������ ����");
		}
		System.out.println(vo);
		
		System.out.println("====== selectAll() �׽�Ʈ ======");
		List<StudentVO> list = crud.selectAll();
		for (StudentVO student : list) {
			//System.out.println(student);
			crud.dispData(student.getId());
		}

		System.out.println("====== insertData(param) �׽�Ʈ =======");
		crud.insertData("2019111", "ȫ�浿", 100, 90, 81, 0, 0);
		crud.dispData("2019111");

		System.out.println("------ main() ���� ----");
	}
	
	
	
	
	
	
	
	

}