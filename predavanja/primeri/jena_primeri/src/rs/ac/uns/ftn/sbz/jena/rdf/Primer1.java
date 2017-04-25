package rs.ac.uns.ftn.sbz.jena.rdf;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

public class Primer1 {
	
	final static Logger logger = Logger.getLogger(Primer1.class);
	
	public static void main(String[] args) {
		// some definitions
		String personURI    = "http://somewhere/JohnSmith";
		String fullName     = "John Smith";

		// create an empty Model
		Model model = ModelFactory.createDefaultModel();

		// create the resource
		Resource johnSmith = model.createResource(personURI);

		// add the property
		johnSmith.addProperty(VCARD.FN, fullName);
		
		logger.info("John Smith object: "+johnSmith);
	}
}
