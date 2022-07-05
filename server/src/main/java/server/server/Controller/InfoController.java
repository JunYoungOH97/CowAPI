package server.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.server.Service.InfoService;

@RestController
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoService;

    @PostMapping("/dev/init/redis")
    public void aiTest() {
        infoService.saveDashboard();
        infoService.saveVgg();
        infoService.saveLang();
    }
}
