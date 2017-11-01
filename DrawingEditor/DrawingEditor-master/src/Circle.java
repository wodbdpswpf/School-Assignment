import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Circle implements MyShape,Serializable {

	int off_x1;
	int off_y1;
	boolean group;
	boolean select;
	Ellipse2D cir;
	
	
	Circle(Ellipse2D cir){
		this.cir = cir;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.draw(cir);
		
	}

	@Override
	public void move(int drag_x1, int drag_y1) {
		// TODO Auto-generated method stub
		cir.setFrame((drag_x1 - off_x1), (drag_y1 - off_y1),
                cir.getWidth(),cir.getHeight());
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return cir;
	}

	@Override
	public void set_offset(int x, int y) {
		// TODO Auto-generated method stub
		 off_x1 = (int) (x-cir.getX());
		 off_y1 = (int) (y-cir.getY());
		
		
	}
	
	public boolean is_select(int x , int y){
		
		if(cir.contains(x, y)){
			select = true;
			return true;
		}
		
		return false;
	}
	
	

	@Override
	public void make_selectbox(Graphics2D g) {
		// TODO Auto-generated method stub
		if(select){
			  	g.drawRect((int) cir.getBounds().getX() - 2,
                      (int) cir.getBounds().getY() - 2, 5, 5);
			  	
                g.drawRect(
                      (int) cir.getBounds().getX()
                            + (int) cir.getBounds().getWidth() / 2,
                      (int) cir.getBounds().getY() - 2, 5, 5);
                
                g.drawRect(
                      (int) cir.getBounds().getX()
                            + (int) cir.getBounds().getWidth() - 2,
                      (int) cir.getBounds().getY() - 2, 5, 5);
                
                g.drawRect((int) cir.getBounds().getX() - 2,
                      (int) cir.getBounds().getY()
                            + (int) cir.getBounds().getHeight() / 2,
                      5, 5);
                
                g.drawRect(
                      (int) cir.getBounds().getX()
                            + (int) cir.getBounds().getWidth() - 2,
                      (int) cir.getBounds().getY()
                            + (int) cir.getBounds().getHeight() / 2,
                      5, 5);
                
                g.drawRect((int) cir.getBounds().getX() - 2,
                      (int) cir.getBounds().getY()
                            + (int) cir.getBounds().getHeight() - 2,
                      5, 5);
                
                g.drawRect(
                      (int) cir.getBounds().getX()
                            + (int) cir.getBounds().getWidth() / 2,
                      (int) cir.getBounds().getY()
                            + (int) cir.getBounds().getHeight() - 2,
                      5, 5);
                
                g.drawRect(
                      (int) cir.getBounds().getX()
                            + (int) cir.getBounds().getWidth() - 2,
                      (int) cir.getBounds().getY()
                            + (int)cir.getBounds().getHeight() - 2,
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
