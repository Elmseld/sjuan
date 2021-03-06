package sjuan;

import java.io.Serializable;

/**
 * This class handle a card
 * @author Tobbe
 *
 */
public class Card implements Serializable {
	private int value, type;

	private transient final static String[] types = { "h", "s", "d", "c" };
	private transient final static String[] values  = { "1", "2", "3", "4", 
		"5", "6", "7", "8", "9", "10", "j", "q", "k" };

	/**
	 * the construktor creates a card
	 * @param type
	 * @param value
	 */
	public Card(int type, int value) {
		this.type=type;
		this.value=value;
	}
	/**
	 * this method returns a String of a Card Object
	 * @return returns a String of a Card Object
	 */
	public String toString() {
		return types[type] + values[value];
	}
	/**
	 * this method returns value of a Card
	 * @return value returns value of a Card
	 */
	public int getValue() {
		return value;
	}
	/**
	 * this method returns type of a Card
	 * @return type returns type of a Card
	 */
	public int getType() {
		return type;
	}
}
