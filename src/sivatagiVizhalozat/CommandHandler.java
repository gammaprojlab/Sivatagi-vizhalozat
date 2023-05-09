package sivatagiVizhalozat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;

public class CommandHandler 
{
	
	/** 
	 * The game being played/tested. 
	 * */
	private Game game;
	
	/**
	 * Load game.
	 *
	 * @param path the path of the file we are loading the game from
	 */
	void loadGame(String path)
	{
		try {
	         FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         game = (Game) in.readObject();
	         in.close();
	         fileIn.close();
	         int array[] = game.getIdArray();
	         Plumber.setNextId(array[0]);
	         Saboteur.getNextId(array[1]);
	         Cistern.getNextId(array[2]);
	         Pipe.getNextId(array[3]);
	         Pump.getNextId(array[4]);
	         Spring.getNextId(array[5]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Save game.
	 *
	 * @param path the path of the file we are saving the game to
	 */
	void saveGame(String path)
	{
		try 
		{
			int array[] = {Plumber.getNextId(), Saboteur.getNextId(), Cistern.getNextId(), Pipe.getNextId(), Pump.getNextId(), Spring.getNextId()};
			game.setIdArray(array);
			FileOutputStream fileOut =
			new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void executeCommand(String command)
	{
		
	}
	
	void plumberHandler(String command)
	{
		
	}
	
	void saboteurHandler(String command)
	{
		
	}
	
	void cisternHandler(String command)
	{
		
	}
	
	void pumpHandler(String command)
	{
		
	}
	
	void springHandler(String command)
	{
		
	}
	
	void pipeHandler(String command)
	{
		
	}
	
	void gameHandler(String command)
	{
		
	}
	
}