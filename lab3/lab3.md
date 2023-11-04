# **Lab Report 3**
---
## Part 1: Bugs

Failure-inducing input:
```
@Test
  public void testReverseInPlace2(){
    int[] input2 = { 4, 5, 6, 7, 8 };
    ArrayExamples.reverseInPlace(input2);
    assertArrayEquals(new int[]{ 8, 7, 6, 5, 4 }, input2);
  }
```

Non-failure-inducing input:
```
@Test
  public void testReverseInPlace() {
    int[] input1 = { 3 };
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{ 3 }, input1);
}
```
