package models;

public class Category {
    private int idCategory;
    private String name;
    private String description;
    private int idVariants;

    public Category(int idCategory, String name, String description, int idVariants) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.idVariants = idVariants;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdVariants() {
        return idVariants;
    }

    public void setIdVariants(int idVariants) {
        this.idVariants = idVariants;
    }
}

