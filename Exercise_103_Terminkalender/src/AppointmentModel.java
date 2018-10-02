
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Matthias
 */
public class AppointmentModel extends AbstractListModel<Appointment>{
    private ArrayList<Appointment> appointments = new ArrayList<>();

    @Override
    public int getSize() {
        return appointments.size();
    }

    @Override
    public Appointment getElementAt(int index) {
        return appointments.get(index);
    }
    
    public void add(Appointment a) {
        appointments.add(a);
        fireIntervalAdded(this, appointments.size()-1, appointments.size()-1);
    }
    
    public void saveAppointments(File f) throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        for (Appointment appointment : appointments) {
            oos.writeObject(appointment);
        }
        oos.close();
    }
    
    public void loadAppointements(File f) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        try {
            Object app;
            while((app = ois.readObject()) != null) {
                appointments.add((Appointment) app);
            }
        } catch(EOFException e) { }
        ois.close();
   }
}
