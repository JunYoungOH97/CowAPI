//package server.server.Controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import server.server.Service.InfoService;
//
//@RestController
//@RequiredArgsConstructor
//public class InfoController {
//    private final InfoService infoService;
//
//    @PostMapping("/dev/init/dashboard")
//    public void initD() {
//        infoService.saveDashboard();
//    }
//
//    @PostMapping("/dev/init/vgg")
//    public void initV() {
//        infoService.saveVgg();
//    }
//
//    @PostMapping("/dev/init/lang")
//    public void initL() {
//        infoService.saveLang();
//    }
//}
