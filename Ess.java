package project2;

public class Ess extends Piece {
	public Ess(TetrisData data) {
		super(data);
		c[0] =  0;		r[0] =  0;
		c[1] =  0;		r[1] =  1;
		c[2] = -1;		r[2] =  0;
		c[3] =  1;		r[3] =  1;
	}
	public int getType() {
		return 6;
	}
	public int roteType() {
		return 2;
	}
}
 