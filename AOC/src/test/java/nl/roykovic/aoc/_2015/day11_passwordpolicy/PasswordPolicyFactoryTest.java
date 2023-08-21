package nl.roykovic.aoc._2015.day11_passwordpolicy;

import nl.roykovic.aoc._2015.day5_naughtystrings.NaughtyOrNiceFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordPolicyFactoryTest {

    @ParameterizedTest
    @CsvSource({
            "hijklmmn,false",
            "abbceffg,false",
            "abbcegjk,false",
            "abcdffaa, true",
            "ghjaabcc, true"
    })
    void testExampleIsValid(String input, boolean expectedIsValid) {
        assertEquals(expectedIsValid, PasswordPolicyFactory.isValid(input));
    }

    @ParameterizedTest
    @CsvSource({
            "hepxcrrq,hepxxyzz",
            "hepxxyzz,heqaabcc",
    })
    void testActualPassword(String password, String output){

        password = PasswordPolicyFactory.incrementPassword(password);
        while(! PasswordPolicyFactory.isValid(password)){
            password = PasswordPolicyFactory.incrementPassword(password);
        }

        assertEquals(output, password);
    }
}
