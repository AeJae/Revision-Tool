# Revision Questions
An application that enables you to create question banks and test your knowledge quickly, aimed at use for revision.

## Get Started
1. Download the latest release [here](https://github.com/AeJae/Revision-Questions/releases).
2. Create your own personalised question bank using the examples below to correctly format a .txt file for the program to use.
3. Place this .txt file in the top level directory.
4. When asked from within the program, enter the name you gave your file (excluding the .txt).

You're good to go!

## Examples
- `&` is used to separate questions.
- `$` is used to separate sections within a question.
- `[]` is used to denote a list.
- `;` is used to separate items in a list.

### Multiple Choice
`MultiChoice$<Source>$<Question>$<Choices>$<Correct Choices>&`
```plaintext
MultiChoice$Lecture 1 Part 1$Here are three options:$[Option 1; Option 2; Option 3]$[1;3]&
```
`<Choices>` and `<Correct Choices>` must always be in list form, even if there is only one option. `<Correct Choices>` must be an integer representing the correct item(s) from the `<Choices>` list. It is NOT zero indexed.

### True or False
`TrueFalse$<Source>$<Question>$<Correct Option>&`
```plaintext
TrueFalse$Lecture 7 Part 1$This is a repository.$True&
```

### Text Input
`TextInput$<Source>$<Question/Fill Blank>$<Correct Entries>&`
```plaintext
TextInput$Lecture 5 Part 2$What is this question for?$[show;example;the readme]&
```
`<Correct Entries>` must always be in list form, even if there is only one option.

### A Short Completed File
```plaintext
MultiChoice$Lecture 1 Part 1$Here are three options:$[Option 1; Option 2; Option 3]$[1;3]&TrueFalse$Lecture 7 Part 1$This is a repository.$True&TextInput$Lecture 5 Part 2$What is this question for?$[show;example;the readme]&
```

## A Project by AJSF ([@AeJae](https://github.com/AeJae))
<a href="https://arun-fletcher.super.site/" target="_blank"><img src="https://super-static-assets.s3.amazonaws.com/46259e8a-18b0-450f-842a-c773f0c8f2ae/uploads/logo/aba94c59-2eb9-4c3e-bedd-857e4b7535b4.png" alt="Logo" width="70px"></a>
