package miniprojekti.io;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import miniprojekti.domain.Reference;

/**
 * Generates a .bib file from reference objects using a BibFileFormatter.
 */
public class BibFileGenerator {
    BibFileFormatter formatter;
    
    /**
     * Initializes the generator with given BibFileFormatter
     * 
     * @param   formatter
     * 
     * @see miniprojekti.io.BibFileFormatter
     */
    public BibFileGenerator(BibFileFormatter formatter) {
        this.formatter = formatter;
    }
    /**
     * Creates .bib file of given name to working directory from provided
     * references.
     * 
     * @param   name
     * @param   references
     * 
     * @see miniprojekti.domain.Reference
     */
    public void createFile(String name, Collection<Reference> references) {
        createFile("", name, references);
    }
    
    /**
     * Creates .bib file of given name to given path from provided references.
     * Uses formatter to generate contents. If the file already exists a 
     * timestamp is added to the end of the filename. It is also not necessary
     * to provide the filename with a .bib extension.
     * 
     * @param   name
     * @param   references
     * 
     * @see miniprojekti.domain.Reference
     */
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
    
    /**
     * Generates a timestamp string. This method is not in a utils class as a
     * static method because it is only used by BibFileGenerator and a static
     * method cannot be 'Mocked' without Powermock library. This method is
     * public for testing purposes only.
     */
    public String generateTimestamp() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(DateTimeFormatter.ofPattern("(yyyy-MM-dd HH:mm:ss)"));
    }
}
