package com.compsci.main;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanel dialog = new JPanel();
	
	public JLabel planeLabel = new JLabel();
	
	public JLabel tDistanceLabel = new JLabel();
	
	public JLabel tTimeLabel = new JLabel();
	
	public JLabel tFuelLabel = new JLabel();
	
	public JLabel altitudeLabel = new JLabel();
	
	public ResultPanel() {
		
		dialog.setPreferredSize(new Dimension(320, 150));
		dialog.setLayout(null);
		
		planeLabel.setBounds(0, 5, 300, 20);
		dialog.add(planeLabel);
		
		altitudeLabel.setBounds(0, 30, 300, 20);
		dialog.add(altitudeLabel);
		
		tDistanceLabel.setBounds(0, 55, 300, 20);
		dialog.add(tDistanceLabel);
		
		tTimeLabel.setBounds(0, 80, 300, 20);
		dialog.add(tTimeLabel);
		
		tFuelLabel.setBounds(0, 105, 300, 20);
		dialog.add(tFuelLabel);
		
	}

}
