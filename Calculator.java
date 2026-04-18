mport java.util.Scanner;
public class Calculator {
public int num1, num2;
public int addNums(int n1, int n2) {
return n1 + n2;
}
public int subNums(int n1, int n2) {
return n1 - n2;
}
public long multiplyNums(int n1, int n2) {
return (long) n1 * n2;
}
public float divNums(int n1, int n2) {
return (float) n1 / n2;
}
public int calcMod(int n1, int n2) {
return n1 % n2;
}
public static void main(String[] args) {
Calculator op = new Calculator();
Scanner scan = new Scanner(System.in);
int choice;
do {
System.out.println("\nEnter first number:");
op.num1 = scan.nextInt();
System.out.println("Enter second number:");
op.num2 = scan.nextInt();System.out.println("\nChoose operation:");
System.out.println("1. Addition");
System.out.println("2. Subtraction");
System.out.println("3. Multiplication");
System.out.println("4. Division");
System.out.println("5. Modulus");
System.out.println("0. Exit");
choice = scan.nextInt();
switch (choice) {
case 1:
System.out.println("Sum: " + op.addNums(op.num1, op.num2));
break;
case 2:
System.out.println("Difference: " + op.subNums(op.num1, op.num2));
break;
case 3:
System.out.println("Product: " + op.multiplyNums(op.num1, op.num2));
break;
case 4:
if (op.num2 == 0) {
System.out.println("Cannot divide by zero");
} else {
System.out.println("Division: " + op.divNums(op.num1, op.num2));
}
break;
case 5:
if (op.num2 == 0) {
System.out.println("Cannot modulus by zero");
} else {
System.out.println("Remainder: " + op.calcMod(op.num1, op.num2));
}
break;
case 0:
System.out.println("Exiting calculator...");
break;
default:
System.out.println("Invalid choice");
}
} while (choice != 0);
scan.clos
e();
}
