package com.egstestmyquizi.demo.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

//    @Autowired
//   // UserService userService;
//
//    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @ResponseBody
//    public Map<String, String> login(@RequestBody Map<String, String> values) {
//        Map<String, String> accessToken = new HashMap<>();
//        accessToken.put("access_token", userService.loginByUserNameAndPassword(
//                values.get("userName"), values.get("password")));
//        return accessToken;
//    }
//
//    @SneakyThrows
//    @PostMapping("/register")
//    public HttpStatus register(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        try {
//            userService.save(user);
//        } catch (UserRegistrationException e) {
//            throw new UserRegistrationException();
//        }
//        return HttpStatus.OK;
//    }
//
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/users")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public List<User> findAllUser() {
//        return userService.findAll();
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/user/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public User findByIdUser(@PathVariable("id") Integer id) {
//        return userService.findById(id);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @DeleteMapping("/user/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public void deleteUserById(@PathVariable("id") Integer id) {
//        userService.findById(id);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping("/user/update/email")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public void updateEmail(@RequestHeader("token") String token, @RequestParam("email") String email) {
//        String[] tokenParsValues = Encrypt.decodeAccount(token);
//        userService.updateEmail(tokenParsValues[0], email);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping("/user/update/name")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public void updateName(@RequestHeader("token") String token, @RequestParam("name") String name) {
//        String[] tokenParsValues = Encrypt.decodeAccount(token);
//        userService.updateName(tokenParsValues[0], name);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping("/user/update/surname")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public void updateSurName(@RequestHeader("token") String token, @RequestParam("surname") String surname) {
//        String[] tokenParsValues = Encrypt.decodeAccount(token);
//        userService.updateSurName(tokenParsValues[0], surname);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping("/user/update/password")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public void updatePassword(@RequestHeader("token") String token, @RequestParam("password") String password) {
//        String[] tokenParsValues = Encrypt.decodeAccount(token);
//        userService.updatePassword(tokenParsValues[0], password);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping("/user/update/enable")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public void updateEnable(@RequestHeader("token") String token, @RequestParam("enable") boolean isEnable) {
//        String[] tokenParsValues = Encrypt.decodeAccount(token);
//        userService.updateEnable(tokenParsValues[0], isEnable);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/leaderBoard")
//    public List<LeaderBoard> leaderBoard() {
//        return userService.leaderBoard();
//    }

}
