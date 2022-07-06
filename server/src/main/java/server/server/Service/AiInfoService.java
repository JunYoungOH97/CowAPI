//package server.server.Service;
//
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import server.server.Domain.Dto.AiDto;
//import server.server.Redis.AiRedisService;
//
//import java.util.Arrays;
//
//@Aspect
//@RequiredArgsConstructor
//public class AiInfoService {
//    private final AiRedisService aiRedisService;
//
//    @Around(value = "execution(* *..Service.AiApiService..*(..))")
//    public Object test(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//
//        Object result = proceedingJoinPoint.proceed();
//
//        Double timeMs = (System.currentTimeMillis() - start) * 1.0D;
//
//        AiDto aiDto = AiDto.of(aiRedisService.read("vgg"));
//
//        long updateUserCnt = aiDto.getUseCnt() + 1L;
//        Double updateResponseTime = (aiDto.getResponseTime() + timeMs) / updateUserCnt;
//
//        aiDto.setUseCnt(updateUserCnt);
//        aiDto.setResponseTime(updateResponseTime);
//
//        aiRedisService.update(aiDto);
//
//        System.out.println("proceedingJoinPoint = " + aiDto.getName() + "args : " + Arrays.toString(proceedingJoinPoint.getArgs()));
//        return result;
//    }
//}
