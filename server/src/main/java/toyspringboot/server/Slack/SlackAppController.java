//package toyspringboot.server.Slack;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import toyspringboot.server.Slack.SlackService;
//
//@RequiredArgsConstructor
//@EnableScheduling
//@Configuration
//public class SlackAppController {
//    private final SlackService slackService;
//    @Scheduled(cron="0 0/1 * * * *") //1분
//    public void todayCocktail(){
//        slackService.postSlackMessage("안녕..");
//    }
//}