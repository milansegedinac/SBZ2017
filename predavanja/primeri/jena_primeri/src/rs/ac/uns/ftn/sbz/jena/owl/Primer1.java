package rs.ac.uns.ftn.sbz.jena.owl;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class Primer1 {

	final static Logger logger = Logger.getLogger(Primer1.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String NS = "http://www.eswc2006.org/technologies/ontology#";
		OntModel base = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
		base.read( SOURCE, "RDF/XML" );

		// create the reasoning model using the base
		OntModel inf = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF, base );

		// create a dummy paper for this example
		OntClass paper = base.getOntClass( NS + "Paper" );
		Individual p1 = base.createIndividual( NS + "paper1", paper );

		// list the asserted types
		logger.info("asserted types:");
		for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
			logger.info( p1.getURI() + " is asserted in class " + i.next() );
		}

		// list the inferred types
		p1 = inf.getIndividual( NS + "paper1" );
		logger.info("infered types");
		for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
			logger.info( p1.getURI() + " is inferred to be in class " + i.next() );
		}	
		// list the inferred types
		p1 = inf.getIndividual( NS + "paper1" );
		logger.info("asserted types, listRDFTypes(true)");
		for (Iterator<Resource> i = p1.listRDFTypes(true); i.hasNext(); ) {
			logger.info( p1.getURI() + " is asserted to be in class " + i.next() );
		}	
	}
	
}
