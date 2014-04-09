package sjuan;

/**
 * This class handle cards
 * @author Tobbe
 *
 */

public class Card {

	private String value;

	/**
	 * Constructs a card
	 */
	public Card(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
