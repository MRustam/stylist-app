package live.evsianna.stylist.controller;

import live.evsianna.stylist.model.projection.FavorProjection;
import live.evsianna.stylist.service.interfaces.IFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/favor")
public class FavorController {

    @Autowired
    private IFavorService favorService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<FavorProjection> findAll() {
        return favorService.findAll();
    }

}
