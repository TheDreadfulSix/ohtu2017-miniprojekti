
package miniprojekti.io;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.domain.Reference;

public class BibFileFormatter {
    private String indent;
    private String end;
    
    public BibFileFormatter() {
        this("  ", "\n}\n");
    }
    
    public BibFileFormatter(String indent, String end) {
        this.indent = indent;
        this.end = end;
    }
    
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
        return String.format(indent + "%s = {%s}", field.getName(), field.getValue());
    }
    
    private String escapeScandicCharacters(String contents) {
        return contents.replace("ö", "\\\"{o}")
                       .replace("Ö", "\\\"{O}")
                       .replace("ä", "\\\"{a}")
                       .replace("A", "\\\"{A}")
                       .replace("å", "\\aa")
                       .replace("Å", "\\AA");
    }
}
