package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class RestRoute extends RouteBuilder {




    @Override
    public void configure() throws Exception {
//        restConfiguration()
//                .component("servlet")
//                .host("localhost").port(8080)
//                .bindingMode(RestBindingMode.auto);

        restConfiguration().component("servlet").host("localhost").port(8080).bindingMode(RestBindingMode.off);

        rest("/say")
                .get("/hello").to("direct:hello")
                .get("/bye").consumes("application/json").to("direct:bye")
                .post("/bye").to("mock:update");

        from("direct:hello")
                .transform().constant("Hello World");
        from("direct:bye")
                .transform().constant("Bye World");

    }
    }

class Thing {
    Integer id;
    String name;
    String owner;
    String json;
}

class ThingSearchResults {
    Integer size;
    List<Thing> things;
}

