package com.dkmo.integrationnextjs.services;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUrl {
public static String urlFoto(OAuth2AuthorizedClient authorizedClient){
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://graph.facebook.com/v22.0/me?fields=picture&access_token=" + authorizedClient.getAccessToken().getTokenValue();
    JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
    @SuppressWarnings("null")
    String pictureUrl = jsonNode.get("picture").get("data").get("url").asText();
    return pictureUrl;
}
}
