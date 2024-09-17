package com.comerce.product.entity.products;

public enum Category {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    HOME_APPLIANCES("Home Appliances"),
    BOOKS("Books"),
    TOYS("Toys"),
    FURNITURE("Furniture"),
    SPORTS("Sports");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
