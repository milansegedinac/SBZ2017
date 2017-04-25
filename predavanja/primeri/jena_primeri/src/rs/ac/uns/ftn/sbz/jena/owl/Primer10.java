package rs.ac.uns.ftn.sbz.jena.owl;


import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class Primer10 {

	final static Logger logger = Logger.getLogger(Primer10.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String BASE = "http://www.eswc2006.org/technologies/ontology";
		
		// create the reasoning model using the base
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		model.read(SOURCE);

		Ontology ont = model.getOntology(BASE);
		ont.addImport(model.createResource("http://blablabla.org"));
		for (Iterator<OntResource> i = ont.listImports(); i.hasNext(); ) {
			  Resource r = (Resource) i.next();
			  logger.info("imported ontology: " + r.getURI() );
			}

	}
	
}
