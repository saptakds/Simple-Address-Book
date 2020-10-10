import java.util.List;

public class AddressBook {

	private String bookID;
	private List<Person> persons;

	public AddressBook(String bookID, List<Person> persons) {
		this.bookID = bookID;
		this.persons = persons;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
