package com.vladnickgofj.hotel.service.util;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class PasswordEncryptionServiceTest {
    private final PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("providePasswordEncryptedPasswordAndSalt")
    public void testOfAuthenticationUser(String encryptedPassword, String salt, String password, String message) throws NoSuchAlgorithmException, InvalidKeySpecException {
        assertEquals(true, passwordEncryptionService.authenticate(password, encryptedPassword, salt));
    }

    private static Stream<Arguments> providePasswordEncryptedPasswordAndSalt() {
        return Stream.of(Arguments.of("c2d7fada2740a044f84eca026bd8a74ca66839bc", "779d57716e19e2eb", "qwerty", "Test of easy password"),
                Arguments.of("d45d8d9d96873ba43661751f57b59fc4d0555e56", "1a8ce9934aba6a6e", "a3Yx2oVACgGN83PrasEc", "Test of 20 letters password"),
                Arguments.of("034afa3249f228b0f141db11518399a5bc70ab61", "7e5fe85efb72e120", "zB019m0H", "Test of 8 letters password"),
                Arguments.of("2101231085bbec8ae13b43a363622084189ce99b", "5e65e2f35601d421", "fnE90d8i5xEJ3HMK", "Test of 16 letters password"),
                Arguments.of("b2d351833023fc00c5d3d23e77ad8e1e3836250a", "513f63dafdfce07d", "DHM1grrbZvmTABM0PvpuVQodMdbsCa", "Test of 30 letters password"),
                Arguments.of("196d6ba0714367af6be2b3c3032e2bb6d26b6ed2", "2461cadd422c756a", "mrDNG8yUeaL4ewWoJBRE576pbh12ydqYXNZstNNBsZ0Yp", "Test of 45 letter password"));
    }
}