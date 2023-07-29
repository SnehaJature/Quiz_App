package com.example.quizapp;

import android.provider.BaseColumns;

public class QuizContract {

    private QuizContract() {
    }

    public static class CategoriesTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_categories";
        public static final String COLUMN_NAME = "name";


            private int id;
            private String name;

            public CategoriesTable(int id, String name) {
                this.id = id;
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            // Other methods and properties of the CategoriesTable class


    }

    public static  class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION =  "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_CATEGORY_ID = "category_id";

    }
}
