
import java.time.LocalDateTime;

/**
 *
 * @author Matthias
 */
public class Appointment {
    
    private LocalDateTime date;
    private String text;

    public Appointment(LocalDateTime date, String text) {
        this.date = date;
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }
}
