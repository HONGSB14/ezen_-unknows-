package Day17;

import java.util.ArrayList;
import java.util.Arrays;

public class Day17_3 {
	
	public static void main(String[] args) {
		// 코스 클래스 제네릭에 사람클래스 넣기 
		코스<사람> 사람코스 = new 코스<>("일반인과정",5);
			사람코스.add( new 사람("일반인") );
			사람코스.add( new 직장인("직장인") ); // o : 사람클래스에게 상속 받았기 때문에 가능 
			사람코스.add( new 학생("학생") );  // o : 사람클래스에게 상속 받았기 때문에 가능 
			사람코스.add( new 고등학생("고등학생") ); // o : 학생클래스에게 상속 -> 사람클래스 상속 받았기 때문에 가능 
		// * 제네릭에 들어가는 클래스는 자식클래스도 같이 들어갈수 있다.
		코스<직장인> 직장인코스 = new 코스<>("직장인과정",5);
			// 직장인코스.add( new 사람("일반인") ); // x : 부모 클래스는 들어갈수 없다 .
			직장인코스.add( new 직장인("직장인") ); // o : 본인은 가능 
			//직장인코스.add( new 학생("학생") ); // x : 부모 클래스는 같지만 학생->직장인 변환 불가능
			//직장인코스.add( new 고등학생<>("고등학생") ); // x : 고등학생-> 직장인 변환 불가능
		코스<학생> 학생코스 = new 코스<>("학생과정", 5);
			// 학생코스.add( new 일반인("일반인") ); // x 
			// 학생코스.add( new 직장인("직장인") ); // x
			학생코스.add( new 학생("학생") ); // o
			학생코스.add( new 고등학생("고등학생") ); // o : 학생 클래스에게 상속 받았기 때문에 가능
		코스<고등학생> 고등학생코스 = new 코스<>("고등학생과정",5);
			//고등학생코스.add( new 일반인("일반인") ); // x 
			//고등학생코스.add( new 직장인("직장인") );//x
			//고등학생코스.add( new 학생("학생") );//x
			고등학생코스.add( new 고등학생("고등학생") );//o 
		
		// 메소드 호출 
		코스명단출력(사람코스);
		코스명단출력(직장인코스);
		코스명단출력(학생코스);
		코스명단출력(고등학생코스);
		// 메소드 호출 
			//학생코스명단출력(사람코스); // x
			//학생코스명단출력(직장인코스); // x
		학생코스명단출력(학생코스);
		학생코스명단출력(고등학생코스);
		
		직장인코스명단출력(사람코스);
		직장인코스명단출력(직장인코스);
			//직장인코스명단출력(학생코스); // x
			//직장인코스명단출력(고등학생코스); // x
			
			
			
			
			
			
			
			
			
	// *ArrayList
//		ArrayList<사람> list = new ArrayList<>();
//			list.add( new 사람("일반인") );
			
			
	} // main end 
	
	// 와일드카드 : ? [ 모든 클래스 대응 ]
		// 코스< ? >  : 모든 제네릭 가능 
		// 코스< ? extends 클래스명 > : 해당 클래스로부터 상속 받은(자식) 제네릭 클래스 가능
		// 코스< ? super 클래스명 > : 해당 클래스로부터 상속 준(부모) 제네릭 클래스 가능 
	
	// 1.
	public static void 코스명단출력( 코스<?> 코스명단 ) {
						//인수로부터 모든코스의 제네릭 받음
						// 코스<사람> : 사람 제네릭 코스만 가능
						// 코스< ? > : 모든 제네릭 코스가 가능 
		System.out.println( 코스명단.getName() +" 수강생명단 : "
					+ Arrays.toString( 코스명단.getStudents()) );
				// Arrays : 배열 관련 메소드 제공 
					// Arrays.toString( 배열명 ) : 해당 배열내 내용 모두 호출 
	}
	// 2. 
	public static void 학생코스명단출력( 코스< ? extends 학생> 코스명단 ) {
						// *학생 클래스로부터 상속받은 모든 클래스만 가능 
		System.out.println( 코스명단.getName() +" 수강생명단 : "
				+ Arrays.toString( 코스명단.getStudents()) );
	}
	// 3. 
	public static void 직장인코스명단출력( 코스< ? super 직장인> 코스명단) {
		System.out.println( 코스명단.getName() +" 수강생명단 : "
				+ Arrays.toString( 코스명단.getStudents()) );
	}
	
}





























