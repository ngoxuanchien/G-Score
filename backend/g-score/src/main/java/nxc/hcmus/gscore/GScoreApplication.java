package nxc.hcmus.gscore;

import nxc.hcmus.gscore.service.StatisticService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GScoreApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            CsvService csvService
//    ) {
//        return args -> {
//            csvService.importDataFromCsv("E:\\workspace\\CV\\GoldenOwlAsia\\diem_thi_thpt_2024.csv");
//        };
//    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            StatisticService statisticService
//    ) {
//        return args -> {
//            var statistics = statisticService.getStatistics();
//            System.out.println(statistics);
//        };
//    }

}
