import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Books implements CRUDOps {

	private List<AddressBook> addressBooks;

	public Books() {
		addressBooks = new ArrayList<AddressBook>();
		addressBooks.add(new AddressBook("123", new ArrayList<Person>()));
	}

	public static void main(String[] args) {

		Books books = new Books();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Book ID of address book: ");
		String bookID = sc.next();

		// Check if address book with given ID exist
		if (!doesAddressBookExist(books, bookID)) {
			System.out.println("Address book " + bookID + " doesn't exist!");
			System.exit(0);
		}

		System.out.println("-->Welcome to " + bookID + "<--");

		// Console based menu
		String choice;
		do {
			System.out.println("Choose an operation");
			System.out.println("1. Create person");
			System.out.println("2. Update person");
			System.out.println("3. Delete person");
			System.out.println("4. Sort by name");
			System.out.println("5. Sort by ZIP code");
			System.out.println("6. Display");
			int menuChoice = sc.nextInt();
			switch (menuChoice) {
			case 1:
				books.createPerson(bookID);
				break;
			case 2:
				books.updatePerson(bookID);
				break;
			case 3:
				books.deletePerson(bookID);
				break;
			case 4:
				books.sortByName(bookID);
				break;
			case 5:
				books.sortByZIP(bookID);
				break;
			case 6:
				books.displayBook(bookID);
				break;
			default:
				System.out.println("Invalid selection!");
				break;
			}
			System.out.println("Do you wish to continue?(y/n)");
			choice = sc.next();
		} while (choice.equals("y") || choice.equals("Y"));

		sc.close();

	}

	// Method to search the address book with given book ID
	private static boolean doesAddressBookExist(Books books, String bookID) {
		for (AddressBook b : books.addressBooks) {
			if (b.getBookID().equals(bookID)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void createPerson(String bookID) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the person ID:");
		long id = sc.nextLong();
		System.out.println("Enter the firstname:");
		String fname = sc.next();
		System.out.println("Enter the lastname:");
		String lname = sc.next();
		System.out.println("Enter the address:");
		String address = sc.next();
		System.out.println("Enter the city:");
		String city = sc.next();
		System.out.println("Enter the state:");
		String state = sc.next();
		System.out.println("Enter the zip:");
		String zip = sc.next();
		System.out.println("Enter the phoneNo:");
		String phoneNo = sc.next();
		for (AddressBook b : addressBooks) {
			if (b.getBookID().equals(bookID)) {
				b.getPersons().add(new Person(id, fname, lname, address, city, state, zip, phoneNo));
				break;
			}
		}
		System.out.println("Person added into address book successfully");
	}

	@Override
	public void updatePerson(String bookID) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the person ID:");
		long id = sc.nextLong();
		for (AddressBook b : addressBooks) {
			if (b.getBookID().equals(bookID)) {
				for (Person p : b.getPersons()) {
					if (p.getId() == id) {
						System.out.println("-->Existing data<--");
						System.out.println(p);
						System.out.println("-->Enter new data<--");
						System.out.println("Enter the firstname:");
						String fname = sc.next();
						System.out.println("Enter the lastname:");
						String lname = sc.next();
						System.out.println("Enter the address:");
						String address = sc.next();
						System.out.println("Enter the city:");
						String city = sc.next();
						System.out.println("Enter the state:");
						String state = sc.next();
						System.out.println("Enter the zip:");
						String zip = sc.next();
						System.out.println("Enter the phoneNo:");
						String phoneNo = sc.next();
						p.setFirstName(fname);
						p.setLastName(lname);
						p.setAddress(address);
						p.setCity(city);
						p.setState(state);
						p.setZip(zip);
						p.setPhoneNo(phoneNo);
						System.out.println("Person details updated!");
						return;
					}
				}
				break;
			}
		}
		System.out.println("Person with that ID doesn't exist!");
	}

	@Override
	public void deletePerson(String bookID) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the person ID:");
		long id = sc.nextLong();
		for (AddressBook b : addressBooks) {
			if (b.getBookID().equals(bookID)) {
				for (Person p : b.getPersons()) {
					if (p.getId() == id) {
						b.getPersons().remove(p);
						System.out.println("Person deleted!");
						return;
					}
				}
				break;
			}
		}
		System.out.println("Person with that ID doesn't exist!");
	}

	@Override
	public void sortByName(String bookID) {
		for (AddressBook b : addressBooks) {
			if (b.getBookID().equals(bookID)) {
				Collections.sort(b.getPersons(), new Comparator<Person>() {

					@Override
					public int compare(Person o1, Person o2) {
						return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
					}
				});
				System.out.println("Book sorted by name");
				break;
			}
		}
	}

	@Override
	public void sortByZIP(String bookID) {
		for (AddressBook b : addressBooks) {
			if (b.getBookID().equals(bookID)) {
				Collections.sort(b.getPersons(), new Comparator<Person>() {

					@Override
					public int compare(Person o1, Person o2) {
						if (Long.parseLong(o1.getZip()) <= Long.parseLong(o2.getZip())) {
							return -1;
						}
						return 1;
					}
				});
				System.out.println("Book sorted by ZIP");
				break;
			}
		}
	}

	@Override
	public void displayBook(String bookID) {
		for (AddressBook b : addressBooks) {
			if (b.getBookID().equals(bookID)) {
				for (Person p : b.getPersons()) {
					System.out.println(p);
				}
				break;
			}
		}
	}

}
