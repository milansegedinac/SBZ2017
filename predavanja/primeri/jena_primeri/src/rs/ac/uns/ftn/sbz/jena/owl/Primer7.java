package rs.ac.uns.ftn.sbz.jena.owl;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.HasValueRestriction;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.IntersectionClass;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class Primer7 {

	final static Logger logger = Logger.getLogger(Primer7.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String NS = "http://www.eswc2006.org/technologies/ontology#";

		// create the reasoning model using the base
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		model.read(SOURCE);
		
		// get the class references
		OntClass place = model.createClass( NS + "Place" );
		OntClass indTrack = model.createClass( NS + "IndustryTrack" );

		// get the property references
		ObjectProperty hasPart = model.createObjectProperty( NS + "hasPart" );
		ObjectProperty hasLoc = model.createObjectProperty( NS + "hasLocation" );

		// create the UK instance
		Individual uk = place.createIndividual( NS + "united_kingdom" );

		// now the anonymous restrictions
		HasValueRestriction ukLocation =
			model.createHasValueRestriction( null, hasLoc, uk );
		HasValueRestriction hasIndTrack =
			model.createHasValueRestriction( null, hasPart, indTrack );

		// finally create the intersection class
		@SuppressWarnings("unused")
		IntersectionClass ukIndustrialConf =
			model.createIntersectionClass( NS + "UKIndustrialConference",
					model.createList( new RDFNode[] {ukLocation, hasIndTrack} ) );	
		
		model.write(System.out);
	}
	
}
