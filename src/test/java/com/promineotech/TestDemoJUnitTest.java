package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import com.promineo.TestDemo;
import static org.junit.jupiter.params.provider.Arguments.arguments;



class TestDemoJUnitTest {
	
	private TestDemo testDemo;

	@BeforeEach
	public void setUp() {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive") 

	public void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> 
			testDemo.addPositive(a, b))
			.isInstanceOf(IllegalArgumentException.class);
			
		}
	}

	static Stream<Arguments> argumentsForAddPositive(){
		return Stream.of(
			arguments(2, 4, 6, false),
			arguments(0, 4, 0, true),
			arguments(2, -4, 0, true),
			arguments(-2, -4, 0, true), 
			arguments(5, 3, 8, false)
			);
	}
	
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
		assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
		assertThat(testDemo.addPositive(10, 20)).isEqualTo(30);
		assertThat(testDemo.addPositive(3, 8)).isEqualTo(11);
	}
	
	@Test
	void assertThatTwoNumbersAreMultipliedCorrectlyThenDividedByAnotherNumber() {
		assertThat(testDemo.multiplyThenDivide(2, 4, 5)).isEqualTo(1.6);
		assertThat(testDemo.multiplyThenDivide(-2, 4, 5)).isEqualTo(-1.6);
		assertThat(testDemo.multiplyThenDivide(45, 55, 36)).isEqualTo(68.75);
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
}
