package mvc;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingFrame extends JFrame {
	
	private DrawingView drawingView = new DrawingView();
	
	private DrawingController drawingController;
	
	private ButtonGroup tglButtons = new ButtonGroup();
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnLine = new JToggleButton("Line");		
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JToggleButton tglbtnFill = new JToggleButton("Fill");
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	private JColorChooser colorPalete = new JColorChooser(Color.BLACK);
	private JButton btnModify = new JButton("Modify");	
	private JButton btnDelete = new JButton("Delete");
	private JPanel contentPane;
	
	public DrawingFrame() {
		
		drawingView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawingController.mouseClicked(e);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingController.delete();
			}
		});	
		
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingController.modify();
			}
		});	
		
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
		tglButtons.add(tglbtnHexagon);
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(colorPalete, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnDonut, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(tglbtnCircle, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(tglbtnSelect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addComponent(tglbtnFill, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
						.addComponent(tglbtnHexagon, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addGap(518))
				.addComponent(drawingView, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnPoint)
								.addComponent(tglbtnHexagon))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnLine)
								.addComponent(tglbtnSelect))
							.addGap(4)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tglbtnFill))
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
					.addComponent(drawingView, GroupLayout.PREFERRED_SIZE, 596, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}

	public DrawingView getDrawingView() {
		return drawingView;
	}

	public void setDrawingController(DrawingController drawingController) {
		this.drawingController = drawingController;
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
	
	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public JColorChooser getColorPalete() {
		return colorPalete;
	}
}
