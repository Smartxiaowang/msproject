package bs.common.Global;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPollHelp
 * @Description 线程池辅助类
 * @Author Dear lin
 * @Date 15:32 2022/7/19
 * @Version 1.0
 **/
@Component
@Scope("singleton")
public class ThreadPoolHelp {
    private static ExecutorService fixedService = Executors.newFixedThreadPool(10);
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private ThreadPoolHelp() {
    }

    //获取线程实例
    public ExecutorService getInstance(String pollName) {
        if (pollName.equals("fixed")) {
            if (fixedService != null) {
                return fixedService;
            }
        }
        return null;
    }
    public ExecutorService executorService(){
        return executorService;
    }
}
