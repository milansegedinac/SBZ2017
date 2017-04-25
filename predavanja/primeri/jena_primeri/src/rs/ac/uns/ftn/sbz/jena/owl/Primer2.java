package rs.ac.uns.ftn.sbz.jena.owl;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class Primer2 {

	final static Logger logger = Logger.getLogger(Primer2.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String NS = "http://www.eswc2006.org/technologies/ontology#";

		// create the reasoning model using the base
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		model.read(SOURCE);
		
		//class as a resource facet
		Resource r = model.getResource( NS + "Paper" );
		OntClass paper = r.as( OntClass.class );
		logger.info("class as a resource facet: "+paper);
		
		//class from the ontology
		paper = model.getOntClass( NS + "Paper" );
		logger.info("class from ontology: "+paper);

		//if the class does not exist, getOntClass will return null
		paper = model.getOntClass( NS + "BestPaper" );
		logger.info("nonexistent class: "+paper);
		
		//createClass will try to retrieve the class. If it does not exist it will be created
		paper = model.createClass( NS + "Paper" );
		logger.info("retieved class: "+paper);
		paper = model.createClass( NS + "BestPaper" );
		logger.info("created class: "+paper);
		
		//the created class can be anonymous
		paper = model.createClass();
		logger.info("anonymous class: "+paper);
		
		//the classes can be processed in a similar way to resources
		OntClass artefact = model.getOntClass( NS + "Artefact" );
		for (Iterator<OntClass> i = artefact.listSubClasses(); i.hasNext(); ) {
		  OntClass c = i.next();
		  logger.info("artefact: " + c.getURI() );
		}
		
	
	}
	
}
