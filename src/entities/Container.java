package entities;

public abstract class Container {
    // @NotNull
    // @Unique
    private String id;
    private double weight;

    public Container(String id, double weight) {
        this.id = id;
        this.weight = weight;
    }
}
