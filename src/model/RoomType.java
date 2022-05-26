package model;

public enum RoomType {
    SINGLE("1"),
    DOUBLE("2");
    public String identifier;
    private RoomType(String identifier){
    this.identifier = identifier;
    }
/*get the enum name given the enum value, reference to this stackoverflow
https://stackoverflow.com/questions/3889248/java-getting-the-enum-name-given-the-enum-value
 */
    public static RoomType retrieveLabel(String identifier){
        for (RoomType roomType: values()){
            if(roomType.identifier.equals(identifier)){
                return roomType;
            }
        }
        return null;
    }
}



