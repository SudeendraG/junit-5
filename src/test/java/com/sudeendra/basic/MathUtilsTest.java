package com.sudeendra.basic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

// PER_METHOD --->>  Once Instance of MathUtilsTest is created for each method this is default behavior
// PER_CLASS -->> Only one instance of MathUtilsTest on class loading... during this for BeforeAll and AfterAll static is Optional

/*
 * @EnabledOnOs(OS.LINUX)
 * @EnabledOnJre(JRE.JAVA11)
 * @EnableIf
 * @EnableIfSystemProperty
 * @EnableIfEnvironmentVariable
 *
 * Similar to Enable and Disable
 * Assumptions are useful to tell JUnit when to trigger the test cases.
 * */

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MathUtilsTest {

  private MathUtils mathUtils;
  private TestInfo info;
  private TestReporter report;

  // Executes Before Anything even executed here even before MathUtilsTest
  // since JUNIT doesn't have the instance to run , there is a restriction to use
  // before all and after all
  // we need to make methods as static

  @BeforeAll
  static void beforeAllInit() {
    System.out.println("This needs to run before all.....");
  }

  // executed Before each method getting executed
  @BeforeEach
  void init(TestInfo info, TestReporter report) {
    this.info = info;
    this.report = report;
    mathUtils = new MathUtils();
  }

  // After each method getting executeds
  @AfterEach
  void cleanup() {
    System.out.println("Cleaning up.....");
  }

  @Test
  @DisplayName("Testing Add Method")
  void testAdd() {
    /*
     * boolean isServerUp =false;
     * assumeTrue(isServerUp);
     *  if false skips this test case.
     * */
    int expected = 5;
    int actual = mathUtils.add(2, 3);
    assertEquals(expected, actual, "Add Method is to Add two Numbers....");
  }

  @Test
  @DisplayName("Testing Divide Method")
  void testDivide() {
    assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "Cannot Divide by zero");
  }

  @Test
  @DisplayName("TDD method Should not run")
  // Skips this test case while building  the jarsss
  @Disabled
  void testDisabled() {
    System.out.println("Disabled Test ,, Something in Progress dont run this test");
  }

  @Test
  @DisplayName("Multupiply")
  @Tag("Mul")
  void testMultiply() {
    //assertEquals(4,mathUtils.multiply(2, 2),"should return the product of two numbers");
    // Execute Multiple Conditions......
    // specific to junit 5
    // it not includes timestamp
    //System.out.println("Running"+info.getDisplayName()+"with Tags" +info.getTags());
    // it includes timestap
    report.publishEntry("Running" + info.getDisplayName() + "with Tags" + info.getTags());
    assertAll(
        () -> assertEquals(4, mathUtils.multiply(2, 2)),
        () -> assertEquals(1, mathUtils.multiply(1, 1)),
        () -> assertEquals(6, mathUtils.multiply(2, 3)),
        () -> assertEquals(0, mathUtils.multiply(0, 2))
    );
  }

}
