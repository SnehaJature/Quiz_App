package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quizapp.Category;

import androidx.annotation.Nullable;

import com.example.quizapp.QuizContract.QuestionsTable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quizapp.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized QuizDbHelper getInstance(Context context){
        if (instance == null){
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = " CREATE TABLE " +
                QuizContract.CategoriesTable.TABLE_NAME + "( " +
                QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE  " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable(){
        Category c1 = new Category("Programming");
        addCategory(c1);
        Category c2 = new Category("Geography");
        addCategory(c2);
        Category c3 = new Category("Math");
        addCategory(c3);
    }

    private void  addCategory(Category category){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Programming, Easy: Number of primitive data types in Java are?", "6", "7", "8", 3, Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q1);
        Question q2 = new Question("Geography, Medium: In which of the following states, the Sharavati Project is located?", "Andhra Pradesh", "Karnataka", "Tamil Nadu", 2, Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q2);
        Question q3 = new Question("Math, Hard: A line which cuts a pair of parallel lines is called", "chord", "traversal", "intersector", 3, Question.DIFFICULTY_HARD, Category.MATH);
        addQuestion(q3);
        Question q4 = new Question("Math, Easy: An angle whose value is ____, is called complete angle.", "180°", "240°", "360°", 3, Question.DIFFICULTY_EASY, Category.MATH);
        addQuestion(q4);
        Question q5 = new Question("Programming, Easy: Who is the father of C language?", "Steve Jobs", "James Gosling", "Dennis Ritchie", 3, Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q5);
        Question q6 = new Question("Programming, Medium: Which is valid C expression?", "int my_num = 100,000;", "int my_num = 100000;", "int $my_num = 10000;", 2, Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q6);
        Question q7 = new Question("Programming, Hard: Which of these cannot be used for a variable name in Java?", "identifier & keyword", "identifier", " keyword", 3, Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q7);
        Question q8 = new Question("Programming, Easy: Which statement is true about Java?", "Java is a sequence-dependent programming language", "Java is a platform-dependent programming language", " Java is a platform-independent programming language", 3, Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q8);
        Question q9 = new Question("Programming, Easy: Which of these cannot be used for a variable name in Java?", " identifier & keyword", "identifier", " keyword", 3, Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q9);
        Question q10 = new Question("Programming, Easy: What is the extension of java code files?", " .txt", ".class", " .java", 3, Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q10);
        Question q11 = new Question("Programming, Easy: Which environment variable is used to set the java path?", "JavaPATH", "JAVA", " JAVA_HOME", 3, Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q11);
        Question q12 = new Question("Programming, Medium: Which of the following is not an OOPS concept in Java?", "Polymorphism", "Inheritance", "Compilation", 3, Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q12);
        Question q13 = new Question("Programming, Medium: What is not the use of “this” keyword in Java?", "Referring to the instance variable when a local variable has the same name", "Passing itself to another method", " Passing itself to the method of the same class", 3, Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q13);
        Question q14 = new Question("Programming, Medium: Which exception is thrown when java is out of memory?", "MemoryError", "MemoryOutOfBoundsException", "OutOfMemoryError", 3, Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q14);
        Question q15 = new Question("Programming, Medium: Which exception is thrown when java is out of memory?", "MemoryError", "MemoryOutOfBoundsException", "OutOfMemoryError", 3, Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q15);
        Question q16 = new Question("Programming, Hard:  Which of the following is a superclass of every class in Java?", "ArrayList", "Abstract class", " Object class", 3, Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q16);
        Question q17 = new Question("Programming, Hard:   Which of the below is not a Java Profiler?", " JProfiler", "Eclipse Profiler", " JVM", 3, Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q17);
        Question q18 = new Question("Programming, Hard:   Which of these packages contains the exception Stack Overflow in Java?", " java.io", "java.system", "java.lang", 3, Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q18);
        Question q19 = new Question("Programming, Hard:   Which of these keywords are used for the block to be examined for exceptions?", " check", "catch", "try", 3, Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q19);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()){
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndexOrThrow(QuizContract.CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndexOrThrow(QuizContract.CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    @SuppressLint("Range")
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID,String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? "
                + " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
        Cursor c = db.query(
                QuestionsTable.TABLE_NAME ,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndexOrThrow(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}

