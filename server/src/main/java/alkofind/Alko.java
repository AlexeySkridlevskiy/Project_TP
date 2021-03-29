package alkofind;

public class Alko {
 
    private Long ID;
    private String Name;
    private String Creator;
    private int TypeID;
    private Double Price;

    public Alko(Long id, String name,String company, int kind, Double price){
        this.ID =id;
        this.Name =name;
        this.Creator =company;
        this.TypeID =kind;
        this.Price=price;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        this.TypeID = typeID;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        this.Creator = creator;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
