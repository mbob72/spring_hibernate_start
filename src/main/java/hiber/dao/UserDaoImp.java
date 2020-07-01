package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getUserByCarProps(String name, int series) {
      TypedQuery<User> query= sessionFactory
              .getCurrentSession()
              .createQuery("from User user where user.car.name = :nameParam and user.car.series = :seriesParam")
              .setParameterList("nameParam", Collections.singleton(name))
              .setParameterList("seriesParam", Collections.singleton(series));
      return query.getResultList().get(0);
   }

}
