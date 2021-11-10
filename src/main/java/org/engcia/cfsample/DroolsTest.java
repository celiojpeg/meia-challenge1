package org.engcia.cfsample;

//import org.drools.core.rule.builder.dialect.asm.ClassGenerator;
import org.kie.api.KieServices;
//import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.engcia.cf.listeners.TrackingAgendaListener;
import org.engcia.cf.model.Hypothesis;
import org.engcia.cf.model.Evidence;
import org.engcia.cf.listeners.FactListener;


/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
        	// Agenda listener

        	kSession.addEventListener(new TrackingAgendaListener());
        	
        	// Facts listener
        	kSession.addEventListener(new FactListener());

            // go !
            
            kSession.insert(new Evidence(0.50, Evidence.PRECO_PRIORITARIO, "true"));
            //kSession.insert(new Evidence(0.25, Evidence.CONSUMO_PRIORITARIO, "true"));
            //kSession.insert(new Evidence(0.25, Evidence.DESIGN_PRIORITARIO, "true"));
            //kSession.insert(new Evidence(1.00, Evidence.PRECO_MAIS_10K, "true"));
            //kSession.insert(new Evidence(-1.00, Evidence.PRECO_MENOS_10K, "true"));

            kSession.insert(new Hypothesis(0.00, "prioridade_preco", "true"));
            //kSession.insert(new Hypothesis(0.00, "prioridade_consumo", "true"));
            //kSession.insert(new Hypothesis(0.00, "prioridade_design", "true"));


            kSession.insert(new Evidence(0.50, Evidence.QUAL_O_PRECO, "false"));
            kSession.insert(new Evidence(0.50, Evidence.ANTIGUIDADE_QUILOMETRAGEM, "false"));
            kSession.insert(new Evidence(0.50, Evidence.ESTADO_VEICULO, "false"));

            kSession.insert(new Hypothesis(0.00, "mais_quilometragem", "true"));

            //kSession.insert(new Evidence(1.00, "ESTADO_VEICULO", "true"));


           // kSession.insert(new Hypothesis(0.50, "mais_antigo", "true"));
           // kSession.insert(new Evidence(1.00, "IMPOSTO_VEICULO", "true"));


            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
