package com.washingtonpost.helloadmiral;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;


public class HelloAdmiralConfiguration extends Configuration {

    @NotEmpty
    private String arcAuthUri;

    @NotEmpty
    private String arcAuthUser;

    @NotEmpty
    private String arcAuthPass;

    @JsonProperty
    public String getArcAuthUri() {
        return arcAuthUri;
    }

    @JsonProperty
    public String getArcAuthUser() {
        return arcAuthUser;
    }

    @JsonProperty
    public String getArcAuthPass() {
        return arcAuthPass;
    }

}
