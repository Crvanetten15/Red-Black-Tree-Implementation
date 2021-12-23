//Code below was provided by the Professor (Dr. Hadong Wang)

public class MapEntry {
    private String key;
    private String value;

    public MapEntry() {
        this(null, null);
    }

    public MapEntry(String k, String v) {
        this.key = k;
        this.value = v;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    public void setKey(String key) {this.key = key;}
    public String setValue(String value) {String old = this.value; this.value = value; return old;}

    
    @Override
    public int hashCode() {
        if (key == null) return 0;
        int h = 0;
        for (int i=0; i < key.length(); i++){
            h += (int)key.charAt(i);
            h = (h << 5) | (h >>> 27);
        }
        return h;
    }
}
