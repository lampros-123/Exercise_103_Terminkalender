
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
    
    public int add(Appointment appointment) {
        int idx;
        for (idx = 0; idx < appointments.size(); idx++) {
            if(appointments.get(idx).getDate().isAfter(appointment.getDate()))
                break;
        }
        appointments.add(idx, appointment);
        fireIntervalAdded(this, idx, idx);
        return idx;
    }
    
    public void remove(int[] indices) {
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            appointments.remove(idx - i);
        }
        fireIntervalRemoved(this, indices[0], indices[indices.length-1]);
    }
    
    public int update(int index, Appointment updated) {
        appointments.remove(index);
        fireIntervalRemoved(this, index, index);
        return add(updated);
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
                add((Appointment) app);
            }
        } catch(EOFException e) { }
        ois.close();
   }
}
