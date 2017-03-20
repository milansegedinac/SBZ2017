package primeri;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import model.Item;
import model.Item.Category;
import util.KnowledgeSessionHelper;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ItemTest {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
		kieContainer = KnowledgeSessionHelper.createRuleBase();
	}
	
	@Test
    public void lowRangeItemClassificationTest() {
        kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        Item item = new Item("A", 123.0, 234.0);
        kSession.insert(item);
        int fired = kSession.fireAllRules();
        
        assertThat(1, is(fired));
        assertThat(Category.LOW_RANGE, is(item.getCategory()));
    }
    
    @Test
    public void midRangeItemClassificationTest() {
    	kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
    	kSession.getAgenda().getAgendaGroup("promotions").setFocus();
    	kSession.fireAllRules();
        Item item = new Item("B", 352.0, 234.0);
        kSession.insert(item);
        int fired = kSession.fireAllRules();
        
        assertThat(1, is(fired));
        assertThat(Category.MID_RANGE, is(item.getCategory()));
    }
    
    @Test
    public void highRangeItemClassificationTest() {
    	kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        Item item = new Item("C", 588.0, 234.0);
        kSession.insert(item);
        int fired = kSession.fireAllRules();
        
        assertThat(1, is(fired));
        assertThat(Category.HIGH_RANGE, is(item.getCategory()));
    }
    
    @Test
    public void all() {
    	kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "test-session");
        Item itemC = new Item("C", 588.0, 234.0);
        kSession.insert(itemC);
        
        Item itemB = new Item("B", 352.0, 234.0);
        kSession.insert(itemB);
        
        Item itemA = new Item("A", 123.0, 234.0);
        kSession.insert(itemA);
        
        int fired = kSession.fireAllRules();
        
        assertThat(3, is(fired));
        assertThat(Category.HIGH_RANGE, is(itemC.getCategory()));
        assertThat(Category.MID_RANGE, is(itemB.getCategory()));
        assertThat(Category.LOW_RANGE, is(itemA.getCategory()));
    }
    
    

}
