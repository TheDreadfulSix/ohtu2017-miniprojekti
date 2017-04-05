package miniprojekti.io;

import miniprojekti.domain.Article;

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
     * @param articles Collection of articles to append to StringBuffer
     * @return
     */
    public StringBuilder formatReferences(Collection<Article> articles) {
        articles.forEach((article) -> formatter.formatReference(article));
        return formatter.getStringBuffer();
    }

}
