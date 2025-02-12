package lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm;

import javafx.scene.control.Button;

public class CartTM {
    private String productId;
    private int count;
    private double amount;
    private double unitPrice;
    private Button removeBtn;

    public CartTM(String productId, int count, double amount, double unitPrice, Button removeBtn) {
        this.productId = productId;
        this.count = count;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.removeBtn = removeBtn;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Button getRemoveBtn() {
        return removeBtn;
    }

    public void setRemoveBtn(Button removeBtn) {
        this.removeBtn = removeBtn;
    }
}

