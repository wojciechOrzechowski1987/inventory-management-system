package com.worzech.inventorymanagementsystem;

import com.worzech.inventorymanagementsystem.domain.*;
import com.worzech.inventorymanagementsystem.domain.security.Role;
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

            District bialogard = new District("Białogard", bia.getUsername());
            District kolobrzeg = new District("Kołobrzeg", kol.getUsername());
            District koszalin = new District("Koszalin", kos.getUsername());
            District slawno = new District("Sławno", sla.getUsername());

            districtRepository.save(bialogard);
            districtRepository.save(kolobrzeg);
            districtRepository.save(koszalin);
            districtRepository.save(slawno);
            districtRepository.save(new District("Telekom Optic", admin.getUsername()));

            projectRepository.save(new Project("1001", "BIAŁ_BIA_BIAŁOGARD_OLT_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1002", "BIAŁ_BIA_BIAŁOGARD_SZKOŁA_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1003", "BIAŁ_BIA_STANOMINO_SZKOŁA_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1004", "BIAŁ_BIA_POMIANOWO_SZKOŁA_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1005", "BIAŁ_TYC_DOBROWO_SZKOŁA_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1006", "BIAŁ_TYC_TYCHOWO_OLT_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1007", "BIAŁ_BIA_ROGOWO_SZKOŁA_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1008", "BIAŁ_TYC_PODBORSKO_SZKOŁA_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1009", "BIAŁ_KAR_KARWIN_SZKOŁA_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1010", "BIAŁ_BIA_ŁĘCZNO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1011", "BIAŁ_BIA_KAMOSOWO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1012", "BIAŁ_BIA_ROŚCINO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1013", "BIAŁ_BIA_ZAGÓRZE_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1014", "BIAŁ_BIA_RARWINO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1015", "BIAŁ_KAR_DOMACYNO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1016", "BIAŁ_BIA_SIŃCE_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1017", "BIAŁ_BIA_LASKI_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1018", "BIAŁ_BIA_MOCZYŁKI_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1019", "BIAŁ_BIA_DĘBCZYNO_1", districtRepository.getById(1L)));
            projectRepository.save(new Project("1020", "BIAŁ_BIA_ROGOWO_2", districtRepository.getById(1L)));

            projectRepository.save(new Project("2001", "KOŁO_DYG_CZERNIN_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2002", "KOŁO_DYG_DYGOWO_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2003", "KOŁO_DYG_WRZOSOWO_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2004", "KOŁO_GOŚ_GOŚCINO_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2005", "KOŁO_GOŚ_GOŚCINO_SZKOŁA_2", districtRepository.getById(2L)));
            projectRepository.save(new Project("2006", "KOŁO_GOŚ_ROBUŃ_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2007", "KOŁO_KOŁ_DRZONOWO_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2008", "KOŁO_KOŁ_KORZYSTNO_2", districtRepository.getById(2L)));
            projectRepository.save(new Project("2009", "KOŁO_RYM_DROZDOWO_1_SZKOLNY", districtRepository.getById(2L)));
            projectRepository.save(new Project("2010", "KOŁO_RYM_RYMAŃ_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2011", "KOŁO_SIE_CHARZYNO_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2012", "KOŁO_UST_USTRONIE MORSKIE_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2013", "KOŁO_KOŁ_DŹWIRZYNO_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2014", "KOŁO_RYM_GORAWINO_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2015", "KOŁO_GOŚ_MOŁOTOWO_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2016", "KOŁO_SIE_SIEMYŚL_SZKOŁA_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2017", "KOŁO_KOŁ_NOWOGARDEK_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2018", "KOŁO_KOŁ_KORZYSTNO_1_SZKOLNY", districtRepository.getById(2L)));
            projectRepository.save(new Project("2019", "KOŁO_KOŁ_GRZYBOWO_1", districtRepository.getById(2L)));
            projectRepository.save(new Project("2020", "KOŁO_Goś_Dargocice_1_Szkolny", districtRepository.getById(2L)));

            projectRepository.save(new Project("3001", "KOSZ_BĘD_DOBRZYCA_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3002", "KOSZ_BĘD_ŁEKNO_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3003", "KOSZ_BĘD_MŚCICE_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3004", "KOSZ_BIE_BIESIEKIERZ_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3005", "KOSZ_BIE_STARE BIELICE_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3006", "KOSZ_BIE_ŚWIEMINO_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3007", "KOSZ_BIE_WARMINO_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3008", "KOSZ_BOB_DARGIŃ_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3009", "KOSZ_BOB_DRZEWIANY_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3010", "KOSZ_BOB_KŁANINO_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3011", "KOSZ_BOB_KUROWO_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3012", "KOSZ_MAN_ROSNOWO_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3013", "KOSZ_MIE_MIELNO_SZKOŁA_2", districtRepository.getById(3L)));
            projectRepository.save(new Project("3014", "KOSZ_MIE_SARBINOWO_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3015", "KOSZ_POL_NACŁAW_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3016", "KOSZ_POL_BUKOWO_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3017", "KOSZ_POL_POLANÓW_SZKOŁA_1", districtRepository.getById(3L)));
            projectRepository.save(new Project("3018", "KOSZ_POL_POLANÓW_SZKOŁA_2", districtRepository.getById(3L)));
            projectRepository.save(new Project("3019", "KOSZ_POL_POLANÓW_SZKOŁA_3", districtRepository.getById(3L)));
            projectRepository.save(new Project("3020", "KOSZ_POL_ŻYDOWO_SZKOŁA_1", districtRepository.getById(3L)));


            projectRepository.save(new Project("4001", "SŁAW_SŁA_ŻUKOWO_SZKOŁA", districtRepository.getById(4L)));
            projectRepository.save(new Project("4002", "SŁAW_SŁA_BOBROWICE_SZKOŁA", districtRepository.getById(4L)));
            projectRepository.save(new Project("4003", "SŁAW_SŁA_POMIŁOWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4004", "SŁAW_SŁA_GWIAZDOWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4005", "SŁAW_SŁA_BRZEŚCIE", districtRepository.getById(4L)));
            projectRepository.save(new Project("4006", "SŁAW_SŁA_JANIEWICE", districtRepository.getById(4L)));
            projectRepository.save(new Project("4007", "SŁAW_SŁA_ŁĘTOWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4008", "SŁAW_SŁA_KWASOWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4009", "SŁAW_SŁA_SŁAWNO_1", districtRepository.getById(4L)));
            projectRepository.save(new Project("4010", "SŁAW_SŁA_SŁAWNO_2", districtRepository.getById(4L)));
            projectRepository.save(new Project("4011", "SŁAW_SŁA_SŁAWNO_3", districtRepository.getById(4L)));
            projectRepository.save(new Project("4012", "SŁAW_SŁA_SŁAWNO_4", districtRepository.getById(4L)));
            projectRepository.save(new Project("4013", "SŁAW_SŁA_BOBROWICZKI", districtRepository.getById(4L)));
            projectRepository.save(new Project("4014", "SŁAW_SŁA_BOBROWICE", districtRepository.getById(4L)));
            projectRepository.save(new Project("4015", "SŁAW_SŁA_RZYSZCZEWO", districtRepository.getById(4L)));
            projectRepository.save(new Project("4016", "SŁAW_SŁA_SMARDZEWO_SŁAWNO_SZKOLNY", districtRepository.getById(4L)));
            projectRepository.save(new Project("4017", "SŁAW_SŁA_SŁAWNO_5", districtRepository.getById(4L)));
            projectRepository.save(new Project("4018", "SŁAW_SŁA_WARSZKOWO_1", districtRepository.getById(4L)));
            projectRepository.save(new Project("4019", "SŁAW_SŁA_WARSZKOWO_2", districtRepository.getById(4L)));
            projectRepository.save(new Project("4020", "SŁAW_SŁA_WARSZKOWO_3", districtRepository.getById(4L)));

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
            popcGroupRepository.save(new PopcGroup("ŚWIATŁOWODY"));
            popcGroupRepository.save(new PopcGroup("OSPRZĘT OPTYCZNY"));
            popcGroupRepository.save(new PopcGroup("STUDNIE I TAŚMY"));
            popcGroupRepository.save(new PopcGroup("OLT"));
            popcSubgroupRepository.save(new PopcSubgroup("PAKIET", popcGroupRepository.getById(1L)));
            popcSubgroupRepository.save(new PopcSubgroup("HDPE", popcGroupRepository.getById(1L)));
            popcSubgroupRepository.save(new PopcSubgroup("HDPE ZŁĄCZKI", popcGroupRepository.getById(1L)));
            popcSubgroupRepository.save(new PopcSubgroup("DAC", popcGroupRepository.getById(2L)));
            popcSubgroupRepository.save(new PopcSubgroup("MIKROKABEL", popcGroupRepository.getById(2L)));
            popcSubgroupRepository.save(new PopcSubgroup("KABEL KANAŁOWY", popcGroupRepository.getById(2L)));
            popcSubgroupRepository.save(new PopcSubgroup("PRZEŁĄCZNICE ZEWNĘTRZNE", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("PRZEŁĄCZNICE WEWNĘTRZNE", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("SPLITTER", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("MUFA", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("AKCESORIA OPTYKA", popcGroupRepository.getById(3L)));
            popcSubgroupRepository.save(new PopcSubgroup("STUDNIA", popcGroupRepository.getById(4L)));
            popcSubgroupRepository.save(new PopcSubgroup("TAŚMY", popcGroupRepository.getById(4L)));
            popcSubgroupRepository.save(new PopcSubgroup("OLT", popcGroupRepository.getById(5L)));

            popcSubgroupRepository.save(new PopcSubgroup("TESTOWA"));

            popcMaterialRepository.save(new PopcMaterial("12x2,0", "Pojedyńcza mikrorurka grubościenna 12/8mm", "Pojedyńcza mikrorurka grubościenna 12/8mm",
                    popcSubgroupRepository.getById(1L)));
            popcMaterialRepository.save(new PopcMaterial("14x2,0", "Pojedyńcza mikrorurka grubościenna 14/10mm", "Pojedyńcza mikrorurka grubościenna 14/10mm",
                    popcSubgroupRepository.getById(1L)));
            popcMaterialRepository.save(new PopcMaterial("4x12x2,0", "Pakiet mikrorur grubościennych 4x12/8mm", "Pakiet-kwadrat mikrorur grubościennych 4x12/8mm",
                    popcSubgroupRepository.getById(1L)));
            popcMaterialRepository.save(new PopcMaterial("4x14x2,0", "Pakiet mikrorur grubościennych 4x14/10mm", "Pakiet-kwadrat mikrorur grubościennych 4x14/10mm",
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
            popcMaterialRepository.save(new PopcMaterial("USZCZ_MIKRO_MIKRO_12", "Uszczelnienie mikrorurki 12mm", "Złączka prosta mikrorurki 12mm",
                    popcSubgroupRepository.getById(3L)));
            popcMaterialRepository.save(new PopcMaterial("USZCZ_MIKRO_MIKRO_14", "Uszczelnienie mikrorurki 14mm", "Złączka prosta mikrorurki 14mm",
                    popcSubgroupRepository.getById(3L)));
            popcMaterialRepository.save(new PopcMaterial("ZŁĄCZ_12", "Złączka prosta mikrorurki 12mm", "Złączka prosta mikrorurki 12mm",
                    popcSubgroupRepository.getById(3L)));
            popcMaterialRepository.save(new PopcMaterial("ZŁĄCZ_14", "Złączka prosta mikrorurki 14mm", "Złączka prosta mikrorurki 14mm",
                    popcSubgroupRepository.getById(3L)));

            popcMaterialRepository.save(new PopcMaterial("DAC_002J", "Kabel światłowodowy zewnętrzny DAC 2 włóknowy G.657A1", "Kabel światłowodowy zewnętrzny DAC 2 włóknowy",
                    popcSubgroupRepository.getById(4L)));

            popcMaterialRepository.save(new PopcMaterial("LTMC_012", "Mikrokabel światłowodowy 12J", "Kabel (mikro) światłowodowy  12 włóknowy (1*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_024", "Mikrokabel światłowodowy 24J", "Kabel (mikro) światłowodowy LTMC 24 włóknowy (2*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_036", "Mikrokabel światłowodowy 36J)", "Kabel (mikro) światłowodowy LTMC 36 włóknowy (3*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_048", "Mikrokabel światłowodowy 48J", "Kabel (mikro) światłowodowy LTMC 48 włóknowy (4*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_072", "Mikrokabel światłowodowy 72J", "Kabel (mikro) światłowodowy LTMC 72 włóknowy (6*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_096", "Mikrokabel światłowodowy 96J", "Kabel (mikro) światłowodowy LTMC 96 włóknowy (8*12) G.657A1",
                    popcSubgroupRepository.getById(5L)));
            popcMaterialRepository.save(new PopcMaterial("LTMC_0144", "Mikrokabel światłowodowy 144J", "Kabel (mikro) światłowodowy LTMC 144 włóknowy (12*12) G.657A1,",
                    popcSubgroupRepository.getById(5L)));

            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_012", "Kabel kanałowy światłowodowy 12J", "Kabel kanałowy światłowodowy 12 włóknowy (1*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_024", "Kabel kanałowy światłowodowy 24J", "Kabel kanałowy światłowodowy 24 włóknowy (2*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_036", "Kabel kanałowy światłowodowy 36J", "Kabel kanałowy światłowodowy 36 włóknowy (3*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_048", "Kabel kanałowy światłowodowy 48J", "Kabel kanałowy światłowodowy 48 włóknowy (4*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_072", "Kabel kanałowy światłowodowy 72J", "Kabel kanałowy światłowodowy 72 włóknowy (6*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_096", "Kabel kanałowy światłowodowy 96J", "Kabel kanałowy światłowodowy 96 włóknowy (8*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));
            popcMaterialRepository.save(new PopcMaterial("Z_XOTKTSD_144", "Kabel kanałowy światłowodowy 144J", "Kabel kanałowy światłowodowy 144 włóknowy (12*12) G.657A1",
                    popcSubgroupRepository.getById(6L)));

            popcMaterialRepository.save(new PopcMaterial("N_SLUPEK_RF", "Słupek RF na 12 HP", "Słupek RF na 12 HP, 4 porte liniowe, 2 tacki spawów",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-SLUPEK-RF-PLUS", "Neptun słupek RF PLUS", "Neptun słupek RF PLUS wyposażony w insert",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("DLAWNICA_RF", "Dławica kablowa do słupka RF", "Dławica kablowa do słupka RF",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-VICTORIA-P-36HP+5L", "PGz36 - Przełącznica światłowodowa zewnętrzna (do 36 spawów)", "Przełącznica światłowodowa zewnętrzna (do 36 spawów)",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-VICTORIA-P-48HP+3L", "PGz48 - Przełącznica światłowodowa zewnętrzna (do 48 spawów)", "Przełącznica światłowodowa zewnętrzna (do 48 spawów)",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-VICTORIA-P-72HP+5L", "PGz72 - Przełącznica światłowodowa zewnętrzna (do 72 spawów)", "Przełącznica światłowodowa zewnętrzna (do 72 spawów)",
                    popcSubgroupRepository.getById(7L)));
            popcMaterialRepository.save(new PopcMaterial("N-MARS-P-288HP+6L", "PGz288 - Przełącznica światłowodowa zewnętrzna (do 288 spawów)", "Przełącznica światłowodowa zewnętrzna (do 288 spawów)",
                    popcSubgroupRepository.getById(7L)));


            popcMaterialRepository.save(new PopcMaterial("EZ_BOX+ADAPTER_06", "PDw6 - Przełącznica EZ_BOX 6 adapterów", "Przełącznica światłowodowa wewnętrzna (do 6 spawów)",
                    popcSubgroupRepository.getById(8L)));
            popcMaterialRepository.save(new PopcMaterial("EZ_BOX+ADAPTER_12", "PDw6 - Przełącznica EZ_BOX 12 adapterów", "Przełącznica światłowodowa wewnętrzna (do 12 spawów)",
                    popcSubgroupRepository.getById(8L)));
            popcMaterialRepository.save(new PopcMaterial("EZ_BOX_LONG_18", "PDw6 - Przełącznica EZ_BOX 18 adapterów", "Przełącznica światłowodowa wewnętrzna (do 18 spawów)",
                    popcSubgroupRepository.getById(8L)));
            popcMaterialRepository.save(new PopcMaterial("EZ_BOX_LONG_24", "PDw6 - Przełącznica EZ_BOX 24 adaptery", "Przełącznica światłowodowa wewnętrzna (do 24 spawów)",
                    popcSubgroupRepository.getById(8L)));


            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/2", "Sprzęgacz optyczny 1 wejście 2 wyjścia, ABS (BOX)", "Sprzęgacz optyczny 1 wejście 2 wyjścia, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/4", "Sprzęgacz optyczny 1 wejście 4 wyjścia, ABS (BOX)", "Sprzęgacz optyczny 1 wejście 4 wyjścia, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial(" SPLIT_ABS_1/8", "Sprzęgacz optyczny 1 wejście 8 wyjścia, ABS (BOX)", "Sprzęgacz optyczny 1 wejście 8 wyjścia, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/16", "Sprzęgacz optyczny 1 wejście 16 wyjść, ABS (BOX)", "Sprzęgacz optyczny 1 wejście 16 wyjść, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/32", "Sprzęgacz optyczny 1 wejście 32 wyjść, ABS (BOX)", "Sprzęgacz optyczny 1 wejście 32 wyjść, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));
            popcMaterialRepository.save(new PopcMaterial("SPLIT_ABS_1/64", "Sprzęgacz optyczny 1 wejście 64 wyjść, ABS (BOX)", "Sprzęgacz optyczny 1 wejście 64 wyjść, ABS (BOX)",
                    popcSubgroupRepository.getById(9L)));


            popcMaterialRepository.save(new PopcMaterial("MUFA_ABON", "Mufa abonencka kompletna", "Mufa abonencka kompletna",
                    popcSubgroupRepository.getById(10L)));
            popcMaterialRepository.save(new PopcMaterial("MUFA_LIN_012", "Mufa liniowa na 12 spawów kompletna", "Mufa liniowa na 12 spawów kompletna",
                    popcSubgroupRepository.getById(10L)));
            popcMaterialRepository.save(new PopcMaterial("MUFA_LIN_024", "Mufa liniowa na 24 spawy kompletna", "Mufa liniowa na 24 spawy kompletna",
                    popcSubgroupRepository.getById(10L)));
            popcMaterialRepository.save(new PopcMaterial("MUFA_LIN_048", "Mufa liniowa na 48 spawów kompletna", "Mufa liniowa na 48 spawów kompletna",
                    popcSubgroupRepository.getById(10L)));

            popcMaterialRepository.save(new PopcMaterial("T-A-SM-SCA-CAP-TRANS", "Adapter SM SC APC simplex ", "Adapter SM SC APC simplex transparentny",
                    popcSubgroupRepository.getById(11L)));
            popcMaterialRepository.save(new PopcMaterial("T-A-SM-SCADX-CAP-TRANS", "Adapter sm SC APC duplex", "Adapter sm SC APC duplex with transparentny",
                    popcSubgroupRepository.getById(11L)));
            popcMaterialRepository.save(new PopcMaterial("PGT-9-SCA-02-YL", "Pigtail 1 włóknowy G.652D ", "Pigtail 1 włóknowy G.652D 0,9mm SC/APC 2.5mb",
                    popcSubgroupRepository.getById(11L)));

            popcMaterialRepository.save(new PopcMaterial("STUD.SKR1", "Studnia betonowa SKR1", "Studnia betonowa SKR1 kompletna",
                    popcSubgroupRepository.getById(12L)));
            popcMaterialRepository.save(new PopcMaterial("STUD.SK2", "Studnia betonowa SK2", "Studnia betonowa SK2 kompletna",
                    popcSubgroupRepository.getById(12L)));
            popcMaterialRepository.save(new PopcMaterial("ZK_1", "Zasobnik kablowy ZK_1", "Zasobnik kablowy ZK_1 kompletna",
                    popcSubgroupRepository.getById(12L)));

            popcMaterialRepository.save(new PopcMaterial("TAŚMA.OCHR.10_OPT", "Taśma ochronna 10cm", "Taśma ochronna 10cm z napisem ostrzegawczym",
                    popcSubgroupRepository.getById(13L)));

            popcMaterialRepository.save(new PopcMaterial("OSU-19-36U-1000-1050W", "Szafa zewnętrzna 36U, 19” trójkomorowa", "Szafa zewnętrzna 36U, 19” trójkomorowa",
                    popcSubgroupRepository.getById(14L)));
            popcMaterialRepository.save(new PopcMaterial("OSX-ZEST-EL", "Zestaw wyposażenia elektrycznego", "Zestaw wyposażenia elektrycznego",
                    popcSubgroupRepository.getById(14L)));
            popcMaterialRepository.save(new PopcMaterial("ZESTAW-00527", "Zestaw wyposażenia transmisyjnego", "Zestaw wyposażenia transmisyjnego",
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
            productItemRepository.save(new ProductItem("Szafa zewnętrzna trójkomorowa GB3KLIM",15000.0, popcMaterialRepository.getById(58L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Osprzęt elektryczny GB3KLIM_UPGRADE",1854.50, popcMaterialRepository.getById(59L), vendorRepository.getById(1L)));
            productItemRepository.save(new ProductItem("Wyposażenie transmisyjne GB3KLIM_TRANS", 35944.23, popcMaterialRepository.getById(60L), vendorRepository.getById(1L)));

            productItemRepository.save(new ProductItem("Kabel światłowodowy zewnętrzny DAC 2 włóknowy G.657A1", 0.51, popcMaterialRepository.getById(15L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) światłowodowy LTMC 12 włóknowy G.657A1 ", 0.77, popcMaterialRepository.getById(16L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) światłowodowy LTMC 24 włóknowy G.657A1 ", 1.15, popcMaterialRepository.getById(17L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) światłowodowy LTMC 48 włóknowy G.657A1", 1.67, popcMaterialRepository.getById(19L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) światłowodowy LTMC 72 włóknowy G.657A1",2.32, popcMaterialRepository.getById(20L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) światłowodowy LTMC 96 włóknowy",3.225, popcMaterialRepository.getById(21L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Kabel (mikro) światłowodowy LTMC PA 144 włóknowy G.657A1", 4.515, popcMaterialRepository.getById(22L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Mufa abonencka FHSC", 150.00, popcMaterialRepository.getById(47L), vendorRepository.getById(2L)));
            productItemRepository.save(new ProductItem("Mufa liniowa 12 spawów", 170.00, popcMaterialRepository.getById(48L), vendorRepository.getById(2L)));
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

            productItemRepository.save(new ProductItem("Neptun słupek RF", 299.00, popcMaterialRepository.getById(30L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Neptun słupek RF PLUS", 509.00, popcMaterialRepository.getById(31L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Dławica kablowa do słupka RF", 5.3, popcMaterialRepository.getById(32L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Przełącznica Victoria P wyposażona w 18 adaptery SC DXAPC", 608.00, popcMaterialRepository.getById(33L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Przełącznica Victoria P wyposażona w 24 adaptery SC DXAPC", 629.00, popcMaterialRepository.getById(34L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Przełącznica Victoria P wyposażona w 36 adaptery SC DXAPC", 719.00, popcMaterialRepository.getById(35L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Neptun MARS P wyposażony w 288 adapetery SC APC", 3741.00, popcMaterialRepository.getById(36L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("EZ BOX LONG na 6 spawów", 105.4, popcMaterialRepository.getById(37L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("EZ BOX LONG na 12 spawów", 115.7, popcMaterialRepository.getById(38L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("KEZ BOX LONG na 18 spawów", 121.1, popcMaterialRepository.getById(39L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("EZ BOX LONG na 24 spawy", 145.2, popcMaterialRepository.getById(40L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x2 ze złączami SC/APC", 17.9742, popcMaterialRepository.getById(41L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x4 ze złączami SC/APC", 18.4651, popcMaterialRepository.getById(42L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x8 ze złączami SC/APC", 27.1879, popcMaterialRepository.getById(43L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x16 ze złączami SC/APC", 47.5789, popcMaterialRepository.getById(44L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x32 ze złączami SC/APC", 83.5273, popcMaterialRepository.getById(45L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Splitter NEPTUN PLC 1x64 ze złączami SC/APC", 213.614, popcMaterialRepository.getById(46L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Mufa hermetyczna, abonencka Spider", 207.6855, popcMaterialRepository.getById(47L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Mufa hermetyczna FOXX Optima na 12 spawów", 160.32, popcMaterialRepository.getById(48L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Mufa hermetyczna FOXX Optima na 24 spawy", 192.45, popcMaterialRepository.getById(49L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Mufa hermetyczna FOXX Optima na 48 spawów", 212.685, popcMaterialRepository.getById(50L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Adapter SM SC APC simplex with transparent cap", 0.75, popcMaterialRepository.getById(51L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Adapter sm SC APC duplex with transparent cap", 1.5, popcMaterialRepository.getById(52L), vendorRepository.getById(3L)));
            productItemRepository.save(new ProductItem("Pigtail 9/125 SC APC 2m żółty", 1.77, popcMaterialRepository.getById(53L), vendorRepository.getById(3L)));

            productItemRepository.save(new ProductItem("Studnia SKR1", 489.0, popcMaterialRepository.getById(54L), vendorRepository.getById(4L)));
            productItemRepository.save(new ProductItem("Studnia SK2", 350.0, popcMaterialRepository.getById(55L), vendorRepository.getById(4L)));
            productItemRepository.save(new ProductItem("Zasobnik kablowy ZK1", 203.5, popcMaterialRepository.getById(56L), vendorRepository.getById(4L)));
            productItemRepository.save(new ProductItem("Taśma ochronna pomarańczowa", 0.12, popcMaterialRepository.getById(57L), vendorRepository.getById(4L)));

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
            productItemRepository.save(new ProductItem("AROT MM NOVOFIT ZŁACZKA 12", 4.96, popcMaterialRepository.getById(13L), vendorRepository.getById(5L)));
            productItemRepository.save(new ProductItem("AROT MM NOVOFIT ZŁACZKA 14", 6.29, popcMaterialRepository.getById(14L), vendorRepository.getById(5L)));


          /*  demandRepository.save(new Demand("010001_A", projectRepository.getById(1L)));
            demandRepository.save(new Demand("010001_B", projectRepository.getById(1L)));


            demandPopcMaterialRepository.save(new DemandPopcMaterial(1, popcMaterialRepository.getById(16L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(2, popcMaterialRepository.getById(17L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(3, popcMaterialRepository.getById(18L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(4, popcMaterialRepository.getById(19L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(5, popcMaterialRepository.getById(20L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(6, popcMaterialRepository.getById(21L), demandRepository.getById(1L)));
            demandPopcMaterialRepository.save(new DemandPopcMaterial(1, popcMaterialRepository.getById(16L), demandRepository.getById(2L)));

            purchaseRepository.save(new Purchase(demandRepository.getById(1L)));

            purchaseProductItemRepository.save(new PurchaseProductItem(1, productItemRepository.getById(1L), purchaseRepository.getById(1L)));*/


        };
    }
}
