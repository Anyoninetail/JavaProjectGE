package shapes;

import java.awt.Point;
import java.awt.Polygon;

public class GEPolygon extends GEShape{
	public GEPolygon() {
		super(new Polygon());
	}
	
	@Override
	public void initDraw(Point startP) {
		((Polygon)myShape).addPoint(startP.x, startP.y);
	}
	
	@Override
	public void setCoordinate(Point currentP) {
		((Polygon)myShape).xpoints[((Polygon)myShape).npoints - 1] = currentP.x;
		((Polygon)myShape).ypoints[((Polygon)myShape).npoints - 1] = currentP.y;
	}
	
	public void continueDrawing(Point p) {
		((Polygon)myShape).addPoint(p.x, p.y);
	}

	@Override
	public GEShape clone() {
		return new GEPolygon();
	}
}
