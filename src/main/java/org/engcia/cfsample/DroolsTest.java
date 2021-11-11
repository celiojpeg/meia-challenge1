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

            //---------------------- Hypothesis ----------------------
            kSession.insert(new Hypothesis(0.00, "prioridade_preco"));
            kSession.insert(new Hypothesis(0.00, "prioridade_consumo"));
            kSession.insert(new Hypothesis(0.00, "prioridade_design"));
            kSession.insert(new Hypothesis(0.00, "mais_quilometragem"));
            kSession.insert(new Hypothesis(0.00, "mais_antigo"));
            kSession.insert(new Hypothesis(0.00, "mais_quilometragem"));
            kSession.insert(new Hypothesis(0.00, "combustiveis_fosseis"));
            kSession.insert(new Hypothesis(0.00, "carro_eletrico"));
            kSession.insert(new Hypothesis(0.00, "carro_gasoleo"));
            kSession.insert(new Hypothesis(0.00, "carro_gasolina"));
            kSession.insert(new Hypothesis(0.00, "perfil_versatil"));
            kSession.insert(new Hypothesis(0.00, "perfil_desportivo"));
            kSession.insert(new Hypothesis(0.00, "perfil_familiar"));
            kSession.insert(new Hypothesis(0.00, "suv_on_road"));
            kSession.insert(new Hypothesis(0.00, "suv_off_road"));
            kSession.insert(new Hypothesis(0.00, "familiar_7lugares"));
            kSession.insert(new Hypothesis(0.00, "familiar_45lugares"));
            kSession.insert(new Hypothesis(0.00, "carro_gasolina"));
            kSession.insert(new Hypothesis(0.00, "desportivo_2portas"));
            kSession.insert(new Hypothesis(0.00, "desportivo_45portas"));
            kSession.insert(new Hypothesis(0.00, "carro_gasolina"));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO01));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO02));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO03));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO04));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO05));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO06));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO07));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO08));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO09));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO10));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO11));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO12));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO13));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO14));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO15));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO16));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO17));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO18));

            /*
            ---------------------- Evidences ----------------------
            ---------------------- Neste exemplo: ----------------------
                            -70% Preço
                            -15% Consumo
                            -15% Design

                            Preço: Tipo 04

                            Consumo: 40% gasolina -> Tipo 07
                                     60% gasoleo -> Tipo 05

                            Design: 60% versátil -> 70% Tipo 12
                                                    30% Tipo 13
                                    20% familiar -> Tipo 17
                                    20% desportivo -> Tipo 15
            */
            kSession.insert(new Evidence(0.70, Evidence.PRECO_PRIORITARIO, "true"));
            kSession.insert(new Evidence(1.00, Evidence.PRECO_PRIORITARIO, "false"));
            kSession.insert(new Evidence(0.15, Evidence.CONSUMO_PRIORITARIO, "true"));
            kSession.insert(new Evidence(0.15, Evidence.CONSUMO_PRIORITARIO, "false"));
            kSession.insert(new Evidence(1.00, Evidence.QUAL_O_PRECO, "false"));
            kSession.insert(new Evidence(0.00, Evidence.QUAL_O_PRECO, "true"));
            kSession.insert(new Evidence(1.00, Evidence.ANTIGUIDADE_QUILOMETRAGEM, "false"));
            kSession.insert(new Evidence(0.00, Evidence.ANTIGUIDADE_QUILOMETRAGEM, "true"));
            kSession.insert(new Evidence(1.00, Evidence.ESTADO_VEICULO, "false")); // -> TIPO 4
            kSession.insert(new Evidence(0.00, Evidence.ESTADO_VEICULO, "true"));
            kSession.insert(new Evidence(0.00, Evidence.IMPOSTO_VEICULO, "true"));
            kSession.insert(new Evidence(0.00, Evidence.IMPOSTO_VEICULO, "false"));
            kSession.insert(new Evidence(1.00, Evidence.COMBUSTIVEIS_FOSSEIS, "true"));
            kSession.insert(new Evidence(0.00, Evidence.COMBUSTIVEIS_FOSSEIS, "false"));
            kSession.insert(new Evidence(0.00, Evidence.MAIS_200KM, "true"));
            kSession.insert(new Evidence(0.00, Evidence.MAIS_200KM, "false"));
            kSession.insert(new Evidence(0.00, Evidence.CARREGAMENTO_DIÁRIO, "true"));
            kSession.insert(new Evidence(0.00, Evidence.CARREGAMENTO_DIÁRIO, "false"));
            kSession.insert(new Evidence(0.00, Evidence.PILOTO_AUTOMATICO, "true"));
            kSession.insert(new Evidence(0.00, Evidence.PILOTO_AUTOMATICO, "false"));
            kSession.insert(new Evidence(0.60, Evidence.MEDIA_CONSUMO, "false"));
            kSession.insert(new Evidence(0.40, Evidence.MEDIA_CONSUMO, "true"));
            kSession.insert(new Evidence(1.00, Evidence.PERFORMANCE, "true")); // -> TIPO 7
            kSession.insert(new Evidence(0.00, Evidence.PERFORMANCE, "false"));
            kSession.insert(new Evidence(1.00, Evidence.EMISSOES, "true")); // -> TIPO 5
            kSession.insert(new Evidence(0.00, Evidence.EMISSOES, "false"));
            kSession.insert(new Evidence(0.60, Evidence.CARRO_VERSATIL, "true"));
            kSession.insert(new Evidence(1.00, Evidence.CARRO_VERSATIL, "false"));
            kSession.insert(new Evidence(0.20, Evidence.CARRO_FAMILIAR, "false"));
            kSession.insert(new Evidence(0.20, Evidence.CARRO_FAMILIAR, "true"));
            kSession.insert(new Evidence(0.70, Evidence.TIPO_SUV_OFF_ROAD, "false")); // -> TIPO 12
            kSession.insert(new Evidence(0.30, Evidence.TIPO_SUV_OFF_ROAD, "true")); // -> TIPO 13
            kSession.insert(new Evidence(1.00, Evidence.DESPORTIVO_2PORTAS, "true"));
            kSession.insert(new Evidence(0.00, Evidence.DESPORTIVO_2PORTAS, "false"));
            kSession.insert(new Evidence(1.00, Evidence.DESPORTIVO_CABRIO, "false")); // -> TIPO 15
            kSession.insert(new Evidence(0.00, Evidence.DESPORTIVO_CABRIO, "true"));
            kSession.insert(new Evidence(1.00, Evidence.FAMILIAR_7_LUGARES, "true")); // -> TIPO 17
            kSession.insert(new Evidence(0.00, Evidence.FAMILIAR_7_LUGARES, "false"));

            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
