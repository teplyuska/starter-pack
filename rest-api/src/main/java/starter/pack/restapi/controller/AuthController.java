package starter.pack.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import starter.pack.core.enums.AuthStatusEnum;
import starter.pack.core.model.service.auth.AuthenticationResult;
import starter.pack.core.service.interfaces.IAccountService;
import starter.pack.restapi.model.dto.auth.AuthDTO;
import starter.pack.restapi.util.IpAddressUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
@Transactional
public class AuthController {
    private IAccountService accountService;

    @Autowired
    public AuthController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> authenticate(HttpServletRequest httpServletRequest, @RequestBody AuthDTO auth) {
        String ipAddress = IpAddressUtil.getClientIpAddress(httpServletRequest);
        AuthenticationResult authenticationResult = accountService.authenticateAccount(auth.getEmail(), auth.getPassword(), ipAddress);

        if (authenticationResult.getAuthStatus().equals(AuthStatusEnum.SUCCESS)) {
            return new ResponseEntity<>(authenticationResult.getAccount().getEmail(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Try again", HttpStatus.UNAUTHORIZED);
        }
    }
}
