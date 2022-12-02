package shapes;

import java.awt.Point;
import java.awt.geom.Line2D;

public class GELine extends GEShape {
	public GELine() {
		super(new Line2D.Double());
	}
	
	@Override
	public void initDraw(Point startP) {
		this.startP = startP;
	}
	
	@Override
	public void setCoordinate(Point currentP) {
		Line2D temLine = (Line2D)myShape;
		temLine.setLine(startP.x, startP.y, currentP.x, currentP.y);
	}
	
	@Override
	public GEShape clone() {
		return new GELine();
	}
}
