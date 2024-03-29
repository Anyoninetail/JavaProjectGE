package transformer;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

import constants.GEConstants;
import shapes.GEShape;

public abstract class GETransformer {
	protected GEShape shape;
	protected BasicStroke dashedLineStroke;
	protected Point previousP;
	
	public GETransformer(GEShape shape) {
		this.shape = shape;
		float dashes[] = {GEConstants.DEFAULT_DASH_OFFSET};
		
		dashedLineStroke = new BasicStroke(
				GEConstants.DEFAULT_DASHLINE_WIDTH, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND, 10, dashes, 0);
	}
	
	abstract public void transformer(Graphics2D g2D, Point p);
	abstract public void init(Point p);
}
