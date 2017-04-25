package rs.ac.uns.ftn.sbz.jena.rdf;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class Primer9 {

	static final String inputFileName1 = "files/primer9_1.rdf";

	static final String inputFileName2 = "files/primer9_2.rdf";

	final static Logger logger = Logger.getLogger(Primer9.class);

    public static void main (String args[]) {
        // create an empty model
        Model model1 = ModelFactory.createDefaultModel();
        Model model2 = ModelFactory.createDefaultModel();
       
        // use the class loader to find the input file
        InputStream in1 = FileManager.get().open(inputFileName1);
        if (in1 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName1 + " not found");
        }
        InputStream in2 = FileManager.get().open(inputFileName2);
        if (in2 == null) {
            throw new IllegalArgumentException( "File: " + inputFileName2 + " not found");
        }
        
        // read the RDF/XML files
        model1.read( in1, "" );
        model2.read( in2, "" );
        
        // merge the graphs
        Model model = model1.union(model2);
        
        // print the graph as RDF/XML
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println();
    }
}
