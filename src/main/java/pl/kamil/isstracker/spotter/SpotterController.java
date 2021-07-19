package pl.kamil.isstracker.spotter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamil.isstracker.shared.dto.LocationData;
import pl.kamil.isstracker.shared.dto.FullSpottingData;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SpotterController {

    private final SpotterService spotterService;

    @GetMapping({"", "/", "home"})
    public String home(Model model) {
        model.addAttribute("location", new LocationData());
        return "home";
    }

    @GetMapping("get-result")
    public String getResults(Model model, @Valid LocationData locationData, BindingResult bindingResult) {

        List<FullSpottingData> fullFlyoverDataSet = spotterService.findPossibleFlyOvers(locationData);
        model.addAttribute("flyovers", fullFlyoverDataSet);

        return "result";

    }
    @GetMapping("map")
    public String showStreetView(Model model) {
        return "map";
    }
}
