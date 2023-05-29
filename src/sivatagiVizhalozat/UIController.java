package sivatagiVizhalozat;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class UIController extends JFrame{
	
	private FieldElement selectedObject;
	private CommandHandler controller;
	
	private ArrayList<IObserver> Observers;
	
	public UIController() {
		//window Game Initialize
		setTitle("Sivatagi Vizhalozat");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.cyan);
        setContentPane(MainMenu());
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
	
	public JPanel MainMenu() {
		JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(173, 216, 230));

        // Create a JPanel with BoxLayout for vertical alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(173, 216, 230));

        // Create and customize the buttons
        JButton startButton = createButton("Start", Color.GREEN, new Dimension(150, 30));
        startButton.addActionListener(action -> ChangePane(StartGame()));
        JButton settingsButton = createButton("Settings", Color.YELLOW, new Dimension(150, 30));
        settingsButton.addActionListener(action -> ChangePane((SettingsMenu())));
        JButton loadGameButton = createButton("LoadGame", Color.GRAY, new Dimension(150, 30));
        loadGameButton.addActionListener(action -> ChangePane(LoadGame()));
        JButton exitButton = createButton("Exit", Color.RED, new Dimension(150, 30));
        exitButton.addActionListener(action -> System.exit(0));

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
        return mainPanel;
	}
	
	public JPanel StartGame() {
		return null;
	}
	
	public JPanel SettingsMenu() {
		JPanel settingsPanel = new JPanel(new BorderLayout());
		settingsPanel.setBackground(new Color(173, 216, 230));

        // Create a JPanel with BoxLayout for vertical alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(173, 216, 230));

        JLabel playerLabel = new JLabel("Players:");
        JLabel mapLabel = new JLabel("Map:");
        // Create an array of numbers from 1 to 8
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};

        // Create a JComboBox with the array of numbers
        JComboBox<Integer> cbPlayer = new JComboBox<>(numbers);
        JComboBox<String> cbMap = new JComboBox<String>();
        List<String> maps = GetFiles("maps/");
        for(String map : maps) {
        	cbMap.addItem(map.substring(0, map.length()-4));
        }
        cbPlayer.setPreferredSize(new Dimension(150, 30));
        cbPlayer.setMaximumSize(new Dimension(150, 30));
        cbMap.setPreferredSize(new Dimension(150, 30));
        cbMap.setMaximumSize(new Dimension(150, 30));

        // Add ActionListener to handle selection events
        cbPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + cbPlayer.getSelectedItem());
            }
        });
        
        cbMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Selected: " + cbMap.getSelectedItem());
            }
        });
        
        JButton btBack = createButton("Back", Color.RED, new Dimension(150, 30));
        btBack.addActionListener(action -> ChangePane(MainMenu()));
        
        // Add the buttons to the button panel with vertical spacing
        buttonPanel.add(Box.createVerticalGlue()); // Aligns buttons to the middle of the panel
        buttonPanel.add(playerLabel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Adds vertical spacing
        buttonPanel.add(cbPlayer);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(mapLabel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(cbMap);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        //buttonPanel.add(btBack);
        buttonPanel.add(Box.createVerticalGlue()); // Aligns buttons to the middle of the panel

        // Create a horizontal BoxLayout for horizontal alignment
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
        horizontalPanel.add(Box.createHorizontalGlue()); // Aligns buttons to the middle of the panel
        horizontalPanel.add(buttonPanel);
        horizontalPanel.add(Box.createHorizontalGlue()); // Aligns buttons to the middle of the panel
        horizontalPanel.setBackground(new Color(173, 216, 230));

        // Add the horizontal panel to the center of the main panel
        settingsPanel.add(horizontalPanel, BorderLayout.CENTER);
        return settingsPanel;
	}
	
	public JPanel LoadGame() {
		JPanel loadGame = new JPanel(new BorderLayout());
        loadGame.setBackground(new Color(173, 216, 230));
		
        JPanel loadPanel = new JPanel(new BorderLayout());
		loadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		loadPanel.setBackground(new Color(173, 216, 230));
		
		JPanel selectedGame =  new JPanel();
		selectedGame.setLayout(new BoxLayout(selectedGame, BoxLayout.Y_AXIS));
		selectedGame.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		selectedGame.setBackground(new Color(197, 197, 197)); // #C5C5C5
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
		JPanel pGames = new JPanel();
		pGames.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JScrollPane spGames = new JScrollPane(pGames);
		spGames.getVerticalScrollBar().setBackground(Color.BLACK);
		spGames.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spGames.setPreferredSize(new Dimension(240, 600));
		spGames.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = Color.WHITE;
		    }
		});
		pGames.setLayout(new BoxLayout(pGames, BoxLayout.Y_AXIS));
		pGames.setBackground(new Color(173, 216, 230));
		List<String> games = GetFiles("saves/");
		games.sort((a,b) -> a.compareTo(b));
		for(String game : games) {
			JButton g = createButton(game.substring(0, game.length()-4), Color.WHITE, new Dimension(200, 80));
			g.addActionListener(action -> {
				System.out.println("Action not implemented");
				// TODO Fill missing values
				selectedGame.removeAll();
				JLabel[] labels = new JLabel[4];
				labels[0] = new JLabel("Plumbers: ");
				labels[1] = new JLabel("Saboteurs: ");
				labels[2] = new JLabel("Springs: ");
				labels[3] = new JLabel("Cisterns: ");
				for(JLabel label : labels) {
					label.setFont(new Font("Hack", Font.PLAIN, 18));
					label.setBorder(BorderFactory.createEmptyBorder(5, 50, 0, 0));
					selectedGame.add(label);
				}
				labels[0].setBorder(BorderFactory.createEmptyBorder(25, 50, 0, 0));
				try {
					ImageIcon icon = new ImageIcon("icon.png"); // TODO get actual image of game
					int dist = (selectedGame.getWidth() - icon.getIconWidth()) / 2;
					JLabel image = new JLabel(icon);
					image.setBorder(BorderFactory.createEmptyBorder(100, dist,0,0));
					selectedGame.add(image);
				} catch (Exception e) {}
				selectedGame.doLayout();
				selectedGame.repaint();
			});
			pGames.add(g);
			pGames.add(Box.createRigidArea(new Dimension(0, 10))); // Adds vertical spacing
		}
		
		JButton btBack = createButton("Back", Color.RED, new Dimension(200,50)); btBack.addActionListener(action -> ChangePane(MainMenu()));
		JButton btLoad = createButton("Load", Color.GREEN, new Dimension(200,50));
		buttonPanel.add(btBack);
		buttonPanel.add(btLoad);
		buttonPanel.setBackground(new Color(173, 216, 230));
		
		loadPanel.add(buttonPanel, BorderLayout.SOUTH);
		loadPanel.add(selectedGame, BorderLayout.CENTER);
		
		loadGame.add(spGames, BorderLayout.WEST);
		loadGame.add(loadPanel, BorderLayout.CENTER);
		return loadGame;
	}
	
	public JPanel InGame() {
		JButton moveButton = new JButton("Move");
		moveButton.addActionListener(action -> controller.ExecuteCommand("Move(" + controller.getGame().getActivePlayer() + "," + selectedObject + ")"));
		
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(action -> controller.ExecuteCommand("ConnectPipe(" + controller.getGame().getActivePlayer() + ")"));
		
		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(action -> controller.ExecuteCommand("DisconnectPipe(" + controller.getGame().getActivePlayer() + "," + selectedObject + ")"));
		
		JButton takePumpButton = new JButton("TakePump");
		takePumpButton.addActionListener(action -> controller.ExecuteCommand("TakePump(" + controller.getGame().getActivePlayer() + ")"));
		
		JButton placePumpButton = new JButton("PlacePump");
		placePumpButton.addActionListener(action -> controller.ExecuteCommand("PlacePump(" + controller.getGame().getActivePlayer() + ")"));
		
		JButton grabPipeButton = new JButton("GrabPipe");
		grabPipeButton.addActionListener(action -> controller.ExecuteCommand("GrabPipe(" + controller.getGame().getActivePlayer() + ")"));
		
		JButton pumpDirectionButton = new JButton("PumpDirection");
		//pumpDirectionButton.addActionListener(action -> controller.ExecuteCommand("")); TODO
		
		JButton punctureButton = new JButton("Puncture");
		punctureButton.addActionListener(action -> controller.ExecuteCommand("Puncture(" + controller.getGame().getActivePlayer() + ")"));
		
		JButton makeStickyButton = new JButton("MakeSticky");
		makeStickyButton.addActionListener(action -> controller.ExecuteCommand("MakeSticky(" + controller.getGame().getActivePlayer() + ")"));
		
		JButton makeSlipperyButton = new JButton("MakeSlippery");
		makeSlipperyButton.addActionListener(action -> controller.ExecuteCommand("MakeSlippery(" + controller.getGame().getActivePlayer() + ")"));
		
		JButton repairButton = new JButton("Repair");
		repairButton.addActionListener(action -> controller.ExecuteCommand("Repair(" + controller.getGame().getActivePlayer() + ")"));
		
		JButton saveButton = new JButton("Save");
		//saveButton.addActionListener(action -> controller.ExecuteCommand("Save(" + controller.getGame().getActivePlayer() + "," + selectedObject + ")"));
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(action -> ChangePane(MainMenu()));
		
		JButton skipButton = new JButton("Skip");
		//skipButton.addActionListener(action -> controller.ExecuteCommand("")); TODO
		
		return null;
	}
	
	private void ChangePane(JPanel pane) {
		if(pane == null) return;
		getContentPane().removeAll();
		getContentPane().add(pane);
		getContentPane().doLayout();
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	public List<String> GetFiles(String dir){

		try (Stream<Path> stream = Files.list(Paths.get(dir))) {

			return stream
					.filter(file -> !Files.isDirectory(file))
					.map(Path::getFileName)
					.map(Path::toString)
					.collect(Collectors.toList());
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return null;
	}
	
	/**
	 * Creates an image of a table
	 * source: https://stackoverflow.com/questions/1626735/how-can-i-display-a-bufferedimage-in-a-jframe
	 * @param panel The jpanel which the picture is taken of
	 * @param width The width of the image
	 * @param height The height of the image
	 * @return The created image
	 */
	private BufferedImage createImage(JPanel panel, int width, int height) {
		Dimension size = panel.getPreferredSize();
		BufferedImage bi = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		panel.setSize(panel.getPreferredSize());
		panel.paint(g);
		g.dispose();
		BufferedImage ret = resize(bi, width, height);
		return ret;
	}
	/**
	 * Resizes a given image to the given size
	 * source: https://stackoverflow.com/questions/9417356/bufferedimage-resize
	 * @param img The image to be resized
	 * @param width The width of the image
	 * @param height The height of the image
	 * @return The resized image
	 */
	private BufferedImage resize(BufferedImage img, int width, int height) { 
	    Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    
	    return dimg;
	}  

}
