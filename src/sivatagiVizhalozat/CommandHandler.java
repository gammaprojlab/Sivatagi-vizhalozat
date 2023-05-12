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
	         FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
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
	 * Read the test input from console, write the test output to output.txt.
	 */
	public void TestMenu() {
		boolean end = false;
		int testCount = 1;
		Scanner in = new Scanner(System.in);
		//exit from test mode on "exit\n\n"
		while(!end) {
			out.println("\nTest" + testCount + ": ");
			testCount++;
			
			//the next line should not be read
			boolean noMoreLines = false;
			while(in.hasNextLine() && !noMoreLines) {
				String line = in.nextLine();
				if(line.equals("exit")) {
					end = true;
					noMoreLines = true;
				} else if(line.equals("")) {
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
			
		}
		in.close();
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
			case"Random":
				//maybe no need for game handler? unless we move this switch in there
				gameHandler(cmd);
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
		}
		catch(Exception e)
		{
			out.println(cmd + " FAILED");
			e.printStackTrace();
		}
	}
	
	// Nem ír ki még semmit csak végrehajtja a parancsokat
	
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
		//parse int only works if the entire string is numbers so we remove every non number from the input
		
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
					plumber.setLocation(location);
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
					saboteur.setLocation(location);
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
			case"Capactiy": 
				pipe.setCapacity(Integer.parseInt(arguments[1]));
				out.println(cmd + " Success");
			case"Unpuncturable":
				pipe.setUnpuncturable(Integer.parseInt(arguments[1]));
				out.println(cmd + " Success");
			case"Water": 
				pipe.setWater(Integer.parseInt(arguments[1]));
				out.println(cmd + " Success");
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
		plumberId = Integer.parseInt(arguments[0].replaceAll("[\\D]", ""));
		Plumber plumber = game.getPlumber(plumberId);
		switch(command[0])
		{
		case"ListParams":
			out.println(arguments[0]+":");
			out.println();
			out.println(game.toString());
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
