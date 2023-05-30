package sivatagiVizhalozat;

/**
 * The class, that controls the whole UI. This controls the menu and creates the GamePanel too.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JOptionPane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class UIController extends JFrame implements IObserver {
	
	private static final long serialVersionUID = -7808476090807496762L;
	private CommandHandler controller = new CommandHandler(new PrintStream(OutputStream.nullOutputStream()));		/**  The Commandhandler, that handles the commands lol :D*/
	private GamePanel gamePanel = new GamePanel(this);			/** The GamenPanel, that shows the current game's map*/
	static int playerCount = 2;		/** The number of players needed to start a game*/
	static int generated = 0;		/** The number of players already in the game*/
	static int turn = 25;			/** The number of turns until the end of game*/
	static String map = "map2.txt";	/** The map, that should be loaded in*/
	
	/**
	 * Initializes the game
	 */
	public UIController() {
		//window Game Initialize
		setTitle("Sivatagi Vizhalozat");
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.cyan);
        setContentPane(MainMenu());
        setResizable(false);
        gamePanel.addMouseListener(gamePanel);
		gamePanel.addMouseMotionListener(gamePanel);
    }
	
	/**Creates a JButton
	 * @param text The text on the button
	 * @param backgroundColor The background color
	 * @param size The size of the button
	 * @return JButton The created button
	 */
    private JButton createButton(String text, Color backgroundColor, Dimension size) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setFocusPainted(false);
        return button;
    }
	
    /** The initialization of the MainMenu panel
     * @return JPanel The MainMenu panel
     */
	public JPanel MainMenu() {
		controller.setGame(new Game());
		generated = 0;
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
	/**
	 * The method, that starts the game by loading the map
	 * @return JPanel The panel created?
	 */
	public JPanel StartGame() {
		JPanel gamePanel = new JPanel(new BorderLayout());
		gamePanel.setBackground(new Color(173, 216, 230));
		loadMap();
		ChangePane(TheMiddle());
		return null;
	}
	
	/** The middle panel's initialization
	 * @return JPanel The middle panel
	 */
	public JPanel TheMiddle() {
		JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(173, 216, 230));

		JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(173, 216, 230));
		
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(getHeight()/2, 0 , 0, 0));

        JPanel pNameS = new JPanel(); pNameS.setLayout(new BoxLayout(pNameS, BoxLayout.X_AXIS));
        pNameS.setBorder(BorderFactory.createEmptyBorder(0, 0 , 10, 0));
        pNameS.setBackground(new Color(173,216,230));
        JPanel pNameP = new JPanel(); pNameP.setLayout(new BoxLayout(pNameP, BoxLayout.X_AXIS));
        pNameP.setBackground(new Color(173,216,230));
		JLabel NameS = new JLabel("Saboteur" + (generated+1) + "'s Name: "); NameS.setFont(new Font("Hack", Font.BOLD, 16));
		JLabel NameP = new JLabel("Plumber" + (generated+1) + "'s Name: "); NameP.setFont(new Font("Hack", Font.BOLD, 16));

		JTextField SaboteurName = new JTextField();
		SaboteurName.setColumns(1);
		SaboteurName.setPreferredSize(new Dimension(150, 30));
		SaboteurName.setMaximumSize(new Dimension(150, 30));
		
		JTextField PlumberName = new JTextField();
		PlumberName.setColumns(1);
		PlumberName.setPreferredSize(new Dimension(150, 30));
		PlumberName.setMaximumSize(new Dimension(150, 30));
		
		pNameS.add(NameS); pNameS.add(SaboteurName);
		pNameP.add(NameP); pNameP.add(PlumberName);
		centerPanel.add(pNameS); centerPanel.add(pNameP);
		
		JButton btStart = new JButton("Next");
		btStart.setBackground(Color.green);
		btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				if(!PlumberName.getText().isBlank() && !SaboteurName.getText().isBlank()){
					controller.ExecuteCommand("Create(Plumber)");
					controller.ExecuteCommand("SetName(Plumber" + (generated + 1) + "," + PlumberName.getText() + ")");
					controller.ExecuteCommand("SetLocation(Plumber" + (generated + 1) + ",Cistern1)");

					controller.ExecuteCommand("Create(Saboteur)");
					controller.ExecuteCommand("SetName(Saboteur" + (generated + 1) + "," + SaboteurName.getText() + ")");
					controller.ExecuteCommand("SetLocation(Saboteur" + (generated + 1) + ",Spring1)");
					generated++;
					if(generated < playerCount-1){
						ChangePane(TheMiddle());
					}
					else{
						ChangePane(SettingGameData());
					}
				}
			}
        });
		btStart.setPreferredSize(new Dimension(150, 30));
		btStart.setMaximumSize(new Dimension(150, 30));
		
		JButton btBack = new JButton("Back");
		btBack.setBackground(Color.red);
		btBack.addActionListener(action -> ChangePane(MainMenu()));
		btBack.setPreferredSize(new Dimension(150, 30));
		btBack.setMaximumSize(new Dimension(150, 30));

        JPanel functionP = new JPanel(); functionP.setLayout(new BoxLayout(functionP, BoxLayout.X_AXIS));
        functionP.setBackground(new Color(173, 216, 230));
        functionP.setBorder(BorderFactory.createEmptyBorder(10, (getWidth() - 300) / 2, 10, 10));
        functionP.add(btBack);
        functionP.add(Box.createRigidArea(new Dimension(10, 10)));
        functionP.add(btStart);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(functionP, BorderLayout.SOUTH);
		return mainPanel;
	}
	
	/**
	 * The settings panel initialization and function
	 * @return JPanel The panel created
	 */
	public JPanel SettingGameData(){
		controller.getGame().setRemainingRounds(turn);
		JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(173, 216, 230));

		JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(173, 216, 230));
		
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(getHeight()/2, 0 , 0, 0));

        JPanel pNameS = new JPanel(); pNameS.setLayout(new BoxLayout(pNameS, BoxLayout.X_AXIS));
        pNameS.setBorder(BorderFactory.createEmptyBorder(0, 0 , 10, 0));
        pNameS.setBackground(new Color(173,216,230));
        JPanel pNameP = new JPanel(); pNameP.setLayout(new BoxLayout(pNameP, BoxLayout.X_AXIS));
        pNameP.setBackground(new Color(173,216,230));
		JLabel NameS = new JLabel("Saboteur" + (generated+1) + "'s Name: "); NameS.setFont(new Font("Hack", Font.BOLD, 16));
		JLabel NameP = new JLabel("Plumber" + (generated+1) + "'s Name: "); NameP.setFont(new Font("Hack", Font.BOLD, 16));

		JTextField SaboteurName = new JTextField();
		SaboteurName.setColumns(1);
		SaboteurName.setPreferredSize(new Dimension(150, 30));
		SaboteurName.setMaximumSize(new Dimension(150, 30));
		
		JTextField PlumberName = new JTextField();
		PlumberName.setColumns(1);
		PlumberName.setPreferredSize(new Dimension(150, 30));
		PlumberName.setMaximumSize(new Dimension(150, 30));
		
		pNameS.add(NameS); pNameS.add(SaboteurName);
		pNameP.add(NameP); pNameP.add(PlumberName);
		centerPanel.add(pNameS); centerPanel.add(pNameP);
		
		JButton btStart = new JButton("Start Game");
		btStart.setBackground(Color.green);
		btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.ExecuteCommand("Create(Plumber)");
				controller.ExecuteCommand("SetName(Plumber" + (generated + 1) + "," + PlumberName.getText() + ")");
				controller.ExecuteCommand("SetLocation(Plumber" + (generated + 1) + ",Cistern1)");

				controller.ExecuteCommand("Create(Saboteur)");
				controller.ExecuteCommand("SetName(Saboteur" + (generated + 1) + "," + SaboteurName.getText() + ")");
				controller.ExecuteCommand("SetLocation(Saboteur" + (generated + 1) + ",Spring1)");

				generated = 0;
				controller.getGame().NextPlayer();
				controller.getGame().setIsRunning(true);
				ChangePane(InGame());
			}
        });
		btStart.setPreferredSize(new Dimension(150, 30));
		btStart.setMaximumSize(new Dimension(150, 30));
		
		JButton btBack = new JButton("Back");
		btBack.setBackground(Color.red);
		btBack.addActionListener(action -> ChangePane(MainMenu()));
		btBack.setPreferredSize(new Dimension(150, 30));
		btBack.setMaximumSize(new Dimension(150, 30));

        JPanel functionP = new JPanel(); functionP.setLayout(new BoxLayout(functionP, BoxLayout.X_AXIS));
        functionP.setBackground(new Color(173, 216, 230));
        functionP.setBorder(BorderFactory.createEmptyBorder(10, (getWidth() - 300) / 2, 10, 10));
        functionP.add(btBack);
        functionP.add(Box.createRigidArea(new Dimension(10, 10)));
        functionP.add(btStart);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(functionP, BorderLayout.SOUTH);
		return mainPanel;
	}
	
	/**
	 * The initialization and function of the SettingsMenu panel
	 * @return JPanel The panel created
	 */
	public JPanel SettingsMenu() {
		JPanel settingsPanel = new JPanel(new BorderLayout());
		settingsPanel.setBackground(new Color(173, 216, 230));

        // Create a JPanel with BoxLayout for vertical alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(173, 216, 230));

        JLabel playerLabel = new JLabel("Players:");
        JLabel mapLabel = new JLabel("Map:");
        JLabel turnLabel = new JLabel("Turns:");
        // Create an array of numbers from 2 to 8
        Integer[] numbers = {2, 3, 4, 5, 6, 7, 8};
        Integer[] turns = {5,10,15,20,25,30,40,50};

        // Create a JComboBox with the array of numbers
        JComboBox<Integer> cbPlayer = new JComboBox<>(numbers);
        JComboBox<String> cbMap = new JComboBox<String>();
        JComboBox<Integer> cbTurn = new JComboBox<>(turns);
        List<String> maps = GetFiles("maps/");
        for(String map : maps) {
        	cbMap.addItem(map.substring(0, map.length()-4));
        }
        cbPlayer.setSelectedItem(playerCount);
        cbMap.setSelectedItem(map.substring(0, map.length()-4));
        cbTurn.setSelectedItem(turn);
        
        cbPlayer.setPreferredSize(new Dimension(150, 30));
        cbPlayer.setMaximumSize(new Dimension(150, 30));
        cbMap.setPreferredSize(new Dimension(150, 30));
        cbMap.setMaximumSize(new Dimension(150, 30));
        cbTurn.setPreferredSize(new Dimension(150, 30));
        cbTurn.setMaximumSize(new Dimension(150, 30));

        // Add ActionListener to handle selection events
        cbPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = (int)cbPlayer.getSelectedItem();
            }
        });
        
        cbMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	map = (String)cbMap.getSelectedItem() + ".txt";
            }
        });
        
        cbTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	turn = (int)cbTurn.getSelectedItem();
            }
        });
        
        JButton btBack = createButton("Back", Color.RED, new Dimension(150, 30));
        btBack.addActionListener(action -> ChangePane(MainMenu()));
        JButton btReset = createButton("Reset", Color.GREEN, new Dimension(150, 30));
        btReset.addActionListener(action -> {
        	playerCount = 2; cbPlayer.setSelectedItem(2);
        	map = "map2.txt"; cbMap.setSelectedItem("map2");
        	turn = 25; cbTurn.setSelectedItem(25);
        });
        
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
        buttonPanel.add(turnLabel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(cbTurn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(Box.createVerticalGlue()); // Aligns buttons to the middle of the panel

        // Create a horizontal BoxLayout for horizontal alignment
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
        horizontalPanel.add(Box.createHorizontalGlue()); // Aligns buttons to the middle of the panel
        horizontalPanel.add(buttonPanel);
        horizontalPanel.add(Box.createHorizontalGlue()); // Aligns buttons to the middle of the panel
        horizontalPanel.setBackground(new Color(173, 216, 230));
        
        JPanel functionP = new JPanel(); functionP.setLayout(new BoxLayout(functionP, BoxLayout.X_AXIS));
        functionP.setBackground(new Color(173, 216, 230));
        functionP.setBorder(BorderFactory.createEmptyBorder(10, (getWidth() - 300) / 2, 10, 10));
        functionP.add(btBack);
        functionP.add(Box.createRigidArea(new Dimension(10, 10)));
        functionP.add(btReset);
        // Add the horizontal panel to the center of the main panel
        settingsPanel.add(horizontalPanel, BorderLayout.CENTER);
        settingsPanel.add(functionP, BorderLayout.SOUTH);
        return settingsPanel;
	}
	
	/**
	 * List saved Games and load if needed
	 * @return The UI of the LoadGame Menu
	 */
	public JPanel LoadGame() {
		JPanel loadGame = new JPanel(new BorderLayout());
        loadGame.setBackground(new Color(173, 216, 230));
		
        JPanel loadPanel = new JPanel(new BorderLayout());
		loadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		loadPanel.setBackground(new Color(173, 216, 230));
		
		JPanel selectedGame =  new JPanel();
		selectedGame.setLayout(new BoxLayout(selectedGame, BoxLayout.Y_AXIS));
		selectedGame.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		selectedGame.setBackground(new Color(197, 197, 197));
		
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
		JButton btBack = createButton("Back", Color.RED, new Dimension(200,50)); btBack.addActionListener(action -> ChangePane(MainMenu()));
		JButton btLoad = createButton("Load", Color.GREEN, new Dimension(200,50)); btLoad.addActionListener(action -> ChangePane(InGame())); btLoad.setEnabled(false);
		for(String game : games) {
			JButton g = createButton(game.substring(0, game.length()-4), Color.WHITE, new Dimension(200, 80));
			g.addActionListener(action -> {
				selectedGame.removeAll();
				JLabel[] labels = new JLabel[4];
				try {
				controller.LoadGame("saves/" + game);
				labels[0] = new JLabel("Plumbers: " + controller.getGame().getPlumbers().size());
				labels[1] = new JLabel("Saboteurs: " + controller.getGame().getSaboteurs().size());
				labels[2] = new JLabel("Springs: " + controller.getGame().getMap().getSprings().size());
				labels[3] = new JLabel("Cisterns: " + controller.getGame().getMap().getCisterns().size());
				for(JLabel label : labels) {
					label.setFont(new Font("Hack", Font.PLAIN, 18));
					label.setBorder(BorderFactory.createEmptyBorder(5, 50, 0, 0));
					selectedGame.add(label);
				}
				labels[0].setBorder(BorderFactory.createEmptyBorder(25, 50, 0, 0));
					ImageIcon icon = new ImageIcon(createImage(InGame(), getWidth()/2, getHeight()/2));
					int dist = (selectedGame.getWidth() - icon.getIconWidth()) / 2;
					JLabel image = new JLabel(icon);
					image.setBorder(BorderFactory.createEmptyBorder(20, dist+(pGames.getWidth()/2),0,0));
					selectedGame.add(image);
					btLoad.setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			pGames.add(g);
			pGames.add(Box.createRigidArea(new Dimension(0, 10))); // Adds vertical spacing
		}
		
		buttonPanel.add(btBack);
		buttonPanel.add(btLoad);
		buttonPanel.setBackground(new Color(173, 216, 230));
		
		loadPanel.add(buttonPanel, BorderLayout.SOUTH);
		loadPanel.add(selectedGame, BorderLayout.CENTER);
		
		loadGame.add(spGames, BorderLayout.WEST);
		loadGame.add(loadPanel, BorderLayout.CENTER);
		return loadGame;
	}
	
	/**
	 * Creates the inGame UI
	 * @return The created panel of the inGame UI
	 */
	public JPanel InGame() {
		Dimension btSize = new Dimension(170,25);
		JButton[] actions = new JButton[14];
		actions[0] = createButton("Move", Color.WHITE, btSize);
		actions[0].addActionListener(action -> {
			controller.ExecuteCommand("Move(" + controller.getGame().getActivePlayer() + "," + gamePanel.getSelectedObject() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[1] = createButton("Connect", Color.WHITE, btSize);
		actions[1].addActionListener(action -> {
			controller.ExecuteCommand("ConnectPipe(" + controller.getGame().getActivePlayer() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[2] = createButton("Disconnect", Color.WHITE, btSize);
		actions[2].addActionListener(action -> {
			controller.ExecuteCommand("DisconnectPipe(" + controller.getGame().getActivePlayer() + "," + gamePanel.getSelectedObject() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[3] = createButton("TakePump", Color.WHITE, btSize);
		actions[3].addActionListener(action -> {
			controller.ExecuteCommand("TakePump(" + controller.getGame().getActivePlayer() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[4] = createButton("PlacePump", Color.WHITE, btSize);
		actions[4].addActionListener(action -> {
			controller.ExecuteCommand("PlacePump(" + controller.getGame().getActivePlayer() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[5] = createButton("GrabPipe", Color.WHITE, btSize);
		actions[5].addActionListener(action -> {
			controller.ExecuteCommand("GrabPipe(" + controller.getGame().getActivePlayer() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[6] = createButton("PumpDirection", Color.WHITE, btSize);
		actions[6].addActionListener(action -> {
			if(!controller.getGame().getActivePlayer().getLocation().toString().contains("Pump")) return;
			DirectionSelect selector = new DirectionSelect(controller.getGame().getActivePlayer().getLocation().GetNeighbor(), this);
			String[] result = selector.ChangeDirection();
			if(result[0].equals("Cancelled")) return;
			else {
				controller.ExecuteCommand("PumpDirection(" + controller.getGame().getActivePlayer().toString() + "," + result[0] + "," + result[1] + ")");
				controller.getGame().NextPlayer();
				Update(getGraphics());
			}
		}); 
		
		actions[7] = createButton("Puncture", Color.WHITE, btSize);
		actions[7].addActionListener(action -> {
			controller.ExecuteCommand("Puncture(" + controller.getGame().getActivePlayer() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[8] = createButton("MakeSticky", Color.WHITE, btSize);
		actions[8].addActionListener(action -> {
			controller.ExecuteCommand("MakeSticky(" + controller.getGame().getActivePlayer() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[9] = createButton("MakeSlippery", Color.WHITE, btSize);
		actions[9].addActionListener(action -> {
			controller.ExecuteCommand("MakeSlippery(" + controller.getGame().getActivePlayer() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[10] = createButton("Repair", Color.WHITE, btSize);
		actions[10].addActionListener(action -> {
			controller.ExecuteCommand("Repair(" + controller.getGame().getActivePlayer() + ")");
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		actions[11] = createButton("Save", Color.WHITE, btSize);
		actions[11].addActionListener(action -> {
			String name = "";
			name = JOptionPane.showInputDialog("Please provide name to save game: ");
			if(name != null) {
				if(!name.isBlank())
					controller.ExecuteCommand("SaveGame("+ name + ")");
			}
			Update(getGraphics());
		});
		
		actions[12] = createButton("Exit", Color.WHITE, btSize);
		actions[12].addActionListener(action -> {
			ChangePane(MainMenu());
			controller.setGame(new Game());
		});
		
		actions[13] = createButton("Skip", Color.WHITE, btSize);
		actions[13].addActionListener(action -> {
			controller.getGame().NextPlayer();
			Update(getGraphics());
		});
		
		JPanel inGame = new JPanel(new BorderLayout());
		inGame.setBackground(new Color(173, 216, 230));
		
        JPanel lPanel = new JPanel();
        lPanel.setLayout(new BoxLayout(lPanel, BoxLayout.Y_AXIS));
        lPanel.setPreferredSize(new Dimension(350,700));
        lPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        
        // UserList panel
        Player p = controller.getGame().getActivePlayer();
        if(p == null) controller.getGame().NextPlayer();
        if(gamePanel.getSelectedObject() == null || !controller.getGame().getMap().getFields().contains(gamePanel.getSelectedObject())) {
        	FieldElement f = controller.getGame().getMap().getFieldElement("Pipe1");
        	f.getObserver().setSelected(true);
        	gamePanel.setSelectedObject(f);
        } else {
        	gamePanel.getSelectedObject().getObserver().setSelected(true);
        }
        JPanel pP = listPanel(p.toString() + ": " + p.getName(),p.List());
        pP.setPreferredSize(new Dimension(345,90));
        pP.setSize(pP.getPreferredSize());
        lPanel.add(pP);
		
        // ButtonPanel/Actions List
		JPanel btPanel = new JPanel();
		btPanel.setLayout(new BoxLayout(btPanel, BoxLayout.Y_AXIS));
		for(int i = 0; i < actions.length; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JPanel p1 = new JPanel();
			p1.setPreferredSize(new Dimension(170,15));
			p1.setSize(p1.getPreferredSize());
			p1.add(actions[i++]);
			panel.add(p1);
			JPanel p2 = new JPanel();
			p2.setPreferredSize(new Dimension(170,15));
			p2.setSize(p2.getPreferredSize());
			p2.add(actions[i]);
			panel.add(p2);
			btPanel.add(panel);
		}
		lPanel.add(btPanel);
		
		// ObjectList - SelectedObject
		lPanel.add(listPanel(gamePanel.getSelectedObject().toString(), gamePanel.getSelectedObject().List()));
		
		// Game status list
		JLabel lRR = new JLabel("   " + controller.getGame().List().split("\n")[0]); lRR.setFont(new Font("Hack", Font.PLAIN, 18));
		lRR.setPreferredSize(new Dimension(345,45));
		JLabel lPlu = new JLabel("   " + controller.getGame().List().split("\n")[1]); lPlu.setFont(new Font("Hack", Font.PLAIN, 18));
		lPlu.setPreferredSize(new Dimension(345,45));
		JLabel lSab = new JLabel("   " + controller.getGame().List().split("\n")[2]); lSab.setFont(new Font("Hack", Font.PLAIN, 18));
		lSab.setPreferredSize(new Dimension(345,45));
		JPanel pRR = new JPanel();
		pRR.add(lRR); pRR.setPreferredSize(new Dimension(350,30));
		JPanel pPlu = new JPanel();
		pPlu.add(lPlu); pPlu.setPreferredSize(new Dimension(350,30));
		JPanel pSab = new JPanel();
		pSab.add(lSab); pSab.setPreferredSize(new Dimension(350,30));
		lPanel.add(pRR);
		lPanel.add(pPlu);
		lPanel.add(pSab);
		
		// Add the list panel
		inGame.add(lPanel, BorderLayout.WEST);
		
		// Add game panel
		gamePanel.Clear();
		for (FieldElement f: controller.getGame().getMap().getFields()) {
			gamePanel.Add(f.getObserver());
		}
		gamePanel.Add(controller.getGame().getActivePlayer().getObserver());
		gamePanel.setBackground(new Color(199,184,135));
		gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gamePanel.setSize(new Dimension(getWidth() - lPanel.getPreferredSize().width, getHeight()));
		
		inGame.add(gamePanel, BorderLayout.CENTER);
		inGame.setPreferredSize(getSize());
		return inGame;
	}
	
	/** Change content pane to given panel
	 * 
	 * @param pane A panel to change the contentPane to
	 */
	private void ChangePane(JPanel pane) {
		if(pane == null) return;
		getContentPane().removeAll();
		getContentPane().add(pane);
		getContentPane().doLayout();
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	/**
	 * Creates the list view of an object
	 * @param name The name of the object
	 * @param list The list of the object's parameters
	 * @return The created panel, containing the list view
	 */
	public JPanel listPanel(String name, String list) {
		JPanel lPanel = new JPanel();
		lPanel.setLayout(new BoxLayout(lPanel, BoxLayout.Y_AXIS));
		if(!name.isEmpty()) {
			JPanel lName = new JPanel();
			JLabel oName = new JLabel(name);
			oName.setFont(new Font("Hack", Font.PLAIN, 18));
			lName.add(oName);
			lPanel.add(lName);
		}
		lPanel.add(extractList(list));
		
		return lPanel;
	}
	
	/**
	 * Processes a list String, and creates the panel for the list view
	 * @param list The list of the object's parameters
	 * @return The created panel, containing the list view
	 */
	public JPanel extractList(String list) {
		JPanel oList = new JPanel();
		oList.setLayout(new BoxLayout(oList, BoxLayout.Y_AXIS));
		String[] extracted = list.split("\n");
		ArrayList<String> params = new ArrayList<String>();
		for(String str : extracted) {
			if(!str.contains("id: ") && !str.contains("name: ")) {
				params.add(str);
			}
		}
		int i = 0;
		for(; i < params.size(); i++) {
			if(params.get(i).contains("connections:")) break;
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JPanel p1 = new JPanel();
			JLabel l1 = new JLabel("   ".concat(params.get(i)));
			p1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			p1.setPreferredSize(new Dimension(170,23));
			p1.setSize(p1.getPreferredSize());
			p1.add(l1);
			l1.setPreferredSize(new Dimension(170,23));
			l1.setSize(l1.getPreferredSize());
			panel.add(p1);
			if(params.size() == ++i) break;
			if(params.get(i).contains("connections:")) break;
			JPanel p2 = new JPanel();
			JLabel l2 = new JLabel("   ".concat(params.get(i)));
			l2.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
			p2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			p2.setPreferredSize(new Dimension(170,23));
			p2.setSize(p2.getPreferredSize());
			l2.setPreferredSize(new Dimension(170,23));
			l2.setSize(l2.getPreferredSize());
			p2.add(l2);
			panel.add(p2);
			oList.add(panel);
		}
		i++;
		if(params.size() > i) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JPanel p1 = new JPanel();
			JLabel l1 = new JLabel("   connections");
			p1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			p1.setPreferredSize(new Dimension(170,23));
			p1.setSize(p1.getPreferredSize());
			l1.setPreferredSize(new Dimension(170,23));
			l1.setSize(l1.getPreferredSize());
			p1.add(l1);
			panel.add(p1);
			l1.setPreferredSize(new Dimension(170,23));
			JPanel p2 = new JPanel();
			JLabel l2 = new JLabel("   players");
			p2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			p2.setPreferredSize(new Dimension(170,23));
			p2.setSize(p2.getPreferredSize());
			l2.setPreferredSize(new Dimension(170,23));
			l2.setSize(l2.getPreferredSize());
			p2.add(l2);
			panel.add(p2);
			
			oList.add(panel);
			
			JPanel panel2 = new JPanel();
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
			for(int j = 0; j < 2; j++) {
				JPanel pCon = new JPanel();
				pCon.setLayout(new BoxLayout(pCon, BoxLayout.Y_AXIS));
				JScrollPane spCon = new JScrollPane(pCon);
				spCon.setPreferredSize(new Dimension(160,23));
				spCon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				while(i < params.size() && !params.get(i).contains("players")) {
					pCon.add(new JLabel("   ".concat(params.get(i))));
					i++;
				}
				panel2.add(spCon);
				i++;
			}
			oList.add(panel2);
		}
		return oList;
	}
	
	/**
	 * Get all files from a directory and store them in a List
	 * @param dir The Path to the directory
	 * @return The created List containing all files found in the given directory
	 */
	public List<String> GetFiles(String dir) {

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

	    // Render the panel and its components
	    panel.paint(g);
	    for (Component component : panel.getComponents()) {
	        if (component instanceof JComponent) {
	            JComponent jComponent = (JComponent) component;
	            jComponent.paint(g);
	        }
	    }

	    g.dispose();

	    BufferedImage resizedImage = resize(bi, width, height);
	    return resizedImage;
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
	
	/**
	 * Small inner menu to list and select wich preset map we would like to use
	 *
	 * @param scanner The scanner that we use to receive input
	 * @param controller The command controller used to execute the read commands
	 */
	private void loadMap() {
		File inputFile = new File("maps/" + map);
		try {
			Scanner in = new Scanner(inputFile);
			controller.Read(in, new PrintStream(OutputStream.nullOutputStream()));
			in.close();
			return;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public FieldElement Clicked(int x, int y) {
		return null;
	}

	@Override
	public void Update(Graphics graphics) {
		ChangePane(InGame());
		if(!controller.getGame().getIsRunning()) {
			JOptionPane.showMessageDialog(null, controller.getGame().EndGame(), "EndGame", JOptionPane.INFORMATION_MESSAGE);
			ChangePane(MainMenu());
		}
	}

	@Override
	public void setSelected(boolean s) { }

	@Override
	public Point getPosition() {
		return null;
	}

	@Override
	public void setPosition(Point pos) { }
}
