package pl.kamil.isstracker.spotter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SpotterController {

    private final SpotterService spotterService;

    @GetMapping({"", "/", "home"})
    public String home(Model model) {
        model.addAttribute("location", new CurrentLocation());
        return "home";
    }

    @GetMapping("get-result")
    public String getResults(Model model, @Valid CurrentLocation currentLocation, BindingResult bindingResult) {

        return "result";

    }
}
