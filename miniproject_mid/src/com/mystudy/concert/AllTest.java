package com.mystudy.concert;

public class AllTest {

	public static void main(String[] args) {
		
		ConcertAll ca = new ConcertAll();
		
		ca.joinStart(); //ȸ������, �α���
		ca.SelectConcert(); //�ܼ�Ʈ Ƽ�� ��ȸ
		ca.startReserv(); //�޴�����
		ca.startPayment(); //����ȭ��
		
	}
}