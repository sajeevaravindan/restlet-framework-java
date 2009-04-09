/**
 * Copyright 2005-2009 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.ext.rdf;

import java.io.IOException;
import java.io.OutputStream;

import org.restlet.data.MediaType;
import org.restlet.ext.rdf.internal.turtle.RdfTurtleParsingContentHandler;
import org.restlet.ext.rdf.internal.turtle.RdfTurtleWritingContentHandler;
import org.restlet.representation.Representation;

/**
 * Representation for RDF/Turtle documents. It knows how to serialize and
 * deserialize a {@link Graph}.
 * 
 * @author Thierry Boileau
 */
public class RdfTurtleRepresentation extends RdfRepresentation {

    /**
     * Constructor.
     * 
     * @param linkSet
     *            The given graph of links.
     */
    public RdfTurtleRepresentation(Graph linkSet) {
        super(linkSet);
        this.setMediaType(MediaType.APPLICATION_RDF_TURTLE);
    }

    /**
     * Constructor. Parses the given representation into the given graph.
     * 
     * @param rdfRepresentation
     *            The RDF Turtle representation to parse.
     * @param linkSet
     *            The graph to update.
     * @throws IOException
     */
    public RdfTurtleRepresentation(Representation rdfRepresentation,
            Graph linkSet) throws IOException {
        super(linkSet);
        new RdfTurtleParsingContentHandler(linkSet, rdfRepresentation).parse();
    }

    @Override
    public void write(OutputStream outputStream) throws IOException {
        if (getGraph() != null) {
            new RdfTurtleWritingContentHandler(getGraph(), outputStream)
                    .write();
        }
    }
}
