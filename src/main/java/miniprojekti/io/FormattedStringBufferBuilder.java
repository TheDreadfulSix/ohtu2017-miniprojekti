package miniprojekti.io;

import miniprojekti.domain.Reference;

import java.util.Collection;

/**
 * Class for building bib formatter StringBuffer that can be written out
 */
public class FormattedStringBufferBuilder {

    private final BibReferenceFormatter formatter;

    public FormattedStringBufferBuilder(BibReferenceFormatter bfr) {
        formatter = bfr;
    }

    /**
     * Method goes through the references, formats them with given Formatter and appends to Formatter's StringBuilder
     *
     * @param references Collection of articles to append to StringBuffer
     * @return
     */
    public StringBuilder formatReferences(Collection<Reference> references) {
        references.forEach((reference) -> formatter.formatReference(reference));
        return formatter.getStringBuffer();
    }

}
