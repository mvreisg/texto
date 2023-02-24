package texto.model;

public class Challenge {
    
    private String originalData;
    private String wrongData;
    
    public Challenge(String originalData, String wrongData){
        this.originalData = originalData;
        this.wrongData = wrongData;
    }
    
    public boolean compare(String value){
        String newValue = value.replace("\r\n", "\n");
        String newData = originalData.replace("\r\n", "\n");
        return newData.contentEquals(newValue);
    }
    
    public String getOriginalData(){
        return this.originalData;
    }
    
    public String getWrongData(){
        return this.wrongData;
    }
    
}