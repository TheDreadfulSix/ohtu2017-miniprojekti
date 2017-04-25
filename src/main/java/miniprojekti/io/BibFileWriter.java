package miniprojekti.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static miniprojekti.io.IOUtil.getCurrentTime;

/**
 * Class for creating the output file
 */
public class BibFileWriter {

    private String name;
    private String path;
    private Path file;
    private final Charset encoding;

    public BibFileWriter(String fileEncoding) {
        encoding = Charset.forName(fileEncoding);
    }

    public BibFileWriter(String fileName, String fileEncoding) {
        name = fileName;
        path = ".\\";
        file = Paths.get(".\\" + name + ".bib");
        encoding = Charset.forName(fileEncoding);
    }

    /**
     *
     * @param fileName name of the output file
     * @param path path to the output file
     * @param fileEncoding encoding used in the file
     */
    public BibFileWriter(String fileName, String path, String fileEncoding) {
        name = fileName;
        setFile();
        encoding = Charset.forName(fileEncoding);
    }

    /**
     * Writes the bib file from formatted output.
     *
     * @param output String written to file
     */
    public void writeFile(String output) {
        try {
            // If file with the same name already exists, append timestamp to filename
            if (checkFile()) {
                setName(name + getCurrentTime());
            }

            output = fixScandicCharacters(output);
            
            BufferedWriter writer = Files.newBufferedWriter(file, encoding);
            writer.write(output, 0, output.length());
            writer.close();
        } catch (IOException e) {
            System.err.format("IOEXception: %s%n", e);
        }
    }
//this may or may or be used for checking the path name as well..

    private String fixScandicCharacters(String output) {
        output = output.replaceAll("ö", "{\\\\\"o}");
        output = output.replaceAll("ä", "{\\\\\"a}");
        output = output.replaceAll("å", "{\\\\aa}");
        output = output.replaceAll("Ö", "{\\\\\"O}");
        output = output.replaceAll("Ä", "{\\\\\"A}");
        output = output.replaceAll("Å", "{\\\\AA}");
        return output;
    }

    private boolean checkFile() {
        return file.toFile().exists();
    }

    public void setName(String n) {
        name = n;
        setFile();
    }

    public void setFile() {
        file = Paths.get(path + name + ".bib");
    }

    public Path getFile() {
        return file;
    }

    public void setPath(String p) {
        path = p;
        setFile();
    }

}
