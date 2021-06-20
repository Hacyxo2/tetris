package project2;

import java.awt.Point;

public abstract class Piece {
	final int DOWN = 0; // 방향 지정
	final int LEFT = 1;
	final int RIGHT = 2;
	protected int r[]; // Y축 좌표 배열
	protected int c[]; // X축 좌표 배열
	protected TetrisData data; // 테트리스 내부 데이터
	protected Point center; // 조각의 중심 좌표

	public Piece(TetrisData data) {
		r = new int[4];
		c = new int[4];
		this.data = data;
		center = new Point(6, 0);
	}

	public abstract int getType();

	public abstract int roteType();

	public int getX() {
		return center.x;
	}

	public int getY() {
		return center.y;
	}

	public boolean copy() { // 값 복사
		boolean value = false;
		int x = getX();
		int y = getY();
		if (getMinY() + y <= 0) { // 게임 종료 상황
			value = true;
		}

		for (int i = 0; i < 4; i++) {
			data.setAt(y + r[i], x + c[i], getType());
		}
		return value;
	}

	public boolean isOverlap(int dir) { // 다른 조각과 겹치는지 파악
		int x = getX();
		int y = getY();
		switch (dir) {
		case 0: // 아래
			for (int i = 0; i < r.length; i++) {
				if (data.getAt(y + r[i] + 1, x + c[i]) != 0) {
					return true;
				}
			}
			break;
		case 1: // 왼쪽
			for (int i = 0; i < r.length; i++) {
				if (data.getAt(y + r[i], x + c[i] - 1) != 0) {
					return true;
				}
			}
			break;
		case 2: // 오른쪽
			for (int i = 0; i < r.length; i++) {
				if (data.getAt(y + r[i], x + c[i] + 1) != 0) {
					return true;
				}
			}
			break;
		}
		return false;
	}

	public int getMinX() {
		int min = c[0];
		for (int i = 1; i < c.length; i++) {
			if (c[i] < min) {
				min = c[i];
			}
		}
		return min;
	}

	public int getMaxX() {
		int max = c[0];
		for (int i = 1; i < c.length; i++) {
			if (c[i] > max) {
				max = c[i];
			}
		}
		return max;
	}

	public int getMinY() {
		int min = r[0];
		for (int i = 1; i < r.length; i++) {
			if (r[i] < min) {
				min = r[i];
			}
		}
		return min;
	}

	public int getMaxY() {
		int max = r[0];
		for (int i = 1; i < r.length; i++) {
			if (r[i] > max) {
				max = r[i];
			}
		}
		return max;
	}

	public boolean moveDown() { // 아래로 이동
		if (center.y + getMaxY() + 1 < TetrisData.ROW-1) {
			if (isOverlap(DOWN) != true) {
				center.y++;
			} else {
				return true;
			}
		} else {
			return true;
		}

		return false;
	}

	public void moveLeft() { // 왼쪽으로 이동
		if (center.x + getMinX() - 1 >= 1)
			if (isOverlap(LEFT) != true) {
				center.x--;
			} else
				return;
	}

	public void moveRight() { // 오른쪽으로 이동
		if (center.x + getMaxX() + 1 < TetrisData.COL-1)
			if (isOverlap(RIGHT) != true) {
				center.x++;
			} else
				return;
	}
	public void moveBottom() { // 가장 아래로 내림
		for(int i=0; i<21; i++) {
			this.moveDown();
		}
	}
	
	public void rotate() { // 조각 회전
		int rc = roteType();
		if (rc <= 1)
			return;

		if (rc == 2) {
			rotate4();
			
		} else {
			rotate4();
		}
	}
	
	public void rotate4() { // 조각 회전
		
		if(center.x == 1) {// 왼쪽 벽에 붙어있으면 한 칸 오른쪽으로 밀어냄
				center.x++;
		}
		
		else if(center.x == TetrisData.COL - 2) {// 오른쪽 벽에 붙어있으면 한 칸 왼쪽으로 밀어냄
				center.x--;
		}
		if (center.x > 1 && center.x < TetrisData.COL - 2) {// 양쪽 벽에 븥어있지 않은 블럭
			
			//왼쪽에 Bar 블럭이 아래가 긴 경우
			if(center.x + getMinY() <= 1) {
				center.x++; // 한 칸 오른쪽으로 밀어냄
			}
			if(center.x == TetrisData.COL-3 && center.x + getMaxY() <= TetrisData.COL) { // 오른쪽에 Bar 블럭이
				center.x--;					// 벽에서 두 칸 떨어져있고 위가 긴 경우 한 칸 왼쪽으로 밀어냄
			}
						
			for (int i = 0; i < 4; i++) {
				int temp = c[i];
				c[i] = -r[i];
				r[i] = temp;
			}
		}
	}
}