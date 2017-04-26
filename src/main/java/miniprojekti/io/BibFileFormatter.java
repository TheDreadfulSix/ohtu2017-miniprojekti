package miniprojekti.io;

import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.domain.Reference;

/**
 * Generates in .bib file contents from reference objects.
 */
public class BibFileFormatter {
    private String indent;
    private String end = "\n}\n";
    
    /**
     * Initializes the formatter with default indentation(2).
     */
    public BibFileFormatter() {
        this("  ");
    }
    
    /**
     * Initializes the formatter with given indentation.
     * 
     * @param   indent
     */
    public BibFileFormatter(String indent) {
        this.indent = indent;
    }
    
    /**
     * Generates the a .bib files contents from a given reference collection.
     * Scandic characters are escaped and abbreviations in titles are
     * preserved.
     * 
     * @param   references
     * 
     * @return .bib file contents as a string
     * 
     * @see miniprojekti.domain.Reference
     */
    public String generateContents(Collection<Reference> references) {
        String contents = references.stream().map(
                ref -> formatReference(ref)
        ).collect(Collectors.joining("\n"));

        return escapeScandicCharacters(contents);
    }
    
    private String formatReference(Reference ref) {
        Map<FieldName, Field> fields = ref.getFieldMap();
        SortedSet<FieldName> fnames = new TreeSet<>(fields.keySet());

        String start = String.format("@%s{%s,\n", ref.getClass().getSimpleName(), ref.getCitationKey());

        return fnames.stream().map(
            fname -> formatField(fields.get(fname))
        ).collect(Collectors.joining(",\n", start, end));
    }
    
    private String formatField(Field field) {
        String value = field.getValue();
        
        if (field.getName().equals(FieldName.TITLE) || field.getName().equals(FieldName.BOOKTITLE)) {
            value = escapeCapitalLetters(value);
        }
        
        return String.format(indent + "%s = {%s}", field.getName(), value);
    }
    
    private String escapeScandicCharacters(String contents) {
        return contents.replace("ö", "\\\"{o}")
                       .replace("Ö", "\\\"{O}")
                       .replace("ä", "\\\"{a}")
                       .replace("Ä", "\\\"{A}")
                       .replace("å", "\\aa")
                       .replace("Å", "\\AA");
    }
    
    private String escapeCapitalLetters(String contents) {
        String formatstring = contents.replaceAll("[A-ZÄÖÅ]", "---");
        
        for(int i=0; i < contents.length(); i++) {
            if(Character.isUpperCase(contents.charAt(i))) {
                formatstring = formatstring.replaceFirst("---", "{" + contents.charAt(i) + "}");
            }
        }
        
        return formatstring;
    }
}
