import java.sql.*;
public class RestaurantJDBC {
static final String URL = "jdbc:mysql://localhost:3306/restaurant_db";
static final String USER = "root";
static final String PASS = "root";
public static void main(String[] args) {
try {
Connection con = DriverManager.getConnection(URL, USER, PASS);
insertRestaurants(con);
insertMenuItems(con);
System.out.println("\n--- Menu Items with Price <= 100 ---");
selectPriceLessThan100(con);
System.out.println("\n--- Menu Items from Cafe Java ---");
selectCafeJavaItems(con);
System.out.println("\n--- Updating Price <=100 to 200 ---");
updatePrices(con);
System.out.println("\n--- After Update ---");
selectAllMenu(con);
System.out.println("\n--- Deleting items starting with P ---");
deleteItems(con);
System.out.println("\n--- After Deletion ---");
selectAllMenu(con);
con.close();
} catch (Exception e) {
e.printStackTrace();
}
}
// Insert 10 Restaurants
public static void insertRestaurants(Connection con) throws
SQLException {
String query = "INSERT INTO Restaurant VALUES (?, ?, ?)";
PreparedStatement ps = con.prepareStatement(query);
for (int i = 1; i <= 10; i++) {
ps.setInt(1, i);
ps.setString(2, (i == 1) ? "Cafe Java" : "Restaurant_" + i);
ps.setString(3, "Address_" + i);
ps.executeUpdate();
}
System.out.println("Inserted 10 Restaurants");
}
// Insert 10 Menu Items
public static void insertMenuItems(Connection con) throws SQLException
{
String query = "INSERT INTO MenuItem VALUES (?, ?, ?, ?)";
PreparedStatement ps = con.prepareStatement(query);
String[] names = {"Pizza", "Pasta", "Burger", "Sandwich", "Coffee",
"Tea", "Noodles", "Paratha", "Paneer", "Pepsi"};
double[] prices = {80, 120, 90, 70, 50, 40, 110, 60, 150, 30};
for (int i = 0; i < 10; i++) {
ps.setInt(1, i + 1);
ps.setString(2, names[i]);
ps.setDouble(3, prices[i]);
ps.setInt(4, (i % 10) + 1);
ps.executeUpdate();
}
System.out.println("Inserted 10 Menu Items");
}
// Select price <= 100
public static void selectPriceLessThan100(Connection con) throws
SQLException {
String query = "SELECT * FROM MenuItem WHERE Price <= 100";
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(query);
printMenu(rs);
}
// Select items from Cafe Java
public static void selectCafeJavaItems(Connection con) throws
SQLException {
String query = "SELECT m.* FROM MenuItem m " +
"JOIN Restaurant r ON m.ResId = r.Id " +
"WHERE r.Name = 'Cafe Java'";
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(query);
printMenu(rs);
}
// Update price <=100 to 200
public static void updatePrices(Connection con) throws SQLException {
String query = "UPDATE MenuItem SET Price = 200 WHERE Price <=
100";
Statement st = con.createStatement();
int rows = st.executeUpdate(query);
System.out.println("Rows Updated: " + rows);
}
// Delete items starting with P
public static void deleteItems(Connection con) throws SQLException {
String query = "DELETE FROM MenuItem WHERE Name LIKE 'P%'";
Statement st = con.createStatement();
int rows = st.executeUpdate(query);
System.out.println("Rows Deleted: " + rows);
}
// Print all menu items
public static void selectAllMenu(Connection con) throws SQLException {
String query = "SELECT * FROM MenuItem";
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(query);
printMenu(rs);
}
// Utility method to print table
public static void printMenu(ResultSet rs) throws SQLException {
System.out.println("ID\tName\t\tPrice\tResId");
System.out.println("----------------------------------------");
while (rs.next()) {
System.out.println(
rs.getInt("Id") + "\t" +
rs.getString("Name") + "\t\t" +
rs.getDouble("Price") + "\t" +
rs.getInt("ResId")
);
}
}
}
