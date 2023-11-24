package sgsits.cse.dis.administration.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * The type Swagger config.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Api key api key.
   *
   * @return the api key
   */
  private ApiKey apiKey() {
    return new ApiKey("JWT", "Authorization", "header");
  }

  /**
   * Security context security context.
   *
   * @return the security context
   */
  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  /**
   * Default auth list.
   *
   * @return the list
   */
  private List<SecurityReference> defaultAuth() {
    final AuthorizationScope authorizationScope = new AuthorizationScope("global",
        "accessEverything");
    final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
  }

  /**
   * Discoverers link discoverers.
   *
   * @return the link discoverers
   */
  @Bean
  public LinkDiscoverers discoverers() {
    final List<LinkDiscoverer> plugins = new ArrayList<>();
    plugins.add(new CollectionJsonLinkDiscoverer());
    return new LinkDiscoverers(SimplePluginRegistry.of(plugins));
  }

  /**
   * Api docket.
   *
   * @return the docket
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(metaData())
        .securityContexts(Collections.singletonList(securityContext()))
        .securitySchemes(Collections.singletonList(apiKey()))
        .select()
        .apis(RequestHandlerSelectors.basePackage("sgsits.cse.dis.administration"))
        .build();
  }

  /**
   * Meta data api info.
   *
   * @return the api info
   */
  private ApiInfo metaData() {
    return new ApiInfo(
        "DIS Administration Microservice",
        "API serves all controller related to administration.",
        "1.0",
        "http://localhost",
        new Contact("DIS Team", "sgsits.ac.in", "dis.cse.sgsits.app@gmail.com"),
        "License of API", "http://localhost", Collections.emptyList());
  }
}