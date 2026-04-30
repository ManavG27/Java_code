import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Product interface
interface Product {
    void displayDetails();
}

// LegacyItem class
class LegacyItem {
    private String itemId;
    private String description;

    public LegacyItem(String itemId, String description) {
        this.itemId = itemId;
        this.description = description;
    }

    public void print() {
        System.out.println("Legacy Item ID: " + itemId + ", Description: " + description);
    }
}

// Adapter Pattern: ProductAdapter
class ProductAdapter implements Product {
    private LegacyItem legacyItem;

    public ProductAdapter(LegacyItem legacyItem) {
        this.legacyItem = legacyItem;
    }

    @Override
    public void displayDetails() {
        legacyItem.print();
    }
}

// NewProduct class
class NewProduct implements Product {
    private String name;

    public NewProduct(String name) {
        this.name = name;
    }

    @Override
    public void displayDetails() {
        System.out.println("New Product: " + name);
    }
}

// Singleton Pattern: InventoryManager
class InventoryManager {
    private static InventoryManager instance;
    private List<Product> inventory;

    private InventoryManager() {
        inventory = new ArrayList<>();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        inventory.add(product);
    }

    public Iterator<Product> returnInventory() {
        return inventory.iterator();
    }
}

// Main class
public class InventoryManagementSystem {
    public static void main(String[] args) {

        // Singleton instance
        InventoryManager manager = InventoryManager.getInstance();

        // Add NewProduct objects
        manager.addProduct(new NewProduct("Laptop"));
        manager.addProduct(new NewProduct("Smartphone"));

        // Add LegacyItem objects via Adapter
        LegacyItem item1 = new LegacyItem("L001", "Old Keyboard");
        LegacyItem item2 = new LegacyItem("L002", "Old Mouse");
        manager.addProduct(new ProductAdapter(item1));
        manager.addProduct(new ProductAdapter(item2));

        // Iterator Pattern: iterate through inventory
        Iterator<Product> iterator = manager.returnInventory();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            product.displayDetails();
        }
    }
}
