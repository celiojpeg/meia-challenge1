package org.engcia.cfsample;

//import org.drools.core.rule.builder.dialect.asm.ClassGenerator;
import org.engcia.cf.model.Conclusion;
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

            //Hypothesis
            kSession.insert(new Hypothesis(0.00, "prioridade_preco", "true"));
            kSession.insert(new Hypothesis(0.00, "prioridade_consumo", "true"));
            kSession.insert(new Hypothesis(0.00, "prioridade_design", "true"));
            kSession.insert(new Hypothesis(0.00, "mais_quilometragem", "true"));
            kSession.insert(new Hypothesis(0.00, "mais_antigo", "true"));
            kSession.insert(new Hypothesis(0.00, "mais_quilometragem", "true"));
            kSession.insert(new Hypothesis(0.00, "combustiveis_fosseis", "true"));
            kSession.insert(new Hypothesis(0.00, "carro_eletrico", "true"));
            kSession.insert(new Hypothesis(0.00, "carro_gasoleo", "true"));
            kSession.insert(new Hypothesis(0.00, "carro_gasolina", "true"));
            kSession.insert(new Hypothesis(0.00, "perfil_versatil", "true"));
            kSession.insert(new Hypothesis(0.00, "perfil_desportivo", "true"));
            kSession.insert(new Hypothesis(0.00, "perfil_familiar", "true"));
            kSession.insert(new Hypothesis(0.00, "suv_on_road", "true"));
            kSession.insert(new Hypothesis(0.00, "suv_off_road", "true"));
            kSession.insert(new Hypothesis(0.00, "familiar_7lugares", "true"));
            kSession.insert(new Hypothesis(0.00, "familiar_45lugares", "true"));
            kSession.insert(new Hypothesis(0.00, "carro_gasolina", "true"));
            kSession.insert(new Hypothesis(0.00, "desportivo_2portas", "true"));
            kSession.insert(new Hypothesis(0.00, "desportivo_45portas", "true"));
            kSession.insert(new Hypothesis(0.00, "carro_gasolina", "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO01, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO02, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO03, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO04, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO05, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO06, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO07, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO08, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO09, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO10, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO11, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO12, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO13, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO14, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO15, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO16, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO17, "true"));
            kSession.insert(new Hypothesis(0.00, Conclusion.TIPO18, "true"));

            // Evidences
            kSession.insert(new Evidence(1.00, Evidence.PRECO_PRIORITARIO, "true"));
            kSession.insert(new Evidence(1.00, Evidence.QUAL_O_PRECO, "false"));
            kSession.insert(new Evidence(0.50, Evidence.ANTIGUIDADE_QUILOMETRAGEM, "false"));
            kSession.insert(new Evidence(1.00, Evidence.ESTADO_VEICULO, "false"));

            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
