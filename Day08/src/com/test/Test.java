package com.test;

import java.util.HashSet;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		
		
		Random ran = new Random(System.currentTimeMillis()); //랜덤함수 나중에 업무때 사용하실일 없으실건데 사용하실일 있으시면 씨드 값 넣어야 합니다. 
		
		//------------------------------------방법1-------------------------------------------------
		
		//자바컬렉션 셋의 경우 중복을 허용하지 않음... 
		//이놈 쓰실일은 왠만하면 없으실건데 HashMap, ArrayList 이거 두개는 반드시 알아두셔야 합니다.
		
		HashSet<Integer> set = new HashSet<>(); //자바컬렉션 set을 이용한 방법
		
		while (set.size() < 7) { //셋에 길이가 7개 미만일때 까지 반복하여 돌림
			set.add(1 + ran.nextInt(44)); //셋에 랜덤수를 입력
		}
		System.out.println("방법1: " + set.toString()); //프린트
		
		//------------------------------------방법1  끝-------------------------------------------------
		
		int[] arr = new int[6]; //크기가 6개인 숫자형 배열을 선언 
		int cnt = 0;
		
		while( cnt < 6 ) { // cnt는 0부터 시작하기때문에 6개까지만 넣으려면 이렇게 써야함.
			int randomValue = 1 + ran.nextInt(44);
			boolean isDup = false;
			for (int j = 0; j < arr.length; j++) { //배열안에 중복값이 존재 하는지 체크하기 위해 존재함
				  if (arr[j] == randomValue ) { //배열안에 중복 값이 존재하면 true
					  isDup = true;
				  }
			}
			if (! isDup) { //중복이 존재 하지 않을때만 로직 진행
				arr[cnt] = randomValue; //배열안에 랜덤값을 넣어줌 (배열은 0부터 시작)
				cnt++; //(배열에 유효한 값을 체크해줌)
			} 
		}
		System.out.print("방법2: ["); //print는 줄바꿈을 하지 않음
		for (int i = 0; i < arr.length; i++) { //배열의 크기만큼 반복문 진행
			System.out.print((i == 0 ? "" : ", ") + arr[i]); // 해당부분은 삼항식임 왜 저렇게 나오는지는 숙제.
		}
		System.out.print("]");
		/* 아래와 같은 형식으로도 사용가능
		for (int i : arr ) {
			
		}
		*/
	}
}
