package pl.kamil.isstracker.iss;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ISSLocatorImpl implements ISSLocator {

    @Value("${app.api.2nyo.key}")
    private final String apiKey;
}
