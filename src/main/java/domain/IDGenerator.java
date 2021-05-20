package domain;

public class IDGenerator {
    private Long ID;

    public IDGenerator(Long startID){
        if ( startID == null ){
            ID = 0L;
        }
        else {
            ID = startID;
        }
    }

    public Long getID() {
        ID++;
        return ID;
    }
}
