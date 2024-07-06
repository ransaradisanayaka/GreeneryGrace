package lk.ijse.Greenery.entity;

public class Stock {
    private String stockId;
    private String name;
    private String qtyOnHand;
    private String type;

    public Stock(){

    }
    public Stock(String stockId,String name,String qtyOnHand,String type){
        this.stockId=stockId;
        this.name=name;
        this.qtyOnHand=qtyOnHand;
        this.type=type;
    }
    public String getStockId(){
        return stockId;
    }
   public void setStockId(String stockId){
        this.stockId=stockId;
   }
   public String getName(){
        return name;
   }
   public void setName(String name){
        this.name=name;
   }
   public String getQtyOnHand(){
        return qtyOnHand;
   }
   public void setQtyOnHand(String qtyOnHand){
        this.qtyOnHand=qtyOnHand;
   }
   public String getType(){
        return type;
   }
   public void setType(String type){
        this.type=type;
   }
@Override
public String toString(){
    return "Stock{" +
            "stockId='" + stockId + '\'' +
            ", name='" + name + '\'' +
            ", qtyOnHand='" + qtyOnHand + '\'' +
            ", type='" + type + '\'' +
            '}';

}}
