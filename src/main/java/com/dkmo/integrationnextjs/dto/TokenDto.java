package com.dkmo.integrationnextjs.dto;

import lombok.Builder;

@Builder
 public record TokenDto(String token, String refreshToken) {
// public static Builder builder(){
//     return new Builder();
// }
// public static class Builder {
// private String token;
// private String refreshToken;
//     public Builder token(String token){
//         this.token = token;
//         return this;
//     }
//     public Builder refreshToken(String refreshToken){
//         this.refreshToken = refreshToken;
//         return this;
//     }
//     public TokenDto build(){
//         return new TokenDto(token,refreshToken);
//     }
// }
}
