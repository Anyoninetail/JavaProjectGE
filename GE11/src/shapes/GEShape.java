package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import constants.GEConstants;
import constants.GEConstants.EAnchorTypes;
import utils.GEAnchorList;

public abstract class GEShape {
	protected Point startP;
	protected Shape myShape;
	
	protected boolean selected; //도형이 선택되었는가
	protected GEAnchorList anchorList; //전체 앵커 배열 리스트
	protected EAnchorTypes selectedAnchor; //선택된 앵커
	protected Color lineColor, fillColor;	
	protected AffineTransform affineTransform; //2차원 이미지를 좌표 바꿔 유지시키는 클래스
	
	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public Shape getShape() {
		return myShape;
	}
	
	public void setShape(Shape myShape) {
		this.myShape = myShape;
	}
	
	public void initializeGraphicsAttributes() {
		lineColor = GEConstants.FOREGROUND_COLOR;
		fillColor = GEConstants.BACKGROUND_COLOR;
	}

	public GEShape(Shape shape) {
		this.myShape = shape;
		affineTransform = new AffineTransform();
		selected = false;
		anchorList = null;
	}
	
	public void moveCoordinate(Point tempP) {
		affineTransform.setToTranslation(tempP.getX(), tempP.getY());
		setShape(affineTransform.createTransformedShape(getShape()));
	}
	
	public void draw(Graphics2D g2D) {
		if(getFillColor() != null) {
			g2D.setColor(fillColor);
			g2D.fill(myShape);
		}
		if(getLineColor() != null) {
			g2D.setColor(lineColor);
			g2D.draw(myShape);
		}
		if(this.isSelected()) {
			getAnchorList().setPosition(this.getBounds());
			getAnchorList().draw(g2D);
		}
	}
	
	public Rectangle getBounds() {
		return myShape.getBounds();
	}
	
	public GEAnchorList getAnchorList() {
		return anchorList;
	}
	   
	public void setAnchorList(GEAnchorList anchorList) {
		this.anchorList = anchorList;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected) {
			anchorList = new GEAnchorList();
			anchorList.setPosition(myShape.getBounds());
		} else {
			anchorList = null;
		}
	}
	
	public boolean onShape(Point p) {
		if(anchorList != null) {
			selectedAnchor = anchorList.onAnchors(p);
			if(selectedAnchor != EAnchorTypes.NONE) {
				return true;
			}
		}
		return myShape.intersects(new Rectangle(p.x, p.y, 2, 2));
	}
	
	public abstract void initDraw(Point startP);
	public abstract void setCoordinate(Point currentP);
	public abstract GEShape clone();
}
