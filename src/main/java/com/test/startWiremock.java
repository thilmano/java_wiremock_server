package com.test;

/**
 * Hello world!
 *
 */

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.extension.Extension;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;

import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class startWiremock {
    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer((Options) WireMockConfiguration.options()
		.asynchronousResponseEnabled(true)
        .extensions(new Extension[] { (Extension) new ResponseTemplateTransformer(true) }));
        wireMockServer.start();
        //helloWorld();
        stubs();
    }

    public static void helloWorld() {
        System.out.println("Hello World outside the main class!");

    }

    public static void stubs(){

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/api/v1/user"))
                .withQueryParam("id", matching(".*"))
                .willReturn(WireMock.aResponse().withStatus(200)
                        .withHeader("Content-Type", new String[] { "application/json" })
                        .withHeader("Cache-Control", new String[] { "no-cache" })
                        .withBodyFile("userDetails.json")
                        .withTransformers(new String[] { "response-template" })));

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/api/v1/user/add"))
                .withRequestBody(
                        equalToJson("{\"user_id\":  \"${json-unit.any-string}\"}"))
                        //equalToJson("{\"user_id\":  \"${json-unit.any-boolean}\"}"))
                        //equalToJson("{\"user_id\":  \"${json-unit.any-number}\"}"))
                        //matchingJsonPath("$.name"))
                        //matchingJsonPath("$[?(@.name.size() == 2)]"))
                .willReturn(WireMock.aResponse().withStatus(200)
                        .withHeader("Content-Type", new String[] { "application/json" })
                        .withHeader("Cache-Control", new String[] { "no-cache" })
                        .withBodyFile("addUser.json")
                        .withTransformers(new String[] { "response-template" })));
    }

}
