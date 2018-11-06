package ge.example.cacheexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private CacheService cacheService;

    @Autowired
    public TestController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PutMapping("/put")
    public ResponseEntity<?> put(@RequestParam int value){
        logger.info("Invoking put method with parameter: "+value);
        try {
            cacheService.putInteger(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam int value){
        logger.info("Invoking get method with parameter: "+value);
        try{
            return ResponseEntity.ok("success "+cacheService.getInteger(value));
        }catch (Exception e){
            return ResponseEntity.ok("Not found");
        }

    }

    @PutMapping("/put2")
    public ResponseEntity<?> put2(@RequestParam int value){
        logger.info("Invoking put2 method with parameter: "+value);
        try {
            cacheService.putIntegerInCache2(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get2")
    public ResponseEntity<?> get2(@RequestParam int value){
        logger.info("Invoking get2 method with parameter: "+value);
        try{
            return ResponseEntity.ok("success "+cacheService.getIntegerFromCache2(value));
        }catch (Exception e){
            return ResponseEntity.ok("Not found");
        }

    }
}
