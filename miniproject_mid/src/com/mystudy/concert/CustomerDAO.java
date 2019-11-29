//package com.mystudy.concert;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
////ȸ������
//public class CustomerDAO {
//	private static final String DRIVER = "oracle.jdbc.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//	private static final String USER = "mdguest";
//	private static final String PASSWORD = "mdguest";
//
//	private Connection conn;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
//	
//	Scanner scan = new Scanner(System.in);
//	private String id;
//	private String pw;
//
////	---------------------------------------------------------------------------------------����̹� �ε�
//	static {
//		try {
//			Class.forName(DRIVER);
//		} catch (ClassNotFoundException e) {
//		}
//		
//	}
////	---------------------------------------------------------------------------------------DB�� �Է�ó��
//	
//	
////	---------------------------------------------------------------------------------------join����
//	public void joinStart() {
//
//		CustomerVO vo = null;
//		CustomerDAO dao = new CustomerDAO();
//		int result = 0;
//		int select = 0;
//
//		String id = null;
//		String password;
//		String name;
//		String tel;
//
//		//���̵�, ��й�ȣ, �̸� ����
//		String idpwForm = "^[a-zA-Z0-9]{3,8}$"; //�ƾƵ� ��й�ȣ ���� : 3~8�ڸ�, �����ҹ���, �빮��, ���� ����
//		String nameForm = "^[��-�R]{1,12}$"; //�̸� ���� : �ѱ۸� ����, 1~12�ڸ�
//		String telForm = "^010-\\d{4}-\\d{4}$";//��ȭ��ȣ �Է� ����
//
//		while (true) {
//			System.out.println("========== �ݰ����ϴ� ȸ������ ������ �Դϴ�. ==========");
//			System.out.println(" [1.ȸ������    2.ȸ�� ���� ����    3.ȸ�� Ż��    4.��������]");
//			System.out.println("==============================================");
//			System.out.println("[�޴� ����]");
//			select = Integer.parseInt(scan.nextLine());
//
//
//			switch (select) {
//			case 1:
//				while (true) {
//					System.out.print("���̵� : ");
//					id = scan.nextLine();
//					if (id.matches(idpwForm)) {
//						if (dao.checkId(id)) {
//							System.out.println("�����ϴ� ���̵� �Դϴ�!");
//						} else {
//							break;
//						}
//					} else {
//						System.out.println("�ٸ� ���̵� �Է��ϼ���.");
//					}
//				}
//
//				while (true) {
//					System.out.print("��й�ȣ :");
//					password = scan.nextLine();
//					if (password.matches(idpwForm)) {
//						break;
//					} else {
//						System.out.println("���ǿ� ���� �ʽ��ϴ� �ٽ� �Է��ϼ���");
//					}
//				}
//
//				while (true) {
//					System.out.print("�̸� : ");
//					name = scan.nextLine();
//					if (name.matches(nameForm)) {
//						break;
//					} else {
//						System.out.println("�̸��� Ȯ���ϼ���");
//					}
//				}
//
//				while (true) {
//					System.out.print("�ڵ��� ��ȣ : ");
//					tel = scan.nextLine();
//					if (tel.matches(telForm)) {
//						if (dao.checkTel(tel)) {
//							System.out.println("�����ϴ� �ڵ�����ȣ �Դϴ�.");
//						} else {
//							break;
//						}
//					} else {
//						System.out.println("�ٽ� �Է� �ϼ���.");
//					}
//				}
//				vo = new CustomerVO(id, password, name, tel);
//				dao.join(vo);
//				System.out.println(" �α��� ȭ������ �Ѿ�ϴ�");
//				break;
//
//			case 2:
//				while (true) {
//					System.out.print("���̵� : ");
//					id = scan.nextLine();
//					System.out.print("��й�ȣ :");
//					password = scan.nextLine();
//					if (!dao.checkIdPassword(id, password)) {
//						System.out.println("���Գ����� �����ϴ�.");
//					} else {
//						break;
//					}
//				}
//
//				while (true) {
//					System.out.println("================ ȸ�� ���� ���� ===================");
//					System.out.println("[1.��й�ȣ ����    2.�̸� ����    3.��ȭ��ȣ ����    4.����ȭ������]");
//					System.out.println("==============================================");
//					select = Integer.parseInt(scan.nextLine());
//
//					if (select == 1) {
//						while (true) {
//							System.out.print("������ ��й�ȣ : ");
//							password = scan.nextLine();
//							if (password.matches(idpwForm)) {
//								dao.updatePassword(password, id);
//								System.out.println(password + "�� �ٲ�����ϴ�");
//								break;
//							} else {
//								System.out.println("��й�ȣ Ȯ��");
//							}
//						}
//					}
//					if (select == 2) {
//						while (true) {
//							System.out.print("������ �̸� : ");
//							name = scan.nextLine();
//							if (name.matches(nameForm)) {
//								dao.updateName(name, id);
//								System.out.println(name + "���� �ٲ�����ϴ�.");
//								break;
//							} else {
//								System.out.println("�̸�Ȯ��");
//							}
//						}
//					}
//					if(select == 3) {
//						while(true) {
//							System.out.print("������ ��ȭ��ȣ : ");
//							tel = scan.nextLine();
//							if(tel.matches(telForm)) {
//								dao.updatetel(tel, id);
//								System.out.println(tel +"���� ����Ǿ����ϴ�.");
//								break;
//							} else {
//								System.out.println("��ȭ��ȣȮ��");
//							}
//						}
//					}
//					if(select == 4) {
//						System.out.println("ó��ȭ��");
//						break;
//					}
//				}
//				break;
//
//			case 3:
//				while (true) {
//					System.out.print("���̵� : ");
//					id = scan.nextLine();
//					System.out.print("��й�ȣ :");
//					password = scan.nextLine();
//					if (!dao.checkIdPassword(id, password)) {
//						System.out.println("���� ȸ���Դϴ�");
//					} else {
//						vo = new CustomerVO(id, password);
//						dao.delete(vo);
//						System.out.println("Ż�� �Ǿ����ϴ�.");
//						break;
//					}
//				}
//				break;
//			case 4:
//				System.out.print("��������");
//				System.out.println(" �α��� ȭ������ �Ѿ�ϴ�");
//				CustomerLogMethod cl = new CustomerLogMethod();
//				cl.inputIdPw();
//				break;			
//			}
//			break;
//		}
//	}
////	--------------------------------------------------------------------------------------------
//	public int join(CustomerVO Customer_Join) {
//		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("INSERT INTO CUSTOMER ");
//			sql.append("	(CUSTOMER_ID, CUSTOMER_PW, CUSTOMER_NAME, CUSTOMER_TEL) ");
//			sql.append("VALUES (?, ?, ?, ?) ");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			int idx = 1;
//			pstmt.setString(idx++, Customer_Join.getId());
//			pstmt.setString(idx++, Customer_Join.getPassword());
//			pstmt.setString(idx++, Customer_Join.getName());
//			pstmt.setString(idx++, Customer_Join.getTel());
//
//			return pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmt(conn, pstmt);
//		}
//		return -1;
//	}
//
//	//��й�ȣ ���� �޼ҵ�
//	public int updatePassword(String password, String id) {
//		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("UPDATE CUSTOMER SET CUSTOMER_PW = ? WHERE CUSTOMER_ID = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			pstmt.setString(1, password);
//			pstmt.setString(2, id);
//
//			return pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmt(conn, pstmt);
//		}
//		return -1;
//	}
//
//	//�̸� ���� �޼ҵ�
//	public int updateName(String name, String id) {
//
//		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("UPDATE CUSTOMER SET CUSTOMER_NAME = ? WHERE CUSTOMER_ID = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			pstmt.setString(1, name);
//			pstmt.setString(2, id);
//
//			return pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmt(conn, pstmt);
//		}
//		return -1;	
//	}
//
//	//��ȭ��ȣ ���� �޼ҵ�
//	public int updatetel(String tel, String id) {
//
//		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("UPDATE CUSTOMER SET CUSTOMER_TEL = ? WHERE CUSTOMER_ID = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			pstmt.setString(1, tel);
//			pstmt.setString(2, id);
//
//			return pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmt(conn, pstmt);
//		}
//		return -1;	
//	}
//
//	public int delete(CustomerVO Customer_Join) {
//		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("DELETE FROM CUSTOMER ");
//			sql.append("	WHERE CUSTOMER_ID = ? AND CUSTOMER_PW = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			pstmt.setString(1, Customer_Join.getId());
//			pstmt.setString(2, Customer_Join.getPassword());
//
//			return pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmt(conn, pstmt);
//		}
//		return -1;
//	}
//
//	public boolean checkId(String id) {
//		boolean result = false;
//
//		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_ID = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			pstmt.setString(1, id);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				result = true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
//		}		
//		return result;
//	}
//
//	public boolean checkIdPassword(String id, String password) {
//		boolean result = false;
//
//		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT CUSTOMER_ID, CUSTOMER_PW FROM CUSTOMER WHERE CUSTOMER_ID = ? AND CUSTOMER_PW = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			pstmt.setString(1, id);
//			pstmt.setString(2, password);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				result = true;
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
//		}		
//		return result;
//	}
//
//	public boolean checkTel(String tel) {
//		boolean result = false;
//
//		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT CUSTOMER_TEL FROM CUSTOMER WHERE CUSTOMER_TEL = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//
//			pstmt.setString(1, tel);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {//�����Ͱ� ������
//				result = true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
//		}		
//		return result;
//	}
////	--------------------------------------------------------------------------------------------�α��ν���
//	protected boolean inputIdPw() {
//		boolean inputLog = false;
//		System.out.println("========================");
//		System.out.println("[ȯ���մϴ� �α����� �����մϴ�]");
//		System.out.println("========================");
//		
//		while(true) {
//			System.out.print("���̵� : ");
//			id = scan.nextLine(); // ���̵� �Է�
//			
//			System.out.print("��й�ȣ : ");
//			pw = scan.nextLine(); // ��й�ȣ �Է�
//			
//			CustomerLogVO lvo = new CustomerLogVO(id, pw);
//			 
//			boolean logTrue = new CustomerLogDAO().checkIdPassword(lvo);
//			
//			if (logTrue) {
//				inputLog = true;
//				break;
//			} else {
//				System.out.println("�ٽ� �Է����ּ���.");
//			}			
//		}
//		return inputLog;
//	}
////	�α��� ���� �޼ҵ�
//	
//	// �α���ó���� ���� id, password üũ
//	// boolean checkIdPassword(id, password)
//	public boolean checkIdPassword(CustomerLogVO lvo) {
//		boolean result = false;
//		String inputId = "";
//		String inputPw = "";
//
//		if (lvo.getId() != null && lvo.getPassword() != null) {
//			try {
//				conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//				StringBuffer sql = new StringBuffer();
//				sql.append("SELECT CUSTOMER_ID, CUSTOMER_PW ");
//				sql.append("  FROM CUSTOMER ");
//				sql.append(" WHERE CUSTOMER_ID = ? ");
//
//				pstmt = conn.prepareStatement(sql.toString());
//				String str = lvo.getId();
//				pstmt.setString(1, str);
//
//				rs = pstmt.executeQuery();
//
//				if (rs.next()) {
//					inputId = rs.getString(1);
//					inputPw = rs.getString(2);
//
//					if (checkIdPassword2(inputPw, lvo.getPassword())) {
//						result = true;
//						System.out.println("[�α��� �ϼ̽��ϴ�]");
//					} else {
//						System.out.println("[��й�ȣ�� Ʋ�Ƚ��ϴ�.]");
//					}
//
//				} else {
//					System.out.println("[���̵� �����ϴ�.]");
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} catch (NullPointerException e) {
//			} finally {
//				closeCnPsRs(conn, pstmt, rs);
//			}
//		} else {
//			System.out.println("[�߸� �Է��ϼ̽��ϴ�.]");
//		}
//
//		return result;
//	}
//
//	private boolean checkIdPassword2(String inputPw, String inputPw1) {
//
//		boolean result1 = false;
//		if (inputPw.equals(inputPw1)) {
//			result1 = true;
//		}
//		return result1;
//	}
//
//	private void closeCnPsRs(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//
//		try {
//			if (rs != null)
//				rs.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			if (pstmt != null)
//				pstmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			if (conn != null)
//				conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void closeCnPsRs(Connection conn, PreparedStatement pstmt) {
//
//		try {
//			if (pstmt != null)
//				pstmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			if (conn != null)
//				conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
////	--------------------------------------------------------------------------------------------
//
//
//}
//