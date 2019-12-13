package br.com.caelum.workshop.workshopapibolao.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import br.com.caelum.workshop.workshopapibolao.shared.security.SystemUserDetails;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		Predicate<RequestHandler> basePackage = RequestHandlerSelectors.basePackage("br.com.caelum.workshop.workshopapibolao");
		Predicate<String> apiUrls = PathSelectors.ant("/api/**");
		return new Docket(DocumentationType.SWAGGER_2).select().apis(basePackage)
				.paths(apiUrls).build()
				.ignoredParameterTypes(SystemUserDetails.class)
				.globalOperationParameters(Arrays.asList(new ParameterBuilder().name("X-AUTH-TOKEN")
						.description("Description of header").modelRef(new ModelRef("string"))
						.parameterType("header").required(false).build()));		
		
	}
}
