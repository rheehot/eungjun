package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import admin1.concertView;

public class Admin {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "MDGUEST";
	private static final String PASSWORD = "mdguest";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	Scanner scan = new Scanner(System.in);

	Admin ad = null;
	
//	---------------------------------------------------------------------------------------����̹� �ε�
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
		}
	}
//	---------------------------------------------------------------------------------------DB�� �Է�ó��
//�ܼ�Ʈ ����
	public List<ConcertInfoVO> selectAll() {
		 		
		   List<ConcertInfoVO> list = new ArrayList<>();

		 try {
			 conn = DriverManager.getConnection(URL, USER, PASSWORD);

			 StringBuilder sql = new StringBuilder();
			 sql.append("SELECT CONCERT_NUM ,CONCERT_NAME , HALL_NUM , CONCERT_DATE");
			 sql.append(" FROM SCHEDULE_INFO ");

			 pstmt = conn.prepareStatement(sql.toString());

			 rs = pstmt.executeQuery();
			 String str = "";
			 while(rs.next()) {
				 str += rs.getInt(1)+"\t\t";
				 str += rs.getString(2)+"\t";
				 str += rs.getInt(3)+"\t";
				 str += rs.getString(4);

				 list.add(new ConcertInfoVO(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			 }
		 }catch (SQLException e) {
					e.printStackTrace();
		 }finally {
					JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		 }
		 return list;	
		 }	
//------------------------------------------------------------------------------------------	
//�ܼ�Ʈ ��ġ����
	public List<LocationVO> location(int concert_num) {
		 
		 List<LocationVO> list = new ArrayList<>();
		 
		//DB���� - Connection ��ü ����(DB�� �����)
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CONCERT_NUM,HALL_LOCATION,SEAT_SUM");
			sql.append(" FROM HALL_INFO ");
			sql.append("WHERE CONCERT_NUM = ?");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, concert_num);
			rs = pstmt.executeQuery();
			String str = "";
			while(rs.next()) {
				str += rs.getInt(1)+"\t\t";
				str += rs.getString(2)+"\t";
				str += rs.getInt(3);

				list.add(new LocationVO(rs.getInt(3),rs.getString(2),rs.getInt(1)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	 
		}
//---------------------------------------------------------------------------	
	//ȸ��������ȸ
	public  ArrayList<CustomerVO> MemList1() {
		ArrayList <CustomerVO> list = new ArrayList();
			
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CUSTOMER_ID, CUSTOMER_PW,CUSTOMER_NAME,CUSTOMER_TEL");
			sql.append(" FROM CUSTOMER");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			CustomerVO str;
			while(rs.next()) { str = new CustomerVO(
				 rs.getString(1)+"\t",	
				 rs.getString(2)+"\t",	
				 rs.getString(3)+"\t",	
				 rs.getString(4));	

			list.add(str);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		}	
//------------------------------------------------------------------------------	
//������ȸ

	public List<BookListVO> BookList() {
		
		List<BookListVO> list = new ArrayList();
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT T.RESERVATION_NUM ,T.CONCERT_NAME,T.SEAT_NUM,T.CUSTOMER_ID,P.PAYMENT_DATE");
			sql.append(" FROM TICKET T, PAYMENT P ");
			sql.append(" WHERE T.PAYMENT_UID = P.PAYMENT_UID");
			

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				list.add(new BookListVO(rs.getInt("RESERVATION_NUM"),
						rs.getString("CONCERT_NAME"),rs.getString("SEAT_NUM"),
						rs.getString("CUSTOMER_ID"), rs.getString("PAYMENT_DATE")));
				
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//-----------------------------------------------------------------------	

	//�ܼ�Ʈ���
	public List<concertTotalVO> concertTotal() {
			
		List <concertTotalVO>list = new ArrayList();
			
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT T1.CONCERT_NUM, T1.CONCERT_NAME, T1.CNT, T1.SUM_PRICE, H.SEAT_SUM, (T1.CNT/H.SEAT_SUM)*100 AS PER ");
			sql.append(" FROM HALL_INFO H, ( SELECT CONCERT_NUM , CONCERT_NAME, COUNT(*) AS CNT, SUM(SEAT_PRICE) AS SUM_PRICE FROM TICKET GROUP BY CONCERT_NUM, CONCERT_NAME ) T1 ");
			sql.append("WHERE H.CONCERT_NUM = T1.CONCERT_NUM ");

			pstmt = conn.prepareStatement(sql.toString());
				
			rs = pstmt.executeQuery();
			
				while(rs.next()) 
				{	
					list.add(new concertTotalVO(
						rs.getString("CONCERT_NAME"),
						rs.getInt("CONCERT_NUM"),
						rs.getInt("CNT"),
						rs.getInt("SUM_PRICE"),
						rs.getInt("SEAT_SUM"),
						rs.getInt("PER")
						));
				}
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return list;
	}	
	
//--------------------------------------------------------------------------------	

//-----------------------------------------------------------------------------------
//���θ޼ҵ�
	public void AdminMenu() {
		boolean run = false;
		
		concertView view = new concertView();
		
		while(run == false) {
			
			System.out.println("1.�������� 2.ȸ����� 3.���Ÿ�� 4.���");
			System.out.print("�޴��� �����ϼ��� : ");

			String selectMenu = scan.nextLine();
//			if(selectMenu.equals("") && Integer.parseInt(selectMenu) >5 ) {
				
				if("1".equals(selectMenu)) {
		
					List<ConcertInfoVO> v2 = concertList();
					
					int i = 0;
					
					for(ConcertInfoVO viewV2 : v2) {
						
						System.out.println(v2.get(i).getConcert_num()+"\t"+v2.get(i).getConcert_name());
						i++;
					}
					System.out.println("������ �������� ��ȭ�� �����ϼ���");
					String selectConcert = scan.nextLine();
					
					view.selectConcertView(v2,Integer.parseInt(selectConcert)-1);
					
				}else if("2".equals(selectMenu)) {
					MemList();
					run = false;
					
				}else if("3".equals(selectMenu)) {
					ConcertBookLists();
					run = false;
				}else {
					System.out.println("ȸ�����������");
					ConcertTotal();
					run = false;
					
				}
//			}else {
//			System.out.println("[����] ���� 1-4������ �Է����ּ���");
		
//	
				}
			
		}
//	}
		
	
//-------------------------------------------------------------------
	
//--------------------------------------------------------------------------------------------
//�ܼ�Ʈ����Ʈ �޼ҵ�
	public List<ConcertInfoVO> concertList() {
		 
		 System.out.println("��������");
		 
		 System.out.println("��ȣ\t ������\t  ");
		 
		 List<ConcertInfoVO> vo1 = selectAll();
		 
		 return vo1;
	 }
//-------------------------------------------------------------------------------------------
//�ܼ�Ʈ���� �޼ҵ�
	public void concertInfo(List<ConcertInfoVO> con) {
		 
		 System.out.println("�ܼ�Ʈ ����");
		 
		 System.out.println("������� ����");
		 
		 System.out.println(con.get(0).getConcert_date());
		 
		 
		 List<LocationVO> info = location(con.get(0).getConcert_num());
		 
		 for (LocationVO mvo : info) {
				System.out.println( "�ܼ�Ʈ��ȣ" +mvo.concert_num +
									"�ܼ�Ʈ��ġ" +mvo.hall_location+
									"���¼���"+mvo.seat_sum);
			}
	 }
//------------------------------------------------------------------
//�������
	public ArrayList<CustomerVO> MemList() {
		
		System.out.println("ȸ����ȸ������");
		System.out.println("���̵�\t\t��й�ȣ\t\t�̸�\t\t��ȭ��ȣ");
		
		ArrayList<CustomerVO> vo = MemList1();
		
		for(CustomerVO vo2 : vo) {

			System.out.print(vo2.getCustomer_id());
			System.out.print("\t");
			System.out.print(vo2.getPassword());
			System.out.print("\t");
			System.out.print(vo2.getName());
			System.out.print("\t");
			System.out.print(vo2.getTel());
			System.out.print("\n");
		}
		return vo;
	}
//-----------------------------------------------------------------
//���Ÿ��
	
	public List<BookListVO> ConcertBookLists() {
		
		System.out.println("���Ÿ�� ��Ȳ");
		System.out.println("���Ź�ȣ\t  �ܼ�Ʈ�̸�\t �¼���ȣ\t ���̵� \t ���ų�¥");
		List<BookListVO> vo=BookList();
		
		int i =0;
		
		for (BookListVO vo1 : vo) {
			
			System.out.println(vo.get(i).getReservation_num()+
						       vo.get(i).getConcert_name()+
						       vo.get(i).getSeat_num()+
						       vo.get(i).getCustomer_id()+
						       vo.get(i).getPayment_date());
			
			i++;
		}
		return vo;
	}
//------------------------------------------------------------------
//��� �ܼ�Ʈ�� ��� �ܼ�Ʈ��ȣ, �ܼ�Ʈ�̸�, �ܼ�Ʈ ���ż�, ����, ���¼��� , ������
	
	
	public void ConcertTotal() {
		
		System.out.println("\t�ܼ�Ʈ ���������");
		System.out.println("�ܼ�Ʈ��ȣ        �ܼ�Ʈ�̸�      �ܼ�Ʈ���ż�        �Ѱ���       ���¼���     ������ ");
		
		
		List <concertTotalVO> list = new ArrayList();
		
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT  T1.CONCERT_NUM, T1.CONCERT_NAME, T1.CNT, T1.SUM_PRICE,  H.SEAT_SUM, (T1.CNT/H.SEAT_SUM)*100 AS PER  ");
			sql.append(" FROM HALL_INFO H,  (SELECT CONCERT_NUM , CONCERT_NAME, COUNT(*) AS CNT, SUM(SEAT_PRICE) AS SUM_PRICE FROM TICKET GROUP BY CONCERT_NUM, CONCERT_NAME ) T1  ");
			sql.append("WHERE H.CONCERT_NUM = T1.CONCERT_NUM");
			sql.append(" ORDER BY CONCERT_NUM ");

			pstmt = conn.prepareStatement(sql.toString());
				
			rs = pstmt.executeQuery();
			
				while(rs.next()) 
				{	
					list.add(new concertTotalVO(
						rs.getString("CONCERT_NAME"),
						rs.getInt("CONCERT_NUM"),
						rs.getInt("CNT"),
						rs.getInt("SUM_PRICE"),
						rs.getInt("SEAT_SUM"),
						rs.getInt("PER")
						));
				}
				for (concertTotalVO vo : list) {
					System.out.print(vo.concert_num);
					System.out.print(  "\t     ");
					System.out.print(vo.concert_name);
					System.out.print("\t");
					System.out.print(vo.total_cnt);
					System.out.print("\t");
					System.out.print(vo.total_price);
					System.out.print("\t");
					System.out.print(vo.seat_sum);
					System.out.print("\t");
					System.out.print(vo.seat_per);
					System.out.println("\n");
							
							
				}
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	//-----------------------------------------------------------------------------------------------------------------
	
}