package com.compsci.main;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindEditor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanel dialog = new JPanel();
	
	public JLabel windInputTitle = new JLabel("Enter the wind speed and direction");
	
	public JTextField windSpeed = new JTextField();
	
	public JLabel magTitle = new JLabel("Magnitude:                             mph");
	
	public JTextField windDir = new JTextField();
	
	public JLabel dirTitle = new JLabel("Direction:                               °");
	
	public WindEditor() {
		
		dialog.setPreferredSize(new Dimension(300, 100)); //Set the size of the window, which is updated with the pack(); command
		dialog.setLayout(null);
		
		windInputTitle.setBounds(10, 5, 250, 15);
		dialog.add(windInputTitle);
		
		windSpeed.setBounds(120, 35, 100, 25);
		dialog.add(windSpeed);
		
		magTitle.setBounds(30, 35, 250, 25);
		dialog.add(magTitle);
		
		windDir.setBounds(120, 75, 100, 25);
		dialog.add(windDir);
		
		dirTitle.setBounds(35, 75, 250, 25);
		dialog.add(dirTitle);
	
	}
}
