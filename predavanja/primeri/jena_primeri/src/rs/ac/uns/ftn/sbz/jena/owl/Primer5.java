package rs.ac.uns.ftn.sbz.jena.owl;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.AllValuesFromRestriction;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.ontology.SomeValuesFromRestriction;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Primer5 {

	final static Logger logger = Logger.getLogger(Primer5.class);

	public static void main(String[] args) {
		// create the base model
		String SOURCE = "files/eswc.rdf";
		String NS = "http://www.eswc2006.org/technologies/ontology#";

		// create the reasoning model using the base
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		model.read(SOURCE);

		//select a named restriction (the restriction does not exist)
		Restriction r = model.getRestriction( NS + "theName" );
		logger.info("selected restriction: "+r);
		
		//going through all the restrictions from the model
		Iterator<Restriction> i = model.listRestrictions();
		while (i.hasNext()) {
		    r = i.next();
		    logger.info("restriction from the model: "+r);
		}
		
		OntProperty p;
		p = model.createOntProperty( NS + "hasLocation" );
		r = model.createRestriction( p );		
		//going through the list of restrictions for the given property
		//p = model.getOntProperty( NS + "hasLocation" );
		i = p.listReferringRestrictions();
		while (i.hasNext()) {
		    r = i.next();
		    logger.info("one of the restrictions for the property: "+r);
		}
			
		//creating an unnamed restriction
		p = model.createOntProperty( NS + "hasLocation" );
		r = model.createRestriction( p );
		logger.info("created restriction: "+r);
		

		//a type of the restriction can be observed as a facet
		OntClass c = model.createClass(NS+"OrganisedEvent");
		logger.info("OrganizedEvent class: "+c);
		SomeValuesFromRestriction svfr = r.convertToSomeValuesFromRestriction(c);
		logger.info("someValuesFrom restriction: "+svfr);
		model.write(System.out);
		
		//creating a restriction from the scratch
		p = model.createObjectProperty( NS + "forEvent" );
		c = model.createClass( NS + "Event" );

		// null denotes the URI in an anonymous restriction
		AllValuesFromRestriction avf = model.createAllValuesFromRestriction( null, p, c );
		logger.info("Restriction forEvent allValuesFrom Event: "+avf);
		c = model.createClass(NS+"Call");
		c.addSuperClass(avf);
		//restriction is one of the superclasses
		logger.info("superclasses:");
		Iterator<OntClass> i1 = c.listSuperClasses();
		while (i1.hasNext()) {
			OntClass x = i1.next();
			//show restrictions
			if(x.isRestriction()){
				logger.info("superclass: : "+x);
			}
		}
	}
	
}
