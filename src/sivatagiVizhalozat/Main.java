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
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		CommandHandler handler = new CommandHandler();
		String input;
		boolean flag = true;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose:\n\t0. exit\n\t1. Test\n\t2. Game");
		while(flag) {
			if(scanner.hasNextLine()) {
				input = scanner.nextLine();
				switch(input){
					case "0": 
						scanner.close();
						System.exit(0);
						flag = false;
						break;
						
					case "1": 
						handler.TestMenu(scanner);
						break;
						
					case "2": 
						mapHandle(scanner, handler);
						ParamSetting(scanner, handler);
						
						handler.getGame().setIsRunning(true);
						
						System.out.println("Start!");
						handler.getGame().NextPlayer();
						while(handler.getGame().getIsRunning()) {
							GameRounds(scanner, handler);
							handler.getGame().NextPlayer();
						}					
						break;
					case "3":
						
					default: 
						System.out.println("Wrong input, try again!");
						break;
				}
				System.out.println("Choose:\n\t0. exit\n\t1. Test\n\t2. Game");
			}
		}
	}
	
	public static void mapHandle(Scanner scanner, CommandHandler handler) {
		List<String> maps = new ArrayList<String>(); 
		try {
			maps = GetFiles("maps/");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		while(true) {
			int i = 0;
            for(String m : maps) {
            	System.out.println(++i + " " + m);
            }
			String input = scanner.nextLine();
			try {
				int num = Integer.parseInt(input);
				
				File inputFile = new File("maps/" + maps.get(num-1));
			
				try {
					Scanner in = new Scanner(inputFile);
					
					handler.Read(in, new PrintStream(OutputStream.nullOutputStream()));
					in.close();
					return;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Not number!");
			}
            
        }
	}
	
	
	public static List<String> GetFiles(String dir) throws IOException {
	    
		try (Stream<Path> stream = Files.list(Paths.get(dir)) ) {
			
	        return stream
	          .filter(file -> !Files.isDirectory(file))
	          .map(Path::getFileName)
	          .map(Path::toString)
	          .collect(Collectors.toList());
	    }
	}
	
	
	public static void ParamSetting(Scanner scanner, CommandHandler handler) {
		System.out.println("Add a number of Saboteur and Plumber (Same fo each): ");
		String input = scanner.nextLine();
		try {
            int num = Integer.parseInt(input);
            for(int i = 0; i < num; i++) {
            	System.out.println("Plumber" + (i+1) + " name: ");
            	input = scanner.nextLine();
            	handler.executeCommand("Create(Plumber)");
            	handler.executeCommand("SetName(Plumber" + (i+1) + "," + input +")");
            	handler.executeCommand("SetLocation(Plumber" + (i+1) + ",Cistern1)");
            	
            	System.out.println("Saboteur" + (i+1) + " name: ");
            	input = scanner.nextLine();
            	handler.executeCommand("Create(Saboteur)");
            	handler.executeCommand("SetName(Saboteur" + (i+1) + "," + input +")");
            	handler.executeCommand("SetLocation(Saboteur" + (i+1) + ",Spring1)");
            }
            System.out.println("Type a number how many round do you want: ");
            input = scanner.nextLine();
            num = Integer.parseInt(input);
            handler.executeCommand("SetremainingRounds(Game1,"+ num +")");
        } catch (NumberFormatException e) {
            System.out.println("Not number!");
        }
	}
	
	
	public static void GameRounds(Scanner scanner, CommandHandler handler) {
		System.out.println(handler.getGame().getActivePlayer().getName() + "'s turn it is.");
		System.out.println("Choose an action (you can call for 'help' or 'list'): ");
		boolean flag = true;
		while(flag) {
			String in = scanner.nextLine();
			String[] input1 = in.split(" ");
			if(input1[0].toLowerCase().equals("help") && handler.getGame().getActivePlayer().getClass().getSimpleName().equals("Plumber"))
				System.out.println("Move\nConnectPipe\nDisconnectPipe\nTakePump\nPlacePump\nGrabPipe\nPumpDirection\nPuncture\nMakeSticky\nRepair\nSave");
			
			else if(input1[0].toLowerCase().equals("help"))
				System.out.println("Move\nPumpDirection\nPuncture\nMakeSticky\nMakeSlippery");
			
			else if(input1[0].toLowerCase().equals("list") && input1.length > 1)
				handler.executeCommand("ListParams("+ input1[1] + ")");
			
			else if(input1[0].toLowerCase().equals("list"))
				handler.executeCommand("ListParams("+handler.getGame().getActivePlayer().getClass().getSimpleName() + handler.getGame().getActivePlayer().getId() + ")");

			else {
				flag = false;
				if(handler.getGame().getActivePlayer().getClass().getSimpleName().equals("Plumber")) {
					switch(input1[0].toLowerCase()) {
						case "move":
							handler.executeCommand("Move(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + "," + input1[1] + ")");
							break;
						case "connectpipe":
							handler.executeCommand("ConnectPipe(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "disconnectpipe":
							handler.executeCommand("DisconnectPipe(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + "," +input1[1] + ")");
							break;
						case "takepump":
							handler.executeCommand("TakePump(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "placepump":
							handler.executeCommand("PlacePump(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "grabepipe":
							handler.executeCommand("GrabPipe(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "pumpdirection":
							handler.executeCommand("PumpDiection(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + "," + input1[1] + "," + input1[2] +")");
							break;
						case "puncture":
							handler.executeCommand("Puncture(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "makesticky":
							handler.executeCommand("MakeSticky(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "repair":
							handler.executeCommand("Repair(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "save":
							handler.executeCommand("Save(" + input1[1] + ")");
							break;
						default:
							System.out.println("Invalid command, try again!");
							flag = true;
							break;
					}
				}
				
				else {
					switch (input1[0].toLowerCase()) {
						case "move":
							handler.executeCommand("Move(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + "," + input1[1] + ")");
							break;
						case "pumpdirection":
							handler.executeCommand("PumpDiection(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + "," + input1[1] + "," + input1[2] +")");
							break;
						case "puncture":
							handler.executeCommand("Puncture(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "makesticky":
							handler.executeCommand("MakeSticky(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "makeslippery":
							handler.executeCommand("MakeSlippery(" + handler.getGame().getActivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getActivePlayer().getId()) + ")");
							break;
						case "save":
							handler.executeCommand("Save(" + input1[1] + ")");
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
