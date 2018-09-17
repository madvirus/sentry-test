package sentrytest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleApi {

    @RequestMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam("a") Double a, @RequestParam("b") Double b) {
        return ResponseEntity.ok(a / b);
    }

}
