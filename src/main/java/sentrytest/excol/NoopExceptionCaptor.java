package sentrytest.excol;

public class NoopExceptionCaptor implements ExceptionCaptor {
    @Override
    public void capture(ExceptionMessage message) {
        // ignore
    }
}
