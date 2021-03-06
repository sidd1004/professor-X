package ai.profX.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import ai.profX.model.Character;
import ai.profX.model.Question;

public interface Game {
	public List<Question> getInitialQuestions(List<Question> initialQuestions);
	public LinkedHashMap<Long, Integer> initCharacterValues(LinkedHashMap<Long, Integer> characterValues);
	public LinkedHashMap<Long, Integer> sortCharacterValues(LinkedHashMap<Long, Integer> characterValues);
	public List<Character> getNearByCharacters(LinkedHashMap<Long, Integer> characterValues,int count);
	public LinkedHashMap<Long, Integer> getNearbyCharacterValues(LinkedHashMap<Long, Integer> characterValues,int count);
	public Double getId3Entropy(List<Character> characterList,Question question);
	public Question chooseQuestion(List<Question> initialQuestions, LinkedHashMap<Long, Integer> characterValues, HashMap<Long, Integer> askedQuestions, int count);
	public void updateLocalKnowledge(LinkedHashMap<Long, Integer> characterValues, HashMap<Long, Integer> askedQuestions, long questionId, int answer);
	public Character guess(LinkedHashMap<Long, Integer> characterValues);
	public long learnCharacter(HashMap<Long, Integer> askedQuestions, String text,Boolean finalAnswer);
	public void learn(HashMap<Long, Integer> askedQuestions, long charId,Boolean finalAnswer);
}
