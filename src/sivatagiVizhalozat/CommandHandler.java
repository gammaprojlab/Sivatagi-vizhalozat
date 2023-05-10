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
	         Pipe.setNextId(array[3]);
	         Pump.setNextId(array[4]);
	         Spring.setNextId(array[5]);
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
			int array[] = {Plumber.nextId(), Saboteur.nextId(), Cistern.nextId(), Pipe.nextId(), Pump.nextId(), Spring.nextId()};
			game.setIdArray(array);
			
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
			this.out.println("SaveGame(" + path + ") Success");

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
				//ther is no way to check this commend so it just writes success every time
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
		switch(command[0])
		{
			
		case"ConnectPipe":
			// needs a bool return to see success and then we can check with an if else statement
			plumber.ConnectPipe();
			break;
		case"DisconnectPipe":
			plumber.DisconnectPipe(Integer.parseInt(arguments[1]));
			break;
		
		}
	}
	
	void saboteurHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
	}
	
	void cisternHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
	}
	
	void pumpHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
	}
	
	void springHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
	}
	
	void pipeHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
	}
	
	void gameHandler(String cmd)
	{
		String[] command = cmd.split("[(]", 0);
		command[1] = command[1].replace(')', ' ');
		command[1] = command[1].strip();
		String[] arguments = command[1].split(",", 0);
	}
	
}
