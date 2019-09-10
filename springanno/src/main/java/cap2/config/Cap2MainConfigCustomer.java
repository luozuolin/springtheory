package cap2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(value = "cap2",useDefaultFilters = false,includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {CustomTypeFilter.class})
})
public class Cap2MainConfigCustomer {
}
