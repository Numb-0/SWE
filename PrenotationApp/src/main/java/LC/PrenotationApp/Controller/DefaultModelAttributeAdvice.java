package LC.PrenotationApp.Controller;

import LC.PrenotationApp.BuisnessLogic.CustomUserDetailsService;
import LC.PrenotationApp.BuisnessLogic.ItemService;
import LC.PrenotationApp.Entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

// This class sets default attributes for all thDefaultModelAttributeAdvice controllers model
@ControllerAdvice
public class DefaultModelAttributeAdvice {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    ItemService itemService;

    @ModelAttribute("isIndexPage")
    public boolean isIndexPage() {
        return false; // Default value
    }

    @ModelAttribute("isUserLogged")
    public boolean isUserLogged() {
        return userDetailsService.isUserLogged();
    }

    @ModelAttribute("AuthUser")
    public UserDetails AuthUser() {
        return userDetailsService.getLoggedInUser();
    }
}