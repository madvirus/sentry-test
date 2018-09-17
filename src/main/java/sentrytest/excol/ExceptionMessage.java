package sentrytest.excol;

import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExceptionMessage {
    private String ip;
    private String user;
    private Map<String, String> tags = new HashMap<>();
    private Map<String, String> extras = new HashMap<>();
    private Throwable exception;

    public Map<String, String> getTags() {
        return Collections.unmodifiableMap(tags);
    }

    public Map<String, String> getExtras() {
        return Collections.unmodifiableMap(extras);
    }

    public Throwable getException() {
        return exception;
    }

    public static class ExceptionMessageBuilder {
        private ExceptionMessage result = new ExceptionMessage();

        public ExceptionMessageBuilder addTag(String tag, String value) {
            result.tags.put(tag, value);
            return this;
        }

        public ExceptionMessageBuilder addTagIfHasText(String tag, String value) {
            if (StringUtils.hasText(value)) {
                result.tags.put(tag, value);
            }
            return this;
        }

        public ExceptionMessageBuilder addExtra(String extra, String value) {
            result.extras.put(extra, value);
            return this;
        }

        public ExceptionMessageBuilder addExtraIfHasText(String extra, String value) {
            if (StringUtils.hasText(value)) {
                result.extras.put(extra, value);
            }
            return this;
        }

        public ExceptionMessageBuilder exception(Throwable exception) {
            result.exception = exception;
            return this;
        }

        public ExceptionMessage build() {
            return result;
        }

    }

}
