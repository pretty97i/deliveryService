package com.fish;

public class FishPrint {
	
	String str;
	//개인정보출력
	public String basicPrint(FishDTO dto){
		String gender;
		if(dto.getGender()==1){
			gender = "남";
		}else{
			gender = "여";
		}
		//id, name, gender, age, birth, addr, birth, tel,
		str = String.format("id : %10s, 이름 : %s\n성별 : %s, 나이 : %d\n생일 : %s, 전화번호 : %s\n주소 : %s %s",
				dto.getId(),dto.getName(),gender,dto.getAge(),dto.getBirth(),dto.getTel(),dto.getAdd1(),dto.getAdd2());
		
		return str;
	}
	//주문확인출력
	public StringBuffer OrderPrint(FishDTO dto){
		
		//id, delidate, pat, shu, pizza, fish, choco
		StringBuffer sb = new StringBuffer("id : "+ dto.getId() +", 배송날짜 : "+dto.getDate());
			
		
		if(dto.getPat()!=0)
			sb.append("\n팥     : "+ dto.getPat());
		
		if(dto.getShu()!=0)
			sb.append("\n슈크림 : "+ dto.getShu());
		
		if(dto.getPizza()!=0)
			sb.append("\n피  자 : "+ dto.getPizza());
		
		if(dto.getFish()!=0)
			sb.append("\n붕어맛 : "+ dto.getFish());
		
		if(dto.getChoco()!=0)
			sb.append("\n초  코 : "+ dto.getChoco());
		
		sb.append("\n주문총액 : "+ dto.getTot());
		return sb;
	}
	
	//매출조회 출력
	public StringBuffer totPrint(FishDTO dto, String start, String end){
		
		//id, delidate, pat, shu, pizza, fish, choco
		StringBuffer sb = new StringBuffer(start +" ~ "+end);
			
		
		if(dto.getPat()!=0)
			sb.append("\n팥     : "+ dto.getPat());
		
		if(dto.getShu()!=0)
			sb.append("\n슈크림 : "+ dto.getShu());
		
		if(dto.getPizza()!=0)
			sb.append("\n피  자 : "+ dto.getPizza());
		
		if(dto.getFish()!=0)
			sb.append("\n붕어맛 : "+ dto.getFish());
		
		if(dto.getChoco()!=0)
			sb.append("\n초  코 : "+ dto.getChoco());
		
		sb.append("\n주문총액 : "+ dto.getTot());
		return sb;
	}

}
