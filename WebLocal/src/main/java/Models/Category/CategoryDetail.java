package Models.Category;

public class CategoryDetail {
    public int id;
    public String name;

    public CategoryDetail(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
