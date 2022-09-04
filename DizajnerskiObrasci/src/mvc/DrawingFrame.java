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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;

public class DrawingFrame extends JFrame implements PropertyChangeListener {
	
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
	private JButton btnModify = new JButton("Modify");	
	private JButton btnDelete = new JButton("Delete");
	private JPanel contentPane;
	private Color color = Color.BLACK;
	private Color innerColor = Color.WHITE;
	
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
		btnDelete.setEnabled(false);
		
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingController.modify();
			}
		});	
		btnModify.setEnabled(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		setResizable(false);
		setTitle("IT 34-2019 Sevic Djordje");
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//colorPalete.setPreviewPanel(new JPanel());
		//colorPalete.setChooserPanels(new AbstractColorChooserPanel[] {colorPalete.getChooserPanels()[0]});
		
		tglButtons.add(tglbtnSelect);
		tglButtons.add(tglbtnPoint);
		tglButtons.add(tglbtnRectangle);
		tglButtons.add(tglbtnCircle);
		tglButtons.add(tglbtnDonut);
		tglButtons.add(tglbtnLine);
		tglButtons.add(tglbtnFill);
		tglButtons.add(tglbtnHexagon);
		
		JButton btnBorderColor = new JButton("");
		btnBorderColor.setBackground(color);
		btnBorderColor.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	color = JColorChooser.showDialog(drawingView, "Choose color", color);
		    	btnBorderColor.setBackground(color);
		    }
		});
		
		JButton btnInnerColor = new JButton("");
		btnInnerColor.setBackground(innerColor);
		btnInnerColor.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	innerColor = JColorChooser.showDialog(drawingView, "Choose color", innerColor);
		    	btnInnerColor.setBackground(innerColor);
		    }
		});
		
		JLabel lblBorderColor = new JLabel("Active Color:");
		lblBorderColor.setForeground(Color.WHITE);
		
		JLabel lblInnerColor = new JLabel("Active Inner Color:");
		lblInnerColor.setForeground(Color.WHITE);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(236)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblBorderColor, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnDonut, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.addComponent(tglbtnCircle, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
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
						.addComponent(lblBorderColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(tglbtnHexagon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBorderColor, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(tglbtnSelect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnInnerColor, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
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
						.addComponent(btnDelete))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(drawingView, GroupLayout.PREFERRED_SIZE, 596, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("numberOfSelectedShapes")) {
			int count = (int)evt.getNewValue();
			if(count == 0) {
				btnModify.setEnabled(false);
				btnDelete.setEnabled(false);
			} else if(count == 1){
				btnDelete.setEnabled(true);
				btnModify.setEnabled(true);
			} else if(count > 1){
				btnModify.setEnabled(false);
			}
		}
		
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
}
