/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.io;

import miniprojekti.domain.Reference;
import java.util.Collection;

/**
 *
 * @author Joonas
 */
public class IO {

    private BibFileWriter fileWriter;
    private FormattedStringBufferBuilder bufferBuilder;

    public IO (BibFileWriter bfw, FormattedStringBufferBuilder rsbb) {
        fileWriter = bfw;
        bufferBuilder = rsbb;
    }

    /**
     * Write bib file for the given references
     * @param ref Collection of references
     * @param filename A filename given by the user.
     */
    public void writeBibFile(String filename, Collection<Reference> ref) {
        fileWriter.setName(filename);
        fileWriter.writeFile(bufferBuilder.formatReferences(ref).toString());
    }
}
