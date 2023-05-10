package sivatagiVizhalozat;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommandHandler handler = new CommandHandler();
		handler.executeCommand("CreateGame()");
		handler.executeCommand("Create(Plumber)");
		handler.executeCommand("Create(Pipe)");
		handler.executeCommand("Create(Pipe)");
		handler.executeCommand("ListParams(Plumber1)");
		handler.executeCommand("SetLocation(Plumber1,Pipe1)");
		handler.executeCommand("SetHeldPipe(Plumber1,Pipe2)");
		handler.executeCommand("ListParams(Plumber1)");
		handler.executeCommand("ConnectPipe(Plumber1)");
		handler.executeCommand("ListParams(Plumber1)");
		handler.executeCommand("SaveGame(save.txt)");
	}

}
