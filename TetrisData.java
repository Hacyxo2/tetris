package project2;


public class TetrisData {
	public static final int ROW = 21;
	public static final int COL = 12;
	public Field field;
	private static int data[][];
	static int line = 0;
	private int count = 0;
	static int gauge = 0;
	
	

	public TetrisData() {
		data = new int[ROW][COL];
	}
	
	public int getAt(int x, int y) {
		if(x < 0 || x >= ROW || y < 0 || y >= COL) // x가 0보다 작거나. x가 최대행 보다 크거나. y가 0보다 작거나, y가 최대열보다 크면
			return 0;
		return data[x][y]; //아니면 해당 좌표의 값을 리턴
	}
	
	public void setAt(int x, int y, int v) { // x 좌표, y 좌표, v는 값
		data[x][y] = v;
	}
	
	public int getLine() {
		
		return line;
	}
	public void sort() {
		for(int i = ROW - 1; i >= 1; i--){   // i가 행 -1 부터, 1과 같거나 클때까지 i--	   	
			for(int k = 1; k < COL-1; k++) { // k가 1부터 열보다 작을 동안 k++
				if(getAt(i, k) != 0){        // 해당 좌표가 0이 아니면 
					data[i][k] =0;     		 // 그 좌표를 0으로 만든다. 배열에서 해당 값이 사라진다.
					count++;				 // 지워진 수 만큼 count를 늘린다.
				}
			}
		}
		for(int i = ROW -2; i >= 1; i--) {   // i가 행 -2 부터, 0과 같거나 클때까지 i--
			for(int k = 1; k < COL-1; k++) { // k가 0부터 열보다 작을 동안 k++
				if(count > 0) { 			 // count가 0보다 크면
					data[i][k] = (int)((Math.random() * Integer.MAX_VALUE) % 6 + 1); // 조각의 색은 숫자에 따라 바뀐다.
					count--; 				 // count를 1 줄인다. 
				}
			}
		}gauge--; // 정렬을 사용하면 게이지를 1 줄인다.
	}
	public synchronized void removeLines() {
		
		NEXT: 							     // 배열 라벨
		for(int i = ROW - 1; i >= 1; i--){   // i가 행 -1 부터, 1과 같거나 클때까지 i--
			boolean done = true;		   	 // done을 참
			for(int k = 1; k < COL-1; k++) { // k가 1부터 열보다 작을 동안 k++
				if(data[i][k] == 0) {		 // 해당 좌표가 0이면
					done = false;			 // done을 거짓으로 바꿈
					continue NEXT;           // 해당 배열을 반복한다.
				}
			}
			if(i != 0) {						// i가 0이 아니면
				for(int y = 0; y < COL; y++) {  // y가 0 y가 열보다 작을 동안 y++
					data[0][y] = 0;				// 0행의 좌표 값을 전부 0으로 초기화
				}
			}
			if(done) {								// 위 이중배열을 반복하여 done값이 참일 때
				line++;								// 라인++
				if(line % 5 == 0) {
					gauge++;
				}
				for(int x = i; x > 0; x--) {		// x가 행, x가 0보다 클때까지 x--
					for(int y = 0; y < COL; y++) {  // y가 0 y가 열보다 작을 동안 y++
						data[x][y] = data[x-1][y];	// 위에 쌓인 블럭을 아래로 내림
					}
					
				}	
			}
		}
	}
	public void clear() {
		for(int i = 0; i < ROW; i++) {
			for(int k = 0; k < COL; k++) {
				data[i][k] = 0;
			}
		}
	}
	public void dump() {
		for(int i = 0; i < ROW; i++) {
			for(int k = 0; k < COL; k++) {
				System.out.print(data[i][k] + " ");
			}
			System.out.println();
		}
	}
}
