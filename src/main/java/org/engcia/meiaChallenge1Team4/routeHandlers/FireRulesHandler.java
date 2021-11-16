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
            kSession.insert(new Hypothesis(0.00, Hypothesis.PRIORIDADE_PRECO));
            kSession.insert(new Hypothesis(0.00, Hypothesis.PRIORIDADE_CONSUMO));
            kSession.insert(new Hypothesis(0.00, Hypothesis.PRIORIDADE_DESIGN));
            kSession.insert(new Hypothesis(0.00, Hypothesis.MAIS_QUILOMETRAGEM));
            kSession.insert(new Hypothesis(0.00, Hypothesis.MAIS_ANTIGO));
            kSession.insert(new Hypothesis(0.00, Hypothesis.COMBUSTIVEIS_FOSSEIS));
            kSession.insert(new Hypothesis(0.00, Hypothesis.CARRO_ELETRICO));
            kSession.insert(new Hypothesis(0.00, Hypothesis.CARRO_GASOLEO));
            kSession.insert(new Hypothesis(0.00, Hypothesis.CARRO_GASOLINA));
            kSession.insert(new Hypothesis(0.00, Hypothesis.PERFIL_VERSATIL));
            kSession.insert(new Hypothesis(0.00, Hypothesis.PERFIL_DESPORTIVO));
            kSession.insert(new Hypothesis(0.00, Hypothesis.PERFIL_FAMILIAR));
            kSession.insert(new Hypothesis(0.00, Hypothesis.FAMILIAR_45LUGARES));
            kSession.insert(new Hypothesis(0.00, Hypothesis.FAMILIAR_7LUGARES));
            kSession.insert(new Hypothesis(0.00, Hypothesis.DESPORTIVO_2PORTAS));
            kSession.insert(new Hypothesis(0.00, Hypothesis.DESPORTIVO_45PORTAS));
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
