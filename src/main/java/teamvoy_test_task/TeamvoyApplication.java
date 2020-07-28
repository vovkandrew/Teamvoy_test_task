package teamvoy_test_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import teamvoy_test_task.service.OrderService;

@SpringBootApplication
public class TeamvoyApplication {
    private static ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        TeamvoyApplication.context = context;
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(TeamvoyApplication.class, args);
        OrderService service = context.getBean(OrderService.class);
        while (true) {
            service.removeOldOrders();
            Thread.sleep(1000);
        }
    }
}
