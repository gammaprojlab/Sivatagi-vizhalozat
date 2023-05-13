package sivatagiVizhalozat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class CommandHandler 
{
	
	/** 
	 * The game being played/tested. 
	 * */
	private Game game;
	
	
	
	/** The print stream. 
	 * the print stream commands use
	 * Can be reassigned to a file for testing
	 * 
	 * */
	private PrintStream out = System.out;
	
	CommandHandler()
	{
		//sets print stream to file
		try {
		File outFile = new File("output.txt");
		out = new PrintStream(outFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Load game.
	 *
	 * @param path the path of the file we are loading the game from
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	void loadGame(String path) throws IOException, ClassNotFoundException
	{
	         FileInputStream fileIn = new FileInputStream(path);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         game = (Game) in.readObject();
	         in.close();
	         fileIn.close();
	         //loads form the game every classes static nextId
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
	void saveGame(String path) throws IOException
	{
			// Saves the static id of every class to the game
			int array[] = {Plumber.nextId(), Saboteur.nextId(), Cistern.nextId(), Pump.nextId(), Spring.nextId(), Pipe.nextId()};
			game.setIdArray(array);
			
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
			this.out.println("SaveGame(" + path + ") Success");

	}
	
	/**
	 * Read the required output from test.txt, write the check result to console.
	 */
	public void TestCheck(String file) {
		try {
			File testFile = new File("tests/" + file + "Output.txt");
			Scanner test = new Scanner(testFile);
			File outputFile = new File("output.txt");
			Scanner output = new Scanner(outputFile);
			int i = 0;
			boolean testCheck = true;
			while(output.hasNextLine()) {
				i++;
				//compare the lines of the required output and the output
				String outputLine = output.nextLine();
				String testLine = test.nextLine();
				if(!outputLine.equals(testLine)) {
					testCheck = false;
					System.out.println("Row:\t\t\t" + i + "\nRequired output:\t" + testLine + "\nActual output:\t\t" + outputLine + "\n");
				}
			}
			
			if(!testCheck || test.hasNextLine()) {
				System.out.println("TestCheck FAILED");
			} else {
				System.out.println("TestCheck Success");
			}
			
			
			output.close();
			test.close();
		} catch(FileNotFoundException e) {
			System.out.println(file + "Output.txt not found");
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("TestCheck FAILED");
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the test input from input.txt, write the test output to output.txt.
	 */
	public void TestMenu(Scanner console) {
		try {
			ArrayList<String> lst = new ArrayList<String>();
		
			try {
				//get testlist
				File testListFile = new File("tests/TestList.txt");
				Scanner testList = new Scanner(testListFile);
				
				while(testList.hasNextLine()) {
					lst.add(testList.nextLine());
					int size = lst.size();
					System.out.println(size + "\t" + lst.get(size - 1) + "\n-------------------------------------------");
				}
				
				testList.close();
			} catch(FileNotFoundException e) {
				System.out.println("TestList.txt not found");
				e.printStackTrace();
			}
			
			
			boolean end = false;
			Scanner in = console;
			//exit from test mode on "exit\n"
			while(!end) {
				//next line of the console
				String nl = in.nextLine();
				if(nl.equals("exit")) {
					end = true;
				} else {
					try {
						int num = Integer.parseInt(nl);
						
						//delete the content of out
						out.close();
						//sets print stream to file
						try {
							File outFile = new File("output.txt");
							out = new PrintStream(outFile);
						}
						catch(Exception e) {
							System.out.println("output.txt not found");
							e.printStackTrace();
						}
						
						//new game
						game = null;
						
						//reset the static variables
						Cistern.setNextId(1);
						Pipe.setNextId(1);
						Plumber.setNextId(1);
						Pump.setNextId(1);
						Saboteur.setNextId(1);
						Spring.setNextId(1);
						
						try {
							File inputFile = new File("tests/" + lst.get(num - 1) + ".txt");
							Scanner input = new Scanner(inputFile);
							
							//the next line should not be read
							boolean noMoreLines = false;
							while(input.hasNextLine() && !noMoreLines) {
								String line = input.nextLine();
								
								if(line.equals("")) {
									//new game
									game = null;
									
									//reset the static variables
									Cistern.setNextId(1);
									Pipe.setNextId(1);
									Plumber.setNextId(1);
									Pump.setNextId(1);
									Saboteur.setNextId(1);
									Spring.setNextId(1);
									
									noMoreLines = true;
								} else {
									executeCommand(line);
								}
							}
							
							input.close();
						} catch(FileNotFoundException e) {
							System.out.println(lst.get(num - 1) + ".txt not found");
							e.printStackTrace();
						}
					
						TestCheck(lst.get(num - 1));
					} catch(NumberFormatException e) {
						System.out.println("Invalid number!");
					}
				}
			}
			
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Index out of range!");
		}
	}
	
	void executeCommand(String cmd)
	{
		try {
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
		//we handle a few of the commands that are to be performed on the game here
		switch(command[0])
		{
			case"CreateGame":
				game = new Game();
				if(game != null)
					out.println(cmd + " Success");
				else
					out.println(cmd + " FAILED");
				break;
			case"LoadGame":
				loadGame(arguments[0]);
				break;
			case"SaveGame":
				saveGame(arguments[0]);
				break;
			case"Tick":
				game.Tick();
				//there is no way to check this command so it just writes success every time
				out.println(cmd + " Success");
				break;
		}
		if(arguments[0].contains("Pipe"))
			pipeHandler(cmd);
		else if(arguments[0].contains("Pump"))
			pumpHandler(cmd);
		else if(arguments[0].contains("Cistern"))
			cisternHandler(cmd);
		else if(arguments[0].contains("Spring"))
			springHandler(cmd);
		else if(arguments[0].contains("Plumber"))
			plumberHandler(cmd);
		else if(arguments[0].contains("Saboteur"))
			saboteurHandler(cmd);
		else gameHandler(cmd);
		}
		catch(Exception e)
		{
			out.println(cmd + " FAILED");
			e.printStackTrace();
		}
	}
	
	void plumberHandler(String cmd) throws NumberFormatException
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
		
		
		if(command[0].equals("Create"))
		{
			Plumber newPlumber = new Plumber();
			game.addPlumber(newPlumber);
			out.println(cmd + " Success Plumber" + newPlumber.getId() + " created");
			return;
		}
		
		int plumberId = 0;
		plumberId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Plumber plumber = game.getPlumber(plumberId);
		if(plumber == null)
		{
			out.println(cmd + "FAILED");
			return;
		}
			
		switch(command[0])
		{
		case"ListParams":
			out.println(arguments[0]+":");
			out.println();
			out.println(plumber.toString());
			break;
		case"Move":
			if(plumber.PlayerMove(game.getMap().getFieldElement(arguments[1])))
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"ConnectPipe":
			// needs a bool return to see success and then we can check with an if else statement
			if(plumber.ConnectPipe())
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"DisconnectPipe":
			if(plumber.DisconnectPipe(Integer.parseInt(arguments[1].replaceAll("[\\D]", ""))))
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"TakePump":
			if(plumber.TakePump())
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"PlacePump":
			if(plumber.PlacePump())
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"GrabPipe":
			if(plumber.GrabPipe())
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"PumpDirection":
			int pipeId1;
			int pipeId2;
			if(arguments[1].equals("close") && arguments[2].equals("close"))
			{
				pipeId1 = -1;
				pipeId2 = -1;
			}
			else
			{
				pipeId1 = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
				pipeId2 = Integer.parseInt(arguments[2].replaceAll("[\\D]", ""));
			}
			if(plumber.PumpDirection(pipeId1, pipeId2))
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"Puncture":
			if(plumber.PuncturePipe())
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"MakeSticky":
			if(plumber.MakeSticky())
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		default:
			if(command[0].contains("Set"))
			{
				String attribute = command[0].substring(3);
				switch(attribute)
				{
				case"Location":
					FieldElement location = game.getMap().getFieldElement(arguments[1]);
					if(location == null)
					{
						out.println(cmd + " FAILED");
						break;
					}
					location.StepOn(plumber);
					if(plumber.getLocation() == location)
						out.println(cmd + " Success");
					else
						out.println(cmd + " FAILED");
					break;
				case"HeldPipe":
					int pipeId =Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
					Pipe pipe = game.getMap().getPipe(pipeId);
					if(pipe == null)
					{
						out.println(cmd + " FAILED");
						break;
					}
					plumber.setHeldPipe(pipe);
					if(plumber.getHeldPipe() == pipe)
						out.println(cmd + " Success");
					else
						out.println(cmd + " FAILED");
					break;
				case"HeldPump":
					int pumpId =Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
					Pump pump = game.getMap().getPump(pumpId);
					if(pump == null)
					{
						out.println(cmd + " FAILED");
						break;
					}
					plumber.setHeldPump(pump);
					if(plumber.getHeldPump() == pump)
						out.println(cmd + " Success");
					else
						out.println(cmd + " FAILED");
					break;
				case"Immobile":
					plumber.setImmobile(Integer.parseInt(arguments[1]));
					//this can't really fail if there isn't any integer then parseInt throws an exception
					out.println(cmd + " Success");
					break;
				case"Name":
					plumber.setName(arguments[1]);
					//this can't really fail anything is a string that can be a name
					out.println(cmd + " Success");
					break;
				}
			}
			else
				out.println(cmd + " FAILED");
		}
	}
	
	void saboteurHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
		
		if(command[0].equals("Create"))
		{
			Saboteur newSaboteur = new Saboteur();
			game.addSaboteur(newSaboteur);
			out.println(cmd + " Success Saboteur" + newSaboteur.getId() + " created");
			return;
		}
		
		int saboteurId = 0;
		//parse int only works if the entire string is numbers so we remove every non number from the input
		
		saboteurId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Saboteur saboteur = game.getSaboteur(saboteurId);
		switch(command[0])
		{
		case"ListParams":
			out.println(arguments[0]+":");
			out.println();
			out.println(saboteur.toString());
			break;
		case"Move":
			if(saboteur.PlayerMove(game.getMap().getFieldElement(arguments[1])))
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"PumpDirection":
			int pipeId1;
			int pipeId2;
			if(arguments[1].equals("close"))
			{
				pipeId1 = -1;
				pipeId2 = -1;
			}
			else
			{
				pipeId1 = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
				pipeId2 = Integer.parseInt(arguments[2].replaceAll("[\\D]", ""));
			}
			if(saboteur.PumpDirection(pipeId1, pipeId2))
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"Puncture":
			if(saboteur.PuncturePipe())
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"MakeSticky":
			if(saboteur.MakeSticky())
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		case"MakeSlippery":
			if(saboteur.MakeSlippery())
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILED");
			break;
		default:
			if(command[0].contains("Set"))
			{
				String attribute = command[0].substring(3);
				switch(attribute)
				{
				case"Location":
					FieldElement location = game.getMap().getFieldElement(arguments[1]);
					if(location == null)
					{
						out.println(cmd + " FAILED");
						break;
					}
					location.StepOn(saboteur);
					if(saboteur.getLocation() == location)
						out.println(cmd + " Success");
					else
						out.println(cmd + " FAILED");
					break;
				case"Immobile":
					saboteur.setImmobile(Integer.parseInt(arguments[1]));
					//this can't really fail if there isn't any integer then parseInt throws an exception
					out.println(cmd + " Success");
					break;
				case"Name":
					saboteur.setName(arguments[1]);
					//this can't really fail anything is a string that can be a name
					out.println(cmd + " Success");
					break;
				}
			}
			else
				out.println(cmd + " FAILED");
		}
		
		
		
		
	}
	
	void cisternHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
		
		if(command[0].equals("Create"))
		{
			Cistern newCistern = new Cistern(game);
			out.println(cmd + " Success Cistern" + newCistern.getId() + " created");
			return;
		}
		int cisternId = 0;
		//parse int only works if the entire string is numbers so we remove every non number from the input
		cisternId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Cistern cistern = game.getMap().getCistern(cisternId);
		switch(command[0])
		{
		case"ListParams":
			out.println(arguments[0]+":");
			out.println();
			out.println(cistern.toString());
			break;
		case"Connect":
			Pipe pipe = game.getMap().getPipe(Integer.parseInt(arguments[1].replaceAll("[\\D]", "")));
			if(cistern.Connect(pipe))
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILURE");
			break;
		case"Disconnect":
			if(cistern.Disconnect(Integer.parseInt(arguments[1].replaceAll("[\\D]", ""))) != null)
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILURE");
			break;
		default:			
			out.println(cmd + " FAILURE");
		}
	}
	
	void pumpHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
		
		if(command[0].equals("Create"))
		{
			Pump newPump = new Pump(game);
			out.println(cmd + " Success Pump" + newPump.getId() + " created");
			return;
		}
		
		int pumpId = 0;
		pumpId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Pump pump = game.getMap().getPump(pumpId);
		
		if(pump == null)
		{
			out.println(cmd + "FAILED");
			return;
		}
		
		switch(command[0])
		{
		case"ListParams":
			out.println(arguments[0]+":");
			out.println();
			out.println(pump.toString());
			break;
		case"Connect":
			Pipe pipe = game.getMap().getPipe(Integer.parseInt(arguments[1].replaceAll("[\\D]", "")));
			if(pump.Connect(pipe))
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILURE");
			break;
		case"Disconnect":
			if(pump.Disconnect(Integer.parseInt(arguments[1].replaceAll("[\\D]", ""))) != null)
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILURE");
			break;
		default:	
			if(command[0].contains("Set"))
			{
				String attribute = command[0].substring(3);
				switch(attribute)
				{
				case"IsWorking":
					switch(arguments[1]) 
					{
					case"true": 
						pump.setIsWorking(true);
						out.println(cmd + " Success");
						break;
					case"false":
						pump.setIsWorking(false);
						out.println(cmd + " Success");
						break;
					default:
						out.println(cmd + " FAILED");
						break;
					}
				case"Input":
					int pipeId1 = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
					if(pump.setInput(pipeId1))
						out.println(cmd + " Success");
					else
						out.println(cmd + " FAILED");
					break;
				case"Output":
					int pipeId2 = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
					if(pump.setOutput(pipeId2))
						out.println(cmd + " Success");
					else
						out.println(cmd + " FAILED");
					break;
				case"Water":
					int water = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));
					pump.setWater(water);
					out.println(cmd + " Success");
					break;
				default:
					out.println(cmd + " FAILED");
						
				}
			}
			else
				out.println(cmd + " FAILED");
			
		}	
	}
	
	void springHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
		
		if(command[0].equals("Create"))
		{
			Spring newSpring = new Spring(game);
			out.println(cmd + " Success Spring" + newSpring.getId() + " created");
			return;
		}
		int springId = 0;
		//parse int only works if the entire string is numbers so we remove every non number from the input
		springId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Spring spring = game.getMap().getSpring(springId);
		switch(command[0])
		{
		case"ListParams":
			out.println(arguments[0]+":");
			out.println();
			out.println(spring.toString());
			break;
		case"Connect":
			Pipe pipe = game.getMap().getPipe(Integer.parseInt(arguments[1].replaceAll("[\\D]", "")));
			if(spring.Connect(pipe))
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILURE");
			break;
		case"Disconnect":
			if(spring.Disconnect(Integer.parseInt(arguments[1].replaceAll("[\\D]", ""))) != null)
				out.println(cmd + " Success");
			else
				out.println(cmd + " FAILURE");
			break;
		default:
		}
	}
	
	void pipeHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
		
		if(command[0].equals("Create"))
		{
			Pipe newPipe = new Pipe(game);
			out.println(cmd + " Success Pipe" + newPipe.getId() + " created");
			return;
		}
		int pipeId = 0;
		
		pipeId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Pipe pipe = game.getMap().getPipe(pipeId);
		
		if(command[0].equals("ListParams"))
		{
			out.println(arguments[0]+":");
			out.println();
			out.println(pipe.toString());
			return;
		}
		if(command[0].contains("Set") && pipe != null)
		{
			String attribute = command[0].substring(3);
			switch(attribute)
			{
			case"IsPunctured":
				switch(arguments[1]) 
				{
				case"true": 
					pipe.setIsPunctured(true);
					out.println(cmd + " Success");
					break;
				case"false":
					pipe.setIsPunctured(false);
					out.println(cmd + " Success");
					break;
				default:
					out.println(cmd + " FAILED");
					break;
				}
				break;
			case"IsGrabbed":
				switch(arguments[1]) 
				{
				case"true": 
					pipe.setIsGrabbed(true);
					out.println(cmd + " Success");
					break;
				case"false":
					pipe.setIsGrabbed(false);
					out.println(cmd + " Success");
					break;
				default:
					out.println(cmd + " FAILED");
					break;
				}
				break;
			case"State":
				switch(arguments[1])
				{
				case"normal":
					pipe.setState(PipeSurfaceState.Normal);
					out.println(cmd + " Success");
					break;
				case"slippery":
					pipe.setState(PipeSurfaceState.Slippery);
					out.println(cmd + " Success");
					break;
				case"sticky":
					pipe.setState(PipeSurfaceState.Sticky);
					out.println(cmd + " Success");
					break;
				default:
					out.println(cmd + " FAILED");
					break;
				}
				break;
			case"Duration":
				pipe.setDuration(Integer.parseInt(arguments[1]));
				out.println(cmd + " Success");
				break;
			case"Capactiy": 
				pipe.setCapacity(Integer.parseInt(arguments[1]));
				out.println(cmd + " Success");
				break;
			case"Unpuncturable":
				pipe.setUnpuncturable(Integer.parseInt(arguments[1]));
				out.println(cmd + " Success");
				break;
			case"Water": 
				pipe.setWater(Integer.parseInt(arguments[1]));
				out.println(cmd + " Success");
				break;
			}
		}
		else
			out.println(cmd + " FAILED");
		
		
	}
	
	void gameHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
		if(command[0].equals("Create"))
		{
			game = new Game();
			out.println(cmd + " Success Game created");
			return;
		}
		int plumberId = 0;
		//parse int only works if the entire string is numbers so we remove every non number from the input
		/*plumberId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Plumber plumber = game.getPlumber(plumberId);*/ // Ez kell ide?? TODO
		switch(command[0])
		{
			case"ListParams":
				out.println(arguments[0]+":");
				out.println();
				out.println(game.toString());
				break;
			case "Random":
				game.setTester((arguments[0].equals("true") ? 1 : 0));
				out.println(cmd + " Success");
				break;
		default:
			if(command[0].contains("Set"))
			{
				String attribute = command[0].substring(3);
				switch(attribute)
				{
				case"spilledWater":
					int sWater = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));  //arguments[0] maybe?
					game.setSpilledWater(sWater);
					out.println(cmd + " Success");
					break;
				case"collectedWater":
					int cWater = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));  //arguments[0] maybe?
					game.setSpilledWater(cWater);
					out.println(cmd + " Success");
					break;
				case"Tester":
					int tester = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));  //arguments[0] maybe?
					game.setTester(tester);
					out.println(cmd + " Success");
					break;
				case"remainingRounds":
					int remaining = Integer.parseInt(arguments[1].replaceAll("[\\D]", ""));  //arguments[0] maybe?
					game.setRemainingRounds(remaining);
					out.println(cmd + " Success");
					break;			
				}
			}
			
		}
	}
	
}
