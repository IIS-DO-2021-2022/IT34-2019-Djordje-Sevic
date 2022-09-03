package mvc;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import dialogs.*;
import geometry.*;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JColorChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmDrawing extends JFrame {

	private ButtonGroup tglButtons = new ButtonGroup();
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnLine = new JToggleButton("Line");		
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JToggleButton tglbtnFill = new JToggleButton("Fill");
	private JColorChooser colorPalete = new JColorChooser(Color.BLACK);
	private PnlDrawing drawingPanel = new PnlDrawing(this);
	private JButton btnModify = new JButton("Modify");	
	private JButton btnDelete = new JButton("Delete");
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		setResizable(false);
		setTitle("IT 34-2019 Sevic Djordje");
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		colorPalete.setPreviewPanel(new JPanel());
		colorPalete.setChooserPanels(new AbstractColorChooserPanel[] {colorPalete.getChooserPanels()[0]});
		
		tglButtons.add(tglbtnSelect);
		tglButtons.add(tglbtnPoint);
		tglButtons.add(tglbtnRectangle);
		tglButtons.add(tglbtnCircle);
		tglButtons.add(tglbtnDonut);
		tglButtons.add(tglbtnLine);
		tglButtons.add(tglbtnFill);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(drawingPanel.getSelectedShape() == null) {
					JOptionPane.showMessageDialog(drawingPanel, "You didn't select shape!", "Warrning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(JOptionPane.showConfirmDialog(drawingPanel, "Are you sure?") != 0) return;
				drawingPanel.getListOfShapes().remove(drawingPanel.getSelectedShape());
				drawingPanel.setSelectedShape(null);
				drawingPanel.repaint();
			}
		});	
		
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modify();
			}
		});	
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(colorPalete, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnDonut, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(tglbtnCircle, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(tglbtnSelect, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
						.addComponent(tglbtnFill, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addGap(518))
				.addComponent(drawingPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnPoint)
								.addComponent(tglbtnSelect))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnLine)
								.addComponent(tglbtnFill))
							.addGap(4)
							.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnCircle)
								.addComponent(btnModify))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnDonut)
								.addComponent(btnDelete)))
						.addComponent(colorPalete, GroupLayout.PREFERRED_SIZE, 137, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(drawingPanel, GroupLayout.PREFERRED_SIZE, 596, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void modify() {
		Shape selectedShape = drawingPanel.getSelectedShape();
		
		if(selectedShape == null) {
			JOptionPane.showMessageDialog(drawingPanel, "You didn't select shape!", "Warrning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(selectedShape instanceof Point) {
			DlgPoint p = new DlgPoint();
			p.getTxtX1().setText(String.valueOf(((Point) selectedShape).getX()));
			p.getTxtY1().setText(String.valueOf(((Point) selectedShape).getY()));
			p.setColor(selectedShape.getColor());
			p.setVisible(true);
			if(!p.isOk()) return;
			selectedShape = new Point(-1,-1, true, p.getColor());
			try {
				((Point)selectedShape).setX(Integer.parseInt(p.getTxtX1().getText()));
				((Point)selectedShape).setY(Integer.parseInt(p.getTxtY1().getText()));
				drawingPanel.getListOfShapes().set(drawingPanel.getSelectedIndex(), selectedShape);
				drawingPanel.setSelectedShape(selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingPanel, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}					
		} else if (selectedShape instanceof Line){
			DlgLine l = new DlgLine();
			l.getTxtX1().setText(String.valueOf(((Line) selectedShape).getStartPoint().getX()));
			l.getTxtY1().setText(String.valueOf(((Line) selectedShape).getStartPoint().getY()));
			l.getTxtX2().setText(String.valueOf(((Line) selectedShape).getEndPoint().getX()));
			l.getTxtY2().setText(String.valueOf(((Line) selectedShape).getEndPoint().getY()));
			l.setColor(selectedShape.getColor());
			l.setVisible(true);
			if(!l.isOk()) return;
			selectedShape = new Line(null, null, true, l.getColor());			
			try {
				((Line)selectedShape).setStartPoint(new Point(Integer.parseInt(l.getTxtX1().getText()), Integer.parseInt(l.getTxtY1().getText())));
				((Line)selectedShape).setEndPoint(new Point(Integer.parseInt(l.getTxtX2().getText()), Integer.parseInt(l.getTxtY2().getText())));
				drawingPanel.getListOfShapes().set(drawingPanel.getSelectedIndex(), selectedShape);
				drawingPanel.setSelectedShape(selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingPanel, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if(selectedShape instanceof Rectangle) {
			DlgRectangle r = new DlgRectangle();
			r.getTxtXCoordinate().setText(String.valueOf(((Rectangle) selectedShape).getUpperLeftPoint().getX()));
			r.getTxtYCoordinate().setText(String.valueOf(((Rectangle) selectedShape).getUpperLeftPoint().getY()));
			r.getTxtWidth().setText(String.valueOf(((Rectangle) selectedShape).getWidth()));
			r.getTxtHeigth().setText(String.valueOf(((Rectangle) selectedShape).getHeigth()));
			r.setColor(((Rectangle) selectedShape).getColor());
			r.setInnerColor(((Rectangle) selectedShape).getInnerColor());
			r.setVisible(true);
			if(!r.isOk()) return;
			selectedShape = new Rectangle(null, -1, -1, true, r.getColor(), r.getInnerColor());
			try {
				((Rectangle)selectedShape).setUpperLeftPoint(new Point(Integer.parseInt(r.getTxtXCoordinate().getText()), Integer.parseInt(r.getTxtYCoordinate().getText())));
				((Rectangle)selectedShape).setWidth(Integer.parseInt(r.getTxtWidth().getText()));
				((Rectangle)selectedShape).setHeigth(Integer.parseInt(r.getTxtHeigth().getText()));
				drawingPanel.getListOfShapes().set(drawingPanel.getSelectedIndex(), selectedShape);
				drawingPanel.setSelectedShape(selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingPanel, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if(selectedShape instanceof Donut) {
			DlgDonut d = new DlgDonut();
			d.getTxtX1().setText(String.valueOf(((Donut) selectedShape).getCenter().getX()));
			d.getTxtY1().setText(String.valueOf(((Donut) selectedShape).getCenter().getY()));
			d.getTxtInnerRadius().setText(String.valueOf(((Donut) selectedShape).getInnerRadius()));
			d.getTxtOuterRadius().setText(String.valueOf(((Donut) selectedShape).getRadius()));
			d.setColor(((Donut) selectedShape).getColor());
			d.setInnerColor(((Donut) selectedShape).getInnerColor());
			d.setVisible(true);
			if(!d.isOk()) return;
			selectedShape = new Donut(null, -1, -1, true, d.getColor(), d.getInnerColor());
			try {
				((Donut)selectedShape).setCenter(new Point(Integer.parseInt(d.getTxtX1().getText()), Integer.parseInt(d.getTxtY1().getText())));
				((Donut)selectedShape).setRadius(Integer.parseInt(d.getTxtOuterRadius().getText()));
				((Donut)selectedShape).setInnerRadius(Integer.parseInt(d.getTxtInnerRadius().getText()));
				drawingPanel.getListOfShapes().set(drawingPanel.getSelectedIndex(), selectedShape);
				drawingPanel.setSelectedShape(selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingPanel, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}				
		} else if(selectedShape instanceof Circle) {
			DlgCircle c = new DlgCircle();
			c.getTxtX1().setText(String.valueOf(((Circle) selectedShape).getCenter().getX()));
			c.getTxtY1().setText(String.valueOf(((Circle) selectedShape).getCenter().getY()));
			c.getTxtRadius().setText(String.valueOf(((Circle) selectedShape).getRadius()));
			c.setColor(((Circle) selectedShape).getColor());
			c.setInnerColor(((Circle) selectedShape).getInnerColor());
			c.setVisible(true);
			if(!c.isOk()) return;
			selectedShape = new Circle(null, -1, true, c.getColor(), c.getInnerColor());
			try {
				((Circle)selectedShape).setCenter(new Point(Integer.parseInt(c.getTxtX1().getText()), Integer.parseInt(c.getTxtY1().getText())));
				((Circle)selectedShape).setRadius(Integer.parseInt(c.getTxtRadius().getText()));
				drawingPanel.getListOfShapes().set(drawingPanel.getSelectedIndex(), selectedShape);
				drawingPanel.setSelectedShape(selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingPanel, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}					
		} 
		drawingPanel.repaint();
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}
	
	public JToggleButton getTglbtnFill() {
		return tglbtnFill;
	}

	public JColorChooser getColorPalete() {
		return colorPalete;
	}
}
