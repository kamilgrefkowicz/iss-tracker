package pl.kamil.isstracker.spotter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamil.isstracker.shared.FullFlyOverData;

import javax.validation.Valid;
import java.util.List;

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

        List<FullFlyOverData> possibleFlyOvers = spotterService.findPossibleFlyOvers(currentLocation);
        model.addAttribute(possibleFlyOvers);

        return "result";

    }
    @GetMapping("map")
    public String showStreetView(Model model) {
        return "map";
    }
}
