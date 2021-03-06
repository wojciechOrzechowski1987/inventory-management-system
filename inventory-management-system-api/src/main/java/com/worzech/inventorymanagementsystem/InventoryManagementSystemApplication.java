package com.worzech.inventorymanagementsystem;

import com.worzech.inventorymanagementsystem.domain.*;
import com.worzech.inventorymanagementsystem.domain.security.Role;
import com.worzech.inventorymanagementsystem.enums.Status;
import com.worzech.inventorymanagementsystem.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class InventoryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystemApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner dataLoader(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, DistrictRepository districtRepository, ProjectRepository projectRepository, PopcGroupRepository popcGroupRepository,
                                        PopcSubgroupRepository popcSubgroupRepository, PopcMaterialRepository popcMaterialRepository, ProductItemRepository productItemRepository, VendorRepository vendorRepository,
                                        DemandRepository demandRepository, DemandPopcMaterialRepository demandPopcMaterialRepository, PurchaseRepository purchaseRepository, PurchaseProductItemRepository purchaseProductItemRepository) {
        return args -> {

            User admin = new User("Admin", "Admin", "admin", passwordEncoder.encode("1234"), "admin@admin.pl", Role.ADMIN);
            User bia = new User("Bia", "Bia", "bia", passwordEncoder.encode("1234"), "bia@bia.pl", Role.COORDINATOR);
            User kol = new User("Kol", "Kol", "kol", passwordEncoder.encode("1234"), "kol@kol.pl", Role.COORDINATOR);
            User kos = new User("Kos", "Kos", "kos", passwordEncoder.encode("1234"), "kos@kos.pl", Role.COORDINATOR);
            User sla = new User("Sla", "Sla", "sla", passwordEncoder.encode("1234"), "sla@sla.pl", Role.COORDINATOR);

            userRepository.save(admin);
            userRepository.save(bia);
            userRepository.save(kol);
            userRepository.save(kos);
            userRepository.save(sla);

            District bialogard = new District("Bia??ogard", bia.getUsername());
            District kolobrzeg = new District("Ko??obrzeg", kol.getUsername());
            District koszalin = new District("Koszalin", kos.getUsername());
            District slawno = new District("S??awno", sla.getUsername());

            districtRepository.save(bialogard);
            districtRepository.save(kolobrzeg);
            districtRepository.save(koszalin);
            districtRepository.save(slawno);
            districtRepository.save(new District("Telekom Optic", admin.getUsername()));

            projectRepository.save(new Project("1001", "BIA??_BIA_BIA??OGARD_OLT_1", districtRepository.getById(1L), Status.DEMAND));
            projectRepository.save(new Project("1002", "BIA??_BIA_BIA??OGARD_SZKO??A_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1003", "BIA??_BIA_STANOMINO_SZKO??A_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1004", "BIA??_BIA_POMIANOWO_SZKO??A_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1005", "BIA??_TYC_DOBROWO_SZKO??A_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1006", "BIA??_TYC_TYCHOWO_OLT_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1007", "BIA??_BIA_ROGOWO_SZKO??A_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1008", "BIA??_TYC_PODBORSKO_SZKO??A_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1009", "BIA??_KAR_KARWIN_SZKO??A_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1010", "BIA??_BIA_????CZNO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1011", "BIA??_BIA_KAMOSOWO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1012", "BIA??_BIA_RO??CINO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1013", "BIA??_BIA_ZAG??RZE_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1014", "BIA??_BIA_RARWINO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1015", "BIA??_KAR_DOMACYNO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1016", "BIA??_BIA_SI??CE_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1017", "BIA??_BIA_LASKI_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1018", "BIA??_BIA_MOCZY??KI_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1019", "BIA??_BIA_D??BCZYNO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1020", "BIA??_BIA_ROGOWO_2", districtRepository.getById(1L)));

            projectRepository.save(new Project("2001", "KO??O_DYG_CZERNIN_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2002", "KO??O_DYG_DYGOWO_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2003", "KO??O_DYG_WRZOSOWO_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2004", "KO??O_GO??_GO??CINO_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2005", "KO??O_GO??_GO??CINO_SZKO??A_2", districtRepository.getById(2L)));
            projectRepository.save(new Project("2006", "KO??O_GO??_ROBU??_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2007", "KO??O_KO??_DRZONOWO_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2008", "KO??O_KO??_KORZYSTNO_2", districtRepository.getById(2L)));
            projectRepository.save(new Project("2009", "KO??O_RYM_DROZDOWO_1_SZKOLNY", districtRepository.getById(2L)));
            projectRepository.save(new Project("2010", "KO??O_RYM_RYMA??_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2011", "KO??O_SIE_CHARZYNO_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2012", "KO??O_UST_USTRONIE MORSKIE_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2013", "KO??O_KO??_D??WIRZYNO_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2014", "KO??O_RYM_GORAWINO_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2015", "KO??O_GO??_MO??OTOWO_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2016", "KO??O_SIE_SIEMY??L_SZKO??A_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2017", "KO??O_KO??_NOWOGARDEK_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2018", "KO??O_KO??_KORZYSTNO_1_SZKOLNY", districtRepository.getById(2L)));
            projectRepository.save(new Project("2019", "KO??O_KO??_GRZYBOWO_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2020", "KO??O_Go??_Dargocice_1_Szkolny", districtRepository.getById(2L)));

            projectRepository.save(new Project("3001", "KOSZ_B??D_DOBRZYCA_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3002", "KOSZ_B??D_??EKNO_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3003", "KOSZ_B??D_M??CICE_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3004", "KOSZ_BIE_BIESIEKIERZ_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3005", "KOSZ_BIE_STARE BIELICE_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3006", "KOSZ_BIE_??WIEMINO_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3007", "KOSZ_BIE_WARMINO_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3008", "KOSZ_BOB_DARGI??_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3009", "KOSZ_BOB_DRZEWIANY_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3010", "KOSZ_BOB_K??ANINO_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3011", "KOSZ_BOB_KUROWO_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3012", "KOSZ_MAN_ROSNOWO_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3013", "KOSZ_MIE_MIELNO_SZKO??A_2", districtRepository.getById(3L)));
            projectRepository.save(new Project("3014", "KOSZ_MIE_SARBINOWO_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3015", "KOSZ_POL_NAC??AW_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3016", "KOSZ_POL_BUKOWO_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3017", "KOSZ_POL_POLAN??W_SZKO??A_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3018", "KOSZ_POL_POLAN??W_SZKO??A_2", districtRepository.getById(3L)));
            projectRepository.save(new Project("3019", "KOSZ_POL_POLAN??W_SZKO??A_3", districtRepository.getById(3L)));
            projectRepository.save(new Project("3020", "KOSZ_POL_??YDOWO_SZKO??A_1", districtRepository.getById(3L)));


            projectRepository.save(new Project("4001", "S??AW_S??A_??UKOWO_SZKO??A", districtRepository.getById(4L)));
            projectRepository.save(new Project("4002", "S??AW_S??A_BOBROWICE_SZKO??A", districtRepository.getById(4L)));
            projectRepository.save(new Project("4003", "S??AW_S??A_POMI??OWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4004", "S??AW_S??A_GWIAZDOWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4005", "S??AW_S??A_BRZE??CIE", districtRepository.getById(4L)));
            projectRepository.save(new Project("4006", "S??AW_S??A_JANIEWICE", districtRepository.getById(4L)));
            projectRepository.save(new Project("4007", "S??AW_S??A_????TOWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4008", "S??AW_S??A_KWASOWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4009", "S??AW_S??A_S??AWNO_1", districtRepository.getById(4L)));
            projectRepository.save(new Project("4010", "S??AW_S??A_S??AWNO_2", districtRepository.getById(4L)));
            projectRepository.save(new Project("4011", "S??AW_S??A_S??AWNO_3", districtRepository.getById(4L)));
            projectRepository.save(new Project("4012", "S??AW_S??A_S??AWNO_4", districtRepository.getById(4L)));
            projectRepository.save(new Project("4013", "S??AW_S??A_BOBROWICZKI", districtRepository.getById(4L)));
            projectRepository.save(new Project("4014", "S??AW_S??A_BOBROWICE", districtRepository.getById(4L)));
            projectRepository.save(new Project("4015", "S??AW_S??A_RZYSZCZEWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4016", "S??AW_S??A_SMARDZEWO_S??AWNO_SZKOLNY", districtRepository.getById(4L)));
            projectRepository.save(new Project("4017", "S??AW_S??A_S??AWNO_5", districtRepository.getById(4L)));
            projectRepository.save(new Project("4018", "S??AW_S??A_WARSZKOWO_1", districtRepository.getById(4L)));
            projectRepository.save(new Project("4019", "S??AW_S??A_WARSZKOWO_2", districtRepository.getById(4L)));
            projectRepository.save(new Project("4020", "S??AW_S??A_WARSZKOWO_3", districtRepository.getById(4L)));

            projectRepository.save(new Project("9999", "Pusty1"));
            projectRepository.save(new Project("9998", "Pusty2"));
            projectRepository.save(new Project("9997", "Pusty3"));
            projectRepository.save(new Project("9996", "Pusty4"));
            projectRepository.save(new Project("9995", "Pusty5"));
            projectRepository.save(new Project("9994", "Pusty6"));
            projectRepository.save(new Project("9993", "Pusty7"));
            projectRepository.save(new Project("9992", "Pusty8"));
            projectRepository.save(new Project("9991", "Pusty9"));
            projectRepository.save(new Project("9990", "Pusty10"));
            projectRepository.save(new Project("9989", "Pusty11"));
            projectRepository.save(new Project("9988", "Pusty12"));
            projectRepository.save(new Project("9987", "Pusty13"));
            projectRepository.save(new Project("9986", "Pusty14"));


            popcGroupRepository.save(new PopcGroup("PAKIETY I RURY"));
            popcGroupRepository.save(new PopcGroup("??WIAT??OWODY"));
            popcGroupRepository.save(new PopcGroup("OSPRZ??T OPTYCZNY"));
            popcGroupRepository.save(new PopcGroup("STUDNIE I TA??MY"));
            popcGroupRepository.save(new PopcGroup("OLT"));
            popcSubgroupRepository.save(new PopcSubgroup("PAKIET", popcGroupRepository.getById(1L)));
            popcSubgroupRepository.save(new PopcSubgroup("HDPE", popcGroupRepository.getById(1L)));
            popcSubgroupRepository.save(new PopcSubgroup("HDPE Z????CZKI", popcGroupRepository.getById(1L)));
            popcSubgroupRepository.save(new PopcSubgroup("DAC", popcGroupRepository.getById(2L)));
            popcSubgroupRepository.save(new PopcSubgroup("MIKROKABEL", popcGroupRepository.getById(2L)));
            popcSubgroupRepository.save(new PopcSubgroup("KABEL KANA??OWY", popcGroupRepository.getById(2L)));
            popcSubgroupRepository.save(new PopcSubgroup("PRZE????CZNICE ZEWN??TRZNE", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("PRZE????CZNICE WEWN??TRZNE", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("SPLITTER", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("MUFA", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("AKCESORIA OPTYKA", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("STUDNIA", popcGroupRepository.getById(4L)));
            popcSubgroupRepository.save(new PopcSubgroup("TA??MY", popcGroupRepository.getById(4L)));
            popcSubgroupRepository.save(new PopcSubgroup("OLT", popcGroupRepository.getById(5L)));

            popcSubgroupRepository.save(new PopcSubgroup("TESTOWA"));

            popcMaterialRepository.save(new PopcMaterial("12x2,0", "Pojedy??cza mikrorurka grubo??cienna 12/8mm", "Pojedy??cza mikrorurka grubo??cienna 12/8mm",
                    popcSubgroupRepository.getById(1L)));
            popcMaterialRepository.save(new PopcMaterial("14x2,0", "Pojedy??cza mikrorurka grubo??cienna 14/10mm", "Pojedy??cza mikrorurka grubo??cienna 14/10mm",
                    popcSubgroupRepository.getById(1L)));
            popcMaterialRepository.save(new PopcMaterial("4x12x2,0", "Pakiet mikrorur grubo??ciennych 4x12/8mm", "Pakiet-kwadrat mikrorur grubo??ciennych 4x12/8mm",
                    popcSubgroupRepository.getById(1L)));
            popcMaterialRepository.save(new PopcMaterial("4x14x2,0", "Pakiet mikrorur grubo??ciennych 4x14/10mm", "Pakiet-kwadrat mikrorur grubo??ciennych 4x14/10mm",
                    popcSubgroupRepository.getById(1L)));

            popcMaterialRepository.save(new PopcMaterial("HDPE.032", "Rura-HDPE 32/2,9mm", "Rura-HDPE 32/2,9mm odcinek 6m",
                    popcSubgroupRepository.getById(2L)));
            popcMaterialRepository.save(new PopcMaterial("HDPE.050", "Rura-HDPE 50/3,5mm", "Rura-HDPE 50/3,5mm odcinek 6m",
                    popcSubgroupRepository.getById(2L)));
            popcMaterialRepository.save(new PopcMaterial("HDPE.075", "Rura-HDPE 75/4,5mm", "Rura-HDPE 75/4,5mm odcinek 6m",
                    popcSubgroupRepository.getById(2L)));
            popcMaterialRepository.save(new PopcMaterial("HDPE.110", "Rura-HDPE 110/6,3mm", "Rura-HDPE 110/6,3mm odcinek 6m",
                    popcSubgroupRepository.getById(2L)));

            popcMaterialRepository.save(new PopcMaterial("12_CAP", "Zatyczka (endcap) mikrorurki 12mm", "Zatyczka (endcap) mikrorurki 12mm",
                    popcSubgroupRepository.getById(3L)));
            popcMaterialRepository.save(new PopcMaterial("14_CAP", "Zatyczka (endcap) mikrorurki 14mm", "Zatyczka (endcap) mikrorurki 14mm",
                    popcSubgroupRepository.getById(3L)));
            popcMaterialRepository.save(new PopcMaterial("USZCZ_MIKRO_MIKRO_12", "Uszczelnienie mikrorurki 12mm", "Z????czka prosta mikrorurki 12mm",
                    popcSubgroupRepository.getById(3L)));
            popcMaterialRepository.save(new PopcMaterial("USZCZ_MIKRO_MIKRO_14", "Uszczelnienie mikrorurki 14mm", "Z????czka prosta mikrorurki 14mm",
                    popcSubgroupRepository.getById(3L)));
            popcMaterialRepository.save(new PopcMaterial("Z????CZ_12", "Z????czka prosta mikrorurki 12mm", "Z????czka prosta mikrorurki 12mm",
                    popcSubgroupRepository.getById(3L)));
            popcMaterialRepository.save(new PopcMaterial("Z????CZ_14", "Z????czka prosta mikrorurki 14mm", "Z????czka prosta mikrorurki 14mm",
                    popcSubgroupRepository.getById(3L)));

            popcMaterialRepository.save(new PopcMaterial("DAC_002J", "Kabel ??wiat??owodowy zewn??trzny DAC 2 w????knowy G.657A1", "Kabel ??wiat??owodowy zewn??trzny DAC 2 w????knowy",
                    popcSubgroupRepository.getById(4L)));

            popcMaterialRepository.save(new PopcMaterial("LTMC_012", "Mikrokabel ??wiat??owodowy 12J", "Kabel (mikro) ??wiat??owodowy  12 w????knowy (1*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_024", "Mikrokabel ??wiat??owodowy 24J", "Kabel (mikro) ??wiat??owodowy LTMC 24 w????knowy (2*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_036", "Mikrokabel ??wiat??owodowy 36J)", "Kabel (mikro) ??wiat??owodowy LTMC 36 w????knowy (3*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_048", "Mikrokabel ??wiat??owodowy 48J", "Kabel (mikro) ??wiat??owodowy LTMC 48 w????knowy (4*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_072", "Mikrokabel ??wiat??owodowy 72J", "Kabel (mikro) ??wiat??owodowy LTMC 72 w????knowy (6*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_096", "Mikrokabel ??wiat??owodowy 96J", "Kabel (mikro) ??wiat??owodowy LTMC 96 w????knowy (8*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_0144", "Mikrokabel ??wiat??owodowy 144J", "Kabel (mikro) ??wiat??owodowy LTMC 144 w????knowy (12*12) G.657A1,",
                    popcSubgroupRepository.getById(5L)));

            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_012", "Kabel kana??owy ??wiat??owodowy 12J", "Kabel kana??owy ??wiat??owodowy 12 w????knowy (1*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_024", "Kabel kana??owy ??wiat??owodowy 24J", "Kabel kana??owy ??wiat??owodowy 24 w????knowy (2*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_036", "Kabel kana??owy ??wiat??owodowy 36J", "Kabel kana??owy ??wiat??owodowy 36 w????knowy (3*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_048", "Kabel kana??owy ??wiat??owodowy 48J", "Kabel kana??owy ??wiat??owodowy 48 w????knowy (4*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_072", "Kabel kana??owy ??wiat??owodowy 72J", "Kabel kana??owy ??wiat??owodowy 72 w????knowy (6*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_096", "Kabel kana??owy ??wiat??owodowy 96J", "Kabel kana??owy ??wiat??owodowy 96 w????knowy (8*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_144", "Kabel kana??owy ??wiat??owodowy 144J", "Kabel kana??owy ??wiat??owodowy 144 w????knowy (12*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));

            popcMaterialRepository.save(new PopcMaterial("N_SLUPEK_RF", "S??upek RF na 12 HP", "S??upek RF na 12 HP, 4 porte liniowe, 2 tacki spaw??w",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-SLUPEK-RF-PLUS", "Neptun s??upek RF PLUS", "Neptun s??upek RF PLUS wyposa??ony w insert",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("DLAWNICA_RF", "D??awica kablowa do s??upka RF", "D??awica kablowa do s??upka RF",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-VICTORIA-P-36HP+5L", "PGz36 - Prze????cznica ??wiat??owodowa zewn??trzna (do 36 spaw??w)", "Prze????cznica ??wiat??owodowa zewn??trzna (do 36 spaw??w)",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-VICTORIA-P-48HP+3L", "PGz48 - Prze????cznica ??wiat??owodowa zewn??trzna (do 48 spaw??w)", "Prze????cznica ??wiat??owodowa zewn??trzna (do 48 spaw??w)",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-VICTORIA-P-72HP+5L", "PGz72 - Prze????cznica ??wiat??owodowa zewn??trzna (do 72 spaw??w)", "Prze????cznica ??wiat??owodowa zewn??trzna (do 72 spaw??w)",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-MARS-P-288HP+6L", "PGz288 - Prze????cznica ??wiat??owodowa zewn??trzna (do 288 spaw??w)", "Prze????cznica ??wiat??owodowa zewn??trzna (do 288 spaw??w)",
                    popcSubgroupRepository.getById(7L)));


            popcMaterialRepository.save(new PopcMaterial("EZ_BOX+ADAPTER_06", "PDw6 - Prze????cznica EZ_BOX 6 adapter??w", "Prze????cznica ??wiat??owodowa wewn??trzna (do 6 spaw??w)",
                    popcSubgroupRepository.getById(8L)));
            popcMaterialRepository.save(new PopcMaterial("EZ_BOX+ADAPTER_12", "PDw6 - Prze????cznica EZ_BOX 12 adapter??w", "Prze????cznica ??wiat??owodowa wewn??trzna (do 12 spaw??w)",
                    popcSubgroupRepository.getById(8L)));
            popcMaterialRepository.save(new PopcMaterial("EZ_BOX_LONG_18", "PDw6 - Prze????cznica EZ_BOX 18 adapter??w", "Prze????cznica ??wiat??owodowa wewn??trzna (do 18 spaw??w)",
                    popcSubgroupRepository.getById(8L)));
            popcMaterialRepository.save(new PopcMaterial("EZ_BOX_LONG_24", "PDw6 - Prze????cznica EZ_BOX 24 adaptery", "Prze????cznica ??wiat??owodowa wewn??trzna (do 24 spaw??w)",
                    popcSubgroupRepository.getById(8L)));


            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/2", "Sprz??gacz optyczny 1 wej??cie 2 wyj??cia, ABS (BOX)", "Sprz??gacz optyczny 1 wej??cie 2 wyj??cia, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/4", "Sprz??gacz optyczny 1 wej??cie 4 wyj??cia, ABS (BOX)", "Sprz??gacz optyczny 1 wej??cie 4 wyj??cia, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial(" SPLIT_ABS_1/8", "Sprz??gacz optyczny 1 wej??cie 8 wyj??cia, ABS (BOX)", "Sprz??gacz optyczny 1 wej??cie 8 wyj??cia, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/16", "Sprz??gacz optyczny 1 wej??cie 16 wyj????, ABS (BOX)", "Sprz??gacz optyczny 1 wej??cie 16 wyj????, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/32", "Sprz??gacz optyczny 1 wej??cie 32 wyj????, ABS (BOX)", "Sprz??gacz optyczny 1 wej??cie 32 wyj????, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/64", "Sprz??gacz optyczny 1 wej??cie 64 wyj????, ABS (BOX)", "Sprz??gacz optyczny 1 wej??cie 64 wyj????, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));


            popcMaterialRepository.save(new PopcMaterial("MUFA_ABON", "Mufa abonencka kompletna", "Mufa abonencka kompletna",
                    popcSubgroupRepository.getById(10L)));
            popcMaterialRepository.save(new PopcMaterial("MUFA_LIN_012", "Mufa liniowa na 12 spaw??w kompletna", "Mufa liniowa na 12 spaw??w kompletna",
                    popcSubgroupRepository.getById(10L)));
            popcMaterialRepository.save(new PopcMaterial("MUFA_LIN_024", "Mufa liniowa na 24 spawy kompletna", "Mufa liniowa na 24 spawy kompletna",
                    popcSubgroupRepository.getById(10L)));
            popcMaterialRepository.save(new PopcMaterial("MUFA_LIN_048", "Mufa liniowa na 48 spaw??w kompletna", "Mufa liniowa na 48 spaw??w kompletna",
                    popcSubgroupRepository.getById(10L)));

            popcMaterialRepository.save(new PopcMaterial("T-A-SM-SCA-CAP-TRANS", "Adapter SM SC APC simplex ", "Adapter SM SC APC simplex transparentny",
                    popcSubgroupRepository.getById(11L)));
            popcMaterialRepository.save(new PopcMaterial("T-A-SM-SCADX-CAP-TRANS", "Adapter sm SC APC duplex", "Adapter sm SC APC duplex with transparentny",
                    popcSubgroupRepository.getById(11L)));
            popcMaterialRepository.save(new PopcMaterial("PGT-9-SCA-02-YL", "Pigtail 1 w????knowy G.652D ", "Pigtail 1 w????knowy G.652D 0,9mm SC/APC 2.5mb",
                    popcSubgroupRepository.getById(11L)));

            popcMaterialRepository.save(new PopcMaterial("STUD.SKR1", "Studnia betonowa SKR1", "Studnia betonowa SKR1 kompletna",
                    popcSubgroupRepository.getById(12L)));
            popcMaterialRepository.save(new PopcMaterial("STUD.SK2", "Studnia betonowa SK2", "Studnia betonowa SK2 kompletna",
                    popcSubgroupRepository.getById(12L)));
            popcMaterialRepository.save(new PopcMaterial("ZK_1", "Zasobnik kablowy ZK_1", "Zasobnik kablowy ZK_1 kompletna",
                    popcSubgroupRepository.getById(12L)));

            popcMaterialRepository.save(new PopcMaterial("TA??MA.OCHR.10_OPT", "Ta??ma ochronna 10cm", "Ta??ma ochronna 10cm z napisem ostrzegawczym",
                    popcSubgroupRepository.getById(13L)));

            popcMaterialRepository.save(new PopcMaterial("OSU-19-36U-1000-1050W", "Szafa zewn??trzna 36U, 19??? tr??jkomorowa", "Szafa zewn??trzna 36U, 19??? tr??jkomorowa",
                    popcSubgroupRepository.getById(14L)));
            popcMaterialRepository.save(new PopcMaterial("OSX-ZEST-EL", "Zestaw wyposa??enia elektrycznego", "Zestaw wyposa??enia elektrycznego",
                    popcSubgroupRepository.getById(14L)));
            popcMaterialRepository.save(new PopcMaterial("ZESTAW-00527", "Zestaw wyposa??enia transmisyjnego", "Zestaw wyposa??enia transmisyjnego",
                    popcSubgroupRepository.getById(14L)));


            vendorRepository.save(new Vendor("BKT Elektronik"));
            vendorRepository.save(new Vendor("CC Partners"));
            vendorRepository.save(new Vendor("Cellco Communications"));
            vendorRepository.save(new Vendor("FCA Sp. z o.o."));
            vendorRepository.save(new Vendor("Vavin Polska S.A."));



            productItemRepository.save(new ProductItem("Kabel FO BKT abonencki typu DAC", 0.6077, popcMaterialRepository.getById(15L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Mikrokabel FO BKT 12E9/125 1x12 5.3 mm 750N AF11", 1.0609, popcMaterialRepository.getById(16L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Mikrokabel FO BKT 24E9/125 2x12 5.3 mm 750N AF11", 1.5244, popcMaterialRepository.getById(17L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Mikrokabel FO BKT 36E9/125 3x12 5.3 mm 750N AF11", 2.399, popcMaterialRepository.getById(18L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Mikrokabel FO BKT 48E9/125 4x12 6,8 mm 750N AF11",2.5647, popcMaterialRepository.getById(19L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Mikrokabel FO BKT 72E9/125 6x12 5.3 mm 750N AF11",3.4402, popcMaterialRepository.getById(20L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Mikrokabel FO BKT 96E9/125 8x12 6.3 mm 750N AF11", 4.5526, popcMaterialRepository.getById(21L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Szafa zewn??trzna tr??jkomorowa GB3KLIM",15000.0, popcMaterialRepository.getById(58L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Osprz??t elektryczny GB3KLIM_UPGRADE",1854.50, popcMaterialRepository.getById(59L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Wyposa??enie transmisyjne GB3KLIM_TRANS", 35944.23, popcMaterialRepository.getById(60L), vendorRepository.getById(1L)));

            productItemRepository.save(new ProductItem("Kabel ??wiat??owodowy zewn??trzny DAC 2 w????knowy G.657A1", 0.51, popcMaterialRepository.getById(15L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) ??wiat??owodowy LTMC 12 w????knowy G.657A1 ", 0.77, popcMaterialRepository.getById(16L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) ??wiat??owodowy LTMC 24 w????knowy G.657A1 ", 1.15, popcMaterialRepository.getById(17L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) ??wiat??owodowy LTMC 48 w????knowy G.657A1", 1.67, popcMaterialRepository.getById(19L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) ??wiat??owodowy LTMC 72 w????knowy G.657A1",2.32, popcMaterialRepository.getById(20L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) ??wiat??owodowy LTMC 96 w????knowy",3.225, popcMaterialRepository.getById(21L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) ??wiat??owodowy LTMC PA 144 w????knowy G.657A1", 4.515, popcMaterialRepository.getById(22L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Mufa abonencka FHSC", 150.00, popcMaterialRepository.getById(47L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Mufa liniowa 12 spaw??w", 170.00, popcMaterialRepository.getById(48L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Mufa liniowa 24 spawy", 220.00, popcMaterialRepository.getById(49L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Mufa liniowa 48 spawy", 245.00, popcMaterialRepository.getById(50L), vendorRepository.getById(2L)));


            productItemRepository.save(new ProductItem("Kabel zewn. 9/125x02 FOXX DAC G.657.A1 A-yarn 1200N", 0.4380, popcMaterialRepository.getById(15L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Runfiber mikro kabel zewn. 9/125x12 Runfiber GX", 0.6574, popcMaterialRepository.getById(16L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Runfiber mikro kabel zewn. 9/125x24 Runfiber GX", 0.9497, popcMaterialRepository.getById(17L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Runfiber mikro kabel zewn. 9/125x36 Runfiber GX", 1.2337, popcMaterialRepository.getById(18L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Runfiber mikro kabel zewn. 9/125x48 Runfiber GX", 1.5459, popcMaterialRepository.getById(19L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Runfiber mikro kabel zewn. 9/125x72 Runfiber GX", 2.1543, popcMaterialRepository.getById(20L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Runfiber mikro kabel zewn. 9/125x96 Runfiber GX", 2.7988, popcMaterialRepository.getById(21L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Runfiber mikro kabel zewn. 9/125x144 Runfiber GX", 4.2125, popcMaterialRepository.getById(22L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("K-CMF-O-J9-012/06-FOXX-DMMTP", 1.0683, popcMaterialRepository.getById(23L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("K-CMF-O-J9-024/06-FOXX-DMMTP", 1.3885, popcMaterialRepository.getById(24L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("K-CMF-O-J9-036/06-FOXX-DMMTP", 1.8643, popcMaterialRepository.getById(25L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("K-CMF-O-J9-048/06-FOXX-DMMTP", 2.0584, popcMaterialRepository.getById(26L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("K-CMF-O-J9-072/06-FOXX-DMMTP", 2.8158, popcMaterialRepository.getById(27L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("K-CMF-O-J9-144/06-FOXX-DMMTP", 4.8598, popcMaterialRepository.getById(29L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Neptun s??upek RF", 299.00, popcMaterialRepository.getById(30L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Neptun s??upek RF PLUS", 509.00, popcMaterialRepository.getById(31L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("D??awica kablowa do s??upka RF", 5.3, popcMaterialRepository.getById(32L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Prze????cznica Victoria P wyposa??ona w 18 adaptery SC DXAPC", 608.00, popcMaterialRepository.getById(33L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Prze????cznica Victoria P wyposa??ona w 24 adaptery SC DXAPC", 629.00, popcMaterialRepository.getById(34L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Prze????cznica Victoria P wyposa??ona w 36 adaptery SC DXAPC", 719.00, popcMaterialRepository.getById(35L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Neptun MARS P wyposa??ony w 288 adapetery SC APC", 3741.00, popcMaterialRepository.getById(36L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("EZ BOX LONG na 6 spaw??w", 105.4, popcMaterialRepository.getById(37L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("EZ BOX LONG na 12 spaw??w", 115.7, popcMaterialRepository.getById(38L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("KEZ BOX LONG na 18 spaw??w", 121.1, popcMaterialRepository.getById(39L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("EZ BOX LONG na 24 spawy", 145.2, popcMaterialRepository.getById(40L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x2 ze z????czami SC/APC", 17.9742, popcMaterialRepository.getById(41L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x4 ze z????czami SC/APC", 18.4651, popcMaterialRepository.getById(42L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x8 ze z????czami SC/APC", 27.1879, popcMaterialRepository.getById(43L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x16 ze z????czami SC/APC", 47.5789, popcMaterialRepository.getById(44L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x32 ze z????czami SC/APC", 83.5273, popcMaterialRepository.getById(45L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x64 ze z????czami SC/APC", 213.614, popcMaterialRepository.getById(46L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Mufa hermetyczna, abonencka Spider", 207.6855, popcMaterialRepository.getById(47L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Mufa hermetyczna FOXX Optima na 12 spaw??w", 160.32, popcMaterialRepository.getById(48L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Mufa hermetyczna FOXX Optima na 24 spawy", 192.45, popcMaterialRepository.getById(49L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Mufa hermetyczna FOXX Optima na 48 spaw??w", 212.685, popcMaterialRepository.getById(50L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Adapter SM SC APC simplex with transparent cap", 0.75, popcMaterialRepository.getById(51L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Adapter sm SC APC duplex with transparent cap", 1.5, popcMaterialRepository.getById(52L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Pigtail 9/125 SC APC 2m ??????ty", 1.77, popcMaterialRepository.getById(53L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Studnia SKR1", 489.0, popcMaterialRepository.getById(54L), vendorRepository.getById(4L)));
            productItemRepository.save(new ProductItem("Studnia SK2", 350.0, popcMaterialRepository.getById(55L), vendorRepository.getById(4L)));
            productItemRepository.save(new ProductItem("Zasobnik kablowy ZK1", 203.5, popcMaterialRepository.getById(56L), vendorRepository.getById(4L)));
            productItemRepository.save(new ProductItem("Ta??ma ochronna pomara??czowa", 0.12, popcMaterialRepository.getById(57L), vendorRepository.getById(4L)));

            productItemRepository.save(new ProductItem("AROTNOVOMICRODB12x2,0*UDPOMA1700", 0.7, popcMaterialRepository.getById(1L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROTNOVOMICRODB14x2,0*UDPOMA1100", 0.87, popcMaterialRepository.getById(2L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROTNOVOSPLIT4*12x2,0*UDPOMA1250", 3.5, popcMaterialRepository.getById(3L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROTNOVOSPLIT 4*14x2,0*UDPOMA1000", 4.18, popcMaterialRepository.getById(4L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROT OPTO 32/2,9-CZAR/CZER L=250", 1.79, popcMaterialRepository.getById(5L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROT SRS 50 CZARNA BEZ KIELICHA L=6", 3.2, popcMaterialRepository.getById(6L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROT SRS 75 CZARNA BEZ KIELICHA L=6", 5.26, popcMaterialRepository.getById(7L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROT SRS-G 110/6,3-CZARNY L=6", 11.12, popcMaterialRepository.getById(8L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("ZATYCZKA NOVOSEAL 12", 0.6, popcMaterialRepository.getById(9L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("ZATYCZKA NOVOSEAL 14", 0.65, popcMaterialRepository.getById(10L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROT EWB-G 12/6,5-5 USZCZ/NOVOSEAL", 13.41, popcMaterialRepository.getById(11L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROT EWB-G 14/8,0-6,5 USZCZ/NOVOSEAL", 15.62, popcMaterialRepository.getById(12L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROT MM NOVOFIT Z??ACZKA 12", 4.96, popcMaterialRepository.getById(13L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROT MM NOVOFIT Z??ACZKA 14", 6.29, popcMaterialRepository.getById(14L), vendorRepository.getById(5L)));


            demandRepository.save(new Demand("1001_A", projectRepository.getById(1L), Status.DEMAND));
            demandRepository.save(new Demand("1001_B", projectRepository.getById(1L), Status.ORDERED));

            demandPopcMaterialRepository.save(new DemandPopcMaterial(1, popcMaterialRepository.getById(16L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(2, popcMaterialRepository.getById(17L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(3, popcMaterialRepository.getById(18L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(4, popcMaterialRepository.getById(19L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(5, popcMaterialRepository.getById(20L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(6, popcMaterialRepository.getById(21L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(1, popcMaterialRepository.getById(16L), demandRepository.getById(2L)));

            purchaseRepository.save(new Purchase(demandRepository.getById(2L)));

            purchaseProductItemRepository.save(new PurchaseProductItem(1, productItemRepository.getById(1L), purchaseRepository.getById(1L)));


        };
    }
}
