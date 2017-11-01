import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Square implements MyShape,Serializable {
	int x1;
	int y1;
	int width;
	int height;
	
	Square(int x1, int y1, int y2, int x2){
		this.x1 = x1;
		this.y1 = y1;
		this.width = (x2 > x1) ? x2 - x1 : x1 - x2;
		this.height = (y2 > y1) ? y2 - y1 : y1 - y2;
		
	}
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public java.awt.Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void set_offset(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void set_select(boolean select) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean is_select(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void make_selectbox(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void set_group(boolean group) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean get_group() {
		// TODO Auto-generated method stub
		return false;
	}

}
