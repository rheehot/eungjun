package com.mystudy.concert;
import java.util.Scanner;

public class Reservation {
	/*
	1. ȸ���� ���� �ý��� - �α��� �ʿ�
	2. �޴� : ���� / ��� / ��ȸ / ����
		"[1]����                [2]���                [3]��ȸ                [4]����                "
	3. �¼� : vip, sr, r, s... �¼� ������ �ϴ� �˾Ƽ�
			���ڸ� : �� , ���ŵ��ڸ� : ��
	4. ���� : �α��� - �޴����� - �ܼ�Ʈ ���� - �ܼ�Ʈ�� ���� - �¼� ��� ���� - �ڸ� ��ȣ ���� - ���� - �α׾ƿ�
	5. �����޽��� : �޴��� ���� �� �������� ��
	6. ����� ���� ���̵�, ��� �޾Ƽ� �ش� �ڸ� ���
	*/
	
	//�¼� ��ȣ�� �¼� ��ȣ�� ����� ���� ��Ÿ���� �ؼ� pk ���� �ȵǰ�!!!!!
	//VO�� DAO�� �ٲٱ�~~~
	//haveseat �Էµǰ� �����!!!(���ڸ�, ���õ��ڸ�)
	//seatprice ����(vip sr r s)
	//hallnum �ٸ� ���̺����� ��������
	//��� ��� ���� ���ϱ�
	 
	int seat_price;
	String have_seat;
	int concert_num = 1; //�ӽ÷� ����
	
	Scanner scan = new Scanner(System.in);
	
	int seatNum[] = new int[25];
	String seatVip[] = new String[25];
	String seatSr[] = new String[25];
	String seatR[] = new String[25];
	String seatS[] = new String[25];

	Reservation() {
		for (int i = 0; i < seatVip.length; i++) {
			seatNum[i] = i + 1;
			seatVip[i] = "��\t";
			seatSr[i] = "��\t";
			seatR[i] = "��\t";
			seatS[i] = "��\t";
		}
	}
	
	public void startReserv() {
		boolean bool = true;
		System.out.println("<<< �ܼ�Ʈ ���Ű� ���۵Ǿ����ϴ�");
		while (bool) {
			Integer choice;
			menu();
			System.out.print("���ϴ� �۾��� �������ּ���(1~4) : ");		
			try {
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
					case 1: //�����۾�
						showSeat();
						grade();
						new PaymentDAO().startPayment();
						break;
					case 2: //����۾�
						cancel();				
						break;
					case 3: //���� ���� ��ȸ
						showSeat();
						break;
					case 4: //���α׷� ����
						System.out.println("<<< �ܼ�Ʈ ���Ű� ����Ǿ����ϴ�.");
						bool = false;
						break;
					default:
						System.out.println("[����] 1~4 ������ ���ڸ� �����ϼ���");
				}
			}
			catch(Exception e) {
				System.out.println("[����] ���ڸ� �Է� �����մϴ�.");
				
			}
		}
		
		SEAT_INFO_VO svo = new SEAT_INFO_VO(grade , num, grade, concert_num, seat_price, have_seat);
		SEAT_INFO_DAO sdao = new SEAT_INFO_DAO();
		sdao.insert(svo);
		sdao.selectAll();
		//System.out.println(svo);
	}
	
	//��� �޼ҵ�
	public void cancel() {
		//���̵�, ��� üũ ��ġ�� ��� �ش� �¼� ���
		System.out.println("��Ҹ� ���� ���̵�� ��й�ȣ�� �ٽ� �Է����ּ���");
		inputIdPw();		
	}
	
	//�α��� - ��� �޼ҵ�
	private String id;
	private String pw;
	
	protected boolean inputIdPw() {
		boolean inputLog = false;
		
		while(true) {
			System.out.print("���̵� : ");
			id = scan.nextLine(); // ���̵� �Է�
			
			System.out.print("��й�ȣ : ");
			pw = scan.nextLine(); // ��й�ȣ �Է�
			
			CustomerLogVO lvo = new CustomerLogVO(id, pw);
			 
			boolean logTrue = new CustomerLogDAO().checkIdPassword(lvo);
			
			if (logTrue) {
				inputLog = true;
				System.out.println(grade + num + " �¼��� ���������� ��ҵǾ����ϴ�.");
				if (grade.equalsIgnoreCase("VIP")) {
					seatVip[num - 1] = "��\t";
					showSeat();
					break;
				}
				if (grade.equalsIgnoreCase("SR")) {
					seatSr[num - 1] = "��\t";			
					showSeat();
					break;
				}
				if (grade.equalsIgnoreCase("R")) {
					seatR[num - 1] = "��\t";
					showSeat();
					break;
				}
				if (grade.equalsIgnoreCase("S")) {
					seatS[num - 1] = "��\t";
					showSeat();
					break;
				}
				break;
			} else {
				System.out.println("�ٽ� �Է����ּ���.");
			}			
		}
		return inputLog;
	}
	
	
	//������ �¼� ��� ���� �޼ҵ�
	String grade;		
	public void grade() {
		while (true) {
			System.out.print("�¼� ����� �������ּ���(VIP/SR/R/S) : ");			
			grade = scan.nextLine();
			if (grade.equalsIgnoreCase("VIP")) {
				seatNum(seatVip);
				showSeat();
				break;
			}
			if (grade.equalsIgnoreCase("SR")) {
				seatNum(seatSr);				
				showSeat();
				break;
			}
			if (grade.equalsIgnoreCase("R")) {
				seatNum(seatR);
				showSeat();
				break;
			}
			if (grade.equalsIgnoreCase("S")) {
				seatNum(seatS);
				showSeat();
				break;
			}
			else {
				System.out.println("[����] ���� �¼� ����Դϴ�.");
			}
		
		}	
	}

	//������ �¼� ��ȣ ���� �޼ҵ�
	String seat[];
	Integer num;
	public void seatNum(String seat[]) {
		while (true) {
			try {
				System.out.print("�¼� ��ȣ�� �������ּ���(1~25) : ");
				num = Integer.parseInt(scan.nextLine());
				
				if (num < 1 || num > 25) {
					System.out.println("���� �¼� ��ȣ�Դϴ�.");
			
				} else {
					if (seat == seatVip) {
						if (seatVip[num - 1].toString().equals("��\t")) {
							System.out.println("�̹� ���õ� �ڸ��Դϴ�");
							continue;
						} 
						else {
							seatVip[num - 1] = "��\t";								
						}
					}
					if (seat == seatSr) {
						if (seatSr[num - 1].toString().equals("��\t")) {
							System.out.println("�̹� ���õ� �ڸ��Դϴ�");
							continue;
						} 
						else {
							seatSr[num - 1] = "��\t";								
						}
					}
					if (seat == seatR) {
						if (seatR[num - 1].toString().equals("��\t")) {
							System.out.println("�̹� ���õ� �ڸ��Դϴ�");
							continue;
						} 
						else {
							seatR[num - 1] = "��\t";								
						}
					}
					if (seat == seatS) {
						if (seatS[num - 1].toString().equals("��\t")) {
							System.out.println("�̹� ���õ� �ڸ��Դϴ�");
							continue;
						} 
						else {
							seatS[num - 1] = "��\t";
						}
					}
					break;
				}				
			}
			catch (Exception e) {
				System.out.println("[����] ���ڸ� �Է� �����մϴ�.");
			}	
		}
		if (seat == seatVip) {
			seat_price = 150000;
		}
		if (seat == seatSr) {
			seat_price = 130000;
		}
		if (seat == seatR) {
			seat_price = 100000;
		}
		if (seat == seatS) {
			seat_price = 90000;
		}
		
		if (seat[num-1].toString().equals("��")) {
			have_seat = "�����ȵ�";
		}
		else {
			have_seat = "������";
		}
	}
	

	//�¼� ��ȸ
	public void showSeat() {
		System.out.println();
		System.out.print("\t");
		for (int i = 0; i < seatVip.length; i++) {
			System.out.print(seatNum[i] + "\t");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("VIP\t");
		for (int i = 0; i < seatVip.length; i++) {
			System.out.print(seatVip[i]);
		}
		System.out.println();
		System.out.print("SR\t");
		for (int i = 0; i < seatSr.length; i++) {
			System.out.print(seatSr[i]);
		}
		System.out.println();
		System.out.print("R\t");
		for (int i = 0; i < seatR.length; i++) {
			System.out.print(seatR[i]);
		}
		System.out.println();
		System.out.print("S\t");
		for (int i = 0; i < seatS.length; i++) {
			System.out.print(seatS[i]);
		}
		System.out.println();
	}	
		
	//�޴� ����
	public void menu() {
		System.out.println("======================= �ܼ�Ʈ ���� �ý��� =======================");
		System.out.println("");
		System.out.println("[1]����                [2]���                [3]��ȸ                [4]����                ");
		System.out.println("");
		System.out.println("============================================================");
	}
	
}