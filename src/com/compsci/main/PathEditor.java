package com.compsci.main;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PathEditor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JPanel dialog = new JPanel(); //Make the JPanel importable into the main class
	
	public JLabel initAltTitle = new JLabel("Initial Altitude:                            ft");
	
	public JTextField initAltInput = new JTextField();
		
	public JRadioButton rdbtnCoords = new JRadioButton("Change path at a coordinate");
	
	public JRadioButton rdbtnDistance = new JRadioButton("Change altitude after a certain distance");
		
	public ButtonGroup group = new ButtonGroup();
	
	public JLabel latLongFormat = new JLabel("(                ° ,                ° )");
	
	public JTextField pathLat = new JTextField();
	
	public JTextField pathLong = new JTextField();
	
	public JTextField distanceInput = new JTextField();
	
	public JLabel distanceTitle = new JLabel("Distance: ");
	
	public JComboBox<String> units = new JComboBox<String>();
		
	public JTextField finalAltInput = new JTextField();
	
	public JLabel finalAltTitle = new JLabel("New Altitude:                            ft");
	
	public PathEditor() {
		
		dialog.setPreferredSize(new Dimension(350, 250)); //Set the size of the pop-up
		dialog.setLayout(null);
		
		initAltTitle.setBounds(30, 10, 400, 20);
		dialog.add(initAltTitle);
		
		initAltInput.setBounds(140, 10, 100, 22);
		dialog.add(initAltInput);
		
		rdbtnCoords.setBounds(0, 55, 250, 30);
		dialog.add(rdbtnCoords);
		group.add(rdbtnCoords);
		
		rdbtnDistance.setBounds(0, 125, 300, 30);
		dialog.add(rdbtnDistance);
		group.add(rdbtnDistance);
				
		pathLat.setBounds(35, 95, 60, 19);
		dialog.add(pathLat);
		pathLat.setEnabled(false);
		
		pathLong.setBounds(115, 95, 60, 19);
		dialog.add(pathLong);
		pathLong.setEnabled(false);
		
		latLongFormat.setBounds(25, 95, 400, 19);
		dialog.add(latLongFormat);
		latLongFormat.setEnabled(false);
		
		distanceInput.setBounds(100, 160, 80, 20);
		dialog.add(distanceInput);
		distanceInput.setEnabled(false);
		
		distanceTitle.setBounds(25, 160, 80, 20);
		dialog.add(distanceTitle);
		distanceTitle.setEnabled(false);
		
		units.setBounds(200, 160, 110, 20);
		units.addItem("Miles");
		units.addItem("Feet");
		units.addItem("Kilometers");
		dialog.add(units);
		units.setEnabled(false);
		units.setSelectedIndex(-1);
		
		finalAltTitle.setBounds(30, 210, 400, 20);
		dialog.add(finalAltTitle);
		
		finalAltInput.setBounds(130, 210, 100, 20);
		dialog.add(finalAltInput);
		
		rdbtnCoords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				//Enable the inputs for coords
				pathLong.setEnabled(true);
				pathLat.setEnabled(true);
				latLongFormat.setEnabled(true);
				
				//Disable the other inputs
				distanceInput.setEnabled(false);
				distanceTitle.setEnabled(false);
				units.setEnabled(false);
				
			}
		});
		
		rdbtnDistance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Enable the inputs for distance
				distanceInput.setEnabled(true);
				distanceTitle.setEnabled(true);
				units.setEnabled(true);
				
				//Disable the other inputs
				pathLong.setEnabled(false);
				pathLat.setEnabled(false);
				latLongFormat.setEnabled(false);
				

			}
		});
		
	} 
}
