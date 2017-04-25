package rs.ac.uns.ftn.sbz.jena.rdf;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

public class Primer2 {

	final static Logger logger = Logger.getLogger(Primer2.class);
	
	public static void main (String args[]) {
	        // some definitions
	        String personURI    = "http://somewhere/JohnSmith";
	        String givenName    = "John";
	        String familyName   = "Smith";
	        String fullName     = givenName + " " + familyName;

	        // create an empty model
	        Model model = ModelFactory.createDefaultModel();

	        // create the resource
	        //   and add the properties cascading style
	        Resource johnSmith  = model.createResource(personURI)
	             .addProperty(VCARD.FN, fullName)
	             .addProperty(VCARD.N, 
	                      model.createResource()
	                           .addProperty(VCARD.Given, givenName)
	                           .addProperty(VCARD.Family, familyName));
	        logger.info("john smith: "+johnSmith);
	        logger.info("john smith's full name: "+johnSmith.getProperty(VCARD.FN));
	        logger.info("john smith's given name: "+johnSmith.getProperty(VCARD.N).getProperty(VCARD.Given));
	        logger.info("john smith's family name: "+johnSmith.getProperty(VCARD.N).getProperty(VCARD.Family));
	      }
}