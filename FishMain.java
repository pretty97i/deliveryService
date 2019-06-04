package com.fish;

import java.io.IOException;
import java.util.Scanner;

import com.db.DBConn;

public class FishMain extends Thread {

	public static void main(String[] args) throws Exception {
		
		Fish ob = new Fish();
		FishException fe = new FishException();
		FishImage fi = new FishImage();
		Scanner sc  = new Scanner(System.in);
		
		
		int ch;
		String temp;
		
//-----------------------------------------------------------------------------------------------------------
		boolean flag = true;
		
		for(int i=0;i<10;i++){
			try {sleep(300);} catch (Exception e) {}

			for(int x=0;x<40;x++){System.out.println();}

			if(flag){
				for(int j=0;j<fi.Fish1.length;j++){System.out.print(fi.Fish1[j]);}
				flag = false;
			}else {
				for(int k=0;k<fi.Fish2.length;k++){System.out.print(fi.Fish2[k]);}
				flag = true;
			}
		}
		
		for (int i=0;i<fi.name.length;i++) {
			System.out.print(fi.name[i]);
			try {sleep(100);} catch (Exception e) {}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		while(true){
			do{
				System.out.println("1.�α��� 2.ȸ������ 3.����");
				temp = sc.next();
				fe.onlyNum(temp);
				ch = Integer.parseInt(temp);			
			}while((ch<0||ch>4)&&ch!=100);
			
			switch (ch){
			case 100:
				ob.todayDelivaryCheck();break;
			
			case 0:
				//������ȸ
				ob.searchCash();break;
			case 2:
				//ȸ������
				ob.insert();break;
			case 3:
				//����
				DBConn.close();System.exit(0);
			case 1:
				//�α���
				if(!ob.login()){break;
				}else{
					//�α�����
					ob.CheckDate();
					while(ch!=6&&ch!=5){
						do{
							System.out.println("1.�ֹ� 2.�ֹ�Ȯ�� 3.��۳�¥���� 4.������������ 5.ȸ��Ż�� 6.�α׾ƿ�");
							ch = sc.nextInt();
						}while(ch<1||ch>6);
						
						switch (ch){
						case 1:
							//�ֹ�
							ob.order();break;
						case 2:
							//�ֹ�Ȯ��
							ob.orderCheck();break;
						case 3:
							//��۳�¥����
							ob.SetDelidate();break;
						case 4:
							//������������
							ob.memberUpdate();break;
						case 5:
							ob.delete();break;
						case 6:
							//�α׾ƿ�
							ob.logout();break;
						}//switch~end
					}//while(ch!=5)~end
				}//else~end
				break;
			}//switch~end
		}//while(true)~end
	}
}
