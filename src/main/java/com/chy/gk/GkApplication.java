package com.chy.gk;

import com.chy.gk.model.uesr.Permission;
import com.chy.gk.model.uesr.Role;
import com.chy.gk.model.uesr.User;
import com.chy.gk.repository.PermissionRepository;
import com.chy.gk.repository.RoleRepository;
import com.chy.gk.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class GkApplication
//        implements CommandLineRunner
{
    private static final Logger logger = LoggerFactory.getLogger(GkApplication.class);

//    @Autowired
//    private UserRepository userRepositoryImpl;
//
//    @Autowired
//    private RoleRepository roleRepositoryImpl;
//
//    @Autowired
//    private PermissionRepository permissionRepository;
    public static void main(String[] args) {

        SpringApplication.run(GkApplication.class, args);


    }

//    @Override
//    @Transactional
//    public void run(String... strings) throws Exception {
//        // save a couple of books
//        Permission p1 = new Permission();
//        p1.setPermissionName("发动十字军");
//
//        Role r1 = new Role();
//        r1.setRoleName("教监");
//        Set<Permission> permissionSet = new HashSet<Permission>();
//        permissionSet.add(p1);
//        r1.setPermissionSet(permissionSet);
//
//        User u1 = new User();
//        u1.setUserName("天主孝子法兰西");
//        u1.setPassword("13588887777");
//        u1.setPhoneNum("15236654126");
//        u1.setSalt("gfhg3465");
//        Set<Role> roleSet = new HashSet<Role>();
//        roleSet.add(r1);
//        u1.setRoleSet(roleSet);
//
//        userRepositoryImpl.save(u1);

//        Publisher publisherC = new Publisher("Publisher C");
//
//        bookRepository.save(new HashSet<Book>(){{
//            add(new Book("Book A", new HashSet<Publisher>(){{
//                add(publisherA);
//                add(publisherB);
//            }}));
//
//            add(new Book("Book B", new HashSet<Publisher>(){{
//                add(publisherA);
//                add(publisherC);
//            }}));
//        }});
//
//        // fetch all books
//        for(Book book : bookRepository.findAll()) {
//            logger.info(book.toString());
//        }
//
//        // save a couple of publishers
//        Book bookA = new Book("Book A");
//        Book bookB = new Book("Book B");
//
//        publisherRepository.save(new HashSet<Publisher>() {{
//            add(new Publisher("Publisher A", new HashSet<Book>() {{
//                add(bookA);
//                add(bookB);
//            }}));
//
//            add(new Publisher("Publisher B", new HashSet<Book>() {{
//                add(bookA);
//                add(bookB);
//            }}));
//        }});
//
//        // fetch all publishers
//        for(Publisher publisher : publisherRepository.findAll()) {
//            logger.info(publisher.toString());
//        }
//    }


//    public UserRepository getUserRepositoryImpl() {
//        return userRepositoryImpl;
//    }
//
//    public void setUserRepositoryImpl(UserRepository userRepositoryImpl) {
//        this.userRepositoryImpl = userRepositoryImpl;
//    }
//
//    public RoleRepository getRoleRepositoryImpl() {
//        return roleRepositoryImpl;
//    }
//
//    public void setRoleRepositoryImpl(RoleRepository roleRepositoryImpl) {
//        this.roleRepositoryImpl = roleRepositoryImpl;
//    }
//
//    public PermissionRepository getPermissionRepository() {
//        return permissionRepository;
//    }
//
//    public void setPermissionRepository(PermissionRepository permissionRepository) {
//        this.permissionRepository = permissionRepository;
//    }

}
