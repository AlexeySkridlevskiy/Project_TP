package alkofind;

public class Marks {
    private int Mark;
    private int UserID;
    private int AlcoholID;

    public Marks(int mark, int uID, int aID){
        this.setMark(mark);
        this.setUserID(uID);
        this.setAlcoholID(aID);
    }
    public int getMark() {
        return Mark;
    }

    public void setMark(int mark) {
        if(mark<=6 &&mark>=0)
            Mark = mark;
        else
            mark=6;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getAlcoholID() {
        return AlcoholID;
    }

    public void setAlcoholID(int alcoholID) {
        AlcoholID = alcoholID;
    }
}
