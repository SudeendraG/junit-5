package com.sudeendra.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sudeendra.basic.MathUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

// Nested test cases are used to group the test cases
// if one  test case fails entire test class will be failed


//@RepeatedTest(3)  --> Executes test case 3 times,
// Tag can be used to differentiate the unit and integration test cases...
// RUN -->Run Config -> JUNIT CONFIG --> CONFIGURE TAG to selectively run the particular tags
@DisplayName("Nested Classes Example")
class NestedTest {

  private MathUtils mathUtils;

  @BeforeEach
  void init() {
    mathUtils = new MathUtils();
  }

  @Nested
  @DisplayName("Nested TestCases Grouping....")
  class AddTest {

    @Test
    @DisplayName("Testing Divide Method")
    @Tag("Divide UNIT TEST CASE")
    void testDivide() {
      assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0),
          "Cannot Divide by zero");
    }

    @Test
    @DisplayName("Testing Add Method")
    @Tag("ADD UNIT TEST CASE")
    void testAdd() {
      /*
       * boolean isServerUp =false;
       * assumeTrue(isServerUp);
       *  if false skips this test case.
       * */
      int expected = 65;
      int actual = mathUtils.add(2, 3);
      // if you put message in lambda only prints if it fails
      assertEquals(expected, actual, () -> "It will execute only when test fails");
    }

  }

  @Test
  @RepeatedTest(3)
  void testRepeatTestExample(RepetitionInfo info) {
    info.getCurrentRepetition();
  }

}
