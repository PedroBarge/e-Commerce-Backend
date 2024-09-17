package com.comerce.product.entity.products;

public enum Category {
    ELECTRONICS("Eletronica"),
    CLOTHING("Roupa"),
    HOME_APPLIANCES("Eletrodomésticos"),
    BOOKS("Livros"),
    TOYS("Brinquedos"),
    FURNITURE("Mobilia"),
    SPORTS("Desporto");

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
