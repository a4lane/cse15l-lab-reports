# Lab Report 5

**OP:** There's something going wrong when I try to run the grading script; its worked for every other student submission, but running it on this one causes all these errors. I'm guessing it has to do with the students' files? Here's a screenshot of the symptoms:
![](symptom.png)


**TA:** It looks like a lot of these error messages have to do with the ListExamples.java file. Can you add some code to the bash script that checks if the file structure of each student submission is correct?




**OP:** Here's what I did:  
![](fixed-bug.png)
I added this if statement to check if the file ListExamples.java exists, and if not, print an error message and exit the script. It turns out that's what the issue was, here's the output from running the script with the same student submission:
![](fixed-output.png)
