package live.evsianna.stylist.controller;

import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.dto.UserFavorDTO;
import live.evsianna.stylist.model.projection.UserProjection;
import live.evsianna.stylist.service.interfaces.IUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping(value = "/all")
    public Page<UserProjection> findAllEnabledAsPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {
        return iUserService.findAll(true, page, size);
    }

    @GetMapping(value = "/find/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserProjection findById(@PathVariable final String id) {
        return iUserService.findProjectionById(id);
    }

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String currentUser(@AuthenticationPrincipal Authentication authentication) {
        return authentication.getName();
    }

    @PutMapping(value = "/{id}/disable")
    public void setEnabledById(@PathVariable final String id) {
        iUserService.setIsEnabledById(false, id);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity saveNewUser(@RequestBody @NotNull @Valid final User user) {
        iUserService.saveSimple(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value = "/register-with-order",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserFavorDTO> saveNewUserWithFavor(@RequestBody @NotNull @Valid final UserFavorDTO dto) {
        return new ResponseEntity<>(iUserService.saveNewUserWithFavor(dto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/subscribe/{favorId}")
    public ResponseEntity saveFavor(@AuthenticationPrincipal User user,
                                    @PathVariable final String favorId) {

        iUserService.subscribe(user, favorId);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
