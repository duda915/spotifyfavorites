package com.mdud.spotifyfavorites.mongo;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.Storage;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.File;

import java.io.IOException;

@Configuration
public class MongoConfig {

    @Bean
    public IMongodConfig iMongodConfig() throws IOException {
        Storage storage = new Storage(
                System.getProperty("user.home") + "/.spotifyfav", null, 0);

        return new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(Network.getFreeServerPort(), Network.localhostIsIPv6()))
                .replication(storage)
                .build();

    }

}