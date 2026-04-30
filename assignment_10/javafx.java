mport javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;
public class RestaurantFXApp extends Application {
static final String URL = "jdbc:mysql://localhost:3306/restaurant_db";
static final String USER = "root";
static final String PASS = "root";
TableView<MenuItem> table = new TableView<>();
Connection connect() throws SQLException {
return DriverManager.getConnection(URL, USER, PASS);
}
@Override
public void start(Stage stage) {
MenuBar menuBar = new MenuBar();
// MENU: Restaurant
Menu restaurantMenu = new Menu("Restaurant");
MenuItem insertRes = new MenuItem("Insert");
MenuItem viewRes = new MenuItem("View");
restaurantMenu.getItems().addAll(insertRes, viewRes);
// MENU: MenuItem
Menu menuItemMenu = new Menu("MenuItem");
MenuItem insertItem = new MenuItem("Insert");
MenuItem viewItem = new MenuItem("View");
MenuItem updateItem = new MenuItem("Update Price <=100");
MenuItem deleteItem = new MenuItem("Delete Name starts with P");
menuItemMenu.getItems().addAll(insertItem, viewItem, updateItem,
deleteItem);
menuBar.getMenus().addAll(restaurantMenu, menuItemMenu);
// Table columns
TableColumn<MenuItem, Integer> idCol = new TableColumn<>("ID");
idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
TableColumn<MenuItem, String> nameCol = new TableColumn<>("Name");
nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
TableColumn<MenuItem, Double> priceCol = new TableColumn<>("Price");
priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
TableColumn<MenuItem, Integer> resIdCol = new TableColumn<>("ResId");
resIdCol.setCellValueFactory(new PropertyValueFactory<>("resId"));
table.getColumns().addAll(idCol, nameCol, priceCol, resIdCol);
// EVENTS
// Insert MenuItem
insertItem.setOnAction(e -> insertMenuItem());
// View MenuItem
viewItem.setOnAction(e -> loadMenuItems());
// Update
updateItem.setOnAction(e -> updatePrices());
// Delete
deleteItem.setOnAction(e -> deleteItems());
VBox root = new VBox(menuBar, table);
root.setPadding(new Insets(10));
stage.setScene(new Scene(root, 700, 500));
stage.setTitle("Restaurant Management System");
stage.show();
}
// INSERT
void insertMenuItem() {
try (Connection con = connect()) {
String sql = "INSERT INTO MenuItem VALUES (?,?,?,?)";
PreparedStatement ps = con.prepareStatement(sql);
ps.setInt(1, 11);
ps.setString(2, "NewItem");
ps.setDouble(3, 99);
ps.setInt(4, 1);
ps.executeUpdate();
showAlert("Inserted successfully");
} catch (Exception e) {
e.printStackTrace();
}
}
  / SELECT
void loadMenuItems() {
table.getItems().clear();
try (Connection con = connect()) {
ResultSet rs = con.createStatement().executeQuery("SELECT * FROM
MenuItem");
while (rs.next()) {
table.getItems().add(new MenuItem(
rs.getInt("Id"),
rs.getString("Name"),
rs.getDouble("Price"),
rs.getInt("ResId")
));
}
} catch (Exception e) {
e.printStackTrace();
}
}
// UPDATE
void updatePrices() {
try (Connection con = connect()) {
int rows = con.createStatement()
.executeUpdate("UPDATE MenuItem SET Price=200 WHERE
Price<=100");
showAlert("Updated rows: " + rows);
loadMenuItems();
} catch (Exception e) {
e.printStackTrace();
}
}
// DELETE
void deleteItems() {
try (Connection con = connect()) {
int rows = con.createStatement()
.executeUpdate("DELETE FROM MenuItem WHERE Name LIKE
'P%'");
showAlert("Deleted rows: " + rows);
loadMenuItems();
} catch (Exception e) {
e.printStackTrace();
}
}
void showAlert(String msg) {
Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setContentText(msg);
alert.show();
}
public static void main(String[] args) {
launch(args);
}
}
// MODEL CLASS
class MenuItem {
private int id;
private String name;
private double price;
private int resId;
public MenuItem(int id, String name, double price, int resId) {
this.id = id;
this.name = name;
this.price = price;
this.resId = resId;
}
public int getId() { return id; }
public String getName() { return name; }
public double getPrice() { return price; }
public int getResId() { return resId; }
}
