package live.evsianna.stylist.controller;

import live.evsianna.stylist.controller.model.UserOrderDTO;
import live.evsianna.stylist.controller.model.UsersRequestDTO;
import live.evsianna.stylist.model.projection.UserProjection;
import live.evsianna.stylist.service.interfaces.IUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping(value = "/all", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<UserProjection> findAll(@RequestBody @NotNull final UsersRequestDTO dto) {
        return iUserService.findAll(dto);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserOrderDTO> save(@RequestBody @NotNull @Valid final UserOrderDTO dto) {
        return new ResponseEntity<>(iUserService.saveUserAndOrder(dto), HttpStatus.CREATED);
    }

}
