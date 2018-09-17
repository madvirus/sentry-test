package sentrytest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import sentrytest.excol.NoopExceptionCaptor;
import sentrytest.excol.ExceptionCaptor;
import sentrytest.excol.SentryExceptionCaptor;

@Configuration
public class ExceptionCaptorConfig {
    @Profile("local")
    @Bean
    public ExceptionCaptor localExCaptor() {
        return new NoopExceptionCaptor();
    }

    @Value("${sentry.dsn}")
    private String sentryDsn;

    @Profile("!local")
    @Bean
    public ExceptionCaptor sentryExCaptor() {
        return new SentryExceptionCaptor(sentryDsn);
    }
}
