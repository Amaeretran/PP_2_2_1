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

      User user1 = new User(new Car("Tesla", 3));
      user1.setData("Ivan", "Grozniy", "ivan@grozniy");
      userService.add(user1);

      User user2 = new User(new Car("Mercedes", 310));
      user2.setData("Georg", "Second", "georg@second");
      userService.add(user2);

      User user3 = new User(new Car("Rolls_Royce", 256983));
      user3.setData("Petr", "Velikiy", "petr@velikiy");
      userService.add(user3);

      List<User> users2 = userService.listUsers();
      for (User user : users2) {
         System.out.println(user);
      }
      System.out.println("\n");
      System.out.println(userService.getUser("Rolls_Royce", 256983));
      context.close();
   }
}
