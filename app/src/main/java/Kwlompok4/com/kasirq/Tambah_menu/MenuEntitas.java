package Kwlompok4.com.kasirq.Tambah_menu;

public class MenuEntitas {
    String menuName, menuPrice, menuCost, menuVariant, imageUrl;

    public MenuEntitas() {
    }

    public MenuEntitas(String menuName, String menuPrice, String menuCost, String menuVariant, String imageUrl) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCost = menuCost;
        this.menuVariant = menuVariant;
        this.imageUrl = imageUrl;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuCost() {
        return menuCost;
    }

    public void setMenuCost(String menuCost) {
        this.menuCost = menuCost;
    }

    public String getMenuVariant() {
        return menuVariant;
    }

    public void setMenuVariant(String menuVariant) {
        this.menuVariant = menuVariant;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
