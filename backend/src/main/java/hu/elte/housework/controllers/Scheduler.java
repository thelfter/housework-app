package hu.elte.housework.controllers;

import hu.elte.housework.entities.User;
import hu.elte.housework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Scheduler {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0/5 * * * *")
    //Scheduled(cron = "0 0 0 * * 1") heti lenullázás
    public void ResetScore() {
        Iterable<User> users = userRepository.findAll();

        for (User user : users) {
            user.setActualScore(0);
            System.out.println(user.getFullName() + " aktuális pontjának átállítása, Dátum: " + new Date());
            userRepository.save(user);
        }
    }


}
