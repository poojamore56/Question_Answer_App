
package Application;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    static Scanner sc = new Scanner(System.in);
    static QuestionService service = new QuestionImpl() {
        @Override
        public List<Question> getAllQuestions(int questionId) {
            return null;
        }
    };

    public static void main(String[] args) {
        System.out.println("SELECT OPERATION --> ");
        System.out.println("1. ADD QUESTION ");
        System.out.println("2. REMOVE QUESTION ");
        System.out.println("3. UPDATED QUESTION");
        System.out.println("4. DISPLAY ALL QUESTIONS");
        System.out.println("5. TAKE TEST ");
        System.out.println("6. exit ");
        int ch = sc.nextInt() ;

        if (ch <5)
        {
            System.out.println("ENTER PASSWORD ");
            String pass = sc.next();
            if (!pass.equals("tiger"))
                System.exit(0);
        }

        switch (ch)
        {
            case 1:
                addQuestion() ;
                break;
            case 2:
                removeQuestion();
                break;
            case 3:
                updateQuestion();
                break;
            case 4:
                displayAllQuestion();
                break;
            case 5:
                takeTest();
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("INVALID INPUT ");
        }

        main(args);
    }

    private static void takeTest() {
        List<Question> questionList = service.getAllQuestions();
        int marks = 0 ;

        System.out.println("READY FOR TEST ");
        String ans = sc.nextLine();
        for (Question q : questionList)
        {
            System.out.println("Q"+ q.getQuestionId()+". "+q.getQuestion() );
            System.out.println("1. "+ q.getOption1());
            System.out.println("2. "+ q.getOption2());
            System.out.println("3. "+ q.getOption3());
            System.out.println("ENTER YOUR ANS ");
            ans = sc.nextLine();
            String actualAns = q.getAnswer();
            if (ans.equals(actualAns))
                marks+=5 ;
            else
                marks-=2 ;
        }
        System.out.println("\n\n\n\n-----------------------------------");
        System.out.println(" UR TOTAL MARKS ARE : "+ marks);
        System.out.println("-----------------------------------");
        System.exit(0);

    }

    private static void displayAllQuestion() {
        List<Question> questionList = service.getAllQuestions();
        for (Question q : questionList){
            System.out.println("Q"+ q.getQuestionId()+". "+q.getQuestion() );
            System.out.println("1. "+ q.getOption1());
            System.out.println("2. "+ q.getOption2());
            System.out.println("3. "+ q.getOption3());
            System.out.println("-----> "+ q.getAnswer());
            System.out.println("\n-------------------------------------\n");
        }


    }

    public static void addQuestion()
    {
        System.out.println("ENTER QUESTION -->");
        String question = sc.nextLine() ;
        question = sc.nextLine() ;

        System.out.println("ENTER OPTION 1 ");
        String option1 = sc.nextLine() ;

        System.out.println("ENTER OPTION 2 ");
        String option2 = sc.nextLine() ;

        System.out.println("ENTER OPTION 3 ");
        String option3 = sc.nextLine() ;

        System.out.println("ENTER ANSWER ");
        String answer = sc.nextLine() ;

        Question newQuestion = new Question(question , option1 , option2 , option3 , answer);
        int n = service.addQuestions(newQuestion);
        System.out.println(n+" RECORD INSERTED !!");
        System.out.println("\n\n");
    }


    public static void removeQuestion()
    {
        System.out.println("ENTER THE QUESTION ID ");
        int questionId = sc.nextInt() ;

        int n = service.removeQuestions(questionId);
        System.out.println(n + "RECORD DELETED ");
        System.out.println("\n\n");
    }

    public static void updateQuestion(){
        System.out.println("1. MODIFY QUESTION ");
        System.out.println("2. MODIFY OPTIONS ");
        System.out.println("3. <- back ");
        int ch = sc.nextInt();
        switch (ch){
            case 1:
                 modifyQuestion() ;
                break;
            case 2:

                modifyOptions();
                break;
            case 3 :
                return;

        }

        updateQuestion();

    }



    private static  void modifyQuestion()
    {
        System.out.println("Enter Question Id");
        int questionId=sc.nextInt();
        System.out.println("Enter Question");
        String ques=sc.nextLine();
        ques=sc.nextLine();

        int n=service.modifyQuestion(questionId,ques);
        System.out.println(n+"Question Updated");
        System.out.println("\n\n");

    }

    private static  void modifyOptions(){
        System.out.println("Enter Question Id");
        int questionId=sc.nextInt();
        List<Question> displayQuestionList=service.getAllQuestions(questionId);
        System.out.println("Question_Id\t|Question\t| Option1 \t| Option2 \t| Option3 \t|Answer");
        for (Question q:displayQuestionList){
            System.out.println(
                    q.getQuestionId() +"\t|"+q.getQuestion()+"\t|"+q.getOption1()+"\t|"+q.getOption2()+"\t|"+q.getOption3()+"\t|"+q.getAnswer()
            );
            System.out.println("Enter Option");
            String opt1=sc.nextLine();
            opt1=sc.nextLine();
            System.out.println("Enter Option2");
            String opt2=sc.nextLine();
            System.out.println("Enter Option3");
            String opt3=sc.nextLine();
            System.out.println("Enter Answer");
            String ans=sc.nextLine();
            Question newQuestion=new Question(q.getQuestion(),opt1,opt2,opt3,ans);

            int n=service.modifyOption(newQuestion,questionId);
            System.out.println(n+"Option modified!!");
            System.out.println("\t\t");
        }

    }


}
