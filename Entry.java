import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.nio.file.*;

public class Entry implements Serializable{

    private String title;
    private String content;
    private LocalDateTime date = LocalDateTime.now();
    private File file;
    private transient FileOutputStream fos;
    private transient ObjectOutputStream output;

    public Entry(){
        this.date = LocalDateTime.now();
        this.title = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
    }

    public Entry(String content){
        this();
        this.content = content;
    }

    void setTitle(String title){
        this.title = title;
    }

    String getTitle(){
        return title;
    }

    File getFile(){
        return file;
    }

    String getContent(){
        return content;
    }

    void setContent(String content){
        this.content = content;
    }

    public void editTitle(String newTitle){
        StringTokenizer titleRaw = new StringTokenizer(newTitle.toLowerCase(), " ");
        StringBuilder title = new StringBuilder(titleRaw.countTokens());
        while(titleRaw.hasMoreTokens()){
            title.append(titleRaw.nextToken());
            if(titleRaw.hasMoreTokens()) {
                title.append("_");
            }
        }
        this.setTitle(title.toString());
    }

    public void writeToFile(){
        Path path = FileSystems.getDefault().getPath("entries");
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            file = FileSystems.getDefault().getPath("entries", title + ".entry").toFile();
            fos = new FileOutputStream(file);
            output = new ObjectOutputStream(fos);
            output.writeObject(this);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                fos.close();
                output.close();
            }catch (Exception ignored){}
        }
    }



    @Override
    public String toString() {
        return title;
    }
}