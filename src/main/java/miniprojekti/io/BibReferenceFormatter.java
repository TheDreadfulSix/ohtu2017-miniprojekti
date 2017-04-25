package miniprojekti.io;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.stream.Collectors;
import miniprojekti.domain.Reference;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;

/**
 * Class for formatting the references to bib format.
 */
public class BibReferenceFormatter {
    private final StringBuilder output;

    public BibReferenceFormatter() {
        output = new StringBuilder();
    }

    /**
     * Writes a reference in bib format
     *
     * @param ref Reference to write out
     */
    public void formatReference(Reference ref) {
        output.append(String.format("@%s{%s,\n", ref.getClass().getSimpleName().toLowerCase(), ref.getCitationKey()));
        
        output.append(
            ref.getFieldMap().values().stream().map(
                field -> String.format("  %s = {%s}", field.getName(), field.getValue())
            ).collect(Collectors.joining(",\n", "", "\n}\n"))
        );
    }

    public StringBuilder getStringBuffer() {
        return output;
    }
}
