
/**
 * 
 * Author Swoop
 * Last update: 1/12/2020
 * 
 * 
 * Notes: 
 *  
 *  Rules: 
 *    - Each Adult gets 5 presents. They buy 6 presents. 
 *    - Children get 5 presents and buys none. 
 *  
 *  TODO:
 *   - Randomise initial array
 *   
 */

import java.io.BufferedReader;

import java.io.FileReader;
import java.util.ArrayList;


public class SecretSanta {

	public static void main(String[] args) {

		//////////////////////////////////////////////////////
		// SET VARIABLES //
		int numGiving = 6;

		ArrayList<Person> santaList = new ArrayList<Person>();
		csvReader(santaList);
		allocatePresents(santaList, numGiving);
		printCSV(santaList);
	}

	public static ArrayList<Person> csvReader(ArrayList<Person> santaList) {
		try {
			BufferedReader CSV = new BufferedReader(new FileReader("./SantaNames.csv"));
			System.out.println("***Successfully read csv file***");
			String currentLine = ""; // where we will load in lines

			// Iterates through, sets current line and detects end
			// Also stops where ",," exists from excel empty cells.
			// RULE: Assumes no blanks.

			while ((currentLine = CSV.readLine()) != null && currentLine.equals(",,") == false) {

				// santaDetails will be what we rip off the csv. The rest is for the array use.
				String[] arrInfo = currentLine.split(","); // split string into parts

				if (arrInfo.length != 3) {
					System.out.println("CSV error. This line has empty cells(excel) and thus missing information.");
					break;
				}

				Person ss = new Person();
				ss.setName(arrInfo[0]);
				ss.setType(arrInfo[1]); // which sets max presents in person.
				ss.setCat(arrInfo[2]);
				santaList.add(ss);

			}

			CSV.close(); // close off the CSV reader.

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end array list loop
		return santaList;
	}

	public static void printCSV(ArrayList<Person> santaList) {
		// readable
		System.out.println("\n === Readable Output ====");
		for (int x = 0; x < santaList.size(); x++) {
			System.out.print(
					santaList.get(x).getName() + ": " + 
					santaList.get(x).printPresents() + " "	+ 
					santaList.get(x).getType() + " " + 
					santaList.get(x).getCat() + " SS:" + 
					santaList.get(x).totalSS() + 
					" Times chosen:" + santaList.get(x).timesChosen() + 
					" Allowed:"	+ santaList.get(x).totalPresentsAllowed() + "\n\n");
		}

		System.out.println("\n === CSV ====");
		for (int x = 0; x < santaList.size(); x++) {

			if (santaList.get(x).getType().equals("a")) {
				System.out.print(
					santaList.get(x).getName() + ":," + 
					santaList.get(x).printPresents() + "\n\n");
			} else {
				System.out.print(
					santaList.get(x).getName() + ":,Guest \n\n");
			}
		}

		System.out.println("\n Whatsapp");

		String context = "";
		context += new String(Character.toChars(0x1F349));
		
		for (int x = 0; x < santaList.size(); x++) {

			if (santaList.get(x).getType().equals("a")) {
				System.out.print(
					"Hello there " + santaList.get(x).getName() + ". Your secret santa list is as follows 😊 Please remember these are secret, so copy them somewhere safe. Good luck and enjoy 😃 Your secret santa: " + 
					santaList.get(x).printPresents() + "\n\n");
			}
		}
	}

	public static ArrayList<Person> allocatePresents(ArrayList<Person> santaList, int numGiving) {

		// Clone list to record people that are full up and reduce short list
		ArrayList<Person> tallySantaList = new ArrayList<Person>();
		tallySantaList = (ArrayList<Person>) santaList.clone(); // TODO -----

		// create shortlist to apply rules to and make easier to pick number. Clone from
		// tallylist, which is shorter
		ArrayList<Person> shortList = new ArrayList<Person>();

		// shortList = constructShortList(tallySantaList);
		for (int y = 0; y < numGiving; y++) { // loop the amount of times there are presents - rounds of presents

			for (Person p : santaList) {

				if (santaList.get(santaList.indexOf(p)).getType().equals("a")) {

					shortList = (ArrayList<Person>) tallySantaList.clone();
					
					shortList = reduceList(shortList, tallySantaList.get(santaList.indexOf(p)));
					
					String varPick = giveAllocator(shortList);
					//System.out.println("Giving SS: " + varPick + " " + santaList.get(santaList.indexOf(p)).totalSS() + " to " + santaList.get(santaList.indexOf(p)).getName() + " " + santaList.get(santaList.indexOf(p)).timesChosen());
					santaList.get(santaList.indexOf(p)).giveSS(varPick);

				}

			}

		}
		return santaList;
	}

	public static ArrayList<Person> reduceList(ArrayList<Person> shorterList, Person giverP) {

		boolean flag = true; // check if the last run was clean

		int flagCount = 0; // keep track of loops for flag

		do {

			for (int x = 0; x < shorterList.size(); x++) {

				if (shorterList.get(x).getName().equals(giverP.getName())) {
					shorterList.remove(x);
					flagCount =0;
					break;
				} else if (shorterList.get(x).timesChosen() >= shorterList.get(x).totalPresentsAllowed()) {
					shorterList.remove(x);
					flagCount =0;
					break;
				} else if (giverP.getCat().equals(shorterList.get(x).getCat())) { // if same group
					shorterList.remove(x);
					flagCount =0;
					break;
				} else if (giverP.pickCheck(shorterList.get(x).getName())) { // if already picked
					shorterList.remove(x);
					flagCount =0;
					break;
				}

				flagCount++; // Measure loops for exit condition
			}

			if (flagCount >= shorterList.size()) { // escape WHILE LOOP
				flag = false;
			}

		} while (flag);

		// System.out.println(" ");
		return shorterList;
	}

	public static String giveAllocator(ArrayList<Person> presentList) {

		if (presentList.isEmpty()) {
			//System.out.println("returning ...");
			return "-";
		}

		int santaPick = 0 + (int) (Math.random() * ((presentList.size() - 1)));

		presentList.get(santaPick).picked();
		return presentList.get(santaPick).getName();
	}
}
