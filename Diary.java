import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

class Diary implements Serializable{
    private Entry[] entries = new Entry[0];
    private String title;
    private FileInputStream fis;
    private ObjectInputStream input;

    Diary(){
        try{
            File path = Paths.get("").resolve("entries").toFile();
            File[] files = path.listFiles();
            if(files != null) {
                entries = new Entry[files.length];
                for (int i = 0; i < files.length; i++) {
                    fis = new FileInputStream(files[i]);
                    input = new ObjectInputStream(fis);
                    entries[i] = (Entry) input.readObject();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                fis.close();
                input.close();
            }catch (Exception ignored){}
        }
    }

    public void addEntry(Entry newEntry){
        this.entries = Arrays.copyOf(entries, entries.length + 1);
        entries[entries.length - 1] = newEntry;
    }

    public Entry[] getEntries(){
        return entries;
    }

}
