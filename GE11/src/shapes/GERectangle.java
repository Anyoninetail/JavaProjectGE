package shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class GERectangle extends GEShape {
	public GERectangle() {
		super(new Rectangle());
	}
	
	@Override
	public void initDraw(Point startP) {
		this.startP = startP;
	}
	
	@Override
	public void setCoordinate(Point currentP) {
		Rectangle temRectangle = (Rectangle)myShape;
		temRectangle.setFrame(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);
	}

	@Override
	public GEShape clone() {
		return new GERectangle();
	}
}