package ro.msg.learning.shop.controller;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile(value = "test")
public class TestController {
        private Flyway flyway = Flyway.configure().dataSource("jdbc:h2:mem:shoptest", "sa", "").load();

        @GetMapping("/test/populate")
        public void populate() {
            flyway.migrate();
        }

        @GetMapping("/test/clear")
        public void clear() {
            flyway.clean();
        }
}
