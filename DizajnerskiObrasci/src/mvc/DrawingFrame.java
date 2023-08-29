package mvc;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import java.io.File;
import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

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
	private JButton btnToFront = new JButton("To Front");
	private JButton btnBringToFront = new JButton("Bring To Front");
	private JButton btnToBack = new JButton("To Back");
	private JButton btnBringToBack = new JButton("Bring To Back");
	private JButton btnBorderColor = new JButton("");
	private JButton btnInnerColor = new JButton("");
	private JButton btnUndo = new JButton("Undo");
	private JButton btnRedo = new JButton("Redo");
	private JButton btnSaveDraw = new JButton("Save Draw");
	private JButton btnSaveLog = new JButton("Save Log");
	private JFileChooser fc = new JFileChooser();
	private JButton btnOpenDraw = new JButton("Open Draw");
	private JButton btnOpenLog = new JButton("Open Log");
	private JButton btnNextLogCommand = new JButton("Next line");
	private JTextArea txtAreaLogger = new JTextArea();
	private JScrollPane scrollPaneLogger = new JScrollPane(txtAreaLogger);
	
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
				
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingController.bringToBack();
			}
		});
		btnBringToBack.setEnabled(false);
				
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingController.toBack();
			}
		});
		btnToBack.setEnabled(false);
		
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingController.bringToFront();
			}
		});
		btnBringToFront.setEnabled(false);
		
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingController.toFront();
			}
		});
		btnToFront.setEnabled(false);
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingController.undo();
			}
		});
		btnUndo.setEnabled(false);
		
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingController.redo();
			}
		});
		btnRedo.setEnabled(false);
		
		btnSaveDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showSaveDialog(drawingView);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					drawingController.saveDraw(fc.getSelectedFile());
				}
				
			}
		});
		
		btnOpenDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(drawingView);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					drawingController.openDraw(fc.getSelectedFile());
				}
				
			}
		});
		
		btnOpenLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(drawingView);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					drawingController.openLog(fc.getSelectedFile());
				}
				
			}
		});
		
		btnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showSaveDialog(drawingView);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					drawingController.saveLog(fc.getSelectedFile());
				}
			}
		});
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 820);
		setResizable(false);
		setTitle("IT 34-2019 Sevic Djordje");
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tglButtons.add(tglbtnSelect);
		tglButtons.add(tglbtnPoint);
		tglButtons.add(tglbtnRectangle);
		tglButtons.add(tglbtnCircle);
		tglButtons.add(tglbtnDonut);
		tglButtons.add(tglbtnLine);
		tglButtons.add(tglbtnFill);
		tglButtons.add(tglbtnHexagon);
		
		
		btnBorderColor.setBackground(color);
		btnBorderColor.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	color = JColorChooser.showDialog(drawingView, "Choose color", color);
		    	btnBorderColor.setBackground(color);
		    }
		});
		
		
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
		
		
		txtAreaLogger.setEditable(false);		
		
		btnNextLogCommand.setEnabled(false);	
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSaveLog, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOpenDraw, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnOpenLog, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNextLogCommand, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnSaveDraw, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblInnerColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblBorderColor, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBorderColor, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnDonut, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addComponent(tglbtnCircle, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tglbtnSelect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
								.addComponent(tglbtnFill, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnBringToBack, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
								.addComponent(btnToBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBringToFront, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnHexagon, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnToFront, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(356))
				.addComponent(drawingView, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
				.addComponent(scrollPaneLogger, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblBorderColor, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addComponent(btnUndo)
							.addComponent(btnRedo))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnHexagon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnToFront))
							.addComponent(btnBorderColor, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblInnerColor, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addComponent(btnSaveDraw))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tglbtnSelect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBringToFront))
							.addComponent(btnInnerColor, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tglbtnFill)
						.addComponent(btnToBack)
						.addComponent(btnSaveLog))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnCircle)
						.addComponent(btnModify)
						.addComponent(btnBringToBack)
						.addComponent(btnOpenDraw))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnDonut)
						.addComponent(btnDelete)
						.addComponent(btnOpenLog)
						.addComponent(btnNextLogCommand))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(drawingView, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneLogger, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
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
				btnBringToBack.setEnabled(false);
				btnToBack.setEnabled(false);
				btnBringToFront.setEnabled(false);
				btnToFront.setEnabled(false);
			} else if(count == 1){
				btnDelete.setEnabled(true);
				btnModify.setEnabled(true);
				btnBringToBack.setEnabled(true);
				btnToBack.setEnabled(true);
				btnBringToFront.setEnabled(true);
				btnToFront.setEnabled(true);
			} else if(count > 1){
				btnModify.setEnabled(false);
				btnBringToBack.setEnabled(false);
				btnToBack.setEnabled(false);
				btnBringToFront.setEnabled(false);
				btnToFront.setEnabled(false);
			}
		} else if(evt.getPropertyName().equals("undoEnabled")) {
			int size = (int)evt.getNewValue();
			btnUndo.setEnabled(size == 0 ? false : true);
		} else if(evt.getPropertyName().equals("redoEnabled")) {
			int size = (int)evt.getNewValue();
			btnRedo.setEnabled(size == 0 ? false : true);
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
	
	public JButton getBtnNextLine() {
		return btnNextLogCommand;
	}
	
	public JButton getBtnUndo() {
		return btnUndo;
	}
	
	public JButton getBtnRedo() {
		return btnRedo;
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
	
	public JTextArea getTxtAreaLogger() {
		return txtAreaLogger;
	}
}