package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;


    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        if (outsourcedPartRepository.count() == 0) {
            //part1
            OutsourcedPart hybelecWheels = new OutsourcedPart();
            hybelecWheels.setCompanyName("Razor");
            hybelecWheels.setName("Hybrid Electric Wheels");
            hybelecWheels.setInv(100);
            hybelecWheels.setPrice(25.0);
            hybelecWheels.setId(2030L);
            hybelecWheels.setMinInv(0);
            hybelecWheels.setMaxInv(500);
            outsourcedPartRepository.save(hybelecWheels);
            //OutsourcedPart thePart=null;
            //part2
            OutsourcedPart normWheels = new OutsourcedPart();
            normWheels.setCompanyName("Amazon");
            normWheels.setName("Normal Wheels");
            normWheels.setInv(150);
            normWheels.setPrice(10.0);
            normWheels.setId(2040L);
            normWheels.setMinInv(0);
            normWheels.setMaxInv(500);
            outsourcedPartRepository.save(normWheels);
            //part3
            OutsourcedPart wheelScrewsEH = new OutsourcedPart();
            wheelScrewsEH.setCompanyName("Amazon");
            wheelScrewsEH.setName("Hybrid/Electric Whl Screws");
            wheelScrewsEH.setInv(200);
            wheelScrewsEH.setPrice(2.0);
            wheelScrewsEH.setId(200L);
            wheelScrewsEH.setMinInv(0);
            wheelScrewsEH.setMaxInv(1000);
            outsourcedPartRepository.save(wheelScrewsEH);
            //part 4
            OutsourcedPart normScrews = new OutsourcedPart();
            normScrews.setCompanyName("Amazon");
            normScrews.setName("Normal Whl Screws");
            normScrews.setInv(150);
            normScrews.setPrice(1.0);
            normScrews.setId(100L);
            normScrews.setMinInv(0);
            normScrews.setMaxInv(1000);
            outsourcedPartRepository.save(normScrews);
            //part 5
            OutsourcedPart handleSleeves = new OutsourcedPart();
            handleSleeves.setCompanyName("Amazon");
            handleSleeves.setName("Handle Bar Sleeves");
            handleSleeves.setInv(120);
            handleSleeves.setPrice(5.0);
            handleSleeves.setId(1000L);
            handleSleeves.setMinInv(0);
            handleSleeves.setMaxInv(1000);
            outsourcedPartRepository.save(handleSleeves);
            List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
            for (OutsourcedPart part : outsourcedParts) {
                if (part.getName().equals("Hybrid Electric Wheels")) /*thePart=part */ ;
                if (part.getName().equals("Normal Wheels")) ;
                if (part.getName().equals("Hybrid/Electric Whl Screws")) ;
                if (part.getName().equals("Normal Whl Screws")) ;
                if (part.getName().equals("Handle Bar Sleeves")) ;
            }
            //System.out.println(thePart.getCompanyName());
        }

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

//products
    if (productRepository.count() == 0) {
        Product electricScooter = new Product("Electric Scooter", 250.0, 60);
        Product heatwaveScooter = new Product("Heatwave Scooter", 89.0, 100);
        Product ultimateScooter = new Product("Ultimate Kid's Scooter", 60.0, 40);
        Product superElectricScooter = new Product("Super Electric Scooter", 275.0, 100);
        Product hybridScooter = new Product("Hybrid Scooter", 300.0, 90);
        productRepository.save(electricScooter);
        productRepository.save(heatwaveScooter);
        productRepository.save(ultimateScooter);
        productRepository.save(superElectricScooter);
        productRepository.save(hybridScooter);
    }

//create multi-pack part
    if (outsourcedPartRepository.count() == 0) {
        Set<String> uniquePart = new HashSet<String>();
        for (OutsourcedPart part : outsourcedParts) {
            if (!uniquePart.add(part.getName())) {
                OutsourcedPart multipack = new OutsourcedPart();
                multipack.setCompanyName(part.getCompanyName());
                multipack.setName("Multi-Pack" + part.getName());
                multipack.setId(2010L);
                multipack.setPrice(part.getPrice() * 2);
                multipack.setInv(part.getInv());
            }
        }
    }
//create multi-pack product
    List<Product> allProducts = (List<Product>) productRepository.findAll();
    if (productRepository.count() == 0) {
        Set<String> uniqueProduct = new HashSet<String>();
        for (Product product : allProducts) {
            if (!uniqueProduct.add(product.getName())) {
                Product multipackProd = new Product();
                multipackProd.setName("Multi-Pack" + product.getName());
                multipackProd.setId(1090L);
                multipackProd.setPrice(product.getPrice() * 2);
                multipackProd.setInv(product.getInv());
            }
        }
    }


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}

