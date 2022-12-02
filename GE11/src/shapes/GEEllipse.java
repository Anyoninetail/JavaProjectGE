package shapes;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape {
	public GEEllipse() {
		super(new Ellipse2D.Double());
	}
	
	@Override
	public void initDraw(Point startP) {
		this.startP = startP;
	}
	
	@Override
	public void setCoordinate(Point currentP) {
		Ellipse2D temEllipse = (Ellipse2D)myShape;
		temEllipse.setFrame(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);
	}
	
	@Override
	public GEShape clone() {
		return new GEEllipse();
	}
}
