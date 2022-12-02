package transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import shapes.GEShape;

public class GEMover extends GETransformer {
	public GEMover(GEShape shape) {
		super(shape);
		previousP = new Point();
	}

	@Override
	public void transformer(Graphics2D g2D, Point p) {
		g2D.setXORMode(g2D.getBackground()); //배경색 빠짐
		g2D.setStroke(dashedLineStroke); //점선 설정
		shape.draw(g2D); //그리기
		
		Point tempP = new Point(p.x - previousP.x, p.y - previousP.y); //이동한 x, y 좌표 거리
		shape.moveCoordinate(tempP); //이동한 좌표의 도형으로 myShape를 바꾸는 메소드
		shape.draw(g2D); //그리기
		previousP = p; //현재 좌표 값을 이전 좌표값으로 처리
	}

	@Override
	public void init(Point p) {
		previousP = p;
	}
}
