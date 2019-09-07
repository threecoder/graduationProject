package com.lutayy.campbackend.common.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import com.lutayy.campbackend.pojo.request.TokenRequest;

public class JwtUtil {

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    public static <T> String sign(T object, long maxAge,String SECRET) {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch(Exception e) {
            return null;
        }
    }


    public static<T> T unsign(String jwt, Class<T> classT,String SECRET) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String,Object> claims= verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long)claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String)claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String [] args){
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setName("12312311223");
        String token = sign(tokenRequest, 60*1000*30 * 8L, "dnfslaAWRFaWR561wa&%==_+");
        System.out.println(token);
    }

}
