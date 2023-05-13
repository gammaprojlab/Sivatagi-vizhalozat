package sivatagiVizhalozat;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
						System.out.println("Start!");
						handler.executeCommand("NextPlayer()");
						GameRounds(scanner, handler);
						break;
					default: 
						System.out.println("Wrong input, try again!");
						break;
				}
				System.out.println("Choose:\n\t0. exit\n\t1. Test\n\t2. Game");
			}
		}
	}
	
	public static void mapHandle(Scanner scanner, CommandHandler handler) {
		boolean flag = true;
		while(flag) {
    		System.out.println("Choose Map: (1-3)");
            String input = scanner.nextLine();
            try {
                int num = Integer.parseInt(input);
                switch(num) {
	                case 1:
	                	//read the first map
	                	flag = false;
	                	break;
	                case 2:
	                	//read the second map
	                	flag = false;
	                	break;
                	case 3:
	                	//read the third map
                		flag = false;
	                	break;
                	default:
                		System.out.println("Invalid map!");
                		break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Not number!");
            }
        }
	}
	
	public static void ParamSetting(Scanner scanner, CommandHandler handler) {
		System.out.println("Add a number of Saboteur and Plumber (Same fo each): ");
		String input = scanner.nextLine();
		try {
            int num = Integer.parseInt(input);
            for(int i = 0; i < num; i++) {
            	input = scanner.nextLine();
            	handler.executeCommand("Create(Plumber)");
            	handler.executeCommand("SetName(Plumber" + (i+1) + "," + input +")");
            	handler.executeCommand("SetLocation(Plumber" + (i+1) + ", Cistern1)");
            	handler.executeCommand("Create(Saboteur)");
            	handler.executeCommand("SetName(Saboteur" + (i+1) + "," + input +")");
            	handler.executeCommand("SetLocation(Saboteur" + (i+1) + ", Spring1)");
            }
            System.out.println("Type a number how many round do you want: ");
            input = scanner.nextLine();
            num = Integer.parseInt(input);
            handler.executeCommand("SetremainingRounds("+ num +")");
        } catch (NumberFormatException e) {
            System.out.println("Not number!");
        }
	}
	
	public static void GameRounds(Scanner scanner, CommandHandler handler) {
		System.out.println(handler.getGame().getactivePlayer().getName() + "'s turn it is.");
		System.out.println("Choose an action (you can call for 'help' or 'list'): ");
		String input = scanner.nextLine();
		if(input.toLowerCase() == "help" && handler.getGame().getactivePlayer().getClass().getSimpleName() == "Plumber")
			System.out.println("Move\nConnectPipe\nDisconnectPipe\nTakePump\nPlacePump\nGrabPipe\nPumpDirection\nPuncture\nMakeSticky\nRepair");
		
		else if(input.toLowerCase() == "help")
			System.out.println("Move\nPumpDirection\nPuncture\nMakeSticky\nMakeSlippery");
		
		else if(input.toLowerCase() == "list")
			handler.executeCommand("ListParams("+handler.getGame().getactivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getactivePlayer().getId()) + ")");
		
		else
			handler.executeCommand(input + "(" + handler.getGame().getactivePlayer().getClass().getSimpleName() + String.valueOf(handler.getGame().getactivePlayer().getId()) + ")");
		
		handler.executeCommand("Tick()");
	}
}
