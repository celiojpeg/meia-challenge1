package org.engcia.meiaChallenge1Team4.routeHandlers;

import com.google.gson.Gson;
import fi.iki.elonen.NanoHTTPD.*;
import fi.iki.elonen.router.RouterNanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD.*;
import org.engcia.meiaChallenge1Team4.model.Car;
import org.engcia.meiaChallenge1Team4.model.CarInfo;
import org.engcia.meiaChallenge1Team4.model.Conclusion;
import org.engcia.meiaChallenge1Team4.model.Evidence;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public class CarInfoHandler extends DefaultHandler{
    private final Gson gson;
    private ArrayList<CarInfo> carInfos;

    public CarInfoHandler() {
        super();
        this.gson = new Gson();
        this.carInfos = new ArrayList<>();
    }

    @Override
    public String getText() {
        return gson.toJson(this.carInfos);
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
        String[] links = gson.fromJson(json, String[].class);


        for (String link : links) {
            try {
                Document doc = Jsoup.connect(link).get();
                Elements metaTags = doc.getElementsByTag("meta");
                String imageURL = "";
                String price = "N/A";

                for (Element metaTag : metaTags) {
                    String property = metaTag.attr("property");
                    if(!"og:image".equals(property)) {
                        continue;
                    }

                    imageURL = metaTag.attr("content");
                    break;
                }

                Elements prices = doc.getElementsByClass("vsPricingEstimateTableValue");
                if (prices.first() != null) {
                    price = Objects.requireNonNull(prices.first()).text();
                }
                this.carInfos.add(new CarInfo(imageURL, price));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
