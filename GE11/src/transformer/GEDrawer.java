package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import shapes.GEPolygon;
import shapes.GEShape;

public class GEDrawer extends GETransformer{
	public GEDrawer(GEShape shape) {
		super(shape);
	}
	
	public void init(Point p) {
		shape.initDraw(p);
	}

	@Override
	public void transformer(Graphics2D g2D, Point p) {
		g2D.setXORMode(g2D.getBackground());
		shape.draw(g2D);
		shape.setCoordinate(p);
		shape.draw(g2D);
	}
	
	public void continueDrawing(Point p) {
		((GEPolygon)shape).continueDrawing(p);
	}
	
	public void finalize(ArrayList<GEShape> shapeList) {
		shapeList.add(shape);
	}
}
