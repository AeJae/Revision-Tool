# Revision Questions
An application that enables you to create question banks and test your knowledge quickly, aimed at use for revision.

To add your own questions, create a .txt file with a suitable name, place it in the top level directory, and use the template below to create your questions:

```plaintext
questionType,questionSource,questionTitle,[questionOptions]&questionType,questionSource,questionTitle,[questionOptions]&
questionType,questionSource,questionTitle,[questionOptions]&questionType,questionSource,questionTitle,[questionOptions]&
```
Then from within the program, when asked for the text file, enter the name you gave it (excluding the .txt).

## Examples

### Multiple Choice
`MultiChoice$<Source>$<Question>$<Choices>$<Correct Choices>&`
```plaintext
MultiChoice$Lecture 1 Part 1$Here are four options:$[Option 1, Option 2, Option 3, Option 4]$[Option 2, Option4]&
```

### True or False
`TrueFalse$<Source>$<Question>$<Correct Option>&`
```plaintext
TrueFalse$Lecture 7 Part 1$This is a repository.$True
```

### Text Input
`TextInput$<Source>$<Question/Fill Blank>$<Correct Entries>&`
```plaintext
TextInput$Lecture 5 Part 2$What is this question for?$[show,example,the readme]&
```

## A Project by AJSF ([@AeJae](https://github.com/AeJae))
<a href="https://arun-fletcher.super.site/"><img src="https://super-static-assets.s3.amazonaws.com/46259e8a-18b0-450f-842a-c773f0c8f2ae/uploads/logo/aba94c59-2eb9-4c3e-bedd-857e4b7535b4.png" alt="Logo" width="70px"></a>
