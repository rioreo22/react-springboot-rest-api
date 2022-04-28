package com.example.gccoffee.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class EmailTest {

    @Test
    void testEmail() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Email("acc"));
    }

    @Test
    void testValidEmail() {
        Email email = new Email("hello@gmail.com");
        assertThat(email.getAddress()).isEqualTo("hello@gmail.com");
    }

    @Test
    void testEqEmail() {
        Email email = new Email("hello@gmail.com");
        Email email2 = new Email("hello@gmail.com");
        assertThat(email.getAddress()).isEqualTo(email2.getAddress());
    }

}