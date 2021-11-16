package org.engcia.meiaChallenge1Team4.routeHandlers;

import com.google.gson.Gson;
import fi.iki.elonen.NanoHTTPD.*;
import fi.iki.elonen.router.RouterNanoHTTPD.*;
import org.engcia.meiaChallenge1Team4.listeners.FactListener;
import org.engcia.meiaChallenge1Team4.listeners.TrackingAgendaListener;
import org.engcia.meiaChallenge1Team4.model.Conclusion;
import org.engcia.meiaChallenge1Team4.model.Evidence;
import org.engcia.meiaChallenge1Team4.model.Hypothesis;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FireRulesHandler extends DefaultHandler{
    @Override
    public String getText() {
        return "not implemented";
    }

    @Override
    public String getMimeType() {
        return "application/json";
    }

    @Override
    public Response.IStatus getStatus() {
        return Response.Status.OK;
    }

    public Response post(UriResource uriResource, Map<String, String> urlParams, IHTTPSession session) {
        final HashMap<String, String> map = new HashMap<String, String>();
        try {
            session.parseBody(map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }

        final String json = map.get("postData");
        Gson gson = new Gson();

        Evidence[] evidences = gson.fromJson(json, Evidence[].class);


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

            for (Evidence evidence : evidences) {
                kSession.insert(evidence);
            }

            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return this.get(uriResource, urlParams, session);
    }
}
