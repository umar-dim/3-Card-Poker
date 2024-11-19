import java.io.Serializable;

public class card implements Serializable {
    public String suit;
    public String value;
    public transient String image ;
    public card (String suit, String value, String image) {
        this.suit = suit;
        this.value = value;
        this.image = image ; 
    }
}
