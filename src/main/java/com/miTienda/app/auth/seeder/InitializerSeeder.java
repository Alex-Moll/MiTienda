package com.miTienda.app.auth.seeder;

import com.miTienda.app.model.entity.CategoryEntity;
import com.miTienda.app.model.entity.BrandEntity;
import com.miTienda.app.model.entity.ProviderEntity;
import com.miTienda.app.model.request.UserRequest;
import com.miTienda.app.repository.*;
import com.miTienda.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.stream.IntStream;

@Component
public class InitializerSeeder implements CommandLineRunner {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findAll().isEmpty()) {
            this.createUsers(1, "admin");
            this.createUsers(1, "user");
        }

//        if (proveedorRepository.findAll().isEmpty()) {
//            createProveedor();
//        }

//        if (categoriaRepository.findAll().isEmpty()) {
//            createCategoria();
//        }

//        if (clientRepository.findAll().isEmpty()) {
//            createCliente();
//        }

//        if (marcaRepository.findAll().isEmpty()) {
//            createMarca();
//        }

    }

    private void createUsers(int users, String userType) {
        final String PASSWORD = "1234";
        if (userType.equalsIgnoreCase("admin")){
            IntStream.range(0, users).forEach(userNumber ->
                    createAdmin(userType, PASSWORD, userNumber));
        }
        else{
            IntStream.range(0, users).forEach(userNumber ->
                    createNormalUser(userType, PASSWORD, userNumber));
        }
    }

    private void createNormalUser(String userType, String PASSWORD, Integer userNumber) {
        try {
            authService.register(new UserRequest(userType + userNumber, userType + userNumber,
                    "email"+ userType + userNumber +"@mail.com", PASSWORD, userType +"Photo"+ userNumber +".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createAdmin(String userType, String PASSWORD, Integer userNumber) {
        try {
            authService.registerAdmin(new UserRequest(userType + userNumber, userType + userNumber,
                    "email"+ userType + userNumber +"@mail.com", PASSWORD, userType +"Photo"+ userNumber +".png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private void createProveedor() {
//        try{
//            providerRepository.save(new ProviderEntity( "bora", "Aranguren 1253",
//                    "CABA", "2022565252", "iibb 12563", "351315555", "122",
//                    100d, new Timestamp(System.currentTimeMillis()), false));
//            providerRepository.save(new ProviderEntity( "Zest", "moron 5252",
//                    "CABA", "3266363365", "iibb 55636", "115252563", "134",
//                    200d, new Timestamp(System.currentTimeMillis()), false));
//            providerRepository.save(new ProviderEntity( "sistar", "cuenca 547",
//                    "CABA", "4125523365", "iibb 778587", "11455888", "145",
//                    300d, new Timestamp(System.currentTimeMillis()), false));
//        }catch(Exception ex){
//            throw new RuntimeException(ex);
//        }
//
//    }

    private void createCategoria(){
        try{
            categoryRepository.save(new CategoryEntity("familia-1", false));
            categoryRepository.save(new CategoryEntity("familia-2", false));
            categoryRepository.save(new CategoryEntity("familia-3", false));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void createMarca(){
        try{
            brandRepository.save(new BrandEntity("marca-1", false));
            brandRepository.save(new BrandEntity("marca-2", false));
            brandRepository.save(new BrandEntity("marca-3", false));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//    private void createCliente(){
//        try {
//            clientRepository.save(new ClientEntity("alejandro", "m28 l11", "cordoba",
//                    "20225625255", "I", "alex@gm.com", "351666666", "1",
//                    0d, 100000d, "obs", true, new Timestamp(System.currentTimeMillis()), false));
//            clientRepository.save(new ClientEntity("fer", "jujuy 1234", "santiago",
//                    "515151515151", "F", "fer@gm.com", "11552255", "2",
//                    0d, 400000d, "obs1", true, new Timestamp(System.currentTimeMillis()), false));
//            clientRepository.save(new ClientEntity("jose", "alameda 55236", "unquillo",
//                    "275555555", "M", "jose@gm.com", "35899669", "3",
//                    0d, 560000d, "obs2", true, new Timestamp(System.currentTimeMillis()), false));
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }

}
