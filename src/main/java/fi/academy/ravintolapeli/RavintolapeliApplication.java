package fi.academy.ravintolapeli;

import fi.academy.ravintolapeli.objects.Mission;
import fi.academy.ravintolapeli.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RavintolapeliApplication {

    public static void main(String[] args) {
        SpringApplication.run(RavintolapeliApplication.class, args);
    }

//    @Bean//tällä Beanilla luotiin tietokanta ja lisättiin siihen kaksi missiota
//    public CommandLineRunner alustus(@Autowired MissionRepository repository) {
//        return args -> {
//            repository.deleteAll();
//            // save a couple of customers
//            repository.save(new Mission("Chicken Hunt","https://4fi8v2446i0sw2rpq2a3fg51-wpengine.netdna-ssl.com/wp-content/uploads/2016/06/KittenProgression-Darling-week3.jpg",
//                    "Where's my chicken?", "Manhattan", "Chicken", "Cat"));
//            repository.save(new Mission("Meat!!","http://a57.foxnews.com/images.foxnews.com/content/fox-news/tech/2018/03/01/lion-with-luscious-head-hair-becomes-viral-sensation/_jcr_content/par/featured_image/media-0.img.jpg/931/524/1519920904249.jpg?ve=1&tl=1",
//                    "I'm getting very very hungry right about now!", "Bronx", "Steak", "Beef"));
//
//            System.out.println("Missions found with findAll():");
//            System.out.println("-------------------------------");
//            for (Mission mission : repository.findAll()) {
//                System.out.println(mission);
//            }
//            System.out.println();
//            // fetch an individual customer
//            System.out.println("Mission found with findAllByStoryIsContaining(chicken)");
//            System.out.println("--------------------------------");
//            System.out.println(repository.findAllByStoryIsContaining("chicken"));
//
//        };
//    }
}
