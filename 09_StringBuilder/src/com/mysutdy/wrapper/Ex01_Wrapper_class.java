package com.mysutdy.wrapper;

public class Ex01_Wrapper_class {

	public static void main(String[] args) {
		// Wrapper_class : �⺻ ����Ÿ Ÿ���� Ŭ���� Ÿ�Ե��� ��Ī
		// �⺻����Ÿ Ÿ�� : ��ü �ҹ���, char, int, ...
		// boolean, char, byte, short, int, long, float, double
		// Wrapper class : �⺻����Ÿ Ÿ���� ù���ڸ� �빮�ڷ� �ۼ�
		// ���� : char->Character int->Integer
		// Boolean, Byte, Short,Long, Float, Double
		int num=100;
		System.out.println("num : " + num);
		
		//Integer inNum = 100; //���� Ư���� ���, 100����
		Integer intNum=new Integer(100);// ����, ��ü�� �ϳ� �����ȴ�
		System.out.println("intNum : " + intNum);
		intNum = new Integer("100");//������ ��쿡 ���� ����, String�� �޴� �����ڰ� �ֱ⶧��
		System.out.println("intNum : " + intNum);
		
		intNum = 900;//Integer <- int : �ڵ�����ȯ
		num = intNum;//int <- Integer : �ڵ�����ȯ
		
		System.out.println("������ int �ִ� : " + Integer.MAX_VALUE);
		System.out.println("������ int �ִ� : " + Integer.MIN_VALUE);
		
		num = Integer.parseInt("999");//Integer.parseInt : String Ÿ���� int Ÿ������ ��ȯ
		intNum = Integer.valueOf("999");//Integer.valueOf : IntergerŸ���� String Ÿ������ ��ȯ
		System.out.println("num+1 : " + (num+1));
		System.out.println("intNum+1 : " + (intNum+1));
		
		System.out.println("----- Boolean -----");
		Boolean bool = true;
		bool = new Boolean(true);
		bool = new Boolean("true"); //���ڿ� true �� ���� true�� �ٲ��
		System.out.println("bool : " + true);
		if(bool) {
			System.out.println("bool ���� boolean true");
		}
		
		bool = new Boolean("true1");
		System.out.println("bool : " + bool);//true �ƴѰ�� ��� false
		System.out.println(Boolean.TRUE);
		System.out.println(Boolean.FALSE);
		
		System.out.println("----- String -> Boolean -----"); //��ҹ��� �������
		bool = Boolean.valueOf("true");
		System.out.println("Boolean.valueOf(\"true\") : " + bool);
		
		bool = Boolean.valueOf("TRUE");
		System.out.println("Boolean.valueOf(\"TRUE\") : " + bool);
		
		bool = Boolean.valueOf("TRue");
		System.out.println("Boolean.valueOf(\"TRue\") : " + bool);
		
		System.out.println("----- Float, Double ���� -----");
		Float f = 10.5f; // Float  <- float
		f=new Float(12.5f);
		f=new Float("12.5f");
		System.out.println("new Float(\"12.5f\") : " + f);
		
		String str = String.valueOf(10.5f);//String <- Float
		str=String.valueOf(f);//String <- Float
		System.out.println("str : " + str);
		
		f=Float.valueOf(str);
		System.out.println("f : " + f);
		
	}

}
















