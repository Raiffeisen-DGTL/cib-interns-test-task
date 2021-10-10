package ru.danilarassokhin.raiffeisensocks;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.flywaydb.test.annotation.FlywayTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FlywayTest
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
public abstract class EmbeddedTest {
    protected Logger logger = LoggerFactory.getLogger(EmbeddedTest.class);
}
