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
		//데이터가 없으면 null 값이 리턴되는 경우
		if (vo != null) {
			System.out.println("ID: " + vo.getId() + ", 이름: " + vo.getName());
			crud.dispData(vo.getId());
		} else {
			System.out.println(id +" : " + "데이터 없음");
		}
		System.out.println(vo);
		
		System.out.println("====== selectAll() 테스트 ======");
		List<StudentVO> list = crud.selectAll();
		for (StudentVO student : list) {
			//System.out.println(student);
			crud.dispData(student.getId());
		}

		System.out.println("====== insertData(param) 테스트 =======");
		crud.insertData("2019111", "홍길동", 100, 90, 81, 0, 0);
		crud.dispData("2019111");

		System.out.println("------ main() 종료 ----");
	}
	
	
	
	
	
	
	
	

}
