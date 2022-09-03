package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtWidth;
	private JTextField txtHeigth;	
	private boolean isOk = false;
	private Color color;
	private Color innerColor;
	private JButton btnColor;
	private JButton btnInnerColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setModal(true);
		setTitle("Add rectangle");
		setBounds(100, 100, 350, 316);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtYCoordinate = new JTextField();
		txtYCoordinate.setColumns(10);
		
		txtXCoordinate = new JTextField();
		txtXCoordinate.setColumns(10);
				
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		
		txtHeigth = new JTextField();
		txtHeigth.setColumns(10);
		
		JLabel lblXCoordinate = new JLabel("X Coordinate");
		
		JLabel lblYCoordinate = new JLabel("Y Coordinate:");
		
		JLabel lblWidth = new JLabel("Width:");
		
		JLabel lblHeigth = new JLabel("Heigth:");
		
		btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(contentPanel, "Choose color",color);
			}
		});
		
		btnInnerColor = new JButton("Inner color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(contentPanel, "Choose color",innerColor);
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnInnerColor))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnColor))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGap(52)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblXCoordinate)
									.addComponent(lblYCoordinate)
									.addComponent(lblWidth)
									.addComponent(lblHeigth))
								.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtHeigth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(70))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXCoordinate))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYCoordinate))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtHeigth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeigth))
					.addGap(18)
					.addComponent(btnColor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInnerColor)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			JButton okButton = new JButton("OK");
			JButton cancelButton = new JButton("Cancel");
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isOk = true;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(103)
						.addComponent(okButton)
						.addGap(18)
						.addComponent(cancelButton)
						.addGap(101))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public JButton getBtnColor() {
		return btnColor;
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

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public JTextField getTxtXCoordinate() {
		return txtXCoordinate;
	}

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeigth() {
		return txtHeigth;
	}

	public void setTxtHeigth(JTextField txtHeigth) {
		this.txtHeigth = txtHeigth;
	}
}
