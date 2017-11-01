import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Rectangle implements MyShape,Serializable {
	int width;
	int height;
	int off_x1;
	int off_y1;
	boolean group;
	boolean select;
	Rectangle2D rec ;
	
	
	Rectangle(Rectangle2D rec){
		this.rec = rec;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.draw(rec);
	}

	@Override
	public void move(int drag_x1, int drag_y1) {
		// TODO Auto-generated method stub
		rec.setRect((drag_x1 - off_x1), (drag_y1 - off_y1),
                rec.getWidth(), rec.getHeight());
	
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return rec;
	}

	@Override
	public void set_offset(int x, int y) {
		// TODO Auto-generated method stub
		 off_x1 = (int) (x-rec.getX());
		 off_y1 = (int) (y-rec.getY());
		
	}

	@Override
	public boolean is_select(int x , int y) {
		// TODO Auto-generated method stub
		
		if(rec.contains(x, y)){
			select = true;
			
			return true;
		}
		return false;
	}

	@Override
	public void make_selectbox(Graphics2D g) {
		// TODO Auto-generated method stub
		if(select){
			  g.drawRect((int) rec.getBounds().getX() - 2,
                      (int) rec.getBounds().getY() - 2, 5, 5);
                g.drawRect(
                      (int) rec.getBounds().getX()
                            + (int) rec.getBounds().getWidth() / 2,
                      (int) rec.getBounds().getY() - 2, 5, 5);
                g.drawRect(
                      (int) rec.getBounds().getX()
                            + (int) rec.getBounds().getWidth() - 2,
                      (int) rec.getBounds().getY() - 2, 5, 5);
                g.drawRect((int) rec.getBounds().getX() - 2,
                      (int) rec.getBounds().getY()
                            + (int) rec.getBounds().getHeight() / 2,
                      5, 5);
                g.drawRect(
                      (int) rec.getBounds().getX()
                            + (int) rec.getBounds().getWidth() - 2,
                      (int) rec.getBounds().getY()
                            + (int) rec.getBounds().getHeight() / 2,
                      5, 5);
                g.drawRect((int) rec.getBounds().getX() - 2,
                      (int) rec.getBounds().getY()
                            + (int) rec.getBounds().getHeight() - 2,
                      5, 5);
                g.drawRect(
                      (int) rec.getBounds().getX()
                            + (int) rec.getBounds().getWidth() / 2,
                      (int) rec.getBounds().getY()
                            + (int) rec.getBounds().getHeight() - 2,
                      5, 5);
                g.drawRect(
                      (int) rec.getBounds().getX()
                            + (int) rec.getBounds().getWidth() - 2,
                      (int) rec.getBounds().getY()
                            + (int)rec.getBounds().getHeight() - 2,
                      5, 5);

			}
	}

	@Override
	public void set_select(boolean select) {
		// TODO Auto-generated method stub
		this.select = select;
	}

	@Override
	public void set_group(boolean group) {
		// TODO Auto-generated method stub
		
		this.group = group;
		
	}

	@Override
	public boolean get_group() {
		// TODO Auto-generated method stub
		return this.group;
	}

	
}
