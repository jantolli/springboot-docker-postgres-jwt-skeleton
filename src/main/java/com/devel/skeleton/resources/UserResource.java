package com.devel.skeleton.resources;

import com.devel.skeleton.constants.Constants;
import com.devel.skeleton.domain.User;
import com.devel.skeleton.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Map<String, String>> getUser(@RequestBody Map<String, Object> emailPass) throws URISyntaxException {
        String email = (String) emailPass.get("email");
        String password = (String) emailPass.get("password");
        Assert.notNull(email, "Email cannot be empty");
        Assert.notNull(email, "Password cannot be empty");
        User user = userService.login(email, password);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(generateJWTToken(user));
    }

    @GetMapping("users/get/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<User> getUser(HttpServletRequest request,
                                        @PathVariable("email") String email) throws URISyntaxException {
        User user = userService.getUser(email);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found");
        }
        return ResponseEntity.ok().body(user);
    }

//    @PostMapping("/add")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String addUser(@RequestBody Map<String, Object> userMap) {
//        String firstName = (String) userMap.get("firstName");
//        String lastName = (String) userMap.get("lastName");
//        String email = (String) userMap.get("email");
//        String password = (String) userMap.get("password");
//        userService.addUser(firstName, lastName, email, password);
//        return "User Created";
//    }

    @PostMapping("/users/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        String id = userService.addUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        user.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("email", user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.SECRET)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.DURATION))
                .setClaims(claims)
                .compact();

        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        return map;
    }
}
