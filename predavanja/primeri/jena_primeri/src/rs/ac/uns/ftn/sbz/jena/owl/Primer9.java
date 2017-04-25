package rs.ac.uns.ftn.sbz.jena.owl;


import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Primer9 {

	final static Logger logger = Logger.getLogger(Primer9.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String NS = "http://www.eswc2006.org/technologies/ontology#";

		// create the reasoning model using the base
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		model.read(SOURCE);

		OntClass c = model.createClass( NS + "SomeClass" );

		// first way: use a call on OntModel
		@SuppressWarnings("unused")
		Individual ind0 = model.createIndividual( NS + "ind0", c );

		// second way: use a call on OntClass
		@SuppressWarnings("unused")
		Individual ind1 = c.createIndividual( NS + "ind1" );

		model.write(System.out);
	}
	
}
