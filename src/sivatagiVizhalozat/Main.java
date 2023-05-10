package sivatagiVizhalozat;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommandHandler handler = new CommandHandler();
		handler.executeCommand("CreateGame()");
		handler.executeCommand("Create(Plumber)");
		handler.executeCommand("ListParams(Plumber1)");
		handler.executeCommand("ConnectPipe(Plumber1)");
		handler.executeCommand("SaveGame(save.txt)");
	}

}
