package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

//   @PersistenceContext   //так не запускается
   private final SessionFactory sessionFactory;

   @Autowired  //а так работает (аннотация необязательна, но так читаемее)
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
      if (user.getCar() != null) {
         sessionFactory.getCurrentSession().save(user.getCar());
      }
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
      if (car.getUser() != null) {
         sessionFactory.getCurrentSession().save(car.getUser());
      }
   }

   @Override
   public Object getUser(String model, int series) {
      try {
         TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                 "from User where car = (from Car where model = :model and series = :series)"
         );
         query.setParameter("model", model);
         query.setParameter("series", series);
         return query.getSingleResult();
      } catch (Exception e) {
         System.out.println("Пользователь с таким автомобилем не найден!");
      }
      return null;
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

}
