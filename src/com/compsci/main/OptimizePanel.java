package com.compsci.main;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.compsci.aircrafts.*;
import com.compsci.math.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class OptimizePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanel dialog = new JPanel();
	
	//Causes a stack overflow error because of a circular reference
	//protected final GameWindow gw = new GameWindow();
	
	public DefaultListModel<String> aircrafts = new DefaultListModel<String>(); //Build a list using the default model
	public JList<String> aircraftList = new JList<String>(aircrafts); //Convert this to a JList
	
	public JLabel aircraftTypes = new JLabel("Choose an aircraft:");
	
	public JRadioButton rdbtnFuel = new JRadioButton("Fuel Consumption");
	
	public JRadioButton rdbtnTime = new JRadioButton("Time");

	public JLabel choiceLabel = new JLabel("Optimize for:");
	
	public ButtonGroup group = new ButtonGroup();
	
	public JButton btnOptimize = new JButton("Optimize");
	
	public JLabel result = new JLabel("Optimal cruise altitude is:");
	
	public JLabel result2 = new JLabel("");
	
	public JLabel latLongFormat1 = new JLabel("(                ° ,                ° )");
	
	public JLabel latLongFormat2 = new JLabel("(                ° ,                ° )");
	
	public JTextField initLatInput = new JTextField();
	
	public JTextField initLongInput = new JTextField();
	
	public JTextField finalLatInput = new JTextField();
	
	public JTextField finalLongInput = new JTextField();
	
	public JRadioButton rdbtnSave = new JRadioButton("Save to altitude");
	
	public JSlider optimize = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
	
	public JLabel optimizeTime = new JLabel("Time");
	
	public JLabel optimizeFuel = new JLabel("Fuel");
	
	public int finalAlt;
	
	public OptimizePanel() {
		
		dialog.setPreferredSize(new Dimension(450, 250));
		dialog.setLayout(null);
		
		dialog.add(aircraftList); //Add it to the screen
		aircraftList.setBounds(10, 35, 170, 55); //Set the dimensions
		aircraftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Only allow the user to select one plane
		aircrafts.addElement("Boeing 737-800");
		aircrafts.addElement("Cessna Skyhawk 172SP");
		aircrafts.addElement("Helicopter (N/A)");
		
		aircraftTypes.setBounds(10, 10, 300, 20);
		dialog.add(aircraftTypes);
		
		choiceLabel.setBounds(10, 100, 250, 20);
		dialog.add(choiceLabel);
		
		//rdbtnFuel.setBounds(10, 130, 200, 20);
		//dialog.add(rdbtnFuel);
		//rdbtnFuel.setEnabled(false);
		
		//rdbtnTime.setBounds(10, 160, 200, 20);
		//dialog.add(rdbtnTime);
		//rdbtnTime.setEnabled(false);
		
		group.add(rdbtnFuel);
		group.add(rdbtnTime);
		
		btnOptimize.setBounds(70, 210, 100, 25);
		dialog.add(btnOptimize);
		
		result.setBounds(200, 185, 200, 20);
		dialog.add(result);
		result.setVisible(false);
		
		result2.setBounds(200, 205, 200, 20);
		dialog.add(result2);
		result2.setVisible(false);
		
		initLatInput.setBounds(250, 65, 60, 19);
		dialog.add(initLatInput);
		
		initLongInput.setBounds(328, 65, 60, 19);
		dialog.add(initLongInput);
		
		latLongFormat1.setBounds(243, 65, 400, 19);
		dialog.add(latLongFormat1);

		finalLatInput.setBounds(250, 95, 60, 19);
		dialog.add(finalLatInput);
		
		finalLongInput.setBounds(328, 95, 60, 19);
		dialog.add(finalLongInput);
		
		latLongFormat2.setBounds(243, 95, 400, 19);
		dialog.add(latLongFormat2);
		
		rdbtnSave.setBounds(250, 135, 300, 20);
		dialog.add(rdbtnSave);
				
		optimize.setBounds(10, 150, 150, 30);
		dialog.add(optimize);
		
		optimizeTime.setBounds(0, 135, 80, 20);
		dialog.add(optimizeTime);
		
		optimizeFuel.setBounds(140, 135, 80, 20);
		dialog.add(optimizeFuel);
		
		btnOptimize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				
				double climb = 0, heightSpeed1 = 0, heightSpeed2 = 0, cruiseSpeed = 0, descent = 0, fuelBurnAscent = 0, fuelBurnDescent = 0, fuelBurnCruise = 0, maxAltitude = 0, minAltitude = 0; //Variables pertaining to the plane type
				double leastFuel = 1000000; //Just a really high number, so it's immediately overwritten
				double leastTime = 1000000; //Same thing
				double bestAltFuel = 0, bestAltTime = 0;
				double initLat = 0, initLong = 0, finalLat = 0, finalLong = 0, totalDistance = 0;
				
				try {
					
					initLat = Double.parseDouble(initLatInput.getText());
					initLong = Double.parseDouble(initLongInput.getText());
					finalLat = Double.parseDouble(finalLatInput.getText());
					finalLong = Double.parseDouble(finalLongInput.getText());

					totalDistance = Distance.totalDistance(initLat, initLong, finalLat, finalLong);
					
					
				} catch(Exception ex) {
					//Do nothing (your error handling is in another castle...)
				}
				
				double percentFuel = optimize.getValue();
				double percentTime = 50 - percentFuel;
				
				for (int altitude = 10; altitude <= 35000; altitude += 10) {
					
					switch (aircraftList.getSelectedIndex()) {
						case 0:
							Boeing737800 boeing = new Boeing737800();
							climb = boeing.climb;
							heightSpeed1 = boeing.heightSpeed1;
							heightSpeed2 = boeing.heightSpeed2;
							cruiseSpeed = boeing.cruiseSpeed(altitude);
							descent = boeing.descent;
							fuelBurnAscent = boeing.fuelBurnAscent;
							fuelBurnDescent = boeing.fuelBurnDescent;
							fuelBurnCruise = boeing.cruiseFuel(altitude);
							maxAltitude = boeing.maxAltitude;
							minAltitude = boeing.minAltitude;
		
							break;
							
						case 1:
							CessnaSkyhawk172SP cessna = new CessnaSkyhawk172SP();
							climb =  cessna.climb;
							heightSpeed1 = cessna.heightSpeed1;
							heightSpeed2 = cessna.heightSpeed2;
							cruiseSpeed = cessna.cruiseSpeed;
							descent = cessna.descent;
							fuelBurnAscent = cessna.fuelBurnAscent;
							fuelBurnDescent = cessna.fuelBurnDescent;
							fuelBurnCruise = cessna.cruiseFuel(altitude);
							maxAltitude = cessna.maxAltitude;
							minAltitude = cessna.minAltitude;
							
							break;
							
						case 2:
							
							break;	
					}
					
					try {
						
						//Make sure the plane is actually going somewhere
						if (totalDistance < 5280) {
							break;
						}
						
						double ascentTime = 0, descentTime = 0;
						
						if (altitude < 10000) {
							descentTime = Time.timeDescent3(descent, altitude);
							ascentTime = Time.timeAscent3(climb, altitude);
						} else {
							descentTime = Time.timeDescent1(descent, altitude) + Time.timeDescent2(descent);
							ascentTime = Time.timeAscent1(climb) + Time.timeAscent2(climb, altitude);
						}
						
						double cruiseDistance = Distance.cruiseDistance(totalDistance, heightSpeed1, heightSpeed2, descentTime, climb, altitude);
						double cruiseTime = Time.timeCruise(cruiseDistance, cruiseSpeed);
						double totalTime = cruiseTime + ascentTime + descentTime;

						
						double ascentFuel = FuelBurn.ascentFuel(ascentTime, fuelBurnAscent);
						double descentFuel = FuelBurn.descentFuel(descentTime, fuelBurnDescent);
						double cruiseFuel = FuelBurn.cruiseFuel(cruiseTime, fuelBurnCruise);
						
						double fuelBurned = ascentFuel + descentFuel + cruiseFuel;
												
						if (fuelBurned < leastFuel && altitude >= minAltitude && altitude <= maxAltitude) {
							leastFuel = fuelBurned;
							bestAltFuel = altitude;
						}
						if (totalTime < leastTime && altitude >= minAltitude && altitude <= maxAltitude) {
							leastTime = totalTime;
							bestAltTime = altitude;
						}
						
					} catch(Exception ex) {
						break;
					}
					
				}
				
				finalAlt = (int)((percentTime * bestAltTime) + (percentFuel * bestAltFuel)) / 50;
				result2.setText(finalAlt+" ft");
				result.setVisible(true);
				result2.setVisible(true);
				
								
				if (rdbtnSave.isSelected()) {
					if (rdbtnFuel.isSelected());
						//gw.altitudeInput.setText(""+bestAltFuel);
					//else
						//gw.altitudeInput.setText(""+bestAltTime);

				}
			}
		});
		
	}
}
