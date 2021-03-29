package alkofind;

public class AlcoholTypes {
    private int ID;
    private String Type;

    public AlcoholTypes(int id,String type){
        this.setID(id);
        this.setType(type);
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}