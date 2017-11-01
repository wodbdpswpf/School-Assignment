import java.awt.Graphics2D;
import java.awt.Shape;

public interface MyShape{

	void draw(Graphics2D g);
	
	void move(int x , int y);
	
	void set_offset(int x, int y);
	
	void set_select(boolean select);
	
	void set_group(boolean group);
	
	boolean get_group();
	
	boolean is_select(int x,int y);
	
	void make_selectbox(Graphics2D g);
	
	Shape getShape();
}
