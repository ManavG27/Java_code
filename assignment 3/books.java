import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class ArrayListOfBooks {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
ArrayList<Book> bookList = new ArrayList<>();
try {
// Diary of a Wimpy Kid Books
bookList.add(new Book(1, "Diary of a Wimpy Kid", 499.99,
"ISBN001", "Fiction", "Jeff Kinney"));
bookList.add(new Book(2, "Rodrick Rules", 520.50, "ISBN002",
"Fiction", "Jeff Kinney"));
bookList.add(new Book(3, "The Last Straw", 540.75, "ISBN003",
"Fiction", "Jeff Kinney"));
bookList.add(new Book(4, "Dog Days", 560.40, "ISBN004", "Fiction",
"Jeff Kinney"));
bookList.add(new Book(5, "The Ugly Truth", 580.00, "ISBN005",
"Fiction", "Jeff Kinney"));
bookList.add(new Book(6, "Cabin Fever", 600.99, "ISBN006",
"Fiction", "Jeff Kinney"));
bookList.add(new Book(7, "The Third Wheel", 620.25, "ISBN007",
"Fiction", "Jeff Kinney"));
bookList.add(new Book(8, "Hard Luck", 640.00, "ISBN008",
"Fiction", "Jeff Kinney"));
} catch (InvalidBookException e) {
System.out.println("Error creating book: " + e.getMessage());return; // stop program if book creation fails
}
int n = 0;
try {
System.out.print("How many books (1 to " + bookList.size() + ") do
you want to calculate sum/average for? ");
n = sc.nextInt();
if (n < 1 || n > bookList.size()) {
System.out.println("Invalid number! Using all " +
bookList.size() + " books.");
n = bookList.size();
}
} catch (InputMismatchException e) {
System.out.println("Invalid input! Please enter a numeric
value.");
n = bookList.size();
}
double totalPrice = 0;
Book highest = bookList.get(0);
Book lowest = bookList.get(0);
System.out.println("\n===== Selected Book Details =====");
for (int i = 0; i < n; i++) {
Book b = bookList.get(i);
b.displayBook(b);
totalPrice += b.price;
if (b.price > highest.price) highest = b;
if (b.price < lowest.price) lowest = b;
}
double average = totalPrice / n;
System.out.println("\n===== Final Results =====");
System.out.println("Total Books Considered: " + n);
System.out.println("Total Price: " + totalPrice);
System.out.println("Average Price: " + average);
System.out.println("Highest Price Book: " + highest.title + " - " +
highest.price);
System.out.println("Lowest Price Book: " + lowest.title + " - " +
lowest.price);sc.close();
}
}
