package knu.networksecuritylab.appserver.app.config;

import knu.networksecuritylab.appserver.app.common.BookRegisterRequestDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ConverterConfig implements WebMvcConfigurer {

    private final BookRegisterRequestDtoConverter bookRegisterRequestDtoConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(bookRegisterRequestDtoConverter);
    }
}
