package com.facebook.samples.learn;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Utility methods for models
 */
public class ModelUtils {
    private static ModelUtils ourInstance = null;

    public static ModelUtils getInstance() {
        if (ourInstance == null) {
            ourInstance = new ModelUtils();
        }
        return ourInstance;
    }

    private ArrayList<Subject> subjects;
    private ArrayList<Topic> topics;
    private ArrayList<Question> questions;
    private ArrayList<Score> scores;

    private ModelUtils() {
        subjects = new ArrayList<Subject>();
        topics = new ArrayList<Topic>();
        questions = new ArrayList<Question>();
        scores = new ArrayList<Score>();

        // Hard coding subject/topic/quiz info on creation
        Subject math = createSubject("Math", 0);
        Subject history = createSubject("History", 1);

        Topic algebra = createTopic("Algebra", math);
        Topic addition = createTopic("Addition", math);
        Topic subtraction = createTopic("Subtraction", math);

        Topic prehistoric = createTopic("Pre-historic", history);
        Topic modern = createTopic("Modern History", history);

        createQuestion(
                "1 + 1",
                addition,
                new ArrayList<String>(Arrays.asList("3", "10", "5", "2")),
                3);
        createQuestion(
                "5 + 3",
                addition,
                new ArrayList<String>(Arrays.asList("8", "7", "12", "20")),
                0);
        createQuestion(
                "13 + 6",
                addition,
                new ArrayList<String>(Arrays.asList("20", "17", "19", "20")),
                2);

        createQuestion(
                "6 - 2",
                subtraction,
                new ArrayList<String>(Arrays.asList("1", "0", "4", "3")),
                2);
        createQuestion(
                "25 - 9",
                subtraction,
                new ArrayList<String>(Arrays.asList("20", "16", "11", "14")),
                1);


        createQuestion(
                "4x = 3x + 5, what is x?",
                algebra,
                new ArrayList<String>(Arrays.asList("0", "5", "10", "7")),
                1);
        createQuestion(
                "4x = x + 9, what is x?",
                algebra,
                new ArrayList<String>(Arrays.asList("10", "2", "3", "11")),
                2);
        createQuestion(
                "5x = x + 4, what is x?",
                algebra,
                new ArrayList<String>(Arrays.asList("1", "2", "4", "3")),
                0);

        createQuestion(
                "What great event occurred in the year 776 B.C. in Europe?",
                prehistoric,
                new ArrayList<String>(Arrays.asList(
                        "Spanish Civil War",
                        "Second World War",
                        "Assassination of Julius Caesar",
                        "Olympic Games")),
                2);

        createQuestion(
                "Which country declared war on Britain and France on June 10, 1940?",
                modern,
                new ArrayList<String>(Arrays.asList("Germany", "Russia", "Italy", "Morocco")),
                2);
    }

    private Subject createSubject(String name, int order) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setOrder(order);
        subjects.add(subject);
        return subject;
    }

    private Topic createTopic(String name, Subject subject) {
        Topic topic = new Topic();
        topic.setName(name);
        topic.setSubjectId(subject.getId());
        topics.add(topic);
        return topic;
    }

    private void createQuestion(
            String name, Topic topic, ArrayList<String> choices, int answer) {
        Question question = new Question();
        question.setTopicId(topic.getId());
        question.setQuestion(name);
        question.setChoices(choices);
        question.setAnswer(answer);
        questions.add(question);
    }

    public ArrayList<Topic> getTopics(UUID subjectId) {
        ArrayList<Topic> filteredTopics = new ArrayList<Topic>();
        for (int i = 0; i < topics.size(); i++) {
            if (topics.get(i).getSubjectId() == subjectId) {
                filteredTopics.add(topics.get(i));
            }
        }
        return filteredTopics;
    }

    public ArrayList<Topic> getTopics(String subjectName) {
        ArrayList<Topic> filteredTopics = new ArrayList<Topic>();
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getName().equals(subjectName)) {
                filteredTopics = this.getTopics(subjects.get(i).getId());
                break;
            }
        }
        return filteredTopics;
    }

    public ArrayList<Question> getQuestions(UUID topicId) {
        ArrayList<Question> filteredQuizzes = new ArrayList<Question>();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getTopicId() == topicId) {
                filteredQuizzes.add(questions.get(i));
            }
        }
        return filteredQuizzes;
    }

    public ArrayList<Question> getQuestions(String topicName) {
        ArrayList<Question> filteredQuizzes = new ArrayList<Question>();
        for (int i = 0; i < topics.size(); i++) {
            if (topics.get(i).getName().equals(topicName)) {
                filteredQuizzes = this.getQuestions(topics.get(i).getId());
                break;
            }
        }
        return filteredQuizzes;
    }

    public ArrayList<Score> getScores(String topicName) {
        ArrayList<Score> filteredScores = new ArrayList<Score>();
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i).getTopicName().equals(topicName)) {
                filteredScores.add(scores.get(i));
            }
        }
        return filteredScores;
    }

    public void addScore(Context context, int topicScore, String topicName) {
        Score score = new Score();
        score.setScore(topicScore);
        score.setTopicName(topicName);
        scores.add(score);
        saveHighScore(context, topicName);
    }

    private void saveHighScore(Context context, String topicName) {
        ArrayList<Score> scores = getScores(topicName);
        int highScore = 0;
        for (Score score : scores) {
            if (score.getScore() > highScore) {
                highScore = score.getScore();
            }
        }
        // Save in shared preferences
        SharedPreferences preferences = context.getSharedPreferences(context
                .getResources().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(topicName, highScore);
        editor.commit();
    }

    public int getHighScore(Context context, String topicName) {
        SharedPreferences preferences = context.getSharedPreferences(context
                .getResources().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return preferences.getInt(topicName, 0);
    }
}
