package texto.model;

public class Challenge {
    
    private String originalData;
    private String wrongData;
    
    public Challenge(String originalData, String wrongData){
        this.originalData = originalData;
        this.wrongData = wrongData;
    }
    
    public boolean compare(String value){
        return (value.contentEquals(originalData));
    }
    
    public String getOriginalData(){
        return this.originalData;
    }
    
    public String getWrongData(){
        return this.wrongData;
    }
    
}