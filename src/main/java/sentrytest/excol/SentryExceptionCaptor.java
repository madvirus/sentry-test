package sentrytest.excol;

import io.sentry.Sentry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SentryExceptionCaptor implements ExceptionCaptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String dsn;

    public SentryExceptionCaptor(String dsn) {
        this.dsn = dsn;
    }

    @PostConstruct
    public void init() {
        try {
            Sentry.init(dsn);
        } catch(Exception ex) {

        }
    }

    @PreDestroy
    public void clese() {
        try {
            Sentry.close();
        } catch (Exception ex) {
            // ignore
        }
    }

    @Override
    public void capture(ExceptionMessage message) {
        if (Sentry.getStoredClient() == null) {
            logger.warn("no initialized SentryClient");
            return;
        }
        try {
            message.getTags().forEach((k, v) -> Sentry.getContext().addTag(k, v));
            message.getExtras().forEach((k, v) -> Sentry.getContext().addExtra(k, v));
            Sentry.capture(message.getException());
        } catch (Exception ex) {
            logger.warn("fail to Sentry.capture()", ex);
        } finally {
            try {
                Sentry.clearContext();
            } catch (Exception ex) {
            }
        }
    }
}
