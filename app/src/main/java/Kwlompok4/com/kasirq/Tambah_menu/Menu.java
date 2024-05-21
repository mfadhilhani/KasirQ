package Kwlompok4.com.kasirq.Tambah_menu;

public class Menu {
    public String menuName;
    public String menuPrice;
    public String menuCost;
    public String menuVariant;
    public String imageUrl;

    public Menu() {
        // Default constructor required for calls to DataSnapshot.getValue(Menu.class)
    }

    public Menu(String menuName, String menuPrice, String menuCost, String menuVariant, String imageUrl) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCost = menuCost;
        this.menuVariant = menuVariant;
        this.imageUrl = imageUrl;
    }

    // Getters and setters (if needed)
}