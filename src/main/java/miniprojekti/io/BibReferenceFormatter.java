package miniprojekti.io;
import miniprojekti.domain.Reference;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;

/**
 * Class for formatting the references to bib format.
 */
public class BibReferenceFormatter {

    private final String bibLeftPadding = " ";
    private final StringBuilder output;

    public BibReferenceFormatter() {
        output = new StringBuilder();
    }

    /**
     * Writes a reference in bib format
     *
     * @param art Article to write out
     *
     * @return StringBuffer bib formatted reference
     */
    public void formatReference(Reference ref) {
        output.append("@" + ref.getClass().getSimpleName().toLowerCase() + "{" +  ref.getCitationKey() + ",\n");
        ref.getFieldMap().values().forEach((f) -> output.append(formatField(f)));
        closeReference();
    }

    public StringBuilder getStringBuffer() {
        return output;
    }

    /**
     * Writes a single field in bib format
     *
     * @param f field
     *
     * @return StringBuffer bib formatted key value
     */
    private StringBuffer formatField(Field f) {
        StringBuffer bibField = new StringBuffer().append(bibLeftPadding);
        bibField.append(bibLeftPadding);
        bibField.append(f.getName());
        bibField.append(" = ");
        bibField.append("{" + f.getValue() + "},\n");
        return bibField;
    }

    /**
     * Close the reference with "}"
     */
    private void closeReference() {
        output.append("}\n");
    }
}
