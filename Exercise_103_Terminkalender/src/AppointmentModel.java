
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Matthias
 */
public class AppointmentModel extends AbstractListModel{
    private ArrayList<Appointment> appointments = new ArrayList<>();

    @Override
    public int getSize() {
        return appointments.size();
    }

    @Override
    public Object getElementAt(int index) {
        return appointments.get(index);
    }
    
    public void add(Appointment a) {
        appointments.add(a);
        fireIntervalAdded(this, appointments.size()-1, appointments.size()-1);
    }
}
