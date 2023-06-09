package sivatagiVizhalozat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * Class, that is liable for the handling of all the test commands.
 */
public class CommandHandler {

	/**
	 * The game being played/tested.
	 */
	private Game game;

	/**
	 * The print stream.
	 * the print stream commands use
	 * Can be reassigned to a file for testing
	 * 
	 */
	private PrintStream out = System.out;

	/**
	 * Default constructor, it sets the output to output.txt
	 */
	CommandHandler() {
		// sets print stream to file
		try {
			File outFile = new File("output.txt");
			out = new PrintStream(outFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * One parameter contructor, sets the output stream to the given PrintStream
	 * 
	 * @param output PrintStream to redirect output
	 */
	CommandHandler(PrintStream output) {
		out = output;
	}

	/**
	 * Load game.
	 *
	 * @param path the path of the file we are loading the game from
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void LoadGame(String path) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		game = (Game) in.readObject();
		in.close();
		fileIn.close();
		// loads form the game every classes static nextId
		int array[] = game.getIdArray();
		Plumber.setNextId(array[0]);
		Saboteur.setNextId(array[1]);
		Cistern.setNextId(array[2]);
		Pump.setNextId(array[3]);
		Spring.setNextId(array[4]);
		Pipe.setNextId(array[5]);
		out.println("LoadGame(" + path + ") Success");
	}

	/**
	 * Save game.
	 *
	 * @param path the path of the file we are saving the game to
	 * @throws IOException
	 */
	void SaveGame(String path) throws IOException {
		// Saves the static id of every class to the game
		int array[] = { Plumber.getNextId(), Saboteur.getNextId(), Cistern.getNextId(), Pump.getNextId(),
				Spring.getNextId(), Pipe.getNextId() };
		game.setIdArray(array);

		FileOutputStream fileOut = new FileOutputStream("saves/" + path + ".ser");
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(game);
		objOut.close();
		fileOut.close();
		out.println("SaveGame(" + path + ") Success");
	}

	/**
	 * Read the required output from test.txt, write the check result to console.
	 *
	 * @param file The output file's prefix
	 */
	public void TestCheck(String file) {
		try {
			File testFile = new File("tests/" + file + "Output.txt");
			Scanner test = new Scanner(testFile);
			File outputFile = new File("output.txt");
			Scanner output = new Scanner(outputFile);
			int i = 0;
			boolean testCheck = true;
			while (output.hasNextLine() && test.hasNextLine()) {
				i++;
				// compare the lines of the required output and the output
				String outputLine = output.nextLine();
				String testLine = test.nextLine();
				if (!outputLine.equals(testLine)) {
					testCheck = false;
					System.out.println("Row:\t\t\t" + i + "\nRequired output:\t" + testLine + "\nActual output:\t\t"
							+ outputLine + "\n");
				}
			}

			if (!testCheck || test.hasNextLine() || output.hasNextLine())
				System.out.println("TestCheck FAILED");
			else
				System.out.println("TestCheck Success");

			output.close();
			test.close();
		} catch (FileNotFoundException e) {
			System.out.println(file + "Output.txt not found");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("TestCheck FAILED");
			e.printStackTrace();
		}
	}

	/**
	 * Read the test input from input.txt, write the test output to output.txt.
	 *
	 * @param console The console from which the data will be read.
	 * @param filename The name of the test which needs to be ran.
	 */
	public void TestMenu(Scanner console, String filename) {
		if (filename.isBlank()) {
			try {
				// List tests
				ArrayList<String> lst = new ArrayList<String>();
				try {
					// get testlist
					File testListFile = new File("tests/TestList.txt");
					Scanner testList = new Scanner(testListFile);

					while (testList.hasNextLine()) {
						lst.add(testList.nextLine());
						int size = lst.size();
						System.out.println(
								size + "\t" + lst.get(size - 1) + "\n-------------------------------------------");
					}

					testList.close();
				} catch (FileNotFoundException e) {
					System.out.println("TestList.txt not found");
					e.printStackTrace();
				}

				boolean end = false;
				Scanner in = console;

				// exit from test mode on "exit\n"
				while (!end) {
					// next line of the console
					String nl = in.nextLine();
					if (nl.equals("exit"))
						end = true;
					else if (nl.equals("all")) {
						for (String test : lst) {
							System.out.print(test + ": ");
							RunTest(test);
						}
					} else {
						try {
							int num = Integer.parseInt(nl);
							if (num > 0 && num <= lst.size())
								RunTest(lst.get(num - 1));
						} catch (NumberFormatException e) {
							System.out.println("Invalid number!");
						}
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Index out of range!");
			}
		} else {
			RunTest(filename);
		}
	}

	/**
	 * The method, that runs the test received as a parameter.
	 * 
	 * @param test That we want to run
	 */
	public void RunTest(String test) {
		// sets print stream to file
		try {
			File inputFile = new File("tests/" + test + ".txt");
			Scanner input = new Scanner(inputFile);
			Read(input, new PrintStream(new File("output.txt")));
			input.close();

		} catch (FileNotFoundException e) {
			System.out.println(test + ".txt not found");
			e.printStackTrace();
		}
		TestCheck(test);
	}

	/**
	 * Executes the received command.
	 * 
	 * @param cmd The command we want to execute.
	 */
	void ExecuteCommand(String cmd) {
		try {
			String[] command = cmd.split("[(]", 0);
			command[1] = command[1].replace(')', ' ');
			command[1] = command[1].strip();
			String[] arguments = command[1].split(",", 0);
			// we check what every command has as arguments if it isn
			if (arguments[0].toLowerCase().contains("pipe"))
				PipeHandler(cmd);
			else if (arguments[0].toLowerCase().contains("pump"))
				PumpHandler(cmd);
			else if (arguments[0].toLowerCase().contains("cistern"))
				CisternHandler(cmd);
			else if (arguments[0].toLowerCase().contains("spring"))
				SpringHandler(cmd);
			else if (arguments[0].toLowerCase().contains("plumber"))
				PlumberHandler(cmd);
			else if (arguments[0].toLowerCase().contains("saboteur"))
				SaboteurHandler(cmd);
			else
				GameHandler(cmd);
		} catch (Exception e) {
			// If any exception gets here we assume the command failed
			out.println(cmd + " FAILED");
			e.printStackTrace();
		}
	}

	/**
	 * Handles all the possible commands, that can be executed with a plumber.
	 * 
	 * @param cmd The command we want to execute
	 * @throws NumberFormatException
	 */
	void PlumberHandler(String cmd) throws NumberFormatException {
		// Splits up the command into its parts
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);

		// Creates a plumber
		if (command[0].equals("Create")) {
			Plumber newPlumber = new Plumber(game);
			out.println(cmd + " Success Plumber" + newPlumber.getId() + " created");
			return;
		}

		// If we are not creating a new Plumber we get the id of the plumber
		// Then we load it into the plumber variable
		// We use this plumber to execute commands
		int plumberId = 0;
		plumberId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Plumber plumber = game.getPlumber(plumberId);
		if (plumber == null) {
			out.println(cmd + "FAILED");
			return;
		}

		// Chooses command to execute
		// Then if necessary checks if the command was executed successfully
		switch (command[0]) {
			case "ListParams":
				out.println(arguments[0] + ":");
				out.println();
				out.println(plumber.List());
				break;
			case "Move":
				if (plumber.PlayerMove(game.getMap().getFieldElement(arguments[1])))
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "ConnectPipe":
				if (plumber.ConnectPipe())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "DisconnectPipe":
				if (plumber.DisconnectPipe(Integer.parseInt(arguments[1].replaceAll("[\\D]", ""))))
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "TakePump":
				if (plumber.TakePump())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "PlacePump":
				if (plumber.PlacePump())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "GrabPipe":
				if (plumber.GrabPipe())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "PumpDirection":
				int pipeId1;
				int pipeId2;
				if (arguments[1].toLowerCase().equals("close"))
					pipeId1 = -1;
				else
					pipeId1 = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
				if (arguments[2].toLowerCase().equals("close"))
					pipeId2 = -1;
				else
					pipeId2 = Integer.parseInt(arguments[2].replaceAll("[\\D]", ""));

				if (plumber.PumpDirection(pipeId1, pipeId2))
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "Puncture":
				if (plumber.PuncturePipe())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "MakeSticky":
				if (plumber.MakeSticky())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "Repair":
				if (plumber.Repair())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			default:
				// A separate switch for all the setting of variables
				if (command[0].contains("Set")) {
					String attribute = command[0].substring(3);
					switch (attribute) {
						case "Location":
							FieldElement location = game.getMap().getFieldElement(arguments[1]);
							if (location == null) {
								out.println(cmd + " FAILED");
								break;
							}
							location.StepOn(plumber);
							if (plumber.getLocation() == location)
								out.println(cmd + " Success");
							else
								out.println(cmd + " FAILED");
							break;
						case "HeldPipe":
							int pipeId = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
							Pipe pipe = game.getMap().getPipe(pipeId);
							if (pipe == null) {
								out.println(cmd + " FAILED");
								break;
							}
							plumber.setHeldPipe(pipe);
							if (plumber.getHeldPipe() == pipe)
								out.println(cmd + " Success");
							else
								out.println(cmd + " FAILED");
							break;
						case "HeldPump":
							int pumpId = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
							Pump pump = game.getMap().getPump(pumpId);
							if (pump == null) {
								out.println(cmd + " FAILED");
								break;
							}
							plumber.setHeldPump(pump);
							if (plumber.getHeldPump() == pump)
								out.println(cmd + " Success");
							else
								out.println(cmd + " FAILED");
							break;
						case "Immobile":
							plumber.setImmobile(Integer.parseInt(arguments[1]));
							// this can't really fail if there isn't any integer then parseInt throws
							// an exception
							out.println(cmd + " Success");
							break;
						case "Name":
							plumber.setName(arguments[1]);
							// this can't really fail anything is a string that can be a name
							out.println(cmd + " Success");
							break;
					}
				} else
					out.println(cmd + " FAILED");
		}
	}

	/**
	 * Handles all the possible commands, that can be executed with a saboteur.
	 * 
	 * @param cmd The command we want to execute
	 * @throws NumberFormatException
	 */
	void SaboteurHandler(String cmd) throws NumberFormatException {
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);

		// Creates a Saboteur
		if (command[0].equals("Create")) {
			Saboteur newSaboteur = new Saboteur(game);
			out.println(cmd + " Success Saboteur" + newSaboteur.getId() + " created");
			return;
		}


		// If we are not creating a new Saboteur we get the id of the saboteur
		// Then we load it into the saboteur variable
		// We use this saboteur to execute commands
		int saboteurId = 0;
		saboteurId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Saboteur saboteur = game.getSaboteur(saboteurId);
		switch (command[0]) {
			case "ListParams":
				out.println(arguments[0] + ":");
				out.println();
				out.println(saboteur.List());
				break;
			case "Move":
				if (saboteur.PlayerMove(game.getMap().getFieldElement(arguments[1])))
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "PumpDirection":
				int pipeId1;
				int pipeId2;
				if (arguments[1].toLowerCase().equals("close")) {
					pipeId1 = -1;
					pipeId2 = -1;
				} else {
					pipeId1 = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
					pipeId2 = Integer.parseInt(arguments[2].replaceAll("[\\D]", ""));
				}
				if (saboteur.PumpDirection(pipeId1, pipeId2))
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "Puncture":
				if (saboteur.PuncturePipe())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "MakeSticky":
				if (saboteur.MakeSticky())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "MakeSlippery":
				if (saboteur.MakeSlippery())
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			default:
				// A separate switch for all the setting of variables
				if (command[0].contains("Set")) {
					String attribute = command[0].substring(3);
					switch (attribute) {
						case "Location":
							FieldElement location = game.getMap().getFieldElement(arguments[1]);
							if (location == null) {
								out.println(cmd + " FAILED");
								break;
							}
							location.StepOn(saboteur);
							if (saboteur.getLocation() == location)
								out.println(cmd + " Success");
							else
								out.println(cmd + " FAILED");
							break;
						case "Immobile":
							saboteur.setImmobile(Integer.parseInt(arguments[1]));
							// this can't really fail if there isn't any integer then parseInt throws an
							// exception
							out.println(cmd + " Success");
							break;
						case "Name":
							saboteur.setName(arguments[1]);
							// this can't really fail anyastring can be a name
							out.println(cmd + " Success");
							break;
					}
				} else
					out.println(cmd + " FAILED");
		}
	}

	/**
	 * Handles all the commands we can execute with a cistern.
	 * 
	 * @param cmd The command we want to execute
	 * @throws NumberFormatException
	 */
	void CisternHandler(String cmd) throws NumberFormatException {
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);

		// Creates a Cistern
		if (command[0].equals("Create")) {
			Cistern newCistern = new Cistern(game);
			out.println(cmd + " Success Cistern" + newCistern.getId() + " created");
			return;
		}

		// If we are not creating a new Cistern we get the id of the cistern
		// Then we load it into the cistern variable
		// We use this cistern to execute commands
		int cisternId = 0;
		cisternId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Cistern cistern = game.getMap().getCistern(cisternId);
		switch (command[0]) {
			case "ListParams":
				out.println(arguments[0] + ":");
				out.println();
				out.println(cistern.List());
				break;
			case "Connect":
				Pipe pipe = game.getMap().getPipe(Integer.parseInt(arguments[1].replaceAll("[\\D]", "")));
				if (cistern.Connect(pipe))
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILURE");
				break;
			case "Disconnect":
				if (cistern.Disconnect(Integer.parseInt(arguments[1].replaceAll("[\\D]", ""))) != null)
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILURE");
				break;
			default:
				out.println(cmd + " FAILURE");
		}
	}

	/**
	 * Handles all the commands we can execute with a pump.
	 * 
	 * @param cmd The command we want to execute
	 * @throws NumberFormatException
	 */
	void PumpHandler(String cmd) throws NumberFormatException {
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);


		// Creates a Pump
		if (command[0].equals("Create")) {
			Pump newPump = new Pump(game);
			out.println(cmd + " Success Pump" + newPump.getId() + " created");
			return;
		}

		// If we are not creating a new Pump we get the id of the pump
		// Then we load it into the pump variable
		// We use this pump to execute commands
		int pumpId = 0;
		pumpId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Pump pump = game.getMap().getPump(pumpId);

		if (pump == null) {
			out.println(cmd + "FAILED");
			return;
		}

		switch (command[0]) {
			case "ListParams":
				out.println(arguments[0] + ":");
				out.println();
				out.println(pump.List());
				break;
			case "Connect":
				Pipe pipe = game.getMap().getPipe(Integer.parseInt(arguments[1].replaceAll("[\\D]", "")));
				if (pump.Connect(pipe))
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILURE");
				break;
			case "Disconnect":
				if (pump.Disconnect(Integer.parseInt(arguments[1].replaceAll("[\\D]", ""))) != null)
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILURE");
				break;
			default:
				// A separate switch for all the setting of variables
				if (command[0].contains("Set")) {
					String attribute = command[0].substring(3);
					switch (attribute) {
						case "IsWorking":
							switch (arguments[1]) {
								case "true":
									pump.setIsWorking(true);
									out.println(cmd + " Success");
									break;
								case "false":
									pump.setIsWorking(false);
									out.println(cmd + " Success");
									break;
								default:
									out.println(cmd + " FAILED");
									break;
							}
							break;
						case "Input":
							int pipeId1 = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
							if (pump.setInput(pipeId1))
								out.println(cmd + " Success");
							else
								out.println(cmd + " FAILED");
							break;
						case "Output":
							int pipeId2 = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
							if (pump.setOutput(pipeId2))
								out.println(cmd + " Success");
							else
								out.println(cmd + " FAILED");
							break;
						case "Water":
							int water = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
							pump.setWater(water);
							out.println(cmd + " Success");
							break;
						default:
							out.println(cmd + " FAILED");
							break;
					}
				} else
					out.println(cmd + " FAILED");

		}
	}

	/**
	 * Handles all the commands we can execute with a spring.
	 * 
	 * @param cmd The command we want to execute
	 * @throws NumberFormatException
	 */
	void SpringHandler(String cmd) throws NumberFormatException {
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);

		// Creates a Spring
		if (command[0].equals("Create")) {
			Spring newSpring = new Spring(game);
			out.println(cmd + " Success Spring" + newSpring.getId() + " created");
			return;
		}

		// If we are not creating a new Spring we get the id of the spring
		// Then we load it into the spring variable
		// We use this spring to execute commands
		int springId = 0;
		springId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Spring spring = game.getMap().getSpring(springId);
		switch (command[0]) {
			case "ListParams":
				out.println(arguments[0] + ":");
				out.println();
				out.println(spring.List());
				break;
			case "Connect":
				Pipe pipe = game.getMap().getPipe(Integer.parseInt(arguments[1].replaceAll("[\\D]", "")));
				if (spring.Connect(pipe))
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILURE");
				break;
			case "Disconnect":
				if (spring.Disconnect(Integer.parseInt(arguments[1].replaceAll("[\\D]", ""))) != null)
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILURE");
				break;
			default:
		}
	}

	/**
	 * Handles all the commands we can execute with a pipe.
	 * 
	 * @param cmd The command we want to execute
	 * @throws NumberFormatException
	 */
	void PipeHandler(String cmd) throws NumberFormatException {
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
		
		// Creates a Pipe
		if (command[0].equals("Create")) {
			Pipe newPipe = new Pipe(game);
			out.println(cmd + " Success Pipe" + newPipe.getId() + " created");
			return;
		}

		// If we are not creating a new Pipe we get the id of the pipe
		// Then we load it into the pipe variable
		// We use this pipe to execute commands
		int pipeId = 0;
		pipeId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Pipe pipe = game.getMap().getPipe(pipeId);

		if (command[0].equals("ListParams") && pipe != null) {
			out.println(arguments[0] + ":");
			out.println();
			out.println(pipe.List());
			return;
		}
		// A separate switch for all the setting of variables
		if (command[0].contains("Set") && pipe != null) {
			String attribute = command[0].substring(3);
			switch (attribute) {
				case "IsPunctured":
					switch (arguments[1]) {
						case "true":
							pipe.setIsPunctured(true);
							out.println(cmd + " Success");
							break;
						case "false":
							pipe.setIsPunctured(false);
							out.println(cmd + " Success");
							break;
						default:
							out.println(cmd + " FAILED");
							break;
					}
					break;
				case "IsGrabbed":
					switch (arguments[1]) {
						case "true":
							pipe.setIsGrabbed(true);
							out.println(cmd + " Success");
							break;
						case "false":
							pipe.setIsGrabbed(false);
							out.println(cmd + " Success");
							break;
						default:
							out.println(cmd + " FAILED");
							break;
					}
					break;
				case "State":
					switch (arguments[1]) {
						case "Normal":
							pipe.setState(PipeSurfaceState.Normal);
							out.println(cmd + " Success");
							break;
						case "Slippery":
							pipe.setState(PipeSurfaceState.Slippery);
							out.println(cmd + " Success");
							break;
						case "Sticky":
							pipe.setState(PipeSurfaceState.Sticky);
							out.println(cmd + " Success");
							break;
						default:
							out.println(cmd + " FAILED");
							break;
					}
					break;
				case "Duration":
					pipe.setDuration(Integer.parseInt(arguments[1]));
					out.println(cmd + " Success");
					break;
				case "Capactiy":
					pipe.setCapacity(Integer.parseInt(arguments[1]));
					out.println(cmd + " Success");
					break;
				case "Unpuncturable":
					pipe.setUnpuncturable(Integer.parseInt(arguments[1]));
					out.println(cmd + " Success");
					break;
				case "Water":
					pipe.setWater(Integer.parseInt(arguments[1]));
					out.println(cmd + " Success");
					break;
			}
		} else
			out.println(cmd + " FAILED");

	}

	/**
	 * Handles all the commands we can execute with the game object.
	 * 
	 * @param cmd The command we want to execute
	 * @throws NumberFormatException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	void GameHandler(String cmd) throws NumberFormatException, ClassNotFoundException, IOException {
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);

		// Creates a new game and sets it as the game of the CommandHandler
		if (command[0].equals("Create")) {
			game = new Game();
			out.println(cmd + " Success Game created");
			return;
		}


		switch (command[0]) {
			case "CreateGame":
				game = new Game();
				if (game != null)
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case "LoadGame":
				LoadGame(arguments[0]);
				break;
			case "SaveGame":
				SaveGame(arguments[0]);
				break;
			case "Tick":
				game.Tick();
				// there is no way to check this command so it just writes success every time
				out.println(cmd + " Success");
				break;
			case "ListParams":
				out.println(arguments[0] + ":");
				out.println();
				out.println(game.List());
				break;
			case "Random":
				game.setTester((arguments[0].equals("true") ? 1 : 0));
				out.println(cmd + " Success");
				break;
			default:
				// A separate switch for all the setting of variables
				if (command[0].contains("Set")) {
					String attribute = command[0].substring(3);
					switch (attribute) {
						case "spilledWater":
							int sWater = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
							game.setSpilledWater(sWater);
							out.println(cmd + " Success");
							break;
						case "collectedWater":
							int cWater = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
							game.setSpilledWater(cWater);
							out.println(cmd + " Success");
							break;
						case "remainingRounds":
							int remaining = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
							game.setRemainingRounds(remaining);
							out.println(cmd + " Success");
							break;
					}
				}
		}
	}

	/**
	 * Get a reference to the currently running game.
	 * 
	 * @return game This game instance
	 */
	public Game getGame() {
		return game;
	}

	public void setGame(Game g) {
		game = g;
	}
	
	/**
	 * The method, that reads the input files we want to use to run the tests
	 */
	public void Read(Scanner input, PrintStream output) {
		// new game
		game = null;

		PrintStream temp = out;
		out = output;

		// reset the static variables
		Cistern.setNextId(1);
		Pipe.setNextId(1);
		Plumber.setNextId(1);
		Pump.setNextId(1);
		Saboteur.setNextId(1);
		Spring.setNextId(1);

		while (input.hasNextLine()) {
			String line = input.nextLine();
			if (!line.isBlank()) {
				ExecuteCommand(line);
			}
		}
		out = temp;
	}

	/**
	 * It sets the output stream to the str given as patameter
	 * @param str the PrintStrem we set the output to
	 */

	public void setOutput(PrintStream str) {
		out = str;
	}
}
