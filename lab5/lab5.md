# Lab Report 5
## Part 1

**OP:** There's something going wrong when I try to run the grading script; its worked for every other student submission, but running it on this one causes all these errors. I'm guessing it has to do with the students' files? Here's a screenshot of the symptoms:
![](symptom.png)


**TA:** It looks like a lot of these error messages have to do with the ListExamples.java file. Can you add some code to the bash script that checks if the file structure of each student submission is correct?




**OP:** Here's what I did:  
![](fixed-bug.png)  

I added this if statement to check if the file ListExamples.java exists, and if not, print an error message and exit the script. It turns out that's what the issue was, here's the output from running the script with the same student submission:
![](fixed-output.png)

I assumed every student submission would have correctly named files, so I didn't include any checks for this in the bash script. Because there was no ListExamples.java, the junit tests in TestListExamples couldn't run correctly because they call ListExamples methods, and that's why I got the errors.



Setup:

directory structure: 

![](d-structure.png)


grade.sh before fixing the bug:
```
CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission
echo 'Finished cloning'

cp student-submission/ListExamples.java TestListExamples.java grading-area
cp -r lib grading-area

cd grading-area

javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java
java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore TestListExamples > junit-results.txt 


result=$( tail -n 2 junit-results.txt )
echo " "

if [[ $result == OK* ]]
then
    echo "GRADE: 100%"
elif [[ $result == *3*1 ]]
then
    echo "GRADE: 66%"
elif [[ $result == *3*2 ]]
then
    echo "GRADE: 33%"
else 
    echo "GRADE: 0"
fi

all_results+=$result
echo $all_results >> ../final-results.txt
```


TestListExamples.java:
```
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd1() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test
  public void testFilter1(){
    IsMoon sc = new IsMoon();
    List<String> input1 = Arrays.asList("a", "notmoon", "cow");
    List<String> expected = Arrays.asList();
    assertEquals(expected, ListExamples.filter(input1, sc));
  }

  @Test
  public void testFilter2(){
    IsMoon sc = new IsMoon();
    List<String> input2 = Arrays.asList("MOON", "notmoon", "moon");
    List<String> expected = Arrays.asList("MOON", "moon");
    assertEquals(expected, ListExamples.filter(input2, sc));
  }
}
```

Line that triggered the bug:  
![](command.png)


To fix the bug, I added this block of code:
```
if [[ ! -f ListExamples.java ]]
then    
    echo "ListExamples.java does not exist"
    exit
fi
```
to grade.sh directly after cloning the student submission.
