package rs.ac.uns.ftn.sbz.jena.owl;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class Primer6 {

	final static Logger logger = Logger.getLogger(Primer6.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String NS = "http://www.eswc2006.org/technologies/ontology#";

		// create the reasoning model using the base
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		model.read(SOURCE);

/*		OntClass c0 = model.createClass( NS + "c0" );
		OntClass c1 = model.createClass( NS + "c1" );
		OntClass c2 = model.createClass( NS + "c2" );

		RDFList cs = model.createList( new OntClass[] {c0, c1, c2} );
*/		
		RDFList cs = model.createList(); // Cs is empty
		cs = cs.cons( model.createClass( NS + "c0" ) );
		cs = cs.cons( model.createClass( NS + "c1" ) );
		cs = cs.cons( model.createClass( NS + "c2" ) );
		
		logger.info("model with list of classes: ");
		model.write(System.out);
		
		logger.info( "List has " + cs.size() + " members:" );
		for (Iterator<RDFNode> i = cs.iterator(); i.hasNext(); ) {
			logger.info( i.next() );
		}
	}
	
}
