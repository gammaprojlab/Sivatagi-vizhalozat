package sivatagiVizhalozat;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommandHandler handler = new CommandHandler();
		Scanner scanner = new Scanner(System.in);
		String input;
		boolean flag = true;
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
						handler.TestMenu();
						break;
					case "2": 
						mapHandle(scanner, handler);
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
		while(true) {
    		System.out.println("Choose Map: (1-3)");
            String input = scanner.nextLine();
            try {
                int num = Integer.parseInt(input);
                switch(num) {
	                case 1:
	                	//read the first map
	                	break;
	                case 2:
	                	//read the second map
	                	break;
                	case 3:
	                	//read the third map
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

}
