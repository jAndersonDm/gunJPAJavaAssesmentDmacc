/**
 * @author ${Josiah Anderson} - janderson78@dmacc.edu
 * CIS175 - Fall 2021
 * ${2/2/2022}
 */


import java.util.List;
import java.util.Scanner;
import controller.dbHelper;
import model.guns;




public class start {

	// Input scanner and the helper for inputing information into the table
	static Scanner in = new Scanner(System.in);
	static dbHelper dbHelp = new dbHelper();

	private static void addGun() {
		// add information
		System.out.print("Enter Company: ");
		String company = in.nextLine();
		System.out.print("Enter Gun: ");
		String type = in.nextLine();
		
		guns adding = new guns(company, type);
		dbHelp.enterGun(adding);

	}

	private static void delGun() {
		// Delete information
		
		allGuns();
		
		System.out.print("Enter Company to delete: ");
		String company = in.nextLine();
		
		System.out.print("Enter Gun Type to delete: ");
		String type = in.nextLine();
		
		guns delete = new guns(company, type);
		System.out.println("Information deleted, " + company + " : " + type);
		dbHelp.deleteGun(delete);
		
		

	}

	
	private static void editGun() {
		// Edit Gun Information on the table
		System.out.println("Searching by Company");
		List<guns> found;
		
		System.out.print("Company Name: ");
		String compName = in.nextLine();
		found = dbHelp.searchByCompany(compName);

		if (!found.isEmpty()) {
			System.out.println("Results:");
			for (guns f : found) {
				System.out.println(f.getId() + " : " + f.toString());
			}
			System.out.print("Select ID to change: ");
			int edit = in.nextInt();

			guns theEdit = dbHelp.searchId(edit);
			System.out.println("Got " + theEdit.getType() + " with company " + theEdit.getCompany());
			
			
			System.out.println("Update the Company - 1");
			System.out.println("Update the type/gun - 2");
			
			int ch = in.nextInt();
			in.nextLine();

			if (ch == 1) {
				System.out.print("Change company: ");
				String newCh = in.nextLine();
				theEdit.setCompany(newCh);
			}
			else if (ch == 2) {
				System.out.print("Change type: ");
				String newTy = in.nextLine();
				theEdit.setType(newTy);
			}

			dbHelp.editGun(theEdit);

		} else {
			System.out.println("Nothing found");
		}

	}

	public static void main(String[] args) {
		// Main to run the program.
		run();

	}
	
	private static void allGuns() {
		// View all information
		List<guns> gunInfo = dbHelp.showGuns();
		for(guns gun : gunInfo) {
			System.out.println(gun.gunDetails());
		}

	}

	public static void run() {
		// Main menu method
		boolean running = true;
		
		System.out.println("Company and Gun Information DB");
		
		while (running) {
			
			System.out.println("Add Company, and gun - 1");
			System.out.println("Delete gun - 2");
			System.out.println("All guns - 3");
			System.out.println("Edit guns - 4");
			System.out.println("Exit - 5");
			System.out.print("Select: ");
			
			int select = in.nextInt();
			in.nextLine();

			if (select == 1) {
				addGun();
			} 
			else if (select == 2) {
				delGun();
			} 
			else if (select == 3) {
				allGuns();
			} 
			else if (select == 4) {
				editGun();
			} 
			else {
				
				dbHelp.cleaner();
				
				System.out.println("Exiting....");
				running = false;
			}

		}

	}

	
}
