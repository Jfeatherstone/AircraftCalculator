package com.compsci.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import com.compsci.main.WindEditor;
import com.compsci.math.*;
import com.compsci.main.PathEditor;
import com.compsci.main.ErrorPanel;
import com.compsci.aircrafts.*;
import com.compsci.main.ResultPanel;

public class GameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public JTextField initLatInput, initLongInput, finalLatInput, finalLongInput, altitudeInput;
	private static JMenuBar menu = new JMenuBar();
	public static GameWindow frame = new GameWindow();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//Setup the menu bar					
					//GameWindow frame = new GameWindow();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("Aircraft Calculator");					
					frame.setJMenuBar(menu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
					
		}
	

	/**
	 * Create the frame.
	 **/
	
	public GameWindow() {
		
		ImageIcon img = new ImageIcon("/AircraftCalculator/icon/airplane_icon.png");
		setIconImage(img.getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(550, 250));
		contentPane.setRequestFocusEnabled(true);
		pack();
		
		JMenu menuFile = new JMenu("File");
		menu.add(menuFile);		
			JMenuItem mnitmStats = new JMenuItem("Info");
			menuFile.add(mnitmStats);
			
			JMenuItem mnitmQuit = new JMenuItem("Quit");
			menuFile.add(mnitmQuit);
			
		JMenu menuEdit = new JMenu("Edit");
		menu.add(menuEdit);
			JMenuItem mnitmReset = new JMenuItem("Reset");
			menuEdit.add(mnitmReset);
			
			JMenuItem mnitmOptimize = new JMenuItem("Optimize...");
			menuEdit.add(mnitmOptimize);
			
		//Create the input for the initial latitude
		initLatInput = new JTextField();
		initLatInput.setBounds(20, 40, 60, 19);
		contentPane.add(initLatInput);
		initLatInput.setColumns(10);
		
		//Create the input for the initial longitude
		initLongInput = new JTextField();
		initLongInput.setBounds(100, 40, 60, 19);
		contentPane.add(initLongInput);
		initLongInput.setColumns(10);
		
		//Create the labels to format the initial coordinates
		JLabel initFormat = new JLabel("(                ° ,                °)");
		initFormat.setBounds(12, 43, 300, 15);
		contentPane.add(initFormat);
		
		JLabel initTitle = new JLabel("Initial Coordinates:");
		initTitle.setBounds(20, 20, 150, 15);
		contentPane.add(initTitle);
		
		//Create the input for the final latitude
		finalLatInput = new JTextField();
		finalLatInput.setBounds(20, 90, 60, 19);
		contentPane.add(finalLatInput);
		finalLatInput.setColumns(10);
		
		//Create the input for the final longitude
		finalLongInput = new JTextField();
		finalLongInput.setBounds(100, 90, 60, 19);
		contentPane.add(finalLongInput);
		finalLongInput.setColumns(10);
		
		//Create the labels to format the final coordinates
		JLabel finalFormat = new JLabel("(                ° ,                °)");
		finalFormat.setBounds(12, 93, 300, 15);
		contentPane.add(finalFormat);
		
		JLabel finalTitle = new JLabel("Final Coordinates:");
		finalTitle.setBounds(20, 70, 150, 15);
		contentPane.add(finalTitle);
		
		//Create the jtextfield for the aircraft altitude
		altitudeInput = new JTextField();
		altitudeInput.setBounds(400, 70, 60, 19);
		contentPane.add(altitudeInput);
		altitudeInput.setColumns(10);
		altitudeInput.setVisible(false);
		
		//Create the labels to format the altitude
		JLabel altitudeTitle = new JLabel("Intended Altitude:");
		altitudeTitle.setBounds(380, 50, 150, 15);
		contentPane.add(altitudeTitle);
		altitudeTitle.setVisible(false);
			
		//Create the labels for the radio buttons
		JLabel radioTitle1 = new JLabel("Is there an intermediate");
		radioTitle1.setBounds(190, 20, 200, 15);
		contentPane.add(radioTitle1);
		
		JLabel radioTitle2 = new JLabel("change in altitude?");
		radioTitle2.setBounds(190, 35, 200, 15);
		contentPane.add(radioTitle2);
		
		//Radio button for the yes option in terms of an altitude change
		JRadioButton rdbtnPathYes = new JRadioButton("Yes");
		rdbtnPathYes.setBounds(220, 55, 50, 23);
		contentPane.add(rdbtnPathYes);
		
		//Radio button for the no option in terms of an altitude change
		JRadioButton rdbtnPathNo = new JRadioButton("No");
		rdbtnPathNo.setBounds(220, 85, 50, 23);
		contentPane.add(rdbtnPathNo);
		
		//Create a group of buttons in which only one can be activated at once
		//This makes it so you can only choose one of the two radio buttons in the group
		ButtonGroup pathGroup = new ButtonGroup();
		pathGroup.add(rdbtnPathNo);
		pathGroup.add(rdbtnPathYes);
		
		JButton btnEditPath = new JButton("Edit Path");
		btnEditPath.setBounds(380, 70, 100, 25);
		contentPane.add(btnEditPath);
		btnEditPath.setVisible(false);
		
		//The list of aircrafts to choose from
		DefaultListModel<String> aircrafts = new DefaultListModel<String>(); //Build a list using the default model
		JList<String> aircraftList = new JList<String>(aircrafts); //Convert this to a JList
		contentPane.add(aircraftList); //Add it to the screen
		aircraftList.setBounds(5, 145, 170, 55); //Set the dimensions
		aircraftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Only allow the user to select one plane
		
		//Create the calculate button
		//This is where most of the code will end up going
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(410, 150, 110, 25);
		contentPane.add(btnCalculate);
		
		//Create the label for the wind buttons
		JLabel windTitle = new JLabel("Is there wind?");
		windTitle.setBounds(195, 135, 100, 15);
		contentPane.add(windTitle);
		
		//Create the yes button for the wind
		JRadioButton rdbtnWindYes = new JRadioButton("Yes");
		rdbtnWindYes.setBounds(220, 160, 50, 15);
		contentPane.add(rdbtnWindYes);
		
		//Create the no button for the wind
		JRadioButton rdbtnWindNo = new JRadioButton("No");
		rdbtnWindNo.setBounds(220, 190, 50, 15);
		contentPane.add(rdbtnWindNo);
		
		//Create the button group for the wind
		ButtonGroup windGroup = new ButtonGroup();
		windGroup.add(rdbtnWindNo);
		windGroup.add(rdbtnWindYes);
		
		//Create the button that will open the wind edit dialog
		JButton btnEditWind = new JButton("Edit Wind");
		btnEditWind.setBounds(285, 170, 100, 25);
		contentPane.add(btnEditWind);
		btnEditWind.setVisible(false);
		
		
		//TODO: Update the helicopter name
		aircrafts.addElement("Boeing 737-800");
		aircrafts.addElement("Cessna Skyhawk 172SP");
		aircrafts.addElement("Helicopter (N/A)");

		//This is one of the radio buttons that changes whether the edit path dialog is visible or not
		rdbtnPathYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Remove the prompts for a single altitude flight
				altitudeInput.setVisible(false);
				altitudeTitle.setVisible(false);
				
				//Enable the prompts for an intermediate change in altitude
				btnEditPath.setVisible(true);

			}
		});
		
		//This is one of the radio buttons that changes whether the edit path dialog is visible or not
		rdbtnPathNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Enable the prompts for a single altitude flight
				altitudeInput.setVisible(true);
				altitudeTitle.setVisible(true);
				
				//Remove the prompts for an intermediate change in altitude
				btnEditPath.setVisible(false);
				
			}
		});
		
		//One of the radio button mouse listeners that displays the wind editor button
		rdbtnWindYes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				btnEditWind.setVisible(true);

			}
		});
		
		//The wind radio button that makes the dialog invisible
		rdbtnWindNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				btnEditWind.setVisible(false);
			}
		});

		
		//Import the pop-up window that will be used to edit the path of a non uniform flight from another class
		PathEditor pe = new PathEditor(); //Call the other class which has the path editor dialog
		JPanel pathEditor = new JPanel(); //Create a new JPanel
		pathEditor.add(pe.dialog); //Add the JPanel from the other class to this one, pretty much duplicates it into this class
		
		btnEditPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				pack();
				JOptionPane.showConfirmDialog(null, pathEditor, "Path Editor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION); //Display a pop-up using a panel from the PathEditor class
				//This dialog calls the JPanel created in the other class
				//The OK_CANCEL_OPTION changes what buttons appear at the button of the dialog
			
			}
		});
		
		//Do the same thing as the path editor but for the wind editor from its class
		WindEditor we = new WindEditor();
		JPanel windEditor = new JPanel();
		windEditor.add(we.dialog);
		
		btnEditWind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				pack();
				JOptionPane.showConfirmDialog(null, windEditor, "Wind Editor", JOptionPane.OK_CANCEL_OPTION); //Display a pop-up using a panel from the PathEditor class
				//Again, pretty much the same
				
			}
		});
		
		//Most of the code will end up is this mouse clicked
		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Import the results panel from another class
				ResultPanel rp = new ResultPanel();
				JPanel results = new JPanel();
				results.add(rp.dialog);
				
				//WindEditor we = new WindEditor();
				
				//Create a decimal format to make things look nice
				DecimalFormat df = new DecimalFormat(".#");
				
				double climb = 0, heightSpeed1 = 0, heightSpeed2 = 0, cruiseSpeed = 0, descent = 0, fuelBurnAscent = 0, fuelBurnDescent = 0, fuelBurnCruise = 0, maxAltitude = 0, cruiseSpeed1 = 0, cruiseSpeed2 = 0, fuelBurnCruise1 = 0, fuelBurnCruise2 = 0, minAltitude = 0; //Variables pertaining to the plane type
				double initLat = 0, initLong = 0, finalLat = 0, finalLong = 0; //Variables from the user
				double windDir = 0, windMag = 0;
				int altitude = 0;
				boolean altitudeError = false, initLatError = false, initLongError = false, finalLatError = false, finalLongError = false, aircraftError = false, windError = false;
				String aircraftType = null;
				
				//Fetch the selected aircraft
				int aircraft = aircraftList.getSelectedIndex();
				if (aircraft == -1) {
					aircraftError = true;
				}
				
				int altitude1 = 0, altitude2 = 0;
				double changeLat = 0, changeLong = 0, changeDistance = 0, changeTime = 0;
				
				//Only fetch this altitude if there is only one altitude the entire flight
				if (rdbtnPathNo.isSelected()) {
					//Fetch the cruise altitude
					try {
						altitude = Integer.parseInt(altitudeInput.getText());
						altitudeError = false;
					} catch (Exception ex) {
						altitudeError = true;
					}
				} else {
					if (rdbtnPathYes.isSelected()) {
												
						try {
							
							altitude1 = Integer.parseInt(pe.initAltInput.getText());
							altitude2 = Integer.parseInt(pe.finalAltInput.getText());
							
							if (pe.rdbtnCoords.isSelected()) {
								
								changeLat = Double.parseDouble(pe.pathLat.getText());
								changeLong = Double.parseDouble(pe.pathLong.getText());
								
							}
							if (pe.rdbtnDistance.isSelected()) {
								
								changeDistance = Double.parseDouble(pe.distanceInput.getText());
								
								if (pe.units.getSelectedIndex() == 0)
									//Convert distance to feet from miles
									changeDistance *= 5280;
								if (pe.units.getSelectedIndex() == 1);
									//The distance is already in correct units
								if (pe.units.getSelectedIndex() == 2)
									//Convert distance to feet from kilometers
									changeDistance *= 0.621 * 5280;
							}
							
							if (!pe.rdbtnDistance.isSelected() && !pe.rdbtnCoords.isSelected())
								altitudeError = true;
							else 
								altitudeError = false;
							
						} catch (Exception ex) {
							altitudeError = true;
						}
						
					} else {
						altitudeError = true;
					}
				}
				
				if (rdbtnWindNo.isSelected()) {
					//Do nothing
					
				} else {
					if (rdbtnWindYes.isSelected()) {
						
						try {
							
							//Parse the values from textfields
							windDir = Double.parseDouble(we.windDir.getText());
							windMag = Double.parseDouble(we.windSpeed.getText());
							windMag *= 5280 / 3600; //Convert to feet per second
							windError = false;
							
						} catch(Exception ex) {
							windError = true;
						}
						
					} else {
						//If no button is selected, give an error
						windError = true;
					}
				}

				//Fetch the initial latitude
				try {
					initLat = Double.parseDouble(initLatInput.getText());
					initLatError = false;
				} catch (Exception ex) {
					initLatError = true;
				}
				
				//Fetch the initial longitude
				try {
					initLong = Double.parseDouble(initLongInput.getText());
					initLongError = false;
				} catch (Exception ex) {
					initLongError = true;
				}
				
				//Fetch the final latitude
				try {
					finalLat = Double.parseDouble(finalLatInput.getText());
					finalLatError = false;
				} catch (Exception ex) {
					finalLatError = true;
				}
				
				//Fetch the final longitude
				try {
					finalLong = Double.parseDouble(finalLongInput.getText());
					finalLongError = false;
				} catch (Exception ex) {
					finalLongError = true;
				}
				
				//Make sure there are no fetching errors before running calculations
				while (!initLatError && !initLongError && !finalLatError && !finalLongError && !altitudeError && !aircraftError && !windError) { 
					
					//The first thing is to fetch the corresponding variables for the selected plane type
					switch (aircraft) {
					case 0:
						Boeing737800 boeing = new Boeing737800();
						climb = boeing.climb;
						heightSpeed1 = boeing.heightSpeed1;
						heightSpeed2 = boeing.heightSpeed2;
						cruiseSpeed = boeing.cruiseSpeed(altitude);
						cruiseSpeed1 = boeing.cruiseSpeed(altitude1);
						cruiseSpeed2 = boeing.cruiseSpeed(altitude2);
						descent = boeing.descent;
						fuelBurnAscent = boeing.fuelBurnAscent;
						fuelBurnDescent = boeing.fuelBurnDescent;
						fuelBurnCruise = boeing.cruiseFuel(altitude);
						fuelBurnCruise1 = boeing.cruiseFuel(altitude1);
						fuelBurnCruise2 = boeing.cruiseFuel(altitude2);
						maxAltitude = boeing.maxAltitude;
						minAltitude = boeing.minAltitude;
						aircraftType = "Boeing 737";
						break;
					case 1:
						CessnaSkyhawk172SP cessna = new CessnaSkyhawk172SP();
						climb =  cessna.climb;
						heightSpeed1 = cessna.heightSpeed1;
						heightSpeed2 = cessna.heightSpeed2;
						cruiseSpeed = cessna.cruiseSpeed;
						cruiseSpeed1 = cessna.cruiseSpeed;
						cruiseSpeed2 = cessna.cruiseSpeed;
						descent = cessna.descent;
						fuelBurnAscent = cessna.fuelBurnAscent;
						fuelBurnDescent = cessna.fuelBurnDescent;
						fuelBurnCruise = cessna.cruiseFuel(altitude);
						fuelBurnCruise1 = cessna.cruiseFuel(altitude1);
						fuelBurnCruise2 = cessna.cruiseFuel(altitude2);
						maxAltitude = cessna.maxAltitude;
						minAltitude = cessna.minAltitude;
						aircraftType = "Cessna Skyhawk";
						break;
					case 2:
						
						break;
					}
					
					//If the flight has uniform altitude
					if (rdbtnPathNo.isSelected()) {
						
						//Account for wind
						if (rdbtnWindYes.isSelected()) {
							cruiseSpeed = Wind.windDotSpeed(initLat, initLong, finalLat, finalLong, windDir, windMag, cruiseSpeed);
						}
						
						//Make sure the altitude is less than the max altitude
						if (altitude > maxAltitude ) {
							JOptionPane.showMessageDialog(contentPane, "That airplane cannot fly at that \n high of an altitude!", "Error", JOptionPane.OK_OPTION);
							break;
						}
						
						if (altitude < minAltitude ) {
							JOptionPane.showMessageDialog(contentPane, "That airplane cannot fly at that \n low of an altitude!", "Error", JOptionPane.OK_OPTION);
							break;
						}
						
						//Math!
						double totalDistance = Distance.totalDistance(initLat, initLong, finalLat, finalLong);
						//These aren't actually needed as they are innately referenced in cruise distance
						//double descentDistance = Distance.distanceDescent(heightSpeed1, heightSpeed2, descent, altitude);
						//double ascentDistance = Distance.distanceAscent(heightSpeed1, heightSpeed2, climb, altitude);
						
						//Make sure the plane is actually going somewhere
						if (totalDistance < 5280) {
							JOptionPane.showMessageDialog(contentPane, "You have to actually go somewhere...", "Error", JOptionPane.OK_OPTION);
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
												
						rp.planeLabel.setText("<html> Aircraft: <font color='red'>"+aircraftType);
						rp.altitudeLabel.setText("<html> Cruise Altitude: <font color='red'>"+altitude+" ft");
						rp.tDistanceLabel.setText("<html> Total Distance: <font color='red'>"+df.format(totalDistance/5280)+" miles");
						rp.tTimeLabel.setText("<html> Total Time: <font color='red'>"+Extra.timeFormat(totalTime));
						rp.tFuelLabel.setText("<html> Total Fuel Burned: <font color='red'>"+df.format(fuelBurned/6.7)+" gallons");	
						
						JOptionPane.showMessageDialog(contentPane, results, "Results", JOptionPane.PLAIN_MESSAGE);
						break;
						
					} else {
						//With a more complicated flight path:
						
						//Account for wind
						//Has to be different if there is an intermediate coordinate, as the bearing will change
						if (rdbtnWindYes.isSelected()) {
							if (pe.rdbtnCoords.isSelected()) {
								cruiseSpeed1 = Wind.windDotSpeed(initLat, initLong, changeLat, changeLong, windDir, windMag, cruiseSpeed1);
								cruiseSpeed2 = Wind.windDotSpeed(changeLat, changeLong, finalLat, finalLong, windDir, windMag, cruiseSpeed2);
							}
							if (pe.rdbtnDistance.isSelected()) {
								cruiseSpeed1 = Wind.windDotSpeed(initLat, initLong, finalLat, finalLong, windDir, windMag, cruiseSpeed1);
								cruiseSpeed2 = Wind.windDotSpeed(initLat, initLong, finalLat, finalLong, windDir, windMag, cruiseSpeed2);

							}
						}

						
						//Make sure both altitudes are below the max
						if (altitude1 > maxAltitude || altitude2 > maxAltitude) {
							JOptionPane.showMessageDialog(contentPane, "That airplane cannot fly at that \n high of an altitude!", "Error", JOptionPane.OK_OPTION);
							break;
						}
						
						//More math!
						
						double totalDistance1 = 0, totalDistance2 = 0, totalDistance = 0;
						
						double ascentTime1 = 0, ascentTime2 = 0, descentTime1 = 0, descentTime2 = 0, ascentTime = 0, descentTime = 0, cruiseTime1 = 0;
						double ascentTimeChange = 0, descentTimeChange = 0;
						
						if (altitude1 < 10000) {
							ascentTime = Time.timeAscent3(climb, altitude1);
						} else {
							ascentTime1 = Time.timeAscent1(climb);
							ascentTime2 = Time.timeAscent2(climb, altitude1);
							ascentTime = ascentTime1 + ascentTime2;
						}
						
						if (altitude2 < 10000) {
							descentTime = Time.timeDescent3(descent, altitude2);
						} else {
							descentTime1 = Time.timeDescent1(descent, altitude2);
							descentTime2 = Time.timeDescent2(descent);
							descentTime = descentTime1 + descentTime2;
						}

						//If the coords rdbtn is selected
						if (pe.rdbtnCoords.isSelected()) {
							//First leg of the trip
							totalDistance1 = Distance.totalDistance(initLat, initLong, changeLat, changeLong);
							//Second leg of the trip
							totalDistance2 = Distance.totalDistance(changeLat, changeLong, finalLat, finalLong);
							//Add them together
							totalDistance = totalDistance1 + totalDistance2;
						}
						
						//If the distance rdbtn is selected
						if (pe.rdbtnDistance.isSelected()) {
							totalDistance1 = changeDistance;
							totalDistance = Distance.totalDistance(initLat, initLong, finalLat, finalLong);
							totalDistance2 = totalDistance - changeDistance;
						}
						
						double descentDistance = Distance.distanceDescent(heightSpeed1, heightSpeed2, descent, altitude1);
						double ascentDistance = Distance.distanceAscent(heightSpeed1, heightSpeed2, climb, altitude2);

						//Calculate the distance the plane travels at altitude1
						double cruiseDistance1 = totalDistance1 - ascentDistance;
																		
						//Determine whether the plane goes up during the change or down and calculate the time it takes
						if (altitude1 > altitude2) {
							descentTimeChange = Time.timeDescentChange(altitude1, altitude2, descent);
						} else {
							if (altitude1 < altitude2) {
								ascentTimeChange = Time.timeAscentChange(altitude1, altitude2, climb);
							} else {
								
							}
						}
						
						double timeChange = ascentTimeChange + descentTimeChange;
						
						double distanceChange;
						
						//NOTE: This assumes that the plane will not change altitude such that they change whether they are traveling above/below 10000 ft
						//Otherwise you would have to calculate when they cross the line, how far, etc.
						
						if (altitude2 > 10000)
							distanceChange = timeChange * heightSpeed2;
						else
							distanceChange = timeChange * heightSpeed1;

						//The distance the plane travels at altitude2
						double cruiseDistance2 = totalDistance2 - (distanceChange + descentDistance);
						
						cruiseTime1 = cruiseDistance1 / cruiseSpeed1;
						double cruiseTime2 = cruiseDistance2 / cruiseSpeed2;
						
						double totalTime = cruiseTime1 + cruiseTime2 + changeTime + ascentTime + descentTime;
						
						//Both ascent and descent account for the change, one should always be zero so we can add both of them
						double ascentFuel = FuelBurn.ascentFuel((ascentTime + ascentTimeChange), fuelBurnAscent);
						double descentFuel = FuelBurn.descentFuel((descentTime + descentTimeChange), fuelBurnDescent);
						double cruiseFuel1 = FuelBurn.cruiseFuel(cruiseTime1, fuelBurnCruise1);
						double cruiseFuel2 = FuelBurn.cruiseFuel(cruiseTime2, fuelBurnCruise2);
						
						double fuelBurned = ascentFuel + descentFuel + cruiseFuel1 + cruiseFuel2;
						
						//Set the final panel values
						rp.planeLabel.setText("<html> Aircraft: <font color='red'>"+aircraftType);
						rp.altitudeLabel.setText("<html> Cruise Altitude: <font color='red'>"+altitude1+" ("+altitude2+") ft");
						rp.tDistanceLabel.setText("<html> Total Distance: <font color='red'>"+df.format(totalDistance/5280)+" miles");
						rp.tTimeLabel.setText("<html> Total Time: <font color='red'>"+Extra.timeFormat(totalTime));
						rp.tFuelLabel.setText("<html> Total Fuel Burned: <font color='red'>"+df.format(fuelBurned/6.7)+" gallons");	
						 
						//Display the final panel
						JOptionPane.showMessageDialog(contentPane, results, "Results", JOptionPane.PLAIN_MESSAGE);
						break;

					}
				}
				
				if (initLatError || initLongError || finalLatError || finalLongError || altitudeError || aircraftError || windError) {
					//Error handling through calling a panel in another class (ErrorPanel)
					//Import the other JPanel
					//NOTE: this might not actually be necessary, but it works :/
					ErrorPanel ep = new ErrorPanel();
					JPanel errorDialog = new JPanel();
					errorDialog.add(ep.dialog);
					
					//Show any applicable error messages depending on previous info
					if (initLatError)
						ep.iLatError.setVisible(true);
					if (initLongError)
						ep.iLongError.setVisible(true);
					if (finalLatError)
						ep.fLatError.setVisible(true);
					if (finalLongError)
						ep.fLongError.setVisible(true);
					if (altitudeError)
						ep.altError.setVisible(true);
					if (aircraftError)
						ep.planeError.setVisible(true);
					if (windError)
						ep.windError.setVisible(true);
					
					//Show the JPanel made in the other class, imported earlier
					JOptionPane.showMessageDialog(contentPane, errorDialog, "Error", JOptionPane.OK_OPTION);
					
					//Remove the error messages so the next time it runs, there aren't any left over
					ep.iLatError.setVisible(false);
					ep.iLongError.setVisible(false);
					ep.fLatError.setVisible(false);
					ep.fLongError.setVisible(false);
					ep.altError.setVisible(false);
					ep.planeError.setVisible(false);
					ep.windError.setVisible(false);
				}
			}
		});
		
		OptimizePanel op = new OptimizePanel();
		JPanel optimizePanel = new JPanel();
		optimizePanel.add(op.dialog);
		
		//Resets everything
		mnitmReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				
				//Reset some text fields
				initLatInput.setText("");
				initLongInput.setText("");
				finalLatInput.setText("");
				finalLongInput.setText("");
				
				pathGroup.clearSelection();
				windGroup.clearSelection();
				
				//Reset some more text fields and disable other components
				btnEditPath.setVisible(false);
				altitudeInput.setVisible(false);
				altitudeTitle.setVisible(false);
				altitudeInput.setText("");
				aircraftList.clearSelection();
				btnEditWind.setVisible(false);
				
				//Reset the fields in the wind editor
				we.windDir.setText("");
				we.windSpeed.setText("");
				
				//Reset the fields in the path editor
				pe.distanceInput.setText("");
				pe.finalAltInput.setText("");
				pe.initAltInput.setText("");
				pe.pathLat.setText("");
				pe.pathLong.setText("");				
				pe.units.setSelectedIndex(-1);
				
				pe.distanceInput.setEnabled(false);
				pe.pathLat.setEnabled(false);
				pe.pathLong.setEnabled(false);
				pe.units.setEnabled(false);
				pe.group.clearSelection();
				
				//Reset fields in the wind editor
				we.windSpeed.setText("");
				we.windDir.setText("");
				
				//Reset fields in the optimizer
				op.aircraftList.setSelectedIndex(-1);
				op.initLatInput.setText("");
				op.initLongInput.setText("");
				op.finalLatInput.setText("");
				op.finalLongInput.setText("");
				op.optimize.setValue(25);
				
				
			}
			
		});
		
		mnitmQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				frame.dispose();
			}
		});
		
		mnitmStats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				JOptionPane.showMessageDialog(null, "This program was created by Jack and Camryn! \n "
													+ "It calculates the fuel burn and travel time \n"
													+ "of different aircrafts incorporating wind and path \n"
													+ "changes.", "Info", JOptionPane.PLAIN_MESSAGE);
			}
		});
				
		mnitmOptimize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				JOptionPane.showMessageDialog(null, optimizePanel, "Optimization", JOptionPane.PLAIN_MESSAGE);
				if (op.rdbtnSave.isSelected())
					altitudeInput.setText(op.finalAlt + "");
			}
		});
		
	}
}
