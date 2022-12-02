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
		g2D.setXORMode(g2D.getBackground()); //���� ����
		g2D.setStroke(dashedLineStroke); //���� ����
		shape.draw(g2D); //�׸���
		
		Point tempP = new Point(p.x - previousP.x, p.y - previousP.y); //�̵��� x, y ��ǥ �Ÿ�
		shape.moveCoordinate(tempP); //�̵��� ��ǥ�� �������� myShape�� �ٲٴ� �޼ҵ�
		shape.draw(g2D); //�׸���
		previousP = p; //���� ��ǥ ���� ���� ��ǥ������ ó��
	}

	@Override
	public void init(Point p) {
		previousP = p;
	}
}
