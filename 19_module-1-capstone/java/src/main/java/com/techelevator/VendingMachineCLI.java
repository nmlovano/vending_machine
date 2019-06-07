package com.techelevator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;

public class VendingMachineCLI {
	
	VendingMachine vendOMatic = new VendingMachine();
	Log finalLog = new Log();

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT
													    };
	
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT      = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION          = "Finish Transaction";
	private static final String PURCHASE_MENU_OPTION_EXIT          = "Exit";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
															PURCHASE_MENU_OPTION_SELECT_PRODUCT,
															PURCHASE_MENU_OPTION_FINISH_TRANSACTION,
													    PURCHASE_MENU_OPTION_EXIT
													    };
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	public VendingMachineCLI(Menu menu) {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	 * @throws FileNotFoundException 
	***************************************************************************************************************************/

	public void run() throws FileNotFoundException , IOException{

		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					System.out.println("Thanks for stopping by. Enjoy your snack!");
					vendOMatic.salesReport();
					break;                    // Exit switch statement
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems() {      // static attribute used as method is not associated with specific object instance
		vendOMatic.displayStock();
	}
	
	public void purchaseItems() throws IOException {	 // static attribute used as method is not associated with specific object instance
		
		boolean done = false;
		
		BigDecimal currentBalance = new BigDecimal("0.00");
		
		do {

			System.out.println("************************************************************");
			System.out.println("Please pick an option below");
			System.out.println("(1) Feed Money");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");
			
			Scanner userInput = new Scanner(System.in);
			String value = userInput.nextLine();
			
			if(value.equals("1")) {
				currentBalance = vendOMatic.takeMoney();
				
				
				
				
			} else if (value.equals("2")) {
				//figure out how to do the $ transaction and the qty transaction.
				System.out.println("************************************************************");
				vendOMatic.productSelector();
				
//				
//				if(vendOMatic.getCurrentSelection().doubleValue() < vendOMatic.getCurrentBalance().doubleValue()) {
//
//					vendOMatic.moneyTransact();
//					System.out.println("Remaining balance: $"+ vendOMatic.getCurrentBalance());
//					
//				} else if(vendOMatic.getCurrentSelection().doubleValue() > vendOMatic.getCurrentBalance().doubleValue()) {
//					System.out.println("Not enough $. Please make another selection or add more money.");
//				}
//				
			} else if (value.equals("3")) {
				System.out.println("************************************************************");
				vendOMatic.moneyTransactComplete();
				System.out.println("\nPlease enjoy your snack(s)!!! \n");
				System.out.println("************************************************************");
				vendOMatic.makeSound();
				System.out.println("************************************************************");
								
				done = true;

				
			} else {
				System.out.println("Invalid input");
			}
			
		} while(done == false);
		

	}
	
	public static void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
}
