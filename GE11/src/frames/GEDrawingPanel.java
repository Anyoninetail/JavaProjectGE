package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import constants.GEConstants;
import constants.GEConstants.EState;
import shapes.GEPolygon;
//import constants.GEConstants.EToolBarButtons;
//import shapes.GEEllipse;
//import shapes.GELine;
//import shapes.GERectangle;
import shapes.GEShape;
import transformer.GEDrawer;
import transformer.GEMover;
import transformer.GETransformer;

public class GEDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GEShape currentShape; //���� ���� ��ü
	private ArrayList<GEShape> shapeList; //�׷��� �������� �����صδ� �迭����Ʈ
	private MouseDrawingHandler drawingHandler;
	private EState currentState;
	private GEShape selectedShape; //���õ� ����
	private GETransformer transformer; //���� ���(�׸���, �̵�, ũ�� ���� �� �ϳ�)
	private Color lineColor, fillColor;
	
	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		//������ ���õ� �������� �ƴ��� üũ
		if(selectedSetColor(true, lineColor)) {
			return;
		}
		this.lineColor = lineColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		//������ ���õ� �������� �ƴ��� üũ
		if(selectedSetColor(false, fillColor)) {
			return;
		}
		this.fillColor = fillColor;
	}
	
	public boolean selectedSetColor(boolean flag, Color color) {
		if(selectedShape != null) {
			if(flag) {
				selectedShape.setLineColor(color);
			} else {
				selectedShape.setFillColor(color);
			}
			repaint();
			return true;
		}
		return false;
	}

	public EState getCurrentState() {
		return currentState;
	}
	
	public void setCurrentShape(GEShape currentShape) {
		this.currentShape = currentShape;
	}
	
	public void initializeGraphicsAttributes() {
		lineColor = GEConstants.FOREGROUND_COLOR;
		fillColor = GEConstants.BACKGROUND_COLOR;
	}
	
	public GEDrawingPanel() {
		super();
		
		currentState = EState.Idle;
		shapeList = new ArrayList<GEShape>();
		drawingHandler = new MouseDrawingHandler();
		addMouseListener(drawingHandler);
		addMouseMotionListener(drawingHandler);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		initializeGraphicsAttributes();
		}
	
	private void initDraw(Point startP) {
		selectedShape = currentShape.clone();
		transformer = new GEDrawer(selectedShape);
		((GEDrawer)transformer).init(startP);
		selectedShape.setLineColor(lineColor);
		selectedShape.setFillColor(fillColor);
	}
	
	private void continueDrawing(Point currentP) {
		((GEDrawer)transformer).continueDrawing(currentP);
	}
	
	//���õ� ������ ��ȯ�ϴ� �޼ҵ�
	private GEShape onShape(Point p) {
		for(GEShape shape : shapeList) {
			if(shape.onShape(p)) {
				return shape;
			}
		}
		return null;
	}
	
	//��Ŀ ���� �޼ҵ�
	private void clearSelectedShapes() {
		for(GEShape shape : shapeList) {
			shape.setSelected(false);
		}
	}
	
	private class MouseDrawingHandler extends MouseInputAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			//������ �׸� ��ġ�� ���� ��ǥ���� ������(�ʱ⼼�� �޼ҵ� ȣ��)
			if(currentState == EState.Idle) {
				if(currentShape != null) {
					clearSelectedShapes();
					initDraw(e.getPoint());
					transformer = new GEDrawer(selectedShape);
					((GEDrawer)transformer).init(e.getPoint());
					if(currentShape instanceof GEPolygon) {
						currentState = EState.NPointsDrawing;
					} else {
						currentState = EState.TwoPointsDrawing;
					}
				} else {
					selectedShape = onShape(e.getPoint());
					if(selectedShape != null) {
						clearSelectedShapes();
						selectedShape.setSelected(true);
						transformer = new GEMover(selectedShape);
						currentState = EState.Moving;
						((GEMover)transformer).init(e.getPoint());
					} else {
						clearSelectedShapes();
					}
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			//������ �巡�� �� �� ���� ����ؼ� �׸��� �޼ҵ� ȣ��
			if(currentState == EState.TwoPointsDrawing) {
				transformer.transformer((Graphics2D)getGraphics(), e.getPoint());
			} else if(currentState == EState.Moving) {
				transformer.transformer((Graphics2D)getGraphics(), e.getPoint());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// �Ϲ� ������ ���� �Ʒ� �޼ҵ� ����ǵ��� ���ǹ� �߰�
			// �׸��Ⱑ ������ ���´� �ٽ� �ʱⰪ(Idle) ���·� ���ư�
			if(currentState == EState.TwoPointsDrawing) {
				((GEDrawer)transformer).finalize(shapeList);
				selectedShape = null;
			} else if(currentState == EState.NPointsDrawing) {
				return;
			}
			currentState = EState.Idle;
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(currentState == EState.NPointsDrawing) {
					if(e.getClickCount() == 1) {
						continueDrawing(e.getPoint());
					} else if(e.getClickCount() == 2) {
						((GEDrawer)transformer).finalize(shapeList);
						currentState = EState.Idle;
						selectedShape = null;
						//repaint();
					}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(currentState == EState.NPointsDrawing) {
				transformer.transformer((Graphics2D)getGraphics(), e.getPoint());
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		for(GEShape shape : shapeList) {
			shape.draw(g2D);
			if(shape.isSelected()) {
				shape.getAnchorList().draw(g2D);
			}
		}
	}
}