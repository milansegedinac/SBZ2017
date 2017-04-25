package rs.ac.uns.ftn.sbz.jena.owl;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.EnumeratedClass;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class Primer8 {

	final static Logger logger = Logger.getLogger(Primer8.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String NS = "http://www.eswc2006.org/technologies/ontology#";

		// create the reasoning model using the base
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		model.read(SOURCE);

		OntClass place = model.getOntClass( NS + "Place" );

		EnumeratedClass ukCountries =
		    model.createEnumeratedClass( NS + "UKCountries", null );
		ukCountries.addOneOf( place.createIndividual( NS + "england" ) );
		ukCountries.addOneOf( place.createIndividual( NS + "scotland" ) );
		ukCountries.addOneOf( place.createIndividual( NS + "wales" ) );
		ukCountries.addOneOf( place.createIndividual( NS + "northern_ireland" ) );

		for (Iterator<?> i = ukCountries.listOneOf(); i.hasNext(); ) {
		  Resource r = (Resource) i.next();
		  logger.info("UK country: " + r.getURI() );
		}
	}
	
}
