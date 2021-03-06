package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data...**************");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        User bob = new User("bob@bob.com", "password", "Bob", "Bobberson", true, "bob");
        userService.saveUser(bob);

        User alice = new User("alice@alice.com", "password", "Alice", "Allison", true, "alice");
        userService.saveUser(alice);

        User admin = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        userService.saveAdmin(admin);

        //Pet Data
        Pet fido = new Pet("fido", "2019-03-25", "https://res.cloudinary.com/mhussainshah1/image/upload/v1553388047/dog.jpg", "friendly dog", "lost");
        fido.getUsers().add(bob);
        petRepository.save(fido);
        bob.getPets().add(fido);
        userRepository.save(bob);

        Pet fifi = new Pet("fifi", "2019-03-25", "https://res.cloudinary.com/mhussainshah1/image/upload/v1553388980/fido.jpg", "grumpy dog", "lost");
        fifi.getUsers().add(alice);
        petRepository.save(fifi);
        alice.getPets().add(fifi);
        userRepository.save(alice);

        fido.setStatus("found");
        System.out.println("Data loaded...**************");
    }

}
