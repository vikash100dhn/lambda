package com.aws.handler;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public enum ConfigurationHolder {
    instance;

    private final Yaml yaml = new Yaml(new Constructor(Configuration.class));
    private Configuration configuration;

    ConfigurationHolder() {
        load();
    }

    private void load() {
        InputStream inputStream = ConfigurationHolder.class.getResourceAsStream("/application.yml");

        this.configuration = Configuration.class.cast(yaml.load(inputStream));
    }

    public Configuration configuration() {
        return this.configuration;
    }
}
