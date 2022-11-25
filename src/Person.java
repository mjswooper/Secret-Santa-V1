import java.util.Arrays;
import java.util.ArrayList;

/////////////////////////
/// Objects  ////////////
/////////////////////////

class Person {

	String ssName = "";
	String ssType = ""; // adult or child (a or c)
	String ssCat = ""; // which household or group they are in
	String arrP[] = {}; // names of those to give presents to.
	ArrayList<String> arrP2 = new ArrayList<String>();
	String arrG[]; // names of those giving to this person.
	int totalSS = 0; // total secret santas allocated
	int chosenP = 0; // total of presents receiving

	/////////////////////////////////
	// Object Functions:
	/////////////////////////////////

	public int totalSS() {
		// returns the tally of the presents this person has.
		return arrP2.size();
	}

	public int totalPresentsAllowed() {
		// returns the tally of the presents this person is allowed
		// System.out.println(ssType);
		if (getType().equals("a")) {
			return 5;
		} else if (getType().equals("c")) {
			return 5;
		} else {
			System.out.println("Issue: category not set for max presents");
			return 0;
		}
	}

	public int timesChosen() {
		return chosenP;
	}

	public void picked() {
		chosenP++;
	}

	public int numPresents() {
		return arrP.length;
	}

	
	public void giveSS(String recipient) {
		// sets present and returns true on success.
		// toGive = name of secret santa, pNumber is the number of the present (p1).
		if (recipient == null || recipient == "" ) {
			arrP2.add("-");
		} else {
			arrP2.add(recipient);
			totalSS++;
		}
	}
	

	public String printPresents() {
		StringBuilder builder = new StringBuilder();

		for (String value : arrP2) {
			if (builder.length() != 0) {
				builder.append(", ");
			}
			builder.append(value);
		}
		builder.append(".");
		return builder.toString();
	}


	public boolean pickCheck(String propPerson) {
		for (int a = 0; a < arrP2.size(); a++) {
			if (arrP2.get(a) != null)
				if (arrP2.get(a).equals(propPerson))
					return true;
		}
		return false;
	}

	public void setName(String gName) {
		ssName = gName;
	}

	public String getName() {
		return ssName;
	}

	public void setType(String gType) {
		ssType = gType;
	}

	public String getType() {
		return ssType;
	}

	public void setCat(String gCat) {
		ssCat = gCat;
	}

	public String getCat() {
		return ssCat;
	}

}
