package cn.shiyang.tflz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.shiyang.tflz.mapper")
public class TflzApplication {
    public static void main(String[] args) {
        SpringApplication.run(TflzApplication.class, args);
    }
}
