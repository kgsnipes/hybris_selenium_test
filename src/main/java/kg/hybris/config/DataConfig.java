package kg.hybris.config;

import kg.hybris.dto.Address;
import kg.hybris.dto.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kaushik on 6/18/2017.
 */
@Configuration
public class DataConfig {

    @Bean
    @Qualifier("sampleShippingAddress")
    public Address addressSample1()
    {
        return new Address("GB","9323 town court south","","2052120608","kilo"+System.currentTimeMillis()+"@mailinator.com","NJ","Lawrence township","08648","mr","kaushik","ganguly");
    }

    @Bean
    @Qualifier("samplePaymentInfo")
    public Payment paymentSample()
    {
       return new Payment("001","test user","4111111111111111","4","2020","123");
    }



}
