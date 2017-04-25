package rs.ac.uns.ftn.sbz.jena.rdf;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;

public class Primer8 {
	final static Logger logger = Logger.getLogger(Primer3.class);

	static final String inputFileName = "files/primer8.rdf";

	public static void main(String args[]) {
		// create an empty model
		Model model = ModelFactory.createDefaultModel();

		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName
					+ " not found");
		}

		// read the RDF/XML file
		model.read(in, "");

		// select all the resources with a VCARD.FN property
		// whose value ends with "Smith"
		StmtIterator iter = model.listStatements(new SimpleSelector(null,
				VCARD.FN, (RDFNode) null) {
			@Override
			public boolean selects(Statement s) {
				return s.getString().endsWith("Smith");
			}
		});
		if (iter.hasNext()) {
			logger.info("The graph contains vcards for:");
			while (iter.hasNext()) {
				logger.info("  " + iter.nextStatement().getString());
			}
		} else {
			logger.info("No Smith's were found in the graph");
		}
	}
}
