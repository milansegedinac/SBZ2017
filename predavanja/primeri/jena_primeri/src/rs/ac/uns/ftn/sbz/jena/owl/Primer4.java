package rs.ac.uns.ftn.sbz.jena.owl;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.XSD;

public class Primer4 {

	final static Logger logger = Logger.getLogger(Primer4.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String NS = "http://www.eswc2006.org/technologies/ontology#";

		// create the reasoning model using the base
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		model.read(SOURCE);

		//create new properties
		DatatypeProperty subDeadline = model.getDatatypeProperty( NS + "hasSubmissionDeadline" );
		DatatypeProperty notifyDeadline = model.getDatatypeProperty( NS + "hasNotificationDeadline" );
		DatatypeProperty cameraDeadline = model.getDatatypeProperty( NS + "hasCameraReadyDeadline" );

		//retrieve deadline property form the ontology
		DatatypeProperty deadline = model.createDatatypeProperty( NS + "deadline" );
		deadline.addDomain( model.getOntClass( NS + "Call" ) );
		deadline.addRange( XSD.dateTime );

		//add new properties as subproperties
		deadline.addSubProperty( subDeadline );
		deadline.addSubProperty( notifyDeadline );
		deadline.addSubProperty( cameraDeadline );
				
		//the classes can be processed in a similar way to resources
		deadline = model.getDatatypeProperty( NS + "deadline" );
		
		//iterate through the collection of subproperties
		for (Iterator<? extends OntProperty> i = deadline.listSubProperties(); i.hasNext(); ) {
		  OntProperty c = i.next();
		  logger.info("subproperty: " + c.getURI() );
		}

	}
	
}
