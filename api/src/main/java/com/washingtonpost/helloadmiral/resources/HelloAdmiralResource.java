package com.washingtonpost.helloadmiral.resources;

import com.codahale.metrics.annotation.Timed;
import com.washingtonpost.arc.auth.v1.client.ArcAuthClient;
import com.washingtonpost.arc.auth.v1.client.impl.JAXRSArcAuthClient;
import com.washingtonpost.arc.auth.v1.core.UserContextData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URI;

@Path("/api/hello-admiral/userinfo")
@Produces(MediaType.APPLICATION_JSON)
public class HelloAdmiralResource {

    private String arcAuthUri;

    private String arcAuthUser;

    private String arcAuthPass;

    public HelloAdmiralResource(String arcAuthUri, String arcAuthUser, String arcAuthPass) {
        this.arcAuthUri = arcAuthUri;
        this.arcAuthUser = arcAuthUser;
        this.arcAuthPass = arcAuthPass;
    }

    @GET
    @Timed
    public UserContextData getUserInfo(@HeaderParam("X-Auth-Token") String token) throws Exception{
        ArcAuthClient myClient = new JAXRSArcAuthClient(new URI(arcAuthUri), arcAuthUser, arcAuthPass);
        UserContextData data = myClient.auth(token);
        return data;
    }
}
