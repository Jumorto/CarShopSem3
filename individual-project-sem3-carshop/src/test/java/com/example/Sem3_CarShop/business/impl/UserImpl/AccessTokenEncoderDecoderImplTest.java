package com.example.Sem3_CarShop.business.impl.UserImpl;

import com.example.Sem3_CarShop.business.exceptions.InvalidAccessTokenException;
import com.example.Sem3_CarShop.domain.UserDomain.AccessToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccessTokenEncoderDecoderImplTest {

    @InjectMocks//had to add the key because the constructor needs it as a param
    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl("E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5");

    @Test
    public void testEncodeAndDecode() {
        AccessToken accessToken = AccessToken.builder()
                .userId(1L)
                .email("test@example.com")
                .username("testuser")
                .description("Test Description")
                .phone("1234567890")
                .role(Arrays.asList("normal"))
                .build();

        String encodedAccessToken = accessTokenEncoderDecoder.encode(accessToken);
        AccessToken decodedAccessToken = accessTokenEncoderDecoder.decode(encodedAccessToken);

        assertEquals(accessToken.getUserId(), decodedAccessToken.getUserId());
        assertEquals(accessToken.getEmail(), decodedAccessToken.getEmail());
        assertEquals(accessToken.getUsername(), decodedAccessToken.getUsername());
        assertEquals(accessToken.getDescription(), decodedAccessToken.getDescription());
        assertEquals(accessToken.getPhone(), decodedAccessToken.getPhone());
        assertEquals(accessToken.getRole(), decodedAccessToken.getRole());
    }

    @Test
    public void testDecodeInvalidToken() {
        try {
            accessTokenEncoderDecoder.decode("invalid.token");
            fail("Expected InvalidAccessTokenException to be thrown");
        } catch (InvalidAccessTokenException ex) {
            // The exception was thrown as expected
        }
    }
}