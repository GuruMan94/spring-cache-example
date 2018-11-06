package ge.example.cacheexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    private CacheService cacheService;

    @Autowired
    public TestController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/put")
    public ResponseEntity<?> put(@RequestParam int value){
        try {
            cacheService.putInteger(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam int value){
        try{
            return ResponseEntity.ok("success "+cacheService.getInteger(value));
        }catch (Exception e){
            return ResponseEntity.ok("Not found");
        }

    }

    @GetMapping("/put2")
    public ResponseEntity<?> put2(@RequestParam int value){
        try {
            cacheService.putIntegerInCache2(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get2")
    public ResponseEntity<?> get2(@RequestParam int value){
        try{
            return ResponseEntity.ok("success "+cacheService.getIntegerFromCache2(value));
        }catch (Exception e){
            return ResponseEntity.ok("Not found");
        }

    }
}
