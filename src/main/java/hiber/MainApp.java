package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user10 = new User(
              "Ivan", "Grozniy", "ivan@grozniy",
              new Car("Tesla", 3)
      );
      userService.add(user10);

      User user11 = new User(
              "Georg", "Second", "georg@second",
              new Car("Mercedes", 310)
      );
      userService.add(user11);

      User user12 = new User(
              "Petr", "Velikiy", "petr@velikiy",
              new Car("Rolls_Royce", 256983)
      );
      userService.add(user12);

      List<User> users2 = userService.listUsers();
      for (User user : users2) {
         System.out.println(user);
      }
      System.out.println("\n\n\n\n");
      System.out.println(userService.getUser("Tesla", 3));
      context.close();
   }
}
