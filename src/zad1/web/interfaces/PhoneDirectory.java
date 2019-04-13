package zad1.web.interfaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("RedundantThrows")
public class PhoneDirectory implements PhoneDirectoryInterface {

    private Map<String, String> pbMap = new HashMap<>();

    public PhoneDirectory(File file) throws RemoteException {
        // Inicjalna zawartość książki telefonicznej
        // jest wczytywana z pliku o formacie
        //  imię  numer_telefonu
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(" +", 2);
                pbMap.put(info[0], info[1]);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    // Zwraca numer telefonu dla podanej osoby
    public String getPhoneNumber(String name) throws RemoteException {
        return pbMap.get(name);
    }

    // Dodaje nową osobę do książki
    // Wynik:
    // - true - dodana
    // - false - nie (przy próbie dodania osoby zapisanej już w książce)
    public boolean addPhoneNumber(String name, String num) throws RemoteException {
        if (pbMap.containsKey(name)) {
            return false;
        }

        pbMap.put(name, num);
        return true;
    }

    // Zastępuje numer podanej osoby nowym
    // Wynik:
    // - true (numer zastąpiony)
    // - false (nie - próba podania nowegu numeru nieistniejącej osoby)
    public boolean replacePhoneNumber(String name, String num) throws RemoteException {
        if (!pbMap.containsKey(name)) {
            return false;
        }
        pbMap.put(name, num);
        return true;
    }
}
