Hello, my name is Dwight Coles III, and this is a complex application that is coded
in a way to provide a geography quiz. The first scene of the quiz provides 4 buttons:
a play button, a previous results button, a help button, and a quit button. The help button
informs the user on what actions these buttons do. The quiz consist of 6 total questions with
three answer choices. After each quiz is complete, the results are stored in a file permenantly,
and you will be available to view these results any time at the end of a quiz or in the first window
of the quiz appplication with the previous results action. This project was also created using maven
which is a popular build tool for java.

Because we used the build tool Maven, compiling will be different.

To compile:
   - Starting in the project5 directory
   - $ mvn compile

After maven and your terminal run the compiler, you are now ready to test.

To test:
   - Starting in the project4 directory
   - $ mvn exec:java -Dexec.mainClass="edu.uga.cs1302.quiz.GeographyQuiz"
