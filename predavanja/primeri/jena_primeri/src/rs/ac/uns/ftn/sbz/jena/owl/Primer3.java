package rs.ac.uns.ftn.sbz.jena.owl;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Primer3 {

	final static Logger logger = Logger.getLogger(Primer3.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String NS = "http://www.eswc2006.org/technologies/ontology#";

		// create the reasoning model using the base
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		model.read(SOURCE);

		OntClass programme = model.createClass(NS + "Programme");
		OntClass orgEvent = model.createClass( NS + "OrganisedEvent" );
		logger.info("programme: "+programme);
		logger.info("organizedEvent: "+orgEvent);

		//model before adding new property
		logger.info("model before adding hasProgramme property");
		model.write(System.out);
		
		ObjectProperty hasProgramme = model.createObjectProperty( NS + "hasProgramme" );
		
		hasProgramme.addDomain( orgEvent );
		hasProgramme.addRange( programme );
		hasProgramme.addLabel( "has programme", "en" );
	
		//model after adding the property
		logger.info("model after adding hasProgramme property");
		model.write(System.out);
	}
	
}
