# **Lab Report 3**
---
## Part 1 - Bugs

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

The buggy code was going through the entire array and swapping each element at i with the element at i - length - 1. This worked for the first length/2 elements in the array, but not for its second half because the first length/2 elements have already been overwritten and the original values lost. In the new solution, the loop iterates length/2 times and creates a temp variable that stores the value at index i, then overwrites index i with the value at index length - i- 1, then overwrites the value at index length - i - 1 with the the value of temp. 

## Part 2 -  Researching Commands: grep


***-c***
```
allisonlane@Allisons-Air docsearch % grep -c "terrorist" technical/911report/chapter-1.txt 
10
```
grep -c counts and displays the number of times a certain string appears in the given text file. This is useful because we can use wc, but that will count every word in a file and we may just want to know how many times one specific word is there. Its also useful for when we just want the count and don't need to see each line where the word is.
(Source: manual)

```
allisonlane@Allisons-Air docsearch % grep -c "terrorist" technical/911report/*.txt
technical/911report/chapter-1.txt:10
technical/911report/chapter-10.txt:24
technical/911report/chapter-11.txt:31
technical/911report/chapter-12.txt:101
technical/911report/chapter-13.1.txt:14
technical/911report/chapter-13.2.txt:6
technical/911report/chapter-13.3.txt:15
technical/911report/chapter-13.4.txt:28
technical/911report/chapter-13.5.txt:24
technical/911report/chapter-2.txt:19
technical/911report/chapter-3.txt:127
technical/911report/chapter-5.txt:29
technical/911report/chapter-6.txt:53
technical/911report/chapter-7.txt:9
technical/911report/chapter-8.txt:47
technical/911report/chapter-9.txt:11
technical/911report/preface.txt:3
```
Using grep -c on a group of text files prints the count of the number of occurences of the string in each respective file. This is useful instead of searching in each file one by one. 
 (Source: manual)

***-i***
```
allisonlane@Allisons-Air docsearch % grep -i "many" technical/government/Alcohol_Problems/Session2-PDF.txt
Many patients in the emergency department (ED) have alcohol
too many false positives. A test that is used to screen a
other drug use, depression, and anxiety disorders. Many of the
screening tests were developed outside the ED. Fortunately, many
Many injured ED patients are screened with a BAC, which can help
direct testing. Many tests would be improved by wording questions
even higher risk subgroups. Many experts advocate focusing
```
grep -i makes the search case-insensitive. This is useful when you want to search for a keyword but don't know if it will be at the beginning of sentences, or in some other casing convention.   
(Source: [https://developer.mozilla.org/en-US/blog/searching-code-with-grep/](https://developer.mozilla.org/en-US/blog/searching-code-with-grep/))

```
allisonlane@Allisons-MacBook-Air docsearch % grep -i "modular" technical/*/*.txt
technical/biomed/1471-2105-3-37.txt:        designed and developed the MASIA program and the modular
technical/biomed/1471-2156-3-17.txt:        . The modular nature of the NPC structure has also
technical/biomed/1471-2164-2-1.txt:          separate modular proteins that evolved by exon shuffling
technical/biomed/1471-2164-2-2.txt:          (AtDB) and analyzed using SMART (Simple Modular
technical/biomed/1471-2164-3-30.txt:        transmembrane segments [ 32 ] suggesting a modular gene
technical/biomed/1471-2164-4-22.txt:          NCBI as well as the Simple Modular Architecture Research
technical/biomed/1471-2199-2-10.txt:        domains are modular and can be effective when removed from
technical/biomed/1471-2199-2-10.txt:        The yeast two-hybrid system exploits this modular
technical/biomed/1471-2199-2-10.txt:        was modular, remaining a competent activator when grafted
technical/biomed/1471-2199-2-10.txt:          Modularity of a peptide activator
technical/biomed/1471-2199-2-10.txt:        modularity [ 21 ] . Nonetheless, the tendency for
technical/biomed/1472-6793-1-15.txt:        voltage sensor of skeletal muscle is modular in function
technical/biomed/gb-2001-2-4-research0010.txt:          modular power and/or the transition bias are varied over
technical/biomed/gb-2001-2-7-research0024.txt:          (Smart Modular Architecture Research Tool) program [ 56].
technical/biomed/gb-2001-2-9-research0035.txt:        curating information on physiological function and modular
technical/biomed/gb-2001-2-9-research0035.txt:          Proteins as modular entities
technical/biomed/gb-2001-2-9-research0035.txt:          proteins as modular entities where a module is defined as
technical/biomed/gb-2001-2-9-research0035.txt:          modular assignments are based on analysis of protein
technical/biomed/gb-2001-2-9-research0035.txt:          to four modules. Table 2contains a list of multimodular
technical/biomed/gb-2001-2-9-research0035.txt:          the list. The majority of modular proteins, 217, contain
technical/biomed/gb-2001-2-9-research0035.txt:          not shown). Other multimodular proteins appear to be a
technical/biomed/gb-2001-2-9-research0035.txt:          E. coli unimodular aspartokinase
technical/biomed/gb-2001-2-9-research0035.txt:          unimodular sequence-similar dehydrogenase. 
technical/biomed/gb-2001-2-9-research0035.txt:          does contain a unimodular sequence-similar homoserine
technical/biomed/gb-2001-2-9-research0035.txt:          be able to identify additional multimodular proteins.
technical/biomed/gb-2001-2-9-research0035.txt:          modular entities. Half of the modules have been
technical/biomed/gb-2001-2-9-research0035.txt:        modular entities where a module is at least 100 amino
technical/biomed/gb-2002-3-12-research0071.txt:          understanding of the modularity of genomes and of the
technical/biomed/gb-2002-3-5-research0025.txt:          modular structure that is easily adapted to bind
technical/biomed/gb-2002-3-9-research0046.txt:          that class. The packages ensure that MAGE-ML is modular,
technical/biomed/gb-2003-4-1-r5.txt:        constructed in a modular fashion, with
technical/biomed/gb-2003-4-2-r14.txt:          an example of the modular-type entities asserted to be
technical/biomed/gb-2003-4-8-r51.txt:        a common ancestral domain). This modular nature of proteins
technical/plos/journal.pbio.0020267.txt:        lead to the genes that control the development or function of those modules. That modular
technical/plos/journal.pbio.0020311.txt:        fungi in eukaryotes, these modular signaling systems involve a membrane-bound receptor
technical/plos/journal.pbio.0030024.txt:        functions seem more modular than global. And there is no real separation between the mind
```

A case-insensitive search for a string within multiple sub-directories. Results include instances of the string at the beginning of sentences (capitalized) as well as all lowercase instances.   
(Source: manual & [https://developer.mozilla.org/en-US/blog/searching-code-with-grep/](https://developer.mozilla.org/en-US/blog/searching-code-with-grep/))


***--colour***

```
allisonlane@Allisons-Air docsearch % grep --colour "Uruguay" technical/plos/journal.pbio.0020001.txt
        performed particularly well. For example, Uruguay, Chile, Panama, and Cuba averaged,
```
Screenshot to see colored text:
![](colour_1.png)

--colour colors the part of the line that matches the given string. Useful because it helps readability and quickly finding the target string in the output.
(Source: manual)

```
allisonlane@Allisons-Air docsearch % grep -C 2 --colour "perhaps" technical/government/Media/*.txt
technical/government/Media/Law_Schools.txt-one partner stacks pipe and cleans the yard at a plumbing
technical/government/Media/Law_Schools.txt-warehouse. Another handles urine samples in a hospital lab. A sign
technical/government/Media/Law_Schools.txt:of failure, of a feeble economy, perhaps? Hardly. They are heeding
technical/government/Media/Law_Schools.txt-the call of a growing pool of law schools, which are for the first
technical/government/Media/Law_Schools.txt-time pointing graduates in a new direction and teaching them how to
--
technical/government/Media/Law_Schools.txt-the dinners, taxi rides and other extras that are often tacked onto
technical/government/Media/Law_Schools.txt-the bill.
technical/government/Media/Law_Schools.txt:At those rates, perhaps it should come as no surprise that 71
technical/government/Media/Law_Schools.txt-percent of low-income people and 61 percent of moderate-income
technical/government/Media/Law_Schools.txt-people with civil troub les end up without representation,
--
technical/government/Media/Law_Schools.txt-not to teach them how to stay afloat financially and take on
technical/government/Media/Law_Schools.txt-low-income clients at the same time.
technical/government/Media/Law_Schools.txt:How do you advertise? How do you bill clients and, perhaps even
technical/government/Media/Law_Schools.txt-trickier, get them to pay? How can you remain affordable and still
technical/government/Media/Law_Schools.txt-pay your rent? What kind of office software will you need? How
--
technical/government/Media/Legal_Aid_in_Clay_County.txt-years.
technical/government/Media/Legal_Aid_in_Clay_County.txt-The only way to get a lawyer so cheaply will be to find one with
technical/government/Media/Legal_Aid_in_Clay_County.txt:limited experience, or perhaps one fresh out of law school with a
technical/government/Media/Legal_Aid_in_Clay_County.txt-strong desire to "provide justice to low-income people," Figgins
technical/government/Media/Legal_Aid_in_Clay_County.txt-said.
--
technical/government/Media/Lindsays_legacy.txt-Broker later began the Mediation Center in Savannah, with both
technical/government/Media/Lindsays_legacy.txt-serving on the nonprofit's first board.
technical/government/Media/Lindsays_legacy.txt:But, perhaps Lindsay's biggest contribution to Georgia Legal
technical/government/Media/Lindsays_legacy.txt-Services was the inspiration he provided its young lawyers.
technical/government/Media/Lindsays_legacy.txt-"Jim sat in on our weekly meetings, listening to people talk
--
technical/government/Media/Politician_Practices.txt-speculated that Barnes could go to work for any number of
technical/government/Media/Politician_Practices.txt-silk-stocking Atlanta firms for a seven-figure salary.
technical/government/Media/Politician_Practices.txt:So it is perhaps understandable that Steven Gottlieb, executive
technical/government/Media/Politician_Practices.txt-director of the Atlanta Legal Aid Society, initially thought it was
technical/government/Media/Politician_Practices.txt-a joke when he received a phone message from Governor Barnes saying
```
Screenshot to see colored text:
![](colour_2.png)
Combining --colour with added context lines makes it easer to find the target string when you have a few lines for each hit to read through.   
(Source: manual)



***-r***

```
allisonlane@Allisons-Air docsearch % grep -r "animal" technical/government
technical/government/Gen_Account_Office/og97051.txt:feed and, thereby, to minimize the risk to animals and humans.
technical/government/Gen_Account_Office/og97051.txt:This rule amends FDA regulations to provide that animal protein
technical/government/Gen_Account_Office/og97051.txt:action because ruminants are being fed protein derived from animals
technical/government/Gen_Account_Office/og97051.txt:annually. In contrast, nonruminant animal producers may gain up to
technical/government/Gen_Account_Office/og97052.txt:regulations concerning the importation of animal products to allow,
technical/government/Gen_Account_Office/og97052.txt:criteria for certain animal and plant products based on the level
technical/government/Gen_Account_Office/og97043.txt:regulations concerning the importation of animal products to allow,
technical/government/Gen_Account_Office/og97043.txt:criteria for certain animal and plant products based on the level
technical/government/Media/Abuse_penalties.txt:would get for cruelty and abuse toward an animal.
technical/government/Media/Attorney_gives_his_time.txt:wills but also adoptions, divorces and even animal control cases.
```
grep -r searches for the pattern recursively from the given directory. Here, it found the word "animal" inside several different subdirectories of technical/governemnt, without having to type each path seperately.  
(Source: [https://www.cyberciti.biz/tips/howto-see-grep-command-output-in-colours.html](https://www.cyberciti.biz/tips/howto-see-grep-command-output-in-colours.html))


```
allisonlane@Allisons-Air docsearch % grep -r "breadth" technical
technical/government/About_LSC/diversity_priorities.txt:disciplines while acknowledging the breadth of our communities. It
technical/government/Gen_Account_Office/Testimony_cg00010t.txt:increasing complexity and breadth of issues facing the Congress,
technical/government/Gen_Account_Office/GovernmentAuditingStandards_yb2002ed.txt:breadth of those inquiries and observations will vary among audits
technical/government/Gen_Account_Office/d01376g.txt:success to his breadth of experience across a variety of financial,
technical/government/Gen_Account_Office/pe1019.txt:breadth of information. If the purpose is to report what is
technical/government/Gen_Account_Office/Letter_Walkeraug17let.txt:the breadth of the statutory language, the NEPDG as chaired by the
technical/plos/journal.pbio.0020187.txt:        result for many decades, but the same cannot be said for breadth of access irrespective of
technical/plos/journal.pbio.0020147.txt:        bravely attempt here, makes this a hugely worthwhile book. Its breadth of scope and its
technical/plos/pmed.0020203.txt:        protection, and breadth of protection (there are at least four distinct Lassa virus
technical/plos/pmed.0010036.txt:          calculations of magnitude and breadth, as previously described [19].
technical/plos/pmed.0010036.txt:          HIV-1-specific CD8+ T cells, we longitudinally analyzed the breadth and magnitude of CD8+
technical/plos/pmed.0010036.txt:          sustained when therapy was reintroduced. A further increase in the magnitude and breadth
technical/plos/pmed.0010036.txt:          correlates positively with the magnitude and breadth of HIV-1-specific CD8+ T cell
technical/biomed/1471-2148-2-12.txt:          fundamental niche , diet breadth,
technical/biomed/1471-2148-2-12.txt:          survey of diet breadth using Biolog plates, this study
technical/biomed/1471-2148-2-12.txt:          Diet breadth narrows
technical/biomed/1471-2148-2-12.txt:          significant decline in diet breadth of an average of 25%
technical/biomed/1471-2148-2-12.txt:          diet breadth proved to be general and not limited to a
technical/biomed/1471-2148-2-12.txt:          are omitted from the nested ANOVA of diet breadth, then
technical/biomed/1471-2148-2-12.txt:          I evaluated diet breadth in a second, contrasting
technical/biomed/1471-2148-2-12.txt:        fundamental diet breadth are either
technical/biomed/1471-2148-2-12.txt:          Measurement of diet breadth
technical/biomed/1471-2148-2-12.txt:          to obtain estimates of catabolic diet breadth. These
technical/biomed/1471-2148-2-12.txt:          functionality on each resource. Diet breadth is expressed
technical/biomed/cc303.txt:        about the breadth or quality of these services in the rural
technical/biomed/1472-684X-2-1.txt:        means to impacting breadth of care. Future studies might
technical/biomed/1476-511X-1-2.txt:          the breadth of molecular responses to dietary LC-PUFA,
technical/biomed/1471-2105-3-22.txt:        expression were significantly limited in both breadth and
technical/biomed/1471-2105-3-2.txt:            these diagrams, to reveal the breadth of sequence
```
This command found the pattern recursively through the entire technical directory and subdirectories.
(Source: [https://www.cyberciti.biz/tips/howto-see-grep-command-output-in-colours.html](https://www.cyberciti.biz/tips/howto-see-grep-command-output-in-colours.html))










