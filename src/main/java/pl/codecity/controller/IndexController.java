package pl.codecity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.codecity.service.ClientService;
import pl.codecity.service.ClientServiceImpl;

/**
 * @author Michal Stawarski
 */
@Controller
@CrossOrigin
public class IndexController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public String index(){
        return "index.html";
    }



//    @GetMapping(value = "/agreement/id", produces = "text/html; charset=utf-8")
//    public ResponseEntity<?> getClientId(@PathVariable Long id){
//        return clientService.findOne(id);
//    }
}
