package miniprojekti.io;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import miniprojekti.domain.Reference;

public class BibFileGenerator {
    
    BibFileFormatter formatter;
    
    public BibFileGenerator(BibFileFormatter formatter) {
        this.formatter = formatter;
    }
    
    public void createFile(String name, Collection<Reference> references) {
        createFile("", name, references);
    }
    
    public boolean createFile(String path, String name, Collection<Reference> references) {
        File file = newFile(path, name);
        
        String contents = formatter.generateContents(references);
        
        try {
            Files.write(contents, file, Charsets.UTF_8);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    private File newFile(String path, String name) {
        name = name.replace(".bib", "");
        
        File file = new File(path + name + ".bib");
        
        if (file.isFile()) {
            file = new File(path + name + generateTimestamp() + ".bib");
        }
        
        return file;
    }
    
    public String generateTimestamp() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(DateTimeFormatter.ofPattern("(yyyy-MM-dd HH:mm:ss)"));
    }
}
