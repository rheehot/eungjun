package com.mystudy.ex04_interface2;

//�������̽� : �߻�ü
interface I_Phone {
	//{} ���� : �߻�޼ҵ�(abstract method)
	//public abstract �޼ҵ���
	public abstract String getType();
	public String getPhoneNo(); //abstract ��������
	
	void call(); //public abstract ��������
	void receiveCall(); //public abstract ��������
	void view(); //public abstract ��������
	
	void sendMsg();
	void receiveMsg();
}





