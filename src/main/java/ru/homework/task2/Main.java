package ru.homework.task2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.homework.models.Person;



public class Main {

    /*
    Настройте связь между вашим приложением и базой данных MySQL с использованием Hibernate.
    Создайте несколько объектов Person и сохраните их в базу данных.
     */

    public static void main(String[] args) {
        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory()) {

            // Создание сессии
            Session session = sessionFactory.getCurrentSession();

            // Начало транзации
            session.beginTransaction();

            // Создание объекта
            Person person = Person.create();
            Person person1 = Person.create();
            session.save(person);
            session.save(person1);
            System.out.println("Object person save successfully");

            // Чтение объекта из базы данных
            Person retrievedPerson = session.get(Person.class, person.getId());
            System.out.println("Object person retrieved successfully");
            System.out.println("Retrieved person object: " + retrievedPerson);

            // Обновление объекта
            retrievedPerson.updateName();
            retrievedPerson.updateAge();
            session.update(retrievedPerson);
            System.out.println("Object person update successfully");

            // Удаление данных
            //session.delete(retrievedPerson);
            //System.out.println("Object person delete successfully");

            session.getTransaction().commit();


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
