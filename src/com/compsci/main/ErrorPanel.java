package com.compsci.main;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanel dialog = new JPanel();
	
	public JLabel iLatError = new JLabel("Invalid initial latitude!");
	
	public JLabel iLongError = new JLabel("Invalid initial longitude!");
	
	public JLabel fLatError = new JLabel("Invalid final latitude!");
	
	public JLabel fLongError = new JLabel("Invalud final longitude!");
	
	public JLabel planeError = new JLabel("Invalid plane selection!");
	
	public JLabel altError = new JLabel("Invalid altitude!");
	
	public JLabel windError = new JLabel("Invalid wind entry!");
		
	public ErrorPanel() {
		
		dialog.setPreferredSize(new Dimension(200, 200));
		dialog.setLayout(null);
		
		iLatError.setBounds(0, 0, 200, 20);
		iLatError.setVisible(false);
		dialog.add(iLatError);
		
		iLongError.setBounds(0, 30, 200, 20);
		iLongError.setVisible(false);
		dialog.add(iLongError);
		
		fLatError.setBounds(0, 60, 200, 20);
		fLatError.setVisible(false);
		dialog.add(fLatError);
		
		fLongError.setBounds(0, 90, 200, 20);
		fLongError.setVisible(false);
		dialog.add(fLongError);
		
		planeError.setBounds(0, 120, 200, 20);
		planeError.setVisible(false);
		dialog.add(planeError);
		
		altError.setBounds(0, 150, 200, 20);
		altError.setVisible(false);
		dialog.add(altError);
		
		windError.setBounds(0, 180, 200, 20);
		windError.setVisible(false);
		dialog.add(windError);
		
	}
}
