package rx_java;

public class FruitsModel {
    private String fruitName;
    private int price;

    public FruitsModel(String fruitName, int price) {
        this.fruitName = fruitName;
        this.price = price;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
