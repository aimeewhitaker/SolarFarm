package solarfarm.data;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class PanelJdbcTemplateRepositoryTest {

    PanelJdbcTemplateRepository repository;

    public PanelJdbcTemplateRepositoryTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        repository = context.getBean(PanelJdbcTemplateRepository.class);
    }

    @BeforeAll
    static void oneTimeSetup() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        jdbcTemplate.update("call set_known_good_state();");
    }
}
