import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.io.Serializable;

public class Line implements MyShape, Serializable {
	private static final int HIT_BOX_SIZE = 2;
	int off_x1;
	int off_y1;
	int off_x2;
	int off_y2;
	boolean group;
	boolean select;
	Line2D line;

	Line(Line2D line) {

		this.select = false;
		this.line = line;
	}

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.draw(line);

	}

	public void move(int drag_x1, int drag_y1) {
		line.setLine((drag_x1 - off_x1), (drag_y1 - off_y1), (drag_x1 - off_x2), (drag_y1 - off_y2));
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return line;
	}

	@Override
	public void set_offset(int x, int y) {
		// TODO Auto-generated method stub
		off_x1 = (int) (x - line.getX1());
		off_y1 = (int) (y - line.getY1());
		off_x2 = (int) (x - line.getX2());
		off_y2 = (int) (y - line.getY2());

	}

	@Override
	public boolean is_select(int x, int y) {
		// TODO Auto-generated method stub
		int boxX = x - HIT_BOX_SIZE / 2;
		int boxY = y - HIT_BOX_SIZE / 2;
		int width = HIT_BOX_SIZE;
		int height = HIT_BOX_SIZE;
		if (line.intersects(boxX, boxY, width, height)) {
			this.select = true;
			return true;
		}
		return false;
	}

	@Override
	public void make_selectbox(Graphics2D g) {
		// TODO Auto-generated method stub
		if (select) {

			if (line.getX1() - line.getX2() >= 0) {

				g.drawRect((int) line.getBounds().getX() + (int) line.getBounds().getWidth() - 2,
						(int) line.getBounds().getY() - 2, 5, 5);

				g.drawRect((int) line.getBounds().getX() - 2,
						(int) line.getBounds().getY() + (int) line.getBounds().getHeight() - 2, 5, 5);

			} else {

				g.drawRect((int) line.getBounds().getX() - 2, (int) line.getBounds().getY() - 2, 5, 5);

				g.drawRect((int) (line.getBounds().getX() + line.getBounds().getWidth() - 2),
						(int) (line.getBounds().getY() + line.getBounds().getHeight() - 2), 5, 5);
			}
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
