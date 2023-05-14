package sivatagiVizhalozat;

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

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 0) {
			PrintStream out = new PrintStream(OutputStream.nullOutputStream());
			CommandHandler handler = new CommandHandler(out);
			runMainGame(handler, scanner);
		} else {
			CommandHandler handler = new CommandHandler();
			// Start test mode
			if (args.length == 1 && args[0].equals("-t")) {
				handler.TestMenu(scanner, "");
			}
			// Start test mode with reading from console
			else if (args.length == 2 && args[0].equals("-t") && args[1].equals("-c")) {
				PrintStream out = new PrintStream(System.out);
				handler.Read(scanner, out);
			}
			// Run test of given Test case
			else if (args.length == 2 && args[0].equals("-t")) {
				handler.RunTest(args[1]);
			}
			// Run test of given Test case and write output to console
			else if (args.length == 3 && args[0].equals("-t")) {
				if (args[1].equals("-c")) {
					try {
						PrintStream out = new PrintStream(System.out);
						Scanner test = new Scanner(new File("tests/" + args[2] + ".txt"));
						handler.Read(test, out);
					} catch (FileNotFoundException e) {
						System.out.println(args[2] + " could not be found!");
					}
				}
			}
		}
	}

	/**
	 * Main game menu
	 *
	 * @param handler the commandhandler which executes the player commands
	 * @param scanner the scanner which read from the console
	 */
	public static void runMainGame(CommandHandler handler, Scanner scanner) {
		String input;
		boolean flag = true;
		System.out.println("Choose:\n\t0. Exit\n\t1. Game\n\t2. Load Game");
		while (flag) {
			if (scanner.hasNextLine()) {
				input = scanner.nextLine();
				switch (input) {
					case "0":
						scanner.close();
						System.exit(0);
						flag = false;
						break;
					case "1":
						mapHandle(scanner, handler);
						ParamSetting(scanner, handler);

						handler.getGame().setIsRunning(true);

						System.out.println("Start!");
						handler.getGame().NextPlayer();
						while (handler.getGame().getIsRunning()) {
							GameRounds(scanner, handler);
							handler.getGame().NextPlayer();
						}
						break;
					case "2":
						if (!loadGame(scanner, handler))
							break;
						System.out.println("Start!");
						while (handler.getGame().getIsRunning()) {
							GameRounds(scanner, handler);
							handler.getGame().NextPlayer();
						}
						break;
					default:
						System.out.println("Wrong input, try again!");
						break;
				}
				System.out.println("Choose:\n\t0. Exit\n\t1. Game\n\t2. Load Game");
			}
		}
	}

	/**
	 * Small inner menu to list and select wich preset map we would like to use
	 *
	 * @param scanner The scanner that we use to receive input
	 * @param handler The command handler used to execute the read commands
	 */
	public static void mapHandle(Scanner scanner, CommandHandler handler) {
		List<String> maps = new ArrayList<String>();
		try {
			maps = GetFiles("maps/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			int i = 0;
			for (String m : maps) {
				System.out.println(++i + " " + m);
			}
			String input = scanner.nextLine();
			try {
				int num = Integer.parseInt(input);

				File inputFile = new File("maps/" + maps.get(num - 1));

				try {
					Scanner in = new Scanner(inputFile);

					handler.Read(in, new PrintStream(OutputStream.nullOutputStream()));
					in.close();
					return;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException e) {
				System.out.println("Not number!");
			}

		}
	}

	/**
	 * Small inner menu to list and select wich preset map we would like to use
	 *
	 * @param scanner The scanner that we use to receive input
	 * @param handler The command handler used to execute the read commands
	 */
	public static boolean loadGame(Scanner scanner, CommandHandler handler) {
		List<String> saves = new ArrayList<String>();
		try {
			saves = GetFiles("saves/");
			if (saves.size() == 0) {
				System.out.println("Couldn't find saved games!");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean exit = false;
		while (!exit) {
			int i = 0;
			System.out.println("0. Back");
			for (String save : saves) {
				System.out.println(++i + ". " + save.replaceFirst(".ser", ""));
			}
			String input = scanner.nextLine();
			try {
				int num = Integer.parseInt(input);
				if (num == 0)
					return false;
				handler.LoadGame("saves/" + saves.get(num - 1));
				exit = true;
			} catch (Exception e) {
				if (e.getClass() == FileNotFoundException.class) {
					System.out.println("Could not find game to load");
				} else {
					System.out.println("Not a number");
				}
			}
		}
		return true;
	}

	/**
	 * Gets the filenames from a file.
	 * 
	 * @param dir the directory
	 * @return filenames
	 */
	public static List<String> GetFiles(String dir) throws IOException {

		try (Stream<Path> stream = Files.list(Paths.get(dir))) {

			return stream
					.filter(file -> !Files.isDirectory(file))
					.map(Path::getFileName)
					.map(Path::toString)
					.collect(Collectors.toList());
		}
	}

	/**
	 * Small inner menu to set the game up.
	 * Reads the number of plumbers and saboteurs the user or tester would like to
	 * have in the game. Sets up the number of rounds of the game too.
	 *
	 * @param scanner The scanner that we use to receive input
	 * @param handler The command handler used to execute the read commands
	 */
	public static void ParamSetting(Scanner scanner, CommandHandler handler) {
		int num = 0;
		String input;
		do {
			try {
				System.out.println("Add a number of Saboteur and Plumber (Same for each. At least 2): ");
				input = scanner.nextLine();
				num = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Please provide a valid input!");
			}
		} while (num < 2);
		for (int i = 0; i < num; i++) {
			System.out.println("Plumber" + (i + 1) + " name: ");
			input = scanner.nextLine();
			handler.ExecuteCommand("Create(Plumber)");
			handler.ExecuteCommand("SetName(Plumber" + (i + 1) + "," + input + ")");
			handler.ExecuteCommand("SetLocation(Plumber" + (i + 1) + ",Cistern1)");
			System.out.println("Saboteur" + (i + 1) + " name: ");
			input = scanner.nextLine();
			handler.ExecuteCommand("Create(Saboteur)");
			handler.ExecuteCommand("SetName(Saboteur" + (i + 1) + "," + input + ")");
			handler.ExecuteCommand("SetLocation(Saboteur" + (i + 1) + ",Spring1)");
		}

		num = 0;
		do {
			System.out.println("Type a number how many round do you want: ");
			input = scanner.nextLine();
			try {
				num = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Please provide a valid input!");
			}
		} while (num <= 0);
		handler.ExecuteCommand("SetremainingRounds(Game1," + num + ")");
	}

	/**
	 * Game logic, that handles the input of the players while the gameis running.
	 *
	 * @param scanner The scanner that we use to receive input
	 * @param handler The command handler used to execute the read commands
	 */
	public static void GameRounds(Scanner scanner, CommandHandler handler) {
		System.out.println(handler.getGame().getActivePlayer().getName() + "'s turn it is.");
		System.out.println("Choose an action (you can call for 'help' or 'list'): ");
		handler.setOutput(new PrintStream(System.out));
		boolean flag = true;
		while (flag) {
			String in = scanner.nextLine();
			String[] input1 = in.split(" ");
			// help command for the Plumbers
			if (input1[0].toLowerCase().equals("help")
					&& handler.getGame().getActivePlayer().getClass().getSimpleName().equals("Plumber"))
				System.out.println(
						"Move\nConnectPipe\nDisconnectPipe\nTakePump\nPlacePump\nGrabPipe\nPumpDirection\nPuncture\nMakeSticky\nRepair\nSave\nExit");
			// help command for the Saboteurs
			else if (input1[0].toLowerCase().equals("help"))
				System.out.println("Move\nPumpDirection\nPuncture\nMakeSticky\nMakeSlippery\nSave\nExit");
			// list command for the FieldElements
			else if (input1[0].toLowerCase().equals("list") && input1.length > 1)
				handler.ExecuteCommand("ListParams(" + input1[1] + ")");
			// list command for the Players
			else if (input1[0].toLowerCase().equals("list"))
				handler.ExecuteCommand("ListParams(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
						+ handler.getGame().getActivePlayer().getId() + ")");
			// save command
			else if (input1[0].toLowerCase().equals("save"))
				handler.ExecuteCommand("SaveGame(" + input1[1] + ")");
			// exit command
			else if (input1[0].toLowerCase().equals("exit")) {
				handler.getGame().setIsRunning(false);
				return;
			} else {
				flag = false;
				// Commands for the Plumbers
				if (handler.getGame().getActivePlayer().getClass().getSimpleName().equals("Plumber")) {
					switch (input1[0].toLowerCase()) {
						case "move":
							handler.ExecuteCommand(
									"Move(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ","
											+ input1[1] + ")");
							break;
						case "connectpipe":
							handler.ExecuteCommand(
									"ConnectPipe(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "disconnectpipe":
							handler.ExecuteCommand(
									"DisconnectPipe(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ","
											+ input1[1] + ")");
							break;
						case "takepump":
							handler.ExecuteCommand(
									"TakePump(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "placepump":
							handler.ExecuteCommand(
									"PlacePump(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "grabpipe":
							handler.ExecuteCommand(
									"GrabPipe(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "pumpdirection":
							handler.ExecuteCommand(
									"PumpDirection(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ","
											+ input1[1] + "," + input1[2] + ")");
							break;
						case "puncture":
							handler.ExecuteCommand(
									"Puncture(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "makesticky":
							handler.ExecuteCommand(
									"MakeSticky(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "repair":
							handler.ExecuteCommand(
									"Repair(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						default:
							System.out.println("Invalid command, try again!");
							flag = true;
							break;
					}
				}
				// Commands for the Saboteurs
				else {
					switch (input1[0].toLowerCase()) {
						case "move":
							handler.ExecuteCommand(
									"Move(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ","
											+ input1[1] + ")");
							break;
						case "pumpdirection":
							handler.ExecuteCommand(
									"PumpDirection(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ","
											+ input1[1] + "," + input1[2] + ")");
							break;
						case "puncture":
							handler.ExecuteCommand(
									"Puncture(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "makesticky":
							handler.ExecuteCommand(
									"MakeSticky(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "makeslippery":
							handler.ExecuteCommand(
									"MakeSlippery(" + handler.getGame().getActivePlayer().getClass().getSimpleName()
											+ String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						default:
							System.out.println("Invalid command, try again!");
							flag = true;
							break;
					}
				}
			}
		}
	}
}