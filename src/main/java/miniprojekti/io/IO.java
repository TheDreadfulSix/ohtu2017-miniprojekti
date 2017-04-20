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
     * Write a bib file, using the fileWriter, for the given references.
     *
     * @param articles Collection of references
     * @param filename A filename, given by the user.
     * @param path A path for the file, provided by user.
     */
    public void writeBibFile(String filename, String path, Collection<Reference> references) {
        fileWriter.setName(filename);
        fileWriter.setPath(path);
        fileWriter.writeFile(bufferBuilder.formatReferences(references).toString());

    }
}
