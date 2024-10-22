import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class LMS {
	private Scanner sc = new Scanner(System.in);
	private Library library = new Library();

	public static void main(String[] args) {
		LMS lms = new LMS();
		lms.start();
	}
	private void start() {
		mainMenu();
	}
	
	private void mainMenu() {
		System.out.println("-------MAIN MENU-----------");
		System.out.println("1. Admin mode");
		System.out.println("2. User mode");	
		int ch = sc.nextInt();
		switch(ch) {
			case 1:
				adminLog();
				break;
			case 2:
				userMenu();
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}

	private void userMenu()
	{
		
		System.out.println("1. Search a book");
		System.out.println("2. Return a book");
		int ch = sc.nextInt();
		switch(ch) {
			case 1:
				System.out.println("Name the book: ");
				String searchBookTitle = sc.next();
				Book foundBook = library.searchBookByTitle(searchBookTitle);
				if (foundBook!=null) {
					System.out.println("Book found");
				} else {
					System.out.println("Book not found");
				}
				break;
			case 2:	
				System.out.println("name the book which you want to return: ");
				int returnBookCode = sc.nextInt();
				library.returnBook(returnBookCode);
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
		userMenu();
	}

	private void adminLog()
	{
		
		System.out.print("Your username ");
		String username = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
		if(password.equals("Srijana1302")) {
			adminMenu();
		} else {
			System.out.println("Invalid crediatials");
			System.exit(0);
		}
	}

	private void adminMenu()
	{
		Admin admin = new Admin(library);
		System.out.println("------------ADMIN MENU----------------");
		System.out.println("1. Add records of a book");
		System.out.println("2. Update records of a book");
		System.out.println("3. Delete records of a book");
		int ch = sc.nextInt();
		switch(ch) {
			case 1:
				admin.addBook();
				break;
			case 2:
				admin.updateBook();
				break;
			case 3:
				admin.deleteBook();
				break;
			default:

				System.out.println("Invalid option");
				mainMenu();
				break;
		}
		adminMenu();
	}
}



class Library {
	private List<Book> books;

	public Library(){
		books = new ArrayList<>();
	}

	public void addBook(Book book) {
		books.add(book);
		printLibraryTable();
	}

	public Book searchBookByTitle(String title) {
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				return book ;
			}
		}
		return null;
	}
	
	public void returnBook(int code) {
		Book book = searchBook(code);
		if(book!=null) {
			books.remove(book);
			System.out.println("Book reurned: "+book.getTitle());
			printLibraryTable();
		} else {
			System.out.println("Book not found.");
		}
	}

	public Book searchBook(int code) {
		for(Book book : books) {
			if(book.getCode() == code) {
				return book;
			}
		}
		return null;
	}

	public void removeBook(Book book){
		books.remove(book);
		printLibraryTable();
	}

	public void printLibraryTable(){
		System.out.println("-------------------------------");
		System.out.printf("%-10s %-20s%n", "Book Code", "Book Title");
		System.out.println("--------------------------------");
		for(Book book : books) {
			System.out.printf("%-10d %-20s%n", book.getCode(), book.getTitle());
		}
		System.out.println("----------------------------------");
	}
}

class Admin { 
	private Library library;
	private Scanner sc = new Scanner(System.in);

	public Admin(Library library){
		this.library = library;
	}

	public void addBook() {
		System.out.println("Enter the book code: ");
		int code = sc.nextInt();
		System.out.println("Enter the book title: ");
		String title = sc.next();
		
		Book newBook = new Book(code, title);
		library.addBook(newBook);
		System.out.println("Book added");
	}

	public void updateBook() {
		System.out.println("Enter the book code to update");
		int code = sc.nextInt();
		Book existingBook = library.searchBook(code);

		if (existingBook!=null) {
			System.out.println("Enter the new book title");
			String newTitle =sc.next();
			
			existingBook.setTitle(newTitle);
			
			System.out.println("book updated");
		} else{
			System.out.println("Book not found");
		}
	}
	
	public void deleteBook() {
		System.out.println("Enter the code to be deleted");
		int code = sc.nextInt();
		Book bookToDelete = library.searchBook(code);

		if (bookToDelete!=null) {
			library.removeBook(bookToDelete);
			System.out.println("Book deleted");
		} else{
			System.out.println("book not found");
		}
	}
}
		
		









