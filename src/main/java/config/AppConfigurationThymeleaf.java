package config;

import model.Product;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import service.IProductService;
import service.ProductService;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan("controller")
public class AppConfigurationThymeleaf implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    public IProductService productService() {
        return new ProductService();
    }

    @Bean
    public Map<Integer, Product> productMap() {
        Map<Integer, Product> productMap;
        productMap = new LinkedHashMap<>();
        productMap.put(1, new Product(1, "Iphone 11", "Chanh sả", 20000000));
        productMap.put(2, new Product(2, "Iphone 6", "Không chanh sả lắm", 15000000));
        productMap.put(3, new Product(3, "Iphone 12", "Cực kỳ chanh sả", 25000000));
        productMap.put(4, new Product(4, "Samsung Note 7", "Hàng bình thường", 15000000));
        productMap.put(5, new Product(5, "Xiaomi A7", "Hàng nhái Iphone", 5000000));
        productMap.put(6, new Product(6, "Samsung Galaxy A3", "Hàng Lởm", 3000000));
        return productMap;
    }

    @Bean
    ModelAndView modelAndView() {
        return new ModelAndView();
    }
}