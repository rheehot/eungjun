package com.mystudy.jdbc_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//0. JDBC ���̺귯�� ����ȯ�� ����(�����ο� ���)
//1. JDBC ����̹� �ε�
//2. DB����  - Connection ��ü ���� <-DriverManager
//3. Statement�� ����(SQL�� ����)
//4. SQL ���� ����� ���� ó��
//   -SELECT : ��ȸ(�˻�) ����Ÿ ��� ���� ���� ó��
//   -INSERT, UPDATE, DELETE : int��(�Ǽ�) ó��
//5. Ŭ��¡ ó���� ���� �ڿ� �ݳ�
//=====================================
public class StudentCRUD {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	StudentCRUD() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println(">>����̹� �ε� ����");
			e.printStackTrace();
		}
	}
	
	//Student ���̺��� �ִ� 1���� ������ ��ȸ�ؼ� ȭ�� ǥ��
	//ID���� �����ϸ� ������ �����ͼ� ȭ�鿡 ǥ��
	public void dispData(String id) {
		try {
			//2. DB����  - Connection ��ü ���� <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. Statement�� ����(SQL�� ����)
			String sql = "";
			sql += "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ";
			sql += "  FROM STUDENT ";
			sql += " WHERE ID = ? ";
			//3-1. PreparedStatment ��ü ���� <- Connetion ��ü�� ����
			pstmt = conn.prepareStatement(sql);
			
			//3-2. ? (���ε� ����)�� �� ����
			pstmt.setString(1, id);
			
			//3-3. SQL����
			rs = pstmt.executeQuery();
			
			//4. SQL ���� ����� ���� ó��
			//   -SELECT : ��ȸ(�˻�) ����Ÿ ��� ���� ���� ó��
			//   -INSERT, UPDATE, DELETE : int��(�Ǽ�) ó��
			if (rs.next()) {
				String str = "";
				str += rs.getString(1) + "\t";
				str += rs.getString(2) + "\t";
				str += rs.getInt(3) + "\t";
				str += rs.getInt(4) + "\t";
				str += rs.getInt(5) + "\t";
				str += rs.getInt(6) + "\t";
				str += rs.getDouble(7);
				
				System.out.println(str);
			} else {
				System.out.println(id + " - ������ ����");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. Ŭ��¡ ó���� ���� �ڿ� �ݳ�(��ü ���� ������ �������� ����)
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	//�ϳ��� �����͸� ��ȸ�ؼ� VO�� �����ؼ� ����
	public StudentVO selectId(String id) {
		StudentVO stu = null;
		//(����) DB�����ϰ� SQL�� �����ؼ� ������� stu������ ����
		try {
			//2. DB����  - Connection ��ü ���� <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			//3. Statement�� ����(SQL�� ����)
			String sql = "";
			sql += "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ";
			sql += "  FROM STUDENT ";
			sql += " WHERE ID = ? ";
			//3-1. PreparedStatment ��ü ���� <- Connetion ��ü�� ����
			pstmt = conn.prepareStatement(sql);
			
			//3-2. ? (���ε� ����)�� �� ����
			pstmt.setString(1, id);
			
			//3-3. SQL����
			rs = pstmt.executeQuery();
			
			//4. SQL �������� ���� ó��
			if (rs.next()) {//�����Ͱ� ������
				//StudentVO Ÿ���� stu ������ ��ȸ ������ ����
				stu = new StudentVO(id, 
						rs.getString("NAME"), 
						rs.getInt("KOR"), 
						rs.getInt("ENG"), 
						rs.getInt("MATH"), 
						rs.getInt("TOT"), 
						rs.getDouble("AVG"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. Ŭ��¡ ó���� ���� �ڿ� �ݳ�(��ü ���� ������ �������� ����)
			try {
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		return stu;
	}
	
	private void closeConnStmtRs(Connection conn,
			PreparedStatement pstmt, ResultSet rs) {
		//5. Ŭ��¡ ó���� ���� �ڿ� �ݳ�(��ü ���� ������ �������� ����)
		try {
			if (rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	private void closeConnStmt(Connection conn,
			PreparedStatement pstmt) {
		try {
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	//��ü ������ ��ȸ�ؼ� ArrayList�� ��Ƽ� ����
	//�ϳ��� �����͸� StudentVO�� ���, VO�� ArrayList�� ��Ƽ� ����
	public List<StudentVO> selectAll() {
		ArrayList<StudentVO> list = new ArrayList<>();
		
		try {
			//2. DB����  - Connection ��ü ���� <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. Statement�� ����(SQL�� ����)
			String sql = "";
			sql += "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ";
			sql += "  FROM STUDENT ";
			sql += " ORDER BY ID ";
			//3-1. PreparedStatment ��ü ���� <- Connetion ��ü�� ����
			pstmt = conn.prepareStatement(sql);
			
			//3-2. SQL����
			rs = pstmt.executeQuery();
			
			//4. SQL �������� ���� ó��
			while (rs.next()) {
				//DB ������ �ϳ��� StudentVO�� ���� + ����Ʈ�� �߰�
				StudentVO vo = new StudentVO(
						rs.getString("ID"), 
						rs.getString("NAME"), 
						rs.getInt("KOR"), 
						rs.getInt("ENG"), 
						rs.getInt("MATH"), 
						rs.getInt("TOT"), 
						rs.getDouble("AVG"));
				//����Ʈ�� �߰�
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnStmtRs(conn, pstmt, rs);
		}
		
		return list;
	}
	
	//DB�� ������ �Է�(INSERT)
	public int insertData(String id, String name, 
			int kor, int eng, int math, int tot, double avg) {
		return insertData(new StudentVO(id, name, kor, eng, math, tot, avg));
		
		/*
		int cnt = -1;
		//(�ǽ�) DB�����ϰ� ���޹��� ������ DB�� �Է� ó�� 
		try {
			//2. DB����  - Connection ��ü ���� <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. PreparedStatement ��� SQL ����
			String sql = "";
			sql += "INSERT INTO STUDENT ";
			sql += "       (ID, NAME, KOR, ENG, MATH, TOT, AVG) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			// ?(���ε庯��) ��ġ�� �� ����
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, kor);
			pstmt.setInt(4, eng);
			pstmt.setInt(5, math);
			pstmt.setInt(6, tot);
			pstmt.setDouble(7, avg);
			
			cnt = pstmt.executeUpdate(); //SQL�� ����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnStmt(conn, pstmt);
		}
		return cnt;
		*/
	}
	
	//DB�� ������ �Է�(INSERT) : StudentVO Ÿ�� �� ���޹޾� �Է� ó��
	public int insertData(StudentVO student) {
		int cnt = 0;
		//(�ǽ�) DB�����ϰ� ���޹��� ������ DB�� �Է� ó�� 
		try {
			//2. DB����  - Connection ��ü ���� <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. PreparedStatement ��� SQL ����
			String sql = "";
			sql += "INSERT INTO STUDENT ";
			sql += "       (ID, NAME, KOR, ENG, MATH, TOT, AVG) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			// ?(���ε庯��) ��ġ�� �� ����
			pstmt.setString(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setInt(3, student.getKor());
			pstmt.setInt(4, student.getEng());
			pstmt.setInt(5, student.getMath());
			pstmt.setInt(6, student.getTot());
			pstmt.setDouble(7, student.getAvg());
			
			cnt = pstmt.executeUpdate(); //SQL�� ����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnStmt(conn, pstmt);
		}
		return cnt;
	}
	
	//����(UPDATE) : StudentVO �����͸� �޾Ƽ� ����(ID�� ������ ��� ������ ����)
	//ID ������ ã�Ƽ� ����ó��
	public int updateData(StudentVO student) {
		int result = 0;
		try {
			//2. DB����  - Connection ��ü ���� <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. PreparedStatement ��� SQL ����
			String sql = "";
			sql += "UPDATE STUDENT ";
			sql += "   SET NAME = ? ";
			sql += "     , KOR = ? ";
			sql += "     , ENG = ? ";
			sql += "     , MATH = ? ";
			sql += "     , TOT = ? ";
			sql += "     , AVG = ? ";
			sql += " WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			
			// ?(���ε庯��) ��ġ�� �� ����
			int idx = 1;
			pstmt.setString(idx++, student.getName()); // 1
			pstmt.setInt(idx++, student.getKor()); // 2
			pstmt.setInt(idx++, student.getEng());
			pstmt.setInt(idx++, student.getMath());
			pstmt.setInt(idx++, student.getTot());
			pstmt.setDouble(idx++, student.getAvg());
			pstmt.setString(idx++, student.getId()); // 7
			
			result = pstmt.executeUpdate(); //SQL�� ����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnStmt(conn, pstmt);
		}		
		
		return result;
	}
	
	//����(DELETE) : ID���� ���޹޾� ������ ����
	public int deleteData(String id) {
		int result = 0;
		try {
			//2. DB����  - Connection ��ü ���� <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. PreparedStatement ��� SQL ����
			String sql = "DELETE FROM STUDENT WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			
			// ?(���ε庯��) ��ġ�� �� ����
			pstmt.setString(1, id); 
			
			result = pstmt.executeUpdate(); //SQL�� ����
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnStmt(conn, pstmt);
		}		
		
		return result;
	}
	
	//����(DELETE) : StudentVO Ÿ�� ���� ���޹޾� ID������ ������ ����
	public int deleteData(StudentVO student) {
		return deleteData(student.getId());
	}
	

	
}

//===============================================
//------ INSERT �ǽ� -------------
//(�ǽ�) StudentVO ����Ÿ�� ���޹޾Ƽ� �Է� ó��(TOT, AVG ��� �� �Է�)

//------ UPDATE �ǽ� -------------
//(�ǽ�) ID, NAME ���� �޾Ƽ� ID�� ��ȸ�ϰ� NAME ����
//(�ǽ�) ID, KOR, ENG, MATH ���� �޾Ƽ� ID�� ã��
//      KOR, ENG, MATH, TOT, AVG �� ����(TOT, AVG ���ó��)
//(�ǽ�) ID �޾Ƽ� TOT, AVG ���� ó��
//(�ǽ�) �̸� �޾Ƽ� TOT, AVG ���� ó��

//------ DELETE �ǽ� -------------
//(�ǽ�) StudentVO ����Ÿ�� �޾Ƽ� ����(ID������)
//(�ǽ�) �̸� �޾Ƽ� ����

//------ SELECT �ǽ� -------------
//(�ǽ�) �̸����� ��ȸ - �̸� �ߺ� ���ɼ� ����(List, Set, Map ���)








