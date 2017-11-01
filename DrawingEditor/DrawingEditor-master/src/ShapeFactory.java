import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class ShapeFactory {
	
	public MyShape getShape(Shape shape){
				
		if(shape instanceof Ellipse2D){
			
			return new Circle((Ellipse2D) shape);
			
		}else if(shape instanceof Rectangle2D){
			
			return new Rectangle((Rectangle2D) shape);
			
		}else if(shape instanceof Line2D){
			
			return new Line((Line2D) shape);
		}
		return null;
		
	}

}
