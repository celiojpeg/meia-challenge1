package org.engcia.meiaChallenge1Team4.routeHandlers;

import com.google.gson.Gson;
import fi.iki.elonen.NanoHTTPD.*;
import fi.iki.elonen.router.RouterNanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD.*;
import org.engcia.meiaChallenge1Team4.listeners.FactListener;
import org.engcia.meiaChallenge1Team4.listeners.TrackingAgendaListener;
import org.engcia.meiaChallenge1Team4.model.Car;
import org.engcia.meiaChallenge1Team4.model.Conclusion;
import org.engcia.meiaChallenge1Team4.model.Evidence;
import org.engcia.meiaChallenge1Team4.model.Hypothesis;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public class FireRulesHandler extends DefaultHandler{
    private Conclusion[] conclusions;
    private final Gson gson;

    public FireRulesHandler() {
        super();
        conclusions = new Conclusion[0];
        gson = new Gson();
    }

    @Override
    public String getText() {
        return gson.toJson(conclusions);
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
        final HashMap<String, String> map = new HashMap<>();
        try {
            session.parseBody(map);
        } catch (IOException | ResponseException e) {
            e.printStackTrace();
        }

        final String json = map.get("postData");
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

            kSession.insert(new Conclusion(0.00, Conclusion.TIPO01, Car.getCarsForConclusion(Conclusion.TIPO01)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO02, Car.getCarsForConclusion(Conclusion.TIPO02)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO03, Car.getCarsForConclusion(Conclusion.TIPO03)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO04, Car.getCarsForConclusion(Conclusion.TIPO04)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO05, Car.getCarsForConclusion(Conclusion.TIPO05)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO06, Car.getCarsForConclusion(Conclusion.TIPO06)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO07, Car.getCarsForConclusion(Conclusion.TIPO07)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO08, Car.getCarsForConclusion(Conclusion.TIPO08)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO09, Car.getCarsForConclusion(Conclusion.TIPO09)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO10, Car.getCarsForConclusion(Conclusion.TIPO10)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO11, Car.getCarsForConclusion(Conclusion.TIPO11)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO12, Car.getCarsForConclusion(Conclusion.TIPO12)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO13, Car.getCarsForConclusion(Conclusion.TIPO13)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO14, Car.getCarsForConclusion(Conclusion.TIPO14)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO15, Car.getCarsForConclusion(Conclusion.TIPO15)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO16, Car.getCarsForConclusion(Conclusion.TIPO16)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO17, Car.getCarsForConclusion(Conclusion.TIPO17)));
            kSession.insert(new Conclusion(0.00, Conclusion.TIPO18, Car.getCarsForConclusion(Conclusion.TIPO18)));

            for (Evidence evidence : evidences) {
                kSession.insert(evidence);
            }

            kSession.fireAllRules();

            Collection<Conclusion> resultConclusions = (Collection<Conclusion>) kSession.getObjects( new ClassObjectFilter(Conclusion.class) );

            conclusions = resultConclusions.toArray(new Conclusion[0]);

            kSession.destroy();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        Response response = this.get(uriResource, urlParams, session);
        response.addHeader("Access-Control-Allow-Origin", "*");

        return response;
    }

    public Response other(String method, RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, IHTTPSession session) {
        Response response = newFixedLengthResponse("");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.addHeader("Access-Control-Max-Age", "86400");
        return response;
    }
}
