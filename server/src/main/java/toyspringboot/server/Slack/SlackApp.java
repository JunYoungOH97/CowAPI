//package toyspringboot.server.Slack;
//
//import com.slack.api.bolt.App;
//import com.slack.api.methods.SlackApiException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
//@Configuration
//@RequiredArgsConstructor
//public class SlackApp {
//    private final SlackService slackService;
//
//    @Bean
//    public App initSlackApp() {
//        return new App();
//    }
//
//    // 공지생성 이벤트가 발생 했을 때,
//    public void CreateNoticeEvent() {
//        slackService.postSlackMessage("새로운 질문이 생성 되었습니다.");
//        slackService.postSlackMessage("질문 : QnAs/{QnAId}");
//    }
//
//}