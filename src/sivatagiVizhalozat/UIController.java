package sivatagiVizhalozat;


import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class UIController extends JFrame{
	
	private FieldElement SelectedObject;
	private CommandHandler controller;
	
	private ArrayList<IObserver> Observers;
	
	private JButton moveButton = new JButton("move"); 
	private JButton connectButton = new JButton("connect");
	private JButton disconnectButton = new JButton("disconnect");
	private JButton takePumpButton = new JButton("takePump");
	private JButton placePumpButton = new JButton("placePump");
	private JButton grabPipeButton = new JButton("grabPipe");
	private JButton pumpDirectionButton = new JButton("pumpDirection");
	private JButton punctureButton = new JButton("puncture");
	private JButton makeStickyButton = new JButton("makeSticky");
	private JButton makeSlipperyButton = new JButton("makeSlippery");
	private JButton repairButton = new JButton("repair");
	private JButton saveButton = new JButton("save");
	private JButton exitButton = new JButton("exit");
	private JButton skipButton = new JButton("skip");
	
	public UIController() {
		//window Game Initialize
		setTitle("Sivatagi Vizhalozat");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.cyan);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(173, 216, 230));

        // Create a JPanel with BoxLayout for vertical alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(173, 216, 230));

        // Create and customize the buttons
        JButton startButton = createButton("Start", Color.GREEN, new Dimension(150, 30));
        
        
        
        JButton settingsButton = createButton("Settings", Color.YELLOW, new Dimension(150, 30));
        JButton loadGameButton = createButton("LoadGame", Color.GRAY, new Dimension(150, 30));
        JButton exitButton = createButton("Exit", Color.RED, new Dimension(150, 30));

        // Add the buttons to the button panel with vertical spacing
        buttonPanel.add(Box.createVerticalGlue()); // Aligns buttons to the middle of the panel
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Adds vertical spacing
        buttonPanel.add(settingsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(loadGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalGlue()); // Aligns buttons to the middle of the panel

        // Create a horizontal BoxLayout for horizontal alignment
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
        horizontalPanel.add(Box.createHorizontalGlue()); // Aligns buttons to the middle of the panel
        horizontalPanel.add(buttonPanel);
        horizontalPanel.add(Box.createHorizontalGlue()); // Aligns buttons to the middle of the panel
        horizontalPanel.setBackground(new Color(173, 216, 230));

        // Add the horizontal panel to the center of the main panel
        mainPanel.add(horizontalPanel, BorderLayout.CENTER);

        // Set the main panel as the content pane of the frame
        setContentPane(mainPanel);
    }

    private JButton createButton(String text, Color backgroundColor, Dimension size) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setFocusPainted(false);
        return button;
    }
    
	
	public void Redraw(){
		for (IObserver io: Observers) {
			io.Update();
		}
	}
	
	public void MainMenu() {
		
	}
	
	public void StartGame() {
		
	}
	
	public void LoadGame() {
		
	}
	
	public void InGame() {
		
	}
}
