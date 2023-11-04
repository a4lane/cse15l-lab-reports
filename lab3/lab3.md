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

Symptom:
![](symptoms.png)


The Bug- Before:
```
static void reverseInPlace(int[] arr) {
    for(int i = 0; i < arr.length; i += 1) {
      arr[i] = arr[arr.length - i - 1];
    }
  }
```

After:
```
 static void reverseInPlace(int[] arr) {
    for(int i = 0; i < arr.length / 2; i += 1) {
      int temp = arr[i];
      arr[i] = arr[arr.length - i - 1];
      arr[arr.length - i - 1] = temp;
    }
  }
```

The new code has a temp variable to store one of the elements being reversed so that its not overwritten before being swapped.
