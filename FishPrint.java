package com.fish;

public class FishPrint {
	
	String str;
	//�����������
	public String basicPrint(FishDTO dto){
		String gender;
		if(dto.getGender()==1){
			gender = "��";
		}else{
			gender = "��";
		}
		//id, name, gender, age, birth, addr, birth, tel,
		str = String.format("id : %10s, �̸� : %s\n���� : %s, ���� : %d\n���� : %s, ��ȭ��ȣ : %s\n�ּ� : %s %s",
				dto.getId(),dto.getName(),gender,dto.getAge(),dto.getBirth(),dto.getTel(),dto.getAdd1(),dto.getAdd2());
		
		return str;
	}
	//�ֹ�Ȯ�����
	public StringBuffer OrderPrint(FishDTO dto){
		
		//id, delidate, pat, shu, pizza, fish, choco
		StringBuffer sb = new StringBuffer("id : "+ dto.getId() +", ��۳�¥ : "+dto.getDate());
			
		
		if(dto.getPat()!=0)
			sb.append("\n��     : "+ dto.getPat());
		
		if(dto.getShu()!=0)
			sb.append("\n��ũ�� : "+ dto.getShu());
		
		if(dto.getPizza()!=0)
			sb.append("\n��  �� : "+ dto.getPizza());
		
		if(dto.getFish()!=0)
			sb.append("\n�ؾ�� : "+ dto.getFish());
		
		if(dto.getChoco()!=0)
			sb.append("\n��  �� : "+ dto.getChoco());
		
		sb.append("\n�ֹ��Ѿ� : "+ dto.getTot());
		return sb;
	}
	
	//������ȸ ���
	public StringBuffer totPrint(FishDTO dto, String start, String end){
		
		//id, delidate, pat, shu, pizza, fish, choco
		StringBuffer sb = new StringBuffer(start +" ~ "+end);
			
		
		if(dto.getPat()!=0)
			sb.append("\n��     : "+ dto.getPat());
		
		if(dto.getShu()!=0)
			sb.append("\n��ũ�� : "+ dto.getShu());
		
		if(dto.getPizza()!=0)
			sb.append("\n��  �� : "+ dto.getPizza());
		
		if(dto.getFish()!=0)
			sb.append("\n�ؾ�� : "+ dto.getFish());
		
		if(dto.getChoco()!=0)
			sb.append("\n��  �� : "+ dto.getChoco());
		
		sb.append("\n�ֹ��Ѿ� : "+ dto.getTot());
		return sb;
	}

}
