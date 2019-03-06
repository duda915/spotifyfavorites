package com.mdud.spotifyfavorites.mongo;

import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.Storage;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MongoConfig {

    @Bean
    public IMongodConfig iMongodConfig() throws IOException {
        String dbDirectory = System.getProperty("user.home") + "/spotifyfavorites";
        Storage storage = new Storage(dbDirectory, null, 0);

        return new MongodConfigBuilder()
                .version(Version.V3_5_5)
                .net(new Net(Network.getFreeServerPort(), Network.localhostIsIPv6()))
                .replication(storage)
                .build();

    }

}
