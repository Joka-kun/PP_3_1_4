package PP_3_1_4.controller;

import PP_3_1_4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userPanel(Model model,Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "users/userPanel";
    }
}
